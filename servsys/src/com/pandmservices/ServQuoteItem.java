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


public class ServQuoteItem
{
        private int catnum;
	private int itemnum;
	private String mannum;
	private String itemname;
	private String investment;

        public ServQuoteItem (Connection c, int itemnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from quote_items where itemnum='"+itemnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + itemnum);
		}
		this.itemname = rs.getString("itemname");
		this.investment = rs.getString("investment");
		this.catnum = rs.getInt("catnum");
		this.itemnum = rs.getInt("itemnum");
		this.mannum = rs.getString("mannum");
		rs.close();
	}


	public static Vector getIndividualItems(Connection c, int itemnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM quote_items where itemnum="+itemnum+"");
		while(rs.next())
		{
			
			ServQuoteItem t = new ServQuoteItem(c,rs.getInt("itemnum"));
			V.addElement(t);
		}
		rs.close();
		return V;
	}
	
	public static Vector getAllItems(Connection c, int catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM quote_items where catnum="+catnum+" order by itemname ");
		while(rs.next())
		{
			ServQuoteItem t = new ServQuoteItem(c,rs.getInt("itemnum"));
			V.addElement(t);
		}
		return V;
	}
	
	public static void UpdateItem(Connection con, int itemnum, int catnum, String itemname, String mannum, String investment )
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update quote_items Set investment ='" +investment + "', itemname='"+itemname +"', mannum='"+mannum+"', catnum='"+catnum+"'  Where itemnum=" + itemnum + ";");
      	}

	public static void AddItem( Connection con, int catnum,  String itemname, String mannum, String investment)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO quote_items (catnum, itemname, mannum, investment)  Values ('"+catnum+"', '" +itemname+ "','"+mannum+"','"+investment+"')");
		        }

        public int getCatNum() { return catnum; }
        public int getItemNum() { return itemnum; }

        public String getItemName() { return itemname; }
        public String getManNum() { return mannum; }
        public String getInvestment() { return investment; }

        public void setItemNum(int itemnum) { this.itemnum = itemnum; }

}
