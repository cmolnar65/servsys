package com.pandmservices; 
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.lang.Double;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class WsReport
{
	private String wsdesc;
	private String wsdate;
	private String cname;
	private String homephone;
	private String city;
	private int wsrec;
	private int custnum;

        public WsReport (Connection c, int transnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT worksheet.wsrec, worksheet.crec, worksheet.wsdesc, worksheet.wsdate, customers.custnum, customers.cname, customers.city, customers.homephone FROM worksheet, customers where worksheet.crec=customers.custnum and worksheet.wsrec="+transnum+" GROUP BY customers.custnum ORDER BY customers.cname, worksheet.wsdate;");
	
 while(rs.next())
                {
		this.wsrec=rs.getInt("worksheet.wsrec");
//		this.custnum=rs.getInt("customers.custnum");
		this.wsdesc=rs.getString("worksheet.wsdesc");
		this.wsdate=rs.getString("worksheet.wsdate");
		this.cname=rs.getString("customers.cname");
		this.city=rs.getString("customers.city");
		this.homephone=rs.getString("customers.homephone");
	}	
//		if (!rs.next())
//		{
//			throw new TodoException("Record not found, id = " + transnum);
//		}
	}

	public static Vector getAllItems(Connection c, String startdate, String enddate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT wsrec from worksheet where wsdate>='"+startdate+"' and wsdate<='"+enddate+"' ORDER BY crec, wsdesc;");
		while(rs.next())
		{
			
			WsReport t = new WsReport(c,rs.getInt("wsrec"));
			V.addElement(t);
		}
		return V;
	}

	public int getWsRec() { return wsrec; }
	public int getCustNum() { return custnum; }

	public String getWsDesc() { return wsdesc; }
	public String getWsDate() { return wsdate; }
	public String getCName() { return cname; }
	public String getCity() { return city; }
	public String getHomePhone() { return homephone; }
}
