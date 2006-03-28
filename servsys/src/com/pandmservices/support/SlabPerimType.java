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


public class SlabPerimType 
{
	private String slabperimtype;

        public SlabPerimType (Connection c, String slabperimtype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from slabperimtype where wintype='"+slabperimtype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + slabperimtype);
		}
		this.slabperimtype = rs.getString("slabperimtype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM slabperimtype order by slabperimtype");
		while(rs.next())
		{
			
			SlabPerimType t = new SlabPerimType(c,rs.getString("slabperimtype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String SlabPerimType() { return slabperimtype; }

}
