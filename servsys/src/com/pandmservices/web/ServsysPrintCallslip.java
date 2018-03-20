/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author cmolnar
 */
public class ServsysPrintCallslip extends UniCash{
    
    
    public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir)
	throws SQLException, TodoException, Exception
        {

            String mbody="";
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
String tcustnum = req.getParameter("custnum");
String action = req.getParameter("action");
int custnum = Integer.parseInt(tcustnum);
String tcrecnum = req.getParameter("crecnum");
int crecnum = Integer.parseInt(tcrecnum);
String nate_id=doGetTechInfo_nateid(username);
int nateid=Integer.parseInt(nate_id);
 int eenum=0;
int ecustnum=0;
String brand=null;
String modelnum=null;
String serialnum=null;
String filter=null;
String notes=null;
String callslip=null;
String cdate=null;
int equip1=0;
int equip2=0;
int equip3=0;
int equip4=0;
int frcode=0;
String reason=null;
String services=null;
String recommendations=null;
String rscheduled=null;
String charges=null;
String collected=null;
int followup=0;
String descript;
String ccallslip;
double quant;
double price;
double total;
double totalcharge=0.00;
int recnum;
String cname=null;
String address1=null;
String address2=null;
String city =null;
String state=null;
String zip=null;
String homephone=null;
String altphone=null;
String cust_notes=null;
String etype="";
String techid="";
String custsite=null;
String cemail=null;
String sitenum=null;
String parts=null;
	
String tech_init = doGetTechInfo_init(username);
String tech_name = doGetTechInfo_name(username);
String tech_truck = doGetTechInfo_truck(username);

            mbody=combinestring(mbody,"<html>");

		if (!action.equalsIgnoreCase("viewpcallslip")) 
				{
                        mbody=doStyleSheetString(req, res, out, session, username,mbody);
                        mbody=combinestring(mbody,"<title>Service Invoice</title></head><body>");
                        mbody=doMHeaderString(req, res, out, session, username, mbody);
				}
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT *  FROM callslip where crecnum='"+crecnum+"' ORDER BY cdate;");
	 while(rs.next())
	{
	crecnum=rs.getInt("crecnum");
	callslip=rs.getString("callslip");
	custsite=rs.getString("custsite");
	sitenum=rs.getString("sitenum");
	techid=rs.getString("techid");
	cdate=rs.getString("cdate");
	equip1=rs.getInt("equip1");
	equip2=rs.getInt("equip2");
	equip3=rs.getInt("equip3");
	equip4=rs.getInt("equip4");
	reason=rs.getString("reason");
	services=rs.getString("services");
	recommendations=rs.getString("recommendations");
	rscheduled=rs.getString("rscheduled");
	charges=rs.getString("charges");
	collected=rs.getString("collected");
//	notes=rs.getString("notes");
						notes="";		
		Vector vcn;
		vcn = CallslipNotes.getAllItems(con,custsite, sitenum, callslip);
		if (vcn.size() > 0 ) {
		notes=combinestring(notes,"<table border=1 width=100%>");
		notes=combinestring(notes,"<th>Date</th><th>User</th><th>Note</th>");
		String note="";
		String ndate="";
		String ncallslip="";
		String nuserlogin="";
		String nnote="";
		int nrecnum=0;
		int nservsync=0;
                for (int icn = 0 ; icn < vcn.size(); icn++)
                {
                CallslipNotes tcn = (CallslipNotes) vcn.elementAt(icn);
		nnote=tcn.getNote();
		ncallslip=tcn.getCallslip();
		nuserlogin=tcn.getUserLogin();
		nrecnum=tcn.getRecNum();
		ndate=tcn.getNDate();
		nservsync=tcn.getServsync();
                notes=combinestring(notes,"<tr><td>"+ndate+"</td><td>"+nuserlogin+"</td><td>"+nnote+"</td></tr>");
		}
			notes=combinestring(notes,"</table>");
		}
	parts = rs.getString("parts");
	followup=rs.getInt("followup");
}
mbody=combinestring(mbody,"<br>");
mbody=combinestring(mbody,"<table border=0 width=\"95%\" align=\"center\"><td width=\"30%\">");
mbody=combinestring(mbody,"<b align=Left>Service Invoice:</b><br>&nbsp;&nbsp;&nbsp;&nbsp;Call Slip: "+callslip+"<br>&nbsp;&nbsp;&nbsp;&nbsp;Call Date: "+doFormatDate(getDate(cdate))+"<br><br>");
mbody=combinestring(mbody,"</td>");


                Vector vc;
                vc = UniCustomer.getIndItem(con, custnum);
                int counter=0;
                String custtype = null;
                for (int ic = 0 ; ic < vc.size(); ic++)
                {
                        UniCustomer tc = (UniCustomer) vc.elementAt(ic);
                        custtype = tc.getCustType();
			cname=tc.getCustomerName();
			address1=tc.getAddress1();
			address2=tc.getAddress2();
			city =tc.getCity();
			state=tc.getState();
			zip=tc.getZip();
			homephone=tc.getHomePhone();
			altphone=tc.getAltPhone();
			cust_notes=tc.getCustomerNotes();
			custsite=tc.getCustSite();
			sitenum=tc.getSiteNum();
			cemail=tc.getCEmail();
		}	

mbody=combinestring(mbody,"<td width=\"30%\">");
mbody=combinestring(mbody,"<b>Customer:</b><br>&nbsp;&nbsp;&nbsp;&nbsp;"+cname+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+address1+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+city+","+state+"<br>&nbsp;&nbsp;&nbsp;"+cemail+"");
mbody=combinestring(mbody,"</td>");

mbody=combinestring(mbody,"<td width=\"30%\">");
mbody=combinestring(mbody,"Customer Number / Site:&nbsp;&nbsp;&nbsp;"+custsite+"&nbsp;/&nbsp;"+sitenum+"<br>Home Phone:&nbsp;&nbsp;&nbsp;"+homephone+"<br>Alt. Phone:&nbsp;&nbsp;&nbsp;"+altphone+"");
mbody=combinestring(mbody,"</td>");

mbody=combinestring(mbody,"</table>");

mbody=combinestring(mbody,"<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Parts Used</h4>");
mbody=combinestring(mbody,"<table width=\"95%\" align=\"center\" border=1>");
mbody=combinestring(mbody,"<font size=1>");
mbody=combinestring(mbody,"<th>Key</th><th>Part</th><th>Quantity</th><th>Date</th><th>Location</th>");
Vector ci;
ci = InvUse.getAllCallslipItems(con, callslip, cdate);
for (int cc = 0 ; cc < ci.size(); cc++)
{
	InvUse ti = (InvUse) ci.elementAt(cc);
	String keycode = ti.getKeyCode();
	String itemname = ti.getItemName();
	String pquant = ti.getQuantity();
	String invloc = ti.getInvLoc();
	String idate = doFormatDate(getDate(ti.getTDate()));
	mbody=combinestring(mbody,"<tr><td>"+keycode+"</td><td>"+itemname+"</td><td>"+pquant+"</td><td>"+idate+"</td><td>"+invloc+"</td></tr>");
	}

	mbody=combinestring(mbody,"</font>");
	mbody=combinestring(mbody,"</table>");

	mbody=combinestring(mbody,"<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Equipment</h4>");
	mbody=combinestring(mbody,"<table width=\"95%\" align=\"center\" border=1>");
	mbody=combinestring(mbody,"<font size=1>");
stmt = con.createStatement();
rs = stmt.executeQuery("SELECT * FROM  equipment where enum='"+equip1+"' or enum='"+equip2+"' or enum='"+equip3+"' or enum='"+equip4+"';");
mbody=combinestring(mbody,"<th>Type</th><th>Brand</th><th>Model</th><th>Serial</th><th>Filter</th><th>Notes</th>");
	 while(rs.next())
	{
	brand = rs.getString("brand");
	modelnum = rs.getString("modelnum");
	serialnum = rs.getString("serialnum");
	filter = rs.getString("filter");
	notes = rs.getString("notes");
// Add type here
	etype = rs.getString("etype");
if (etype==null) { 
	etype = "-";
	}

mbody=combinestring(mbody,"<tr><td>"+etype+"</td><td>"+brand+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+filter+"</td><td>"+notes+"</tr>");
	}
	mbody=combinestring(mbody,"</font>");
	mbody=combinestring(mbody,"</table>");

/////////////////////////////////////
//Completion Codes Here
////////////////////////////////////

Vector r = UniSvcCompl.getAllItems(con,callslip);
if ((r.size()>0) )
		{
	mbody=combinestring(mbody,"<br><table border=0 width=95% align=\"center\"><font size=1><tr><td><h4>Resolution</h4></td></tr>");
        mbody=combinestring(mbody,"<tr><td><table border=1 width=100% align=\"center\"><font size=1>");
        mbody=combinestring(mbody,"<th>Code</th><th>Resolution</th>");
		
                for (int i = 0 ; i < r.size(); i++)
                {
                UniSvcCompl t = (UniSvcCompl) r.elementAt(i);
		int corecnum=t.getRecnum();
		int codenum=t.getCodeNum();
		String complcode=t.getComplCode();
		String compltext=t.getComplText();
		mbody=combinestring(mbody,"<tr><td>"+complcode+"</td><td>"+compltext+"</td><tr>");
		}
	mbody=combinestring(mbody,"</table></td></tr></table>");
		}

		
		mbody=combinestring(mbody,"<font size=1>");
		mbody=combinestring(mbody,"<table width=\"95%\" size=\"95%\" align=\"center\" border=1 height=5>");
		mbody=combinestring(mbody,"<font size=1");
if ((services!=null)||(recommendations!=null)||(rscheduled!=null)||(notes!=null)) {
		mbody=combinestring(mbody,"<br>");
		mbody=combinestring(mbody,"<table width=\"95%\" size=\"95%\" align=\"center\" border=1>");
		mbody=combinestring(mbody,"<font size=1>");
		if (services.length()>1) {
		mbody=combinestring(mbody,"<tr><td><h4>Services</h4></td></tr><tr><td>"+services+"");
		mbody=combinestring(mbody,"</td></tr>");
		}

		if (recommendations.length()>1) {
		mbody=combinestring(mbody,"<P></P><br>");
		mbody=combinestring(mbody,"<tr><td><h4>Our Trained Technician Recommends</h4></td></tr><tr><td>"+recommendations+"");
		mbody=combinestring(mbody,"<table><font size=1><tr><td>Customer Accepts Recomendations</td><td>______________</td><td>Customer Declines Recommendations</td><td>________________</td></tr></font></table></tr>");
		mbody=combinestring(mbody,"</td></tr>");
		}
	
		if (rscheduled.length()>1) {
		mbody=combinestring(mbody,"<tr><td><h4>Repair Scheduled</h4></td></tr><tr><td>"+rscheduled+"");
		mbody=combinestring(mbody,"</td></tr>");
		}
		if (parts==null) {
			parts="-";
		}
		if (parts.length()>1) {
		mbody=combinestring(mbody,"<tr><td><h4>Parts Needed</h4></td></tr><tr><td>"+parts+"");
		mbody=combinestring(mbody,"</td></tr>");
			}
		if ((notes.length()>1)&&action.equalsIgnoreCase("viewpcallslip")) {
		mbody=combinestring(mbody,"<tr><td><h4>THESE NOTES ARE NOT GIVEN TO CUSTOMER!!!</h4></td></tr><tr><td>"+notes+"");
		mbody=combinestring(mbody,"</td></tr>");
			}
	mbody=combinestring(mbody,"</font>");
	mbody=combinestring(mbody,"</table>");
	mbody=combinestring(mbody,"");
	}
        Vector v;
        v = UniSvcCharges.getAllItems(con,callslip);
	if ((v.size()>0)) {
	mbody=combinestring(mbody,"<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Charges</h4>");
        mbody=combinestring(mbody,"<table border=1 width=95% align=\"center\">");
		out.println("<font size=1>");
        mbody=combinestring(mbody,"<th>Code</th><th>Quantity</th><th>Description</th><th>Price</th><th>Sub Total</th>");
		
                for (int i = 0 ; i < v.size(); i++)
                {
                UniSvcCharges t = (UniSvcCharges) v.elementAt(i);
		recnum=t.getRecnum();
		callslip=t.getCallslip();
		quant=t.getQuant();
		descript=t.getDescript();
		price=t.getPrice();
		frcode = t.getFrcode();
		total=t.getTotal();
		totalcharge=totalcharge+total;
                mbody=combinestring(mbody,"<tr><td>"+frcode+"</td><td>"+quant+"</td><td>"+descript+"</td><td>"+NumberFormat.getCurrencyInstance().format(price)+"</td><td>"+NumberFormat.getCurrencyInstance().format(total)+"</td></tr>");
		}
	mbody=combinestring(mbody,"</font>");
        mbody=combinestring(mbody,"</table>");
		}
	mbody=combinestring(mbody,"</font>");
	mbody=combinestring(mbody,"<table width=\"95%\" align=\"center\">");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<tr>");
	String itech_name = doGetTechInfo_name(techid);
	mbody=combinestring(mbody,"<P></p><br><br>");
	if (totalcharge>0) {
		mbody=combinestring(mbody,"<td>____________________________________</td><td>"+itech_name+"</td><td>Amount Due</td><td><b>"+NumberFormat.getCurrencyInstance().format(totalcharge)+"</b></td>");

	} else {
		mbody=combinestring(mbody,"<td>____________________________________</td><td>"+itech_name+"</td><td>Amount Due</td><td><b>_________________</b></td>");
	}
mbody=combinestring(mbody,"</tr><tr><td><h5>Customer Signature</h5></td><td><h5>Service Tech</h5></td><td>Amount Paid</td><td>_________________</td></tr></font></table>");

// Put the discount information in here...

		if (!action.equalsIgnoreCase("viewpcallslip")) 
				{
	out.println("<table width=\"95%\" align=\"center\">");
	out.println("<font size=1>");
                        if (nateid==0) {
                        if (custtype.equalsIgnoreCase("T")) {

		mbody=combinestring(mbody,"<tr><td></td><td><b>Please ask our technician how you could save money on this call with a Planned Service Agreement!</b></td></tr>");
                        }
			else if (custtype.equalsIgnoreCase("F")) {

		mbody=combinestring(mbody,"<tr><td></td><td><b>Thank you for calling "+doGetCompanyName()+"</b> </td></tr>");
			}
                        else {
		mbody=combinestring(mbody,"<tr><td></td><td><b>Thank you for calling "+doGetCompanyName()+"</b> </td></tr>");
			}
			//NO NATE ID - DO NOT PRINT NATE INFO
			} else {
	mbody=combinestring(mbody,"<tr><td> ");
        // NEED TO FIX THIS AND MAKE THIS CONFIGURABLE
	mbody=combinestring(mbody,"<IMG SRC=\"http://192.168.2.225:8080/servsys/natelogo.gif\" NAME=\"NateLogo\" ALIGN=LEFT WIDTH=60 HEIGHT=60 BORDER=0><BR CLEAR=LEFT>");
	mbody=combinestring(mbody,"</td>");
                        if (custtype.equalsIgnoreCase("T")) {

		mbody=combinestring(mbody,"<td>Your Service Technician is NATE Certified - ID: "+nate_id+"<br><b>Please ask our technician how you could save money on this call with a Planned Service Agreement!</b></td></tr>");
                        }
			else if (custtype.equalsIgnoreCase("F")) {

		mbody=combinestring(mbody,"<td>Your Service Technician is NATE Certified - ID: "+nate_id+"<br><b>Thank you for calling "+doGetCompanyName()+"</td></tr>");
			}
                        else {
		mbody=combinestring(mbody,"<td>Your Service Technician is NATE Certified - ID: "+nate_id+"<br><b>Thank you for calling "+doGetCompanyName()+"</b> </td></tr>");
			}
			}
			}
        mbody=combinestring(mbody,"</font></table>");

        mbody=combinestring(mbody,"</body>");
        mbody=combinestring(mbody,"</html>");
	
		con.close();
		if (action.equalsIgnoreCase("viewpcallslip")) 
				{
                mbody=combinestring(mbody,"<br><br><a href="+classdir+"UniCash?action=showcustdetail&csection=2&custnum="+custnum+">Click here to continue</a>");
				}
            out.println(mbody);
		//con.close();
            return mbody;
        }
    
    
}
