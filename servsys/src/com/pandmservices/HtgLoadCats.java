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


public class HtgLoadCats
{
        private int catnum;
        private int catorder;
	private String description;
	private String unitofm;

        public HtgLoadCats (Connection c, int catnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from htg_load_cats where cat_num='"+catnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + catnum);
		}
		this.catnum = rs.getInt("cat_num");
		this.description = rs.getString("cat_desc");
		this.catorder = rs.getInt("cat_order");
		this.unitofm = rs.getString("unit_of_m");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM htg_load_cats order by cat_order");
		while(rs.next())
		{
			HtgLoadCats t = new HtgLoadCats(c,rs.getInt("cat_num"));
			V.addElement(t);
		}
		return V;
	}
	

	public static Vector getIndividualtems(Connection c, int cat_num)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM htg_load_cats where cat_num="+cat_num+" order by cat_order");
		while(rs.next())
		{
			HtgLoadCats t = new HtgLoadCats(c,rs.getInt("cat_num"));
			V.addElement(t);
		}
		return V;
	}
	
	public static void UpdateItem(Connection con, int catnum, int catorder, String description, String unitofm)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update htg_load_cats Set cat_desc='"+description +"', cat_order='"+catorder+"' , unit_of_m='"+unitofm+"' Where cat_num=" + catnum + ";");
      	}

	public static void addPkgCat( Connection con, int catorder, String description, String unitofm)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO htg_load_cats (cat_order, cat_desc, unit_of_m) Values ('"+catorder+"','" +description+ "', '"+unitofm+"')");
		        }

        public int getCatID() { return catnum; }
        public String getCatDesc() { return description; }
        public String getUnitOfM() { return unitofm; }
        public int getCatOrder() { return catorder; }
}
