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


public class InsJobStatus
{
        private int recnum;
	private int custnum;
	private int jobrecnum;
	private String description;
	private String jobstatus;
	private String statusdate;

        public InsJobStatus (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from jobstatus where recnum='"+recnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recnum=rs.getInt("recnum");
		this.custnum=rs.getInt("custnum");
		this.jobrecnum=rs.getInt("jobrecnum");
		this.statusdate=rs.getString("statusdate");
		this.jobstatus=rs.getString("jobstatus");
		this.description = rs.getString("description");
	}



	public static Vector getIndItem(Connection c, int recnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM jobs where recnum="+recnum+" order by jobrecnum");
		while(rs.next())
		{
			
			InsJobStatus t = new InsJobStatus(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllItems(Connection c, int jobrecnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM jobstatus where jobrecnum="+jobrecnum+" order by statusdate");
		while(rs.next())
		{
			
			InsJobStatus t = new InsJobStatus(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM jobstatus order by custnum, jobrecnum");
		while(rs.next())
		{
			
			InsJobStatus t = new InsJobStatus(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static void deleteItem(Connection con, int d)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From jobstatus Where recnum=" + d + ";");
	}
	  

public static void addJobStatus(Connection con, int custnum, int jobrecnum, String statusdate, String jobstatus, String description)
                throws SQLException
		        {
	                Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO jobstatus (custnum, jobrecnum, statusdate, jobstatus, description) Values ('" + custnum + "','" +jobrecnum+ "','"+statusdate+"','"+ jobstatus + "','"+description+ "')");
		        }

        public int getRecNum() { return recnum; }
        public int getCustNum() { return custnum; }
        public int getJobRecNum() { return jobrecnum; }
	public String getDescription() { return description; }
	public String getJobStatus() { return jobstatus; }
	public String getStatusDate() { return statusdate; }

}
