package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class CallslipNotes
{
        private String callslip=null;     //default value to null
        private String note=null;     //default value to null 
	private int servsync;
	private String ndate=null;
	private String custnum=null;
	private String sitenum=null;
	private String userlogin=null;
	private int recnum;

        public CallslipNotes (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM callnotes WHERE recnum=" + recnum + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.servsync = rs.getInt("servsync");
		this.callslip = rs.getString("callslip");
		this.note = rs.getString("note");
		this.userlogin = rs.getString("userlogin");
		this.ndate = rs.getString("ndate");
		this.custnum = rs.getString("custnum");
		this.sitenum = rs.getString("sitenum");
		this.recnum=rs.getInt("recnum");
	}



	public static Vector getAllItems(Connection c, String custnum, String sitenum, String callslip)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum, ndate FROM callnotes where callslip='"+callslip+"' and custnum='"+custnum+"' and sitenum='"+sitenum+"' ORDER BY ndate;");
		while(rs.next())
		{
			CallslipNotes t = new CallslipNotes(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndItem(Connection c, String custnum, String sitenum, String userlogin, String callslip, String ndate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT custnum, sitenum, callslip, userlogin, ndate FROM callnotes where callslip='"+callslip+"' and ndate='"+ndate+"' and userlogin='"+userlogin+"' ORDER BY descript;");
		while(rs.next())
		{
			CallslipNotes t = new CallslipNotes(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, String custnum, String sitenum, String userlogin, String callslip, String ndate, String note, int servsync)

		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update callnotes Set callslip='" +callslip+ "', note='"+note+"', ndate='"+ndate+"', price='"+userlogin+"' Where custnum='" + custnum + "' and sitenum='" + sitenum + "' and userlogin='"+userlogin+"' and callslip='"+callslip+"' and ndate='"+ndate+"';");
      	}

	public static void AddItem(Connection con, String custnum, String sitenum, String userlogin, String callslip, String ndate, String note, int servsync)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO callnotes (custnum, sitenum, userlogin, ndate, callslip, note, servsync) Values ('" + custnum + "','" + sitenum + "','"+userlogin+"','"+ndate+"', '"+callslip+"', '"+note+"', '"+servsync+"')");
		        }
			
	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From callnotes Where recnum='" + x + "';");
	}


       public int getServsync() { return servsync; }
	public String getUserLogin() { return userlogin; }
        public String getCallslip() { return callslip; }
        public String getNote() { return note; }
	public String getCustNum() { return custnum; }
	public String getSiteNum() { return sitenum; }
	public String getNDate() { return ndate; }
	public int getRecNum() { return recnum; }	

}
