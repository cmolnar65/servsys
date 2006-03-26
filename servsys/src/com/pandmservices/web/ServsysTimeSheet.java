/*
 * ServsysTimeSheet.java
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
public class ServsysTimeSheet extends UniCash
{
    
  
    
	public static void getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, String action,  String menu)
	throws SQLException, TodoException, Exception
        {
            String mbody="";
            String timesheetdate = req.getParameter("listdate");
            
	    
		long lstart, lend, lunch_time, sstart, send, shop_time, day_shoptime, travel_time, day_jobtime, day_travel, paid_time, elapsed, end, jend, disp_time, jstart, job_time, start, dispatch;
		double day_billed, day_collected, day_commission;
		String day_end_time, day_start_time, work_time;
		int jobtime = 0;
		int tottime = 0;
		String tech_init = doGetTechInfo_init(username);
		String tech_name = doGetTechInfo_name(username);
		String tech_truck = doGetTechInfo_truck(username);
                tech_init = doGetTechInfo_init(username);
		tech_name = doGetTechInfo_name(username);
                tech_truck = doGetTechInfo_truck(username);
		
                if (action.equalsIgnoreCase("printtimesheet")){
                doStyleSheet(req, res, out, session, username);
		out.println("<html><head><title>Tracking Log</title></head><basefont face=\"arial, verdana\" size=\"2\" color=\"#0000FF\">");
		doMHeader(req, res, out, session, username);
		out.println("<h4 align=CENTER>Daily Tracking Log</h4>");
		out.println("<BODY TEXT=#000000 LINK=#0000ff VLINK=#000080 BGCOLOR=#ffffff><p align=\"center\"> ");
		out.println("<table width=\"95%\" align=\"center\"><font face=\"arial, verdana\" size=\"2\" color=\"#0000FF\"><tr><td><h3>Date:"+ timesheetdate+"</h3></td><td><h3>Service Tech:"+ tech_name+"</h3></td></tr></table>");
                } else {
                
                mbody = combinestring(mbody,"Date:"+ timesheetdate+"<br>Service Tech:"+ tech_name+"<br><br>");
			}
		mbody = combinestring(mbody,"Software Version:"+ doVersionInfo_VNumber()+"<br><br>");
                Vector v;
                v = UniTimeSheet.getAllItems(con,doFormatDateDb(getDateDb(timesheetdate)), username);
		int counter=0;
		job_time=0;
		jstart=0;
		jend=0;
		disp_time=0;
		travel_time=0;
		day_collected=0;
		day_commission=0;
		day_billed=0;
		day_travel=0;
		day_shoptime=0;
		lunch_time=0;
		day_jobtime=0;
		day_start_time=null;
		day_end_time=null;
		int counter1=0;
                mbody = combinestring(mbody,"<table border=1><th>Callslip</th><th>Customer</th><th>Dispatched</th><th>Time In</th><th>Time Out</th><th>Job Time</th><th>Travel Time</th><th>NC Item Sold</th><th>NC Amount</th><th>Com Item Sold</th><th>Com Amount</th><th>Amount Collected</th><th>Paid By</th><th>Commision</th><th>Code</th>");
	if (v.size()!=0) {
                for (int i = 0 ; i < v.size(); i++)
                {
                       	UniTimeSheet t = (UniTimeSheet) v.elementAt(i);
			int tsid  = t.getId();
                        String  callslip= t.CallSlip();
			String customer = t.Customer();
			String time_in = t.TimeIn();
			String time_out = t.TimeOut();
			String dispatch_time = t.DispatchTime();
			String item_sold = t.ItemSold();
			String amount = t.Amount();
			String citem_sold = t.CItemSold();
			String camount = t.CAmount();
			String amount_collected = t.AmountCollected();
			String commision = t.Commision();
			String tdate = t.TDate();
			String ctype = t.CType();
			String paytype = t.TPayType();
			counter++;
			
			double iamount = Double.parseDouble(amount);
			double ciamount= Double.parseDouble(camount);
			double iamount_collected = Double.parseDouble(amount_collected);
			double icommission = Double.parseDouble(commision);
			dispatch = strtotime(dispatch_time);
			jstart = strtotime(time_in);
			jend = strtotime(time_out);
			job_time = jend - jstart;
			

		 if (counter1==0) 
			{
			day_start_time=dispatch_time;
			day_travel=0;
			day_jobtime=0;
			day_shoptime=0;
			day_billed=0;
			day_commission=0;
			day_collected=0;
			} 
		if (customer.equalsIgnoreCase("Lunch")||callslip.equalsIgnoreCase("LUNCH")) {
			lstart=strtotime(time_in);
			lend=strtotime(time_out);
			lunch_time=lend-lstart;
			}
		if (callslip.equalsIgnoreCase("Shop")&&customer.equalsIgnoreCase("Shop")) {
			sstart=strtotime(time_in);
			send=strtotime(time_out);
			shop_time=send-sstart;
			day_shoptime=day_shoptime+shop_time;
			}
			day_jobtime=day_jobtime+job_time;
			travel_time=jstart-dispatch;
			day_travel=day_travel+travel_time;
			day_billed=day_billed+iamount+ciamount;
			day_commission=day_commission+icommission;
			day_collected=day_collected+iamount_collected;

                mbody = combinestring(mbody,"<tr><td>"+callslip+"</td><td>"+customer+"</td><td>"+formatATimeString(dispatch)+"</td><td>"+formatATimeString(jstart)+"</td><td>"+formatATimeString(jend)+"</td><td>"+formatTimeString(job_time)+"</td><td>"+formatTimeString(travel_time)+"</td><td>"+item_sold+"</td><td>"+amount+"</td><td>"+citem_sold+"</td><td>"+camount+"</td><td>"+amount_collected+"</td><td>"+paytype+"</td><td>"+commision+"</td><td>"+ctype+"</td><tr>");
		counter1++;
		day_end_time=time_out;
		}
	
		mbody=combinestring(mbody,"</table>");
start = strtotime(day_start_time);
end = strtotime(day_end_time);
elapsed = end - start;
	mbody = combinestring(mbody,"<br><br>SUMMARY SECTION:<br>");
        work_time=formatTimeString(elapsed);
	mbody = combinestring(mbody,"<table><tr><td>Hours Worked:</td><td>"+work_time+"</td></tr>");
	mbody = combinestring(mbody,"<tr><td>Lunch:</td><td>"+formatTimeString(lunch_time)+"</td></tr>");
	paid_time=elapsed-lunch_time;
	mbody = combinestring(mbody,"<tr><td>Paid Time:</td><td>"+formatTimeString(paid_time)+"</td></tr>");
	mbody = combinestring(mbody,"<tr><td>Job Time:</td><td>"+formatTimeString(day_jobtime-lunch_time-day_shoptime)+"</td></tr>");
	mbody = combinestring(mbody,"<tr><td>Travel Time:</td><td>"+formatTimeString(day_travel)+"</td></tr>");
	mbody = combinestring(mbody,"<tr><td>Unassigned Time:</td><td>"+formatTimeString(elapsed-lunch_time-day_jobtime-day_travel)+"</td></tr><tr><td>Billed:</td><td>"+day_billed+"</td></tr><tr><td>Collected:</td><td>"+day_collected+"</td></tr></tr><tr><td>Commission:</td><td>"+day_commission+"</td></tr></table><br><br>");        

        if (action.equalsIgnoreCase("printtimesheet")){
            out.println("<br><br>");
            mbody=combinestring(mbody,"<table align=\"center\"><tr>");
            mbody=combinestring(mbody,"<td>Signature:</td><td>______________________________________</td></tr></table>");
        }
        
	mbody= combinestring(mbody,"<h4>Summary Section</h4>");
	mbody= combinestring(mbody,"<table border=1 width=\"50%\" align=\"left\">");
	mbody= combinestring(mbody,"<th>Call Type</th><th>Count</th><th>Total Collected</th><th>Non-Commision<br>Billed</th><th>Commision<br>Billed</th><th>Commision</th><th>Time</th><th>Time Without Travel</th>");
                v = TimeSheetSummary.getAllItems(con,doFormatDateDb(getDateDb(timesheetdate)));
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
	mbody= combinestring(mbody,"<tr><td>"+ctype+"</td><td>"+callcount+"</td><td>"+tamount_collected+"</td><td>"+tamount+"</td><td>"+tcamount+"</td><td>"+tcommision+"</td><td>"+timewithtravel+"</td><td>"+timenotravel+"</td></tr>");
		}
	mbody= combinestring(mbody,"</table>");
	mbody= combinestring(mbody,"</font></html>");

        }
if (mbody.length()>1) {	
    if (!action.equalsIgnoreCase("printtimesheet")) {               
 emailsendaddress=doGetTime_Email(username);
	techemailaddress=doGetTech_Email(username);	
	emailserver = doGetSmtpServer(username);
      String smtpuser = doGetSmtpUser(username);
      String smtppassword = doGetSmtpPassword(username);
        doMailSend(emailserver, emailsendaddress, techemailaddress, "Time Sheet - "+timesheetdate+" - "+ tech_name , mbody, smtpuser, smtppassword);
        out.println(emailserver+"<br>"+smtpuser+"<br>"+smtppassword+"<br>"+emailsendaddress+"<br>"+ techemailaddress+"<br>Time Sheet - "+timesheetdate+" - "+ tech_name+"<br><br>"+mbody);
    } else {
     out.println(mbody);   
    }
    } else
	{
		out.println("No Timesheet data found - did you enter any?</html>");
	}
		con.close();
                
        }
}
