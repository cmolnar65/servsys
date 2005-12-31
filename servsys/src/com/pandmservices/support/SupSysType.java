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


public class SupSysType 
{
	private String systype;

        public SupSysType (Connection c, String Systype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from systype where systype='"+systype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + systype);
		}
		this.systype = rs.getString("systype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM systemtype order by systype");
		while(rs.next())
		{
			
			SupSysType t = new SupSysType(c,rs.getString("doortype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String SysType() { return systype; }

}
