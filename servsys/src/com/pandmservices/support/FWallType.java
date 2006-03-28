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


public class FWallType 
{
	private String fwalltype;

        public FWallType (Connection c, String fwalltype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from fwalltype where wintype='"+fwalltype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + fwalltype);
		}
		this.fwalltype = rs.getString("fwalltype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM fwalltype order by fwalltype");
		while(rs.next())
		{
			
			FWallType t = new FWallType(c,rs.getString("fwalltype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String DoorType() { return fwalltype; }

}
