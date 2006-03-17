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

public class PackageCompare extends UniCash
{
	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, String action, String tcontnum, String tcustnum)
	throws SQLException, TodoException, Exception
{

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
		double totinvestment=0.00;
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

	
	out.println("<html><head><title>Proposal</title>");
	doStyleSheet(req, res, out, session, username);
	out.println("</head><body>");
	doMHeader(req, res, out, session, username); 
	out.println("<h2 align=CENTER>Package Comparison</h2>");
	out.println("<center><table border=1  width=\"95%\" >");
	out.println("<tr><td width=\"33%\" >");
	out.println("<p align=\"left\"><b>Package: "+qdescription+"</b></p><ul>");
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
	
		out.println("<li> "+itemname+"</li>");
		}
	out.println("</ul>");

	out.println("<br><br>");

	 Locale[] locales = NumberFormat.getAvailableLocales();
	  double myNumber = totinvestment;
	   NumberFormat form;
	  form = NumberFormat.getIntegerInstance(locales[1]);
	if (propprice.equalsIgnoreCase("y")) {
	out.println("<p align=\"left\">Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(myNumber)))+"<br></p>");
	} else {
	out.println("<p align=\"left\">Investment: $____________________________<br></p>");
		}

	out.println("</td>");
// GET OPTIONS:

                Vector vo;
                vo = UniQuoteOptions.getAllItems(con,propnum);
                for (int j = 0 ; j < vo.size(); j++)
                {
	out.println("<td   width=\"33%\" >");
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

	out.println("<p align=\"left\"><b>Package: "+qdescription+"</b></p>");

// GET AND PRINT OPTION LIST
		double ototinvestment=0.00;

	out.println("<ul>");
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
		out.println("<li>"+itemname+"</li>");
		}
	out.println("</ul><br>");

	 Locale[] olocales = NumberFormat.getAvailableLocales();
	  double OmyNumber = ototinvestment;
	   NumberFormat oform;
	  oform = NumberFormat.getIntegerInstance(olocales[1]);
	if (propprice.equalsIgnoreCase("y")) {
	out.println("<p align=\"left\">Investment: "+NumberFormat.getCurrencyInstance().format(form.parse(form.format(OmyNumber)))+"<br></p>");
	} else {
	out.println("<p align=\"left\">Investment: $____________________________<br></p>");
		}
	out.println("</td>");

		}
	out.println("</tr>");

	return "true";
}
	
	public String doGetTechInfo_init(String iusername)
        throws Exception
        {
                Vector v;
                v = UniTechInfo.getAllItems(con, iusername);
                int counter=0;
		String tech_init = null;
                for (int i = 0 ; i < v.size(); i++)
                {
                        UniTechInfo t = (UniTechInfo) v.elementAt(i);
                        tech_init = t.getTechInit();
                }
                        return tech_init;                       
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


  public static void doMHeader(HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username)
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


	public static String doGetPropPrice()
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
