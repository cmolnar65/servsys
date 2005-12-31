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


public class ServPropReport
{
	private String wsdesc;
	private String wsdate;
	private String cname;
	private String homephone;
	private String city;
	private String qstatus;
	private int wsrec;
	private int custnum;

        public ServPropReport (Connection c, int transnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT squotes.quotenum, squotes.status, squotes.crecnum, squotes.qdescription, squotes.qdate, customers.custnum, customers.cname, customers.city, customers.homephone FROM squotes, customers where squotes.crecnum=customers.custnum and squotes.quotenum="+transnum+" GROUP BY customers.custnum, squotes.status ORDER BY squotes.status, customers.cname, squotes.qdate;");
	
 while(rs.next())
                {
		this.wsrec=rs.getInt("squotes.quotenum");
//		this.custnum=rs.getInt("customers.custnum");
		this.wsdesc=rs.getString("squotes.qdescription");
		this.wsdate=rs.getString("squotes.qdate");
		this.cname=rs.getString("customers.cname");
		this.qstatus=rs.getString("squotes.status");
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
	ResultSet rs = stmt.executeQuery("SELECT quotenum from squotes where qdate>='"+startdate+"' and qdate<='"+enddate+"' ORDER BY status, crecnum, qdate;");
		while(rs.next())
		{
			
			ServPropReport t = new ServPropReport(c,rs.getInt("quotenum"));
			V.addElement(t);
		}
		return V;
	}

	public int getQNum() { return wsrec; }
	public int getCustNum() { return custnum; }

	public String getQDesc() { return wsdesc; }
	public String getQStatus() { return qstatus; }
	public String getQDate() { return wsdate; }
	public String getCName() { return cname; }
	public String getCity() { return city; }
	public String getHomePhone() { return homephone; }
}
