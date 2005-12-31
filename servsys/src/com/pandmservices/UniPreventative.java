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


public class UniPreventative
{
        private int planrec;
	private String description;
	private String tm_est;
	private String yr_1;
	private String yr_2;
	private String yr_3;

        public UniPreventative(Connection c, int planrec)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from prevprices where planrec='"+planrec+"' order by descript;");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + planrec);
		}
		this.planrec=rs.getInt("planrec");
		this.description = rs.getString("descript");
		this.tm_est = rs.getString("tm_est");
		this.yr_1=rs.getString("yr1");
		this.yr_2=rs.getString("yr2");
		this.yr_3=rs.getString("yr3");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM prevprices order by descript");
		while(rs.next())
		{
			
			UniPreventative t = new UniPreventative(c,rs.getInt("planrec"));
			V.addElement(t);
		}
		return V;
	}


	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From Todo Where ID=" + x + ";");
	}
	  

	public static void UpdateItem(Connection con, int id, String desc)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update todo Set descript='" +desc+ "' Where ID=" + id + ";");
      	}

public static void addPreventative(Connection con, String description, String tm_est, String yr_1, String yr_2, String yr_3)
                throws SQLException
		        {
			String tdescription = description.replaceAll("'","''");
	                Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO prevprices (descript, tm_est, yr1, yr2, yr3) Values ('" + tdescription + "','" +tm_est+ "','"+ yr_1 + "','"+ yr_2 + "','"+ yr_3 + "')");
		        }

        public int getId() { return planrec; }

        public String getDescription() { return description; }
	public String getTmEst() { return tm_est; }
	public String getYr1() { return yr_1; }
	public String getYr2() { return yr_2; }
	public String getYr3() { return yr_3; }


        public void setId(int planrec) { this.planrec = planrec; }
        public void setDescription(String desc) { this.description = desc; }
}
