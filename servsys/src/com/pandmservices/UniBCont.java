package com.pandmservices;
import java.sql.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.util.Date;
import java.security.*;

public class UniBCont
{
        private String contactguid;
	private String customerguid;
	private String contactname;
	private String contactphone;
	private String contactfax;
        private String contacthomephone;   
	private String contactweb;
	private String contactemail;
	private String contactnotes;
	private String contactpager;

        public UniBCont (Connection c, String contactguid)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT contactguid, customerguid, contactname, contactphone, contactfax, contacthomephone, contactweb, contactemail, contactnotes, contactpager FROM buscontacts WHERE contactguid='" + contactguid + "';");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + contactguid);
		}
		this.contactguid = rs.getString("contactguid");
		this.contactname = rs.getString("contactname");
		this.contactphone = rs.getString("contactphone");
		this.contactfax = rs.getString("contactfax");
		this.contacthomephone = rs.getString("contacthomephone");
		this.contactweb = rs.getString("contactweb");
		this.contactemail = rs.getString("contactemail");
		this.contactnotes = rs.getString("contactnotes");
		this.contactpager = rs.getString("contactpager");
		this.customerguid = rs.getString("customerguid");
	}


	public static Vector getSingleItem(Connection c, String contactguid, String customerguid)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT contactguid, contactname  FROM buscontacts Where contactguid='"+contactguid+"' ORDER BY contactname;");
		while(rs.next())
		{
			
			UniBCont t = new UniBCont(c,rs.getString("contactguid"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getCustItems(Connection c, String customerguid)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT contactguid, contactname  FROM buscontacts Where customerguid='"+customerguid+"' ORDER BY contactname;");
		while(rs.next())
		{
			
			UniBCont t = new UniBCont(c,rs.getString("contactguid"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT contactguid, contactname  FROM buscontacts ORDER BY contactname;");
		while(rs.next())
		{
			
			UniBCont t = new UniBCont(c,rs.getString("contactguid"));
			V.addElement(t);
		}
		return V;
	}


	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From buscontacts Where contactguid='" + d + "';");
	}
	  


	public static void UpdateItem(Connection con, String contactguid, String contactname, String contactphone, String contactfax, String contactpager, String contacthomephone, String contactnotes, String contactemail, String contactweb)
	
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update buscontacts Set contactname='" + contactname +"',contactphone='"+contactphone+"',contactfax='"+contactfax+"',contactpager='"+contactpager +"',contacthomephone='"+contacthomephone+"',contactnotes='"+contactnotes+"',contactemail='"+contactemail+"',contactweb='"+contactweb+"' Where contactguid='" + contactguid + "';");
      	}


	public static void addItem(Connection con, String customerguid, String contactname, String contactphone, String contactfax, String contactpager, String contacthomephone, String contactnotes, String contactemail, String contactweb)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();

			Calendar now = Calendar.getInstance();
                        int hour = now.get(Calendar.HOUR_OF_DAY);
			int second = now.get(Calendar.SECOND);
			int year = now.get(Calendar.YEAR);
			int minute = now.get(Calendar.MINUTE);
			int millisecond = now.get(Calendar.MILLISECOND);
		        String contactguid=UniMD5.doMakeMD5("b"+hour+minute+second+millisecond+year+UniCash.getRandomNumber(33));

			stmt.executeUpdate("INSERT INTO buscontacts (contactguid, customerguid, contactname, contactphone, contactfax, contactpager, contacthomephone, contactnotes, contactemail, contactweb) Values ('" + contactguid + "','"+customerguid+"','" + contactname +"','"+contactphone+"','"+contactfax+"','"+contactpager +"','"+contacthomephone+"','"+contactnotes+"','"+contactemail+"','"+contactweb+"')");

		        }


        //public boolean getDone() { return done; }


		public String getContactguid() { return contactguid;}
		public String getCustomerguid() { return customerguid;}
		public String getContactname() { return contactname;}
		public String getContactphone() { return contactphone;}
		public String getContactfax() { return contactfax;}
		public String getContactpager() { return contactpager;}
		public String getContacthomephone() { return contacthomephone;}
		public String getContactnotes() { return contactnotes;}
		public String getContactemail() { return contactemail;}
		public String getContactweb() { return contactweb;}

}
