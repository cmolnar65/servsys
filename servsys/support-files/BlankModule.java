package com.pandmservices.web; 
import com.pandmservices.*;
import com.pandmservices.telnet.*;
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

public class ServInstall extends HttpServlet
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
	out.println("<br><a href=http://127.0.0.1:8080/servsys/index.html target=_top >Home</a><br>");

	
	out.println("<br><a href="+classdir+"UniCash?action=menu&menu=customers target=nav>Customers</a><br>");

	if (menu.equalsIgnoreCase("customers")) {
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=a&custstop=b target=phpmain>Customers A</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=b&custstop=c target=phpmain>Customers B</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=c&custstop=e target=phpmain>Customers C-D</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=e&custstop=g target=phpmain>Customers E-F</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=g&custstop=i target=phpmain>Customers G-H</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=i&custstop=l target=phpmain>Customers I-K</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=l&custstop=m target=phpmain>Customers L</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=m&custstop=n target=phpmain>Customers M</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=n&custstop=o target=phpmain>Customers N</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=o&custstop=p target=phpmain>Customers O</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=p&custstop=q target=phpmain>Customers P</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=q&custstop=s target=phpmain>Customers Q-R</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=s&custstop=t target=phpmain>Customers S</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=t&custstop=u target=phpmain>Customers T</a><br>");
	out.println("&nbsp;&nbsp;&nbsp;-&nbsp;<a href="+classdir+"UniCash?action=listcustomers&custstart=u&custstop=ZZ target=phpmain>Customers U-Z</a><br>");
}

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
			out.println("<h2><i>Service Entry and Reporting System</i></h2>");
			out.println("</TD>");
		out.println("</TR>");
	out.println("</THEAD>");
out.println("</TABLE>");
out.println("</BODY>");
out.println("</HTML>");
out.println("<br><br><br><br><CENTER>");
out.println("Copyright 2002, 2003, 2004 - Christopher J. Molnar <br>All Rights Reserved");
out.println("<br>Version: "+doVersionInfo_VNumber()+" - Compiled: "+doFormatDate(getDate(doVersionInfo_VDate()))+"<br>");
/* #system("uptime"); */

out.println("</CENTER>");

	}



//ADMIN FUNCTION LOGIN
//
private void doLoginAdminUser(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String next_page)
                throws Exception
        {
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Login Admin User</title>");
	out.println("</head>");
	out.println("<h4>All admin user access is logged. You must enter valid password to procede</h4><br>");
	out.println("<form method=\"post\" action=\""+classdir+"UniCash?action=checkadminuser\" name=\"addcust\">");
	out.println("<table><tr><td>");
	out.println("Password        :");
	out.println("</td><td>");
	out.println("<input type=\"password\" name=\"apasswd\" size=\"40\" >");
	out.println("</td></tr><tr><td>");
	out.println("</td></tr></table>");
	out.println("<input type=\"hidden\" name=\"next_page\" size=\"40\" value=\""+ next_page+"\">");
	out.println("<p> <CENTER>");
	out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Login\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
	}


//ADMIN FUNCTION CHECK PASSWORD
//
private void doCheckAdminUser(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {
		String next_page = req.getParameter("next_page");
		String adminok = "0";
		String apass = req.getParameter("apasswd");
		
                if (pass.equalsIgnoreCase(apass))
	                        {
               	res.sendRedirect(""+classdir+"UniCash?action="+next_page+"&adminok=1");
				}
		else
		{
                res.sendRedirect(""+classdir+"UniCash?action=showhomepage");
		}
	out.println("Password Typed: "+apass+"<br>Password Expected: "+pass+"<br>Next Page: "+next_page+"<br>");
	}
		

	public String doGetTechInfo_name()
	throws Exception
	{
                Vector v;
                v = UniTechInfo.getAllItems(con);
                int counter=0;
		String tech_name = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniTechInfo t = (UniTechInfo) v.elementAt(i);
                        tech_name = t.getTechName();
		}	
		return tech_name;
                       
	}

	public String doGetSmtpServer()
        throws Exception
        {
                Vector v;
                v = UniEmailAdd.getAllItems(con);
                int counter=0;
		String smtpserver = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniEmailAdd t = (UniEmailAdd) v.elementAt(i);
                        smtpserver = t.getSmtpServer();
                }
                        return smtpserver;                       
        }


	public double doGetLaborCost()
        throws Exception
        {
                Vector v;
                v = UniWsConfig.getAllItems(con);
                int counter=0;
		double laborcost = 0.00;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniWsConfig t = (UniWsConfig) v.elementAt(i);
                        laborcost = t.getLaborCost();
                }
                        return laborcost;                       
        }

	public double doGetPartMult()
        throws Exception
        {
                Vector v;
                v = UniWsConfig.getAllItems(con);
                int counter=0;
		double partmult = 0.00;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniWsConfig t = (UniWsConfig) v.elementAt(i);
                        partmult = t.getPartMult();
                }
                        return partmult;                       
        }

	public String doGetPropPrice()
        throws Exception
        {
                Vector v;
                v = UniWsConfig.getAllItems(con);
                int counter=0;
		String propprice = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniWsConfig t = (UniWsConfig) v.elementAt(i);
                        propprice = t.getPropPrice();
                }
                        return propprice;                       
        }

	public double doGetMarkUpDiv()
        throws Exception
        {
                Vector v;
                v = UniWsConfig.getAllItems(con);
                int counter=0;
		double markupdiv = 0.00;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniWsConfig t = (UniWsConfig) v.elementAt(i);
                        markupdiv = t.getMarkUpDiviser();
                }
                        return markupdiv;                       
        }


	public double doGetGpToLabor()
        throws Exception
        {
                Vector v;
                v = UniWsConfig.getAllItems(con);
                int counter=0;
		double gptolabor = 0.00;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniWsConfig t = (UniWsConfig) v.elementAt(i);
                        gptolabor = t.getGpToLabor();
                }
                        return gptolabor;                       
        }

//CJM - Changed to get crew billable hours

	public double doGetCrewBillable()
        throws Exception
        {
                Vector v;
                v = UniWsConfig.getAllItems(con);
                int counter=0;
		double crewbillable = 0.00;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniWsConfig t = (UniWsConfig) v.elementAt(i);
                        crewbillable = t.getCrewBillable();
                }
                        return crewbillable;                       
        }


	public String doGetDbServer()
        throws Exception
        {
                Vector v;
                v = UniDbServer.getAllItems(con);
                int counter=0;
		String dbserver = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniDbServer t = (UniDbServer) v.elementAt(i);
                        dbserver = t.getServerName();
                }
                        return dbserver;                       
        }



	public String doGetDbPassword()
        throws Exception
        {
                Vector v;
                v = UniDbServer.getAllItems(con);
                int counter=0;
		String password = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniDbServer t = (UniDbServer) v.elementAt(i);
                        password = t.getPassword();
                }
                        return password;                       
        }


	public String doGetDbName()
        throws Exception
        {
                Vector v;
                v = UniDbServer.getAllItems(con);
                int counter=0;
		String dbname = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniDbServer t = (UniDbServer) v.elementAt(i);
                        dbname = t.getDbName();
                }
                        return dbname;                       
        }


	public String doGetDbUser()
        throws Exception
        {
                Vector v;
                v = UniDbServer.getAllItems(con);
                int counter=0;
		String username = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniDbServer t = (UniDbServer) v.elementAt(i);
                        username = t.getUserName();
                }
                        return username;                       
        }

	public String doGetTech_Email()
        throws Exception
        {
                Vector v;
                v = UniEmailAdd.getAllItems(con);
                int counter=0;
		String tech_email = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniEmailAdd t = (UniEmailAdd) v.elementAt(i);
                        tech_email = t.getTechEmail();
                }
                        return tech_email;                       
        }


	public String doGetStock_Email()
        throws Exception
        {
                Vector v;
                v = UniEmailAdd.getAllItems(con);
                int counter=0;
		String stock_email = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniEmailAdd t = (UniEmailAdd) v.elementAt(i);
                        stock_email = t.getStockEmail();
                }
                        return stock_email;                       
        }

	public String doGetProp_Email()
        throws Exception
        {
                Vector v;
                v = UniEmailAdd.getAllItems(con);
                int counter=0;
		String prop_email = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniEmailAdd t = (UniEmailAdd) v.elementAt(i);
                        prop_email = t.getPropEmail();
                }
                        return prop_email;                       
        }

	public String doGetIns_Email()
        throws Exception
        {
                Vector v;
                v = UniEmailAdd.getAllItems(con);
                int counter=0;
		String ins_email = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniEmailAdd t = (UniEmailAdd) v.elementAt(i);
                        ins_email = t.getInsEmail();
                }
                        return ins_email;                       
        }

	public String doGetSvc_Email()
        throws Exception
        {
                Vector v;
                v = UniEmailAdd.getAllItems(con);
                int counter=0;
		String svc_email = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniEmailAdd t = (UniEmailAdd) v.elementAt(i);
                        svc_email = t.getSvcEmail();
                }
                        return svc_email;                       
        }

	public String doVersionInfo_VNumber()
        throws Exception
        {
                Vector v;
                v = UniVersion.getAllItems(con);
                int counter=0;
		String vnumber  = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniVersion t = (UniVersion) v.elementAt(i);
                        vnumber  = t.getVNumber();
                }
                        return vnumber;                       
        }

	public String doGetCompanyName()
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
		return compname;
	}
            

	public String doVersionInfo_VDate()
        throws Exception
        {
                Vector v;
                v = UniVersion.getAllItems(con);
                int counter=0;
		String vdate  = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniVersion t = (UniVersion) v.elementAt(i);
                        vdate  = t.getVDate();
                }

                        return vdate;                       
        }

	public String doGetTime_Email()
        throws Exception
        {
                Vector v;
                v = UniEmailAdd.getAllItems(con);
                int counter=0;
		String time_email = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniEmailAdd t = (UniEmailAdd) v.elementAt(i);
                        time_email = t.getTimeEmail();
                }
                        return time_email;                       
        }


	public String doGetTechInfo_init()
        throws Exception
        {
                Vector v;
                v = UniTechInfo.getAllItems(con);
                int counter=0;
		String tech_init = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniTechInfo t = (UniTechInfo) v.elementAt(i);
                        tech_init = t.getTechInit();
                }

                        return tech_init;                       
        }

	public String doGetTechInfo_truck()
        throws Exception
        {
                Vector v;
		String truck_num = null;
                v = UniTechInfo.getAllItems(con);
                int counter=0;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniTechInfo t = (UniTechInfo) v.elementAt(i);
                        truck_num = t.getTruckNum();
                }

                        return truck_num;                       
        }

	public static double getRandomNumber (double maxValue)
	{
		return Math.random() * maxValue;

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
                UniCustomer.addCustomer(con, cname, address1, address2, city, state, zip, homephone, altphone, cust_notes,cemail);
                out.println("Your item has been added to the database");
		res.sendRedirect(""+classdir+"UniCash?action=listcustomers&custstart="+custstart+"&custstop="+custstop);
            }


//Format Date to MySql Form

private String doFormatDateDb(Date visited)
		throws Exception
		{
	Date tdate;
	Format formatter;	
	formatter = new SimpleDateFormat("yyyy-MM-dd");
	String newdate = formatter.format(visited);
	return newdate;
		}

      public Date getDateDb( String token ) {
              Date visited; 
              SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy"); 
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

//Format date to human form

private String doFormatDate(Date visited)
		throws Exception
		{
	Date tdate;
	Format formatter;	
	formatter = new SimpleDateFormat("MM-dd-yyyy");
	String newdate = formatter.format(visited);
	return newdate;
		}


private int doFormatDateComp(Date visited)
		throws Exception
		{
	Date tdate;
	Format formatter;	
	formatter = new SimpleDateFormat("yyyyMMdd");
	String newdate = formatter.format(visited);
	int inewdate=Integer.parseInt(newdate);
	return inewdate;
		}

public Date getDate( String token ) {
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



  
  public static String combinestring(String oldstring, String newstring)
  {
   
      String changedstring = oldstring.concat(newstring);
      return changedstring;
  }
 
  public static void doMailSend(String smtpServer, String to, String from, String subject, String body)
  {
    try
    {
      Properties props = System.getProperties();

      // -- Attaching to default Session, or we could start a new one --

      props.put("mail.smtp.host", smtpServer);
      Session session = Session.getDefaultInstance(props, null);

      // -- Create a new message --
      Message msg = new MimeMessage(session);

      // -- Set the FROM and TO fields --
      msg.setFrom(new InternetAddress(from));
      msg.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(to, false));

      // -- We could include CC recipients too --
      // if (cc != null)
      // msg.setRecipients(Message.RecipientType.CC
      // ,InternetAddress.parse(cc, false));

      // -- Set the subject and body text --
      msg.setSubject(subject);
	msg.setContent(body,"text/html");
      //msg.setText(body);

      // -- Set some other header information --
      msg.setHeader("X-Mailer", "LOTONtechEmail");
      msg.setSentDate(new Date());

      // -- Send the message --
      Transport.send(msg);

      System.out.println("Message sent OK.");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
	
  private void doMHeader(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session)
                throws Exception
        {

		String imagename = null;
		String imagewidth = null;
		String imagehight = null;
		String compname = null;
		String complogo = null;
		String compaddress = null;
		String compphone = null;
		String useletterhead = null;

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
			useletterhead=t.getUseLetterHead();
		}
	if (useletterhead.equalsIgnoreCase("no")) 
	{
	out.println("<table align=\"center\" border=0 cellpadding=10 cols=3 width=\"95%\">");
	out.println("<tr><td> ");
	out.println("<IMG SRC=\""+imagename+"\" NAME=\"CompLogo\" ALIGN=LEFT WIDTH="+imagewidth+" HEIGHT="+imagehight+" BORDER=0><BR CLEAR=LEFT>");
	out.println("</td>");
	out.println("<font size=2><td><P ALIGN=CENTER ><b>"+compname+"<br>");
	out.println(complogo);
	out.println(compaddress);
	out.println(compphone+"</b>");
	out.println("</font></td>");
	out.println("</tr>");
	out.println("</table>");
	}
	else 
	{
	out.println("<br><br><br>");
	}
	}



	public static String formatTimeString( long value)
	{ 
		//DecimalFormat is used to display at least two digits 
		DecimalFormat nf = new DecimalFormat( "00" ); 
		//calculate hours, minutes, and seconds 
		long remainder = 0; 
		long hours = (value / MS_HOUR); 
		remainder = value % MS_HOUR; 
		long minutes = remainder / MS_MINUTE; 
		remainder = remainder % MS_MINUTE; 
		long seconds = remainder / MS_SECOND; 
		//build "hh:mm:ss" 
		StringBuffer buffer = new StringBuffer(); 
		buffer.append(nf.format(hours)); 
		buffer.append( ":" ); 
		buffer.append(nf.format(minutes)); 
		buffer.append( ":" ); 
		buffer.append(nf.format(seconds)); 
		System.out.println(buffer);
		
		return buffer.toString(); 
	}

	public static String formatATimeString( long value)
	{ 
		//DecimalFormat is used to display at least two digits 
		DecimalFormat nf = new DecimalFormat( "00" ); 
		//calculate hours, minutes, and seconds 
		long remainder = 0; 
		long hours = (value / MS_HOUR-5); 
		remainder = value % MS_HOUR; 
		long minutes = remainder / MS_MINUTE; 
		remainder = remainder % MS_MINUTE; 
		long seconds = remainder / MS_SECOND; 
		//build "hh:mm:ss" 
		StringBuffer buffer = new StringBuffer(); 
		buffer.append(nf.format(hours)); 
		buffer.append( ":" ); 
		buffer.append(nf.format(minutes)); 
		buffer.append( ":" ); 
		buffer.append(nf.format(seconds)); 
		System.out.println(buffer);
		
		return buffer.toString(); 
	}

	
	public static long strtotime(String timestring)
                throws Exception, java.text.ParseException
        {

		DateFormat formatter = new SimpleDateFormat("HH:mm");
		Date d1 = formatter.parse(timestring);
		long dateDiff = d1.getTime();
		return dateDiff;
	
	}


  private void gmdate(String formstring, String timestring)
                throws Exception
        {
       Format formatter;
       formatter = new SimpleDateFormat(""+formstring+"");
       String s = formatter.format(timestring);
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
		out.println("<a href="+classdir+"UniCash?action=addcustomer&custstart="+custstart+"&custstop="+custstop+">Add a Customer</a>");
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
			
                        out.println("<td><a href="+classdir+"UniCash?action=showcustdetail&custnum="+custnum+">");
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
		out.println("<a href="+classdir+"UniCash?action=addcustomer&custstart="+custstart+"&custstop="+custstop+">Add a Customer</a>");

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
		int enum=0;
		int ecustnum=0;
		String brand=null;
		String etype=null;
		String modelnum=null;
		String serialnum=null;
		String filter=null;
		String notes=null;
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
		String wsdate=null;
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
		out.println("<a href="+classdir+"UniCash?action=editcustomer&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Edit Customer Info</a>");
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
		enum=t.getId();
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

                out.println("<tr><td><a href="+classdir+"UniCash?action=editequipment&enum="+enum+"&custnum="+ecustnum+">"+brand+"</a></td><td>"+etype+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+filter+"</td><td>"+notes+"</td><td><a href="+classdir+"UniCash?action=delequip&enum="+enum+"&custnum="+ecustnum+">D</a>elete</td></tr>");
                }
	out.println("</table><br><br>");

		out.println("<a href="+classdir+"UniCash?action=addequipment&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Equipment to Customer File</a>");

		out.println("<P><P>");
		out.println("<h3>Inspection Record</h3>");
 	Statement stmt3 = con.createStatement();
	ResultSet rs3 = stmt3.executeQuery("SELECT * FROM inspection where custnum="+tcustnum+"");
		out.println("<table border=1 width=50%>");
		out.println("<th>Callslip</th><th>Date</th><th></th>");
		while(rs3.next())
                {
		icrecnum=rs3.getInt("crecnum");
		icallslip=rs3.getString("callslip");
		icustnum=rs3.getString("custnum");
		idate=doFormatDate(getDate(rs3.getString("idate")));
                out.println("<tr><td><a href="+classdir+"UniCash?action=editinspection&icrecnum="+icrecnum+"&custnum="+icustnum+"&custstart="+custstart+"&custstop="+custstop+">"+icallslip+"</a></td><td>"+idate+"</td><td><a href="+classdir+"UniCash?action=inspectprint&crecnum="+icrecnum+"&custnum="+icustnum+" target=\"_blank\">Print Format</a></td><td><a href="+classdir+"UniCash?action=delinspection&crecnum="+icrecnum+"&custnum="+icustnum+">D</a>elete<td></tr>");
                }
		out.println("</table><br><br><a href="+classdir+"UniCash?action=addinspection&custnum="+tcustnum+">Add Inspection to Customer Record</a>");	


	out.println("<P><P>");
	out.println("<h3>Customer Call Record</h3>");

 	Statement stmt4 = con.createStatement();
	ResultSet rs4 = stmt4.executeQuery("SELECT * FROM callslip where custnum="+tcustnum+"");
		//out.println("<table border=1 width=50%>");
	out.println("<table border=1 width=100%>");
        out.println("<th>Callslip</th><th>Date</th><th>Reason</th>");
		while(rs4.next())
			{
		ccrecnum=rs4.getInt("crecnum");
		ccallslip=rs4.getString("callslip");
		ccustnum=rs4.getString("custnum");
// Added t to cdate
		tcdate=rs4.getString("cdate");
// Added date formatting
		cdate=doFormatDate(getDate(tcdate));
		creason=rs4.getString("reason");
                out.println("<tr><td><a href="+classdir+"UniCash?action=editcallslip&crecnum="+ccrecnum+"&custnum="+ccustnum+"&callslip="+ccallslip+">"+ccallslip+"</a></td><td>"+cdate+"</td><td>"+creason+"</td><td><a href="+classdir+"UniCash?action=printcallslip&crecnum="+ccrecnum+"&custnum="+ccustnum+" target=\"_blank\">Print Format</a></td><td><a href="+classdir+"UniCash?action=delcallslip&crecnum="+ccrecnum+"&custnum="+ccustnum+">D</a>elete<td></tr>");
                }
out.println("</table><br><br>");

		out.println("<a href="+classdir+"UniCash?action=addcallslip&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Call Slip to Customer File</a>");

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
                out.println("<tr><td><a href=\""+classdir+"UniCash?action=editprevagreement&contnum="+pcontnum+"&&custnum="+pcustnum+"\">"+pcontnum+"</a></td><td>"+pstartdate+"</td><td>"+penddate+"</td><td>"+pcost+"</td><td><a href="+classdir+"UniCash?action=printprevagreement&contnum="+pcontnum+"&custnum="+pcustnum+" target=_blank>Print Format</a></td></tr>");
                }
out.println("</table><br><br>");
		out.println("<a href="+classdir+"UniCash?action=addprevagreement&custnum="+custnum+"&custstart="+custstart+"&custstop="+custstop+">Add Preventative Agreement to Customer File</a>");

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
 	stmt5 = con.createStatement();
	rs5 = stmt5.executeQuery("SELECT * FROM custsurvey where custnum="+custnum+"");
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


}//UniCash
