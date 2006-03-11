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


public class FormParts
{
        private int recnum;
        private int formnum;
	private String formquestion;

        public FormParts (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from formparts where recnum="+recnum+"");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.formquestion = rs.getString("formquestion");
		this.formnum = rs.getInt("formnum");
		this.recnum = rs.getInt("recnum");
	}


	public static Vector getIndItem(Connection c, String recnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM formparts where recnum="+recnum+";");
		while(rs.next())
		{
			
			FormParts t = new FormParts(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllItems(Connection c, String formnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM formparts where formnum="+formnum+" order by recnum;");
		while(rs.next())
		{
			
			FormParts t = new FormParts(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static void DeleteAllItems(Connection con, String recnum)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Delete from  formparts Where formnum=" + recnum + ";");
      	}

	public static void DeleteItem(Connection con, String recnum)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Delete from  formparts Where recnum=" + recnum + ";");
      	}


	public static void UpdateItem(Connection con, String recnum, String formnum, String formquestion)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update formparts Set formquestion='" +formquestion+ "',formnum='" +formnum+ "' Where recnum=" + recnum + ";");
      	}

	public static void AddItem( Connection con, String formnum, String formquestion)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO formparts (formnum,formquestion) Values ('" + formnum+ "','" +formquestion + "');");
		        }

        public int getFormNum() { return formnum; }
        public int getRecNum() { return recnum; }
        public String getFormQuestion() { return formquestion; }

}
