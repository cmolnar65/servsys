package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.text.*;
import java.util.*;
import java.util.Vector;

public class ServQuoteParts
{
        private int id;
        private int qpartnum=0;     //default value to null
        private int quotenum=0;;     //default value to null
        private String itemname=null;     //default value to null
        private String mannum=null;     //default value to null
        private int itemquant=0;     //default value to null
        private double price=0;     //default value to null
        private double total=0;     //default value to null
        private String investment=null;     //default value to null
    

        public ServQuoteParts (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM squote_parts WHERE qpartnum=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.qpartnum = rs.getInt("qpartnum");
		this.quotenum = rs.getInt("quotenum");
		this.itemname = rs.getString("itemname");
		this.mannum = rs.getString("mannum");
		this.itemquant = rs.getInt("itemquant");
		this.investment = rs.getString("investment");
		this.price= Double.parseDouble(investment);
	        this.total = this.itemquant*this.price;

	}



	public static Vector getAllItems(Connection c, int quotenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT qpartnum  FROM squote_parts where quotenum="+quotenum+" ORDER BY investment;");
		while(rs.next())
		{
			
			ServQuoteParts t = new ServQuoteParts(c,rs.getInt("qpartnum"));
			V.addElement(t);
		}
		return V;
	}


	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From squote_parts Where qpartnum=" + x + ";");
	}
	  

	public static void UpdateItem(Connection con, int qpartnum, int quotenum, String itemname, String mannum, int itemquant, String investment )
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update squote_parts Set quotenum='" +quotenum+ "',itemname = '"+itemname+"',mannum = '"+mannum +"',itemquant = '"+itemquant +"',investment = '"+investment +"' Where qpartnum=" + qpartnum + ";");
      	}

public static void AddItem(Connection con, int quotenum, String itemname, String mannum, int itemquant, double investment)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
			DecimalFormat twoDigits = new DecimalFormat("#######.##");
			String tinvestment = twoDigits.format(investment);

	                stmt.executeUpdate("INSERT INTO squote_parts (quotenum, itemname, mannum, itemquant, investment) Values ('" +quotenum+ "','" +itemname + "','"+mannum +"','"+itemquant +"','"+tinvestment +"')");
		        }

        public int getId() { return id; }

        public int getQPartnum() { return qpartnum; }
        public int getQuoteNum() { return quotenum; }
        public String getItemName() { return itemname; }
        public String getManNum() { return mannum; }
        public int getItemQuant() { return itemquant; }
        public String getInvestment() { return investment; }
	public double getQuoteTotal() { return total; }


}
