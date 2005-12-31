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


public class SupRetType 
{
	private String returntype;

        public SupRetType (Connection c, String returntype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from returntype where returntype='"+returntype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + returntype);
		}
		this.returntype = rs.getString("returntype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM returntype order by returntype");
		while(rs.next())
		{
			
			SupRetType t = new SupRetType(c,rs.getString("returntype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String ReturnType() { return returntype; }

}
