package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniQuoteOptions
{
        private int id;
        private int quotenum=0;     //default value to null
        private int quoteoption=0;     //default value to null
        private int printorder=0;     //default value to null
    

        public UniQuoteOptions (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM quoteoptions WHERE quoteoption=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.quotenum = rs.getInt("quotenum");
		this.quoteoption = rs.getInt("quoteoption");
		this.printorder = rs.getInt("printorder");
	}



	public static Vector getAllItems(Connection c, int quotenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quoteoption  FROM quoteoptions where quotenum='"+quotenum+"' ORDER BY printorder;");
		while(rs.next())
		{
			
			UniQuoteOptions t = new UniQuoteOptions(c,rs.getInt("quoteoption"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndItem(Connection c, int crecnum, int quoteoption)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quoteoption  FROM quoteoptions where quoteoption='"+quoteoption+"';");
		while(rs.next())
		{
			
			UniQuoteOptions t = new UniQuoteOptions(c,rs.getInt("quoteoption"));
			V.addElement(t);
		}
		return V;
	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From quoteoptions Where quoteoption=" + x + ";");
	}
	  

public static void AddItem(Connection con, int quotenum, int quoteoption, int printorder)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO quoteoptions (quotenum, quoteoption, printorder) Values ('"+quotenum+"','" +quoteoption+ "','" +printorder+ "')");
		        }

public static void UpdatePrintOrder(Connection con, int quotenum, int quoteoption, int printorder)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("update quoteoptions set printorder='" +printorder+ "' where quotenum='"+quotenum+"' and quoteoption='" +quoteoption+ "';");
		        }

        public int getQuoteNum() { return quotenum; }
        public int getQuoteOption() { return quoteoption; }
        public int getPrintOrder() { return printorder; }
}
