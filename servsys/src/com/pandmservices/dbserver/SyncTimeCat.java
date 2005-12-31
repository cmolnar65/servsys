package com.pandmservices.dbserver;
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class SyncTimeCat
{
        private int catnum;
	private String category;
	private String code;

        public SyncTimeCat (Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmtu = conu.createStatement();
	ResultSet rs = stmt.executeQuery("select * from time_cats;");
	
		while(rs.next())
		{
		this.category = rs.getString("category");
		this.code = rs.getString("code");
                stmtu.executeUpdate("INSERT INTO time_cats (category, code) Values ('" + category + "','" +code+ "')");
		        }
		}
}
