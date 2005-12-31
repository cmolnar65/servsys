package com.pandmservices.support;
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


public class SupFuelType 
{
	private String fueltype;

        public SupFuelType (Connection c, String fueltype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from fueltype where fueltype='"+fueltype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + fueltype);
		}
		this.fueltype = rs.getString("fueltype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM fueltype order by fueltype");
		while(rs.next())
		{
			
			SupFuelType t = new SupFuelType(c,rs.getString("fueltype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String FuelType() { return fueltype; }

}
