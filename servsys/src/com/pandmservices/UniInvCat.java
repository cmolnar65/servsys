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


public class UniInvCat
{
        private int catnum;
	private String category;
	private String description;
	private String keyprefix;

        public UniInvCat (Connection c, int catnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from inv_cats where catnum='"+catnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + catnum);
		}
		this.category = rs.getString("category");
		this.description = rs.getString("description");
		this.catnum = rs.getInt("catnum");
		this.keyprefix=rs.getString("keyprefix");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_cats order by description");
		while(rs.next())
		{
			UniInvCat t = new UniInvCat(c,rs.getInt("catnum"));
			V.addElement(t);
		}
		return V;
	}
	

	public static Vector getIndItem(Connection c, String catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_cats where catnum='"+catnum+"';");
		while(rs.next())
		{
			UniInvCat t = new UniInvCat(c,rs.getInt("catnum"));
			V.addElement(t);
		}
		return V;
	}
	
	public static void UpdateItem(Connection con, String catnum, String category, String description, String keyprefix)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update inv_cats Set category ='" +category + "', description='"+description +"', keyprefix='"+keyprefix+"' Where catnum=" + catnum + ";");
      	}

	public static void addInvCat( Connection con, String category, String description, String keyprefix)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO inv_cats (category, description, keyprefix) Values ('" + category + "','" +description+ "','"+keyprefix+"')");
		        }

        public int getCatnum() { return catnum; }

        public String Category() { return category; }
        public String Description() { return description; }
	public String KeyPrefix() { return keyprefix; }

        public void setCatnum(int catnum) { this.catnum = catnum; }

}
