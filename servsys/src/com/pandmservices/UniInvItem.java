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


public class UniInvItem
{
        private int catnum;
	private int itemnum;
	private String itemname;
	private String keycode;
	private String minquantity;

        public UniInvItem (Connection c, int itemnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from inv_items where itemnum='"+itemnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + itemnum);
		}
		this.minquantity = rs.getString("minquant");
		this.itemname = rs.getString("itemname");
		this.keycode = rs.getString("description");
		this.catnum = rs.getInt("invcatnum");
		this.itemnum = rs.getInt("itemnum");
		rs.close();
	}


	public static Vector getIndividualItems(Connection c, int itemnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_items where itemnum="+itemnum+"");
		while(rs.next())
		{
			
			UniInvItem t = new UniInvItem(c,rs.getInt("itemnum"));
			V.addElement(t);
		}
		rs.close();
		return V;
	}
	
	public static Vector getIndividualItem(Connection c, String description)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_items where itemname='"+description+"'");
		while(rs.next())
		{
			
			UniInvItem t = new UniInvItem(c,rs.getInt("itemnum"));
			V.addElement(t);
		}
		rs.close();
		return V;
	}

	public static Vector getAllItems(Connection c, String catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_items where invcatnum="+catnum+" order by itemname ");
		while(rs.next())
		{
			
			UniInvItem t = new UniInvItem(c,rs.getInt("itemnum"));
			V.addElement(t);
		}
		return V;
	}
	
	public static void UpdateItem(Connection con, int itemnum, int catnum, String itemname, String keycode, String minquantity)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update inv_items Set minquant ='" +minquantity + "', itemname='"+itemname +"', description='"+keycode+"', invcatnum='"+catnum+"'  Where itemnum=" + itemnum + ";");
      	}

	public static void AddInvItem( Connection con, int catnum,  String itemname, String keycode, String minquantity)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO inv_items (invcatnum, itemname, description, minquant)  Values ('"+catnum+"', '" +itemname+ "','"+keycode+"','"+minquantity+"')");
		        }

        public int getCatNum() { return catnum; }
        public int getItemNum() { return itemnum; }

        public String getItemName() { return itemname; }
        public String getKeyCode() { return keycode; }
        public String getMinQuantity() { return minquantity; }

        public void setItemNum(int itemnum) { this.itemnum = itemnum; }

}
