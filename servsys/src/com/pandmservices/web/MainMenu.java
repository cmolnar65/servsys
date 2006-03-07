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

public class MainMenu extends UniCash
{
	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, String action,  String menu)
	throws SQLException, TodoException, Exception
{
	//String menu="";
	String mbody="";
	String category="";
	int catnum=0;
	//menu = req.getParameter("menu");
	if (menu==null) {
		menu="main";
			}
	username=(String)session.getAttribute("login");
	mbody=combinestring(mbody,"<html>");
	doMenuStyleSheet (req, res, out, session, username);
	mbody=combinestring(mbody,"<body bgcolor=\"#A0B8C8\" text=\"#000000\" link=\"#000000\" vlink=\"#000000\" alink=\"#000000\">");
	mbody=combinestring(mbody,"<br><br><br>");
	mbody=combinestring(mbody,"<br><a href="+apphome+" target=_top >Home</a><br>");
	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=inventory target=nav>Inventory</a><br>");
	if (menu.equalsIgnoreCase("inventory")) {
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=showcatlist target=_blank>Inventory Movement</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=truckstockrequest target=_blank>Truck Restock Report</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=truckbycat target=_blank>Stock by Category</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=truckbykeycode target=_blank>Stock by Keycode</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=startkeycodelookup target=_blank>Lookup Keycodes</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectstockdatespan target=_blank>Inventory Usage</a><br>");
	}

	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=customers target=nav>Customers</a><br>");

	if (menu.equalsIgnoreCase("customers")) {
	mbody=combinestring(mbody,"<table><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=a&custstop=b target=phpmain>A&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=b&custstop=c target=phpmain>B&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=c&custstop=d target=phpmain>C&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=d&custstop=e target=phpmain>D&nbsp;&nbsp;</a></td></tr>");
	mbody=combinestring(mbody,"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=e&custstop=f target=phpmain>E&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=f&custstop=g target=phpmain>F&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=g&custstop=h target=phpmain>G&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=h&custstop=i target=phpmain>H&nbsp;&nbsp;</a></td></tr>");
	mbody=combinestring(mbody,"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=i&custstop=j target=phpmain>I&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=j&custstop=k target=phpmain>J&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=k&custstop=l target=phpmain>K&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=l&custstop=m target=phpmain>L&nbsp;&nbsp;</a></td></tr>");
	mbody=combinestring(mbody,"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=m&custstop=n target=phpmain>M&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=n&custstop=o target=phpmain>N&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=o&custstop=p target=phpmain>O&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=p&custstop=q target=phpmain>P&nbsp;&nbsp;</a></td></tr>");
	mbody=combinestring(mbody,"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=q&custstop=r target=phpmain>Q&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=r&custstop=s target=phpmain>R&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=s&custstop=t target=phpmain>S&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=t&custstop=u target=phpmain>T&nbsp;&nbsp;</a></td></tr>");
	mbody=combinestring(mbody,"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=u&custstop=v target=phpmain>U&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=v&custstop=w target=phpmain>V&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=w&custstop=x target=phpmain>W&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=x&custstop=y target=phpmain>X&nbsp;&nbsp;</a></td></tr>");
	mbody=combinestring(mbody,"<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=y&custstop=z target=phpmain>Y&nbsp;&nbsp;</a></td>");
	mbody=combinestring(mbody,"<td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=z&custstop=ZZ target=phpmain>Z&nbsp;&nbsp;</a><br></td></tr></table>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=searchcustcity  target=phpmain>Search by City</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=searchcustaddress  target=phpmain>Search by Street</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=searchcustnum  target=phpmain>Search for Customer Number</a><br>");
}


	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=addleadstart target=phpmain>Add Lead</a><br>");

	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=timesheet target=nav>Time Sheets</a><br>");
	if (menu.equalsIgnoreCase("timesheet")) {
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selecttimedate target=_blank>Time Sheet</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectsenddate target=_blank>Transmit Daily Reports</a><br>");
	}

	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=listprevprice target=_blank>Planned Service Pricing</a><br>");
	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=calcprice target=_blank>Calculate Price</a><br>");
	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=reminders target=_blank>Reminders</a><br>");
	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=flatrate target=nav>Flat Rate Prices</a><br>");
	menu = req.getParameter("menu");
	if (menu.equalsIgnoreCase("flatrate")) {
                Vector v;
                v = FlatRateCat.getAllItems(con);
                for (int i = 0 ; i < v.size(); i++)
                {
                       	FlatRateCat t = (FlatRateCat) v.elementAt(i);
                      	catnum = t.getCatnum();
                        category = t.Category();
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listflatrate&servicestart=a&serviceend=d&catnum="+catnum+" target=phpmain>"+category+"</a><br>");
                }
	}

	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=masterworksheet target=nav>Master Worksheets</a><br>");
	if (menu.equalsIgnoreCase("masterworksheet")) {
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listmwork&servicestart=a&serviceend=d target=phpmain>Master Work Sheets A-C</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listmwork&servicestart=d&serviceend=g target=phpmain>Master Work Sheets D-F</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listmwork&servicestart=g&serviceend=j target=phpmain>Master Work Sheets G-I</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listmwork&servicestart=j&serviceend=m target=phpmain>Master Work Sheets J-L</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listmwork&servicestart=m&serviceend=p target=phpmain>Master Work Sheets M-O</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listmwork&servicestart=p&serviceend=s target=phpmain>Master Work Sheets P-R</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listmwork&servicestart=s&serviceend=v target=phpmain>Master Work Sheets S-U</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listmwork&servicestart=v&serviceend= target=phpmain>Mater Work Sheets V-Z</a><br>");
	}

	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=documentation target=nav>Documentation</a><br>");
	if (menu.equalsIgnoreCase("documentation")) {
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listphonelist target=_blank>Company Phone List</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=checktempsplit target=_blank>Check Temp Split</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=showdoccatlist target=phpmain>Documentiation Categories</a><br>");
	}
	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=airbalance target=nav>Air Balancing</a><br>");
	String thismainserver=doGetThisMainServer();
	if (menu.equalsIgnoreCase("airbalance")) {
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=airflowstandards target=_blank>Air Flow Standards</a><br>");
//		if (Integer.parseInt(doGetAllowModAirBal(username))==1) {
//	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href=http://127.0.0.1:8080/servsys/index_airbal.html target=_top >Air Flow Module</a><br>");
//		}
	}
//	if (Integer.parseInt(doGetAllowModInstall(username))==1) {
//	mbody=combinestring(mbody,"<br><a href=http://127.0.0.1:8080/servsys/index_install.html target=_top >Installation</a><br>");
//	}
	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=config target=nav>Configuration</a><br>");
	if (menu.equalsIgnoreCase("config")) {
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=editcompinfo target=phpmain>Company Configuration</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listtechinfo target=phpmain>Tech Configuration</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=editserverconfig target=phpmain>Db Server Config</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=editemailtruckcat target=phpmain>Edit Stock Email Cats</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=ecompletioncodes target=phpmain>Completion Codes</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=edittimecats target=phpmain>Time Sheet Category</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=showquotecatlist target=_blank>Equipment Pricing</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=showpackagecatlist target=_blank>Package Pricing</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=showallpackages target=_blank>All Package</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=editwsconfig target=phpmain>WorkSheet Config</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=editfrconfig target=phpmain>Flat Rate Config</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=confightgloadcatlist target=phpmain>Heating Loads</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=zerotruckstock target=_blank>Zero Truck Stock</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=configforms target=_blank>Forms</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=edrespsform target=phpmain>Residential Planned Service Form</a><br>");
	}
	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=reports target=nav>Reports</a><br>");
	if (menu.equalsIgnoreCase("reports")) {
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=reportpackagecatlist target=_blank>Package List Report</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=followupreport target=_blank>Callslip Followup Report</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectcallrepdatespan target=_blank>Call Report</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectcommisiondatespan target=_blank>Commision Report</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectproposaldatespan target=_blank>Proposal Report</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectservproposaldatespan target=_blank>Service Proposal Report</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectwsdatespan target=_blank>Worksheet Report</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=flatratereport target=_blank>Flat Rates</a><br>");
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=exportflatrate&servicestart=a&serviceend=&catnum=0 target=_blank>Flat Rate Export</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=nositenum target=_blank>No Cust Number</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selecttimereportdate target=_blank>Display Time/Activity Report</a><br>");
	}

	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=menu&menu=update target=nav>Update</a><br>");

	if (menu.equalsIgnoreCase("update")) {
		if (thismainserver.equalsIgnoreCase("no")) {
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=updateinvkeycodetable target=phpmain>KeyCode Table</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=updateflatrateprices target=phpmain>Flat Rates</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=updatepsprices target=phpmain>Planned Service</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=downloadfrconfig target=phpmain>Flat Rate Config</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=updatepackages target=phpmain>Packages</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=updateexpiredate target=phpmain>Software Expire Date</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=updatephone_list target=phpmain>Company Phone List</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=updatemasterworksheet target=phpmain>Master Worksheets</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=updatedoclist target=phpmain>Document List</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=updateuserinfo target=phpmain>User Info</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=downloadtimecat target=phpmain>Time Sheet Categories</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=downloadcompconfig target=phpmain>Company Configuration</a><br>");
        mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=syncdbserver target=phpmain>DbServer Config</a><br>");
					} else if (thismainserver.equalsIgnoreCase("yes"))
					 {
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;You are on the main server - anything<br>&nbsp;&nbsp;&nbsp;-&nbsp; you have done here is live Ask<br>&nbsp;&nbsp;&nbsp;-&nbsp; server admin to update dates.<br>");
					} else {
	mbody=combinestring(mbody,"&nbsp;&nbsp;&nbsp;-&nbsp;We have no idea if you are running local or remote - please ask for your configuration to be updated.");
			}
	}

		if (thismainserver.equalsIgnoreCase("no")) {
	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=servsync target=phpmain>Sync To Server</a><br>");
	mbody=combinestring(mbody,"--MUST HAVE INTERNET CONNECT<br>");
	String smtpserver = doGetSmtpServer(username);
	if (smtpserver.equalsIgnoreCase("smtp.emailsrvr.com")) {
	mbody=combinestring(mbody,"<br><a href=http://www.webmail.us target=_blank>Access WebMail</a><br>");
								} else {
	mbody=combinestring(mbody,"<br><a href=http://"+smtpserver+"/WebMail target=_blank>Access WebMail</a><br>");
					}
	mbody=combinestring(mbody,"--MUST HAVE INTERNET CONNECT<br>");
		}
	mbody=combinestring(mbody,"<br><a href="+Gwebhome+"/gpl.html target=phpmain>View License</a><br>");
	mbody=combinestring(mbody,"<br><a href="+classdir+"UniCash?action=logoutuser target=phpmain>Logout User</a><br>");
	if (menu.equalsIgnoreCase("main")) {
		//placeholder
	}

	mbody=combinestring(mbody,"</body>");
	mbody=combinestring(mbody,"</html>");
	out.println(mbody);
	out.close();
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


//		public String getAgreement() { return mbody; }

}
