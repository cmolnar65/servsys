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


public class SyncInspectNotes
{
        private String callslip=null;     //default value to null
        private String note=null;     //default value to null 
	private int servsync;
	private String ndate=null;
	private String custnum=null;
	private String sitenum=null;
	private String userlogin=null;
	private int recnum;

	private String paytype;

        public SyncInspectNotes (Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmtu = conu.createStatement();
	ResultSet rs = stmt.executeQuery("select * from inspectnotes where (servsync=0 or servsync is NULL) and ndate is not NULL;");
	
		while(rs.next())
		{
		this.servsync = rs.getInt("servsync");
		this.callslip = rs.getString("callslip");
		this.note = rs.getString("note");
		this.userlogin = rs.getString("userlogin");
		this.ndate = rs.getString("ndate");
		this.custnum = rs.getString("custnum");
		this.sitenum = rs.getString("sitenum");
		this.recnum=rs.getInt("recnum");
       

		//System.out.println("INSERT INTO time_sheet (tdate, callslip, customer, time_in, time_out, item_sold, amount, amount_collected, commision, dispatch_time, citem_sold, camount, ctype, login) Values ('" + tdate + "','" +callslip+ "','"+ tcustname + "','"+ time_in + "','" + time_out + "','"+ isold + "','"+ amount +"','"+amount_collected+"','"+ commision+"','"+ dispatch_time + "', '"+ cisold + "','"+ camount +"','"+ctype+"', '"+login+"')\n");
	//	stmtu.executeUpdate("INSERT INTO time_sheet (tdate, callslip, customer, time_in, time_out, item_sold, amount, amount_collected, commision, dispatch_time, citem_sold, camount, ctype, login, paytype) Values ('" + tdate + "','" +callslip+ "','"+ tcustname + "','"+ time_in + "','" + time_out + "','"+ isold + "','"+ amount +"','"+amount_collected+"','"+ commision+"','"+ dispatch_time + "', '"+ cisold + "','"+ camount +"','"+ctype+"', '"+login+"', '"+paytype+"')");
		stmtu.executeUpdate("INSERT INTO inspectnotes (custnum, sitenum, userlogin, ndate, callslip, note, servsync) Values ('" + custnum + "','" + sitenum + "','"+userlogin+"','"+ndate+"', '"+callslip+"', '"+note+"', '"+servsync+"')");
		stmt2.executeUpdate("update inspectnotes set servsync=2 where recnum="+recnum+";");
		}
	}

}
