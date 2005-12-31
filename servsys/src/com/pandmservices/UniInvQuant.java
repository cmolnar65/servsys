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


public class UniInvQuant
{
	private int itemnum;
	private String minquant=null;
	private String quant=null;
	private String tquant=null;
	private String itemname=null;
	private String description=null;

        public UniInvQuant (Connection c, int itemnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_items where itemnum="+itemnum+"");
		//ResultSet rs = stmt.executeQuery("SELECT inv_items.itemnum,itemname,description,minquant, sum(inv_detail.quantity) as quant FROM inv_items, inv_detail where inv_items.itemnum="+itemnum+" and inv_detail.itemnum=inv_items.itemnum GROUP BY inv_items.itemnum");	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + itemnum);
		}
		this.minquant = rs.getString("minquant");
		this.itemname = rs.getString("itemname");
		this.description = rs.getString("description");
		this.itemnum = rs.getInt("itemnum");
		this.tquant="0";
		rs.close();
		rs = stmt.executeQuery("SELECT sum(quantity) as quant from inv_detail where inv_detail.itemnum="+itemnum+" group by inv_detail.itemnum");
		while (rs.next()) {
		this.tquant= rs.getString("quant");
		//if (this.tquant==null) this.tquant="0";
			}
	}


	public static Vector getAllItems(Connection c, String catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT itemnum FROM inv_items where inv_items.invcatnum="+catnum+"  order by itemname");
		while(rs.next())
		{
			
			UniInvQuant t = new UniInvQuant(c,rs.getInt("itemnum"));
			V.addElement(t);
		}
		rs.close();
		return V;
	}
	
        public int getItemnum() { return itemnum; }
        public String getMinQuant() { return minquant; }
	public String getTQuant() { return tquant; }
        public String getItemName() { return itemname; }
        public String getDescription() { return description; }


}
