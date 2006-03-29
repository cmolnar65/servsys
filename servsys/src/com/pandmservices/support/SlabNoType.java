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


public class SlabNoType 
{
	private String slabnotype;

        public SlabNoType (Connection c, String slabnotype)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from slabnotype where slabnotype='"+slabnotype+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + slabnotype);
		}
		this.slabnotype = rs.getString("slabnotype");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM slabnotype order by slabnotype");
		while(rs.next())
		{
			
			SlabNoType t = new SlabNoType(c,rs.getString("slabnotype"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String SlabNoType() { return slabnotype; }

}
