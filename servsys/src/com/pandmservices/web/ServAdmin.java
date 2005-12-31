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

public class ServAdmin extends UniCash
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
			out.println("<h2><i>Installation Entry and Reporting System</i></h2>");
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

}//ServAdmin
