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


public class SupEPhase
{
	private String ephase;

        public SupEPhase (Connection c, String ephase)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from ephase where ephase='"+ephase+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + ephase);
		}
		this.ephase = rs.getString("ephase");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ephase order by ephase");
		while(rs.next())
		{
			
			SupEPhase t = new SupEPhase(c,rs.getString("ephase"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String getEPhase() { return ephase; }

}
