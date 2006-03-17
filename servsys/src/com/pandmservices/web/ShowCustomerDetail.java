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

public class ShowCustomerDetail
{
	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, String tcustnum, String custstart, String custstop, String action, String csection)
	throws SQLException, TodoException, Exception
{
	/*String tcustnum = req.getParameter("custnum");
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String action = req.getParameter("action");
	String csection = req.getParameter("csection");
	*/
	int AllowDelete= Integer.parseInt(doGetAllowDelete(username, con));
	String qstatus="-";
	int custnum=0;
	String cname=null;
	String address1=null;
	String address2=null;
	String city =null;
	String state=null;
	String zip=null;
	String homephone=null;
	String altphone=null;
	String cust_notes=null;
	int eenum=0;
	int ecustnum=0;
	String brand=null;
	String etype=null;
	String modelnum=null;
	String serialnum=null;
	String filter=null;
	String notes=null;
	int counter=0;
	int icrecnum=0;
	int ccrecnum=0;
	String icallslip=null;
	double totinvestment=0.00;
	String icustnum=null;
	String idate=null;
	String ccallslip=null;
	String ccustnum=null;
	String cdate=null;
	String tcdate=null;
	String creason=null;
	int pcontnum=0;
	String pcustnum=null;
	String pstartdate=null;
	String penddate=null;
	String pcost=null;
	String pnotes=null;
	int propnum=0;
	String pdate=null;
	int qcustnum=0;
	String psummary=null;
	String wssummary=null;
	String cemail=null;
	int wsrec=0;
	String custsite=null;
	String custtype=null;
	String sitenum=null;
	String wsdate=null;
	int wscustnum=0;
	int wsnum=0;
	double ptotal=0;
	int intcustnum = Integer.parseInt(tcustnum);
	custnum=Integer.parseInt(tcustnum);
	
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Customer Detail</title>");
	UniCash.doStyleSheet(req, res, out, session, username);

	out.println("</head>");
	out.println("<BODY>");
	
	
	out.println("<table border=0 width=100%>");
	out.println("<tr><td width=\"60%\">");
	out.println("<h3>Customer Detail</h3>");
	out.println("<table border=1 width=\"100%\">");
	
	Vector vc;
	vc = UniCustomer.getIndItem(con, intcustnum);
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
		
		out.println("<tr><td>Name</td><td>"+cname+"</td></tr>");
		out.println("<tr><td>Address</td><td>"+address1+"</td></tr>");
		out.println("<tr><td>Address</td><td>"+address2+"</td></tr>");
		out.println("<tr><td>City</td><td>"+city+"</td></tr>");
		out.println("<tr><td>State</td><td>"+state+"</td></tr>");
		out.println("<tr><td>Zip</td><td>"+zip+"</td></tr>");
		out.println("<tr><td>Home Phone</td><td>"+homephone+"</td></tr>");
		out.println("<tr><td>Alt Phone</td><td>"+altphone+"</td></tr>");
		out.println("<tr><td>Email Address</td><td>"+cemail+"</td></tr>");
		out.println("<tr><td>Customer Number</td><td>"+custsite+"</td></tr>");
		out.println("<tr><td>Site Number</td><td>"+sitenum+"</td></tr>");
		out.println("<tr><td>CustType</td><td>"+custtype+"</td></tr>");
		out.println("<tr><td>Customer Notes</td><td>"+cust_notes+"</td></tr>");
		out.println("</table>");
	}
	out.println("</td>");
	out.println("<td valign=top>");
	out.println("<table border=0 width=\"95%\" valign=top class=\"tableoffcolor\" id=\"custforms\">");
	out.println("<tr><td><h3>Customer Forms</h3></td></tr>");
	if (action.equalsIgnoreCase("showcustdetail_ide"))
	{
		out.println("<tr><td><a href="+classdir+"UniCash?action=printspaceheater&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Print Space Heater Liability Form</a><br></td></tr>");
		out.println("<tr><td><a href="+classdir+"UniCash?action=printaccover&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Print AC Cover Disclaimer Form</a><br></td></tr>");
		out.println("<tr><td><a href="+classdir+"UniCash?action=printiaqdisc&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Print IAQ Disclaimer Form</a><br></td></tr>");
		out.println("<tr><td><a href="+classdir+"UniCash?action=printelist&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Print Equipment List</a><br></td></tr>");
	} else {
		out.println("<tr><td><a href="+classdir+"UniCash?action=printspaceheater&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=_blank>Print Space Heater Liability Form</a><br></td></tr>");
		out.println("<tr><td><a href="+classdir+"UniCash?action=printaccover&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=_blank>Print AC Cover Disclaimer Form</a><br></td></tr>");
		out.println("<tr><td><a href="+classdir+"UniCash?action=printiaqdisc&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=_blank>Print IAQ Disclaimer Form</a><br></td></tr>");
		out.println("<tr><td><a href="+classdir+"UniCash?action=printelist&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=_blank>Print Equipment List</a><br></td></tr>");
				}
	out.println("<tr><td></td></tr>");
	out.println("</table>");
	
	out.println("<table border=0 width=\"95%\" valign=top class=\"tableoffcolor2\" id=\"custforms\">");
	out.println("<tr><td><h3>Actions</h3></td></tr>");
	out.println("<tr><td><a href="+classdir+"UniCash?action=sendarrive&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=phpmain>Send Arrived</a><br></td><td><a href="+classdir+"UniCash?action=getservequip&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+"&custsitenum="+custsite+"&sitenum="+sitenum+" target=phpmain>Get Equipment from Server</a><br></td></tr>");
	out.println("<tr><td><a href="+classdir+"UniCash?action=sendextratime&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=phpmain>Send Need Extra Time</a><br></td><td><a href="+classdir+"UniCash?action=getservcallslip&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+"&custsitenum="+custsite+"&sitenum="+sitenum+" target=phpmain>Get Calls from Server</a><br></td></tr>");
	out.println("<tr><td><a href="+classdir+"UniCash?action=sendalmostdone&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=phpmain>Send Doing Paperwork</a><br></td><td><a href="+classdir+"UniCash?action=getservinspection&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+"&custsitenum="+custsite+"&sitenum="+sitenum+" target=phpmain>Get Inspections from Server</a><br></td></tr>");
	out.println("<tr><td><a href="+classdir+"UniCash?action=sendcomplete&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=phpmain>Send Complete</a><br></td></tr>");
	out.println("<tr><td> </td></tr>");
	out.println("<tr><td>MUST HAVE INTERNET<br>CONNECT FOR THESE</td></tr>");
	out.println("</table>");
	
	
	out.println("</td></tr>");
	out.println("</table>");
	
	out.println("<br>");
	out.println("<a href="+classdir+"UniCash?action=editcustomer&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+"&reqsource=UniCash>Edit Customer Info</a><br>");
	String taddress1 = address1.replaceAll(" ","+");
	String tcity = city.replaceAll(" ","+");
	out.println("<a href=http://www.mapquest.com/maps/map.adp?searchtype=address&country=US&addtohistory=&searchtab=home&formtype=address&popflag=0&latitude=&longitude=&name=&phone=&cat=&address="+taddress1+"&city="+tcity+"&state="+state+"&zipcode="+zip+" target=_blank>Show on Mapquest (must have internet connect)</a>");
	out.println("<P><P>");
	
	if (csection.equalsIgnoreCase("1")) {	
	out.println("<table border=1 width=100%>");
	out.println("<th bgcolor=\"red\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=1>Equipment</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=2>CallSlips</a></th> <th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=3>Inspections</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=4>Preventative</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=5>Service<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=6>Sales<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=7>Other</a></th>");
	out.println("</table><br>");
		} else
	if (csection.equalsIgnoreCase("2")) {	
	out.println("<table border=1 width=100%>");
	out.println("<th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=1>Equipment</a></th><th bgcolor=\"red\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=2>CallSlips</a></th> <th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=3>Inspections</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=4>Preventative</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=5>Service<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=6>Sales<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=7>Other</a></th>");
	out.println("</table><br>");
		} else
	if (csection.equalsIgnoreCase("3")) {	
	out.println("<table border=1 width=100%>");
	out.println("<th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=1>Equipment</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=2>CallSlips</a></th> <th bgcolor=\"red\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=3>Inspections</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=4>Preventative</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=5>Service<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=6>Sales<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=7>Other</a></th>");
	out.println("</table><br>");
		} else
	if (csection.equalsIgnoreCase("4")) {	
	out.println("<table border=1 width=100%>");
	out.println("<th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=1>Equipment</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=2>CallSlips</a></th> <th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=3>Inspections</a></th><th bgcolor=\"red\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=4>Preventative</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=5>Service<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=6>Sales<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=7>Other</a></th>");
	out.println("</table><br>");
		} else
	if (csection.equalsIgnoreCase("5")) {	
	out.println("<table border=1 width=100%>");
	out.println("<th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=1>Equipment</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=2>CallSlips</a></th> <th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=3>Inspections</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=4>Preventative</a></th><th bgcolor=\"red\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=5>Service<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=6>Sales<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=7>Other</a></th>");
	out.println("</table><br>");
		} else
	if (csection.equalsIgnoreCase("6")) {	
	out.println("<table border=1 width=100%>");
	out.println("<th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=1>Equipment</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=2>CallSlips</a></th> <th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=3>Inspections</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=4>Preventative</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=5>Service<br>Proposals</a></th><th bgcolor=\"red\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=6>Sales<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=7>Other</a></th>");
	out.println("</table><br>");
		} else 
	if (csection.equalsIgnoreCase("7")) {	
	out.println("<table border=1 width=100%>");
	out.println("<th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=1>Equipment</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=2>CallSlips</a></th> <th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=3>Inspections</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=4>Preventative</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=5>Service<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=6>Sales<br>Proposals</a></th><th bgcolor=\"red\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=7>Other</a></th>");
	out.println("</table><br>");
		}
	else {	
	out.println("<table border=1 width=100%>");
	out.println("<th bgcolor=\"red\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=1>Equipment</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=2>CallSlips</a></th> <th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=3>Inspections</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=4>Preventative</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=5>Service<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=6>Sales<br>Proposals</a></th><th bgcolor=\"lightblue\"><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+"&csection=7>Other</a></th>");
	out.println("</table><br>");
		} 
	
	
	
	if (csection.equalsIgnoreCase("1")) {	
	out.println("<h3>Customer Equipment List</h3>");
	
	Vector v;
	v = UniEquip.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
	out.println("<th>Brand</th><th>Type</th><th>Model</th><th>Serial</th><th>Filter</th><th>Notes</th><th>Delete<br>(no second chance)</th>");
	
	for (int i = 0 ; i < v.size(); i++)
	{
		UniEquip t = (UniEquip) v.elementAt(i);
		eenum=t.getId();
		ecustnum=t.getCustnum();
		brand=t.getBrand();
		modelnum=t.getModelnum();
		serialnum=t.getSerialnum();
		filter=t.getFilter();
		notes=t.getNotes();
		etype=t.getEtype();
		if (etype==null) { 
			etype = "-";
		}
		
		out.println("<tr><td><a href="+classdir+"UniCash?action=editequipment&eenum="+eenum+"&custnum="+ecustnum+">"+brand+"</a></td><td>"+etype+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+filter+"</td><td>"+notes+"</td><td><a href="+classdir+"UniCash?action=delequip&eenum="+eenum+"&custnum="+ecustnum+">D</a>elete</td></tr>");
	}
	out.println("</table><br><br>");
	
	out.println("<a href="+classdir+"UniCash?action=addequipment&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Equipment to Customer File</a>");
	out.println("<P><P>");
	} else if (csection.equalsIgnoreCase("3")) {
	out.println("<h3>Inspection Record</h3>");
 	Statement stmt3 = con.createStatement();
	ResultSet rs3 = stmt3.executeQuery("SELECT * FROM inspection where custnum="+tcustnum+"");
	out.println("<table border=1 width=50%>");
	out.println("<th>Callslip</th><th>Date</th><th>Tech</th><th>Print</th><th>Email</th>");
	while(rs3.next())
	{
		icrecnum=rs3.getInt("crecnum");
		icallslip=rs3.getString("callslip");
		icustnum=rs3.getString("custnum");
		String techname=rs3.getString("techid");
		idate=doFormatDate(getDate(rs3.getString("idate")));
		if (techname==null) { techname="-"; }
		if (techname.equalsIgnoreCase(username)) {
			out.println("<tr><td><a href="+classdir+"UniCash?action=editinspection&icrecnum="+icrecnum+"&custnum="+icustnum+"&custstart="+custstart+"&custstop="+custstop+">"+icallslip+"</a></td><td>"+idate+"</td><td>"+techname+"</td><td><a href="+classdir+"UniCash?action=inspectprint&crecnum="+icrecnum+"&custnum="+icustnum+" target=\"_blank\">Print Format</a></td><td><a href="+classdir+"UniCash?action=sendsingleinspections&csrec="+icrecnum+"&custnum="+icustnum+" target=\"_blank\">Email</a></td>");
		} else {	
			out.println("<tr><td><a href="+classdir+"UniCash?action=vinspectprint&crecnum="+icrecnum+"&custnum="+icustnum+">"+icallslip+"</a></td><td>"+idate+"</td><td>"+techname+"</td><td><a href="+classdir+"UniCash?action=inspectprint&crecnum="+icrecnum+"&custnum="+icustnum+" target=\"_blank\">Print Format</a></td><td><a href="+classdir+"UniCash?action=sendsingleinspections&csrec="+icrecnum+"&custnum="+icustnum+" target=\"_blank\">Email</a></td>");
		}
		
		//DELETE LINK
		if (AllowDelete==1) {
			out.println("<td><a href="+classdir+"UniCash?action=delinspection&crecnum="+icrecnum+"&custnum="+icustnum+">D</a>elete</td>");
		}
		out.println("</tr>");
	}
	out.println("</table><br><br><a href="+classdir+"UniCash?action=addinspection&custnum="+tcustnum+">Add Inspection to Customer Record</a>");	
	
	
	out.println("<P><P>");
	} else if (csection.equalsIgnoreCase("2")) {
	out.println("<h3>Customer Call Record</h3>");
	
 	Statement stmt4 = con.createStatement();
	ResultSet rs4 = stmt4.executeQuery("SELECT * FROM callslip where custnum="+tcustnum+" and crectype='c'");
	//out.println("<table border=1 width=50%>");
	out.println("<table border=1 width=100%>");
	out.println("<th>Callslip</th><th>Date</th><th>Reason</th><th>Tech</th><th>Print</th><th>Email</th>");
	while(rs4.next())
	{
		ccrecnum=rs4.getInt("crecnum");
		ccallslip=rs4.getString("callslip");
		ccustnum=rs4.getString("custnum");
		String techname=rs4.getString("techid");
		
		// Added t to cdate
		tcdate=rs4.getString("cdate");
		// Added date formatting
		cdate=doFormatDate(getDate(tcdate));
		creason=rs4.getString("reason");
		
		if (techname==null) { techname="-"; }
		if (techname.equalsIgnoreCase(username)) {
			
			out.println("<tr><td><a href="+classdir+"UniCash?action=editcallslip&crecnum="+ccrecnum+"&custnum="+ccustnum+"&callslip="+ccallslip+">"+ccallslip+"</a></td><td>"+cdate+"</td><td>"+creason+"</td><td>"+techname+"</td><td><a href="+classdir+"UniCash?action=printcallslip&crecnum="+ccrecnum+"&custnum="+ccustnum+" target=\"_blank\">Print Format</a></td><td><a href="+classdir+"UniCash?action=sendsinglecallslips&csrec="+ccrecnum+"&custnum="+ccustnum+" target=\"_blank\">Email</a></td>");
		} else {
			out.println("<tr><td><a href="+classdir+"UniCash?action=viewpcallslip&crecnum="+ccrecnum+"&custnum="+ccustnum+"&callslip="+ccallslip+">"+ccallslip+"</a></td><td>"+cdate+"</td><td>"+creason+"</td><td>"+techname+"</td><td><a href="+classdir+"UniCash?action=printcallslip&crecnum="+ccrecnum+"&custnum="+ccustnum+" target=\"_blank\">Print Format</a></td><td><a href="+classdir+"UniCash?action=sendsinglecallslips&csrec="+ccrecnum+"&custnum="+ccustnum+" target=\"_blank\">Email</a></td>");
		}
		//DELETE LINK
		if (AllowDelete==1) {
			out.println("<td><a href="+classdir+"UniCash?action=delcallslip&crecnum="+ccrecnum+"&custnum="+ccustnum+">D</a>elete<td>");
		}
		out.println("</tr>");
	}
	out.println("</table><br><br>");
	
	out.println("<a href="+classdir+"UniCash?action=addcallslip&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Call Slip to Customer File</a>");
	
	out.println("<P><P>");

	} else if (csection.equalsIgnoreCase("4")) {
	out.println("<h3>Preventative Agreements</h3>");
 	Statement stmt5 = con.createStatement();
	ResultSet rs5 = stmt5.executeQuery("SELECT * FROM pagreement where custnum="+tcustnum+"");
	out.println("<table border=1 width=100%>");
	out.println("<th>Contract</th><th>Start Date</th><th>End Date</th><th>Cost</th><th>Print</th><th>Email</th>");
	while(rs5.next())
	{
		pcontnum=rs5.getInt("contnum");
		pcustnum=rs5.getString("custnum");
		pstartdate=doFormatDate(getDate(rs5.getString("startdate")));
		penddate=doFormatDate(getDate(rs5.getString("enddate")));
		pcost=rs5.getString("cost");
		pnotes=rs5.getString("notes");
		out.println("<tr><td><a href=\""+classdir+"UniCash?action=editprevagreement&reqsource=UniCash&contnum="+pcontnum+"&&custnum="+pcustnum+"\">"+pcontnum+"</a></td><td>"+pstartdate+"</td><td>"+penddate+"</td><td>"+pcost+"</td>");
		if (action.equalsIgnoreCase("showcustdetail_ide")) {
			out.println("<td><a href="+classdir+"UniCash?action=printprevagreement&contnum="+pcontnum+"&custnum="+pcustnum+">Print Format</a></td></tr>");
		} else {
			out.println("<td><a href="+classdir+"UniCash?action=printprevagreement&contnum="+pcontnum+"&custnum="+pcustnum+" target=_blank>Print Format</a></td><td><a href="+classdir+"UniCash?action=sendprevagreement&contnum="+pcontnum+"&custnum="+pcustnum+" target=_blank>Email</a></tr>");
		}
		
	}
	out.println("</table><br><br>");
	out.println("<a href="+classdir+"UniCash?action=addprevagreement&custnum="+custnum+"&reqsource=UniCash&custstart="+custstart+"&custstop="+custstop+"&custsite="+custsite+"&sitenum="+sitenum+">Add Preventative Agreement to Customer File</a>");
	
	out.println("<P><P>");
	} else if (csection.equalsIgnoreCase("5")) {
	out.println("<h3>Service Proposals</h3>");
	
	Vector vs;
	vs = ServQuotes.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
	out.println("<th>Proposal #</th><th>Date</th><th>Summary</th><th>Cost</th><th>Status</th>");
	counter=0;
	for (int i = 0 ; i < vs.size(); i++)
	{
		ServQuotes ts = (ServQuotes) vs.elementAt(i);
		propnum=ts.getQuoteNum();
		qcustnum=ts.getCrecNum();
		pdate=doFormatDate(getDate(ts.getQDate()));
		psummary=ts.getQDescription();
		qstatus=ts.getQStatus();
		totinvestment=0.00;
		Vector vp;
		vp = ServQuoteParts.getAllItems(con,propnum);
		for (int j = 0 ; j < vp.size(); j++)
		{
			ServQuoteParts tp = (ServQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment=totinvestment+ptotal;
		}
		
		out.println("<tr><td><a href=\""+classdir+"UniCash?action=editservproposal&quotenum="+propnum+"&&custnum="+qcustnum+"\">"+propnum+"</a></td><td>"+pdate+"</td><td>"+psummary+"</td><td>"+NumberFormat.getCurrencyInstance().format(totinvestment)+"</td><td>"+qstatus+"</td><td><a href="+classdir+"UniCash?action=printservproposal&propnum="+propnum+"&custnum="+custnum+" target=_blank>Print Format</a></td>");
		//DELETE LINK				
		if (AllowDelete==1) {
			out.println("<td><a href="+classdir+"UniCash?action=delservproprec&custnum="+custnum+"&propnum="+propnum+">Delete</a></td>");
		}
		out.println("</tr>");
	}
	out.println("</table><br><br>");
	out.println("<a href="+classdir+"UniCash?action=addservproposal&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Service Proposal to Customer File</a>");
	
	
	out.println("<P><P>");
	} else if (csection.equalsIgnoreCase("6")) {
	out.println("<h3>Sales Proposals</h3>");
	
	Vector vv;
	vv = UniQuotes.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
	out.println("<th>Proposal #</th><th>Date</th><th>Summary</th><th>Cost</th><th>Status</th><th>Print</th><th>Compare</th><th>Email</th>");
	counter=0;
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		propnum=tt.getQuoteNum();
		qcustnum=tt.getCrecNum();
		pdate=doFormatDate(getDate(tt.getQDate()));
		psummary=tt.getQDescription();
		qstatus=tt.getQStatus();
		totinvestment=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,propnum);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment=totinvestment+ptotal;
		}
		
		out.println("<tr><td><a href=\""+classdir+"UniCash?action=editproposal&quotenum="+propnum+"&&custnum="+qcustnum+"\">"+propnum+"</a></td><td>"+pdate+"</td><td>"+psummary+"</td><td>"+NumberFormat.getCurrencyInstance().format(totinvestment)+"</td><td>"+qstatus+"</td><td><a href="+classdir+"UniCash?action=printproposal&propnum="+propnum+"&custnum="+custnum+" target=_blank>Print Format</a><td><a href="+classdir+"UniCash?action=printcompare&csrec="+propnum+"&custnum="+custnum+" target=\"_blank\">Compare</a></td><td><a href="+classdir+"UniCash?action=sendsingleproposals&csrec="+propnum+"&custnum="+custnum+" target=\"_blank\">Email</a></td>");
		
		//DELETE LINK	
		if (AllowDelete==1) {
			out.println("<td><a href="+classdir+"UniCash?action=delproprec&custnum="+custnum+"&propnum="+propnum+">Delete</a></td>");
		}
		out.println("</tr>");
	}
	out.println("</table><br><br>");
	out.println("<a href="+classdir+"UniCash?action=addproposal&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Proposal to Customer File</a>");
	
	
	out.println("<P><P>");
	out.println("<h3>Pricing Worksheets</h3>");
	
	Vector vw;
	vw = UniWorksheet.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
	out.println("<th>Worksheet #</th><th>Date</th><th>Summary</th><th>Print</th><th>Email</th>");
	counter=0;
	for (int i = 0 ; i < vw.size(); i++)
	{
		UniWorksheet tw = (UniWorksheet) vw.elementAt(i);
		wsnum=tw.getWsRec();
		wscustnum=tw.getCrec();
		wsdate=doFormatDate(getDate(tw.getWsDate()));
		wssummary=tw.getWsDesc();
		
		
		out.println("<tr><td><a href=\""+classdir+"UniCash?action=editws&wsnum="+wsnum+"&&custnum="+wscustnum+"\">"+wsnum+"</a></td><td>"+wsdate+"</td><td>"+wssummary+"</td><td><a href="+classdir+"UniCash?action=printworksheet&wsnum="+wsnum+"&custnum="+wscustnum+" target=_blank>Print Format</a></td><td><a href="+classdir+"UniCash?action=sendsingleworksheet&wsnum="+wsnum+"&qdate="+wsdate+"&custnum="+wscustnum+" target=_blank>Email</a></td>");
		
		//DELETE LINK	
		if (AllowDelete==1) {
			out.println("<td><a href="+classdir+"UniCash?action=delwsrec&custnum="+custnum+"&wsnum="+wsnum+">Delete</a></td>");
		}
		out.println("</tr>");
	}
	out.println("</table><br><br>");
	out.println("<a href="+classdir+"UniCash?action=addworksheet&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Worksheet to Customer File</a>");
	out.println("<P><P>");

	} else if (csection.equalsIgnoreCase("7")) {


	out.println("<br><h3>Customer Forms</h3>");
	
	Vector vv;
	vv = CustFormList.getAllItems(con,custsite, sitenum);
	out.println("<table border=1 width=100%>");
	out.println("<th>Form #</th><th>Date</th><th>Form Name</th><th>User</th><th>Form Description</th><th>Print</th><th>Email</th>");
	counter=0;
	for (int i = 0 ; i < vv.size(); i++)
	{
		CustFormList tt = (CustFormList) vv.elementAt(i);
		String formname=tt.getFormName();
		String fusername=tt.getUserName();
		String formdesc=tt.getFormDesc();
		int formnum=tt.getFormNum();
		String formdate=doFormatDate(getDate(tt.getFormDate()));
		out.println("<tr><td><a href=\""+classdir+"UniCash?action=editcustform&formnum="+formnum+"&custnum="+custnum+"&questionnum=0\">"+formnum+"</a></td><td>"+formdate+"</td><td>"+formname+"</td><td>"+fusername+"</td><td>"+formdesc+"</td><td><a href="+classdir+"UniCash?action=printcustform&formnum="+formnum+"&custnum="+custnum+" target=_blank>Print Format</a><td><a href="+classdir+"UniCash?action=sendsingleform&formnum="+formnum+"&custnum="+custnum+" target=\"_blank\">Email</a></td>");
		//DELETE LINK	
		if (AllowDelete==1) {
			out.println("<td><a href="+classdir+"UniCash?action=delcustformrec&custnum="+custnum+"&formnum="+formnum+">Delete</a></td>");
		}
		out.println("</tr>");
	}
	out.println("</table><br><br>");
	out.println("<a href="+classdir+"UniCash?action=addcustform&custnum="+custnum+"&custsite="+custsite+"&sitenum="+sitenum+">Add Form to Customer File</a>");

	out.println("<h3>Site Survey</h3>");
	Statement stmt5 = con.createStatement();
	ResultSet rs5 = stmt5.executeQuery("SELECT * FROM custsurvey where custnum="+custnum+"");
	out.println("<table border=1 width=100%>");
	out.println("<th>Number</th><th>Date</th>");
	while(rs5.next())
	{
		int precnum=rs5.getInt("recnum");
		pcustnum=rs5.getString("custnum");
		String psdate=doFormatDate(getDate(rs5.getString("sdate")));
		out.println("<tr><td><a href=\""+classdir+"UniCash?action=editsurvey&recnum="+precnum+"&custnum="+pcustnum+"\">"+precnum+"</a></td><td>"+psdate+"</td></td><td><a href="+classdir+"UniCash?action=printsurvey&recnum="+precnum+"&custnum="+pcustnum+" target=_blank>Print Format</a></td></tr>");
	}
	out.println("</table>");
	out.println("<br><br><a href="+classdir+"UniCash?action=addsurvey&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Survey to Customer File</a><br><br>");		
	
	out.println("</body>");
	out.println("</html>");
	
	
	out.println("<P><P>");
	out.println("<h3>Equipment Check Record</h3>");
	
 	Statement stmtcm = con.createStatement();
	ResultSet rscm = stmtcm.executeQuery("SELECT * FROM checkme where custnum="+tcustnum+" and crectype='c'");
	//out.println("<table border=1 width=50%>");
	out.println("<table border=1 width=100%>");
	out.println("<th>Callslip</th><th>Date</th><th>Reason</th><th>Print</th><th>Email</th>");
	while(rscm.next())
	{
		ccrecnum=rscm.getInt("crecnum");
		ccallslip=rscm.getString("callslip");
		ccustnum=rscm.getString("custnum");
		// Added t to cdate
		tcdate=rscm.getString("cdate");
		// Added date formatting
		cdate=doFormatDate(getDate(tcdate));
		creason=rscm.getString("reason");
		
			out.println("<tr><td><a href="+classdir+"UniCash?action=editcheckme&crecnum="+ccrecnum+"&custnum="+ccustnum+"&callslip="+ccallslip+">"+ccallslip+"</a></td><td>"+cdate+"</td><td>"+creason+"</td><td><a href="+classdir+"UniCash?action=printcheckme&crecnum="+ccrecnum+"&custnum="+ccustnum+" target=\"_blank\">Print Format</a></td><td><a href="+classdir+"UniCash?action=sendsinglecheckme&csrec="+ccrecnum+"&custnum="+ccustnum+" target=\"_blank\">Email</a></td>");
		//DELETE LINK
		if (AllowDelete==1) {
			out.println("<td><a href="+classdir+"UniCash?action=delcheckme&crecnum="+ccrecnum+"&custnum="+ccustnum+">D</a>elete<td>");
		}
		out.println("</tr>");
	}
	out.println("</table><br><br>");
	
	out.println("<a href="+classdir+"UniCash?action=addcheckme&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Equipment Check to Customer File</a>");

	}
	con.close();
	return "true";
}
	
	
public static String doGetAllowDelete(String iusername, Connection con)
throws Exception
{
	Vector v;
	String allow_delete = null;
	v = UniTechInfo.getAllItems(con, iusername);
	int counter=0;
	for (int i = 0 ; i < v.size(); i++)
	{
		UniTechInfo t = (UniTechInfo) v.elementAt(i);
		allow_delete = t.getAllowDelete();
	}
	
	return allow_delete;                       
}


	public static String combinestring (String oldstring, String newstring)
			{
			String rstring=oldstring.concat(newstring);
			return rstring;
			}

//Format date to human form

public static String doFormatDate(Date visited)
		throws Exception
		{
	Date tdate;
	Format formatter;	
	formatter = new SimpleDateFormat("MM-dd-yyyy");
	String newdate = formatter.format(visited);
	return newdate;
		}


public int doFormatDateComp(Date visited)
		throws Exception
		{
	Date tdate;
	Format formatter;	
	formatter = new SimpleDateFormat("yyyyMMdd");
	String newdate = formatter.format(visited);
	int inewdate=Integer.parseInt(newdate);
	return inewdate;
		}


	public String doGetTechInfo_name(Connection c, String iusername)
	throws Exception
	{
                Vector v;
                v = UniTechInfo.getAllItems(c, iusername);
                int counter=0;
		String tech_name = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniTechInfo t = (UniTechInfo) v.elementAt(i);
                        tech_name = t.getTechName();
		}	
		return tech_name;
	}

public static Date getDate( String token ) {
              Date visited; 
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
              ParsePosition pos;
              try {                            
                      pos = new ParsePosition(0);
                      visited = sdf.parse(token,pos);
              } catch (NullPointerException pe) {
                      System.out.println("Cannot parse visited date! " + token ); 
                      pos = new ParsePosition(0);
                      visited = sdf.parse("16/06/97", pos);
              }
	return visited;
      }

//		public String getAgreement() { return mbody; }

}
