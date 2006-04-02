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
public class ServsysProposal extends UniCash
{
    
  	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir)
	throws SQLException, TodoException, Exception
        {

            String mbody="";
        String tcustnum = req.getParameter("custnum");
		String tcontnum = req.getParameter("propnum");
       		 int custnum = Integer.parseInt(tcustnum);
       		 int propnum = Integer.parseInt(tcontnum);
               int eenum=0;
                int ecustnum=0;
                String brand=null;
                String modelnum=null;
                String serialnum=null;
                String filter=null;
                String enotes=null;
                String type=null;
		String propprice = doGetPropPrice();

                int enum1 =0;
                int enum2 = 0;
                int enum3 = 0;
                int enum4 = 0;
                int enum5 = 0;
                int enum6 = 0;
                int enum7 = 0;
                int enum8 = 0;
                int enum9 = 0;
                int enum10 =0;
                String aservice  = null;
                String startdate = null;
                String enddate = null;
                String term = null;
                String cost = null;
                String notes = null;
                String agrdate = null;
                int vperyear = 0;

		String cname=null;
		String address1=null;
		String address2=null;
		String city =null;
		String state=null;
		String zip=null;
		String homephone=null;
		String altphone=null;
		String cust_notes=null;
		
		String tech_init = doGetTechInfo_init(username);
		String tech_name = doGetTechInfo_name(username);
		String tech_truck = doGetTechInfo_truck(username);

                String qdate  = null;
                String qdescription  = null;
                String qpayterms  = null;
                String qnotes  = null;
                String qdisc  = null;
                String qadditionalserv  = null;
		int qpartnum=0;
		String itemname=null;
		String mannum=null;
		int itemquant=0;
		int itemnum=0;
		int counter = 0;
		int combineopts=0;
		double totinvestment=0.00;
		double ototinvestment=0.00;
		String investment=null;
		double qtotal=0.00;
                Vector vv;
                vv = UniQuotes.getIndItem(con,custnum,propnum);
		counter=0;
                for (int i = 0 ; i < vv.size(); i++)
                {
                UniQuotes tt = (UniQuotes) vv.elementAt(i);
		qdate=tt.getQDate();
		qdescription=tt.getQDescription();
		qnotes=tt.getQNotes();
		qdisc=tt.getQDisc();
		qpayterms=tt.getQPayterms();
		qadditionalserv=tt.getQAdditionalServ();
		combineopts=tt.getCombineOpts();
	}

 		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM customers where custnum="+tcustnum+"");
		 while(rs.next())
        	        {
			cname=rs.getString("cname");
			address1=rs.getString("address1");
			address2=rs.getString("address2");
			city =rs.getString("city");
			state=rs.getString("state");
			zip=rs.getString("zip");
			homephone=rs.getString("homephone");
			altphone=rs.getString("altphone");
			cust_notes=rs.getString("cust_notes");
			}

	mbody=combinestring(mbody,"<html><head><title>Proposal</title>");
	mbody=doStyleSheetString(req, res, out, session, username,mbody);
	mbody=combinestring(mbody,"</head><body>");
	mbody=doMHeaderString(req, res, out, session, username,mbody); 
	mbody=combinestring(mbody,"<h2 align=CENTER>Proposal</h2>");
	mbody=combinestring(mbody,"<P ALIGN=LEFT><table><tr>");
	mbody=combinestring(mbody,"<td>This proposal is issued to:</td><td>"+cname+" </td></tr> ");
	mbody=combinestring(mbody,"<tr><td></td><td>"+address1+" "+ address2+"<br>"+city+", "+state+"  "+zip+" <br><br>Home Phone: "+homephone+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td></td><td></td></tr>");
	mbody=combinestring(mbody,"<tr><td>By <b>"+doGetCompanyName()+"</b> on:</td><td>"+doFormatDate(getDate(qdate))+" </td></tr> ");
	mbody=combinestring(mbody,"</table></p> ");
	mbody=combinestring(mbody,"<p>Thank you for the opportunity to quote on "+qdescription+".<br>");
	mbody=combinestring(mbody,"<br>For your <b>Comfort, Good Health, Peace of Mind, and Safety</b> "+doGetCompanyName()+" is recommending:<br>");
	mbody=combinestring(mbody,"<ul>");
                Vector vp;
                vp = UniQuoteParts.getAllItems(con,propnum);
                for (int j = 0 ; j < vp.size(); j++)
                {
                UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
		qpartnum=tp.getQPartnum();
		itemname=tp.getItemName();
		mannum=tp.getManNum();
		itemquant=tp.getItemQuant();
		investment=tp.getInvestment();
		qtotal=tp.getQuoteTotal();
		double subtot=Double.parseDouble(tp.getInvestment())*itemquant;
		totinvestment=totinvestment+subtot;
		if (combineopts==1) {
		ototinvestment=ototinvestment+subtot;
			}
	//mbody=combinestring(mbody,"<li> "+itemname+"; "+mannum+"</li>");
	mbody=combinestring(mbody,"<li> "+itemname+"</li>");
		}
		if (combineopts==0) {
	mbody=combinestring(mbody,"</ul>");
			}

	if (combineopts==0) {
	mbody=combinestring(mbody,"<br><br>");
			}

	 Locale[] locales = NumberFormat.getAvailableLocales();
	  double myNumber = totinvestment;
	   NumberFormat form;
	  form = NumberFormat.getIntegerInstance(locales[1]);
	if (combineopts==0) {
	if (propprice.equalsIgnoreCase("y")) {
	mbody=combinestring(mbody,"<p align=\"right\">Total Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(myNumber)))+"<br></p>");
	} else {
	mbody=combinestring(mbody,"<p align=\"right\">Total Investment: $____________________________<br></p>");
		}
		}

// GET OPTIONS:

                Vector vo;
                vo = UniQuoteOptions.getAllItems(con,propnum);
                for (int j = 0 ; j < vo.size(); j++)
                {
                UniQuoteOptions to = (UniQuoteOptions) vo.elementAt(j);

// GET OPTION PROPOSAL # AND PRINT
		int quoteoption = to.getQuoteOption();	
                Vector vz;
                vz = UniQuotes.getIndItem(con,custnum,quoteoption);
		counter=0;
                for (int i = 0 ; i < vz.size(); i++)
                {
                UniQuotes tz = (UniQuotes) vz.elementAt(i);
		qdescription=tz.getQDescription();
		}
// PRINT OPTION LINE
	if (combineopts==0) {
	mbody=combinestring(mbody,"<p align=\"left\"><b>Option: "+qdescription+"</b></p>");
	mbody=combinestring(mbody,"<br>");
		ototinvestment=0.00;
			}

// GET AND PRINT OPTION LIST
		if (combineopts==0) {
	mbody=combinestring(mbody,"<ul>");
				}
                Vector vx;
                vx = UniQuoteParts.getAllItems(con,quoteoption);
                for (int jj = 0 ; jj < vx.size(); jj++)
                {
                UniQuoteParts tx = (UniQuoteParts) vx.elementAt(jj);
		qpartnum=tx.getQPartnum();
		itemname=tx.getItemName();
		mannum=tx.getManNum();
		itemquant=tx.getItemQuant();
		investment=tx.getInvestment();
		qtotal=tx.getQuoteTotal();
		double subtot=Double.parseDouble(tx.getInvestment())*itemquant;
		ototinvestment=ototinvestment+subtot;
		mbody=combinestring(mbody,"<li>"+itemname+"</li>");
		}
		if (combineopts==0) {
	mbody=combinestring(mbody,"</ul><br>");
	 Locale[] olocales = NumberFormat.getAvailableLocales();
	  double OmyNumber = ototinvestment;
	   NumberFormat oform;
	  oform = NumberFormat.getIntegerInstance(olocales[1]);
	if (propprice.equalsIgnoreCase("y")) {
	mbody=combinestring(mbody,"<p align=\"right\">Optional Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(OmyNumber)))+"<br></p>");
	} else {
	mbody=combinestring(mbody,"<p align=\"right\">Optional Investment: $____________________________<br></p>");
		}
		}
		}

		if (combineopts==1) {
	mbody=combinestring(mbody,"</ul><br>");
	 Locale[] olocales = NumberFormat.getAvailableLocales();
	  double OmyNumber = ototinvestment;
	   NumberFormat oform;
	  oform = NumberFormat.getIntegerInstance(olocales[1]);
	if (propprice.equalsIgnoreCase("y")) {
	mbody=combinestring(mbody,"<p align=\"right\">Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(OmyNumber)))+"<br></p>");
	} else {
	mbody=combinestring(mbody,"<p align=\"right\">Investment: $____________________________<br></p>");
		}
			}
// NOW COMPLETE QUOTE

	mbody=combinestring(mbody,"<br><br>");
	mbody=combinestring(mbody,"Payment to be made as follows:<br>");
	mbody=combinestring(mbody,"<p align=center>"+qpayterms+"</p><br>");
	mbody=combinestring(mbody,"All material is guaranteed to be as specified.  All work completed within Massachusetts code specification. Please be aware that any hidden work which may be discovered or changed work orders will alter the cost of this project. Anticipated Install Dates are not guaranteed. "+doGetCompanyName()+" will strive to meet the anticipated dates, but equipment orders, and scheduling of other jobs sometimes make it necessary to adjust dates by a day or two. The Installation Coordinator will contact you to schedule the install date and time. All agreements contingent upon strikes, accidents or delays beyond our control.  Owner to carry fire, tornado and other necessary insurance.  Our workers are fully covered by Workmen's Compensation Insurance.  "+doGetCompanyName()+" wishes to thank you for your time and consideration on this project.  You may be assured of quality, professionalism and complete satisfaction with our work.<br>");

		if (qdisc.length()>1) {
	mbody=combinestring(mbody,"<br><br>");
	mbody=combinestring(mbody,"<b>The following Disclaimers apply to this proposal:<br>");
	mbody=combinestring(mbody,""+qdisc+"<br></b>");
		}


	mbody=combinestring(mbody,"<p align=center>Authorized Signature ___________________________________________________________________________<br><b>Note: This proposal may be withdrawn by us if not accepted within 10 days.</b></p>");

	mbody=combinestring(mbody,"<br><br><p align=center><b>Acceptance of Proposal</b></p>");
	mbody=combinestring(mbody,"The above prices, specifications and conditions are satisfactory and are hereby accepted.  You are authorized to do the work as specified.  Payment will be made as outlined above.  Cancellation of this proposal will result in a 10% retainer.<br><br>");
	mbody=combinestring(mbody,"</p><table width=\"95%\" align=center><tr><td>____________________________</td><td>_______</td><td>____________________________</td><td>_______</td></tr> ");
	mbody=combinestring(mbody,"<tr><td>Customer Signature</td><td>Date</td><td>The Company</td><td>Date</td></tr> ");
	mbody=combinestring(mbody,"<tr><td>"+cname+"</td><td></td><td>"+tech_name+"</td><td></td></tr></table> ");
	mbody=combinestring(mbody,"<br><br><br><br>");

	//mbody=combinestring(mbody,"<p align=\"right\">Total Investment: "+NumberFormat.getCurrencyInstance().format(totinvestment)+"<br></p>");
	if (propprice.equalsIgnoreCase("n")) {
	mbody=combinestring(mbody,"<p align=\"right\">Total Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(myNumber)))+"<br></p>");
			}

		out.println(mbody);
		//con.close();
				return mbody;
        }
    
	public static String getAllDateItems (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, int custnum, int propnum)
	throws SQLException, TodoException, Exception
        {
		String mbody="";
               int eenum=0;
                int ecustnum=0;
                String brand=null;
                String modelnum=null;
                String serialnum=null;
                String filter=null;
                String enotes=null;
                String type=null;
		String propprice = doGetPropPrice();

                int enum1 =0;
                int enum2 = 0;
                int enum3 = 0;
                int enum4 = 0;
                int enum5 = 0;
                int enum6 = 0;
                int enum7 = 0;
                int enum8 = 0;
                int enum9 = 0;
                int enum10 =0;
                String aservice  = null;
                String startdate = null;
                String enddate = null;
                String term = null;
                String cost = null;
                String notes = null;
                String agrdate = null;
                int vperyear = 0;

		String cname=null;
		String address1=null;
		String address2=null;
		String city =null;
		String state=null;
		String zip=null;
		String homephone=null;
		String altphone=null;
		String cust_notes=null;
		
		String tech_init = doGetTechInfo_init(username);
		String tech_name = doGetTechInfo_name(username);
		String tech_truck = doGetTechInfo_truck(username);

                String qdate  = null;
                String qdescription  = null;
                String qpayterms  = null;
                String qnotes  = null;
                String qdisc  = null;
                String qadditionalserv  = null;
		int qpartnum=0;
		String itemname=null;
		String mannum=null;
		int itemquant=0;
		int itemnum=0;
		int counter = 0;
		int combineopts=0;
		double totinvestment=0.00;
		double ototinvestment=0.00;
		String investment=null;
		double qtotal=0.00;
                Vector vq;
                vq = UniQuotes.getIndItem(con,custnum,propnum);
		counter=0;
                for (int i = 0 ; i < vq.size(); i++)
                {
                UniQuotes tq = (UniQuotes) vq.elementAt(i);
		qdate=tq.getQDate();
		qdescription=tq.getQDescription();
		qnotes=tq.getQNotes();
		qdisc=tq.getQDisc();
		qpayterms=tq.getQPayterms();
		qadditionalserv=tq.getQAdditionalServ();
		combineopts=tq.getCombineOpts();
	}

 		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM customers where custnum="+custnum+"");
		 while(rs.next())
        	        {
			cname=rs.getString("cname");
			address1=rs.getString("address1");
			address2=rs.getString("address2");
			city =rs.getString("city");
			state=rs.getString("state");
			zip=rs.getString("zip");
			homephone=rs.getString("homephone");
			altphone=rs.getString("altphone");
			cust_notes=rs.getString("cust_notes");
			}

	mbody=combinestring(mbody,"<html><head><title>Proposal</title>");
	mbody=doStyleSheetString(req, res, out, session, username,mbody);
	mbody=combinestring(mbody,"</head><body>");
	mbody=doMHeaderString(req, res, out, session, username,mbody); 
	mbody=combinestring(mbody,"<h2 align=CENTER>Proposal</h2>");
	mbody=combinestring(mbody,"<P ALIGN=LEFT><table><tr>");
	mbody=combinestring(mbody,"<td>This proposal is issued to:</td><td>"+cname+" </td></tr> ");
	mbody=combinestring(mbody,"<tr><td></td><td>"+address1+" "+ address2+"<br>"+city+", "+state+"  "+zip+" <br><br>Home Phone: "+homephone+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td></td><td></td></tr>");
	mbody=combinestring(mbody,"<tr><td>By <b>"+doGetCompanyName()+"</b> on:</td><td>"+doFormatDate(getDate(qdate))+" </td></tr> ");
	mbody=combinestring(mbody,"</table></p> ");
	mbody=combinestring(mbody,"<p>Thank you for the opportunity to quote on "+qdescription+".<br>");
	mbody=combinestring(mbody,"<br>For your <b>Comfort, Good Health, Peace of Mind, and Safety</b> "+doGetCompanyName()+" is recommending:<br>");
	mbody=combinestring(mbody,"<ul>");
                Vector vp;
                vp = UniQuoteParts.getAllItems(con,propnum);
                for (int j = 0 ; j < vp.size(); j++)
                {
                UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
		qpartnum=tp.getQPartnum();
		itemname=tp.getItemName();
		mannum=tp.getManNum();
		itemquant=tp.getItemQuant();
		investment=tp.getInvestment();
		qtotal=tp.getQuoteTotal();
		double subtot=Double.parseDouble(tp.getInvestment())*itemquant;
		totinvestment=totinvestment+subtot;
		if (combineopts==1) {
		ototinvestment=ototinvestment+subtot;
			}
	//mbody=combinestring(mbody,"<li> "+itemname+"; "+mannum+"</li>");
	mbody=combinestring(mbody,"<li> "+itemname+"</li>");
		}
		if (combineopts==0) {
	mbody=combinestring(mbody,"</ul>");
			}

	if (combineopts==0) {
	mbody=combinestring(mbody,"<br><br>");
			}

	 Locale[] locales = NumberFormat.getAvailableLocales();
	  double myNumber = totinvestment;
	   NumberFormat form;
	  form = NumberFormat.getIntegerInstance(locales[1]);
	if (combineopts==0) {
	if (propprice.equalsIgnoreCase("y")) {
	mbody=combinestring(mbody,"<p align=\"right\">Total Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(myNumber)))+"<br></p>");
	} else {
	mbody=combinestring(mbody,"<p align=\"right\">Total Investment: $____________________________<br></p>");
		}
		}

// GET OPTIONS:

                Vector vo;
                vo = UniQuoteOptions.getAllItems(con,propnum);
                for (int j = 0 ; j < vo.size(); j++)
                {
                UniQuoteOptions to = (UniQuoteOptions) vo.elementAt(j);

// GET OPTION PROPOSAL # AND PRINT
		int quoteoption = to.getQuoteOption();	
                Vector vz;
                vz = UniQuotes.getIndItem(con,custnum,quoteoption);
		counter=0;
                for (int i = 0 ; i < vz.size(); i++)
                {
                UniQuotes tz = (UniQuotes) vz.elementAt(i);
		qdescription=tz.getQDescription();
		}
// PRINT OPTION LINE
	if (combineopts==0) {
	mbody=combinestring(mbody,"<p align=\"left\"><b>Option: "+qdescription+"</b></p>");
	mbody=combinestring(mbody,"<br>");
		ototinvestment=0.00;
			}

// GET AND PRINT OPTION LIST
		if (combineopts==0) {
	mbody=combinestring(mbody,"<ul>");
				}
                Vector vx;
                vx = UniQuoteParts.getAllItems(con,quoteoption);
                for (int jj = 0 ; jj < vx.size(); jj++)
                {
                UniQuoteParts tx = (UniQuoteParts) vx.elementAt(jj);
		qpartnum=tx.getQPartnum();
		itemname=tx.getItemName();
		mannum=tx.getManNum();
		itemquant=tx.getItemQuant();
		investment=tx.getInvestment();
		qtotal=tx.getQuoteTotal();
		double subtot=Double.parseDouble(tx.getInvestment())*itemquant;
		ototinvestment=ototinvestment+subtot;
		mbody=combinestring(mbody,"<li>"+itemname+"</li>");
		}
		if (combineopts==0) {
	mbody=combinestring(mbody,"</ul><br>");
	 Locale[] olocales = NumberFormat.getAvailableLocales();
	  double OmyNumber = ototinvestment;
	   NumberFormat oform;
	  oform = NumberFormat.getIntegerInstance(olocales[1]);
	if (propprice.equalsIgnoreCase("y")) {
	mbody=combinestring(mbody,"<p align=\"right\">Optional Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(OmyNumber)))+"<br></p>");
	} else {
	mbody=combinestring(mbody,"<p align=\"right\">Optional Investment: $____________________________<br></p>");
		}
		}
		}

		if (combineopts==1) {
	mbody=combinestring(mbody,"</ul><br>");
	 Locale[] olocales = NumberFormat.getAvailableLocales();
	  double OmyNumber = ototinvestment;
	   NumberFormat oform;
	  oform = NumberFormat.getIntegerInstance(olocales[1]);
	if (propprice.equalsIgnoreCase("y")) {
	mbody=combinestring(mbody,"<p align=\"right\">Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(OmyNumber)))+"<br></p>");
	} else {
	mbody=combinestring(mbody,"<p align=\"right\">Investment: $____________________________<br></p>");
		}
			}
// NOW COMPLETE QUOTE

	mbody=combinestring(mbody,"<br><br>");
	mbody=combinestring(mbody,"Payment to be made as follows:<br>");
	mbody=combinestring(mbody,"<p align=center>"+qpayterms+"</p><br>");
	mbody=combinestring(mbody,"All material is guaranteed to be as specified.  All work completed within Massachusetts code specification. Please be aware that any hidden work which may be discovered or changed work orders will alter the cost of this project. Anticipated Install Dates are not guaranteed. "+doGetCompanyName()+" will strive to meet the anticipated dates, but equipment orders, and scheduling of other jobs sometimes make it necessary to adjust dates by a day or two. The Installation Coordinator will contact you to schedule the install date and time. All agreements contingent upon strikes, accidents or delays beyond our control.  Owner to carry fire, tornado and other necessary insurance.  Our workers are fully covered by Workmen's Compensation Insurance.  "+doGetCompanyName()+" wishes to thank you for your time and consideration on this project.  You may be assured of quality, professionalism and complete satisfaction with our work.<br>");

		if (qdisc.length()>1) {
	mbody=combinestring(mbody,"<br><br>");
	mbody=combinestring(mbody,"<b>The following Disclaimers apply to this proposal:<br>");
	mbody=combinestring(mbody,""+qdisc+"<br></b>");
		}


	mbody=combinestring(mbody,"<p align=center>Authorized Signature ___________________________________________________________________________<br><b>Note: This proposal may be withdrawn by us if not accepted within 10 days.</b></p>");

	mbody=combinestring(mbody,"<br><br><p align=center><b>Acceptance of Proposal</b></p>");
	mbody=combinestring(mbody,"The above prices, specifications and conditions are satisfactory and are hereby accepted.  You are authorized to do the work as specified.  Payment will be made as outlined above.  Cancellation of this proposal will result in a 10% retainer.<br><br>");
	mbody=combinestring(mbody,"</p><table width=\"95%\" align=center><tr><td>____________________________</td><td>_______</td><td>____________________________</td><td>_______</td></tr> ");
	mbody=combinestring(mbody,"<tr><td>Customer Signature</td><td>Date</td><td>The Company</td><td>Date</td></tr> ");
	mbody=combinestring(mbody,"<tr><td>"+cname+"</td><td></td><td>"+tech_name+"</td><td></td></tr></table> ");
	mbody=combinestring(mbody,"<br><br><br><br>");

	//mbody=combinestring(mbody,"<p align=\"right\">Total Investment: "+NumberFormat.getCurrencyInstance().format(totinvestment)+"<br></p>");
	if (propprice.equalsIgnoreCase("n")) {
	mbody=combinestring(mbody,"<p align=\"right\">Total Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(myNumber)))+"<br></p>");
			}

		out.println(mbody);
		//con.close();
				return mbody;
        }
}
