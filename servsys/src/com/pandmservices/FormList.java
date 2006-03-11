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


public class FormList
{
        private int formnum;
	private String formname;
	private String formdesc;

        public FormList (Connection c, int formnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from formlist where formnum="+formnum+"");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + formnum);
		}
		this.formname = rs.getString("formname");
		this.formdesc = rs.getString("formdescription");
		this.formnum = rs.getInt("formnum");
	}


	public static Vector getIndItem(Connection c, String formnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM formlist where formnum="+formnum+";");
		while(rs.next())
		{
			
			FormList t = new FormList(c,rs.getInt("formnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM formlist order by formname;");
		while(rs.next())
		{
			
			FormList t = new FormList(c,rs.getInt("formnum"));
			V.addElement(t);
		}
		return V;
	}

	public static void DeleteItem(Connection con, String formnum)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Delete from formlist Where formnum=" + formnum + ";");
      	}


	public static void UpdateItem(Connection con, String formnum, String formname, String formdesc)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update formlist Set formname='" +formname+ "',formdescription='" +formdesc+ "' Where formnum=" + formnum + ";");
      	}

	public static void AddItem( Connection con, String formname, String formdesc)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO formlist (formname,formdescription) Values ('" + formname+ "','" +formdesc + "');");
		        }

        public int getFormNum() { return formnum; }
        public String getFormName() { return formname; }
        public String getFormDesc() { return formdesc; }

}
