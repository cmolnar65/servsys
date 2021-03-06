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


public class SupStatus
{
	private String status;

        public SupStatus (Connection c, String status)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from quotestatus where status='"+status+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + status);
		}
		this.status = rs.getString("status");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM quotestatus order by status");
		while(rs.next())
		{
			
			SupStatus t = new SupStatus(c,rs.getString("status"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String QStatus() { return status; }

}
