/*
 * ServsysProposal.java
 *
 * Created on March 24, 2006, 6:42 PM
 */

package com.pandmservices.web;
import java.util.Date;
import java.math.*;
import java.text.*;
import java.lang.String;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import com.pandmservices.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Vector;
import java.text.*;
import java.lang.*;
import java.lang.Integer;
import java.lang.Math.*;
import java.lang.String;
import java.lang.Float;
import java.lang.Double;
import java.math.*;
import java.util.Date;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Christopher Molnar
 * @version
 */
public class ServsysPrintTimeRecap extends UniCash
{
    
  	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir)
	throws SQLException, TodoException, Exception
        {

            //String mbody="";

	    	Format formatter;
        Calendar now = Calendar.getInstance();
        Date date = new Date();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        int hour = now.get(Calendar.HOUR_OF_DAY); 
        int second = now.get(Calendar.SECOND);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int minute = now.get(Calendar.MINUTE);
        int millisecond = now.get(Calendar.MILLISECOND);
	
	
	String startdate = req.getParameter("startlistdate");
	String enddate = req.getParameter("endlistdate");
	out.println("Time Report From "+startdate+" to "+enddate+" \n");
	Statement stmt2 = con.createStatement();
	Statement stmt = con.createStatement();
	ResultSet rs2 = stmt2.executeQuery("select department, transmit from tech_table where transmit=1 group by department;");
	//Format formatter;
        //String startdate = doFormatDate(dateToBeTested.getTime());
	System.out.println ("Start Date: "+startdate+"\n");
	System.out.println ("End Date: "+enddate+"\n");

   	Vector u;
	String mbody="";
	String mbody2="";
	mbody=combinestring(mbody,"<html><basefont size=-1>");
	mbody=combinestring(mbody,"<head><title>Time Sheet Report</title></head><body><h2>By Technician</h2>");
	//doStyleSheet(req, res, out, session, username);
	u = UniTechInfo.getAllTransmitItems(con);
	int counter=0;
	for (int iu = 0 ; iu < u.size(); iu++)
	{
		UniTechInfo tu = (UniTechInfo) u.elementAt(iu);
		String tech_init = tu.getTechInit();
		String lusername = tu.getUserName();
		String tech_name = tu.getTechName();
		String department = tu.getDepartment();
		Vector v;
		v = TimeSheetSummary.getLoginItemsMultiDate(con,lusername, doFormatDateDb(getDateDb(startdate)),doFormatDateDb(getDateDb(enddate)));
		mbody=combinestring(mbody,"<table width=\"75%\"><tr><td><table border=1 width=\"75%\" align=\"left\">");
		mbody=combinestring(mbody,"<tr><td>Tech ID: "+lusername+" - "+tech_name+"</td></tr>");
		mbody=combinestring(mbody,"</table></td></tr>");		
		if (v.size()>0)
		{
			mbody=combinestring(mbody,"<tr><td><table border=1 width=\"75%\" align=\"left\">");
			mbody=combinestring(mbody,"<tr><td>Call-Type</td><td>Count</td><td>Total-Collected</td><td>Non-Commision-Billed</td><td>Commision-Billed</td><td>Commision</td><td>Time</td><td>Time-Without-Travel</td></tr>");
			for (int i = 0 ; i < v.size(); i++)
			{
				TimeSheetSummary ts = (TimeSheetSummary) v.elementAt(i);
				String tamount = ts.Amount();
				String tcamount = ts.CAmount();
				String tamount_collected = ts.AmountCollected();
				String tcommision = ts.Commision();
				String ctype=ts.CType();
				String callcount=ts.CallCount();
				String timewithtravel=ts.TimeWithTravel();
				String timenotravel=ts.TimeNoTravel();
				mbody=combinestring(mbody,"<tr><td>"+ctype+"</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
				
			}
			        ResultSet rs = stmt.executeQuery("select count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel, ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet where login='"+lusername+"' and tdate>='"+doFormatDateDb(getDateDb(startdate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"';");
                while(rs.next())
                {
                        String tamount =rs.getString("amount");
                        String tcamount = rs.getString("camount");
                        String tamount_collected = rs.getString("amount_collected");
                        String tcommision = rs.getString("commision");
                        String callcount=rs.getString("callcount");
                        String timewithtravel=rs.getString("time_with_travel");
                        //String timewithtravel="-";
                        String timenotravel=rs.getString("time_no_travel");
                        //String timenotravel="-";
        mbody=combinestring(mbody,"<tr><td>Total</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
                }


			mbody=combinestring(mbody,"</table><br></td></tr>");
		}
		else {
			mbody=combinestring(mbody,"<tr><td><table border=0 width=\"75%\" align=\"left\">");
			mbody=combinestring(mbody,"<tr><td>**NO DATA TRANSMITTED</td></tr></table><br></td></tr>");
		}
		mbody=combinestring(mbody,"<tr><td><table border=0 width=\"75%\" align=\"left\">");
		mbody=combinestring(mbody,"<tr><td> </td></tr></table><br>");
	}
	mbody=combinestring(mbody,"</td></tr></table></table>");
	mbody2=combinestring(mbody2,"");
	//mbody2=combinestring(mbody2,"<h2>By Department</h2><br>");
		while(rs2.next())
		{
		String sdepartment = rs2.getString("department");
		Vector v;
		v = TimeSheetSummary.getDepartmentItemsMultiDate(con,sdepartment, doFormatDateDb(getDateDb(startdate)),doFormatDateDb(getDateDb(enddate)));		
		mbody2=combinestring(mbody2,"<table width=\"75%\"><tr><td><table border=1 width=\"75%\" align=\"left\">");
		mbody2=combinestring(mbody2,"<tr><td>Department: "+sdepartment+"</td></tr>");
		mbody2=combinestring(mbody2,"</table></td></tr>");	

		if (v.size()>0)
		{
			mbody2=combinestring(mbody2,"<tr><td><table border=1 width=\"75%\" align=\"left\">");
			mbody2=combinestring(mbody2,"<tr><td>Call-Type</td><td>Count</td><td>Total-Collected</td><td>Non-Commision-Billed</td><td>Commision-Billed</td><td>Commision</td><td>Time</td><td>Time-Without-Travel</td></tr>");
			for (int i = 0 ; i < v.size(); i++)
			{
				TimeSheetSummary ts = (TimeSheetSummary) v.elementAt(i);
				String tamount = ts.Amount();
				String tcamount = ts.CAmount();
				String tamount_collected = ts.AmountCollected();
				String tcommision = ts.Commision();
				String ctype=ts.CType();
				String callcount=ts.CallCount();
				String timewithtravel=ts.TimeWithTravel();
				String timenotravel=ts.TimeNoTravel();
				mbody2=combinestring(mbody2,"<tr><td>"+ctype+"</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
				
			}
 //System.out.println("select tech_table.department as department, count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet, tech_table  where time_sheet.login=tech_table.username and tdate>='"+doFormatDateDb(getDateDb(startdate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"' and department='"+sdepartment+"' group by department;\n");
 ResultSet rs3 = stmt.executeQuery("select tech_table.department as department, count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet, tech_table  where time_sheet.login=tech_table.username and tdate>='"+doFormatDateDb(getDateDb(startdate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"' and department='"+sdepartment+"' group by department;");

                while(rs3.next())
                {
                        String tamount =rs3.getString("amount");
                        String tcamount = rs3.getString("camount");
                        String tamount_collected = rs3.getString("amount_collected");
                        String tcommision = rs3.getString("commision");
                        String callcount=rs3.getString("callcount");
                        String timewithtravel=rs3.getString("time_with_travel");
                        //String timewithtravel="-";
                        String timenotravel=rs3.getString("time_no_travel");
                        //String timenotravel="-";
        mbody2=combinestring(mbody2,"<tr><td>Total</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
		}
		mbody2=combinestring(mbody2,"</table><br></td></tr>");

			}
		else {

			mbody2=combinestring(mbody2,"<tr><td><table border=0 width=\"75%\" align=\"left\">");
			mbody2=combinestring(mbody2,"<tr><td>**NO DATA TRANSMITTED</td></tr></table><br></td></tr>");
		}
		mbody2=combinestring(mbody2,"<tr><td><table border=0 width=\"75%\" align=\"left\">");
		mbody2=combinestring(mbody2,"<tr><td> </td></tr></table><br>");
	}
	mbody2=combinestring(mbody2,"</td></tr></table></table>");
		




////////////////////////////////////////////////////////
// Here is where we end the http headers
////////////////////////////////////////////////////////
//		out.println(
               //String newstring = mbody.replaceAll("<br>","\n");
                out.println(mbody);
                out.println(mbody2);
		mbody="";
	//con.close();
	return mbody;
        }
	
 public static String getYesterday (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir)
	throws SQLException, TodoException, Exception
        {

            //String mbody="";

	    	Format formatter;
		
		
		Calendar now = Calendar.getInstance();
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s = formatter.format(date);
		Date expireationDate=null;
                expireationDate=dateFormatter.parse(s);
                Calendar dateToBeTested=Calendar.getInstance();
                dateToBeTested.setTime(expireationDate);
                dateToBeTested.add(Calendar.DAY_OF_YEAR,-1);
                String enddate = doFormatDate(dateToBeTested.getTime());
                dateToBeTested.add(Calendar.DAY_OF_YEAR,-8);
                String startdate = doFormatDate(dateToBeTested.getTime());
		
		
		
	out.println("Time Report For "+enddate+"<br>");
	Statement stmt2 = con.createStatement();
	Statement stmt = con.createStatement();
	ResultSet rs2 = stmt2.executeQuery("select department, transmit from tech_table where transmit=1 group by department;");
	//Format formatter;
        //String startdate = doFormatDate(dateToBeTested.getTime());
	System.out.println ("Start Date: "+startdate+"\n");
	System.out.println ("End Date: "+enddate+"\n");

   	Vector u;
	String mbody="";
	String mbody2="";
	mbody=combinestring(mbody,"<html>");
	mbody=combinestring(mbody,"<head><title>Time Sheet Report</title></head><body><h2>By Technician</h2>");
	//doStyleSheet(req, res, out, session, username);
	u = UniTechInfo.getAllTransmitItems(con);
	int counter=0;
	for (int iu = 0 ; iu < u.size(); iu++)
	{
		UniTechInfo tu = (UniTechInfo) u.elementAt(iu);
		String tech_init = tu.getTechInit();
		String lusername = tu.getUserName();
		String tech_name = tu.getTechName();
		String department = tu.getDepartment();
		Vector v;
		v = TimeSheetSummary.getLoginItemsMultiDate(con,lusername, doFormatDateDb(getDateDb(enddate)),doFormatDateDb(getDateDb(enddate)));
		mbody=combinestring(mbody,"<table width=\"75%\"><tr width=\"100%\"><td><table border=1 align=\"left\">");
		mbody=combinestring(mbody,"<tr><td>Tech ID: "+lusername+" - "+tech_name+"</td></tr>");
		mbody=combinestring(mbody,"</table></td></tr>");		
		if (v.size()>0)
		{
			mbody=combinestring(mbody,"<tr><td><table border=1 align=\"left\">");
			mbody=combinestring(mbody,"<tr><td>Call-Type</td><td>Count</td><td>Total-Collected</td><td>Non-Commision-Billed</td><td>Commision-Billed</td><td>Commision</td><td>Time</td><td>Time-Without-Travel</td></tr>");
			for (int i = 0 ; i < v.size(); i++)
			{
				TimeSheetSummary ts = (TimeSheetSummary) v.elementAt(i);
				String tamount = ts.Amount();
				String tcamount = ts.CAmount();
				String tamount_collected = ts.AmountCollected();
				String tcommision = ts.Commision();
				String ctype=ts.CType();
				String callcount=ts.CallCount();
				String timewithtravel=ts.TimeWithTravel();
				String timenotravel=ts.TimeNoTravel();
				mbody=combinestring(mbody,"<tr><td>"+ctype+"</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
				
			}
			        ResultSet rs = stmt.executeQuery("select count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel, ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet where login='"+lusername+"' and tdate>='"+doFormatDateDb(getDateDb(enddate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"';");
                while(rs.next())
                {
                        String tamount =rs.getString("amount");
                        String tcamount = rs.getString("camount");
                        String tamount_collected = rs.getString("amount_collected");
                        String tcommision = rs.getString("commision");
                        String callcount=rs.getString("callcount");
                        String timewithtravel=rs.getString("time_with_travel");
                        //String timewithtravel="-";
                        String timenotravel=rs.getString("time_no_travel");
                        //String timenotravel="-";
        mbody=combinestring(mbody,"<tr><td>Total</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
                }


			mbody=combinestring(mbody,"</table><br></td></tr>");
		}
		else {
			mbody=combinestring(mbody,"<tr><td><table border=0 align=\"left\">");
			mbody=combinestring(mbody,"<tr><td>**NO DATA TRANSMITTED</td></tr></table><br></td></tr>");
		}
		mbody=combinestring(mbody,"<tr><td><table border=0 align=\"left\">");
		mbody=combinestring(mbody,"<tr><td> </td></tr></table><br>");
	}
	mbody=combinestring(mbody,"</td></tr></table></table>");
	mbody2=combinestring(mbody2,"");
	//mbody2=combinestring(mbody2,"<h2>By Department</h2><br>");
		while(rs2.next())
		{
		String sdepartment = rs2.getString("department");
		Vector v;
		v = TimeSheetSummary.getDepartmentItemsMultiDate(con,sdepartment, doFormatDateDb(getDateDb(enddate)),doFormatDateDb(getDateDb(enddate)));		
		mbody2=combinestring(mbody2,"<table width=\"75%\"><tr><td><table border=1 align=\"left\">");
		mbody2=combinestring(mbody2,"<tr><td>Department: "+sdepartment+"</td></tr>");
		mbody2=combinestring(mbody2,"</table></td></tr>");	

		if (v.size()>0)
		{
			mbody2=combinestring(mbody2,"<tr><td><table border=1  align=\"left\">");
			mbody2=combinestring(mbody2,"<tr><td>Call-Type</td><td>Count</td><td>Total-Collected</td><td>Non-Commision-Billed</td><td>Commision-Billed</td><td>Commision</td><td>Time</td><td>Time-Without-Travel</td></tr>");
			for (int i = 0 ; i < v.size(); i++)
			{
				TimeSheetSummary ts = (TimeSheetSummary) v.elementAt(i);
				String tamount = ts.Amount();
				String tcamount = ts.CAmount();
				String tamount_collected = ts.AmountCollected();
				String tcommision = ts.Commision();
				String ctype=ts.CType();
				String callcount=ts.CallCount();
				String timewithtravel=ts.TimeWithTravel();
				String timenotravel=ts.TimeNoTravel();
				mbody2=combinestring(mbody2,"<tr><td>"+ctype+"</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
				
			}
 //System.out.println("select tech_table.department as department, count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet, tech_table  where time_sheet.login=tech_table.username and tdate>='"+doFormatDateDb(getDateDb(startdate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"' and department='"+sdepartment+"' group by department;\n");
 ResultSet rs3 = stmt.executeQuery("select tech_table.department as department, count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet, tech_table  where time_sheet.login=tech_table.username and tdate>='"+doFormatDateDb(getDateDb(enddate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"' and department='"+sdepartment+"' group by department;");

                while(rs3.next())
                {
                        String tamount =rs3.getString("amount");
                        String tcamount = rs3.getString("camount");
                        String tamount_collected = rs3.getString("amount_collected");
                        String tcommision = rs3.getString("commision");
                        String callcount=rs3.getString("callcount");
                        String timewithtravel=rs3.getString("time_with_travel");
                        //String timewithtravel="-";
                        String timenotravel=rs3.getString("time_no_travel");
                        //String timenotravel="-";
        mbody2=combinestring(mbody2,"<tr><td>Total</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
		}
		mbody2=combinestring(mbody2,"</table><br></td></tr>");

			}
		else {

			mbody2=combinestring(mbody2,"<tr><td><table border=0  align=\"left\">");
			mbody2=combinestring(mbody2,"<tr><td>**NO DATA TRANSMITTED</td></tr></table><br></td></tr>");
		}
		mbody2=combinestring(mbody2,"<tr><td><table border=0 align=\"left\">");
		mbody2=combinestring(mbody2,"<tr><td> </td></tr></table><br>");
	}
	mbody2=combinestring(mbody2,"</td></tr></table></table>");
		




////////////////////////////////////////////////////////
// Here is where we end the http headers
////////////////////////////////////////////////////////
//		out.println(
               //String newstring = mbody.replaceAll("<br>","\n");
                out.println(mbody);
                out.println(mbody2);
	//con.close();
	
	emailserver = doGetSmtpServer(username);
	//emailsendaddress=doGetIns_Email(username);
	emailsendaddress=doGetCompanyReportEmail();
	techemailaddress=doGetTech_Email(username);	
      String smtpuser = doGetSmtpUser(username);
      String smtppassword = doGetSmtpPassword(username);
        doMailSend(emailserver, emailsendaddress, techemailaddress, "Daily Time Recap - "+enddate+" - By Tech" , mbody, smtpuser, smtppassword);
	doMailSend(emailserver, emailsendaddress, techemailaddress, "Daily Time Recap - "+enddate+" - By Department" , mbody2, smtpuser, smtppassword);
		
	return mbody;    
	
	
        }

public static String getWeekToDate (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir)
	throws SQLException, TodoException, Exception
        {
	    	Format formatter;
		Date monday;
		Date sunday;
		Calendar calendar = Calendar.getInstance();
		sunday=calendar.getTime();
		monday=calendar.getTime();
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
		{
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
		{
			calendar.add(Calendar.DATE,-1);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY)
		{
			calendar.add(Calendar.DATE,5);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
		{
			calendar.add(Calendar.DATE,4);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
		{
			calendar.add(Calendar.DATE,3);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
		{
			calendar.add(Calendar.DATE,2);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
		{
			calendar.add(Calendar.DATE,1);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String enddate = doFormatDate(sunday);
		String startdate = doFormatDate(monday);
		
	out.println("Time Report For "+startdate+" to "+enddate+"<br>");
	Statement stmt2 = con.createStatement();
	Statement stmt = con.createStatement();
	ResultSet rs2 = stmt2.executeQuery("select department, transmit from tech_table where transmit=1 group by department;");
	System.out.println ("Start Date: "+startdate+"\n");
	System.out.println ("End Date: "+enddate+"\n");

   	Vector u;
	String mbody="";
	String mbody2="";
	mbody=combinestring(mbody,"<html>");
	mbody=combinestring(mbody,"<head><title>Time Sheet Report</title></head><body><h2>By Technician</h2>");
	u = UniTechInfo.getAllTransmitItems(con);
	int counter=0;
	for (int iu = 0 ; iu < u.size(); iu++)
	{
		UniTechInfo tu = (UniTechInfo) u.elementAt(iu);
		String tech_init = tu.getTechInit();
		String lusername = tu.getUserName();
		String tech_name = tu.getTechName();
		String department = tu.getDepartment();
		Vector v;
		v = TimeSheetSummary.getLoginItemsMultiDate(con,lusername, doFormatDateDb(getDateDb(startdate)),doFormatDateDb(getDateDb(enddate)));
		mbody=combinestring(mbody,"<table width=\"75%\"><tr width=\"100%\"><td><table border=1 align=\"left\">");
		mbody=combinestring(mbody,"<tr><td>Tech ID: "+lusername+" - "+tech_name+"</td></tr>");
		mbody=combinestring(mbody,"</table></td></tr>");		
		if (v.size()>0)
		{
			mbody=combinestring(mbody,"<tr><td><table border=1 align=\"left\">");
			mbody=combinestring(mbody,"<tr><td>Call-Type</td><td>Count</td><td>Total-Collected</td><td>Non-Commision-Billed</td><td>Commision-Billed</td><td>Commision</td><td>Time</td><td>Time-Without-Travel</td></tr>");
			for (int i = 0 ; i < v.size(); i++)
			{
				TimeSheetSummary ts = (TimeSheetSummary) v.elementAt(i);
				String tamount = ts.Amount();
				String tcamount = ts.CAmount();
				String tamount_collected = ts.AmountCollected();
				String tcommision = ts.Commision();
				String ctype=ts.CType();
				String callcount=ts.CallCount();
				String timewithtravel=ts.TimeWithTravel();
				String timenotravel=ts.TimeNoTravel();
				mbody=combinestring(mbody,"<tr><td>"+ctype+"</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
				
			}
			        ResultSet rs = stmt.executeQuery("select count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel, ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet where login='"+lusername+"' and tdate>='"+doFormatDateDb(getDateDb(startdate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"';");
                while(rs.next())
                {
                        String tamount =rs.getString("amount");
                        String tcamount = rs.getString("camount");
                        String tamount_collected = rs.getString("amount_collected");
                        String tcommision = rs.getString("commision");
                        String callcount=rs.getString("callcount");
                        String timewithtravel=rs.getString("time_with_travel");
                        String timenotravel=rs.getString("time_no_travel");
        mbody=combinestring(mbody,"<tr><td>Total</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
                }


			mbody=combinestring(mbody,"</table><br></td></tr>");
		}
		else {
			mbody=combinestring(mbody,"<tr><td><table border=0 align=\"left\">");
			mbody=combinestring(mbody,"<tr><td>**NO DATA TRANSMITTED</td></tr></table><br></td></tr>");
		}
		mbody=combinestring(mbody,"<tr><td><table border=0 align=\"left\">");
		mbody=combinestring(mbody,"<tr><td> </td></tr></table><br>");
	}
	mbody=combinestring(mbody,"</td></tr></table></table>");
	mbody2=combinestring(mbody2,"");
		while(rs2.next())
		{
		String sdepartment = rs2.getString("department");
		Vector v;
		v = TimeSheetSummary.getDepartmentItemsMultiDate(con,sdepartment, doFormatDateDb(getDateDb(startdate)),doFormatDateDb(getDateDb(enddate)));		
		mbody2=combinestring(mbody2,"<table width=\"75%\"><tr><td><table border=1 align=\"left\">");
		mbody2=combinestring(mbody2,"<tr><td>Department: "+sdepartment+"</td></tr>");
		mbody2=combinestring(mbody2,"</table></td></tr>");	

		if (v.size()>0)
		{
			mbody2=combinestring(mbody2,"<tr><td><table border=1  align=\"left\">");
			mbody2=combinestring(mbody2,"<tr><td>Call-Type</td><td>Count</td><td>Total-Collected</td><td>Non-Commision-Billed</td><td>Commision-Billed</td><td>Commision</td><td>Time</td><td>Time-Without-Travel</td></tr>");
			for (int i = 0 ; i < v.size(); i++)
			{
				TimeSheetSummary ts = (TimeSheetSummary) v.elementAt(i);
				String tamount = ts.Amount();
				String tcamount = ts.CAmount();
				String tamount_collected = ts.AmountCollected();
				String tcommision = ts.Commision();
				String ctype=ts.CType();
				String callcount=ts.CallCount();
				String timewithtravel=ts.TimeWithTravel();
				String timenotravel=ts.TimeNoTravel();
				mbody2=combinestring(mbody2,"<tr><td>"+ctype+"</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
				
			}
 ResultSet rs3 = stmt.executeQuery("select tech_table.department as department, count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet, tech_table  where time_sheet.login=tech_table.username and tdate>='"+doFormatDateDb(getDateDb(startdate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"' and department='"+sdepartment+"' group by department;");

                while(rs3.next())
                {
                        String tamount =rs3.getString("amount");
                        String tcamount = rs3.getString("camount");
                        String tamount_collected = rs3.getString("amount_collected");
                        String tcommision = rs3.getString("commision");
                        String callcount=rs3.getString("callcount");
                        String timewithtravel=rs3.getString("time_with_travel");
                        String timenotravel=rs3.getString("time_no_travel");
        mbody2=combinestring(mbody2,"<tr><td>Total</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
		}
		mbody2=combinestring(mbody2,"</table><br></td></tr>");

			}
		else {

			mbody2=combinestring(mbody2,"<tr><td><table border=0  align=\"left\">");
			mbody2=combinestring(mbody2,"<tr><td>**NO DATA TRANSMITTED</td></tr></table><br></td></tr>");
		}
		mbody2=combinestring(mbody2,"<tr><td><table border=0 align=\"left\">");
		mbody2=combinestring(mbody2,"<tr><td> </td></tr></table><br>");
	}
	mbody2=combinestring(mbody2,"</td></tr></table></table>");
                out.println(mbody);
                out.println(mbody2);
	emailserver = doGetSmtpServer(username);
	emailsendaddress=doGetCompanyReportEmail();
	techemailaddress=doGetTech_Email(username);	
      String smtpuser = doGetSmtpUser(username);
      String smtppassword = doGetSmtpPassword(username);
        doMailSend(emailserver, emailsendaddress, techemailaddress, "Week to Date Time Recap "+startdate+" - "+enddate+" - By Tech" , mbody, smtpuser, smtppassword);
	doMailSend(emailserver, emailsendaddress, techemailaddress, "Week to Date Time Recap "+startdate+" - "+enddate+" - By Department" , mbody2, smtpuser, smtppassword);
	return mbody;    
	
        }

	
public static String getQuarterToDate (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir)
	throws SQLException, TodoException, Exception
        {
	    	Format formatter;
		Date monday;
		Date sunday;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-1);
		sunday=calendar.getTime();
		monday=calendar.getTime();
		// 1st quarter = Sept 1 to December 31
		// 2nd quarter = Jan 1 to March 31
		// 3rd quarter = April 1 to June 31
		// 4th quarter = July 1 to August 31
		String endqtr="";
		String startqtr="";
		if (calendar.get(Calendar.MONTH) == Calendar.JANUARY)
		{
			startqtr="01-01-";
			endqtr="03-31-";
			// 1st Quarter
			
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY)
		{
			startqtr="01-01-";
			endqtr="03-31-";
			// 1st Quarter
			
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.MARCH)
		{
			startqtr="01-01-";
			endqtr="03-31-";
			// 1st Quarter
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.APRIL)
		{
			startqtr="04-01-";
			endqtr="06-30-";
			// 2nd Quarter
			
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.MAY)
		{
			startqtr="04-01-";
			endqtr="06-30-";
			// 2nd Quarter
			
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.JUNE)
		{
			startqtr="04-01-";
			endqtr="06-30-";
			// 2nd Quarter
			
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.JULY)
		{
			startqtr="07-01-";
			endqtr="09-30-";
			// 3rd Quarter
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.AUGUST)
		{
			startqtr="07-01-";
			endqtr="09-30-";
			// 3rd Quarter
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER)
		{
			startqtr="07-01-";
			endqtr="09-30-";
		
			// 3rd Quarter
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.OCTOBER)
		{
			startqtr="10-01-";
			endqtr="12-31-";
			// 4th Quarter
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER)
		{
			startqtr="10-01-";
			endqtr="12-31-";
			// 4th Quarter
		}
		else if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER)
		{
			startqtr="10-01-";
			endqtr="12-31-";
			// 4th Quarter
		}


		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy");
		formatter = new SimpleDateFormat("yyyy");
		String enddate = combinestring(endqtr,formatter.format(sunday));
		String startdate = combinestring(startqtr,formatter.format(monday));
		
	out.println("Time Report For "+startdate+" to "+enddate+"<br>");
	Statement stmt2 = con.createStatement();
	Statement stmt = con.createStatement();
	ResultSet rs2 = stmt2.executeQuery("select department, transmit from tech_table where transmit=1 group by department;");
	System.out.println ("Start Date: "+startdate+"\n");
	System.out.println ("End Date: "+enddate+"\n");

   	Vector u;
	String mbody="";
	String mbody2="";
	mbody=combinestring(mbody,"<html>");
	mbody=combinestring(mbody,"<head><title>Time Sheet Report</title></head><body><h2>By Technician</h2>");
	u = UniTechInfo.getAllTransmitItems(con);
	int counter=0;
	for (int iu = 0 ; iu < u.size(); iu++)
	{
		UniTechInfo tu = (UniTechInfo) u.elementAt(iu);
		String tech_init = tu.getTechInit();
		String lusername = tu.getUserName();
		String tech_name = tu.getTechName();
		String department = tu.getDepartment();
		Vector v;
		v = TimeSheetSummary.getLoginItemsMultiDate(con,lusername, doFormatDateDb(getDateDb(startdate)),doFormatDateDb(getDateDb(enddate)));
		mbody=combinestring(mbody,"<table width=\"75%\"><tr width=\"100%\"><td><table border=1 align=\"left\">");
		mbody=combinestring(mbody,"<tr><td>Tech ID: "+lusername+" - "+tech_name+"</td></tr>");
		mbody=combinestring(mbody,"</table></td></tr>");		
		if (v.size()>0)
		{
			mbody=combinestring(mbody,"<tr><td><table border=1 align=\"left\">");
			mbody=combinestring(mbody,"<tr><td>Call-Type</td><td>Count</td><td>Total-Collected</td><td>Non-Commision-Billed</td><td>Commision-Billed</td><td>Commision</td><td>Time</td><td>Time-Without-Travel</td></tr>");
			for (int i = 0 ; i < v.size(); i++)
			{
				TimeSheetSummary ts = (TimeSheetSummary) v.elementAt(i);
				String tamount = ts.Amount();
				String tcamount = ts.CAmount();
				String tamount_collected = ts.AmountCollected();
				String tcommision = ts.Commision();
				String ctype=ts.CType();
				String callcount=ts.CallCount();
				String timewithtravel=ts.TimeWithTravel();
				String timenotravel=ts.TimeNoTravel();
				mbody=combinestring(mbody,"<tr><td>"+ctype+"</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
				
			}
			        ResultSet rs = stmt.executeQuery("select count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel, ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet where login='"+lusername+"' and tdate>='"+doFormatDateDb(getDateDb(startdate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"';");
                while(rs.next())
                {
                        String tamount =rs.getString("amount");
                        String tcamount = rs.getString("camount");
                        String tamount_collected = rs.getString("amount_collected");
                        String tcommision = rs.getString("commision");
                        String callcount=rs.getString("callcount");
                        String timewithtravel=rs.getString("time_with_travel");
                        String timenotravel=rs.getString("time_no_travel");
        mbody=combinestring(mbody,"<tr><td>Total</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
                }


			mbody=combinestring(mbody,"</table><br></td></tr>");
		}
		else {
			mbody=combinestring(mbody,"<tr><td><table border=0 align=\"left\">");
			mbody=combinestring(mbody,"<tr><td>**NO DATA TRANSMITTED</td></tr></table><br></td></tr>");
		}
		mbody=combinestring(mbody,"<tr><td><table border=0 align=\"left\">");
		mbody=combinestring(mbody,"<tr><td> </td></tr></table><br>");
	}
	mbody=combinestring(mbody,"</td></tr></table></table>");
	mbody2=combinestring(mbody2,"");
		while(rs2.next())
		{
		String sdepartment = rs2.getString("department");
		Vector v;
		v = TimeSheetSummary.getDepartmentItemsMultiDate(con,sdepartment, doFormatDateDb(getDateDb(startdate)),doFormatDateDb(getDateDb(enddate)));		
		mbody2=combinestring(mbody2,"<table width=\"75%\"><tr><td><table border=1 align=\"left\">");
		mbody2=combinestring(mbody2,"<tr><td>Department: "+sdepartment+"</td></tr>");
		mbody2=combinestring(mbody2,"</table></td></tr>");	

		if (v.size()>0)
		{
			mbody2=combinestring(mbody2,"<tr><td><table border=1  align=\"left\">");
			mbody2=combinestring(mbody2,"<tr><td>Call-Type</td><td>Count</td><td>Total-Collected</td><td>Non-Commision-Billed</td><td>Commision-Billed</td><td>Commision</td><td>Time</td><td>Time-Without-Travel</td></tr>");
			for (int i = 0 ; i < v.size(); i++)
			{
				TimeSheetSummary ts = (TimeSheetSummary) v.elementAt(i);
				String tamount = ts.Amount();
				String tcamount = ts.CAmount();
				String tamount_collected = ts.AmountCollected();
				String tcommision = ts.Commision();
				String ctype=ts.CType();
				String callcount=ts.CallCount();
				String timewithtravel=ts.TimeWithTravel();
				String timenotravel=ts.TimeNoTravel();
				mbody2=combinestring(mbody2,"<tr><td>"+ctype+"</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
				
			}
 ResultSet rs3 = stmt.executeQuery("select tech_table.department as department, count(tsid) as callcount, sum(amount) as amount, sum(amount_collected) as amount_collected, sum(camount) as camount, sum(commision) as commision,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,dispatch_time))))) as time_with_travel,  ucase(SEC_TO_TIME(sum(TIME_TO_SEC(subtime(time_out,time_in))))) as time_no_travel from time_sheet, tech_table  where time_sheet.login=tech_table.username and tdate>='"+doFormatDateDb(getDateDb(startdate))+"' and tdate<='"+doFormatDateDb(getDateDb(enddate))+"' and department='"+sdepartment+"' group by department;");

                while(rs3.next())
                {
                        String tamount =rs3.getString("amount");
                        String tcamount = rs3.getString("camount");
                        String tamount_collected = rs3.getString("amount_collected");
                        String tcommision = rs3.getString("commision");
                        String callcount=rs3.getString("callcount");
                        String timewithtravel=rs3.getString("time_with_travel");
                        String timenotravel=rs3.getString("time_no_travel");
        mbody2=combinestring(mbody2,"<tr><td>Total</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
		}
		mbody2=combinestring(mbody2,"</table><br></td></tr>");

			}
		else {

			mbody2=combinestring(mbody2,"<tr><td><table border=0  align=\"left\">");
			mbody2=combinestring(mbody2,"<tr><td>**NO DATA TRANSMITTED</td></tr></table><br></td></tr>");
		}
		mbody2=combinestring(mbody2,"<tr><td><table border=0 align=\"left\">");
		mbody2=combinestring(mbody2,"<tr><td> </td></tr></table><br>");
	}
	mbody2=combinestring(mbody2,"</td></tr></table></table>");
                out.println(mbody);
                out.println(mbody2);
	emailserver = doGetSmtpServer(username);
	emailsendaddress=doGetCompanyReportEmail();
	techemailaddress=doGetTech_Email(username);	
      String smtpuser = doGetSmtpUser(username);
      String smtppassword = doGetSmtpPassword(username);
        doMailSend(emailserver, emailsendaddress, techemailaddress, "Week to Date Time Recap "+startdate+" - "+enddate+" - By Tech" , mbody, smtpuser, smtppassword);
	doMailSend(emailserver, emailsendaddress, techemailaddress, "Week to Date Time Recap "+startdate+" - "+enddate+" - By Department" , mbody2, smtpuser, smtppassword);
	return mbody;    
	
        }

	
}
