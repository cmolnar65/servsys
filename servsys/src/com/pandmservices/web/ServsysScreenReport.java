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
	
 public static String getYesterday (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir)
	throws SQLException, TodoException, Exception
        {

            //String mbody="";

		String department=req.getParameter("department");
		String timeframe=req.getParameter("timeframe");
		String enddate=req.getParameter("enddate");
		String startdate=req.getParameter("startdate");
		String reptype=req.getParameter("reptype");
		String mbody10="<table border=\"1\">";
		String action=req.getParameter("action");
		if (action.equalsIgnoreCase("screenmaintthisweek")) {
		
			department="maintenance";
			timeframe="thisweek";
			reptype="";
		}
		
		if (action.equalsIgnoreCase("screenmaintlastweek")) {
		
			department="maintenance";
			timeframe="lastweek";
			reptype="";
		}
		
			
		if (action.equalsIgnoreCase("screenmaintquarter")) {
		
			department="maintenance";
			timeframe="quarter";
			reptype="";
		}
		
			
		if (action.equalsIgnoreCase("screenmaintytd")) {
		
			department="maintenance";
			timeframe="ytd";
			reptype="";
		}

		
		if (action.equalsIgnoreCase("screenservicethisweek")) {
		
			department="service";
			timeframe="thisweek";
			reptype="";
		}
		
		if (action.equalsIgnoreCase("screenservicelastweek")) {
		
			department="service";
			timeframe="lastweek";
			reptype="";
		}
		
			
		if (action.equalsIgnoreCase("screenservicequarter")) {
		
			department="service";
			timeframe="quarter";
			reptype="";
		}
		
			
		if (action.equalsIgnoreCase("screenserviceytd")) {
		
			department="service";
			timeframe="ytd";
			reptype="";
		}
		
		if (action.equalsIgnoreCase("screenmaintthisweeke")) {
		
			department="maintenance";
			timeframe="thisweek";
			reptype="email";
		}
		
		if (action.equalsIgnoreCase("screenmaintlastweeke")) {
		
			department="maintenance";
			timeframe="lastweek";
			reptype="email";
		}
		
			
		if (action.equalsIgnoreCase("screenmaintquartere")) {
		
			department="maintenance";
			timeframe="quarter";
			reptype="email";
		}
		
			
		if (action.equalsIgnoreCase("screenmaintytde")) {
		
			department="maintenance";
			timeframe="ytd";
			reptype="email";
		}

		
		if (action.equalsIgnoreCase("screenservicethisweeke")) {
		
			department="service";
			timeframe="thisweek";
			reptype="email";
		}
		
		if (action.equalsIgnoreCase("screenservicelastweeke")) {
		
			department="service";
			timeframe="lastweek";
			reptype="email";
		}
		
			
		if (action.equalsIgnoreCase("screenservicequartere")) {
		
			department="service";
			timeframe="quarter";
			reptype="email";
		}
		
			
		if (action.equalsIgnoreCase("screenserviceytde")) {
		
			department="service";
			timeframe="ytd";
			reptype="email";
		}
		
		Format formatter;
		Date monday;
		Date sunday;
		Calendar calendar = Calendar.getInstance();
		sunday=calendar.getTime();
		monday=calendar.getTime();
		
	if (timeframe.equalsIgnoreCase("thisweek")) {	
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
	} else if (timeframe.equalsIgnoreCase("lastweek")) {	
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
			calendar.add(Calendar.DATE,-2);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
		{
			calendar.add(Calendar.DATE,-3);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
		{
			calendar.add(Calendar.DATE,-4);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
		{
			calendar.add(Calendar.DATE,-5);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		}
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
		{
			calendar.add(Calendar.DATE,-6);
			sunday=calendar.getTime();
			calendar.add(Calendar.DATE,-6);
			monday=calendar.getTime();
		} 
	}
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		enddate = doFormatDate(sunday);
		startdate = doFormatDate(monday);
		
		if (timeframe.equalsIgnoreCase("quarter")) {
		
		sunday=calendar.getTime();
		monday=calendar.getTime();
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


		dateFormatter = new SimpleDateFormat("yyyy");
		formatter = new SimpleDateFormat("yyyy");
		enddate = combinestring(endqtr,formatter.format(sunday));
		startdate = combinestring(startqtr,formatter.format(monday));		

		} else if (timeframe.equalsIgnoreCase("ytd")) {
		dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
		formatter = new SimpleDateFormat("MM-dd-yyyy");
		enddate = formatter.format(sunday);
		startdate=doFormatDate(getDate(UniCash.doGetYearEndDate()));	
		}
		
	Statement stmt2 = con.createStatement();
	Statement stmt = con.createStatement();
	ResultSet rs2 = stmt2.executeQuery("select department, transmit from tech_table where transmit=1 and department='"+department+"' group by department;");

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
	if (department.equalsIgnoreCase("service")) {
	mbody5=combinestring(mbody5,"<tr BGCOLOR=#A0B8C8><td>"+sdepartment+"<br>Department</td><td>#ps</td><td>#Tel<br>Leads</td><td>#PS<br>demand<br>calls</td><td>#TM<br>demand<br>calls</td><td>$<br>repair</td><td>avg<br>repair</td><td>Equip<br>Sales</td><td>#psa<br>Sold</td><td>%psa<br>Sold</td><td>$Upgrade<br>Sales</td><td>#War</td><td>#Svc<br>Cb</td><td>#Ins<br>Cb</td><td>#Maint<br>CB</td><td>#Sales<br>Leads</td><td>#Install</td><td>Total<br>Calls<br>Run</td></tr>");
	}
	if (department.equalsIgnoreCase("maintenance")) {
	mbody5=combinestring(mbody5,"<tr BGCOLOR=#A0B8C8><td>"+sdepartment+"<br>Department</td><td>NL<br>Run</td><td>NL<br>Sold</td><td>%<br>NL<br>Sold</td><td>NL<br>$</td><td>NC<br>$</td><td>#<br>PMR</td><td>#PMRR<br>Sold</td><td>%PMRR<br>Sold</td><td>PMRR<br>$</td><td>PMRR<br>NC<br>$</td><td>#PMNR<br>Run</td><td>#<br>Sold</td><td>%PMNR<br>Sold</td><td>$PMNR<br>Sold</td><td>$<br>PMNR<br>NC</td><td>SLNP<br>Run</td><td>SLPG<br>Run</td><td>#<br>Sold</td><td>%<br>Sold</td><td>$<br>Sold</td></tr>");
	mbody10=combinestring(mbody10,"<tr BGCOLOR=#A0B8C8><td>"+sdepartment+"<br>Department</td><td>Tot<br>Run</td><td>Tot<br>Sold</td><td>%<br>Sold</td><td>$<br>Sold</td><td>$<br>NC</td></tr>");
	}
	u = UniTechInfo.getAllTransmitItems(con, sdepartment);
	int counter=0;
	for (int iu = 0 ; iu < u.size(); iu++)
	{
		UniTechInfo tu = (UniTechInfo) u.elementAt(iu);
		String tech_init = tu.getTechInit();
		String lusername = tu.getUserName();
		String tech_name = tu.getTechName();
		department = tu.getDepartment();
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
				int pfs=0;
				int slpgs=0;
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
				double slpgsd=0.00;
				double pfsd=0.00;
				double pfsdc=0.00;
				double slpgd=0.00;
				double slpgdc=0.00;
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
				double slpgsdc=0.00;
				double trdc=0.00;
				double wardc=0.00;
				double dashdc=0.00;
				// NEW CODE 2006-05-16 ADDED
				int countnlrun=0;
				int countpmrrun=0;
				int countpmnrrun=0;
				int countslpgrun=0;
				double mannlsnorund=0.00;
				double mannlsnorundc=0.00;
				int mannlsnorun=0;
				int manslpgnorun=0;
				double manslpgnorund=0.00;
				double manslpgnorundc=0.00;
				int manpmrnorun=0;
				int manpmnrnorun=0;
				double manpmrnorund=0.00;
				double manpmnrnorund=0.00;
				double manpmrnorundc=0.00;
				double manpmnrnorundc=0.00;
				
				
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
					pmr=Integer.parseInt(callcount);
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
				} else if (ctype.equalsIgnoreCase("pfs")) {
					// Proposal Follow-up Sold
					pfsd=Double.parseDouble(tamount);
					pfsdc=Double.parseDouble(tcamount);
					pfs=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("slpgs")) {
					// Sales lead proposal given sold
					slpgsd=Double.parseDouble(tamount);
					slpgsdc=Double.parseDouble(tcamount);
					slpg=Integer.parseInt(callcount);
					// NEW CODE HERE 2006-05-16
				} else if (ctype.equalsIgnoreCase("nlrun")) {
					countnlrun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmrrun")) {
					countpmrrun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnrrun")) {
					countpmnrrun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("slpgrun")) {
					countslpgrun=Integer.parseInt(callcount);
				}  else if (ctype.equalsIgnoreCase("nlsnorun")) {
					// Sales lead proposal given sold
					mannlsnorund=Double.parseDouble(tamount);
					mannlsnorundc=Double.parseDouble(tcamount);
					mannlsnorun=Integer.parseInt(callcount);
				}  else if (ctype.equalsIgnoreCase("slpgnorun")) {
					// Sales lead proposal given sold
					manslpgnorund=Double.parseDouble(tamount);
					manslpgnorundc=Double.parseDouble(tcamount);
					manslpgnorun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmrnorun")) {
					// Sales lead proposal given sold
					manpmrnorund=Double.parseDouble(tamount);
					manpmrnorundc=Double.parseDouble(tcamount);
					manpmrnorun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnrnorun")) {
					// Sales lead proposal given sold
					manpmnrnorund=Double.parseDouble(tamount);
					manpmnrnorundc=Double.parseDouble(tcamount);
					manpmnrnorun=Integer.parseInt(callcount);
				}
				
					}
					
				int totsalescalls=pf+slnp+slpg+slpgs+pfs+countslpgrun;
					// NEW CODE HERE 2006-05-16
				int totslpgrun=slpg+slpgs+countslpgrun;
				int totslsold=pfs+slpgs+manslpgnorun;
				
				double totsalescallsd=pfd+slnpd+slpgd+manslpgnorund;
				double totsalescallsdc=pfdc+slnpdc+slpgdc+manslpgnorundc;
				
				int totservtcall=sc+sct+scte+sctp+sctu+dash;
				int totservfcall=scf+scfe+scfp+scfu;
				int totservcalls=totservtcall+totservfcall;
				
				double totservtcalld=scd+sctd+scted+sctpd+sctud+dashd;
				double totservfcalld=scfd+scfed+scfpd+scfud;
				double totservcallsd=totservtcalld+totservfcalld;
				double totservtcalldc=scdc+sctdc+sctedc+sctpdc+sctudc+dashdc;
				double totservfcalldc=scfdc+scfedc+scfpdc+scfudc;
				double totservcallsdc=totservtcalldc+totservfcalldc;
				
				int totpmnrcall = pmnr+pmnre+pmnru+countpmnrrun;
				int totpmnrsold = pmnre+pmnru+manpmnrnorun;
				int totpmrcall=pmr+pmre+pmrr+pmru+countpmrrun;
				int totpmrsold = pmre+pmrr+pmru+manpmrnorun;

				
				
				double totpmnrcalld = pmnrd+pmnred+pmnrud+manpmnrnorund;
				double totpmrcalld=pmrd+pmred+pmrrd+pmrud+manpmrnorund;
			
				double totpmcallsd=totpmrcalld+totpmnrcalld;
				
				double totpmnrcalldc = pmnrdc+pmnredc+pmnrudc+manpmnrnorundc;
				double totpmrcalldc=pmrd+pmredc+pmrrdc+pmrudc+manpmrnorundc;
				double totpmcallsdc=totpmrcalldc+totpmnrcalldc;
				
				double totupsales = pmrud+pmnrud+scfud+sctud+nlud+pmrudc+pmnrudc+scfudc+sctudc+nludc;
				
				double totequipsales = pmred+pmnred+scfed+scted+nled+pmredc+pmnredc+scfedc+sctedc+nledc+slpgd+slpgdc+pfd+pfdc;
				
				int totnl=nl+nle+nlu+nlp+countnlrun;
				int totnlsold=nle+nlu+nlp+mannlsnorun;
				int totnlpsold=nlp+mannlsnorun;
				double totnld=nld+nled+nlud+nlpd+mannlsnorund;
				double totnldc=nldc+nledc+nludc+nlpdc+mannlsnorundc;
				int totpmcalls=totpmrcall+totpmnrcall;				
				int totpspossible = totpmrcall+totpmnrcall+totnl+totservtcall;
				int pssold= pmrr+nlp+sctp;
				double perpssold=0;
				if (totpspossible > 0) {
				perpssold = (pssold / totpspossible)*100;
				}
				
				BigDecimal d4=new BigDecimal(0.00);
				double pernlsold= 0.00;
				if (totnl > 0) {
				pernlsold=(Double.parseDouble(""+totnlpsold+"")/Double.parseDouble(""+totnl+""))*100;
				d4 = new BigDecimal(pernlsold);
				d4 = d4.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				
				BigDecimal d1=new BigDecimal(0.00);
				double perpmrrsold=0.00;
				if (totpmrcall > 0) {
				perpmrrsold=(Double.parseDouble(""+totpmrsold+"")/Double.parseDouble(""+totpmrcall+""))*100;
				d1 = new BigDecimal(perpmrrsold);
				d1 = d1.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				
				double perpmnrsold=0.00;
				BigDecimal d2=new BigDecimal(0.00);
				if (totpmnrsold > 0) {
				//perpmnrsold=(totpmnrsold/totpmnrcall)*100;
				perpmnrsold=999;
				perpmnrsold=(Double.parseDouble(""+totpmnrsold+"")/Double.parseDouble(""+totpmnrcall+""))*100;
				d2 = new BigDecimal(perpmnrsold);
				d2 = d2.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				
				//double ppmnrsold=0.00;
				//if (totpmnrsold > 0) {
				//	ppmnrsold=(Double.parseDouble(""+totpmnrsold+"")/Double.parseDouble(""+totpmnrcall+""))*100;
				//}
				
				
				double persales=0.00;
				BigDecimal d3=new BigDecimal(0.00);
				if (totsalescalls > 0) {
				persales=(Double.parseDouble(""+totslsold+"")/Double.parseDouble(""+totsalescalls+""))*100;	
				d3 = new BigDecimal(persales);
				d3 = d3.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				
				int gtrun = slnp+totslpgrun+totpmnrcall+totpmrcall+totnl;
				int gtsold = nlp+totpmrsold+totpmnrsold+totslsold;
				BigDecimal d5=new BigDecimal(0.00);
				double pergsold=0.00;
				if (gtrun > 0) {
				pergsold=(Double.parseDouble(""+gtsold+"")/Double.parseDouble(""+gtrun+""))*100;
				d5 = new BigDecimal(pergsold);
				d5 = d5.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				double totcomsold = totsalescallsdc+totpmnrcalldc+totpmrcalldc+totnldc;
				double totnoncomsold = totpmnrcalld+totpmrcalld+totnld;
				
				double avgsvccall=0.00;
				if (totservcalls>0) {
				avgsvccall = totservcallsd/totservcalls;
				} else {
				avgsvccall=0.00;
				}								
				int totruncalls = totpmcalls+totnl+totservfcall+totservtcall+war+cbs+cbm+totsalescalls+ins;
				if (department.equalsIgnoreCase("service")) {
				mbody5=combinestring(mbody5,"<tr><td>"+tech_name+"</td><td>"+totpmcalls+"</td><td>"+totnl+"</td><td>"+totservfcall+"</td><td>"+totservtcall+"</td><td>"+NumberFormat.getCurrencyInstance().format(totservcallsd)+"</td><td>"+NumberFormat.getCurrencyInstance().format(avgsvccall)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totequipsales)+"</td><td>"+pssold+"</td><td>"+perpssold+"</td><td>"+NumberFormat.getCurrencyInstance().format(totupsales)+"</td><td>"+war+"</td><td>"+cbs+"</td><td>"+cbi+"</td><td>"+cbm+"</td><td>"+totsalescalls+"</td><td>"+ins+"</td><td>"+totruncalls+"</td></tr>");
				}
				if (department.equalsIgnoreCase("maintenance")) {
				mbody5=combinestring(mbody5,"<tr><td>"+tech_name+"</td><td>"+totnl+"</td><td>"+totnlpsold+"</td><td>"+d4+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totnldc)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totnld)+"</td><td>"+totpmrcall+"</td><td>"+totpmrsold+"</td><td>"+d1+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totpmrcalldc)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totpmrcalld)+"</td><td>"+totpmnrcall+"</td><td>"+totpmnrsold+"</td><td>"+d2+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totpmnrcalldc)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totpmnrcalld)+"</td><td>"+slnp+"</td><td>"+totslpgrun+"</td><td>"+totslsold+"</td><td>"+d3+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totsalescallsdc)+"</td></tr>");
				mbody10=combinestring(mbody10,"<tr><td>"+tech_name+"</td><td>"+gtrun+"</td><td>"+gtsold+"</td><td>"+d5+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totcomsold)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totnoncomsold)+"</td></tr>");
				}
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
				double pfsd=0.00;
				double pfsdc=0.00;
				double slpgsd=0.00;
				double slpgsdc=0.00;
				int pfs=0;
				int slpgs=0;
				// NEW CODE 2006-05-16 ADDED
				int countnlrun=0;
				int countpmrrun=0;
				int countpmnrrun=0;
				int countslpgrun=0;
				int mannlsnorun=0;
				double mannlsnorund=0.00;
				double mannlsnorundc=0.00;
				int manslpgnorun=0;
				double manslpgnorund=0.00;
				double manslpgnorundc=0.00;
				int manpmrnorun=0;
				int manpmnrnorun=0;
				double manpmrnorund=0.00;
				double manpmnrnorund=0.00;
				double manpmrnorundc=0.00;
				double manpmnrnorundc=0.00;
				
				
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
					pmr=Integer.parseInt(callcount);
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
				} else if (ctype.equalsIgnoreCase("pfs")) {
					// Proposal Follow-up Sold
					pfsd=Double.parseDouble(tamount);
					pfsdc=Double.parseDouble(tcamount);
					pfs=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("slpgs")) {
					// Sales lead proposal given sold
					slpgsd=Double.parseDouble(tamount);
					slpgsdc=Double.parseDouble(tcamount);
					slpg=Integer.parseInt(callcount);
					// NEW CODE HERE 2006-05-16
				} else if (ctype.equalsIgnoreCase("nlrun")) {
					countnlrun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmrrun")) {
					countpmrrun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnrrun")) {
					countpmnrrun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("slpgrun")) {
					countslpgrun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("nlsnorun")) {
					// Sales lead proposal given sold
					mannlsnorund=Double.parseDouble(tamount);
					mannlsnorundc=Double.parseDouble(tcamount);
					mannlsnorun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("slpgnorun")) {
					// Sales lead proposal given sold
					manslpgnorund=Double.parseDouble(tamount);
					manslpgnorundc=Double.parseDouble(tcamount);
					manslpgnorun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmrnorun")) {
					// Sales lead proposal given sold
					manpmrnorund=Double.parseDouble(tamount);
					manpmrnorundc=Double.parseDouble(tcamount);
					manpmrnorun=Integer.parseInt(callcount);
				} else if (ctype.equalsIgnoreCase("pmnrnorun")) {
					// Sales lead proposal given sold
					manpmnrnorund=Double.parseDouble(tamount);
					manpmnrnorundc=Double.parseDouble(tcamount);
					manpmnrnorun=Integer.parseInt(callcount);
				}
			}
					
				int totsalescalls=pf+slnp+slpg+countslpgrun;
				int totslsold = pfs+slpgs+manslpgnorun;
				
				int totslpgrun=slpg+slpgs+countslpgrun;
				
				double totsalescallsd=pfd+slnpd+slpgd+manslpgnorund;
				double totsalescallsdc=pfdc+slnpdc+slpgdc+manslpgnorundc;
				
				int totservtcall=sc+sct+scte+sctp+sctu+dash;
				int totservfcall=scf+scfe+scfp+scfu;
				int totservcalls=totservtcall+totservfcall;
				
				double totservtcalld=scd+sctd+scted+sctpd+sctud+dashd;
				double totservfcalld=scfd+scfed+scfpd+scfud;
				double totservcallsd=totservtcalld+totservfcalld;
				double totservtcalldc=scdc+sctdc+sctedc+sctpdc+sctudc+dashdc;
				double totservfcalldc=scfdc+scfedc+scfpdc+scfudc;
				double totservcallsdc=totservtcalldc+totservfcalldc;
				
				int totpmnrcall = pmnr+pmnre+pmnru+countpmnrrun;
				int totpmrcall=pmr+pmre+pmrr+pmru+countpmrrun;
				int totpmnrsold = pmnre+pmnru+manpmnrnorun;
				int totpmrsold = pmre+pmrr+pmru+manpmrnorun;


				double totpmnrcalld = pmnrd+pmnred+pmnrud+manpmnrnorund;
				double totpmrcalld=pmrd+pmred+pmrrd+pmrud+manpmrnorund;
			
				double totpmcallsd=totpmrcalld+totpmnrcalld;
				
				double totpmnrcalldc = pmnrdc+pmnredc+pmnrudc+manpmnrnorundc;
				double totpmrcalldc=pmrd+pmredc+pmrrdc+pmrudc+manpmrnorundc;
				double totpmcallsdc=totpmrcalldc+totpmnrcalldc;
				
				double totupsales = pmrud+pmnrud+scfud+sctud+nlud+pmrudc+pmnrudc+scfudc+sctudc+nludc;
				double totequipsales = pmred+pmnred+scfed+scted+nled+pmredc+pmnredc+scfedc+sctedc+nledc+slpgd+slpgdc+pfd+pfdc;
				
				int totnl=nl+nle+nlu+nlp+countnlrun;
				int totnlpsold = mannlsnorun+nlp;
				double totnld=nld+nled+nlud+nlpd+mannlsnorund;
				double totnldc=nldc+nledc+nludc+nlpdc+mannlsnorundc;
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
				
				BigDecimal d4 = new BigDecimal(0.00);				
				double pernlsold= 0.00;
				if (totnl > 0) {
				pernlsold=(Double.parseDouble(""+totnlpsold+"")/Double.parseDouble(""+totnl+""))*100;
				d4 = new BigDecimal(pernlsold);
				d4 = d4.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				
				BigDecimal d1=new BigDecimal(0.00);
				double perpmrrsold=0.00;
				if (totpmrcall > 0) {
				perpmrrsold=(Double.parseDouble(""+totpmrsold+"")/Double.parseDouble(""+totpmrcall+""))*100;
				d1 = new BigDecimal(perpmrrsold);
				d1 = d1.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				
				BigDecimal d2=new BigDecimal(0.00);
				double perpmnrsold=0.00;
				if (totpmnrsold > 0) {
				//perpmnrsold=(totpmnrsold/totpmnrcall)*100;
				perpmnrsold=999;
				perpmnrsold=(Double.parseDouble(""+totpmnrsold+"")/Double.parseDouble(""+totpmnrcall+""))*100;
				d2 = new BigDecimal(perpmnrsold);
				d2 = d2.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				
				//double ppmnrsold=0.00;
				//if (totpmnrsold > 0) {
				//	ppmnrsold=(totpmnrsold/totpmnrcall)*100;
				//}
				
				int gtrun = slnp+totslpgrun+totpmnrcall+totpmrcall+totnl;
				int gtsold = nlp+totpmrsold+totpmnrsold+totslsold;
				BigDecimal d5=new BigDecimal(0.00);
				double pergsold=0.00;
				if (gtrun > 0) {
				pergsold=(Double.parseDouble(""+gtsold+"")/Double.parseDouble(""+gtrun+""))*100;
				d5 = new BigDecimal(pergsold);
				d5 = d5.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				double totcomsold = totsalescallsdc+totpmnrcalldc+totpmrcalldc+totnldc;
				double totnoncomsold = totpmnrcalld+totpmrcalld+totnld;
				
				
				
				BigDecimal d3=new BigDecimal(0.00);
				double persales=0.00;
				if (totsalescalls > 0) {
				persales=(Double.parseDouble(""+totslsold+"")/Double.parseDouble(""+totsalescalls+""))*100;
				d3 = new BigDecimal(persales);
				d3 = d3.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				
				int totruncalls = totpmcalls+totnl+totservfcall+totservtcall+war+cbs+cbm+totsalescalls+ins;
				if (department.equalsIgnoreCase("service")) {
				mbody5=combinestring(mbody5,"<tr BGCOLOR=\"red\"><td>"+sdepartment+"</td><td>"+totpmcalls+"</td><td>"+totnl+"</td><td>"+totservfcall+"</td><td>"+totservtcall+"</td><td>"+NumberFormat.getCurrencyInstance().format(totservcallsd)+"</td><td>"+NumberFormat.getCurrencyInstance().format(avgsvccall)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totequipsales)+"</td><td>"+pssold+"</td><td>"+perpssold+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totupsales)+"</td><td>"+war+"</td><td>"+cbs+"</td><td>"+cbi+"</td><td>"+cbm+"</td><td>"+totsalescalls+"</td><td>"+ins+"</td><td>"+totruncalls+"</td></tr>");
				}
				if (department.equalsIgnoreCase("maintenance")) {
				mbody5=combinestring(mbody5,"<tr BGCOLOR=\"red\"><td>"+sdepartment+"</td><td>"+totnl+"</td><td>"+totnlpsold+"</td><td>"+d4+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totnldc)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totnld)+"</td><td>"+totpmrcall+"</td><td>"+totpmrsold+"</td><td>"+d1+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totpmrcalldc)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totpmrcalld)+"</td><td>"+totpmnrcall+"</td><td>"+totpmnrsold+"</td><td>"+d2+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totpmnrcalldc)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totpmnrcalld)+"</td><td>"+slnp+"</td><td>"+totslpgrun+"</td><td>"+totslsold+"</td><td>"+d3+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totsalescallsdc)+"</td></tr>");
				mbody10=combinestring(mbody10,"<tr BGCOLOR=\"red\"><td>"+sdepartment+"</td><td>"+gtrun+"</td><td>"+gtsold+"</td><td>"+d5+"%</td><td>"+NumberFormat.getCurrencyInstance().format(totcomsold)+"</td><td>"+NumberFormat.getCurrencyInstance().format(totnoncomsold)+"</td></tr>");
				}
				
				
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
		mbody5=combinestring(mbody5,"</table>");
		mbody5=combinestring(mbody5,mbody10);
		mbody5=combinestring(mbody5,"</table></html>");
		out.println(mbody5);
		//out.println(mbody4);	
	emailserver = doGetSmtpServer(username);
	//emailsendaddress=doGetIns_Email(username);
	emailsendaddress=doGetCompanyReportEmail();
	techemailaddress=doGetTech_Email(username);	
      String smtpuser = doGetSmtpUser(username);
      String smtppassword = doGetSmtpPassword(username);
      if (reptype.equalsIgnoreCase("email")) {
       doMailSend(emailserver, emailsendaddress, techemailaddress, "Recap Report for "+timeframe+" Department: "+department+" Start: "+startdate+" End:"+enddate+"" , mbody5, smtpuser, smtppassword);
       //doMailSend(emailserver, emailsendaddress, techemailaddress, "Daily Time Recap - "+enddate+" - By Department" , mbody2, smtpuser, smtppassword);
      }
	return mbody;    
	
	
        }
	
}
