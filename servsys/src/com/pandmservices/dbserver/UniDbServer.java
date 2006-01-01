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


public class UniDbServer
{
	private String servername;
	private String username;
	private String password;
	private String dbname;
	private String mainservername;
	private String mainusername;
	private String mainpassword;
	private String maindbname;
	private String thismainserver;

        public UniDbServer (Connection c, String servername)
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
		this.mainservername =rs.getString("mainservername");
		this.mainusername = rs.getString("mainusername");
		this.mainpassword=rs.getString("mainpassword");
		this.maindbname=rs.getString("maindbname");
		this.thismainserver=rs.getString("thismainserver");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM dbserver");
		while(rs.next())
		{
			
			UniDbServer t = new UniDbServer(c,rs.getString("servername"));
			V.addElement(t);
		}
		return V;
	}


        public static void SyncTable (Connection con, Connection con2, String thismainserver)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from dbserver;");
		Statement stmtu = con2.createStatement();
		//int result=stmtu.executeUpdate("DROP TABLE IF EXISTS dbserver;");
		//int result1=stmtu.executeUpdate("Create table dbserver (servername text, username text, password text, dbname text, mainservername text, mainusername text, mainpassword text, thismainserver text)");
		while (rs.next())
		{
		String servername =rs.getString("servername");
		String username = rs.getString("username");
		String password=rs.getString("password");
		String dbname=rs.getString("dbname");
		String mainservername =rs.getString("mainservername");
		String mainusername = rs.getString("mainusername");
		String mainpassword=rs.getString("mainpassword");
		String maindbname=rs.getString("maindbname");
      		stmtu.executeUpdate("Update dbserver Set servername='" +servername+ "' ,username='"+username+"' , password='"+password+"', dbname='"+dbname+"', mainservername='" +mainservername+ "' ,mainusername='"+mainusername+"' , mainpassword='"+mainpassword+"', maindbname='"+maindbname+"', thismainserver='"+thismainserver+"';");
		}
	}


	public static void UpdateItem(Connection con, String servername, String username, String password, String dbname, String mainservername, String mainusername, String mainpassword, String maindbname, String thismainserver)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update dbserver Set servername='" +servername+ "' ,username='"+username+"' , password='"+password+"', dbname='"+dbname+"', mainservername='" +mainservername+ "' ,mainusername='"+mainusername+"' , mainpassword='"+mainpassword+"', maindbname='"+maindbname+"', thismainserver='"+thismainserver+"';");
      	}


	public static void UpdateItem(Connection con, String servername, String username, String password, String dbname, String mainservername, String mainusername, String mainpassword, String maindbname)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update dbserver Set servername='" +servername+ "' ,username='"+username+"' , password='"+password+"', dbname='"+dbname+"', mainservername='" +mainservername+ "' ,mainusername='"+mainusername+"' , mainpassword='"+mainpassword+"', maindbname='"+maindbname+"';");
      	}


        public String getServerName() { return servername; }
        public String getUserName() { return username; }
        public String getPassword() { return password; }
        public String getDbName() { return dbname; }
        public String getMainServerName() { return mainservername; }
        public String getMainUserName() { return mainusername; }
        public String getMainPassword() { return mainpassword; }
        public String getMainDbName() { return maindbname; }
	public String getThisMainServer() { return thismainserver; }
}
