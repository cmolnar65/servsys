package com.pandmservices; 
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class FlatRateTable
{
        private int code;
	private String category;
	private String part;
	private String keycode;
	private double hours;
	private double partcost;
	private String custnotes;
	private int specitem;

	private double labperhour=0.00;
	private double psdiscount=0.00;
	private double mrdiscount=0.00;
	private double commarkup=0.00;
	private double salestax=0.00;

	private String tm_primary;
	private String tm_add;
	private String sa_primary;
	private String sa_add;
	private String com_price;
	private String nodiscount;
	private double sitemlowprice=0.00;
	private double sitemhighprice=0.00;
	private double sitemhighhours=0.00;
	
        public FlatRateTable (Connection c, int code)
		throws SQLException, TodoException
	{
		Vector vt;
                vt = FlatRateConfig.getAllItems(c);
                int counter=0;
                for (int i = 0 ; i < vt.size(); i++)
                {
                        FlatRateConfig t = (FlatRateConfig) vt.elementAt(i);
			labperhour   = t.getLabPerHour();
			psdiscount = t.getPsDiscount();
			mrdiscount = t.getMrDiscount();
			commarkup = t.getComMarkup();
			salestax  = t.getSalesTax();
			sitemlowprice = t.getSitemLowPrice();
			sitemhighprice = t.getSitemHighPrice();
			sitemhighhours = t.getSitemHighHours();
                }

		Statement stmt = c.createStatement();
		Statement stmti = c.createStatement();
	ResultSet rs = stmt.executeQuery("select code,category, part, keycode, hours, partcost, custnotes, nodiscount, specitem from flat_rate_table where code='"+code+"' order by part;");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + code);
		}
		this.code=rs.getInt("code");
		this.category = rs.getString("category");
		this.part = rs.getString("part");
		this.keycode = rs.getString("keycode");
		this.hours = rs.getDouble("hours");
		this.custnotes = rs.getString("custnotes");
		this.nodiscount = rs.getString("nodiscount");
		this.specitem = rs.getInt("specitem");

		//this.tm_primary = ""+this.partcost+(this.hours*labperhour)+"";
		//this.tm_add = ""+Double.parseDouble(this.tm_primary)*mrdiscount+"";
		//this.sa_primary = ""+Double.parseDouble(this.tm_primary)*psdiscount+"";
		//this.sa_add = ""+Double.parseDouble(this.sa_primary)*mrdiscount+"";
		//this.com_price = ""+Double.parseDouble(this.tm_primary)*commarkup+"";

		if (((keycode==null)&&(keycode.length()==0))||(keycode.equalsIgnoreCase("null"))&&nodiscount.equalsIgnoreCase("0")) {
			this.partcost = rs.getDouble("partcost");
			keycode="-";
		}  else if (nodiscount.equalsIgnoreCase("1")) {
			this.partcost=rs.getDouble("partcost");
		}
		else 
		{
		ResultSet rsi = stmti.executeQuery("select part_cost from inv_keycodes where keycodep='"+this.keycode+"';");
		if (!rsi.next()) {
			this.partcost = rs.getDouble("partcost");
				} else {
		this.partcost=rsi.getDouble("part_cost");

				}
		}
		
	}


	public static Vector getAllItems(Connection c, String servicestart, String servicestop)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT code FROM flat_rate_table where category between '"+servicestart+"' and '"+servicestop+"' order by part");
		while(rs.next())
		{
			
			FlatRateTable t = new FlatRateTable(c,rs.getInt("code"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c, String servicestart)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT code FROM flat_rate_table where category >= '"+servicestart+"' order by part");
		while(rs.next())
		{
			
			FlatRateTable t = new FlatRateTable(c,rs.getInt("code"));
			V.addElement(t);
		}
		return V;
	}
        
	public static Vector getCatItems(Connection c, String catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rsc = stmt.executeQuery("Select category from flat_rate_cat where catnum='"+catnum+"';");
		if (!rsc.next())
		{
			throw new TodoException("Record not found, id = " + catnum);
		}
		String newcat = rsc.getString("category");


		ResultSet rs = stmt.executeQuery("SELECT code, part FROM flat_rate_table where category='"+newcat+"' order by part");
		while(rs.next())
		{
			
			FlatRateTable t = new FlatRateTable(c,rs.getInt("code"));
			V.addElement(t);
		}
		return V;
	}
        
	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT code FROM flat_rate_table order by code");
		while(rs.next())
		{
			
			FlatRateTable t = new FlatRateTable(c,rs.getInt("code"));
			V.addElement(t);
		}
		return V;
	}

        	public static Vector getIndItems(Connection c, int code)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT code FROM flat_rate_table where code= '"+code+"'");
		while(rs.next())
		{
			
			FlatRateTable t = new FlatRateTable(c,rs.getInt("code"));
			V.addElement(t);
		}
		return V;
	}
                
	public static void UpdateItem(Connection con, int code, String category, String part, String keycode, String hours, String partcost, String custnotes, String nodiscount, String specitem)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update flat_rate_table Set category='"+category+"', part='"+part+"', keycode='"+keycode+"', hours='"+hours+"', partcost=round('"+partcost+"',2), custnotes='"+custnotes+"', nodiscount='"+nodiscount+"', specitem='"+specitem+"' where code="+code+";");
      	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From flat_rate_table Where code='" + x + "';");
	}

public static void AddItem(Connection con, int code, String category, String part, String keycode, double hours, double partcost, String custnotes, String nodiscount, String specitem)
                throws SQLException
		        {
			String tpart = part.replaceAll("'","''");
			//String tservice = service.replaceAll("'","''");
	                Statement stmt = con.createStatement();
			if (code!=0) {
	                stmt.executeUpdate("INSERT INTO flat_rate_table (code, category, part, keycode, hours, partcost, custnotes, nodiscount,  specitem) Values ('" + code + "','" +category+ "','"+tpart+"','"+keycode+"','"+hours+"',round('"+partcost+"',2),'"+custnotes+"', '"+nodiscount+"','"+specitem+"')");
			} else {
	                stmt.executeUpdate("INSERT INTO flat_rate_table (category, part, keycode, hours, partcost, custnotes, nodiscount, specitem) Values ('" +category+ "','"+tpart+"','"+keycode+"','"+hours+"',round('"+partcost+"',2),'"+custnotes+"', '"+nodiscount+"','"+specitem+"')");
				}
		        }

        public int getCode() { return code; }

        public String getCategory() { return category; }
        public String getPart() { return part; }
	public String getKeycode() { return keycode; }
	public double getHours() { return hours; }
	public double getPartCost() { return partcost; }
	public String getCustNotes() { return custnotes; }
	public String getNoDiscount() { return nodiscount; }
	public int getSpecItem() { return specitem; }

	public String getTmPrimary() { return tm_primary; }
	public String getTmAdd() { return tm_add; }
	public String getSaPrimary() { return sa_primary; }
	public String getSaAdd() { return sa_add; }
	public String getComPrice() { return com_price; }
	
}
