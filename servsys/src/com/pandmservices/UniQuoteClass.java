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


public class UniQuoteClass
{
        private int catnum;
	private String category;
	private String description;

        public UniQuoteClass (Connection c, int catnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from quote_cat where catnum='"+catnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + catnum);
		}
		this.category = rs.getString("category");
		this.description = rs.getString("description");
		this.catnum = rs.getInt("catnum");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM quote_cat order by description");
		while(rs.next())
		{
			
			UniQuoteClass t = new UniQuoteClass(c,rs.getInt("catnum"));
			V.addElement(t);
		}
		return V;
	}
	
	
	public static void UpdateItem(Connection con, int catnum, String category, String description)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update quote_cat Set category ='" +category + "', description='"+description +"' Where catnum=" + catnum + ";");
      	}

	public static void addInvCat( Connection con, String category, String description)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO quote_cat (category, description) Values ('" + category + "','" +description+ "')");
		        }

        public int getCatnum() { return catnum; }

        public String Category() { return category; }
        public String Description() { return description; }

        public void setCatnum(int catnum) { this.catnum = catnum; }

}
