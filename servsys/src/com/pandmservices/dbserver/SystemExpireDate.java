package com.pandmservices.dbserver; 
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import com.pandmservices.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class SystemExpireDate
{
	private String expiredate;

        public SystemExpireDate (Connection c,String expiredate)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from expire_date where expiredate='"+expiredate+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + expiredate);
		}
		this.expiredate =rs.getString("expiredate");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM expire_date");
		while(rs.next())
		{
			
			SystemExpireDate t = new SystemExpireDate(c,rs.getString("expiredate"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, String expiredate)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update expire_date Set expiredate='" +expiredate+ "';");
      	}


        public String getExpireDate() { return expiredate; }
}
