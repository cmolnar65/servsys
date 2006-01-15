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


public class ResPsForm
{
	private String psform;

        public ResPsForm (Connection c)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from prform;");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found");
		}
		this.psform=rs.getString("pagreement");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM prform");
		while(rs.next())
		{
			
			ResPsForm t = new ResPsForm(c);
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, String psform)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update prform Set pagreement='" +psform+ "';"); 
      	}


        public String getResPsForm() { return psform; }
}
