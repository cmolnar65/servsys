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


public class SupWinType 
{
	private String wintype;

        public SupWinType (Connection c, String wintype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from wintype where wintype='"+wintype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + wintype);
		}
		this.wintype = rs.getString("wintype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM wintype order by wintype");
		while(rs.next())
		{
			
			SupWinType t = new SupWinType(c,rs.getString("wintype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String WinType() { return wintype; }

}
