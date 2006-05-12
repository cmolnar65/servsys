package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniWorksheet
{
        private int id;
        private int wsrec=0;     //default value to null
        private int crec=0;     //default value to null
        private String wsdate=null;     //default value to null
        private String wsdesc=null;     //default value to null
	private String wsmult=null;
	private String custsite=null;
	private String sitenum=null;
	private String techid=null;
	private int servsync=0;
    

        public UniWorksheet (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM worksheet WHERE wsrec=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.wsrec = rs.getInt("wsrec");
		this.crec = rs.getInt("crec");
		this.wsmult = rs.getString("wsmult");
		this.wsdate = rs.getString("wsdate");
		this.wsdesc = rs.getString("wsdesc");
		this.custsite=rs.getString("custsite");
		this.sitenum=rs.getString("sitenum");
		this.techid=rs.getString("techid");
		this.servsync=rs.getInt("servsync");
	}



	public static Vector getAllItems(Connection c, int crec)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT wsrec  FROM worksheet where crec='"+crec+"' ORDER BY wsdate;");
		while(rs.next())
		{
			
			UniWorksheet t = new UniWorksheet(c,rs.getInt("wsrec"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllDateItems(Connection c, String wsdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT wsrec  FROM worksheet where wsdate='"+wsdate+"' ORDER BY wsdate;");
		while(rs.next())
		{
			
			UniWorksheet t = new UniWorksheet(c,rs.getInt("wsrec"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getIndItem(Connection c, int wsrec)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT wsrec  FROM worksheet where wsrec="+wsrec+"  ORDER BY wsdate;");
		while(rs.next())
		{
			
			UniWorksheet t = new UniWorksheet(c,rs.getInt("wsrec"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndItem(Connection c, int crec, int wsrec)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT wsrec  FROM worksheet where crec='"+crec+"' and wsrec="+wsrec+"  ORDER BY wsdate;");
		while(rs.next())
		{
			
			UniWorksheet t = new UniWorksheet(c,rs.getInt("wsrec"));
			V.addElement(t);
		}
		return V;
	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From worksheet Where wsrec=" + x + ";");
		stmt.executeUpdate("Delete From wsitem Where wsrec=" + x + ";");
	}
	
		public static void deleteAllCustItems(Connection con, String custsite, String sitenum)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From worksheet Where custsite=" + custsite + " and sitenum='"+sitenum+"';");
		stmt.executeUpdate("Delete From wsitem Where custsite=" + custsite + " and sitenum='"+sitenum+"';");
	}
	
		public static void deleteAllNoCustSite(Connection con)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From worksheet Where custsite=NULL and sitenum=NULL;");
		stmt.executeUpdate("Delete From wsitem Where custsite=NULL and sitenum=NULL;");
	}

	public static void UpdateItem(Connection con, int wsrec, int crec, String wsdate, String wsdescription, String wsmult)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update worksheet Set crec='" +crec+ "',wsdate = '"+wsdate+"',wsdesc = '"+wsdescription +"', wsmult='"+wsmult+"', servsync='9' Where wsrec=" + wsrec + ";");
      	}

public static void AddItem(Connection con, int crec, String wsdate, String wsdescription, String wsmult, String custsite, String sitenum, String techid, int servsync)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                //System.out.println("INSERT INTO worksheet (crec, wsdate, wsdesc, wsmult) Values ('"+crec+"','" +wsdate+ "','" +wsdescription+ "', '"+wsmult+"')");
	                stmt.executeUpdate("INSERT INTO worksheet (crec, wsdate, wsdesc, wsmult, custsite, sitenum, techid, servsync, wsnotes) Values ('"+crec+"','" +wsdate+ "','" +wsdescription+ "', '"+wsmult+"', '"+custsite+"','"+sitenum+"','"+techid+"','"+servsync+"','-')");
		        }

	public static Vector getMaxItem(Connection c)
		throws SQLException, TodoException
		{
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("Select wsrec,max(wsrec) from worksheet group by wsrec;");
		while(rs.next())
	{
		UniWorksheet t=new UniWorksheet(c,rs.getInt("wsrec"));
		V.addElement(t);
	}
		return V;
	}



        public int getId() { return id; }

        public int getWsRec() { return wsrec; }
        public int getCrec() { return crec; }
        public String getWsMult() { return wsmult; }
        public String getWsDate() { return wsdate; }
        public String getWsDesc() { return wsdesc; }
	public String getSiteNum() { return sitenum; }
	public String getCustSite() { return custsite; }
	public String getTechId() { return techid; }
	public int getServSync() { return servsync; }

}
