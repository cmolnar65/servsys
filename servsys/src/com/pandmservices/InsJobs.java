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


public class InsJobs
{
        private int recnum;
	private int cnum;
	private String jobnum;
	private String description;
	private String shophrs;
	private String fieldhrs;
	private String elechrs;
	private String plmhrs;
	private String salesperson;
	private String price;

        public InsJobs (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from jobs where recnum='"+recnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recnum=rs.getInt("recnum");
		this.cnum=rs.getInt("cnum");
		this.jobnum=rs.getString("jobnum");
		this.description = rs.getString("description");
		this.shophrs = rs.getString("shophrs");
		this.fieldhrs = rs.getString("fieldhrs");
		this.elechrs=rs.getString("elechrs");
		this.plmhrs=rs.getString("plmhrs");
		this.salesperson=rs.getString("salesperson");
		this.price=rs.getString("price");
	}



	public static Vector getIndItem(Connection c, int recnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM jobs where recnum="+recnum+" order by jobnum");
		while(rs.next())
		{
			
			InsJobs t = new InsJobs(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllItems(Connection c, int cnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM jobs where cnum="+cnum+" order by jobnum");
		while(rs.next())
		{
			
			InsJobs t = new InsJobs(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM jobs order by jobnum");
		while(rs.next())
		{
			
			InsJobs t = new InsJobs(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From jobs Where recnum=" + x + ";");
	}
	  

	public static void UpdateItem(Connection con, int recnum, int cnum, String jobnum, String desc, String shophrs, String fieldhrs, String elechrs, String plmhrs, String salesperson, String price)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update jobs Set jobnum='" +jobnum+ "', description ='"+desc +"',shophrs  ='"+shophrs +"', fieldhrs ='"+fieldhrs +"',elechrs  ='"+elechrs +"',plmhrs  ='"+plmhrs +"',salesperson  ='"+salesperson +"', price ='"+price +"',cnum  ='"+cnum +"' Where recnum=" + recnum + ";");
      	}

public static void addJob(Connection con, int cnum, String jobnum, String desc, String shophrs, String fieldhrs, String elechrs, String plmhrs, String salesperson, String price)
                throws SQLException
		        {
	                Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO jobs (cnum, jobnum, description, shophrs, fieldhrs, elechrs, plmhrs, salesperson, price) Values ('" + cnum + "','" +jobnum+ "','"+ desc + "','"+shophrs + "','"+ fieldhrs+ "','" +elechrs + "','"+ plmhrs + "','"+ salesperson +"', '"+price+"')");
		        }

        public int getRecNum() { return recnum; }
        public int getCustNum() { return cnum; }

        public String getJobNum() { return jobnum; }
	public String getDesc() { return description; }
	public String getShopHrs() { return shophrs; }
	public String getFieldHrs() { return fieldhrs; }
	public String getElecHrs() { return elechrs; }
	public String getPlmHrs() { return plmhrs; }
	public String getSalesPerson() { return salesperson; }
        public String getPrice() { return price; }

}
