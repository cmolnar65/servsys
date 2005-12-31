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


public class UniPkgCats
{
        private int catnum;
	private String description;
	private String adjper;

        public UniPkgCats (Connection c, int catnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from packagecats where catID='"+catnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + catnum);
		}
		this.catnum = rs.getInt("catID");
		this.description = rs.getString("catDesc");
		this.adjper = rs.getString("adjper");
	}


	public static Vector getAllItems(Connection c, String x)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM packagecats;");
		while(rs.next())
		{
			
			UniPkgCats t = new UniPkgCats(c,rs.getInt("catID"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM packagecats order by catDesc");
		while(rs.next())
		{
			
			UniPkgCats t = new UniPkgCats(c,rs.getInt("catID"));
			V.addElement(t);
		}
		return V;
	}
	
	
	public static void UpdateItem(Connection con, int catnum, String description, String adjper)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update packagecats Set catDesc='"+description +"' Where catID=" + catnum + ", adjper='"+adjper+"';");
      	}

	public static void addPkgCat( Connection con, String description, String adjper)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO packagecats (catDesc, adjper) Values ('" +description+ "','"+adjper+"')");
		        }

        public int getCatID() { return catnum; }
        public String getCatDesc() { return description; }
	public String getAdjPer() { return adjper; }

}
