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


public class InvKeyCodes
{
        private int recnum;
	private String keycodep;
	private String part_number;
	private String sort_desc;
	private String description;
	private String manu;
	private String location;
	private String oh_qty;
	private String qty_opt;
	private String part_cost;
	private String extended_cost;
	private String sell_price;
	private String stocknum;
	private String orderuom;
	private String conversion;
	private String peak_oh;
	private String off_peak_oh;

        public InvKeyCodes (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from inv_keycodes where ID='"+recnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recnum = rs.getInt("ID");
		this.keycodep = rs.getString("keycodep");
		this.part_number = rs.getString("part_number");
		this.sort_desc = rs.getString("sort_desc");
		this.description = rs.getString("description");
		this.manu = rs.getString("manu");
		this.location = rs.getString("location");
		this.oh_qty = rs.getString("oh_qty");
		this.qty_opt = rs.getString("qty_opt");
		this.part_cost = rs.getString("part_cost");
		this.extended_cost = rs.getString("extended_cost");
		this.sell_price = rs.getString("sell_price");
		this.stocknum = rs.getString("stocknum");
		this.orderuom = rs.getString("orderuom");
		this.conversion = rs.getString("conversion");
		this.peak_oh = rs.getString("peak_oh");
		this.off_peak_oh = rs.getString("off_peak_oh");
	}

	public static Vector getSingleKeyCode(Connection c, String keycode)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_keycodes where keycodep like '"+keycode+"%' order by description;");
		while(rs.next())
		{
			InvKeyCodes t = new InvKeyCodes(c,rs.getInt("ID"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getPrefixItems(Connection c, String keyprefix)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_keycodes where keycodep like '"+keyprefix+"%' order by description;");
		while(rs.next())
		{
			InvKeyCodes t = new InvKeyCodes(c,rs.getInt("ID"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_keycodes");
		while(rs.next())
		{
			InvKeyCodes t = new InvKeyCodes(c,rs.getInt("ID"));
			V.addElement(t);
		}
		return V;
	}
	
	public static void AddItem( Connection con, String keycodep, String part_number, String sort_desc, String description, String manu, String location, String oh_qty, String qty_opt, String part_cost, String extended_cost, String sell_price, String stocknum, String orderuom, String conversion, String peak_oh, String off_peak_oh) 
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			String tdescription = description.replaceAll("'","");
			String tsort_desc = sort_desc.replaceAll("'","");
			//String tpart_number = part_number.replaceAll("'","");
			//String tpart_number = part_number.replaceAll("'","''");
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO inv_keycodes (keycodep, part_number, sort_desc, description, manu, location, oh_qty, qty_opt, part_cost, extended_cost, sell_price, stocknum, orderuom, conversion, peak_oh, off_peak_oh) Values ('"+keycodep+"','" +part_number+ "', '"+tsort_desc+"','"+tdescription+"','" +manu+ "', '"+location+"','"+oh_qty+"','" +qty_opt+ "', '"+part_cost+"','"+extended_cost+"','" +sell_price+ "', '"+stocknum+"','"+orderuom+"','" +conversion+ "', '"+peak_oh+"','"+off_peak_oh+"')");
		        }

        public int getCatID() { return recnum; }
        public String getKeycodep() { return keycodep; }
        public String getPartNumber() { return part_number; }
        public String getSortDesc() { return sort_desc; }
        public String getDescription() { return description; }
        public String getManu() { return manu; }
        public String getLocation() { return location; }
        public String getOhQty() { return oh_qty; }
        public String getQtyOpt() { return qty_opt; }
        public String getPartCost() { return part_cost; }
        public String getExtendedCost() { return extended_cost; }
        public String getSellPrice() { return sell_price; }
        public String getStocknum() { return stocknum; }
        public String getOrderuom() { return orderuom; }
        public String getConversion() { return conversion; }
        public String getPeakOh() { return peak_oh; }
        public String getOffPeakOh() { return off_peak_oh; }
}
