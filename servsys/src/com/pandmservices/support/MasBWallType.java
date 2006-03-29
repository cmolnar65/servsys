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


public class MasBWallType 
{
	private String massbwalltype;

        public MasBWallType (Connection c, String massbwalltype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from masbwalltype where masbwalltype='"+massbwalltype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + massbwalltype);
		}
		this.massbwalltype = rs.getString("masbwalltype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM masbwalltype order by masbwalltype");
		while(rs.next())
		{
			
			MasBWallType t = new MasBWallType(c,rs.getString("masbwalltype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String MasBWallType() { return massbwalltype; }

}
