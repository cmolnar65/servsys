package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;

public class UniRemind
{
        private int reminderguid;
	private String duedate;
	private String reminddate;
	private String shortdesc;
	private String notes;
	private String user;

        private String description="";     //default value to null
        private boolean allusers = false;      //default value to false
    

        public UniRemind (Connection c, int reminderguid)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT reminderguid, duedate as fduedate, reminddate as freminddate, shortdesc, notes FROM tblreminders WHERE reminderguid=" + reminderguid + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + reminderguid);
		}
		this.reminderguid = rs.getInt("reminderguid");
		this.notes = rs.getString("notes");
		this.shortdesc = rs.getString("shortdesc");
		this.reminddate = rs.getString("freminddate");
		this.duedate = rs.getString("fduedate");
	}



	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT reminderguid, reminddate FROM tblreminders ORDER BY reminddate;");
		while(rs.next())
		{
			
			UniRemind t = new UniRemind(c,rs.getInt("reminderguid"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getSingleItem(Connection c, int reminderguid)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT reminderguid, reminddate FROM tblreminders WHERE reminderguid='"+reminderguid+"' ORDER BY reminddate;");
		while(rs.next())
		{
			
			UniRemind t = new UniRemind(c,rs.getInt("reminderguid"));
			V.addElement(t);
		}
		return V;
	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From tblreminders Where reminderguid=" + x + ";");
	}
	  

	public static void changeItem(Connection con, int id)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update todo Set Done=true Where ID=" + id + ";");
      	}

	public static void UpdateItem(Connection con, String duedate, String reminddate, String shortdesc, String notes, String remindid)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update tblreminders Set notes='"+notes+"', shortdesc='"+shortdesc+"', reminddate='"+reminddate+  "', duedate='"+duedate+"' Where reminderguid='" + remindid + "';");
      	}


	public static void addRemindItem(Connection con, String duedate, String reminddate, String shortdesc, String notes)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
			Calendar now = Calendar.getInstance();
                        int hour = now.get(Calendar.HOUR_OF_DAY);
			int second = now.get(Calendar.SECOND);
			int year = now.get(Calendar.YEAR);
			int minute = now.get(Calendar.MINUTE);
			int millisecond = now.get(Calendar.MILLISECOND);
			stmt.executeUpdate("INSERT INTO tblreminders Values ('','"+duedate+"','" + reminddate +"','"+shortdesc+"','"+notes+"');");
		        }


        	public boolean getAllUsers() { return allusers ; }
		public int getReminderguid() { return reminderguid;}
		public String getNotes() { return notes; }
		public String getUser() { return user; }
		public String getShortDesc() { return shortdesc; }
		public String getReminddate() { return reminddate; }
		public String getDuedate() { return duedate; }
        	public void setDescription(String desc) { this.description = desc; }
}
