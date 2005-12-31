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


public class SyncInvCat
{
        private int catnum;
	private String category;
	private String description;
	private String keyprefix;

        public SyncInvCat (Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmtu = conu.createStatement();
		int result=stmtu.executeUpdate("DROP TABLE IF EXISTS inv_cats;");
		int result2=stmtu.executeUpdate("CREATE TABLE inv_cats ( catnum int(11) NOT NULL auto_increment,category text NOT NULL,description text,keyprefix text,PRIMARY KEY  (catnum),UNIQUE KEY catnum (catnum),KEY catnum_2 (catnum)) ENGINE=MyISAM DEFAULT CHARSET=latin1");
	ResultSet rs = stmt.executeQuery("select * from inv_cats;");
		while(rs.next())
		{
		this.category = rs.getString("category");
		this.description = rs.getString("description");
		this.catnum = rs.getInt("catnum");
		this.keyprefix=rs.getString("keyprefix");
	                stmtu.executeUpdate("INSERT INTO inv_cats (category, description, keyprefix) Values ('" + category + "','" +description+ "','"+keyprefix+"')");
		}

	}
}

