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


public class SupFurnType 
{
	private String furntype;

        public SupFurnType (Connection c, String furntype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from furntype where furntype='"+furntype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + furntype);
		}
		this.furntype = rs.getString("furntype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM furntype order by furntype");
		while(rs.next())
		{
			
			SupFurnType t = new SupFurnType(c,rs.getString("furntype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String FurnType() { return furntype; }

}
