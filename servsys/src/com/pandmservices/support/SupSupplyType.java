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


public class SupSupplyType 
{
	private String supplytype;

        public SupSupplyType (Connection c, String supplytype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from supplytype where supplytype='"+supplytype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + supplytype);
		}
		this.supplytype = rs.getString("supplytype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM supplytype order by supplytype");
		while(rs.next())
		{
			
			SupSupplyType t = new SupSupplyType(c,rs.getString("supplytype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String SupplyType() { return supplytype; }

}
