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


public class DocCat
{
        private int catnum;
	private String category;

        public DocCat (Connection c, int catnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from doc_cat where catnum='"+catnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + catnum);
		}
		this.category = rs.getString("category");
		this.catnum = rs.getInt("catnum");
	}


	public static Vector getUnsortedItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM doc_cat order by catnum");
		while(rs.next())
		{
			
			DocCat t = new DocCat(c,rs.getInt("catnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM doc_cat order by category");
		while(rs.next())
		{
			
			DocCat t = new DocCat(c,rs.getInt("catnum"));
			V.addElement(t);
		}
		return V;
	}
	
	
	public static void UpdateItem(Connection con, int catnum, String category)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update doc_cat Set category ='" +category + "' Where catnum=" + catnum + ";");
      	}

	public static void addCompCat( Connection con, String category)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO doc_cat (category) Values ('" + category + "')");
		        }

        public int getCatnum() { return catnum; }

        public String Category() { return category; }

        public void setCatnum(int catnum) { this.catnum = catnum; }

}
