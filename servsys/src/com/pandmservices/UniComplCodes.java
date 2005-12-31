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


public class UniComplCodes
{
        private int catnum;
	private int codenum;
	private String complcode;
	private String compltext;

        public UniComplCodes (Connection c, int codenum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from compl_codes where codenum='"+codenum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + codenum);
		}
		this.complcode = rs.getString("complcode");
		this.compltext = rs.getString("compltext");
		this.catnum = rs.getInt("catnum");
		this.codenum = rs.getInt("codenum");
		rs.close();
	}


	public static Vector getIndividualItems(Connection c, int codenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM compl_codes where codenum="+codenum+"");
		while(rs.next())
		{
			
			UniComplCodes t = new UniComplCodes(c,rs.getInt("codenum"));
			V.addElement(t);
		}
		rs.close();
		return V;
	}

 	public static void deleteItem(Connection con, String d)
                 throws SQLException
	         {
                 int x = Integer.parseInt(d);
                 Statement stmt = con.createStatement();
                 stmt.executeUpdate("Delete From compl_codes Where codenum =" + x + ";");
	         }
           

	public static Vector getAllItems(Connection c, String catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM compl_codes where catnum="+catnum+" order by complcode ");
		while(rs.next())
		{
			
			UniComplCodes t = new UniComplCodes(c,rs.getInt("codenum"));
			V.addElement(t);
		}
		return V;
	}
	
	public static void UpdateItem(Connection con, int codenum, int catnum, String complcode, String compltext)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update compl_codes Set complcode='"+complcode +"', compltext='"+compltext+"', catnum='"+catnum+"'  Where codenum=" + codenum + ";");
      	}

	public static void SaveItem( Connection con, int catnum,  String complcode, String compltext)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO compl_codes (catnum, complcode, compltext)  Values ('"+catnum+"', '" +complcode+ "','"+compltext+"')");
		        }

        public int getCatNum() { return catnum; }
        public int getCodeNum() { return codenum; }

        public String getComplCode() { return complcode; }
        public String getComplText() { return compltext; }

        public void setItemNum(int codenum) { this.codenum = codenum; }

}
