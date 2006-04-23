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
public class ServsysScreenReport extends UniCash
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
/*
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
                String startdate = doFormatDate(dateToBeTested.getTime());  */
		
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
		
	//out.println("Time Report For "+enddate+"<br>");
	Statement stmt2 = con.createStatement();
	Statement stmt = con.createStatement();
	ResultSet rs2 = stmt2.executeQuery("select department, transmit from tech_table where transmit=1 group by department;");
	//Format formatter;
        //String startdate = doFormatDate(dateToBeTested.getTime());

   	Vector u;
	String mbody="";
	String mbody2="";
	String mbody3="";
	String mbody4="";
	String mbody5="";
	mbody4=combinestring(mbody4,"<html>");
	mbody5=combinestring(mbody5,"<html>");
	mbody4=combinestring(mbody4,"<br>Start Date: "+startdate+"<br>");
	mbody4=combinestring(mbody4,"End Date: "+enddate+"<br>");
	mbody5=combinestring(mbody5,"<br>Start Date: "+startdate+"<br>");
	mbody5=combinestring(mbody5,"End Date: "+enddate+"<br>");
	mbody5=combinestring(mbody5,"<table>");
	while(rs2.next())
		{
		String sdepartment = rs2.getString("department");
	mbody5=combinestring(mbody5,"<tr><td><table border=1  align=\"left\">");
	mbody5=combinestring(mbody5,"<tr><td>"+sdepartment+"<br>Department</td><td>#ps</td><td>#Tel<br>Leads</td><td>#PS<br>calls</td><td>#TM<br>calls</td><td>$<br>repair</td><td>avg<br>repair</td><td>Equip<br>Sales</td><td>%psa<br>Sold</td><td>#psa<br>Sold</td><td>$Upgrade<br>Sales</td><td>#War</td><td>#Svc<br>Cb</td><td>#Ins<br>Cb</td><td>#Maint<br>CB</td><td>#Sales<br>Leads</td><td>#Install</td></tr>");
		
	u = UniTechInfo.getAllTransmitItems(con, sdepartment);
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
		int cbi=0;
				int cbm=0;
				int cbs=0;
				int cmp=0;
				int ins=0;
				int lnch=0;
				int mtg=0;
				int nl=0;
				int nle=0;
				int nlp=0;
				int nlu=0;
				int oth=0;
				int pf=0;
				int pmnr=0;
				int pmnre=0;
				int pmnru=0;
				int pmr=0;
				int pmre=0;
				int pmrr=0;
				int pmru=0;
				int sc=0;
				int scf=0;
				int scfe=0;
				int scfp=0;
				int scfu=0;
				int sct=0;
				int scte=0;
				int sctp=0;
				int sctu=0;
				int slnp=0;
				int slpg=0;
				int tr=0;
				int war=0;
				int dash=0;
				
				double cbid=0.00;
				double cbmd=0.00;
				double cbsd=0.00;
				double cmpd=0.00;
				double insd=0.00;
				double lnchd=0.00;
				double mtgd=0.00;
				double nld=0.00;
				double nled=0.00;
				double nlpd=0.00;
				double nlud=0.00;
				double othd=0.00;
				double pfd=0.00;
				double pmnrd=0.00;
				double pmnred=0.00;
				double pmnrud=0.00;
				double pmrd=0.00;
				double pmred=0.00;
				double pmrrd=0.00;
				double pmrud=0.00;
				double scd=0.00;
				double scfd=0.00;
				double scfed=0.00;
				double scfpd=0.00;
				double scfud=0.00;
				double sctd=0.00;
				double scted=0.00;
				double sctpd=0.00;
				double sctud=0.00;
				double slnpd=0.00;
				double slpgd=0.00;
				double trd=0.00;
				double ward=0.00;
				double dashd=0.00;
				
				
				double cbidc=0.00;
				double cbmdc=0.00;
				double cbsdc=0.00;
				double cmpdc=0.00;
				double insdc=0.00;
				double lnchdc=0.00;
				double mtgdc=0.00;
				double nldc=0.00;
				double nledc=0.00;
				double nlpdc=0.00;
				double nludc=0.00;
				double othdc=0.00;
				double pfdc=0.00;
				double pmnrdc=0.00;
				double pmnredc=0.00;
				double pmnrudc=0.00;
				double pmrdc=0.00;
				double pmredc=0.00;
				double pmrrdc=0.00;
				double pmrudc=0.00;
				double scdc=0.00;
				double scfdc=0.00;
				double scfedc=0.00;
				double scfpdc=0.00;
				double scfudc=0.00;
				double sctdc=0.00;
				double sctedc=0.00;
				double sctpdc=0.00;
				double sctudc=0.00;
				double slnpdc=0.00;
				double slpgdc=0.00;
				double trdc=0.00;
				double wardc=0.00;
				double dashdc=0.00;
				
		if (v.size()>0)
		{
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
				if (ctype.equalsIgnoreCase("sc")) {
					// plain service calls not sure if full or time material
					scd=Double.parseDouble(tamount);
					scdc=Double.parseDouble(tcamount);
					sc=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("sct")) {
					// plain time material calls
					sctd=Double.parseDouble(tamount);
					sctdc=Double.parseDouble(tcamount);
					sct=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scf")) {
					// plain full service calls
					scfd=Double.parseDouble(tamount);
					scfdc=Double.parseDouble(tcamount);
					scf=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scfe")) {
					// plain full service calls equipment sales
					scfed=Double.parseDouble(tamount);
					scfedc=Double.parseDouble(tcamount);
					scfe=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scfu")) {
					// plain full sercice calls upgrade sales
					scfud=Double.parseDouble(tamount);
					scfudc=Double.parseDouble(tcamount);
					scfu=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scte")) {
					// plain time material calls equipment sales
					scted=Double.parseDouble(tamount);
					sctedc=Double.parseDouble(tcamount);
					scte=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("sctu")) {
					// plain time material calls upgrade sales
					sctud=Double.parseDouble(tamount);
					sctudc=Double.parseDouble(tcamount);
					sctu=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scfp")) {
					// Full service customer planned service sold
					scfpd=Double.parseDouble(tamount);
					scfpdc=Double.parseDouble(tcamount);
					scfp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("sctp")) {
					// Time material customer planned service sale
					sctpd=Double.parseDouble(tamount);
					sctpdc=Double.parseDouble(tcamount);
					sctp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("slnp")) {
					// Sales lead need proposal
					slnpd=Double.parseDouble(tamount);
					slnpdc=Double.parseDouble(tcamount);
					slnp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("slpg")) {
					// Sales lead proposal given
					slpgd=Double.parseDouble(tamount);
					slpgdc=Double.parseDouble(tcamount);
					slpg=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("tr")) {
					// Training needed
					trd=Double.parseDouble(tamount);
					trdc=Double.parseDouble(tcamount);
					tr=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("war")) {
					// Warrenty Call
					ward=Double.parseDouble(tamount);
					wardc=Double.parseDouble(tcamount);
					war=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("cbi")) {
					// Call back on install
					cbid=Double.parseDouble(tamount);
					cbidc=Double.parseDouble(tcamount);
					cbi=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("cbs")) {
					// Call back on service
					cbsd=Double.parseDouble(tamount);
					cbsdc=Double.parseDouble(tcamount);
					cbs=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("cbi")) {
					// Call back on maint
					cbid=Double.parseDouble(tamount);
					cbidc=Double.parseDouble(tcamount);
					cbi=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("cmp")) {
					// Computer work
					cmpd=Double.parseDouble(tamount);
					cmpdc=Double.parseDouble(tcamount);
					cmp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("ins")) {
					// Install Work
					insd=Double.parseDouble(tamount);
					insdc=Double.parseDouble(tcamount);
					ins=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("lnch")) {
					// Lunch Break
					lnchd=Double.parseDouble(tamount);
					lnchdc=Double.parseDouble(tcamount);
					lnch=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("mtg")) {
					// Meeting 
					mtgd=Double.parseDouble(tamount);
					mtgdc=Double.parseDouble(tcamount);
					mtg=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("nl")) {
					// New Lead
					nld=Double.parseDouble(tamount);
					nldc=Double.parseDouble(tcamount);
					nl=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("nle")) {
					// New Lead Equipment Sold
					nled=Double.parseDouble(tamount);
					nledc=Double.parseDouble(tcamount);
					nle=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("nlp")) {
					// New Lead Preventative Sold
					nlpd=Double.parseDouble(tamount);
					nlpdc=Double.parseDouble(tcamount);
					nlp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("nlu")) {
					// New Lead Upgrade Sold
					nlud=Double.parseDouble(tamount);
					nludc=Double.parseDouble(tcamount);
					nlu=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("oth")) {
					// Other - Unknown
					othd=Double.parseDouble(tamount);
					othdc=Double.parseDouble(tcamount);
					oth=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pf")) {
					// Proposal and Follow-up work
					pfd=Double.parseDouble(tamount);
					pfdc=Double.parseDouble(tcamount);
					pf=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnr")) {
					// Preventative not up for renewal
					pmnrd=Double.parseDouble(tamount);
					pmnrdc=Double.parseDouble(tcamount);
					pmnr=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnre")) {
					// Preventative not for renewal equipment sold
					pmnred=Double.parseDouble(tamount);
					pmnredc=Double.parseDouble(tcamount);
					pmnre=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnru")) {
					// Preventative not for renewal upgrade sold
					pmnrud=Double.parseDouble(tamount);
					pmnrudc=Double.parseDouble(tcamount);
					pmnru=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmr")) {
					// Preventative for renewal
					pmrd=Double.parseDouble(tamount);
					pmrdc=Double.parseDouble(tcamount);
					pmrd=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmre")) {
					// Preventative renewal equipment sold
					pmred=Double.parseDouble(tamount);
					pmredc=Double.parseDouble(tcamount);
					pmre=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmru")) {
					// Preventative renewal upgrade sold
					pmrud=Double.parseDouble(tamount);
					pmrudc=Double.parseDouble(tcamount);
					pmru=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmrr")) {
					// Preventative renewal upgrade sold
					pmrrd=Double.parseDouble(tamount);
					pmrrdc=Double.parseDouble(tcamount);
					pmrr=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("-")) {
					// Unknown - unclassified
					dashd=Double.parseDouble(tamount);
					dashdc=Double.parseDouble(tcamount);
					dash=Integer.parseInt(callcount);
				} 
				
					}
					
				int totsalescalls=pf+slnp+slpg;
				double totsalescallsd=pfd+slnpd+slpgd;
				double totsalescallsdc=pfdc+slnpdc+slpgdc;
				
				int totservtcall=sc+sct+scte+sctp+sctu+dash;
				int totservfcall=scf+scfe+scfp+scfu;
				int totservcalls=totservtcall+totservfcall;
				
				double totservtcalld=scd+sctd+scted+sctpd+sctud+dashd;
				double totservfcalld=scfd+scfed+scfpd+scfud;
				double totservcallsd=totservtcalld+totservfcalld;
				double totservtcalldc=scdc+sctdc+sctedc+sctpdc+sctudc+dashdc;
				double totservfcalldc=scfdc+scfedc+scfpdc+scfudc;
				double totservcallsdc=totservtcalldc+totservfcalldc;
				
				int totpmnrcall = pmnr+pmnre+pmnru;
				int totpmrcall=pmr+pmre+pmrr+pmru;

				double totpmnrcalld = pmnrd+pmnred+pmnrud;
				double totpmrcalld=pmrd+pmred+pmrrd+pmrud;
			
				double totpmcallsd=totpmrcalld+totpmnrcalld;
				
				double totpmnrcalldc = pmnrdc+pmnredc+pmnrudc;
				double totpmrcalldc=pmrd+pmredc+pmrrdc+pmrudc;
				double totpmcallsdc=totpmrcalldc+totpmnrcalldc;
				
				double totupsales = pmrud+pmnrud+scfud+sctud+nlud+pmrudc+pmnrudc+scfudc+sctudc+nludc;
				double totequipsales = pmred+pmnred+scfed+scted+nled+pmredc+pmnredc+scfedc+sctedc+nledc+slpgd+slpgdc+pfd+pfdc;
				
				int totnl=nl+nle+nlu+nlp;
				double totnld=nld+nled+nlud+nlpd;
				double totnldc=nldc+nledc+nludc+nlpdc;
				int totpmcalls=totpmrcall+totpmnrcall;				
				int totpspossible = totpmrcall+totpmnrcall+totnl+totservtcall;
				int pssold= pmrr+nlp+sctp;
				double perpssold=0;
				if (totpspossible > 0) {
				perpssold = (pssold / totpspossible)*100;
				}
				double avgsvccall=0.00;
				if (totservcalls>0) {
				avgsvccall = totservcallsd/totservcalls;
				} else {
				avgsvccall=0.00;
				}								
				mbody5=combinestring(mbody5,"<tr><td>"+tech_name+"</td><td>"+totpmcalls+"</td><td>"+totnl+"</td><td>"+totservfcall+"</td><td>"+totservtcall+"</td><td>"+NumberFormat.getCurrencyInstance().format(totservcallsd)+"</td><td>"+NumberFormat.getCurrencyInstance().format(avgsvccall)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totequipsales)+"</td><td>"+perpssold+"</td><td>"+pssold+"</td><td>"+NumberFormat.getCurrencyInstance().format(totupsales)+"</td><td>"+war+"</td><td>"+cbs+"</td><td>"+cbi+"</td><td>"+cbm+"</td><td>"+totsalescalls+"</td><td>"+ins+"</td></tr>");
			}
	}
	mbody4=combinestring(mbody4,"<br><table border=1  align=\"left\">");
	mbody4=combinestring(mbody4,"<tr><td>Department</td><td>#ps</td><td>#Tel<br>Leads</td><td>#PS<br>calls</td><td>#TM<br>calls</td><td>$<br>repair</td><td>avg<br>repair</td><td>Equip<br>Sales</td><td>%psa<br>Sold</td><td>#psa<br>Sold</td><td>$Upgrade<br>Sales</td><td>#War</td><td>#Svc<br>Cb</td><td>#Ins<br>Cb</td><td>#Maint<br>CB</td><td># Sales<br>Leads</td><td>#Install</td></tr>");
		

		Vector v;
		v = TimeSheetSummary.getDepartmentItemsMultiDate(con,sdepartment, doFormatDateDb(getDateDb(startdate)),doFormatDateDb(getDateDb(enddate)));		
		if (v.size()>0)
		{
				int cbi=0;
				int cbm=0;
				int cbs=0;
				int cmp=0;
				int ins=0;
				int lnch=0;
				int mtg=0;
				int nl=0;
				int nle=0;
				int nlp=0;
				int nlu=0;
				int oth=0;
				int pf=0;
				int pmnr=0;
				int pmnre=0;
				int pmnru=0;
				int pmr=0;
				int pmre=0;
				int pmrr=0;
				int pmru=0;
				int sc=0;
				int scf=0;
				int scfe=0;
				int scfp=0;
				int scfu=0;
				int sct=0;
				int scte=0;
				int sctp=0;
				int sctu=0;
				int slnp=0;
				int slpg=0;
				int tr=0;
				int war=0;
				int dash=0;
				
				double cbid=0.00;
				double cbmd=0.00;
				double cbsd=0.00;
				double cmpd=0.00;
				double insd=0.00;
				double lnchd=0.00;
				double mtgd=0.00;
				double nld=0.00;
				double nled=0.00;
				double nlpd=0.00;
				double nlud=0.00;
				double othd=0.00;
				double pfd=0.00;
				double pmnrd=0.00;
				double pmnred=0.00;
				double pmnrud=0.00;
				double pmrd=0.00;
				double pmred=0.00;
				double pmrrd=0.00;
				double pmrud=0.00;
				double scd=0.00;
				double scfd=0.00;
				double scfed=0.00;
				double scfpd=0.00;
				double scfud=0.00;
				double sctd=0.00;
				double scted=0.00;
				double sctpd=0.00;
				double sctud=0.00;
				double slnpd=0.00;
				double slpgd=0.00;
				double trd=0.00;
				double ward=0.00;
				double dashd=0.00;
				
				
				double cbidc=0.00;
				double cbmdc=0.00;
				double cbsdc=0.00;
				double cmpdc=0.00;
				double insdc=0.00;
				double lnchdc=0.00;
				double mtgdc=0.00;
				double nldc=0.00;
				double nledc=0.00;
				double nlpdc=0.00;
				double nludc=0.00;
				double othdc=0.00;
				double pfdc=0.00;
				double pmnrdc=0.00;
				double pmnredc=0.00;
				double pmnrudc=0.00;
				double pmrdc=0.00;
				double pmredc=0.00;
				double pmrrdc=0.00;
				double pmrudc=0.00;
				double scdc=0.00;
				double scfdc=0.00;
				double scfedc=0.00;
				double scfpdc=0.00;
				double scfudc=0.00;
				double sctdc=0.00;
				double sctedc=0.00;
				double sctpdc=0.00;
				double sctudc=0.00;
				double slnpdc=0.00;
				double slpgdc=0.00;
				double trdc=0.00;
				double wardc=0.00;
				double dashdc=0.00;
				
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
				
				if (ctype.equalsIgnoreCase("sc")) {
					// plain service calls not sure if full or time material
					scd=Double.parseDouble(tamount);
					scdc=Double.parseDouble(tcamount);
					sc=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("sct")) {
					// plain time material calls
					sctd=Double.parseDouble(tamount);
					sctdc=Double.parseDouble(tcamount);
					sct=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scf")) {
					// plain full service calls
					scfd=Double.parseDouble(tamount);
					scfdc=Double.parseDouble(tcamount);
					scf=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scfe")) {
					// plain full service calls equipment sales
					scfed=Double.parseDouble(tamount);
					scfedc=Double.parseDouble(tcamount);
					scfe=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scfu")) {
					// plain full sercice calls upgrade sales
					scfud=Double.parseDouble(tamount);
					scfudc=Double.parseDouble(tcamount);
					scfu=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scte")) {
					// plain time material calls equipment sales
					scted=Double.parseDouble(tamount);
					sctedc=Double.parseDouble(tcamount);
					scte=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("sctu")) {
					// plain time material calls upgrade sales
					sctud=Double.parseDouble(tamount);
					sctudc=Double.parseDouble(tcamount);
					sctu=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("scfp")) {
					// Full service customer planned service sold
					scfpd=Double.parseDouble(tamount);
					scfpdc=Double.parseDouble(tcamount);
					scfp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("sctp")) {
					// Time material customer planned service sale
					sctpd=Double.parseDouble(tamount);
					sctpdc=Double.parseDouble(tcamount);
					sctp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("slnp")) {
					// Sales lead need proposal
					slnpd=Double.parseDouble(tamount);
					slnpdc=Double.parseDouble(tcamount);
					slnp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("slpg")) {
					// Sales lead proposal given
					slpgd=Double.parseDouble(tamount);
					slpgdc=Double.parseDouble(tcamount);
					slpg=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("tr")) {
					// Training needed
					trd=Double.parseDouble(tamount);
					trdc=Double.parseDouble(tcamount);
					tr=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("war")) {
					// Warrenty Call
					ward=Double.parseDouble(tamount);
					wardc=Double.parseDouble(tcamount);
					war=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("cbi")) {
					// Call back on install
					cbid=Double.parseDouble(tamount);
					cbidc=Double.parseDouble(tcamount);
					cbi=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("cbs")) {
					// Call back on service
					cbsd=Double.parseDouble(tamount);
					cbsdc=Double.parseDouble(tcamount);
					cbs=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("cbi")) {
					// Call back on maint
					cbid=Double.parseDouble(tamount);
					cbidc=Double.parseDouble(tcamount);
					cbi=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("cmp")) {
					// Computer work
					cmpd=Double.parseDouble(tamount);
					cmpdc=Double.parseDouble(tcamount);
					cmp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("ins")) {
					// Install Work
					insd=Double.parseDouble(tamount);
					insdc=Double.parseDouble(tcamount);
					ins=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("lnch")) {
					// Lunch Break
					lnchd=Double.parseDouble(tamount);
					lnchdc=Double.parseDouble(tcamount);
					lnch=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("mtg")) {
					// Meeting 
					mtgd=Double.parseDouble(tamount);
					mtgdc=Double.parseDouble(tcamount);
					mtg=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("nl")) {
					// New Lead
					nld=Double.parseDouble(tamount);
					nldc=Double.parseDouble(tcamount);
					nl=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("nle")) {
					// New Lead Equipment Sold
					nled=Double.parseDouble(tamount);
					nledc=Double.parseDouble(tcamount);
					nle=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("nlp")) {
					// New Lead Preventative Sold
					nlpd=Double.parseDouble(tamount);
					nlpdc=Double.parseDouble(tcamount);
					nlp=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("nlu")) {
					// New Lead Upgrade Sold
					nlud=Double.parseDouble(tamount);
					nludc=Double.parseDouble(tcamount);
					nlu=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("oth")) {
					// Other - Unknown
					othd=Double.parseDouble(tamount);
					othdc=Double.parseDouble(tcamount);
					oth=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pf")) {
					// Proposal and Follow-up work
					pfd=Double.parseDouble(tamount);
					pfdc=Double.parseDouble(tcamount);
					pf=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnr")) {
					// Preventative not up for renewal
					pmnrd=Double.parseDouble(tamount);
					pmnrdc=Double.parseDouble(tcamount);
					pmnr=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnre")) {
					// Preventative not for renewal equipment sold
					pmnred=Double.parseDouble(tamount);
					pmnredc=Double.parseDouble(tcamount);
					pmnre=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnru")) {
					// Preventative not for renewal upgrade sold
					pmnrud=Double.parseDouble(tamount);
					pmnrudc=Double.parseDouble(tcamount);
					pmnru=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmr")) {
					// Preventative for renewal
					pmrd=Double.parseDouble(tamount);
					pmrdc=Double.parseDouble(tcamount);
					pmrd=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmre")) {
					// Preventative renewal equipment sold
					pmred=Double.parseDouble(tamount);
					pmredc=Double.parseDouble(tcamount);
					pmre=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmru")) {
					// Preventative renewal upgrade sold
					pmrud=Double.parseDouble(tamount);
					pmrudc=Double.parseDouble(tcamount);
					pmru=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmrr")) {
					// Preventative renewal upgrade sold
					pmrrd=Double.parseDouble(tamount);
					pmrrdc=Double.parseDouble(tcamount);
					pmrr=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("-")) {
					// Unknown - unclassified
					dashd=Double.parseDouble(tamount);
					dashdc=Double.parseDouble(tcamount);
					dash=Integer.parseInt(callcount);
				} 
				
					}
					
				int totsalescalls=pf+slnp+slpg;
				double totsalescallsd=pfd+slnpd+slpgd;
				double totsalescallsdc=pfdc+slnpdc+slpgdc;
				
				int totservtcall=sc+sct+scte+sctp+sctu+dash;
				int totservfcall=scf+scfe+scfp+scfu;
				int totservcalls=totservtcall+totservfcall;
				
				double totservtcalld=scd+sctd+scted+sctpd+sctud+dashd;
				double totservfcalld=scfd+scfed+scfpd+scfud;
				double totservcallsd=totservtcalld+totservfcalld;
				double totservtcalldc=scdc+sctdc+sctedc+sctpdc+sctudc+dashdc;
				double totservfcalldc=scfdc+scfedc+scfpdc+scfudc;
				double totservcallsdc=totservtcalldc+totservfcalldc;
				
				int totpmnrcall = pmnr+pmnre+pmnru;
				int totpmrcall=pmr+pmre+pmrr+pmru;

				double totpmnrcalld = pmnrd+pmnred+pmnrud;
				double totpmrcalld=pmrd+pmred+pmrrd+pmrud;
			
				double totpmcallsd=totpmrcalld+totpmnrcalld;
				
				double totpmnrcalldc = pmnrdc+pmnredc+pmnrudc;
				double totpmrcalldc=pmrd+pmredc+pmrrdc+pmrudc;
				double totpmcallsdc=totpmrcalldc+totpmnrcalldc;
				
				double totupsales = pmrud+pmnrud+scfud+sctud+nlud+pmrudc+pmnrudc+scfudc+sctudc+nludc;
				double totequipsales = pmred+pmnred+scfed+scted+nled+pmredc+pmnredc+scfedc+sctedc+nledc+slpgd+slpgdc+pfd+pfdc;
				
				int totnl=nl+nle+nlu+nlp;
				double totnld=nld+nled+nlud+nlpd;
				double totnldc=nldc+nledc+nludc+nlpdc;
				int totpmcalls=totpmrcall+totpmnrcall;				
				int totpspossible = totpmrcall+totpmnrcall+totnl+totservtcall;
				int pssold= pmrr+nlp+sctp+pmnru;
				double perpssold=0;
				if (totpspossible > 0) {
				perpssold = (pssold / totpspossible)*100;
				}
				double avgsvccall=0.00;
				if (totservcalls>0) {
				avgsvccall = totservcallsd/totservcalls;
				} else {
				avgsvccall=0.00;
				}
				mbody5=combinestring(mbody5,"<tr><td>"+sdepartment+"</td><td>"+totpmcalls+"</td><td>"+totnl+"</td><td>"+totservfcall+"</td><td>"+totservtcall+"</td><td>"+NumberFormat.getCurrencyInstance().format(totservcallsd)+"</td><td>"+NumberFormat.getCurrencyInstance().format(avgsvccall)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totequipsales)+"</td><td>"+perpssold+"</td><td>"+pssold+"</td><td>"+NumberFormat.getCurrencyInstance().format(totupsales)+"</td><td>"+war+"</td><td>"+cbs+"</td><td>"+cbi+"</td><td>"+cbm+"</td><td>"+totsalescalls+"</td><td>"+ins+"</td></tr>");
				mbody5=combinestring(mbody5,"</table></td></tr>");
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
		}
			}
	}
		mbody5=combinestring(mbody5,"</table></html>");
		out.println(mbody5);
		//out.println(mbody4);	
	emailserver = doGetSmtpServer(username);
	//emailsendaddress=doGetIns_Email(username);
	emailsendaddress=doGetCompanyReportEmail();
	techemailaddress=doGetTech_Email(username);	
      String smtpuser = doGetSmtpUser(username);
      String smtppassword = doGetSmtpPassword(username);
       //doMailSend(emailserver, emailsendaddress, techemailaddress, "Daily Time Recap - "+enddate+" - By Tech" , mbody, smtpuser, smtppassword);
       //doMailSend(emailserver, emailsendaddress, techemailaddress, "Daily Time Recap - "+enddate+" - By Department" , mbody2, smtpuser, smtppassword);
		
	return mbody;    
	
	
        }
	
}