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


public class MasAWallType 
{
	private String masawalltype;

        public MasAWallType (Connection c, String masawalltype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from masawalltype where masawalltype='"+masawalltype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + masawalltype);
		}
		this.masawalltype = rs.getString("masawalltype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM masawalltype order by masawalltype");
		while(rs.next())
		{
			
			MasAWallType t = new MasAWallType(c,rs.getString("masawalltype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String MasAWallType() { return masawalltype; }

}
