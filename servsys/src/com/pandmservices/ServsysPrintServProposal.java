/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandmservices;
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
public class ServsysPrintServProposal extends UniCash{
    
  	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir)
	throws SQLException, TodoException, Exception
        {

            String mbody="";

	    	Format formatter;
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
                String qadditionalserv  = null;
		int qpartnum=0;
		String itemname=null;
		String mannum=null;
		int itemquant=0;
		int itemnum=0;
		int counter = 0;
		double totinvestment=0.00;
		String investment=null;
		double qtotal=0.00;
                Vector vv;
                vv = ServQuotes.getIndItem(con,custnum,propnum);
		counter=0;
                for (int i = 0 ; i < vv.size(); i++)
                {
                ServQuotes tt = (ServQuotes) vv.elementAt(i);
		qdate=tt.getQDate();
		qdescription=tt.getQDescription();
		qnotes=tt.getQNotes();
		qpayterms=tt.getQPayterms();
		qadditionalserv=tt.getQAdditionalServ();
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
//////////////////////////////////////////////////////
// Print Header Information
/////////////////////////////////////////////////////

	//mbody=combinestring(mbody,"<html><basefont size=2>");
        mbody=combinestring(mbody,"<html>");

        mbody=doStyleSheetString(req, res, out, session, username,mbody);
	mbody=combinestring(mbody,"<title>Service Proposal</title></head><body>");
	mbody=doMHeaderString(req, res, out, session, username, mbody);

        
	mbody=combinestring(mbody,"<h4 align=CENTER>HVAC REPAIR PROPOSAL</h4>");
	mbody=combinestring(mbody,"<P ALIGN=LEFT>"+doFormatDate(getDate(qdate))+"<table><tr>");
	mbody=combinestring(mbody,"<td>Customer:</td><td>"+cname+" </td></tr> ");
	mbody=combinestring(mbody,"<tr><td></td><td>"+address1+" "+ address2+"<br>"+city+", "+state+"  "+zip+" </td></tr>");
	mbody=combinestring(mbody,"<tr><td></td><td></td></tr>");
	mbody=combinestring(mbody,"</table></p> ");
	mbody=combinestring(mbody,"<p>Re: "+qdescription+".<br>");
	mbody=combinestring(mbody,"<br>"+doGetCompanyName()+", recommends the following repairs for your property located at "+address1+", "+city+". These repairs are for your comfort, safety, and peace of mind.<br>");
	mbody=combinestring(mbody,"<br>We suggest the Following Repairs:<br>");
	mbody=combinestring(mbody,"<ul>");
	
                Vector vp;
                vp = ServQuoteParts.getAllItems(con,propnum);
                for (int j = 0 ; j < vp.size(); j++)
                {
                ServQuoteParts tp = (ServQuoteParts) vp.elementAt(j);
		qpartnum=tp.getQPartnum();
		itemname=tp.getItemName();
		mannum=tp.getManNum();
		itemquant=tp.getItemQuant();
		investment=tp.getInvestment();
		qtotal=tp.getQuoteTotal();
		double subtot=Double.parseDouble(tp.getInvestment())*itemquant;
		totinvestment=totinvestment+subtot;
	mbody=combinestring(mbody,"<li>"+itemquant+" - "+itemname+"; "+mannum+".............."+NumberFormat.getCurrencyInstance().format(subtot)+"</li>");
		}
	
	mbody=combinestring(mbody,"</ul>");
	mbody=combinestring(mbody,"");

	 Locale[] locales = NumberFormat.getAvailableLocales();
	  double myNumber = totinvestment;
	   NumberFormat form;
	   form = NumberFormat.getIntegerInstance(locales[1]);
	mbody=combinestring(mbody,"<p align=\"right\">Total Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(myNumber)))+"<br></p>");
	mbody=combinestring(mbody,"<br>");
	mbody=combinestring(mbody,"Pricing does include labor and these prices are subject to change without notice unless accepted within thirty (30) calendar days from the date of this contract.<br><br>");
	mbody=combinestring(mbody,""+doGetCompanyName()+" will coordinate the scheduling of the project with you as soon as you approve the work.<br><br>Please review, and if you have any questions feel free to contact me.<br><br>");

	mbody=combinestring(mbody,"<br><br><p align=center><b>Acceptance of Proposal</b></p>");
	mbody=combinestring(mbody,"The above prices, specifications and conditions are satisfactory and are hereby accepted.  You are authorized to do the work as specified.  Payment will be made upon completion of the work.<br><br>");
	mbody=combinestring(mbody,"</p><table width=\"95%\" align=center><tr><td>____________________________</td><td>_______</td><td>____________________________</td><td>_______</td></tr> ");
	mbody=combinestring(mbody,"<tr><td>Customer Signature</td><td>Date</td><td>"+doGetCompanyName()+"</td><td>Date</td></tr> ");
	mbody=combinestring(mbody,"<tr><td>"+cname+"</td><td></td><td>"+tech_name+"</td><td></td></tr></table> ");
		con.close();  
                
        mbody=combinestring(mbody,mbody);
        out.println(mbody);
		//con.close();
	return mbody;
        }
    
}
