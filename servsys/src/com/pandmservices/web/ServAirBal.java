package com.pandmservices.web; 
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.support.*;
import com.pandmservices.dbserver.*;
import javax.servlet.*;
import javax.servlet.http.*;
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
import java.util.Date;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ServAirBal extends UniCash
{
	ServletConfig config = null;
	Connection con = null;
	Connection condb = null;
	Connection conu = null;
	String glogin = null;
	int sall=0;
	String apphome=null;
	String pass = null;
	String classdir=null;
	String grealname = null;
	String gidcode = null;
	String Gwebhome = null;
	String phpdir=null;
	String menu = null;
	String headerimage=null;
        String emailsendaddress=null;
	String adminok = "0";
        String emailserver=null;
	String techemailaddress=null;
	static String TRUE =  "TRUE";
	public static long MS_SECOND = 1000L; 
	public static long MS_MINUTE = 60L * MS_SECOND; 
	public static long MS_HOUR = 60L * MS_MINUTE; 
	public static long DAY = 24L;

	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		this.config = config;

	try
		{
			Gwebhome = (String) config.getInitParameter("web.home");
			String driver = (String) config.getInitParameter("db.driver");
			apphome = (String) config.getInitParameter("web.apphome");
			classdir = (String) config.getInitParameter("web.classdir");
			String protocol = (String) config.getInitParameter("db.protocol");
			String subProtocol = (String) config.getInitParameter("db.subprotocol");
			String server = (String) config.getInitParameter("db.server");
			String database = (String) config.getInitParameter("db.database");
			String user = (String) config.getInitParameter("db.username");
			pass = (String) config.getInitParameter("db.password");
			phpdir = (String) config.getInitParameter("web.phpdir");
			Class.forName(driver);
			con = DriverManager.getConnection(protocol+":"+subProtocol+"://"+server+"/"+database, user, pass);

		}
		catch (Exception e)
		{
			throw new ServletException(e.getMessage());
		}
	};// init


	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException
	{
		try
		{
			HttpSession session=req.getSession(true);
			res.setContentType("text/html");
			//session.putValue("con",con);
			PrintWriter out= res.getWriter();

			String action = req.getParameter("action");

			if (action == null) {
                                doMenu(req, res, out, session);
				}
			else if (action.equalsIgnoreCase("menu"))
	                        {
                                doMenu(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("showhomepage"))
	                        {
                                doShowHomePage(req, res, out, session);
				}
			else if (action.equalsIgnoreCase("listcustomers"))
	                        {
                                doListCustomers(req, res, out, session);
				}
			else if (action.equalsIgnoreCase("showcustdetail"))
	                        {
                                doShowCustDetail(req, res, out, session);
	                        }
			else
			{
				doError(req, res, out);
			}


		}
		catch (Exception e)
		{
			throw new ServletException(e.getMessage());
		}
	}// doGet


	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException
	{
		doGet(req, res);
	}// doPost



	private void doMenu(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
		throws Exception
	{
	String menu = req.getParameter("menu");
	out.println("<html>");
	doMenuStyleSheet (req, res, out, session);
	out.println("<body bgcolor=\"#A0B8C8\" text=\"#000000\" link=\"#000000\" vlink=\"#000000\" alink=\"#000000\">");
	out.println("<br><br><br>");
	out.println("<br><a href=http://127.0.0.1:8080/servsys/index.html target=_top >Main</a><br>");
	out.println("<br><a href=http://127.0.0.1:8080/servsys/index_install.html target=_top >Home</a><br>");
	out.println("<br><a href="+classdir+"UniCash?action=menu&menu=reports target=nav>Reports</a><br>");
	if (menu.equalsIgnoreCase("reports")) {
        out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=reportpackagecatlist target=_blank>Package List Report</a><br>");
        out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectproposaldatespan target=_blank>Proposal Report</a><br>");
        out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectservproposaldatespan target=_blank>Service Proposal Report</a><br>");
        out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=selectwsdatespan target=_blank>Worksheet Report</a><br>");
        out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=flatratereport target=_blank>Flat Rates</a><br>");
	}

	out.println("<br><a href="+classdir+"ServInstall?action=menu&menu=customers target=nav>Customers</a><br>");

	if (menu.equalsIgnoreCase("customers")) {
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=a&custstop=b target=phpmain>Customers A</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=b&custstop=c target=phpmain>Customers B</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=c&custstop=e target=phpmain>Customers C-D</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=e&custstop=g target=phpmain>Customers E-F</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=g&custstop=i target=phpmain>Customers G-H</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=i&custstop=l target=phpmain>Customers I-K</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=l&custstop=m target=phpmain>Customers L</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=m&custstop=n target=phpmain>Customers M</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=n&custstop=o target=phpmain>Customers N</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=o&custstop=p target=phpmain>Customers O</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=p&custstop=q target=phpmain>Customers P</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=q&custstop=s target=phpmain>Customers Q-R</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=s&custstop=t target=phpmain>Customers S</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=t&custstop=u target=phpmain>Customers T</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServAirBal?action=listcustomers&custstart=u&custstop=ZZ target=phpmain>Customers U-Z</a><br>");
		}
	if (menu.equalsIgnoreCase("main")) {
		//placeholder
	}

	out.println("</body>");
	out.println("</html>");
	}

	
	private void doError(HttpServletRequest req, HttpServletResponse res, PrintWriter out)
		throws Exception
	{

		out.println("The servlet was called with an invalid action: " + req.getParameter("action"));
		printFooter(req, res, out);
	}


	private void printFooter(HttpServletRequest req, HttpServletResponse res, PrintWriter out)
	throws Exception
	{
	out.println("<br>");
	String apphome = (String) config.getInitParameter("web.apphome");
	String classdir = (String) config.getInitParameter("web.classdir");
	out.println("<center><h2>");
	out.println("</h2></center>");
	}

	private void printHeader(HttpServletRequest req, HttpServletResponse res, PrintWriter out)
	throws Exception
	{
	out.println("<http>");

	}
        
	private void doShowHomePage(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
	throws Exception
	{
		String imagename=null;
		String imagewidth=null;
		String imagehight=null;
		String compname=null;
		String complogo=null;
		String compaddress=null;
		String compphone=null;

                Vector v;
                v = UniCompConfig.getAllItems(con);
		int counter=0;
                for (int i = 0 ; i < v.size(); i++)
                {
                       	UniCompConfig t = (UniCompConfig) v.elementAt(i);
			imagename=t.getImage();
			imagewidth=t.getImageWidth();
			imagehight=t.getImageHight();
			compname=t.getCoName();
			complogo=t.getCoLogo();
			compaddress=t.getCoAddress();
			compphone=t.getCoPhone();
		}
            
    out.println("<HTML>");
out.println("<HEAD>");
out.println("        <META HTTP-EQUIV=\"CONTENT-TYPE\" CONTENT=\"text/html; charset=iso-8859-1\">");
 out.println("       <TITLE>P&M Services</TITLE>");

out.println("</HEAD>");
out.println("<BODY TEXT=\"#000000\" LINK=\"#0000ff\" VLINK=\"#000080\" BGCOLOR=\"#ffffff\">");

out.println("<TABLE WIDTH=100% BORDER=0 CELLPADDING=4 CELLSPACING=0>");
	out.println("<THEAD>");
		out.println("<TR>");
			out.println("</TD>");
			out.println("<TD WIDTH=30% VALIGN=TOP>");
				out.println("<P><IMG SRC=\""+imagename+"\" NAME=\"Graphic1\" ALIGN=CENTER WIDTH="+imagewidth+" HEIGHT="+imagehight+" BORDER=0><BR CLEAR=LEFT><BR>");
				out.println("</P>");
			out.println("</TD>");
			out.println("<TD WIDTH=50% ALIGN=LEFT>");
			out.println("<h2><i>Air Balancing System</i></h2>");
			out.println("</TD>");
		out.println("</TR>");
	out.println("</THEAD>");
out.println("</TABLE>");
out.println("</BODY>");
out.println("</HTML>");
out.println("<br><br><br><br><CENTER>");
out.println("Copyright 2002, 2003, 2004, 2005 - Christopher J. Molnar <br>All Rights Reserved");
out.println("<br>Version: "+doVersionInfo_VNumber()+" - Compiled: "+doFormatDate(getDate(doVersionInfo_VDate()))+"<br>");
/* #system("uptime"); */

out.println("</CENTER>");

	}

  private void doStyleSheet(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	out.println("<style type=\"text/css\"><!-- body,table { color: #000000; background-color: #ffffff; margin-left: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; font-size: .9em; font-family: Arial, Helvetica, sans-serif; } ");
	out.println(" .tableoffcolor { font-family: Arial, Helvetica, sans-serif; font-size: 1em; background-color: #A0B8C8; height: auto; width: auto; margin: 1em; padding: 1em; border: thin solid; } ");
	out.println("--> </style>");
	}

public void doMenuStyleSheet(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
	      throws Exception
      {
      out.println("<style type=\"text/css\"><!-- body,table { color: #000000; background-color: #A0B8C8; margin-left: 2px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; font-size: .9em; font-family: Arial, Helvetica, sans-serif; } ");
      out.println(" .tableoffcolor { font-family: Arial, Helvetica, sans-serif; font-size: 1em; background-color: #A0B8C8; height: auto; width: auto; margin: 1em; padding: 1em; border: thin solid; } ");
      out.println("--> </style>");
      }


  private void doListCustomers(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
		String custstart = req.getParameter("custstart");
		String custstop = req.getParameter("custstop");
		int counter=0;
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Select Customer</title>");
		out.println("</head>");
		out.println("<BODY TEXT=#000000 LINK=#0000ff VLINK=#000080 BGCOLOR=#ffffff> ");
		out.println("<a href="+classdir+"UniCash?action=addcustomer&custstart="+custstart+"&custstop="+custstop+"&reqsource=ServInstall>Add a Customer</a>");
		out.println("<table border=0 width=100%>");
		out.println("<th>Name</th><th>Address</th><th>City</th><th>State</th>");

                Vector v;
                v = UniCustomer.getAllItems(con,custstart,custstop);

                for (int i = 0 ; i < v.size(); i++)
                {
                       	UniCustomer t = (UniCustomer) v.elementAt(i);
			String custname = t.getCustomerName();
                        String address1 = t.getAddress1();
			String city = t.getCity();
			String state = t.getState();
			String custnum = t.getCusNum();
			counter++;
			if (counter==1) 
				{
                       	out.println("<tr BGCOLOR=#A0B8C8>");
				}
			if (counter==2) 
				{
                       	out.println("<tr>");
			counter=0;	
				}
			
                        out.println("<td><a href="+classdir+"ServAirBal?action=showcustdetail&custnum="+custnum+">");
                        out.println(custname+"</a></td>");
                        out.println("<td>");
                        out.println(address1+"</td>");
                        out.println("<td>");
                        out.println(city+"</td>");
                        out.println("<td>" + state + "</td>");
                        out.println("</tr>");
                }

                out.println("</table>");
		out.println("</table>");
		out.println("<P><P>");
		out.println("<a href="+classdir+"UniCash?action=addcustomer&custstart="+custstart+"&custstop="+custstop+"&reqsource=ServInstall>Add a Customer</a>");

	}

private void doShowCustDetail(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
		throws Exception
        {
		String tcustnum = req.getParameter("custnum");
		String custstart = req.getParameter("custstart");
		String custstop = req.getParameter("custstop");
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
		String jobnum=null;
		String price=null;
		String notes=null;
		int icrecnum=0;
		int ccrecnum=0;
		String icallslip=null;
		double totinvestment=0.00;
		String icustnum=null;
		int recnum=0;
		String description=null;
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
		String wsdate=null;
		String custsite=null;
		String sitenum=null;
		int wscustnum=0;
		int wsnum=0;
		double ptotal=0;
                int intcustnum = Integer.parseInt(tcustnum);

	out.println("<html>");
	out.println("<head>");
	out.println("<title>Customer Detail</title>");
	doStyleSheet(req, res, out, session);
	//out.println("<style type=\"text/css\"><!-- body,table { color: #000000; background-color: #ffffff; margin-left: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; font-size: .9em; font-family: Arial, Helvetica, sans-serif; } ");
	//out.println(" .tableoffcolor { font-family: Arial, Helvetica, sans-serif; font-size=1em; background-color: #A0B8C8; } ");


	//out.println("--> </style>");
	out.println("</head>");
	out.println("<BODY>");


	out.println("<table border=0 width=100%>");
	out.println("<tr><td width=\"60%\">");
	out.println("<h3>Customer Detail</h3>");
	out.println("<table border=1 width=\"100%\">");
 	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM customers where custnum="+tcustnum+"");

		 while(rs.next())
                {
		custnum=rs.getInt("custnum");
		cname=rs.getString("cname");
		address1=rs.getString("address1");
		address2=rs.getString("address2");
		city =rs.getString("city");
		state=rs.getString("state");
		zip=rs.getString("zip");
		homephone=rs.getString("homephone");
		altphone=rs.getString("altphone");
		cust_notes=rs.getString("cust_notes");
		cemail=rs.getString("cemail");
		custsite=rs.getString("custsite");
		sitenum=rs.getString("sitenum");
		
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
		out.println("<tr><td>Customer Notes</td><td>"+cust_notes+"</td></tr>");
		out.println("</table>");
		}
		out.println("</td>");
		out.println("<td valign=top>");
		out.println("<table border=0 width=\"95%\" valign=top class=\"tableoffcolor\" id=\"custforms\">");
		out.println("<tr><td><h3>Customer Forms</h3></td></tr>");

		out.println("<tr><td><a href="+classdir+"UniCash?action=printspaceheater&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=_blank>Print Space Heater Liability Form</a><br></td></tr>");
		out.println("<tr><td><a href="+classdir+"UniCash?action=printaccover&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=_blank>Print AC Cover Disclaimer Form</a><br></td></tr>");
		out.println("<tr><td><a href="+classdir+"UniCash?action=printiaqdisc&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=_blank>Print IAQ Disclaimer Form</a><br></td></tr>");
		out.println("<tr><td><a href="+classdir+"UniCash?action=printelist&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+" target=_blank>Print Equipment List</a><br></td></tr>");
		out.println("<tr><td> </td></tr>");
		out.println("<tr><td> </td></tr>");
		out.println("<tr><td> </td></tr>");
		out.println("<tr><td> </td></tr>");
		out.println("<tr><td> </td></tr>");
		out.println("<tr><td> </td></tr>");
		out.println("<tr><td></td></tr>");
		out.println("</table>");



		out.println("</td></tr>");
		out.println("</table>");

		out.println("<br>");
		out.println("<a href="+classdir+"UniCash?action=editcustomer&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+"&reqsource=ServInstall>Edit Customer Info</a>");
	out.println("<P><P>");
	out.println("<h3>Customer Equipment List</h3>");

                Vector v;
                v = UniEquip.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>Brand</th><th>Type</th><th>Model</th><th>Serial</th><th>Filter</th><th>Notes</th><th>Delete<br>(no second chance)</th>");

		int counter=0;
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

                out.println("<tr><td><a href=\""+classdir+"UniCash?action=editservproposal&quotenum="+propnum+"&&custnum="+qcustnum+"\">"+propnum+"</a></td><td>"+pdate+"</td><td>"+psummary+"</td><td>"+NumberFormat.getCurrencyInstance().format(totinvestment)+"</td><td>"+qstatus+"</td><td><a href="+classdir+"UniCash?action=printservproposal&propnum="+propnum+"&custnum="+custnum+" target=_blank>Print Format</a></td><td><a href="+classdir+"UniCash?action=delservproprec&custnum="+custnum+"&propnum="+propnum+">Delete</a></td></tr>");
                }
out.println("</table><br><br>");
		out.println("<a href="+classdir+"UniCash?action=addservproposal&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Service Proposal to Customer File</a>");
	}
}//ServAirBal
