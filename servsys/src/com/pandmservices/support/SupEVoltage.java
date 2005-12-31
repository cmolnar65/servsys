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


public class SupEVoltage
{
	private String evoltage;

        public SupEVoltage (Connection c, String evoltage)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from evoltage where evoltage='"+evoltage+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + evoltage);
		}
		this.evoltage = rs.getString("evoltage");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM evoltage order by evoltage");
		while(rs.next())
		{
			
			SupEVoltage t = new SupEVoltage(c,rs.getString("evoltage"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String getEVoltage() { return evoltage; }

}
