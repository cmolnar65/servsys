package com.pandmservices.dbserver; 
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class MainDbServer
{
	private String servername;
	private String username;
	private String password;
	private String dbname;

        public MainDbServer (Connection c, String servername)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from dbserver where servername='"+servername+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + servername);
		}
		this.servername =rs.getString("servername");
		this.username = rs.getString("username");
		this.password=rs.getString("password");
		this.dbname=rs.getString("dbname");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM dbserver");
		while(rs.next())
		{
			
			MainDbServer t = new MainDbServer(c,rs.getString("servername"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, String servername, String username, String password, String dbname)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update dbserver Set servername='" +servername+ "' ,username='"+username+"' , password='"+password+"', dbname='"+dbname+"';");
      	}


        public String getServerName() { return servername; }
        public String getUserName() { return username; }
        public String getPassword() { return password; }
        public String getDbName() { return dbname; }
}
