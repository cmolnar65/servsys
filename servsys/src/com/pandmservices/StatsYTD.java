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


public class StatsYTD
{
        private int id;
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
	private String callcount;
	private String ctype;
	private int tsid;
	private String dispatch_time;
	private String time_with_travel;
	private String time_no_travel;

        public StatsYTD (Connection c, String ctype, String timesheetdate)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select ucase(ctype) as ctype, count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time)))) as time_with_travel,  SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in)))) as time_no_travel from time_sheet where ctype='"+ctype+"' and tdate>'"+timesheetdate+"' group by ctype;");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.amount = rs.getString("amount");
		this.amount_collected = rs.getString("amount_collected");
		this.commision = rs.getString("commision");
		this.ctype=rs.getString("ctype");
		this.camount = rs.getString("camount");
		this.callcount = rs.getString("callcount");
		this.time_no_travel=rs.getString("time_no_travel");
		this.time_with_travel=rs.getString("time_with_travel");
	}


	public static Vector getYearToDate(Connection c, String timesheetdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ucase(ctype) as ctype FROM time_sheet where tdate>'"+timesheetdate+"' group by ctype;");
		while(rs.next())
		{
			
			TimeSheetSummary t = new TimeSheetSummary(c,rs.getString("ctype"), timesheetdate);
			V.addElement(t);
		}
		return V;
	}


        public int getId() { return tsid; }

        public String TDate() { return tdate; }
        public String CallSlip() { return callslip; }
        public String Customer() { return customer; }
        public String TimeIn() { return time_in; }
        public String TimeOut() { return time_out; }
        public String DispatchTime() { return dispatch_time; }
        public String ItemSold() { return item_sold; }
        public String Amount() { return amount; }
        public String AmountCollected() { return amount_collected; }
        public String Commision() { return commision; }
        public String CItemSold() { return cisold; }
        public String CAmount() { return camount; }
        public String CallCount() { return callcount; }
        public String CType() { return ctype; }
	public String TimeWithTravel() { return time_with_travel; }
	public String TimeNoTravel() { return time_no_travel; }

        public void setId(int id) { this.id = id; }
}
