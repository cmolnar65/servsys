package com.pandmservices; 
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class UniCustomer
{
        private int id;
	private String caddress1;
	private String caddress2;
	private String custname;
	private String cusnum;
	private String ccity;
	private String cstate;
	private String czip;
	private String chphone;
	private String caltphone;
	private String cust_notes;
	private String aguid;
        private String description="";     //default value to null
        private boolean done = false;      //default value to false
        private String accountname="";     //default value to null
	private String cemail;
	private String custsite;
	private String sitenum;
	private String custtype;
	private String custnotes;
	private int servsync;

        public UniCustomer (Connection c, int custnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from customers where custnum='"+custnum+"' order by cname;");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.cusnum=rs.getString("custnum");
		this.custname = rs.getString("cname");
		this.caddress1 = rs.getString("address1");
		this.caddress2 = rs.getString("address2");
		this.ccity=rs.getString("city");
		this.cstate=rs.getString("state");
		this.czip=rs.getString("zip");
		this.chphone=rs.getString("homephone");
		this.caltphone=rs.getString("altphone");
		this.cemail=rs.getString("cemail");
		this.custsite=rs.getString("custsite");
		this.sitenum=rs.getString("sitenum");
		this.custnotes=rs.getString("cust_notes");
		this.custtype=rs.getString("custtype");
		this.servsync=rs.getInt("servsync");
	}


	public static Vector getLikeItem(Connection c, String cname, String caddress1, String ccity, String cstate, String chphone)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT custnum FROM customers where cname='"+cname+"' and address1='"+caddress1+"' and city='"+ccity+"' and state='"+cstate+"';");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getIndItem(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT custnum FROM customers where custnum='"+custnum+"';");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getUnSynced(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT custnum FROM customers where servsync=0 or servsync is NULL and custsite not NULL;");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}
	
	public static Vector getNoSiteNum(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from customers where sitenum='' or sitenum=NULL or custsite='' or custsite=NULL order by cname;");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}


	
	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from customers order by custnum;");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllMatching(Connection c, String custsite, String sitenum, String address1, String city, String zip)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM customers where (address1 like '%"+address1+"%' and city like '%"+city+"%' and zip='"+zip+"') or  ( sitenum='"+sitenum+"' and custsite='"+custsite+"') order by cname");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllAddress(Connection c, String scity)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT custnum, cname, address1, city, state FROM customers where address1 like '%"+scity+"%' order by cname");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getCustNumSite(Connection c, String custnum, String sitenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT custnum, cname, address1, city, state FROM customers where custsite='"+custnum+"' and sitenum='"+sitenum+"' order by cname");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getCustSite(Connection c, String custsite)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT custnum, cname, address1, city, state FROM customers where custsite='"+custsite+"' order by cname");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllCity(Connection c, String scity)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT custnum, cname, address1, city, state FROM customers where city like '"+scity+"' order by cname");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllItems(Connection c, String custstart, String custstop)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT custnum, cname, address1, city, state FROM customers where cname between '"+custstart+"' and '"+custstop+"' order by cname");
		while(rs.next())
		{
			UniCustomer t = new UniCustomer(c,rs.getInt("custnum"));
			V.addElement(t);
		}
		return V;
	}

	public static int getMaxID(Connection c)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id FROM todo ORDER BY id;");
		int idlarge = 0;	
		while(rs.next())
		  {
			idlarge = rs.getInt("id");
		  }

		return idlarge;

	}

	public static String getAccountID(Connection c, String TAName)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT custnum FROM customer where cusname='"+TAName+"';");
		String accountguid = null;	
		while(rs.next())
		  {
			accountguid = rs.getString("accountguid");
		  }

		return accountguid;

	}


	public static void deleteItem(Connection con, int x)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From customers Where custnum=" + x + ";");
	}
	  

	public static void changeItemSynced(Connection con, int id)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update customers Set servsync=1 Where custnum=" + id + ";");
      	}

	public static void UpdateItem(Connection con, int custnum, String cname, String address1, String address2, String city, String state, String zip, String homephone, String altphone, String cust_notes, String cemail, String custsite, String sitenum, String custtype)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update customers Set cname='" +cname+ "', address1 ='"+address1 +"',address2  ='"+address2 +"', city ='"+city +"',state  ='"+state +"',zip  ='"+zip +"',homephone  ='"+homephone +"', altphone ='"+altphone +"',cust_notes  ='"+cust_notes +"', cemail='"+cemail+"', custsite='"+custsite+"', sitenum='"+sitenum+"', custtype='"+custtype+"' Where custnum=" + custnum + ";");
      	}

public static void addCustomer(Connection con, String cname, String Address1, String Address2, String City, String State, String Zip, String homephone, String altphone, String cust_notes, String cemail, String custsite, String sitenum, String custtype)
                throws SQLException
		        {
			String tcname = cname.replaceAll("'","''");
	                Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO customers (cname, address1, address2, city, state, zip, homephone, altphone, cust_notes, cemail,custsite,sitenum,custtype,servsync) Values ('" + tcname + "','" +Address1+ "','"+ Address2 + "','"+ City + "','"+ State + "','" + Zip + "','"+ homephone + "','"+ altphone +"', '"+cust_notes+"', '"+cemail+"','"+custsite+"','"+sitenum+"','"+custtype+"',0)");
		        }

        public int getId() { return id; }
	public int getServSync() { return servsync; }

        public String getAddress1() { return caddress1; }
        public String getAddress2() { return caddress2; }
        public String getCustSite() { return custsite; }
        public String getSiteNum() { return sitenum; }
        public String getHomePhone() { return chphone; }
        public String getAltPhone() { return caltphone; }
	public String getCity() { return ccity; }
	public String getState() { return cstate; }
	public String getZip() { return czip; }
	public String getAGuId() { return aguid; }
	public String getCustomerName() { return custname; }
	public String getCustomerNotes() { return custnotes; }
	public String getCusNum() { return cusnum; }
	public String getCEmail() { return cemail; }
	public String getCustType() { return custtype; }
        public boolean getDone() { return done; }


        public void setId(int id) { this.id = id; }
        public void setDescription(String desc) { this.description = desc; }
        public void setDone(boolean done) { this.done = done; }
}
