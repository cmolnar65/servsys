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


public class FlatRateCat
{
        private int catnum;
	private String category;

        public FlatRateCat (Connection c, int catnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from flat_rate_cat where catnum='"+catnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + catnum);
		}
		this.category = rs.getString("category");
		this.catnum = rs.getInt("catnum");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM flat_rate_cat order by category");
		while(rs.next())
		{
			FlatRateCat t = new FlatRateCat(c,rs.getInt("catnum"));
			V.addElement(t);
		}
		return V;
	}
	

	public static Vector getIndItem(Connection c, String catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM flat_rate_cat where catnum='"+catnum+"';");
		while(rs.next())
		{
			FlatRateCat t = new FlatRateCat(c,rs.getInt("catnum"));
			V.addElement(t);
		}
		return V;
	}
	

        public int getCatnum() { return catnum; }
        public String Category() { return category; }
        public void setCatnum(int catnum) { this.catnum = catnum; }
}
