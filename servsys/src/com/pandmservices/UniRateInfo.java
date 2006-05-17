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


public class UniRateInfo
{
	private String labor_rate;
	private String part_mult;
	private String total_mult;

        public UniRateInfo (Connection c, String part_mult)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from rates where partmult='"+part_mult+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + part_mult);
		}
		this.part_mult =rs.getString("partmult");
		this.labor_rate = rs.getString("labor");
		this.total_mult=rs.getString("totalmult");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM rates");
		while(rs.next())
		{
			
			UniRateInfo t = new UniRateInfo(c,rs.getString("partmult"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, String part_mult, String labor_rate,String total_mult)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update rates Set partmult='" +part_mult+ "' ,labor='"+labor_rate+"' , totalmult='"+total_mult+"';");
      	}

	public static void SyncServer(Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmtu = conu.createStatement();
		
		stmt.executeUpdate("drop table if exists rates;");
		stmt.executeUpdate("CREATE TABLE rates (partmult decimal(10,2) NOT NULL default '0.00', labor decimal(10,2) NOT NULL default '0.00', totalmult decimal(10,2) NOT NULL default '0.00')  ;");

		ResultSet rs = stmtu.executeQuery("Select * from rates;");
		
		while (rs.next()) 
		{
		String partmult=rs.getString("partmult");
		String labor=rs.getString("labor");
		String totalmult=rs.getString("totalmult");
		
		stmt.executeUpdate("insert into rates (partmult, labor, totalmult) values ('"+partmult+"','"+labor+"','"+totalmult+"');");
		
		}
		
	}
	

        public String getPartMult() { return part_mult; }
        public String getLaborRate() { return labor_rate; }
        public String getTotalMult() { return total_mult; }
}
