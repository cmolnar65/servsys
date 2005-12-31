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


public class TimeCat
{
        private int catnum;
	private String category;
	private String code;

        public TimeCat (Connection c, int catnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from time_cats where catnum='"+catnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + catnum);
		}
		this.category = rs.getString("category");
		this.code = rs.getString("code");
		this.catnum = rs.getInt("catnum");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM time_cats order by code");
		while(rs.next())
		{
			TimeCat t = new TimeCat(c,rs.getInt("catnum"));
			V.addElement(t);
		}
		return V;
	}
	

	public static Vector getIndItem(Connection c, String catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM time_cats where catnum='"+catnum+"';");
		while(rs.next())
		{
			TimeCat t = new TimeCat(c,rs.getInt("catnum"));
			V.addElement(t);
		}
		return V;
	}
	
	public static void UpdateItem(Connection con, String catnum, String category, String code)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update time_cats Set category ='" +category + "', code='"+code +"' Where catnum=" + catnum + ";");
      	}

	public static void addTimeCat( Connection con, String category, String code)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO time_cats (category, code) Values ('" + category + "','" +code+ "')");
		        }

        public int getCatnum() { return catnum; }
        public String Category() { return category; }
        public String Code() { return code; }
        public void setCatnum(int catnum) { this.catnum = catnum; }

}
