package com.pandmservices; 
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class UniVersion
{
	private String vnumber;;
	private String vdate;

        public UniVersion (Connection c, String vnumber)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from version where vnumber ='"+vnumber+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + vnumber);
		}
		this.vnumber =rs.getString("vnumber");
		this.vdate = rs.getString("vdate");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM version");
		while(rs.next())
		{
			
			UniVersion t = new UniVersion(c,rs.getString("vnumber"));
			V.addElement(t);
		}
		return V;
	}

        public String getVNumber() { return vnumber; }
        public String getVDate() { return vdate; }
}
