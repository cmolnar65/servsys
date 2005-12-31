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

//DROP TABLE IF EXISTS flatrateconfig;
//CREATE TABLE flatrateconfig (
//   labperhour decimal(10,2),
//   psdiscount decimal(10,2),
//   mrdiscount decimal(10,2),
//   salestax decimal(10,2),
//);

public class FlatRateConfig
{
	private double labperhour;
	private double psdiscount;
	private double mrdiscount;
	private double commarkup;
	private double partmarkup;
	private double salestax;
	private double sitemlowprice;
	private double sitemhighprice;
	private double sitemhighhours;

        public FlatRateConfig (Connection c, double labperhour)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from flatrateconfig where labperhour="+labperhour+";");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + labperhour);
		}
		this.labperhour=rs.getDouble("labperhour");
		this.psdiscount= rs.getDouble("psdiscount");
		this.mrdiscount=rs.getDouble("mrdiscount");
		this.commarkup=rs.getDouble("commarkup");
		this.partmarkup=rs.getDouble("partmarkup");
		this.salestax=rs.getDouble("salestax");
		this.sitemlowprice=rs.getDouble("sitemlowprice");
		this.sitemhighprice=rs.getDouble("sitemhighprice");
		this.sitemhighhours=rs.getDouble("sitemhighhours");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM flatrateconfig");
		while(rs.next())
		{
			
			FlatRateConfig t = new FlatRateConfig(c,rs.getDouble("labperhour"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con,String labperhour, String psdiscount, String mrdiscount, String commarkup, String salestax, String partmarkup, String sitemlowprice, String sitemhighprice, String sitemhighhours) 
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update flatrateconfig Set labperhour='" +labperhour+ "' ,psdiscount='"+psdiscount+"' , mrdiscount='"+mrdiscount+"', commarkup='"+commarkup+"', salestax='"+salestax+"', partmarkup='"+partmarkup+"', sitemlowprice='"+sitemlowprice+"', sitemhighhours='"+sitemhighhours+"', sitemhighprice='"+sitemhighprice+"';");
      	}


        public double getLabPerHour() { return labperhour; }
        public double getPsDiscount() { return psdiscount; }
        public double getMrDiscount() { return mrdiscount; }
        public double getComMarkup() { return commarkup; }
        public double getPartMarkup() { return partmarkup; }
        public double getSalesTax() { return salestax; }
	public double getSitemLowPrice() { return sitemlowprice; }
	public double getSitemHighPrice() { return sitemhighprice; }
	public double getSitemHighHours() { return sitemhighhours; }
}
