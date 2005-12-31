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


public class SyncTimeSheets
{
        private int id;
	private String login;
	private String cusnum;
	private String customer;
	private String callslip;
	private String tdate;
	private String listdate;
	private String time_in;
	private String time_out;
	private String item_sold;
	private String isold;
	private String amount;
	private String cisold;
	private String camount;
	private String amount_collected;
	private String commision;
	private String ctype;
	private int tsid;
	private String dispatch_time;

        public SyncTimeSheets (Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmtu = conu.createStatement();
	ResultSet rs = stmt.executeQuery("select * from time_sheet where (servsync=0 or servsync is NULL) and tdate is not NULL;");
	
		while(rs.next())
		{
		this.callslip=rs.getString("callslip");
		this.customer = rs.getString("customer");
		this.tdate = rs.getString("tdate");
		this.time_in = rs.getString("time_in");
		this.time_out = rs.getString("time_out");
		this.item_sold = rs.getString("item_sold");
		this.amount = rs.getString("amount");
		this.amount_collected = rs.getString("amount_collected");
		this.commision = rs.getString("commision");
		this.tsid = rs.getInt("tsid");
		this.dispatch_time = rs.getString("dispatch_time");
		this.ctype=rs.getString("ctype");
		this.cisold = rs.getString("citem_sold");
		this.camount = rs.getString("camount");
		this.login = rs.getString("login");
	             String tcustname="";
                                if (customer!=null) {
                                tcustname = customer.replaceAll("'","''");
                                                }
       

		//System.out.println("INSERT INTO time_sheet (tdate, callslip, customer, time_in, time_out, item_sold, amount, amount_collected, commision, dispatch_time, citem_sold, camount, ctype, login) Values ('" + tdate + "','" +callslip+ "','"+ tcustname + "','"+ time_in + "','" + time_out + "','"+ isold + "','"+ amount +"','"+amount_collected+"','"+ commision+"','"+ dispatch_time + "', '"+ cisold + "','"+ camount +"','"+ctype+"', '"+login+"')\n");
		stmtu.executeUpdate("INSERT INTO time_sheet (tdate, callslip, customer, time_in, time_out, item_sold, amount, amount_collected, commision, dispatch_time, citem_sold, camount, ctype, login) Values ('" + tdate + "','" +callslip+ "','"+ tcustname + "','"+ time_in + "','" + time_out + "','"+ isold + "','"+ amount +"','"+amount_collected+"','"+ commision+"','"+ dispatch_time + "', '"+ cisold + "','"+ camount +"','"+ctype+"', '"+login+"')");

		stmt2.executeUpdate("update time_sheet set servsync=2 where tsid='"+tsid+"';");
		}
	}

}
