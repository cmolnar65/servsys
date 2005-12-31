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

public class ServInstall extends UniCash
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

			else if (action.equalsIgnoreCase("listcustomers"))
	                        {
                                doListCustomers(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("resources"))
	                        {
                                doListResources(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("showresource"))
	                        {
                                doShowResource(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("showcustdetail"))
	                        {
                                doShowCustDetail(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("savejobstatus"))
	                        {
                                doSaveJobStatus(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("editstartup"))
	                        {
                                doEditStartUp(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("startupprint"))
	                        {
                                doPrintStartUp(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("savestartup"))
	                        {
                                doSaveStartUp(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("addstartup"))
	                        {
                                doEditStartUp(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("updatestartup"))
	                        {
                                doUpdateStartUp(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("deljobstatus"))
	                        {
                                doDeleteJobStatus(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("deljobcomprec"))
	                        {
                                doDeleteJobCompRec(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("addjobstatus"))
	                        {
                                doAddStatus(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("updatecustnote"))
	                        {
                                doUpdateCustNote(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("shownote"))
	                        {
                                doEditNote(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("addnote"))
	                        {
                                doAddNote(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("savecustnote"))
	                        {
                                doSaveCustNote(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("delnoterec"))
	                        {
                                doDeleteCustNote(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("deljobrec"))
	                        {
                                doDeleteJobRecord(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("editjobcomp"))
	                        {
                                doEditJobComplete(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("addjobcomp"))
	                        {
                                doAddJobComplete(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("addjob"))
	                        {
                                doAddJob(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("savejob"))
	                        {
                                doSaveJob(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("showjob"))
	                        {
                                doShowJob(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("printjobcompletion"))
	                        {
                                doPrintJobCompletion(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("updatejobcomplete"))
	                        {
                                doUpdateJobCompletion(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("savejobcomplete"))
	                        {
                                doSaveJobCompletion(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("editjob"))
	                        {
                                doEditJob(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("updatejob"))
	                        {
                                doUpdateJob(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("showhomepage"))
	                        {
                                doShowHomePage(req, res, out, session);
	                        }								
			else if (action.equalsIgnoreCase("addresource"))
	                        {
                                doAddResource(req, res, out, session);
	                        }
			else if (action.equalsIgnoreCase("saveresource"))
	                        {
                                doSaveResource(req, res, out, session);
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

	
	out.println("<br><a href="+classdir+"ServInstall?action=menu&menu=customers target=nav>Customers</a><br>");

	if (menu.equalsIgnoreCase("customers")) {
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=a&custstop=b target=phpmain>Customers A</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=b&custstop=c target=phpmain>Customers B</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=c&custstop=e target=phpmain>Customers C-D</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=e&custstop=g target=phpmain>Customers E-F</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=g&custstop=i target=phpmain>Customers G-H</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=i&custstop=l target=phpmain>Customers I-K</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=l&custstop=m target=phpmain>Customers L</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=m&custstop=n target=phpmain>Customers M</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=n&custstop=o target=phpmain>Customers N</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=o&custstop=p target=phpmain>Customers O</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=p&custstop=q target=phpmain>Customers P</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=q&custstop=s target=phpmain>Customers Q-R</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=s&custstop=t target=phpmain>Customers S</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=t&custstop=u target=phpmain>Customers T</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"ServInstall?action=listcustomers&custstart=u&custstop=ZZ target=phpmain>Customers U-Z</a><br>");
}

	out.println("<br><a href="+classdir+"ServInstall?action=resources target=phpmain>Resources</a><br>");
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
        


private void doUpdateStartUp(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {

	String icrecnum = req.getParameter("icrecnum");
        int crecnum = Integer.parseInt(icrecnum);
	String tcustnum = req.getParameter("custnum");
        int custnum = Integer.parseInt(tcustnum);
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String callslip = req.getParameter("callslip");
        String idate = req.getParameter("idate");
        String tequip1 = req.getParameter("equip1");
        String tequip2 = req.getParameter("equip2");
        int equip1 = Integer.parseInt(tequip1);
        int equip2 = Integer.parseInt(tequip2);
        String mbearing = req.getParameter("mbearing");
        String mblades = req.getParameter("mblades");
        String ecoil = req.getParameter("ecoil");
        String dline = req.getParameter("dline");
        String dpan = req.getParameter("dpan");
        String ielect = req.getParameter("ielect");
        String mcap = req.getParameter("mcap");
        String hstrips = req.getParameter("hstrips");
        String gpreassures = req.getParameter("gpreassures");
        String ignition = req.getParameter("ignition");
        String burners = req.getParameter("burners");
        String limits = req.getParameter("limits");
        String flame = req.getParameter("flame");
        String dinducer = req.getParameter("dinducer");
        String humidifier = req.getParameter("humidifier");
        String atemp = req.getParameter("atemp");
        String tempsplit = req.getParameter("tempsplit");
        String crlaa = req.getParameter("crlaa");
        String crlar = req.getParameter("crlar");
        String ccapr = req.getParameter("ccapr");
        String ccapa = req.getParameter("ccapa");
        String frlaa = req.getParameter("frlaa");
        String frlar = req.getParameter("frlar");
        String fcapr = req.getParameter("fcapr");
	String fcapa = req.getParameter("fcapa");
        String fbearing = req.getParameter("fbearing");
        String coilcond = req.getParameter("coilcond");
        String cleancoil = req.getParameter("cleancoil");
        String contactor = req.getParameter("contactor");
        String scap = req.getParameter("scap");
        String ctimedelay = req.getParameter("ctimedelay");
        String oelectrical = req.getParameter("oelectrical");
        String comppad = req.getParameter("comppad");
        String recommendations = req.getParameter("recommendations");
        String services = req.getParameter("services");
        String dueamount = req.getParameter("dueamount");
        String paidamount = req.getParameter("paidamount");
        String lpres = req.getParameter("lpres");
        String hpres = req.getParameter("hpres");
        String startco = req.getParameter("startco");
        String runco =req.getParameter("runco");
        String stacktemp = req.getParameter("stacktemp");
        String ventpipe = req.getParameter("ventpipe");
        String oleaks = req.getParameter("oleaks");
        String ochimney = req.getParameter("ochimney");
        String opump = req.getParameter("opump");
        String ocontrols = req.getParameter("ocontrols");
        String otstat = req.getParameter("otstat");
        String oprimesafety = req.getParameter("oprimesafety");
        String osafetime = req.getParameter("osafetime");
        String oigntrans = req.getParameter("oigntrans");
        String olubemotors = req.getParameter("olubemotors");
        String ofulemix = req.getParameter("ofulemix");
        String onozzle = req.getParameter("onozzle");
        String ogross = req.getParameter("ogross");
        String onet = req.getParameter("onet");
        String osmoke = req.getParameter("osmoke");
        String oco2 = req.getParameter("oco2");
        String oo2 = req.getParameter("oo2");
        String oco = req.getParameter("oco");
        String oexcessair = req.getParameter("oexcessair");
        String obreachdraft = req.getParameter("obreachdraft");
        String ofiredraft = req.getParameter("ofiredraft");
        String oeffic = req.getParameter("oeffic");
        String orating = req.getParameter("orating");
        String opower = req.getParameter("opower");
        String otank = req.getParameter("otank");
        String otcond = req.getParameter("otcond");
        String odheat = req.getParameter("odheat");
        String ocombustion = req.getParameter("ocombustion");
        String oelectrodes = req.getParameter("oelectrodes");
        String obrush = req.getParameter("obrush");
        String ofilters = req.getParameter("ofilters");
        String notes = req.getParameter("notes");
        String filter = req.getParameter("filter");
        String tfollowup = req.getParameter("followup");
	String airflow=req.getParameter("airflow");
	String spres_rated=req.getParameter("spres_rated");
	String spres_return=req.getParameter("spres_return");
	String spres_supply=req.getParameter("spres_supply");
        //int followup = Integer.parseInt(tfollowup);	
	int followup=0;
	String g_filter =req.getParameter("g_filter");
	String g_electrical =req.getParameter("g_electrical");
	String g_looppres =req.getParameter("g_looppres");
	String g_cleancoil =req.getParameter("g_cleancoil");
	String g_cleandrain =req.getParameter("g_cleandrain");
	String g_pansensor =req.getParameter("g_pansensor");
	String g_cleancomp =req.getParameter("g_cleancomp");
	String g_cleanunit =req.getParameter("g_cleanunit");
	String g_oilblower =req.getParameter("g_oilblower");
	String g_cleanpump =req.getParameter("g_cleanpump");
	String g_tsplit =req.getParameter("g_tsplit");
	String g_pampr =req.getParameter("g_pampr");
	String g_pampa =req.getParameter("g_pampa");
	String g_compar =req.getParameter("g_compar");
	String g_compaa =req.getParameter("g_compaa");
	String g_bampr =req.getParameter("g_bampr");
	String g_bampa =req.getParameter("g_bampa");
	String g_pdrop =req.getParameter("g_pdrop");
	String sductsize =req.getParameter("sductsize");
	String rductsize =req.getParameter("rductsize");

               StartUp.UpdateItem(con, crecnum, custnum, callslip, doFormatDateDb(getDateDb(idate)), equip1, equip2, mbearing, mblades, ecoil, dline, dpan, ielect, mcap, hstrips, filter, gpreassures, ignition, burners, limits, flame, dinducer,humidifier, atemp, tempsplit, crlaa, crlar, ccapr, ccapa, frlaa,frlar, fcapr, fcapa, fbearing, coilcond, cleancoil, contactor,scap, ctimedelay, oelectrical, comppad, recommendations, services, dueamount, paidamount, notes,lpres, hpres, startco, runco, stacktemp, ventpipe, oleaks, ochimney, opump, ocontrols, otstat, oprimesafety, osafetime, oigntrans, olubemotors, ofulemix, onozzle, ogross, osmoke, onet, oco2, oo2, oco, oexcessair, obreachdraft, ofiredraft, oeffic,orating, opower,otank,otcond, odheat, ocombustion, oelectrodes, obrush, ofilters, followup, airflow, spres_rated, spres_return, spres_supply, g_filter, g_electrical, g_looppres, g_cleancoil, g_cleandrain, g_pansensor, g_cleancomp, g_cleanunit, g_oilblower, g_cleanpump, g_tsplit, g_pampr, g_pampa, g_compar, g_compaa, g_bampr, g_bampa, g_pdrop, sductsize, rductsize );
                out.println("Your item has been updated in the database<br>");
                res.sendRedirect(""+classdir+"ServInstall?action=showcustdetail&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+"");
}
private void doSaveStartUp(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {

	String tcustnum = req.getParameter("custnum");
        int custnum = Integer.parseInt(tcustnum);
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String callslip = req.getParameter("callslip");
        String idate = req.getParameter("idate");
        String tequip1 = req.getParameter("equip1");
        String tequip2 = req.getParameter("equip2");
        int equip1 = Integer.parseInt(tequip1);
        int equip2 = Integer.parseInt(tequip2);
        String mbearing = req.getParameter("mbearing");
        String mblades = req.getParameter("mblades");
        String ecoil = req.getParameter("ecoil");
        String dline = req.getParameter("dline");
        String dpan = req.getParameter("dpan");
        String ielect = req.getParameter("ielect");
        String mcap = req.getParameter("mcap");
        String hstrips = req.getParameter("hstrips");
        String gpreassures = req.getParameter("gpreassures");
        String ignition = req.getParameter("ignition");
        String burners = req.getParameter("burners");
        String limits = req.getParameter("limits");
        String flame = req.getParameter("flame");
        String dinducer = req.getParameter("dinducer");
        String humidifier = req.getParameter("humidifier");
        String atemp = req.getParameter("atemp");
        String tempsplit = req.getParameter("tempsplit");
        String crlaa = req.getParameter("crlaa");
        String crlar = req.getParameter("crlar");
        String ccapr = req.getParameter("ccapr");
        String ccapa = req.getParameter("ccapa");
        String frlaa = req.getParameter("frlaa");
        String frlar = req.getParameter("frlar");
        String fcapr = req.getParameter("fcapr");
	String fcapa = req.getParameter("fcapa");
        String fbearing = req.getParameter("fbearing");
        String coilcond = req.getParameter("coilcond");
        String cleancoil = req.getParameter("cleancoil");
        String contactor = req.getParameter("contactor");
        String scap = req.getParameter("scap");
        String ctimedelay = req.getParameter("ctimedelay");
        String oelectrical = req.getParameter("oelectrical");
        String comppad = req.getParameter("comppad");
        String recommendations = req.getParameter("recommendations");
        String services = req.getParameter("services");
        String dueamount = req.getParameter("dueamount");
        String paidamount = req.getParameter("paidamount");
        String lpres = req.getParameter("lpres");
        String hpres = req.getParameter("hpres");
        String startco = req.getParameter("startco");
        String runco =req.getParameter("runco");
        String stacktemp = req.getParameter("stacktemp");
        String ventpipe = req.getParameter("ventpipe");
        String oleaks = req.getParameter("oleaks");
        String ochimney = req.getParameter("ochimney");
        String opump = req.getParameter("opump");
        String ocontrols = req.getParameter("ocontrols");
        String otstat = req.getParameter("otstat");
        String oprimesafety = req.getParameter("oprimesafety");
        String osafetime = req.getParameter("osafetime");
        String oigntrans = req.getParameter("oigntrans");
        String olubemotors = req.getParameter("olubemotors");
        String ofulemix = req.getParameter("ofulemix");
        String onozzle = req.getParameter("onozzle");
        String ogross = req.getParameter("ogross");
        String onet = req.getParameter("onet");
        String osmoke = req.getParameter("osmoke");
        String oco2 = req.getParameter("oco2");
        String oo2 = req.getParameter("oo2");
        String oco = req.getParameter("oco");
        String oexcessair = req.getParameter("oexcessair");
        String obreachdraft = req.getParameter("obreachdraft");
        String ofiredraft = req.getParameter("ofiredraft");
        String oeffic = req.getParameter("oeffic");
        String orating = req.getParameter("orating");
        String opower = req.getParameter("opower");
        String otank = req.getParameter("otank");
        String otcond = req.getParameter("otcond");
        String odheat = req.getParameter("odheat");
        String ocombustion = req.getParameter("ocombustion");
        String oelectrodes = req.getParameter("oelectrodes");
        String obrush = req.getParameter("obrush");
        String ofilters = req.getParameter("ofilters");
        String notes = req.getParameter("notes");
        String filter = req.getParameter("filter");
        String tfollowup = req.getParameter("followup");
	String airflow=req.getParameter("airflow");
	String spres_rated=req.getParameter("spres_rated");
	String spres_return=req.getParameter("spres_return");
	String spres_supply=req.getParameter("spres_supply");
        //int followup = Integer.parseInt(tfollowup);	
	int followup=0;
	String g_filter =req.getParameter("g_filter");
	String g_electrical =req.getParameter("g_electrical");
	String g_looppres =req.getParameter("g_looppres");
	String g_cleancoil =req.getParameter("g_cleancoil");
	String g_cleandrain =req.getParameter("g_cleandrain");
	String g_pansensor =req.getParameter("g_pansensor");
	String g_cleancomp =req.getParameter("g_cleancomp");
	String g_cleanunit =req.getParameter("g_cleanunit");
	String g_oilblower =req.getParameter("g_oilblower");
	String g_cleanpump =req.getParameter("g_cleanpump");
	String g_tsplit =req.getParameter("g_tsplit");
	String g_pampr =req.getParameter("g_pampr");
	String g_pampa =req.getParameter("g_pampa");
	String g_compar =req.getParameter("g_compar");
	String g_compaa =req.getParameter("g_compaa");
	String g_bampr =req.getParameter("g_bampr");
	String g_bampa =req.getParameter("g_bampa");
	String g_pdrop =req.getParameter("g_pdrop");
	String sductsize =req.getParameter("sductsize");
	String rductsize =req.getParameter("rductsize");

               StartUp.AddItem(con, custnum, callslip, doFormatDateDb(getDateDb(idate)), equip1, equip2, mbearing, mblades, ecoil, dline, dpan, ielect, mcap, hstrips, filter, gpreassures, ignition, burners, limits, flame, dinducer,humidifier, atemp, tempsplit, crlaa, crlar, ccapr, ccapa, frlaa,frlar, fcapr, fcapa, fbearing, coilcond, cleancoil, contactor,scap, ctimedelay, oelectrical, comppad, recommendations, services, dueamount, paidamount, notes,lpres, hpres, startco, runco, stacktemp, ventpipe, oleaks, ochimney, opump, ocontrols, otstat, oprimesafety, osafetime, oigntrans, olubemotors, ofulemix, onozzle, ogross, osmoke, onet, oco2, oo2, oco, oexcessair, obreachdraft, ofiredraft, oeffic,orating, opower,otank,otcond, odheat, ocombustion, oelectrodes, obrush, ofilters, followup, airflow, spres_rated, spres_supply, spres_return, g_filter, g_electrical, g_looppres, g_cleancoil, g_cleandrain, g_pansensor, g_cleancomp, g_cleanunit, g_oilblower, g_cleanpump, g_tsplit, g_pampr, g_pampa, g_compar, g_compaa, g_bampr, g_bampa, g_pdrop, sductsize, rductsize );
                out.println("Your item has been updated in the database<br>");
                res.sendRedirect(""+classdir+"ServInstall?action=showcustdetail&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+"");
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



private void doSaveCustomerEntry(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String custstart = req.getParameter("custstart");
                String custstop = req.getParameter("custstop");
                String cname = req.getParameter("cname");
                String address1 = req.getParameter("address1");
                String address2 = req.getParameter("address2");
                String city = req.getParameter("city");
                String state = req.getParameter("state");
                String zip = req.getParameter("zip");
                String homephone = req.getParameter("homephone");
                String altphone = req.getParameter("altphone");
                String cust_notes = req.getParameter("cust_notes");
                String cemail = req.getParameter("cemail");
		String custsite=req.getParameter("custsite");
		String sitenum=req.getParameter("sitenum");
		String custtype=req.getParameter("custtype");
                UniCustomer.addCustomer(con, cname, address1, address2, city, state, zip, homephone, altphone, cust_notes,cemail,custsite,sitenum,custtype);
                out.println("Your item has been added to the database");
		res.sendRedirect(""+classdir+"UniCash?action=listcustomers&custstart="+custstart+"&custstop="+custstop);
            }



  private void doListResources(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
		int counter=0;
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Resource List</title>");
		out.println("</head>");
		out.println("<BODY TEXT=#000000 LINK=#0000ff VLINK=#000080 BGCOLOR=#ffffff> ");
		out.println("<a href="+classdir+"ServInstall?action=addresource&reqsource=ServInstall>Add a Resource</a>");
		out.println("<table border=0 width=100%>");
		out.println("<th>Name</th><th>Skill Set</th><th>Lead<br>/Helper</th>");

                Vector v;
                v = InsResource.getAllItems(con);

                for (int i = 0 ; i < v.size(); i++)
                {
                       	InsResource t = (InsResource) v.elementAt(i);
			String name = t.getResName();
                        String skillset = t.getSkillSet();
			String leadhelper = t.getLeadHelp();
			int recnum = t.getRecNum();
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
			
                        out.println("<td><a href="+classdir+"ServInstall?action=showresource&recnum="+recnum+">");
                        out.println(name+"</a></td>");
                        out.println("<td>");
                        out.println(skillset+"</td>");
                        out.println("<td>");
                        out.println(leadhelper+"</td>");
                        out.println("</tr>");
                }

                out.println("</table>");
		out.println("</table>");
		out.println("<P><P>");
		out.println("<a href="+classdir+"ServInstall?action=addresource&reqsource=ServInstall>Add a Resource</a>");
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
			
                        out.println("<td><a href="+classdir+"ServInstall?action=showcustdetail&custnum="+custnum+">");
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


out.println("<P><P>");
out.println("<h3>Preventative Agreements</h3>");
 	Statement stmt5 = con.createStatement();
	ResultSet rs5 = stmt5.executeQuery("SELECT * FROM pagreement where custnum="+tcustnum+"");
	out.println("<table border=1 width=100%>");
        out.println("<th>Contract</th><th>Start Date</th><th>End Date</th><th>Cost</th>");
	while(rs5.next())
		{
		pcontnum=rs5.getInt("contnum");
		pcustnum=rs5.getString("custnum");
		pstartdate=doFormatDate(getDate(rs5.getString("startdate")));
		penddate=doFormatDate(getDate(rs5.getString("enddate")));
		pcost=rs5.getString("cost");
		pnotes=rs5.getString("notes");
                out.println("<tr><td><a href=\""+classdir+"UniCash?action=editprevagreement&contnum="+pcontnum+"&reqsource=ServInstall&custnum="+pcustnum+"\">"+pcontnum+"</a></td><td>"+pstartdate+"</td><td>"+penddate+"</td><td>"+pcost+"</td><td><a href="+classdir+"UniCash?action=printprevagreement&contnum="+pcontnum+"&custnum="+pcustnum+"&reqsource=ServInstall target=_blank>Print Format</a></td></tr>");
                }
out.println("</table><br><br>");
		out.println("<a href="+classdir+"UniCash?action=addprevagreement&custnum="+custnum+"&custstart="+custstart+"&reqsource=ServInstall&custstop="+custstop+">Add Preventative Agreement to Customer File</a>");

out.println("<P><P>");
out.println("<h3>Install Jobs</h3>");
                Vector vj;
                vj = InsJobs.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>Job #</th><th>Summary</th><th>Billable</th><th>Status</th>");
		counter=0;
                for (int i = 0 ; i < vj.size(); i++)
                {
                InsJobs tj = (InsJobs) vj.elementAt(i);
		recnum=tj.getRecNum();
		jobnum=tj.getJobNum();
		String tprice=tj.getPrice();
		double fprice = Double.parseDouble(tprice);
		description=tj.getDesc();
		//qstatus=ts.getQStatus();
                out.println("<tr><td><a href=\""+classdir+"ServInstall?action=showjob&recnum="+recnum+"&custnum="+tcustnum+"\">"+jobnum+"</a></td><td>"+description+"</td><td>"+NumberFormat.getCurrencyInstance().format(fprice)+"</td><td>"+qstatus+"</td><td><a href="+classdir+"ServInstall?action=deljobrec&custnum="+custnum+"&recnum="+recnum+">Delete</a></td></tr>");
                }
out.println("</table><br><br>");

		out.println("<a href="+classdir+"ServInstall?action=addjob&custnum="+custnum+"&reqsource=ServInstall>Add Job to Customer File</a>");
out.println("<P><P>");

//CJM - STARTUPS
	out.println("<P><P>");
	out.println("<h3>Equipment Start Up Forms</h3>");

 	Statement stmt3 = con.createStatement();
	ResultSet rs3 = stmt3.executeQuery("SELECT * FROM startup where custnum="+tcustnum+"");
		out.println("<table border=1 width=50%>");
		out.println("<th>Job</th><th>Date</th><th></th>");
		while(rs3.next())
                {
		icrecnum=rs3.getInt("crecnum");
		icallslip=rs3.getString("callslip");
		icustnum=rs3.getString("custnum");
		idate=doFormatDate(getDate(rs3.getString("idate")));
                out.println("<tr><td><a href="+classdir+"ServInstall?action=editstartup&icrecnum="+icrecnum+"&custnum="+icustnum+"&custstart="+custstart+"&custstop="+custstop+">"+icallslip+"</a></td><td>"+idate+"</td><td><a href="+classdir+"ServInstall?action=startupprint&crecnum="+icrecnum+"&custnum="+icustnum+" target=\"_blank\">Print Format</a></td><td><a href="+classdir+"ServInstall?action=delstartup&crecnum="+icrecnum+"&custnum="+icustnum+">D</a>elete<td></tr>");
                }

out.println("</table><br><br>");

	out.println("<a href="+classdir+"ServInstall?action=addstartup&custnum="+custnum+"&reqsource=ServInstall>Add Start Up to Customer File</a>");
out.println("<P><P>");

out.println("<P><P>");
out.println("<h3>Customer Notes</h3>");
                Vector vn;
                vn = ServCustNotes.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>Note #</th><th>Note Date</th><th>Note Subject</th>");
		counter=0;
                for (int i = 0 ; i < vn.size(); i++)
                {
                ServCustNotes tn = (ServCustNotes) vn.elementAt(i);
		int notenum=tn.getNoteNum();
		String notedate=doFormatDate(getDate(tn.getNoteDate()));
		//String tnotedate=tn.getNoteDate();
		String notesubj=tn.getNoteSubj();
                out.println("<tr><td><a href=\""+classdir+"ServInstall?action=shownote&notenum="+notenum+"&custnum="+tcustnum+"\">"+notenum+"</a></td><td>"+notedate+"</td><td>"+notesubj+"</td><td><a href="+classdir+"ServInstall?action=delnoterec&custnum="+custnum+"&notenum="+notenum+">Delete</a></td></tr>");
                }
out.println("</table><br><br>");

		out.println("<a href="+classdir+"ServInstall?action=addnote&custnum="+custnum+">Add Note to Customer File</a>");
out.println("<P><P>");

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


out.println("<P><P>");
out.println("<h3>Sales Proposals</h3>");

                Vector vv;
                vv = UniQuotes.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>Proposal #</th><th>Date</th><th>Summary</th><th>Cost</th><th>Status</th>");
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

                out.println("<tr><td><a href=\""+classdir+"UniCash?action=editproposal&quotenum="+propnum+"&&custnum="+qcustnum+"\">"+propnum+"</a></td><td>"+pdate+"</td><td>"+psummary+"</td><td>"+NumberFormat.getCurrencyInstance().format(totinvestment)+"</td><td>"+qstatus+"</td><td><a href="+classdir+"UniCash?action=printproposal&propnum="+propnum+"&custnum="+custnum+" target=_blank>Print Format</a></td><td><a href="+classdir+"UniCash?action=delproprec&custnum="+custnum+"&propnum="+propnum+">Delete</a></td></tr>");
                }
out.println("</table><br><br>");
		out.println("<a href="+classdir+"UniCash?action=addproposal&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Proposal to Customer File</a>");


out.println("<P><P>");
out.println("<h3>Site Survey</h3>");
 	Statement stmtp = con.createStatement();
	ResultSet rsp = stmtp.executeQuery("SELECT * FROM custsurvey where custnum="+custnum+"");
	out.println("<table border=1 width=100%>");
        out.println("<th>Number</th><th>Date</th>");
	while(rsp.next())
		{
		int precnum=rsp.getInt("recnum");
		pcustnum=rsp.getString("custnum");
		String psdate=doFormatDate(getDate(rsp.getString("sdate")));
                out.println("<tr><td><a href=\""+classdir+"UniCash?action=editsurvey&recnum="+precnum+"&custnum="+pcustnum+"\">"+precnum+"</a></td><td>"+psdate+"</td></td><td><a href="+classdir+"UniCash?action=printsurvey&recnum="+precnum+"&custnum="+pcustnum+" target=_blank>Print Format</a></td></tr>");
                }
	out.println("</table>");
		out.println("<br><br><a href="+classdir+"UniCash?action=addsurvey&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Survey to Customer File</a><br><br>");
out.println("<P><P>");
out.println("<h3>Pricing Worksheets</h3>");

                Vector vw;
                vw = UniWorksheet.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>Worksheet #</th><th>Date</th><th>Summary</th>");
		counter=0;
                for (int i = 0 ; i < vw.size(); i++)
                {
                UniWorksheet tw = (UniWorksheet) vw.elementAt(i);
		wsnum=tw.getWsRec();
		wscustnum=tw.getCrec();
		wsdate=doFormatDate(getDate(tw.getWsDate()));
		wssummary=tw.getWsDesc();


                out.println("<tr><td><a href=\""+classdir+"UniCash?action=editws&wsnum="+wsnum+"&&custnum="+wscustnum+"\">"+wsnum+"</a></td><td>"+wsdate+"</td><td>"+wssummary+"</td><td><a href="+classdir+"UniCash?action=printworksheet&wsnum="+wsnum+"&custnum="+wscustnum+" target=_blank>Print Format</a></td><td><a href="+classdir+"UniCash?action=delwsrec&custnum="+custnum+"&wsnum="+wsnum+">Delete</a></td></tr>");
                }
out.println("</table><br><br>");
		out.println("<a href="+classdir+"UniCash?action=addworksheet&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Worksheet to Customer File</a>");


out.println("</body>");
out.println("</html>");
	
out.println("</body>");
out.println("</html>");
	
	}


  private void doShowResource(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {

	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");

	}

  private void doAddResource(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {

	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");


	out.println("<html>");
	out.println("<head>");
	out.println("<title>Add Resource</title>");
	out.println("</head>");
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=saveresource&reqsource="+reqsource+"\" name=\"addresource\">");
	out.println("<table><tr><td>");
	out.println("Resource Name        :");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"rname\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Skill Set  :");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"skillset\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Lead / Helper  :");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"leadhelper\" size=\"40\">");
	out.println("</td></tr></table>");
	out.println("<p> <CENTER>");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Save\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
	}


  private void doAddStatus(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");
	String custnum = req.getParameter("custnum");
	String recnum = req.getParameter("recnum");
	String jobnum = req.getParameter("jobnum");
	String jobstatus = "-";
	int counter=0;
 	Format formatter;
	Calendar now = Calendar.getInstance();
        Date date = new Date(); 
	formatter = new SimpleDateFormat("MM-dd-yyyy");
        String s = formatter.format(date);
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Add Job Status</title>");
	out.println("</head>");
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=savejobstatus&reqsource="+reqsource+"\" name=\"addjobstatus\">");
	out.println("<table><tr>");
	out.println("<td>Job Status:</td>");
	out.println("<td align=\"left\"><select width=\"50\" name=\"jobstatus\">");
		Vector v;
               v = SupJobStatus.getAllItems(con);
		out.println("<option value=\"-\">-</option>");
		counter=0;
                for (int i = 0 ; i < v.size(); i++)
                {
                SupJobStatus t = (SupJobStatus) v.elementAt(i);
		out.println("<option value="+t.getAnswer()+">"+t.getAnswer()+"</option>");
		}
		out.println("<option selected>"+jobstatus+"</option>");
		out.println("</select></td></tr>");
	out.println("<tr><td>");
	out.println("Status Date:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"statusdate\" size=\"40\" value=\""+s+"\">");
	out.println("</td></tr><tr><td>");
	out.println("Comments:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"description\" size=\"40\">");
	out.println("</td></tr></table>");
	out.println("<p> <CENTER>");
	out.println("<input type=\"hidden\" name=\"custnum\" value=\""+custnum+"\">");
	out.println("<input type=\"hidden\" name=\"jobrecnum\" value=\""+recnum+"\">");
	out.println("<input type=\"hidden\" name=\"reqsource\" value=\"ServInstall\">");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Save\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
	}


private void doDeleteJobCompRec(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String tjrecnum = req.getParameter("jrecnum");
                String trecnum = req.getParameter("recnum");
                String tcustnum= req.getParameter("custnum");
                int jrecnum = Integer.parseInt(tjrecnum);
                int recnum = Integer.parseInt(trecnum);
                int custnum = Integer.parseInt(tcustnum);
		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                ServJobComp.deleteItem(con, tjrecnum);
		res.sendRedirect(""+classdir+"ServInstall?action=showjob&custnum="+tcustnum+"&recnum="+trecnum+"");
            }


private void doDeleteJobStatus(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String trecnum = req.getParameter("jrecnum");
                String tsrecnum = req.getParameter("srecnum");
                String tcustnum= req.getParameter("custnum");
                int recnum = Integer.parseInt(trecnum);
                int srecnum = Integer.parseInt(tsrecnum);
                int custnum = Integer.parseInt(tcustnum);
		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                InsJobStatus.deleteItem(con, srecnum);
		res.sendRedirect(""+classdir+"ServInstall?action=showjob&custnum="+tcustnum+"&recnum="+trecnum+"");
            }


private void doDeleteJobRecord(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                String trecnum = req.getParameter("recnum");
                int recnum = Integer.parseInt(trecnum);
                String tcustnum= req.getParameter("custnum");

                InsJobStatus.deleteItem(con, recnum);
		InsJobs.deleteItem(con,trecnum);

		res.sendRedirect(""+classdir+""+reqsource+"?action=showcustdetail&custnum="+tcustnum+"");
			}


private void doSaveJobStatus(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String trecnum = req.getParameter("jobrecnum");
                String description= req.getParameter("description");
                String jobstatus= req.getParameter("jobstatus");
                String statusdate= req.getParameter("statusdate");
                String tcustnum= req.getParameter("custnum");
                String salesperson= req.getParameter("salesperson");

                int recnum = Integer.parseInt(trecnum);
                int custnum = Integer.parseInt(tcustnum);
		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                InsJobStatus.addJobStatus(con, custnum, recnum, doFormatDateDb(getDateDb(statusdate)), jobstatus, description);
		res.sendRedirect(""+classdir+"ServInstall?action=showjob&custnum="+tcustnum+"&recnum="+recnum+"");
            }


  private void doAddJobComplete(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");
	String custnum = req.getParameter("custnum");
	String jobnum = req.getParameter("jobnum");
	String jrecnum = req.getParameter("jrecnum");
        int intcustnum = Integer.parseInt(custnum);
	int counter=0;
 	Format formatter;
	Calendar now = Calendar.getInstance();
        Date date = new Date(); 
	formatter = new SimpleDateFormat("MM-dd-yyyy");
        String s = formatter.format(date);
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Add Job Complete</title>");
	out.println("</head>");
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=savejobcomplete\" name=\"addcustnote\">");
	out.println("<table><tr>");
	out.println("<td>");
	out.println("Job Number");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"jobnum\" value=\""+jobnum+"\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Completion Date:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"compdate\" size=\"40\" value=\""+s+"\">");
	out.println("</td></tr><tr><td>");
	out.println("Quality Check Complete?");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"qualdone\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Startup Complete?");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"startupdone\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Customer Satisfied?");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"customersat\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Turnover to Service OK?");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"turnoverok\" size=\"40\">");
	out.println("</td></tr></table>");


	out.println("<P><P>");
	out.println("<h3>Customer Equipment List</h3>");

                Vector v;
                v = UniEquip.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>#</th><th>Brand</th><th>Type</th><th>Model</th><th>Serial</th><th>Filter</th><th>Notes</th>");

		counter=0;
                for (int i = 0 ; i < v.size(); i++)
                {
                UniEquip t = (UniEquip) v.elementAt(i);
		int eenum=t.getId();
		int ecustnum=t.getCustnum();
		String brand=t.getBrand();
		String modelnum=t.getModelnum();
		String serialnum=t.getSerialnum();
		String filter=t.getFilter();
		String notes=t.getNotes();
		String etype=t.getEtype();
		if (etype==null) { 
                	etype = "-";
                	}
                out.println("<tr><td>"+eenum+"</td><td>"+brand+"</td><td>"+etype+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+filter+"</td><td>"+notes+"</td></tr>");
                }
	out.println("</table><br><br>");

	out.println("<table border=1 width=100%>");
	out.println("<tr><td>Equipment 1 </td><td><input type=\"text\" name=\"equip1\" size=\"30\" value=\"0\"></td><td>Equipment 2</td><td><input type=\"text\" name=\"equip2\" size=\"30\" value=\"0\"></td></tr>");
	out.println("<tr><td>Equipment 3</td><td><input type=\"text\" name=\"equip3\" size=\"30\" value=\"0\"></td><td>Equipment 4</td><td><input type=\"text\" name=\"equip4\" size=\"30\" value=\"0\"></td></tr>");
	out.println("<tr><td>Equipment 5</td><td><input type=\"text\" name=\"equip5\" size=\"30\" value=\"0\"></td><td>Equipment 6</td><td><input type=\"text\" name=\"equip6\" size=\"30\" value=\"0\"></td></tr>");
	out.println("</table><br><br>");

out.println("<P><P>");
out.println("<h3>Customer Notes</h3>");
                Vector vn;
                vn = ServCustNotes.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>Note #</th><th>Note Date</th><th>Note Subject</th>");
		counter=0;
                for (int i = 0 ; i < vn.size(); i++)
                {
                ServCustNotes tn = (ServCustNotes) vn.elementAt(i);
		int notenum=tn.getNoteNum();
		String notedate=doFormatDate(getDate(tn.getNoteDate()));
		//String tnotedate=tn.getNoteDate();
		String notesubj=tn.getNoteSubj();
                out.println("<tr><td>"+notenum+"</td><td>"+notedate+"</td><td>"+notesubj+"</td></tr>");
                }
out.println("</table><br><br>");

	out.println("<table border=1 width=100%>");
	out.println("<tr><td>Note 1 </td><td><input type=\"text\" value=\"0\" name=\"note1\" size=\"30\"></td><td>Note 2</td><td><input type=\"text\" value=\"0\" name=\"note2\" size=\"30\"></td></tr>");
	out.println("</table><br><br>");

out.println("<P><P>");
out.println("<h3>Preventative Agreements</h3>");
 	Statement stmt5 = con.createStatement();
	ResultSet rs5 = stmt5.executeQuery("SELECT * FROM pagreement where custnum="+custnum+"");
	out.println("<table border=1 width=100%>");
        out.println("<th>Contract</th><th>Start Date</th><th>End Date</th><th>Cost</th>");
	while(rs5.next())
		{
		int pcontnum=rs5.getInt("contnum");
		String pcustnum=rs5.getString("custnum");
		String pstartdate=doFormatDate(getDate(rs5.getString("startdate")));
		String penddate=doFormatDate(getDate(rs5.getString("enddate")));
		String pcost=rs5.getString("cost");
		String pnotes=rs5.getString("notes");
                out.println("<tr><td><a href=\""+classdir+"UniCash?action=editprevagreement&contnum="+pcontnum+"&reqsource=ServInstall&custnum="+pcustnum+"\">"+pcontnum+"</a></td><td>"+pstartdate+"</td><td>"+penddate+"</td><td>"+pcost+"</td><td><a href="+classdir+"UniCash?action=printprevagreement&contnum="+pcontnum+"&custnum="+pcustnum+"&reqsource=ServInstall target=_blank>Print Format</a></td></tr>");
                }

out.println("</table><br><br>");

	out.println("<table border=1 width=100%>");
	out.println("<tr><td>Contract Offered </td><td><input type=\"text\" value=\"0\" name=\"catnum\" size=\"30\"></td></tr>");
	out.println("</table><br><br>");

	out.println("<p> <CENTER>");
	out.println("<input type=\"hidden\" name=\"custnum\" value=\""+intcustnum+"\">");
	out.println("<input type=\"hidden\" name=\"reqsource\" value=\"ServInstall\">");
	out.println("<input type=\"hidden\" name=\"jrecnum\" value=\""+jrecnum+"\">");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Save\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
	}


  private void doEditJobComplete(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {

	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");
	String custnum = req.getParameter("custnum");
	String recnum = req.getParameter("jobrecnum");
	String jrecnum = req.getParameter("jcrecnum");
	String jobrecnum = req.getParameter("jobcrecnum");
        int intcustnum = Integer.parseInt(custnum);
        int ijrecnum = Integer.parseInt(jrecnum);


                Vector vc;
                vc = ServJobComp.getIndItems(con,intcustnum,ijrecnum);
		int counter=0;
                for (int i = 0 ; i < vc.size(); i++)
                {
                ServJobComp tc = (ServJobComp) vc.elementAt(i);
       ijrecnum=tc.getRecnum();
       int equip1=tc.getEquip1();
       int equip2=tc.getEquip2();
       int equip3=tc.getEquip3();
       int equip4=tc.getEquip4();
       int equip5=tc.getEquip5();
       int equip6=tc.getEquip6();
       int note1=tc.getNote1();
       int note2=tc.getNote2();
       int contnum=tc.getContNum();
       String qualdone=tc.getQualDone();
       String compdate=tc.getCompDate();
       String startupdone=tc.getStartupDone();
       String customersat=tc.getCustomerSat();
       String turnoverok=tc.getTurnoverOK();
       String jobnum=tc.getJobNum();
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Add Job Complete</title>");
	out.println("</head>");
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=updatejobcomplete\" name=\"addcustnote\">");
	out.println("<table><tr>");
	out.println("<td>");
	out.println("Job Number");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"jobnum\" size=\"40\" value=\""+jobnum+"\">");
	out.println("</td></tr><tr><td>");
	out.println("Completion Date:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"compdate\" size=\"40\" value=\""+doFormatDate(getDate(compdate))+"\">");
	out.println("</td></tr><tr><td>");
	out.println("Quality Check Complete?");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"qualdone\" value=\""+qualdone+"\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Startup Complete?");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"startupdone\" value=\""+startupdone+"\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Customer Satisfied?");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"customersat\" value=\""+customersat+"\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Turnover to Service OK?");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"turnoverok\" value=\""+turnoverok+"\" size=\"40\">");
	out.println("</td></tr></table>");


	out.println("<P><P>");
	out.println("<h3>Customer Equipment List</h3>");

                Vector v;
                v = UniEquip.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>#</th><th>Brand</th><th>Type</th><th>Model</th><th>Serial</th><th>Filter</th><th>Notes</th>");

		counter=0;
                for (int ie = 0 ; ie < v.size(); ie++)
                {
                UniEquip t = (UniEquip) v.elementAt(ie);
		int eenum=t.getId();
		int ecustnum=t.getCustnum();
		String brand=t.getBrand();
		String modelnum=t.getModelnum();
		String serialnum=t.getSerialnum();
		String filter=t.getFilter();
		String notes=t.getNotes();
		String etype=t.getEtype();
		if (etype==null) { 
                	etype = "-";
                	}
                out.println("<tr><td>"+eenum+"</td><td>"+brand+"</td><td>"+etype+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+filter+"</td><td>"+notes+"</td></tr>");
                }
	out.println("</table><br><br>");

	out.println("<table border=1 width=100%>");
	out.println("<tr><td>Equipment 1 </td><td><input type=\"text\" name=\"equip1\" size=\"30\" value=\""+equip1+"\"></td><td>Equipment 2</td><td><input type=\"text\" name=\"equip2\" size=\"30\" value=\""+equip2+"\"></td></tr>");
	out.println("<tr><td>Equipment 3</td><td><input type=\"text\" name=\"equip3\" size=\"30\" value=\""+equip3+"\"></td><td>Equipment 4</td><td><input type=\"text\" name=\"equip4\" size=\"30\" value=\""+equip4+"\"></td></tr>");
	out.println("<tr><td>Equipment 5</td><td><input type=\"text\" name=\"equip5\" size=\"30\" value=\""+equip5+"\"></td><td>Equipment 6</td><td><input type=\"text\" name=\"equip6\" size=\"30\" value=\""+equip6+"\"></td></tr>");
	out.println("</table><br><br>");

out.println("<P><P>");
out.println("<h3>Customer Notes</h3>");
                Vector vn;
                vn = ServCustNotes.getAllItems(con,intcustnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>Note #</th><th>Note Date</th><th>Note Subject</th>");
		counter=0;
                for (int in = 0 ; in < vn.size(); in++)
                {
                ServCustNotes tn = (ServCustNotes) vn.elementAt(in);
		int notenum=tn.getNoteNum();
		String notedate=doFormatDate(getDate(tn.getNoteDate()));
		//String tnotedate=tn.getNoteDate();
		String notesubj=tn.getNoteSubj();
                out.println("<tr><td>"+notenum+"</td><td>"+notedate+"</td><td>"+notesubj+"</td></tr>");
                }
out.println("</table><br><br>");

	out.println("<table border=1 width=100%>");
	out.println("<tr><td>Note 1 </td><td><input type=\"text\" value=\""+note1+"\" name=\"note1\" size=\"30\"></td><td>Note 2</td><td><input type=\"text\" value=\""+note2+"\" name=\"note2\" size=\"30\"></td></tr>");
	out.println("</table><br><br>");

out.println("<P><P>");
out.println("<h3>Preventative Agreements</h3>");
 	Statement stmt5 = con.createStatement();
	ResultSet rs5 = stmt5.executeQuery("SELECT * FROM pagreement where custnum="+custnum+"");
	out.println("<table border=1 width=100%>");
        out.println("<th>Contract</th><th>Start Date</th><th>End Date</th><th>Cost</th>");
	while(rs5.next())
		{
		int pcontnum=rs5.getInt("contnum");
		String pcustnum=rs5.getString("custnum");
		String pstartdate=doFormatDate(getDate(rs5.getString("startdate")));
		String penddate=doFormatDate(getDate(rs5.getString("enddate")));
		String pcost=rs5.getString("cost");
		String pnotes=rs5.getString("notes");
                out.println("<tr><td><a href=\""+classdir+"UniCash?action=editprevagreement&contnum="+pcontnum+"&reqsource=ServInstall&custnum="+pcustnum+"\">"+pcontnum+"</a></td><td>"+pstartdate+"</td><td>"+penddate+"</td><td>"+pcost+"</td><td><a href="+classdir+"UniCash?action=printprevagreement&contnum="+pcontnum+"&custnum="+pcustnum+"&reqsource=ServInstall target=_blank>Print Format</a></td></tr>");
                }

out.println("</table><br><br>");

	out.println("<table border=1 width=100%>");
	out.println("<tr><td>Contract Offered </td><td><input type=\"text\" value=\""+contnum+"\" name=\"catnum\" size=\"30\"></td></tr>");
	out.println("</table><br><br>");

	out.println("<p> <CENTER>");
	out.println("<input type=\"hidden\" name=\"custnum\" value=\""+intcustnum+"\">");
	out.println("<input type=\"hidden\" name=\"reqsource\" value=\"ServInstall\">");
	out.println("<input type=\"hidden\" name=\"jrecnum\" value=\""+jrecnum+"\">");
	out.println("<input type=\"hidden\" name=\"recnum\" value=\""+recnum+"\">");
	out.println("<input type=\"hidden\" name=\"jobrecnum\" value=\""+jobrecnum+"\">");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Update\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
	}
	}


private void doUpdateJobCompletion(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String tjobrecnum= req.getParameter("recnum");
                String tcustnum= req.getParameter("custnum");
                int custnum = Integer.parseInt(tcustnum);
                int jobrecnum = Integer.parseInt(tjobrecnum);
                String tjrecnum= req.getParameter("jrecnum");
                int jrecnum = Integer.parseInt(tjrecnum);
                String compdate= req.getParameter("compdate");
                String tequip1= req.getParameter("equip1");
                int equip1 = Integer.parseInt(tequip1);
                String tequip2= req.getParameter("equip2");
                int equip2 = Integer.parseInt(tequip2);
                String tequip3= req.getParameter("equip3");
                int equip3 = Integer.parseInt(tequip3);
                String tequip4= req.getParameter("equip4");
                int equip4 = Integer.parseInt(tequip4);
                String tequip5= req.getParameter("equip5");
                int equip5 = Integer.parseInt(tequip5);
                String tequip6= req.getParameter("equip6");
                int equip6 = Integer.parseInt(tequip6);
                String tnote1= req.getParameter("note1");
                int note1 = Integer.parseInt(tnote1);
                String tnote2= req.getParameter("note2");
                int note2 = Integer.parseInt(tnote2);
                String tcatnum= req.getParameter("catnum");
                int catnum = Integer.parseInt(tcatnum);
                //String jrecnum= req.getParameter("jrecnum");
                String qualdone= req.getParameter("qualdone");
                String startupdone= req.getParameter("startupdone");
                String customersat= req.getParameter("customersat");
                String turnoverok= req.getParameter("turnoverok");
                String jobnum= req.getParameter("jobnum");


		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                ServJobComp.UpdateItem(con,jrecnum, custnum, doFormatDateDb(getDateDb(compdate)), equip1, equip2, equip3, equip4, equip5, equip6, note1, note2, catnum, qualdone, startupdone, customersat, turnoverok, jobnum);
                out.println("Your item has been added to the database");
		res.sendRedirect(""+classdir+""+reqsource+"?action=showjob&recnum="+jobrecnum+"&custnum="+tcustnum+"");
            }

private void doSaveJobCompletion(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String compdate= req.getParameter("compdate");
                String tequip1= req.getParameter("equip1");
                int equip1 = Integer.parseInt(tequip1);
                String tequip2= req.getParameter("equip2");
                int equip2 = Integer.parseInt(tequip2);
                String tequip3= req.getParameter("equip3");
                int equip3 = Integer.parseInt(tequip3);
                String tequip4= req.getParameter("equip4");
                int equip4 = Integer.parseInt(tequip4);
                String tequip5= req.getParameter("equip5");
                int equip5 = Integer.parseInt(tequip5);
                String tequip6= req.getParameter("equip6");
                int equip6 = Integer.parseInt(tequip6);
                String tnote1= req.getParameter("note1");
                int note1 = Integer.parseInt(tnote1);
                String tnote2= req.getParameter("note2");
                int note2 = Integer.parseInt(tnote2);
                String tcatnum= req.getParameter("catnum");
                int catnum = Integer.parseInt(tcatnum);
                String jrecnum= req.getParameter("jrecnum");
                String qualdone= req.getParameter("qualdone");
                String startupdone= req.getParameter("startupdone");
                String customersat= req.getParameter("customersat");
                String turnoverok= req.getParameter("turnoverok");
                String jobnum= req.getParameter("jobnum");
                String tcustnum= req.getParameter("custnum");
                int custnum = Integer.parseInt(tcustnum);


		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                ServJobComp.AddItem(con, custnum, doFormatDateDb(getDateDb(compdate)), equip1, equip2, equip3, equip4, equip5, equip6, note1, note2, catnum, qualdone, startupdone, customersat, turnoverok, jobnum);
                out.println("Your item has been added to the database");
		res.sendRedirect(""+classdir+""+reqsource+"?action=showjob&recnum="+jrecnum+"&custnum="+tcustnum+"");
            }


  private void doAddNote(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");
	String custnum = req.getParameter("custnum");
	int counter=0;
 	Format formatter;
	Calendar now = Calendar.getInstance();
        Date date = new Date(); 
	formatter = new SimpleDateFormat("MM-dd-yyyy");
        String s = formatter.format(date);
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Add Customer Note</title>");
	out.println("</head>");
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=savecustnote\" name=\"addcustnote\">");
	out.println("<table><tr>");
	out.println("<td>");
	out.println("Note Date:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"notedate\" size=\"40\" value=\""+s+"\">");
	out.println("</td></tr><tr><td>");
	out.println("Subject:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"notesubj\" size=\"40\"></td></tr>");
        out.println("<tr><td>Note</td><td>");
        out.println("<textarea name=\"note\" cols=\"60\" rows=\"10\" wrap=\"VIRTUAL\" style=\"width: 500px\"></textarea>");
	out.println("</td></tr></table>");
	out.println("<p> <CENTER>");
	out.println("<input type=\"hidden\" name=\"custnum\" value=\""+custnum+"\">");
	out.println("<input type=\"hidden\" name=\"reqsource\" value=\"ServInstall\">");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Save\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
	}

  private void doEditNote(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	String reqsource = req.getParameter("reqsource");
	String tcustnum = req.getParameter("custnum");
	int custnum = Integer.parseInt(tcustnum);
	String tnotenum = req.getParameter("notenum");
	int notenum = Integer.parseInt(tnotenum);
                Vector vn;
                vn = ServCustNotes.getIndItems(con,custnum, notenum);
		int counter=0;
                for (int i = 0 ; i < vn.size(); i++)
                {
                ServCustNotes tn = (ServCustNotes) vn.elementAt(i);
		notenum=tn.getNoteNum();
		String notedate=doFormatDate(getDate(tn.getNoteDate()));
		//String tnotedate=tn.getNoteDate();
		String notedata=tn.getNoteData();
		String notesubj=tn.getNoteSubj();
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Edit Customer Note</title>");
	out.println("</head>");
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=updatecustnote\" name=\"updatecustnote\">");
	out.println("<table><tr>");
	out.println("<td>");
	out.println("Note Date:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"notedate\" size=\"40\" value=\""+notedate+"\">");
	out.println("</td></tr><tr><td>");
	out.println("Subject:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"notesubj\" size=\"40\" value=\""+notesubj+"\"></td></tr>");
        out.println("<tr><td>Note</td><td>");
        out.println("<textarea name=\"note\" cols=\"60\" rows=\"10\" wrap=\"VIRTUAL\" style=\"width: 500px\">"+notedata+"</textarea>");
	out.println("</td></tr></table>");
	out.println("<p> <CENTER>");
	out.println("<input type=\"hidden\" name=\"custnum\" value=\""+custnum+"\">");
	out.println("<input type=\"hidden\" name=\"notenum\" value=\""+notenum+"\">");
	out.println("<input type=\"hidden\" name=\"reqsource\" value=\"ServInstall\">");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Save\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
		}
	}


private void doDeleteCustNote(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
		String trecnum=req.getParameter("notenum");
                String notesubj= req.getParameter("notesubj");
                String notedate= req.getParameter("notedate");
                String notedata= req.getParameter("note");
                String tcustnum= req.getParameter("custnum");
                int custnum = Integer.parseInt(tcustnum);
		String reqsource=req.getParameter("reqsource");
                int recnum = Integer.parseInt(trecnum);
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                ServCustNotes.deleteItem(con, trecnum);
		res.sendRedirect(""+classdir+""+reqsource+"?action=showcustdetail&custnum="+tcustnum+"");
            }


private void doUpdateCustNote(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String notesubj= req.getParameter("notesubj");
                String notedate= req.getParameter("notedate");
                String notedata= req.getParameter("note");
                String tcustnum= req.getParameter("custnum");
                String tnotenum= req.getParameter("notenum");
                int custnum = Integer.parseInt(tcustnum);
                int notenum = Integer.parseInt(tnotenum);
		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                ServCustNotes.UpdateItem(con, notenum, custnum, doFormatDateDb(getDateDb(notedate)), notesubj, notedata);
                out.println("Your item has been added to the database");
		res.sendRedirect(""+classdir+""+reqsource+"?action=showcustdetail&custnum="+tcustnum+"");
            }
private void doSaveCustNote(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String notesubj= req.getParameter("notesubj");
                String notedate= req.getParameter("notedate");
                String notedata= req.getParameter("note");
                String tcustnum= req.getParameter("custnum");
                int custnum = Integer.parseInt(tcustnum);
		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                ServCustNotes.AddItem(con, custnum, doFormatDateDb(getDateDb(notedate)), notesubj, notedata);
                out.println("Your item has been added to the database");
		res.sendRedirect(""+classdir+""+reqsource+"?action=showcustdetail&custnum="+tcustnum+"");
            }

  private void doAddJob(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");
	String custnum = req.getParameter("custnum");


	out.println("<html>");
	out.println("<head>");
	out.println("<title>Add Job</title>");
	out.println("</head>");
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=savejob&reqsource="+reqsource+"\" name=\"addjob\">");
	out.println("<table><tr><td>");
	out.println("Job Number:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"jobnum\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Job Description:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"description\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Shop Hours:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"shophrs\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Field Hours:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"fieldhrs\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Electrical Hours:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"elechrs\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Plumbing Hours:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"plmhrs\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Salesperson:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"salesperson\" size=\"40\">");
	out.println("</td></tr><tr><td>");
	out.println("Price:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"price\" size=\"40\">");
	out.println("</td></tr></table>");
	out.println("<p> <CENTER>");
	out.println("<input type=\"hidden\" name=\"custnum\" value=\""+custnum+"\">");
	out.println("<input type=\"hidden\" name=\"reqsource\" value=\""+reqsource+"\">");
	out.println("<input type=\"hidden\" name=\"reqsource\" value=\"ServInstall\">");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Save\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
	}


  private void doShowJob(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");
	String custnum = req.getParameter("custnum");
	String trecnum = req.getParameter("recnum");
        int recnum = Integer.parseInt(trecnum);
        int intcustnum = Integer.parseInt(custnum);
	String jobnum=null;
	String shophrs=null;
	String description=null;
	String salesperson=null;
	String fieldhrs=null;
	String elechrs=null;
	String plmhrs=null;
	double fprice=0.00;
	String cname=null;
	String address1=null;
	String address2=null;
	String city =null;
	String state=null;
	String zip=null;
	String homephone=null;
	String altphone=null;
	String cust_notes=null;

	out.println("<html>");
	out.println("<head>");
	out.println("<title>Show Job</title>");
	out.println("</head>");

	out.println("<h3>Job Detail</h3><br>");
	doStyleSheet(req, res, out, session);
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}

	out.println("<a href="+classdir+""+reqsource+"?action=showcustdetail&custnum="+custnum+">Return To Customer Record</a><br><br>");
	out.println("<table border=0 width=\"100%\"><tr><td>");
	out.println("<h3>Customer Information</h3>");
	out.println("<table border=0 width=\"100%\">");
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
		String cemail=rs.getString("cemail");
		
		out.println("<tr><td>Name</td><td>"+cname+"</td></tr>");
		out.println("<tr><td>Address</td><td>"+address1+"</td></tr>");
		out.println("<tr><td>Address</td><td>"+address2+"</td></tr>");
		out.println("<tr><td>City</td><td>"+city+"</td></tr>");
		out.println("<tr><td>State</td><td>"+state+"</td></tr>");
		out.println("<tr><td>Zip</td><td>"+zip+"</td></tr>");
		out.println("<tr><td>Home Phone</td><td>"+homephone+"</td></tr>");
		out.println("<tr><td>Alt Phone</td><td>"+altphone+"</td></tr>");
		out.println("<tr><td>Email Address</td><td>"+cemail+"</td></tr>");
		out.println("<tr><td>Customer Notes</td><td>"+cust_notes+"</td></tr>");
		out.println("</table>");
		}
		out.println("</td><td>");

                Vector v;
                v = InsJobs.getIndItem(con, recnum);
		int counter=0;
                for (int i = 0 ; i < v.size(); i++)
                {
                       	InsJobs t = (InsJobs) v.elementAt(i);

		recnum=t.getRecNum();
		jobnum=t.getJobNum();
		String tprice=t.getPrice();
		fprice = Double.parseDouble(tprice);
		description=t.getDesc();
		salesperson=t.getSalesPerson();
		fieldhrs=t.getFieldHrs();
		shophrs=t.getShopHrs();
		elechrs=t.getElecHrs();
		plmhrs=t.getPlmHrs();
		}
            
	out.println("<h3>Job Estimate Information</h3>");
	out.println("<table><tr><td>");
	out.println("Job Number:");
	out.println("</td><td>");
	out.println(""+jobnum + "");
	out.println("</td></tr><tr><td>");
	out.println("Job Description:");
	out.println("</td><td>");
	out.println(""+description +"");
	out.println("</td></tr><tr><td>");
	out.println("Shop Hours:");
	out.println("</td><td>");
	out.println(""+shophrs +"");
	out.println("</td></tr><tr><td>");
	out.println("Field Hours:");
	out.println("</td><td>");
	out.println(""+fieldhrs +"");
	out.println("</td></tr><tr><td>");
	out.println("Electrical Hours:");
	out.println("</td><td>");
	out.println(""+elechrs +"");
	out.println("</td></tr><tr><td>");
	out.println("Plumbing Hours:");
	out.println("</td><td>");
	out.println(""+ plmhrs+"");
	out.println("</td></tr><tr><td>");
	out.println("Salesperson:");
	out.println("</td><td>");
	out.println(""+salesperson +"");
	out.println("</td></tr><tr><td>");
	out.println("Price:");
	out.println("</td><td>");
	out.println(""+fprice +"");
	out.println("</td></tr></table><br><br>");

	out.println("<a href="+classdir+"ServInstall?action=editjob&custnum="+custnum+"&recnum="+recnum+">Edit Job</a>");
	out.println("</td></tr></table>");
	out.println("");
	out.println("<br><br><h3>Job Status</h3><br><br><table width=100%");
        out.println("<th>Date</th><th>Status</th><th>Note</th><th>Delete<br>(no second chance)</th>");
                Vector vs;
                vs = InsJobStatus.getAllItems(con, recnum);
		int counters=0;
                for (int i = 0 ; i < vs.size(); i++)
                {
                       	InsJobStatus ts = (InsJobStatus) vs.elementAt(i);

        	int srecnum=ts.getRecNum();
        	int scustnum=ts.getCustNum();
        	int jrecnum=ts.getJobRecNum();
		String sdescription=ts.getDescription();
		String jobstatus=ts.getJobStatus();
		String statusdate=ts.getStatusDate();
                out.println("<tr><td><a href="+classdir+"ServInstall?action=editjobstatus&statusrecnum="+srecnum+"&custnum="+scustnum+"&jrecnum="+recnum+">"+doFormatDate(getDate(statusdate))+"</a></td><td>"+jobstatus+"</td><td>"+sdescription+"</td><td><a href="+classdir+"ServInstall?action=deljobstatus&jrecnum="+jrecnum+"&custnum="+scustnum+"&srecnum="+srecnum+">D</a>elete</td></tr>");

		}
		
	out.println("</table>");
	out.println("<br><a href="+classdir+"ServInstall?action=addjobstatus&custnum="+custnum+"&recnum="+recnum+">Add Status to Job</a><br>");


out.println("<br><br><h3>Job Completion</h3><br><br>");
                Vector vc;
                vc = ServJobComp.getAllJobItems(con,intcustnum,jobnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>Completion #</th><th>Job Number</th><th>Completion Date</th>");
		counter=0;
                for (int i = 0 ; i < vc.size(); i++)
                {
                ServJobComp tc = (ServJobComp) vc.elementAt(i);
		int jrecnum=tc.getRecnum();
		String tcompdate=tc.getCompDate();
		jobnum=tc.getJobNum();
                out.println("<tr><td><a href=\""+classdir+"ServInstall?action=editjobcomp&jcrecnum="+jrecnum+"&custnum="+custnum+"&jobrecnum="+recnum+"\">"+jrecnum+"</a></td><td>"+jobnum+"</td><td>"+doFormatDate(getDate(tcompdate))+"</td><td><a href="+classdir+"ServInstall?action=deljobcomprec&custnum="+custnum+"&jrecnum="+jrecnum+"&recnum="+recnum+">Delete</a></td><td><a href="+classdir+"ServInstall?action=printjobcompletion&custnum="+custnum+"&jrecnum="+jrecnum+"&recnum="+recnum+" target=_blank>Print</a></td></tr>");
                }
out.println("</table><br><br>");

		out.println("<a href="+classdir+"ServInstall?action=addjobcomp&custnum="+custnum+"&jobnum="+jobnum+"&jrecnum="+recnum+"&>Add Job Completion to Customer File</a>");
	out.println("<br><br><h3>Resource Utilization</h3><br><br>");


	out.println("<a href="+classdir+"ServInstall?action=addjobresource&custnum="+custnum+"&recnum="+recnum+">Add Resource to Job</a>");
	}

  private void doEditJob(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");
	String custnum = req.getParameter("custnum");
	String trecnum = req.getParameter("recnum");
        int recnum = Integer.parseInt(trecnum);
	String jobnum=null;
	String shophrs=null;
	String description=null;
	String salesperson=null;
	String fieldhrs=null;
	String elechrs=null;
	String plmhrs=null;
	double fprice=0.00;

	out.println("<html>");
	out.println("<head>");
	out.println("<title>Edit Job</title>");
	out.println("</head>");


                Vector v;
                v = InsJobs.getIndItem(con, recnum);
		int counter=0;
                for (int i = 0 ; i < v.size(); i++)
                {
                       	InsJobs t = (InsJobs) v.elementAt(i);

		recnum=t.getRecNum();
		jobnum=t.getJobNum();
		String tprice=t.getPrice();
		fprice = Double.parseDouble(tprice);
		description=t.getDesc();
		salesperson=t.getSalesPerson();
		fieldhrs=t.getFieldHrs();
		shophrs=t.getShopHrs();
		elechrs=t.getElecHrs();
		plmhrs=t.getPlmHrs();
		}
            
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=updatejob&reqsource="+reqsource+"\" name=\"editjob\">");
	out.println("<table><tr><td>");
	out.println("Job Number:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"jobnum\" size=\"40\" value=\""+jobnum + "\">");
	out.println("</td></tr><tr><td>");
	out.println("Job Description:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"description\" size=\"40\" value=\""+description +"\">");
	out.println("</td></tr><tr><td>");
	out.println("Shop Hours:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"shophrs\" size=\"40\" value=\""+shophrs +"\">");
	out.println("</td></tr><tr><td>");
	out.println("Field Hours:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"fieldhrs\" size=\"40\" value=\""+fieldhrs +"\">");
	out.println("</td></tr><tr><td>");
	out.println("Electrical Hours:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"elechrs\" size=\"40\" value=\""+elechrs +"\">");
	out.println("</td></tr><tr><td>");
	out.println("Plumbing Hours:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"plmhrs\" size=\"40\" value=\""+ plmhrs+"\">");
	out.println("</td></tr><tr><td>");
	out.println("Salesperson:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"salesperson\" size=\"40\" value=\""+salesperson +"\">");
	out.println("</td></tr><tr><td>");
	out.println("Price:");
	out.println("</td><td>");
	out.println("<input type=\"text\" name=\"price\" size=\"40\" value=\""+fprice +"\">");
	out.println("</td></tr></table>");
	out.println("<p> <CENTER>");
	out.println("<input type=\"hidden\" name=\"custnum\" value=\""+custnum+"\">");
	out.println("<input type=\"hidden\" name=\"recnum\" value=\""+recnum+"\">");
	out.println("<input type=\"hidden\" name=\"reqsource\" value=\"ServInstall\">");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Update\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");


	}

private void doSaveResource(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String rname = req.getParameter("rname");
                String skillset = req.getParameter("skillset");
                String leadhelper = req.getParameter("leadhelper");
		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                InsResource.addResource(con, rname, skillset, leadhelper);
                out.println("Your item has been added to the database");
		res.sendRedirect(""+classdir+""+reqsource+"?action=resources");
            }


private void doUpdateJob(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String jobnum = req.getParameter("jobnum");
                String trecnum = req.getParameter("recnum");
                String description= req.getParameter("description");
                String shophrs= req.getParameter("shophrs");
                String fieldhrs= req.getParameter("fieldhrs");
                String elechrs= req.getParameter("elechrs");
                String plmhrs= req.getParameter("plmhrs");
                String tcustnum= req.getParameter("custnum");
                String salesperson= req.getParameter("salesperson");
                String price= req.getParameter("price");

                int custnum = Integer.parseInt(tcustnum);
                int recnum = Integer.parseInt(trecnum);
		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                InsJobs.UpdateItem(con, recnum, custnum, jobnum, description, shophrs, fieldhrs, elechrs, plmhrs, salesperson, price);
                out.println("Your item has been added to the database");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
		res.sendRedirect(""+classdir+"ServInstall?action=showcustdetail&custnum="+tcustnum+"");
            }

private void doSaveJob(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
                String jobnum = req.getParameter("jobnum");
                String description= req.getParameter("description");
                String shophrs= req.getParameter("shophrs");
                String fieldhrs= req.getParameter("fieldhrs");
                String elechrs= req.getParameter("elechrs");
                String plmhrs= req.getParameter("plmhrs");
                String tcustnum= req.getParameter("custnum");
                String salesperson= req.getParameter("salesperson");
                String price= req.getParameter("price");

                int custnum = Integer.parseInt(tcustnum);
		String reqsource=req.getParameter("reqsource");
		if (reqsource==null) 
		{
			reqsource="ServInstall";
		}
                InsJobs.addJob(con, custnum, jobnum, description, shophrs, fieldhrs, elechrs, plmhrs, salesperson, price);
                out.println("Your item has been added to the database");
		res.sendRedirect(""+classdir+""+reqsource+"?action=showcustdetail&custnum="+tcustnum+"");
            }


  private void doPrintJobCompletion(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	String custstart = req.getParameter("custstart");
	String custstop = req.getParameter("custstop");
	String reqsource = req.getParameter("reqsource");
	String custnum = req.getParameter("custnum");
	String trecnum = req.getParameter("recnum");
	String tjrecnum = req.getParameter("jrecnum");
        int recnum = Integer.parseInt(trecnum);
        int jrecnum = Integer.parseInt(tjrecnum);
        int intcustnum = Integer.parseInt(custnum);
	String jobnum=null;
	String shophrs=null;
       int equip1=0;
       int equip2=0;
       int equip3=0;
       int equip4=0;
       int equip5=0;
       int equip6=0;
       int equip7=0;
       int equip8=0;
       int equip9=0;
       int equip10=0;
       int note1=0;
       int note2=0;
       int contnum=0;
       String qualdone=null;
       String compdate=null;
       String startupdone=null;
       String customersat=null;
       String turnoverok=null;
	String description=null;
	String salesperson=null;
	String fieldhrs=null;
	String elechrs=null;
	String plmhrs=null;
	double fprice=0.00;
	String cname=null;
	String address1=null;
	String address2=null;
	String city =null;
	String state=null;
	String zip=null;
	String homephone=null;
	String altphone=null;
	String cust_notes=null;

	out.println("<html>");
	out.println("<head>");
	out.println("<title>Print Job Completion</title>");
	out.println("</head>");
	//doMHeader(req, res, out, session);
	out.println("<center><h3>Job Completion Report</h3></center><br>");
	doStyleSheet(req, res, out, session);
	out.println("<table border=0 width=\"100%\"><tr><td>");
	out.println("<h3>Customer Information</h3>");
	out.println("<table border=0 width=\"100%\">");
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
		String cemail=rs.getString("cemail");
		
		out.println("<tr><td>Name</td><td>"+cname+"</td></tr>");
		out.println("<tr><td>Address</td><td>"+address1+"</td></tr>");
		out.println("<tr><td>Address</td><td>"+address2+"</td></tr>");
		out.println("<tr><td>City</td><td>"+city+"</td></tr>");
		out.println("<tr><td>State</td><td>"+state+"</td></tr>");
		out.println("<tr><td>Zip</td><td>"+zip+"</td></tr>");
		out.println("<tr><td>Home Phone</td><td>"+homephone+"</td></tr>");
		out.println("<tr><td>Alt Phone</td><td>"+altphone+"</td></tr>");
		out.println("<tr><td>Email Address</td><td>"+cemail+"</td></tr>");
		out.println("<tr><td>Customer Notes</td><td>"+cust_notes+"</td></tr>");
		out.println("</table>");
		}
		out.println("</td><td>");

                Vector v;
                v = InsJobs.getIndItem(con, recnum);
		int counter=0;
                for (int i = 0 ; i < v.size(); i++)
                {
                       	InsJobs t = (InsJobs) v.elementAt(i);

		recnum=t.getRecNum();
		jobnum=t.getJobNum();
		String tprice=t.getPrice();
		fprice = Double.parseDouble(tprice);
		description=t.getDesc();
		salesperson=t.getSalesPerson();
		fieldhrs=t.getFieldHrs();
		shophrs=t.getShopHrs();
		elechrs=t.getElecHrs();
		plmhrs=t.getPlmHrs();
		}
            
	out.println("<h3>Job Estimate Information</h3>");
	out.println("<table><tr><td>");
	out.println("Job Number:");
	out.println("</td><td>");
	out.println(""+jobnum + "");
	out.println("</td></tr><tr><td>");
	out.println("Job Description:");
	out.println("</td><td>");
	out.println(""+description +"");
	out.println("</td></tr><tr><td>");
	out.println("Shop Hours:");
	out.println("</td><td>");
	out.println(""+shophrs +"");
	out.println("</td></tr><tr><td>");
	out.println("Field Hours:");
	out.println("</td><td>");
	out.println(""+fieldhrs +"");
	out.println("</td></tr><tr><td>");
	out.println("Electrical Hours:");
	out.println("</td><td>");
	out.println(""+elechrs +"");
	out.println("</td></tr><tr><td>");
	out.println("Plumbing Hours:");
	out.println("</td><td>");
	out.println(""+ plmhrs+"");
	out.println("</td></tr><tr><td>");
	out.println("Salesperson:");
	out.println("</td><td>");
	out.println(""+salesperson +"");
	out.println("</td></tr><tr><td>");
	out.println("Price:");
	out.println("</td><td>");
	out.println(""+fprice +"");
	out.println("</td></tr></table><br><br>");

	out.println("</td></tr></table>");
	out.println("");


                Vector vc;
                vc = ServJobComp.getIndItems(con,intcustnum,jrecnum);
	out.println("<br>");
	out.println("<h3>Job Signoff</h3><br>");
	out.println("<table border=0 width=50%>");
		counter=0;
                for (int i = 0 ; i < vc.size(); i++)
                {
                ServJobComp tc = (ServJobComp) vc.elementAt(i);
       jrecnum=tc.getRecnum();
       equip1=tc.getEquip1();
       equip2=tc.getEquip2();
       equip3=tc.getEquip3();
       equip4=tc.getEquip4();
       equip5=tc.getEquip5();
       equip6=tc.getEquip6();
       note1=tc.getNote1();
       note2=tc.getNote2();
       contnum=tc.getContNum();
       qualdone=tc.getQualDone();
       compdate=tc.getCompDate();
       startupdone=tc.getStartupDone();
       customersat=tc.getCustomerSat();
       turnoverok=tc.getTurnoverOK();
       jobnum=tc.getJobNum();
                out.println("<tr><td>Completion Date</td><td>"+compdate+"</td></tr>");
                out.println("<tr><td>Startup Complete</td><td>"+startupdone+"</td></tr>");
                out.println("<tr><td>Quality Check Done</td><td>"+qualdone+"</td></tr>");
                out.println("<tr><td>Customer 100% Satisfied</td><td>"+customersat+"</td></tr>");
                out.println("<tr><td>Turnover To Service</td><td>"+turnoverok+"</td></tr>");
                }
out.println("</table>");

	out.println("<br><h3>Equipment Installed</h3><br><table width=75% border=0");
        out.println("<th>Brand</th><th>Model</th><th>Serial</th><th>Filter</th><th>Type</th><th>Notes</th>");

 	stmt = con.createStatement();
	rs = stmt.executeQuery("SELECT * FROM  equipment where enum='"+equip1+"' or enum='"+equip2+"' or enum='"+equip3+"' or enum='"+equip4+"' or enum='"+equip5+"' or enum='"+equip6+"';");

		 while(rs.next())
                {
		String brand = rs.getString("brand");
                String modelnum = rs.getString("modelnum");
                String serialnum = rs.getString("serialnum");
                String filter = rs.getString("filter");
                String notes = rs.getString("notes");
                String type = rs.getString("etype");
	out.println("<tr><td>"+brand+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+filter+"</td><td>"+type+"</td><td>"+notes+"</tr>");
		}

	out.println(" ");
	out.println(" ");
	out.println("</table> ");
	out.println("<br><h3>Job Status</h3><br><table width=100%");
        out.println("<th>Date</th><th>Status</th><th>Note</th>");
                Vector vs;
                vs = InsJobStatus.getAllItems(con, recnum);
		int counters=0;
                for (int i = 0 ; i < vs.size(); i++)
                {
                       	InsJobStatus ts = (InsJobStatus) vs.elementAt(i);

        	int srecnum=ts.getRecNum();
        	int scustnum=ts.getCustNum();
        	int jsrecnum=ts.getJobRecNum();
		String sdescription=ts.getDescription();
		String jobstatus=ts.getJobStatus();
		String statusdate=ts.getStatusDate();
                out.println("<tr><td><a href="+classdir+"ServInstall?action=editjobstatus&statusrecnum="+srecnum+"&custnum="+scustnum+"&jrecnum="+recnum+">"+doFormatDate(getDate(statusdate))+"</a></td><td>"+jobstatus+"</td><td>"+sdescription+"</td></tr>");

		}
		
	out.println("</table>");
	if (contnum!=0) {

	out.println("<br><h3>Planned Service Offered</h3><br>");


 	stmt = con.createStatement();
	rs = stmt.executeQuery("SELECT * FROM pagreement where contnum="+contnum+"");

		 while(rs.next())
                {
		int enum1=rs.getInt("enum1");
		int enum2=rs.getInt("enum2");
		int enum3=rs.getInt("enum3");
		int enum4=rs.getInt("enum4");
		int enum5=rs.getInt("enum5");
		int enum6=rs.getInt("enum6");
		int enum7=rs.getInt("enum7");
		int enum8=rs.getInt("enum8");
		int enum9=rs.getInt("enum9");
		int enum10=rs.getInt("enum10");
		String aservice=rs.getString("aservice");
		String startdate=doFormatDate(getDate(rs.getString("startdate")));
		String enddate=doFormatDate(getDate(rs.getString("enddate")));
		String term=rs.getString("term");
		String cost=rs.getString("cost");
		String notes=rs.getString("notes");
		String agrdate=doFormatDate(getDate(rs.getString("agrdate")));
		int vperyear=rs.getInt("vperyear");
		
	int vfreq=12/vperyear;
	out.println("<P ALIGN=LEFT><table>");
	out.println("<tr><td>Number of Years</td><td>"+term+" </td></tr> ");
	out.println("<tr><td>Agreement Date</td><td>"+agrdate+" </td></tr> ");
	out.println("<tr><td>Start Date</td><td>"+startdate+" </td></tr> ");
	out.println("<tr><td>End Date</td><td>"+enddate+" </td></tr> ");
	out.println("<tr><td>Visits Per Year</td><td>"+vperyear+" </td></tr> ");
	out.println("<tr><td>Agreement Cost</td><td>"+cost+" </td></tr> ");
	out.println("</table></p> ");

	out.println("<br><b>Covered Equipment</b><br>");
	out.println("<table border=1 width=95% font=\"-2\">");
        out.println("<th>Brand</th><th>Model</th><th>Serial</th><th>Filter</th><th>Type</th><th>Notes</th>");

 	stmt = con.createStatement();
	rs = stmt.executeQuery("SELECT * FROM  equipment where enum='"+enum1+"' or enum='"+enum2+"' or enum='"+enum3+"' or enum='"+enum4+"' or enum='"+enum5+"' or enum='"+enum6+"' or enum='"+enum7+"' or enum='"+enum8+"' or enum='"+enum9+"' or enum='"+enum10+"';");

		 while(rs.next())
                {
		String brand = rs.getString("brand");
                String modelnum = rs.getString("modelnum");
                String serialnum = rs.getString("serialnum");
                String filter = rs.getString("filter");
                String enotes = rs.getString("notes");
                String type = rs.getString("etype");
	out.println("<tr><td>"+brand+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+filter+"</td><td>"+type+"</td><td>"+enotes+"</tr>");
		}
	out.println("</table>");
	}

	}

	out.println("<br><h3>Resource Utilization</h3><br>");

	}



private void doPrintStartUp(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
                        {
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
        int custnum = Integer.parseInt(tcustnum);
	String tcrecnum = req.getParameter("crecnum");
        int crecnum = Integer.parseInt(tcrecnum);
         int eenum=0;
        int ecustnum=0;
        String brand=null;
        String modelnum=null;
        String serialnum=null;
	String cdate=null;
	String reason=null;
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
	String custsite=null;
	String sitenum=null;
	String cemail=null;
	String etype = "";

                String callslip = null;
                String idate = null;
                int equip1 = 0;
                int equip2 = 0;
                String mbearing = null;
                String mblades = null;
                String ecoil = null;
                String dline = null;
                String dpan = null;
                String ielect = null;
                String mcap = null;
                String hstrips = null;
                String filter = null;
                String gpreassures = null;
                String ignition = null;
                String burners = null;
                String limits = null;
                String flame = null;
                String dinducer = null;
                String humidifier = null;
                String atemp = null;
                String tempsplit = null;
                String crlaa = null;
                String crlar = null;
                String ccapr = null;
                String ccapa = null;
                String frlaa = null;
                String frlar = null;
		String fcapr = null;
                String fcapa = null;
                String fbearing = null;
                String coilcond = null;
                String cleancoil = null;
                String contactor = null;
                String scap = null;
                String ctimedelay = null;
                String oelectrical = null;
                String comppad = null;
                String recommendations = null;
                String services = null;
                String dueamount = null;
                String paidamount = null;
                String notes = null;
                String lpres = null;
                String hpres = null;
                String startco = null;
                String runco = null;
                String stacktemp = null;
                String ventpipe = null;
                String oleaks = null;
                String ochimney = null;
                String opump = null;
                String ocontrols = null;
                String otstat = null;
                String oprimesafety = null;
                String osafetime = null;
                String oigntrans = null;
                String olubemotors = null;
		String ofulemix = null;
                String onozzle = null;
                String ogross = null;
                String onet = null;
                String osmoke = null;
                String oco2 = null;
                String oo2 = null;
                String oco = null;
                String oexcessair = null;
                String obreachdraft = null;
                String ofiredraft = null;
                String oeffic = null;
                String orating = null;
                String opower = null;
                String otank = null;
                String otcond = null;
                String odheat = null;
                String ocombustion = null;
                String oelectrodes = null;
                String obrush = null;
                String ofilters = null;
                String efilter = null;

	String spres_return=null;
	String spres_supply=null;
	String spres_rated=null;
	String airflow=null;
	String g_filter = null;
	String g_electrical = null;
	String g_looppres=null;
	String g_cleancoil=null;
	String g_cleandrain=null;
	String g_pansensor=null;
	String g_cleancomp=null;
	String g_cleanunit=null;
	String g_oilblower=null;
	String g_cleanpump=null;
	String g_tsplit=null;
	String g_pampr=null;
	String g_pampa=null;
	String g_compar=null;
	String g_compaa=null;
	String g_bampr=null;
	String g_bampa=null;
	String g_pdrop=null;

	String sductsize=null;
	String rductsize=null;

	//String tech_init = doGetTechInfo_init();
	//String tech_name = doGetTechInfo_name();
	//String tech_truck = doGetTechInfo_truck();
String tech_init="";
	String tech_name="";
	String tech_truck="";
//////////////////////////////////////////////////////
// Print Header Information
/////////////////////////////////////////////////////

	out.println("<html><basefont size=2>");
	out.println("<head><title>Equipment Start Up Report</title></head><body>");
	//doMHeader(req, res, out, session);


////////////////////////////////////////////////////////
// Get callslip information
///////////////////////////////////////////////////////

        Vector v;
        v = StartUp.getIndItems(con, custnum, crecnum);
		
                for (int i = 0 ; i < v.size(); i++)
                {
                StartUp t = (StartUp) v.elementAt(i);
		crecnum=t.getCrecnum();
		custnum=t.getCustnum();
                callslip=t.getCallslip();
                idate=t.getIdate();
                equip1=t.getEquip1();
                equip2=t.getEquip2();
                mbearing=t.getMbearing();
                mblades=t.getMblades();
                ecoil=t.getEcoil();
                dline=t.getDline();
                dpan=t.getDpan();
                ielect=t.getIelect();
                mcap=t.getMcap();
                hstrips=t.getHstrips();
                filter=t.getFilter();
                gpreassures=t.getGpreassures();
                ignition=t.getIgnition();
                burners=t.getBurners();
                limits=t.getLimits();
                flame=t.getFlame();
                dinducer=t.getDinducer();
                humidifier=t.getHumidifier();
                atemp=t.getAtemp();
                tempsplit=t.getTempsplit();
                crlaa=t.getCrlaa();
                crlar=t.getCrlar();
                ccapr=t.getCcapr();
                ccapa=t.getCcapa();
                frlaa=t.getFrlaa();
                frlar=t.getFrlar();
		fcapr=t.getFcapr();
                fcapa=t.getFcapa();
                fbearing=t.getFbearing();
                coilcond=t.getCoilcond();
                cleancoil=t.getCleancoil();
                contactor=t.getContactor();
                scap=t.getScap();
                ctimedelay=t.getCtimedelay();
                oelectrical=t.getOelectrical();
                comppad=t.getComppad();
                recommendations=t.getRecommendations();
                services=t.getServices();
                dueamount=t.getDueamount();
                paidamount=t.getPaidamount();
                notes=t.getNotes();
                lpres=t.getLpres();
                hpres=t.getHpres();
                startco=t.getStartco();
                runco=t.getRunco();
                stacktemp=t.getStacktemp();
                ventpipe=t.getVentpipe();
                oleaks=t.getOleaks();
                ochimney=t.getOchimney();
                opump=t.getOpump();
                ocontrols=t.getOcontrols();
                otstat=t.getOtstat();
                oprimesafety=t.getOprimesafety();
                osafetime=t.getOsafetime();
                oigntrans=t.getOigntrans();
                olubemotors=t.getOlubemotors();
		ofulemix=t.getOfulemix();
                onozzle=t.getOnozzle();
                ogross=t.getOgross();
                onet=t.getOnet();
                osmoke=t.getOsmoke();
                oco2=t.getOco2();
                oo2=t.getOo2();
                oco=t.getOco();
                oexcessair=t.getOexcessair();
                obreachdraft=t.getObreachdraft();
                ofiredraft=t.getOfiredraft();
                oeffic=t.getOeffic();
                orating=t.getOrating();
                opower=t.getOpower();
                otank=t.getOtank();
                otcond=t.getOtcond();
                odheat=t.getOdheat();
                ocombustion=t.getOcombustion();
                oelectrodes=t.getOelectrodes();
                obrush=t.getObrush();
                ofilters=t.getOfilters();
                followup=t.getFollowup();
		airflow=t.getAirflow();
		spres_rated=t.getSpresRated();
		spres_supply=t.getSpresSupply();
		spres_return=t.getSpresReturn();
		g_filter=t.getGFilter();
		g_electrical=t.getGElectrical();
		g_looppres=t.getGLooppres();
		g_cleancoil=t.getGCleanCoil();
		g_cleandrain=t.getGCleanDrain();
		g_pansensor=t.getGPanSensor();
		g_cleancomp=t.getGCleanComp();
		g_cleanunit=t.getGCleanUnit();
		g_oilblower=t.getGOilBlower();
		g_cleanpump=t.getGCleanPump();
		g_tsplit=t.getGTsplit();
		g_pampr=t.getGPampr();
		g_pampa=t.getGPampa();
		g_compar=t.getGCompar();
		g_compaa=t.getGCompaa();
		g_bampr=t.getGBampr();
		g_bampa=t.getGBampa();
		g_pdrop=t.getGPdrop();
		sductsize=t.getSDuctSize();
		rductsize=t.getRDuctSize();
		}

//////////////////////////////////////////
// customer and inventory information
/////////////////////////////////////////

	out.println("<br>");
	out.println("<table border=0 width=\"95%\" align=\"center\"><td width=\"50%\">");
	out.println("<b align=Left>Equipment Startup For:</b><br>&nbsp;&nbsp;&nbsp;&nbsp;Call Slip: "+callslip+"<br>&nbsp;&nbsp;&nbsp;&nbsp;Call Date: "+doFormatDate(getDate(idate))+"");
	out.println("</td>");

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
	cemail=rs.getString("cemail");
	sitenum=rs.getString("sitenum");
	custsite=rs.getString("custsite");
	}	
out.println("<td width=\"30%\">");
out.println("<b>Customer:</b><br>&nbsp;&nbsp;&nbsp;&nbsp;"+cname+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+address1+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+city+","+state+"<br>&nbsp;&nbsp;&nbsp;"+cemail+"");
out.println("</td>");

out.println("<td width=\"30%\">");
out.println("Customer Number / Site:&nbsp;&nbsp;&nbsp;"+custsite+"&nbsp;/&nbsp;"+sitenum+"<br>Home Phone:&nbsp;&nbsp;&nbsp;"+homephone+"<br>Alt. Phone:&nbsp;&nbsp;&nbsp;"+altphone+"");
out.println("</td>");
	out.println("</table>");

	out.println("<table width=\"100%\" align=\"center\" border=0><th width=\"50%\" >Parts Used</th><th>Equipment List</th><tr><td>");
	out.println("<font size=1>");
	out.println("<tr><td>");

	if (custnum==267)
        	{
	rs = stmt.executeQuery("select inv_detail.itemnum, callslip, date as idate, itemname, quantity as quant, description from inv_detail, inv_items where inv_detail.itemnum=inv_items.itemnum and inv_detail.date='"+idate+"' and inv_detail.callslip='"+callslip+"'");
	} else {
	rs = stmt.executeQuery("select inv_detail.itemnum as itemnum, callslip, date as idate, itemname, abs(quantity) as quant, description from inv_detail, inv_items where inv_detail.itemnum=inv_items.itemnum and inv_detail.date='"+idate+"' and inv_detail.callslip='"+callslip+"'");
	}
        if (rs!= null) {
		out.println("<table width=\"100%\" align=\"center\" border=1>");
		out.println("<font size=1>");
		out.println("<th>Key</th><th>Part</th><th>Quantity</th><th>Date</th>");
		while (rs.next()) {
			String description = rs.getString("description");
			String itemname=rs.getString("itemname");
			String pquant = rs.getString("quant");
			out.println("<tr><td>"+description+"</td><td>"+itemname+"</td><td>"+pquant+"</td><td>"+doFormatDate(getDate(idate))+"</td></tr>");
			}
		out.println("</font>");
		out.println("</table>");
		}

	
	out.println("</td><td>");

		out.println("<table width=\"95%\" align=\"center\" border=1>");
		out.println("<th>Type</th><th>Make</th><th>Model</th><th>Serial</th><th>Filter</th><th>Notes</th>");
		out.println("<font size=1>");
 	stmt = con.createStatement();
	rs = stmt.executeQuery("SELECT * FROM  equipment where enum='"+equip1+"' or enum='"+equip2+"';");
		 while(rs.next())
                {
		brand = rs.getString("brand");
                modelnum = rs.getString("modelnum");
                serialnum = rs.getString("serialnum");
                efilter = rs.getString("filter");
                notes = rs.getString("notes");
// add equipment type here
		etype = rs.getString("etype");
if (etype==null) {
		etype = "-";
		}

	out.println("<tr><td>"+etype+"</td><td>"+brand+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+efilter+"</td><td>"+notes+"</tr>");
		}
		out.println("</font>");
		out.println("</table>");
		out.println("<font size=1>");
		out.println("</td></tr></table>");

//////////////////////////////////////////////////////
// Print Air Handler
/////////////////////////////////////////////////////
if ((!mbearing.equalsIgnoreCase("-"))||!(mblades.equalsIgnoreCase("-"))||(!ecoil.equalsIgnoreCase("-")))
        {
out.println ("<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Air Handler</h3>");
out.println ("<font size=1>");
out.println ("<table width=\"95%\" align=center border=1 height=5>");
out.println ("<font size=1>");
out.println ("<tr><td width=\"25%\"><b>Motor Bearings</td><td width=\"25%\">"+mbearing+"</td>");
out.println ("<td width=\"25%\"><b>Motor Blades</td><td width=\"25%\">"+mblades+"</td></tr>");
out.println ("<tr><td><b>Evaporator Coil</td><td>"+ecoil+"</td>");
out.println ("<td><b>Drain Line</td><td>"+dline+"</td></tr>");
out.println ("<tr><td><b>Drain Pan</td><td>"+dpan+"</td>");
out.println ("<td><b>Electrical</td><td>"+ielect+"</td></tr>");
out.println ("<tr><td><b>Motor Capacitor</td><td>"+mcap+"</td>");
out.println ("<td><b>Filter</td><td>"+filter+"</td></tr>");
out.println ("<tr><td><b>Humidifier</td><td>"+humidifier+"</td>");
out.println ("</font>");
out.println ("</table>");
}

////////////////////////////////////////////////////////
// Air Diagnostics Section
///////////////////////////////////////////////////////
if ((!spres_rated.equalsIgnoreCase("-"))||!(spres_return.equalsIgnoreCase("-"))||(!spres_rated.equalsIgnoreCase("-"))||!(rductsize.equalsIgnoreCase("-"))||!(airflow.equalsIgnoreCase("-")))
        {
	out.println("<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Air Diagnostics</h3>");
	out.println("<font size=1>");
	out.println("<table width=\"95%\" align=center border=1 height=5>");
	out.println("<font size=1>");
	out.println ("<tr><td><b>Supply Pressure</td><td>"+spres_supply+"</td>");
	out.println ("<td><b>Return Pressure</td><td>"+spres_return+"</td></tr>");
	out.println ("<tr><td><b>Rated Static Pressue</td><td>"+spres_rated+"</td>");
	out.println ("<td><b>Air Flow</td><td>"+airflow+"</td></tr>");
	out.println ("<tr><td><b>Supply Duct Size</td><td>"+sductsize+"</td>");
	out.println ("<td><b>Return Duct Size</td><td>"+rductsize+"</td></tr>");
	out.println ("</font>");
	out.println ("</table>");
	}


////////////////////////////////////////////////////////
// Print Heating Section
///////////////////////////////////////////////////////
if ((!hstrips.equalsIgnoreCase("-"))||!(burners.equalsIgnoreCase("-"))||(!flame.equalsIgnoreCase("-")))
        {
	out.println("<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Heating</h3>");
	out.println("<font size=1>");
	out.println("<table width=\"95%\" align=center border=1 height=5>");
	out.println("<font size=1>");
	out.println("<tr><td width=\"25%\"><b>Heat Strips</td><td width=\"25%\">"+hstrips+"</td>");
	out.println("<td width=\"25%\"><b>Gas Pressure</td><td width=\"25%\">"+gpreassures+"</td></tr>");
	out.println("<tr><td><b>Ignition</td><td>"+ignition+"</td>");
	out.println("<td><b>Burners</td><td>"+burners+"</td></tr>");
	out.println("<tr><td><b>Limits</td><td>"+limits+"</td>");
	out.println("<td><b>Flame</td><td>"+flame+"</td></tr>");
	out.println("<tr><td><b>Draft Inducer</td><td>"+dinducer+"</td>");
	out.println("<td><b>Stack Temp</td><td>"+stacktemp+"</td></tr>");
	out.println("<tr><td><b>Start CO</td><td>"+startco+"</td>");
	out.println("<td><b>Run CO</td><td>"+runco+"</td></tr>");
	out.println("<tr><td><b>Vent Pipe</td><td>"+ventpipe+"</td></tr>");
	out.println("</font>");
	out.println("</table>");
}

/////////////////////////////////////////////////////////
// Print Condensor section
////////////////////////////////////////////////////////

if ((!atemp.equalsIgnoreCase("-"))||!(crlar.equalsIgnoreCase("-"))||(!lpres.equalsIgnoreCase("-")))
        {
	out.println("<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Condensor</h3>");
	out.println("<table width=\"95%\" align=center border=1 height=5>");
	out.println("<font size=1>");
	out.println("<tr><td width=\"25%\"><b>Approach Temp</td><td width=\"25%\">"+atemp+"</td>");
	out.println("<td width=\"25%\"><b>Temp Split</td><td width=\"25%\">"+tempsplit+"</td></tr>");
	out.println("<tr><td><b>Comp. Rated Amps</td><td>"+crlar+"</td>");
	out.println("<td><b>Comp. Actual Amps</td><td>"+crlaa+"</td></tr>");
	out.println("<tr><td><b>Comp. Cap Actual</td><td>"+ccapa+"</td>");
	out.println("<td><b>Comp. Cap Rated</td><td>"+ccapr+"</td></tr>");
	out.println("<tr><td><b>Fan Amps Rated</td><td>"+frlar+"</td>");
	out.println("<td><b>Fan Amps Actual</td><td>"+frlaa+"</td></tr>");
	out.println("<tr><td><b>Fan Cap. Actual</td><td>"+fcapa+"</td>");
	out.println("<td><b>Fan Cap. Rated</td><td>"+fcapr+"</td></tr>");
	out.println("<tr><td><b>Indoor RH %</td><td>"+coilcond+"</td>");
	out.println("<td><b>Outdoor RH %</td><td>"+cleancoil+"</td></tr>");
	out.println("<tr><td><b>Start Capacitor</td><td>"+scap+"</td>");
	out.println("<td><b>Time Delay</td><td>"+ctimedelay+"</td></tr>");
	out.println("<tr><td><b>Electrical</td><td>"+oelectrical+"</td>");
	out.println("<td><b>Condensor Pad</td><td>"+comppad+"</td></tr>");
	out.println("<tr><td><b>Contactor</td><td>"+contactor+"</td>");
	out.println("<td><b>Fan Bearings</td><td>"+fbearing+"</td></tr>");
	out.println("<tr><td><b>Low Pressure</td><td>"+lpres+"</td>");
	out.println("<td><b>High Pressure</td><td>"+hpres+"</td></tr>");
	out.println("</font>");
	out.println("</table>");
	}

/////////////////////////////////////////////////////////
// Print Oil Heat section
////////////////////////////////////////////////////////

if ((!opump.equalsIgnoreCase("-"))||(!oprimesafety.equalsIgnoreCase("-"))||(!osafetime.equalsIgnoreCase("-"))||(!oco2.equalsIgnoreCase("-"))||(!ofulemix.equalsIgnoreCase("-")))
        {
	out.println("<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Oil Heat</h3>");
	out.println("<table width=\"95%\" align=center border=1 height=5>");
	out.println("<font size=1>");
	out.println("<tr><td width=\"25%\"><b>Oil Leaks</td><td width=\"25%\">"+oleaks+"</td>");  
	out.println("<td width=\"25%\"><b>Chimney and Flue</td><td width=\"25%\">"+ochimney+"</td></tr>");
	out.println("<tr><td><b>Pump Pressure</td><td>"+opump+"</td>");
	out.println("<td><b>Controls</td><td>"+ocontrols+"</td></tr>");
	out.println("<tr><td><b>Thermostat</td><td>"+otstat+"</td>");
	out.println("<td><b>Primary Control Safety</td><td>"+oprimesafety+"</td></tr>");
	out.println("<tr><td><b>Safety Timing</td><td>"+osafetime+"</td>");
	out.println("<td><b>Ignition Transformer</td><td>"+oigntrans+"</td></tr>");
	out.println("<tr><td><b>Lubricate Motors</td><td>"+olubemotors+"</td>");
	out.println("<td><b>Fuel/Air Mix</td><td>"+ofulemix+"</td></tr>");
	out.println("<tr><td><b>Nozzle Size</td><td>"+onozzle+"</td>");
	out.println("<td><b>Gross Stack Temp</td><td>"+ogross+"</td></tr>");
	out.println("<tr><td><b>Net Stack Temp</td><td>"+onet+"</td>");
	out.println("<td><b>Smoke</td><td>"+osmoke+"</td></tr>");
	out.println("<tr><td><b>CO2</td><td>"+oco2+"</td>");
	out.println("<td><b>O2</td><td>"+oo2+"</td></tr>");
	out.println("<tr><td><b>CO</td><td>"+oco+"</td>");
	out.println("<td><b>Excess Air</td><td>"+oexcessair+"</td></tr>");
	out.println("<tr><td><b>Breech Draft</td><td>"+obreachdraft+"</td>");
	out.println("<td><b>Overfire Draft</td><td>"+ofiredraft+"</td></tr>");
	out.println("<tr><td><b>Efficiency</td><td>"+oeffic+"</td>");
	out.println("<td><b>Rating (Excellent, Good, Fair, Poor)</td><td>"+orating+"</td></tr>");
	out.println("<tr><td><b>Chimney/Power Vent</td><td>"+opower+"</td>");
	out.println("<td><b>Tank Size</td><td>"+otank+"</td></tr>");
	out.println("<tr><td><b>Tank Condition</td><td>"+otcond+"</td>");
	out.println("<td><b>Domestic Hot Water</td><td>"+odheat+"</td></tr>");
	out.println("<tr><td><b>Combustion Chamber</td><td>"+ocombustion+"</td>");
	out.println("<td><b>Electrodes</td><td>"+oelectrodes+"</td></tr>");
	out.println("<tr><td><b>Boiler/Furnace Brush/Vacuum</td><td>"+obrush+"</td>");
	out.println("<td><b>Filters</td><td>"+ofilters+"</td></tr>");
	out.println("</font>");
	out.println("</table>");
}


/////////////////////////////////////////////////////////
// Print Geothermal section
////////////////////////////////////////////////////////

if ((!g_looppres.equalsIgnoreCase("-"))||!(g_filter.equalsIgnoreCase("-"))||(!g_electrical.equalsIgnoreCase("-")))
        {
	out.println("<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Geothermal</h3>");
	out.println("<table width=\"95%\" align=center border=1 height=5>");
	out.println("<font size=1>");
	out.println("<tr><td width=\"25%\"><b>Filters</td><td width=\"25%\">"+g_filter+"</td>");  
	out.println("<td width=\"25%\"><b>Electrical</td><td width=\"25%\">"+g_electrical+"</td></tr>");
	out.println("<tr><td><b>Loop Pressure </td><td>"+g_looppres +"</td>");
	out.println("<td><b>Cleaned Coil</td><td>"+g_cleancoil +"</td></tr>");
	out.println("<tr><td><b>Cleaned Drainline</td><td>"+g_cleandrain +"</td>");
	out.println("<td><b>Check Pan Sensor </td><td>"+g_pansensor +"</td></tr>");
	out.println("<tr><td><b>Clean Compartments </td><td>"+g_cleancomp +"</td>");
	out.println("<td><b>Clean Unit </td><td>"+g_cleanunit +"</td></tr>");
	out.println("<tr><td><b>Oil/Check Blower </td><td>"+g_oilblower +"</td>");
	out.println("<td><b>Clean Condensate Pump </td><td>"+g_cleanpump +"</td></tr>");
	out.println("<tr><td><b>Temp Split </td><td>"+g_tsplit +"</td>");
	out.println("<td><b>Pressure Drop </td><td>"+g_pdrop +"</td></tr>");
	out.println("<tr><td><b>Pump Amps Rated </td><td>"+g_pampr +"</td>");
	out.println("<td><b>Pump Amps Actual </td><td>"+g_pampa +"</td></tr>");
	out.println("<tr><td><b>Compressor Amps Rated </td><td>"+g_compar +"</td>");
	out.println("<td><b>Compressor Amps Actual </td><td>"+g_compaa +"</td></tr>");
	out.println("<tr><td><b>Blower Amps Rated </td><td>"+g_bampr +"</td>");
	out.println("<td><b>Blower Amps Actual </td><td>"+g_bampa +"</td></tr>");
	out.println("</font>");
	out.println("</table>");
	}
/////////////////////////////////////////////////////////
// Print Comments and Notes section
////////////////////////////////////////////////////////
if (services.length()>1||recommendations.length()>1) {
out.println("<br>");
out.println("<table size=95% width=\"95%\" align=center border=1>");
out.println("<font size=1>");
if (services.length()>1) {
out.println("<tr><td><b>Services</td></tr><tr><td>"+services);
out.println("</td></tr>");
}

if (recommendations.length()>1) {
out.println("<P></P><br>");
		out.println("<tr><td><h4>Our Trained Technician Recommends</h4></td></tr><tr><td>"+recommendations+"");
		out.println("<table><font size=1><tr><td>Customer Accepts Recomendations</td><td>______________</td><td>Customer Declines Recommendations</td><td>________________</td></tr></font></table></tr>");
		out.println("</td></tr>");
}
out.println("</font>");
out.println("</table>");
out.println("");
}


/////////////////////////////////////////////////////////
// Print footer and signature section
////////////////////////////////////////////////////////

	out.println("<table width=\"95%\" align=\"center\">");
	out.println("<font size=1>");
	out.println("<tr>");
	out.println("<P></p><br><br>");
		out.println("<td>____________________________________</td><td>"+tech_name+"</td><td>Amount Due</td><td><b>_________________</b></td>");
out.println("</tr><tr><td><h5>Customer Signature</h5></td><td><h5>Service Tech</h5></td><td>Amount Paid</td><td>_________________</td>");
out.println("</tr></font></table>");

out.println("</body>");
out.println("</html>");

}
private void doEditStartUp(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
	String action = req.getParameter("action");
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
        int custnum = Integer.parseInt(tcustnum);
	String custstart = req.getParameter("custstart");
	String icrecnum = req.getParameter("icrecnum");
	if (icrecnum==null) {
	icrecnum = req.getParameter("crecnum");
	}
	
	if (action.equalsIgnoreCase("addstartup"))
	                        {
	icrecnum="-";
	}
	String custstop = req.getParameter("custstop");
        int eenum=0;
        int ecustnum=0;
	int crecnum=0;
        String brand=null;
        String modelnum=null;
        String serialnum=null;
        String filter=null;
        String notes=null;
	String callslip = null;
        String idate = null;
        int equip1 = 0;
        int equip2 = 0;
	if (action.equalsIgnoreCase("editstartup"))
	                        {
	crecnum = Integer.parseInt(icrecnum);
		}
	else { crecnum=0; }
        String mbearing = "-";
        String mblades = "-";
        String ecoil = "-";
        String dline = "-";
        String dpan = "-";
        String ielect = "-";
        String mcap = "-";
        String hstrips = "-";
        String gpreassures = "-";
        String ignition = "-";
        String burners = "-";
        String limits = "-";
        String flame = "-";
        String dinducer = "-";
        String humidifier = "-";
        String atemp = "-";
        String tempsplit = "-";
        String crlaa = "-";
        String crlar = "-";
        String ccapr = "-";
        String ccapa = "-";
        String frlaa = "-";
        String frlar = "-";
        String fcapr = "-";
	String fcapa = "-";
        String fbearing = "-";
        String coilcond = "-";
        String cleancoil = "-";
        String contactor = "-";
        String scap = "-";
        String ctimedelay = "-";
        String oelectrical = "-";
        String comppad = "-";
        String recommendations = "-";
        String services = "-";
        String dueamount = "-";
        String paidamount = "-";
        String lpres = "-";
        String hpres = "-";
        String startco = "-";
        String runco = "-";
        String stacktemp = "-";
        String ventpipe = "-";
        String oleaks = "-";
        String ochimney = "-";
        String opump = "-";
        String ocontrols = "-";
        String otstat = "-";
        String oprimesafety = "-";
        String osafetime = "-";
        String oigntrans = "-";
        String olubemotors = "-";
        String ofulemix = "-";
        String onozzle = "-";
        String ogross = "-";
        String onet = "-";
        String osmoke = "-";
        String oco2 = "-";
        String oo2 = "-";
        String oco = "-";
        String oexcessair = "-";
        String obreachdraft = "-";
        String ofiredraft = "-";
        String oeffic = "-";
        String orating = "-";
        String opower = "-";
        String otank = "-";
        String otcond = "-";
        String odheat = "-";
        String ocombustion = "-";
        String oelectrodes = "-";
        String obrush = "-";
        String ofilters = "-";
        int followup = 0;
	String airflow="-";
	String spres_rated="-";
	String spres_supply="-";
	String spres_return="-";
	String g_filter = "-";
	String g_electrical = "-";
	String g_looppres="-";
	String g_cleancoil="-";
	String g_cleandrain="-";
	String g_pansensor="-";
	String g_cleancomp="-";
	String g_cleanunit="-";
	String g_oilblower="-";
	String g_cleanpump="-";
	String g_tsplit="-";
	String g_pampr="-";
	String g_pampa="-";
	String g_compar="-";
	String g_compaa="-";
	String g_bampr="-";
	String g_bampa="-";
	String g_pdrop="-";
	String rductsize="-";
	String sductsize="-";
//////////////////////////////////////////////////////
// Get Origional Call Information
//////////////////////////////////////////////////////

	if (action.equalsIgnoreCase("editstartup"))
	                        {
        Vector v;
        v = StartUp.getIndItems(con, custnum, crecnum);
		
                for (int i = 0 ; i < v.size(); i++)
                {
                StartUp t = (StartUp) v.elementAt(i);
		crecnum=t.getCrecnum();
		custnum=t.getCustnum();
                callslip=t.getCallslip();
                idate=t.getIdate();
                equip1=t.getEquip1();
                equip2=t.getEquip2();
                mbearing=t.getMbearing();
                mblades=t.getMblades();
                ecoil=t.getEcoil();
                dline=t.getDline();
                dpan=t.getDpan();
                ielect=t.getIelect();
                mcap=t.getMcap();
                hstrips=t.getHstrips();
                filter=t.getFilter();
                gpreassures=t.getGpreassures();
                ignition=t.getIgnition();
                burners=t.getBurners();
                limits=t.getLimits();
                flame=t.getFlame();
                dinducer=t.getDinducer();
                humidifier=t.getHumidifier();
                atemp=t.getAtemp();
                tempsplit=t.getTempsplit();
                crlaa=t.getCrlaa();
                crlar=t.getCrlar();
                ccapr=t.getCcapr();
                ccapa=t.getCcapa();
                frlaa=t.getFrlaa();
                frlar=t.getFrlar();
		fcapr=t.getFcapr();
                fcapa=t.getFcapa();
                fbearing=t.getFbearing();
                coilcond=t.getCoilcond();
                cleancoil=t.getCleancoil();
                contactor=t.getContactor();
                scap=t.getScap();
                ctimedelay=t.getCtimedelay();
                oelectrical=t.getOelectrical();
                comppad=t.getComppad();
                recommendations=t.getRecommendations();
                services=t.getServices();
                dueamount=t.getDueamount();
                paidamount=t.getPaidamount();
                notes=t.getNotes();
                lpres=t.getLpres();
                hpres=t.getHpres();
                startco=t.getStartco();
                runco=t.getRunco();
                stacktemp=t.getStacktemp();
                ventpipe=t.getVentpipe();
                oleaks=t.getOleaks();
                ochimney=t.getOchimney();
                opump=t.getOpump();
                ocontrols=t.getOcontrols();
                otstat=t.getOtstat();
                oprimesafety=t.getOprimesafety();
                osafetime=t.getOsafetime();
                oigntrans=t.getOigntrans();
                olubemotors=t.getOlubemotors();
		ofulemix=t.getOfulemix();
                onozzle=t.getOnozzle();
                ogross=t.getOgross();
                onet=t.getOnet();
                osmoke=t.getOsmoke();
                oco2=t.getOco2();
                oo2=t.getOo2();
                oco=t.getOco();
                oexcessair=t.getOexcessair();
                obreachdraft=t.getObreachdraft();
                ofiredraft=t.getOfiredraft();
                oeffic=t.getOeffic();
                orating=t.getOrating();
                opower=t.getOpower();
                otank=t.getOtank();
                otcond=t.getOtcond();
                odheat=t.getOdheat();
                ocombustion=t.getOcombustion();
                oelectrodes=t.getOelectrodes();
                obrush=t.getObrush();
                ofilters=t.getOfilters();
                followup=t.getFollowup();
		airflow=t.getAirflow();
		spres_rated=t.getSpresRated();
		spres_supply=t.getSpresSupply();
		spres_return=t.getSpresReturn();
		g_filter=t.getGFilter();
		g_electrical=t.getGElectrical();
		g_looppres=t.getGLooppres();
		g_cleancoil=t.getGCleanCoil();
		g_cleandrain=t.getGCleanDrain();
		g_pansensor=t.getGPanSensor();
		g_cleancomp=t.getGCleanComp();
		g_cleanunit=t.getGCleanUnit();
		g_oilblower=t.getGOilBlower();
		g_cleanpump=t.getGCleanPump();
		g_tsplit=t.getGTsplit();
		g_pampr=t.getGPampr();
		g_pampa=t.getGPampa();
		g_compar=t.getGCompar();
		g_compaa=t.getGCompaa();
		g_bampr=t.getGBampr();
		g_bampa=t.getGBampa();
		g_pdrop=t.getGPdrop();
		rductsize=t.getRDuctSize();
		sductsize=t.getSDuctSize();
		}

	}
///////////////////////////////////////////////////////
// Actual calls start here
//////////////////////////////////////////////////////



///////////////////////////////////////////////////////
// Old Callslip Information
//////////////////////////////////////////////////////
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Edit Start Up Form</title>");
	out.println("</head>");

///////////////////////////////////////////////////////
// New Information 
//////////////////////////////////////////////////////

	if (action.equalsIgnoreCase("editstartup"))
	                        {
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=updatestartup&custnum="+custnum+"\" name=\"addcat\">");
	} else {
	out.println("<form method=\"post\" action=\""+classdir+"ServInstall?action=savestartup&custnum="+custnum+"\" name=\"addcat\">");
	}
	out.println("<table size=100% border=1>");
	out.println("<tr><td>Date</td><td>");
	if (action.equalsIgnoreCase("editstartup"))
	                        {
	out.println("<input type=\"text\" name=\"idate\" value="+doFormatDate(getDate(idate))+"></td>");
	out.println("<td>Job Number</td><td>");
	out.println("<input type=\"text\" name=\"callslip\" value=\""+callslip+"\" size=15></td>");
	} else {

	out.println("<input type=\"text\" name=\"idate\" value="+doFormatDate(getDate(s))+"></td>");
	out.println("<td>Job Number</td><td>");
	out.println("<input type=\"text\" name=\"callslip\" value=\"-\" size=15></td>");
	}
	out.println("</tr>");
	out.println("</table>");
	out.println("<p></p><p><p>");
	out.println("<table size=100% border=1>");
	out.println("<th><h3>Air Handler</h3></th>");
	if (action.equalsIgnoreCase("editstartup"))
	                        {
	out.println("<tr><td>Filter</td><td><input type=\"text\" name=\"filter\" size=\"30\" value=\""+filter+"\"></td>");
			} else {
	out.println("<tr><td>Filter</td><td><input type=\"text\" name=\"filter\" size=\"30\" value=\"-\"></td>");
			}

	out.println("<td>Motor Bearings</td><td><input type=\"text\" name=\"mbearing\" size=\"30\" value=\""+mbearing+"\"></td></tr>");
	out.println("<tr><td>Motor Blades</td><td><input type=\"text\" name=\"mblades\" size=\"30\" value=\""+mblades+"\"></td>");
	out.println("<td>Evaporator Coil</td><td><input type=\"text\" name=\"ecoil\" size=\"30\" value=\""+ecoil+"\"></td></tr>");
	out.println("<tr><td>Drain Line</td><td><input type=\"text\" name=\"dline\" size=\"30\" value=\""+dline+"\"></td>");
	out.println("<td>Drain Pan</td><td><input type=\"text\" name=\"dpan\" size=\"30\" value=\""+dpan+"\"></td></tr>");
	out.println("<tr><td>Electrical</td><td><input type=\"text\" name=\"ielect\" size=\"30\" value=\""+ielect+"\"></td>");
	out.println("<td>Motor Capacitor</td><td><input type=\"text\" name=\"mcap\" size=\"30\" value=\""+mcap+"\"></td></tr>");
	out.println("<tr><td>Humidifier</td><td><input type=\"text\" name=\"humidifier\" size=\"30\" value=\""+humidifier+"\"></td></tr>");
	
	out.println("</table>");

	out.println("<p></p><p></p>");
	out.println("<table size=100% border=1>");
	out.println("<th><h3>Gas Heat</h3></th><th></th>");
	
	out.println("<tr><td>Heat Strips</td><td><input type=\"text\" name=\"hstrips\" size=\"30\" value=\""+hstrips+"\"></td><td></td><td></td></tr>");
	out.println("<tr><td>Gas Pressure</td><td><input type=\"text\" name=\"gpreassures\" size=\"30\" value=\""+gpreassures+"\"></td>");
	out.println("<td>Ignition</td><td><input type=\"text\" name=\"ignition\" size=\"30\" value=\""+ignition+"\"></td></tr>");
	out.println("<tr><td>Burners</td><td><input type=\"text\" name=\"burners\" size=\"30\" value=\""+burners+"\"></td>");
	out.println("<td>Limits</td><td><input type=\"text\" name=\"limits\" size=\"30\" value=\""+limits+"\"></td></tr>");
	out.println("<tr><td>Flame</td><td><input type=\"text\" name=\"flame\" size=\"30\" value=\""+flame+"\"></td>");
	out.println("<td>Draft Inducer</td><td><input type=\"text\" name=\"dinducer\" size=\"30\" value=\""+dinducer+"\"></td></tr>");
	out.println("<tr><td>Start CO</td><td><input type=\"text\" name=\"startco\" size=\"30\" value=\""+startco+"\"></td>");
	out.println("<td>Run CO</td><td><input type=\"text\" name=\"runco\" size=\"30\" value=\""+runco+"\"></td></tr>");
	out.println("<tr><td>Stack Temp</td><td><input type=\"text\" name=\"stacktemp\" size=\"30\" value=\""+stacktemp+"\"></td>");
	out.println("<td>Vent Pipe</td><td><input type=\"text\" name=\"ventpipe\" size=\"30\" value=\""+ventpipe+"\"></td></tr>");
	out.println("</table>");

	out.println("<p></p><p></p>");
	out.println("<table size=100% border=1>");
	out.println("<th><h3>Air Flow/Quality</h3></th><th></th>");
	out.println("<tr><td>Approach Temp</td><td><input type=\"text\" name=\"atemp\" size=\"30\" value=\""+atemp+"\"></td>");
	out.println("<tr><td>Air Flow</td><td><input type=\"text\" name=\"airflow\" size=\"30\" value=\""+airflow+"\"></td>");
	out.println("<td>Rated Pressure</td><td><input type=\"text\" name=\"spres_rated\" size=\"30\" value=\""+spres_rated+"\"></td></tr>");
	out.println("<tr><td>Supply Duct Size</td><td><input type=\"text\" name=\"sductsize\" size=\"30\" value=\""+sductsize+"\"></td>");
	out.println("<td>Return Duct Size</td><td><input type=\"text\" name=\"rductsize\" size=\"30\" value=\""+rductsize+"\"></td></tr>");
	out.println("<tr><td>Supply Pressure</td><td><input type=\"text\" name=\"spres_supply\" size=\"30\" value=\""+spres_supply+"\"></td>");
	out.println("<td>Return Pressure</td><td><input type=\"text\" name=\"spres_return\" size=\"30\" value=\""+spres_return+"\"></td></tr>");
	out.println("</table>");

	out.println("<p></p><p></p>");
	out.println("<table size=100% border=1>");
	out.println("<th><h3>Condensor</h3></th><th></th>");
	out.println("<tr><td>Approach Temp</td><td><input type=\"text\" name=\"atemp\" size=\"30\" value=\""+atemp+"\"></td>");
	out.println("<td>Temp Split</td><td><input type=\"text\" name=\"tempsplit\" size=\"30\" value=\""+tempsplit+"\"></td></tr>");
	out.println("<tr><td>Comp. Rated Amps</td><td><input type=\"text\" name=\"crlar\" size=\"30\" value=\""+crlar+"\"></td>");
	out.println("<td>Comp. Actual Amps</td><td><input type=\"text\" name=\"crlaa\" size=\"30\" value=\""+crlaa+"\"></td></tr>");
	out.println("<tr><td>Fan Amps Rated</td><td><input type=\"text\" name=\"frlar\" size=\"30\" value=\""+frlar+"\"></td>");
	out.println("<td>Fan Amps Actual</td><td><input type=\"text\" name=\"frlaa\" size=\"30\" value=\""+frlaa+"\"></td></tr>");
	out.println("<tr><td>Comp. Cap Rated</td><td><input type=\"text\" name=\"ccapr\" size=\"30\" value=\""+ccapr+"\"></td>");
	out.println("<td>Comp. Cap Actual</td><td><input type=\"text\" name=\"ccapa\" size=\"30\" value=\""+ccapa+"\"></td></tr>");
	out.println("<tr><td>Fan Cap. Rated</td><td><input type=\"text\" name=\"fcapr\" size=\"30\" value=\""+fcapr+"\"></td>");
	out.println("<td>Fan Cap. Actual</td><td><input type=\"text\" name=\"fcapa\" size=\"30\" value=\""+fcapa+"\"></td></tr>");
	out.println("<tr><td>Outdoor RH %</td><td><input type=\"text\" name=\"coilcond\" size=\"30\" value=\""+coilcond+"\"></td>");
	out.println("<td>Indoor RH %</td><td><input type=\"text\" name=\"cleancoil\" size=\"30\" value=\""+cleancoil+"\"></td></tr>");
	out.println("<tr><td>Start Capacitor</td><td><input type=\"text\" name=\"scap\" size=\"30\" value=\""+scap+"\"></td>");
	out.println("<td>Time Delay</td><td><input type=\"text\" name=\"ctimedelay\" size=\"30\" value=\""+ctimedelay+"\"></td></tr>");
	out.println("<tr><td>Electrical</td><td><input type=\"text\" name=\"oelectrical\" size=\"30\" value=\""+oelectrical+"\"></td>");
	out.println("<td>Contactor</td><td><input type=\"text\" name=\"contactor\" size=\"30\" value=\""+contactor+"\"></td></tr>");
	out.println("<tr><td>Condensor Pad</td><td><input type=\"text\" name=\"comppad\" size=\"30\" value=\""+comppad+"\"></td>");
	out.println("<td>Fan Bearings</td><td><input type=\"text\" name=\"fbearing\" size=\"30\" value=\""+fbearing+"\"></td></tr>");
	out.println("<tr><td>Low Pressure</td><td><input type=\"text\" name=\"lpres\" size=\"30\" value=\""+lpres+"\"></td>");
	out.println("<td>High Pressure</td><td><input type=\"text\" name=\"hpres\" size=\"30\" value=\""+hpres+"\"></td></tr>");
	out.println("</table>");

	out.println("<p></p><p></p>");
	out.println("<table size=100% border=1>");
	out.println("<th><h3>Oil Heat</h3></th><th></th>");
	out.println("<tr><td>Oil Leaks</td><td><input type=\"text\" name=\"oleaks\" value=\""+oleaks+"\" size=\"30\"></td>");
	out.println("<td>Chimney and Flue</td><td><input type=\"text\" name=\"ochimney\" value=\""+ochimney+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Pump Pressure</td><td><input type=\"text\" name=\"opump\" value=\""+opump+"\" size=\"30\"></td>");
	out.println("<td>Controls</td><td><input type=\"text\" name=\"ocontrols\" value=\""+ocontrols+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Thermostat</td><td><input type=\"text\" name=\"otstat\" value=\""+otstat+"\" size=\"30\"></td>");
	out.println("<td>Primary Control Safety</td><td><input type=\"text\" name=\"oprimesafety\" value=\""+oprimesafety+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Safety Timing</td><td><input type=\"text\" name=\"osafetime\" value=\""+osafetime+"\" size=\"30\"></td>");
	out.println("<td>Ignition Transformer</td><td><input type=\"text\" name=\"oigntrans\" value=\""+oigntrans+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Lubricate Motors</td><td><input type=\"text\" name=\"olubemotors\" value=\""+olubemotors+"\" size=\"30\"></td>");
	out.println("<td>Fuel/Air Mix</td><td><input type=\"text\" name=\"ofulemix\" value=\""+ofulemix+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Nozzle Size</td><td><input type=\"text\" name=\"onozzle\" value=\""+onozzle+"\" size=\"30\"></td>");
	out.println("<td>Gross Stack Temp</td><td><input type=\"text\" name=\"ogross\" value=\""+ogross+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Net Stack Temp</td><td><input type=\"text\" name=\"onet\" value=\""+onet+"\" size=\"30\"></td>");
	out.println("<td>Smoke</td><td><input type=\"text\" name=\"osmoke\" value=\""+osmoke+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>CO2</td><td><input type=\"text\" name=\"oco2\" value=\""+oco2+"\" size=\"30\"></td>");
	out.println("<td>O2</td><td><input type=\"text\" name=\"oo2\" value=\""+oo2+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>CO</td><td><input type=\"text\" name=\"oco\" value=\""+oco+"\" size=\"30\"></td>");
	out.println("<td>Excess Air</td><td><input type=\"text\" name=\"oexcessair\" value=\""+oexcessair+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Breech Draft</td><td><input type=\"text\" name=\"obreachdraft\" value=\""+obreachdraft+"\" size=\"30\"></td>");
	out.println("<td>Overfire Draft</td><td><input type=\"text\" name=\"ofiredraft\" value=\""+ofiredraft+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Efficiency</td><td><input type=\"text\" name=\"oeffic\" value=\""+oeffic+"\" size=\"30\"></td>");
	out.println("<td>Rating (Excellent, Good, Fair, Poor)</td><td><input type=\"text\" name=\"orating\" value=\""+orating+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Chimney/Power Vent</td><td><input type=\"text\" name=\"opower\" value=\""+opower+"\" size=\"30\"></td>");
	out.println("<td>Tank Size</td><td><input type=\"text\" name=\"otank\" value=\""+otank+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Tank Condition</td><td><input type=\"text\" name=\"otcond\" value=\""+otcond+"\" size=\"30\"></td>"); 
	out.println("<td>Domestic Hot Water</td><td><input type=\"text\" name=\"odheat\" value=\""+odheat+"\" size=\"30\"></td></tr>");
	out.println("<tr><td>Combustion Chamber</td><td><input type=\"text\" name=\"ocombustion\" value=\""+ocombustion+"\" size=\"30\"></td>");
	out.println("<td>Electrodes</td><td><input type=\"text\" name=\"oelectrodes\" value=\""+oelectrodes+"\" size=\"30\"></td></tr>"); 
	out.println("<tr><td>Boiler/Furnace Brush/Vacuum</td><td><input type=\"text\" name=\"obrush\" value=\""+obrush+"\" size=\"30\"></td>");
	out.println("<td>Filters</td><td><input type=\"text\" name=\"ofilters\" value=\""+ofilters+"\" size=\"30\"></td></tr>");
	out.println("</table>");



	out.println("<p></p><p></p>");
	out.println("<table size=100% border=1>");
	out.println("<th><h3>Geothermal</h3></th><th></th>");
	out.println("<tr><td>Loop Pressure</td><td><input type=\"text\" name=\"g_looppres\" size=\"30\" value=\""+g_looppres+"\"></td>");
	out.println("<td>Pressure Drop</td><td><input type=\"text\" name=\"g_pdrop\" size=\"30\" value=\""+g_pdrop+"\"></td></tr>");
	out.println("<tr><td>Comp. Rated Amps</td><td><input type=\"text\" name=\"g_compar\" size=\"30\" value=\""+g_compar+"\"></td>");
	out.println("<td>Comp. Actual Amps</td><td><input type=\"text\" name=\"g_compaa\" size=\"30\" value=\""+g_compaa+"\"></td></tr>");
	out.println("<tr><td>Blower Amps Rated</td><td><input type=\"text\" name=\"g_bampr\" size=\"30\" value=\""+g_bampr+"\"></td>");
	out.println("<td>Blower Amps Actual</td><td><input type=\"text\" name=\"g_bampa\" size=\"30\" value=\""+g_bampa+"\"></td></tr>");
	out.println("<tr><td>Pump Amps Rated</td><td><input type=\"text\" name=\"g_pampr\" size=\"30\" value=\""+g_pampr+"\"></td>");
	out.println("<td>Pump Amps Actual</td><td><input type=\"text\" name=\"g_pampa\" size=\"30\" value=\""+g_pampa+"\"></td></tr>");
	out.println("<tr><td>Cleaned Coil</td><td><input type=\"text\" name=\"g_cleancoil\" size=\"30\" value=\""+g_cleancoil+"\"></td>");
	out.println("<td>Cleaned Compartments</td><td><input type=\"text\" name=\"g_cleancomp\" size=\"30\" value=\""+g_cleancomp+"\"></td></tr>");
	out.println("<tr><td>Clean Drainlines</td><td><input type=\"text\" name=\"g_cleandrain\" size=\"30\" value=\""+g_cleandrain+"\"></td>");
	out.println("<td>Oil Blower</td><td><input type=\"text\" name=\"g_oilblower\" size=\"30\" value=\""+g_oilblower+"\"></td></tr>");
	out.println("<tr><td>Clean Cond. Pump</td><td><input type=\"text\" name=\"g_cleanpump\" size=\"30\" value=\""+g_cleanpump+"\"></td>");
	out.println("<td>Temp Split</td><td><input type=\"text\" name=\"g_tsplit\" size=\"30\" value=\""+g_tsplit+"\"></td></tr>");
	out.println("<tr><td>Electrical</td><td><input type=\"text\" name=\"g_electrical\" size=\"30\" value=\""+g_electrical+"\"></td>");
	out.println("<td>Filter</td><td><input type=\"text\" name=\"g_filter\" size=\"30\" value=\""+g_filter+"\"></td></tr>");
	out.println("<tr><td>Cleaned Unit</td><td><input type=\"text\" name=\"g_cleanunit\" size=\"30\" value=\""+g_cleanunit+"\"></td>");
	out.println("<td>Checked Pan Sensor</td><td><input type=\"text\" name=\"g_pansensor\" size=\"30\" value=\""+g_pansensor+"\"></td></tr>");
	out.println("</table>");
	out.println("<p></p><p></p>");

	out.println("<p></p><p></p>");
	out.println("<table size=100% border=1>");
///////////////////////////////////////////////////////
// Here is where the services - recomm - notes area is
//////////////////////////////////////////////////////
	out.println("<tr><td>Services</td><td>");
	out.println("<textarea name=\"services\" cols=\"60\" rows=\"4\" wrap=\"VIRTUAL\" style=\"width: 500px\">"+services+"</textarea>");
	out.println("</td></tr>");

	out.println("<tr><td>Recommendations</td><td>");
	out.println("<textarea name=\"recommendations\" cols=\"60\" rows=\"4\" wrap=\"VIRTUAL\" style=\"width: 500px\">"+recommendations+"</textarea>");
	out.println("</td></tr>");
	out.println("<tr><td>Notes</td><td>");
	out.println("<textarea name=\"notes\" cols=\"60\" rows=\"4\" wrap=\"VIRTUAL\" style=\"width: 500px\">"+notes+"</textarea>");
	out.println("</td></tr>");

	out.println("<tr><td>Add Follow-up</td><td>");
	out.println("<input type=\"checkbox\" name=\"followup\" value=1></td></tr>");

	out.println("</table>");

/////////////////////////////////////////////
// Here is where the equipment section starts 
/////////////////////////////////////////////

        Vector v;
        v = UniEquip.getAllItems(con,custnum);
	out.println("<table border=1 width=100%>");
        out.println("<th>Number</th><th>Brand</th><th>Model</th><th>Serial</th><th>Filter</th><th>Notes</th>");
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
                out.println("<tr><td>"+eenum+"</td><td>"+brand+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+filter+"</td><td>"+notes+"</tr>");
                }
	out.println("</table>");

	out.println("</table>");
	out.println("<h3>Enter Equipment Number</h3>");
	out.println("<table size=100% border=1>");
	out.println("<tr><td>Equipment 1</td><td><input type=\"text\" name=\"equip1\" size=\"30\" value=\""+equip1+"\"></td><td>Equipment 2</td><td><input type=\"text\" name=\"equip2\" size=\"30\" value=\""+equip2+"\"></td></tr>");
	out.println("</table>");


	out.println("<input type=\"hidden\" name=\"icrecnum\" value=\""+crecnum+"\" >");
	out.println("<input type=\"hidden\" name=\"custstart\" value=\""+custstart+"\" >");
	out.println("<input type=\"hidden\" name=\"custstop\" value=\""+custstop+"\">");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Update\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
	
	out.println("<h3>Inventory Used</h3>");

Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select inv_detail.itemnum as itemnum, transnum, callslip, date as idate, itemname, abs(quantity) as quant, description from inv_detail, inv_items where inv_detail.itemnum=inv_items.itemnum and inv_detail.date='"+idate+"' and inv_detail.callslip='"+callslip+"' order by description");
if (rs!= null) {
	out.println("<table width=\"100%\" align=\"center\" border=1>");
	out.println("<font size=1>");
	out.println("<th>Key</th><th>Part</th><th>Quantity</th><th>Date</th><th>Delete</th>");
	while (rs.next()) {
		String description = rs.getString("description");
		String itemname=rs.getString("itemname");
		String pquant = rs.getString("quant");
		int transnum = rs.getInt("transnum");
		String iidate = doFormatDate(getDate(rs.getString("idate")));
		out.println("<tr><td>"+description+"</td><td>"+itemname+"</td><td>"+pquant+"</td><td>"+iidate+"</td>");
		out.println("<td><a href="+classdir+"UniCash?action=delinvtrans2&transnum="+transnum+"&psource=editinspection&callslip="+callslip+"&crecnum="+crecnum+"&cdate="+idate+"&custnum="+custnum+">Delete</a></td>");
		out.println("</tr>");
		}
	out.println("</font>");
	out.println("</table>");
	}
	out.println("</table><br><br><a href="+classdir+"UniCash?action=addinvmenu&custnum="+custnum+"&psource=editinspection&callslip="+callslip+"&crecnum="+crecnum+"&cdate="+idate+">Add Inventory to Callslip</a>");
	out.println("<br><br>");
	}

}//ServInstall
