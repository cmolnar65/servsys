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


public class SupGlassType 
{
	private String glasstype;

        public SupGlassType (Connection c, String glasstype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from glasstype where glasstype='"+glasstype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + glasstype);
		}
		this.glasstype = rs.getString("glasstype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM glasstype order by glasstype");
		while(rs.next())
		{
			
			SupGlassType t = new SupGlassType(c,rs.getString("glasstype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String GlassType() { return glasstype; }

}
