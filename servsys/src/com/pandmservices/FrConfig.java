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

//DROP TABLE IF EXISTS frconfig;
//CREATE TABLE frconfig (
//   tmadditional decimal(10,2),
//   psprimary decimal(10,2),
//   psadditional decimal(10,2),
//);

public class FrConfig
{
	private double tmadditional;
	private double psprimary;
	private double psadditional;

        public FrConfig (Connection c, double tmadditional)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from frconfig where tmadditional="+tmadditional+";");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + tmadditional);
		}
		this.tmadditional=rs.getDouble("tmadditional");
		this.psprimary= rs.getDouble("psprimary");
		this.psadditional=rs.getDouble("psadditional");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM frconfig");
		while(rs.next())
		{
			
			FrConfig t = new FrConfig(c,rs.getDouble("tmadditional"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, String tmadditional, String psprimary, String psadditional)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update frconfig Set tmadditional='" +tmadditional+ "' ,psprimary='"+psprimary+"' , psadditional='"+psadditional+"';");
      	}


        public double getTmAdditional() { return tmadditional; }
        public double getPsPrimary() { return psprimary; }
        public double getPsAdditional() { return psadditional; }
}
