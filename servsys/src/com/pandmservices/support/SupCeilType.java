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


public class SupCeilType 
{
	private String ceiltype;

        public SupCeilType (Connection c, String ceiltype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from ceiltype where ceiltype='"+ceiltype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + ceiltype);
		}
		this.ceiltype = rs.getString("ceiltype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ceiltype order by ceiltype");
		while(rs.next())
		{
			
			SupCeilType t = new SupCeilType(c,rs.getString("ceiltype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String SupCeilingType() { return ceiltype; }

}
