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


public class HtgLoadMults
{
        private int catnum;
	private int recnum;
        private int designtemp;
	private String description;
	private String ht_trans_mult;

        public HtgLoadMults (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from htg_load_mult where recnum='"+recnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.catnum = rs.getInt("catnum");
		this.recnum = rs.getInt("recnum");
		this.description = rs.getString("mdesc");
		this.designtemp = rs.getInt("design_temp");
		this.ht_trans_mult = rs.getString("ht_trans_mult");
	}


	public static Vector getAllItems(Connection c, int catnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM htg_load_mult where catnum="+catnum+" order by catnum, mdesc");
		while(rs.next())
		{
			HtgLoadMults t = new HtgLoadMults(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}
	

	public static Vector getIndividualtems(Connection c, int recnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM htg_load_mult where recnum="+recnum+" order by mdesc");
		while(rs.next())
		{
			HtgLoadMults t = new HtgLoadMults(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}
	
	public static void UpdateItem(Connection con, int recnum, int catnum, String description, int design_temp, String ht_trans_mult)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update htg_load_mult Set mdesc='"+description +"', catnum='"+catnum+"' , ht_trans_mult='"+ht_trans_mult+"', design_temp='"+design_temp+"'  Where recnum=" + recnum + ";");
      	}

	public static void addPkgMult( Connection con, int catnum, String description, int design_temp, String ht_trans_mult)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO htg_load_mult (catnum, mdesc, design_temp, ht_trans_mult) Values ('"+catnum+"','" +description+ "', '"+design_temp+"', '"+ht_trans_mult+"')");
		        }

        public int getCatID() { return catnum; }
        public int getRecNum() { return recnum; }
        public String getCatDesc() { return description; }
        public String getHtTransMult() { return ht_trans_mult; }
        public int getDesignTemp() { return designtemp; }
}
