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


public class UniPkgItems
{
        private int recID;
        private int catID;
        private String pkgID;
	private String pkgDesc;
	private int pkgLaborHours;
	private double pkgPartsCost;

        public UniPkgItems (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from package where recID="+recnum+";");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recID = rs.getInt("recID");
		this.catID = rs.getInt("catID");
		this.pkgLaborHours = rs.getInt("pkgLaborHours");
		this.pkgDesc = rs.getString("pkgDesc");
		this.pkgID = rs.getString("pkgID");
		this.pkgPartsCost = rs.getDouble("pkgPartsCost");
	}


	public static Vector getIndividualItems(Connection c, int pkgID)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM package where recID="+pkgID+" order by pkgID");
		while(rs.next())
		{
			
			UniPkgItems t = new UniPkgItems(c,rs.getInt("recID"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndividualItems(Connection c, String pkgID)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM package where pkgID='"+pkgID+"' order by pkgDesc");
		while(rs.next())
		{
			
			UniPkgItems t = new UniPkgItems(c,rs.getInt("recID"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM package order by pkgID;");
		while(rs.next())
		{
			
			UniPkgItems t = new UniPkgItems(c,rs.getInt("recID"));
			V.addElement(t);
		}
		return V;
	}
	public static Vector getAllItems(Connection c, int catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM package where catID='"+catnum+"' order by pkgDesc");
		while(rs.next())
		{
			
			UniPkgItems t = new UniPkgItems(c,rs.getInt("recID"));
			V.addElement(t);
		}
		return V;
	}
	
		public static Vector getAll(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM package order by pkgID");
		while(rs.next())
		{
			
			UniPkgItems t = new UniPkgItems(c,rs.getInt("recID"));
			V.addElement(t);
		}
		return V;
	}
	
	
	public static void UpdateItem(Connection con, int recnum, int catnum, String pkgID, String pkgDesc, int pkgLaborHours, double pkgPartsCost)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update package Set catID='"+catnum+"', pkgID='"+pkgID+"', pkgDesc='"+pkgDesc+"', pkgLaborHours='"+pkgLaborHours+"', pkgPartsCost='"+pkgPartsCost+"' Where recID=" + recnum + ";");
      	}


	public static void addPkgItem(Connection con, int catnum, String pkgID, String pkgDesc, int pkgLaborHours, double pkgPartsCost)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO package (catID, pkgID, pkgDesc, pkgLaborHours, pkgPartsCost) Values ('" +catnum+ "','" +pkgID+ "','" +pkgDesc+ "','" +pkgLaborHours+ "','" +pkgPartsCost+ "')");
		        }


        public int getrecID() { return recID; }
        public int getCatID() { return catID; }
        public String getPkgID() { return pkgID; }
        public String getPkgDesc() { return pkgDesc; }
        public int getPkgLaborHours() { return pkgLaborHours; }
        public double getPkgPartsCost() { return pkgPartsCost; }

}
