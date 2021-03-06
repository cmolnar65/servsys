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


public class DispatchTime
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
	private int servsync;
	private String paytype;
	private String dstatus;
	
        public DispatchTime (Connection c, String tsid)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from time_sheet where tsid='"+tsid+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.dstatus=rs.getString("dstatus");
		this.customer = rs.getString("customer");
		this.tdate = rs.getString("tdate");
		this.time_in = rs.getString("time_in");
		this.dispatch_time = rs.getString("dispatch_time");
		this.login = rs.getString("login");
	}


	public static Vector getAllItems(Connection c, String startdate, String enddate, String login)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM time_sheet where tdate>='"+startdate+"' and tdate<='"+enddate+"' and login='"+login+"' order by tdate, time_in");
		while(rs.next())
		{
			
			UniTimeSheet t = new UniTimeSheet(c,rs.getString("tsid"));
			V.addElement(t);
		}
		return V;
	}
	

	public static Vector getAllCallslipItems(Connection c, String timesheetdate, String callslip, String login)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM time_sheet where tdate='"+timesheetdate+"'and login='"+login+"' and callslip='"+callslip+"' order by tdate, time_in");
		while(rs.next())
		{
			
			UniTimeSheet t = new UniTimeSheet(c,rs.getString("tsid"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c, String timesheetdate, String login)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM time_sheet where tdate='"+timesheetdate+"'and login='"+login+"'  order by tdate, time_in");
		while(rs.next())
		{
			
			UniTimeSheet t = new UniTimeSheet(c,rs.getString("tsid"));
			V.addElement(t);
		}
		return V;
	}
	
	public static Vector getIndividualItems(Connection c, int tsid)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM time_sheet where tsid='"+tsid+"' ");
		while(rs.next())
		{
			
			UniTimeSheet t = new UniTimeSheet(c,rs.getString("tsid"));
			V.addElement(t);
		}
		return V;
	}


	public static int getMaxID(Connection c)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id FROM todo ORDER BY id;");
		int idlarge = 0;	
		while(rs.next())
		  {
			idlarge = rs.getInt("id");
		  }

		return idlarge;

	}

	public static String getAccountID(Connection c, String TAName)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT custnum FROM customer where cusname='"+TAName+"';");
		String accountguid = null;	
		while(rs.next())
		  {
			accountguid = rs.getString("accountguid");
		  }

		return accountguid;

	}


	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("delete FROM time_sheet where tsid='"+d+"' ");
	}
	  

	public static void changeItem(Connection con, int id)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update todo Set Done=true Where ID=" + id + ";");
      	}

	public static void UpdateItem(Connection con, String tsid, String listdate, String callslip, String customer, String dispatch_time, String time_in, String time_out, String isold, String asold, String collected, String commision, String cisold, String casold, String ctype, String login, String paytype)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update time_sheet Set tdate ='" +listdate + "', callslip='"+callslip +"', customer='"+customer +"', dispatch_time='"+dispatch_time +"', time_in='"+time_in +"', time_out='"+time_out +"', item_sold='"+isold +"', amount='"+asold +"', citem_sold='"+cisold +"', camount='"+casold +"',amount_collected='"+collected +"', commision='"+commision +"', ctype='"+ctype +"', login='"+login+"', paytype='"+paytype+"'  Where tsid=" + tsid + ";");
      	}

	public static void addTimeSheetItem( Connection con, String listdate, String callslip, String customer, String dispatch_time, String time_in, String time_out, String isold, String asold, String collected, String commision, String cisold, String casold, String ctype, String login, String paytype)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO time_sheet (tdate, callslip, customer, time_in, time_out, item_sold, amount, amount_collected, commision, dispatch_time, citem_sold, camount, ctype, login, paytype) Values ('" + listdate + "','" +callslip+ "','"+ customer + "','"+ time_in + "','" + time_out + "','"+ isold + "','"+ asold +"','"+collected+"','"+ commision+"','"+ dispatch_time + "', '"+ cisold + "','"+ casold +"','"+ctype+"', '"+login+"', '"+paytype+"')");
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
        public String CType() { return ctype; }
	public String TLogin() { return login; }
	public int getServSync() { return servsync; }
	public String TPayType() { return paytype; }

        public void setId(int id) { this.id = id; }
}
