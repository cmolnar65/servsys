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


public class SupSlDoorType 
{
	private String sldoortype;

        public SupSlDoorType (Connection c, String sldoortype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from sldoortype where sldoortype='"+sldoortype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + sldoortype);
		}
		this.sldoortype = rs.getString("sldoortype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM sldoortype order by sldoortype");
		while(rs.next())
		{
			
			SupSlDoorType t = new SupSlDoorType(c,rs.getString("sldoortype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String SupSlDoorType() { return sldoortype; }

}
