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


public class UncondFloorType 
{
	private String uncondfloortype;

        public UncondFloorType (Connection c, String uncondfloortype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from uncondfloortype where uncondfloortype='"+uncondfloortype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + uncondfloortype);
		}
		this.uncondfloortype = rs.getString("uncondfloortype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM uncondfloortype order by uncondfloortype");
		while(rs.next())
		{
			
			UncondFloorType t = new UncondFloorType(c,rs.getString("uncondfloortype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String UncondFloorType() { return uncondfloortype; }

}
