package com.pandmservices; 
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.lang.Double;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class UniStockReport
{
	private String tech_name;
	private String dkeycode;
	private String dcat;
	private String dtransdate;
	private String ttransdate;
	private Double dquant;
	private String ditemname;
	private String dquantity;
	private String tquantity;
	private String dcallslip;
	private String tech_init;
	private String truck_num;

        public UniStockReport (Connection c, String transnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT inv_cats.category, inv_items.itemnum,invcatnum,itemname,inv_items.description,minquant, inv_detail.quantity, inv_detail.callslip, inv_detail.date FROM inv_items, inv_detail, inv_cats where inv_detail.itemnum=inv_items.itemnum and inv_cats.catnum=inv_items.invcatnum and inv_detail.transnum="+transnum+" GROUP BY inv_items.itemnum, inv_detail.callslip ORDER BY inv_items.description, inv_detail.date, itemname, category;");
	
 while(rs.next())
                {
		this.dcat=rs.getString("category");
		this.dkeycode=rs.getString("description");
		this.ditemname=rs.getString("itemname");
		this.dtransdate=rs.getString("inv_detail.date");
		this.dcallslip=rs.getString("inv_detail.callslip");
		this.dquantity=rs.getString("inv_detail.quantity");
		
		//dquantity = Double.parseDouble(this.tquantity);
	}	
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + transnum);
		}
	
	}


	public static Vector getAllItems(Connection c, String startdate, String enddate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT inv_cats.category, inv_items.itemnum,invcatnum,itemname,inv_items.description,minquant, inv_detail.transnum, inv_detail.quantity, inv_detail.callslip, inv_detail.date FROM inv_items, inv_detail, inv_cats where inv_detail.itemnum=inv_items.itemnum and inv_cats.catnum=inv_items.invcatnum and inv_detail.date>='"+startdate+"' and inv_detail.date<='"+enddate+"' GROUP BY inv_items.itemnum, inv_detail.callslip ORDER BY inv_items.description, inv_detail.date, itemname, category;");
		while(rs.next())
		{
			
			UniStockReport t = new UniStockReport(c,rs.getString("inv_detail.transnum"));
			V.addElement(t);
		}
		return V;
	}

	public String getDQuantity() { return dquantity; }
	public String getCallSlip()	{return dcallslip;}
	public String getKeyCode() {return dkeycode; }
	public String getTransDate() { return dtransdate; }
	public String getItemName() {return ditemname; }
	public String getDCat() { return dcat; }

        //public String getTechInit() { return tech_init; }

        //public String getTechName() { return tech_name; }
        //public String getTruckNum() { return truck_num; }
}
