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
        private int docid;
	private String doctype;
	private String filepath;
	private String docdate;
	private String key1;
	private String key2;
	private String key3;
	private String docdesc;

        public FormList (Connection c, int docid)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from documentation where docid="+docid+"");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + docid);
		}
		this.doctype = rs.getString("doctype");
		this.key1 = rs.getString("key1");
		this.key2 = rs.getString("key2");
		this.key3 = rs.getString("key3");
		this.docdesc = rs.getString("docdesc");
		this.filepath = rs.getString("filepath");
		this.docdate = rs.getString("docdate");
		this.docid = rs.getInt("docid");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM documentation order by docid;");
		while(rs.next())
		{
			
			FormList t = new FormList(c,rs.getInt("docid"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c, String doctype)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM documentation where doctype='"+doctype+"' order by docdesc;");
		while(rs.next())
		{
			
			FormList t = new FormList(c,rs.getInt("docid"));
			V.addElement(t);
		}
		return V;
	}
	
	
	public static void UpdateItem(Connection con, int docid, String doctype, String filepath, String docdate, String key1, String key2, String key3, String document, String docdesc)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update documentation Set doctype='" +doctype+ "',filepath='" +filepath+ "',docdate='" +docdate+ "',key1='" +key1+ "',key2='" +key2+ "',key3='" +key3+ "',docdesc='" +docdesc+ "' Where docid=" + docid+ ";");
      	}

	public static void addItem( Connection con, String doctype, String filepath, String docdate, String key1, String key2, String key3, String docdesc)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO documentation (doctype,filepath,docdate,key1,key2,key3,docdesc) Values ('" + doctype+ "','" +filepath + "','" +docdate + "','" +key1 + "','" +key2 + "','" +key3 + "','"+docdesc+"')");
		        }

        public int getDocId() { return docid; }

        public String getDocType() { return doctype; }
        public String getFilePath() { return filepath; }
        public String getDocDate() { return docdate; }
        public String getKey1() { return key1; }
        public String getKey2() { return key2; }
        public String getKey3() { return key3; }
        public String getDocDesc() { return docdesc; }

        public void setCatnum(int docid) { this.docid = docid; }

}
