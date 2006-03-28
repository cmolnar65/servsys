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

public class PackageCompare2 extends UniCash
{
	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, String action, String tcontnum, String tcustnum)
	throws SQLException, TodoException, Exception
{

       		 int custnum = Integer.parseInt(tcustnum);
       		 int intcustnum = Integer.parseInt(tcustnum);
       		 int propnum = Integer.parseInt(tcontnum);
		String propprice = doGetPropPrice();

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
		double totinvestment1=0.00;
		double totinvestment2=0.00;
		double totinvestment3=0.00;
		double totinvestment4=0.00;
		double totinvestment5=0.00;
		double totinvestment6=0.00;
		double totinvestment7=0.00;
		double totinvestment8=0.00;
		double totinvestment9=0.00;
		double totinvestment10=0.00;
		double totinvestment11=0.00;
		double totinvestment12=0.00;
		double totinvestment13=0.00;
		double totinvestment14=0.00;
		double totinvestment15=0.00;
		String bestdesc="";
		String betterdesc="";
		String gooddesc="";
		String description1="";
		String description2="";
		String description3="";
		String description4="";
		String description5="";
		String description6="";
		String description7="";
		String description8="";
		String description9="";
		String description10="";
		String description11="";
		String description12="";
		String description13="";
		String description14="";
		String description15="";
		String investment=null;
		double qtotal=0.00;
		double ptotal=0.00;
		double besttotal=0.00;
		double bettertotal=0.00;
		double goodtotal=0.00;

	
	Vector vp1;
	vp1 = PackCompare. getIndItems(con,propnum);
	counter=0;
	for (int t = 0 ; t < vp1.size(); t++)
	{
		PackCompare tp1 = (PackCompare) vp1.elementAt(t);
		 int recnum=tp1.getRecNum();
		 
		//wsdate=doFormatDate(getDate(tp.getPackDate()));
		String description=tp1.getDescription();
		int best1=tp1.getBest1();
		int best2=tp1.getBest2();
		int best3=tp1.getBest3();
		int best4=tp1.getBest4();
		int best5=tp1.getBest5();
		int better1=tp1.getBetter1();
		int better2=tp1.getBetter2();
		int better3=tp1.getBetter3();
		int better4=tp1.getBetter4();
		int better5=tp1.getBetter5();
		int good1=tp1.getGood1();
		int good2=tp1.getGood2();
		int good3=tp1.getGood3();
		int good4=tp1.getGood4();
		int good5=tp1.getGood5();
		bestdesc=tp1.getDesc1();
		betterdesc=tp1.getDesc2();
		gooddesc=tp1.getDesc3();

// Get quote information here #1
	Vector vv;
	vv = UniQuotes.getIndItem(con,intcustnum, best1);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description1=tt.getQDescription();
		ptotal=0.00;
		totinvestment1=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,best1);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment1=totinvestment1+ptotal;
		}
		
	}
		

// Get quote information here #2
	vv = UniQuotes.getIndItem(con,intcustnum, best2);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description2=tt.getQDescription();
		ptotal=0.00;
		totinvestment2=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,best2);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment2=totinvestment2+ptotal;
		}
	}

// Get quote information here #3
	vv = UniQuotes.getIndItem(con,intcustnum, best3);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description3=tt.getQDescription();
		ptotal=0.00;
		totinvestment3=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,best3);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment3=totinvestment3+ptotal;
		}
	}

// Get quote information here #4
	vv = UniQuotes.getIndItem(con,intcustnum, best4);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description4=tt.getQDescription();
		ptotal=0.00;
		totinvestment4=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,best4);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment4=totinvestment4+ptotal;
		}
	}

// Get quote information here #5
	vv = UniQuotes.getIndItem(con,intcustnum, best5);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description5=tt.getQDescription();
		ptotal=0.00;
		totinvestment5=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,best5);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment5=totinvestment5+ptotal;
		}
	}
	besttotal=totinvestment1+totinvestment2+totinvestment3+totinvestment4+totinvestment5;
// Get quote information here #6
	vv = UniQuotes.getIndItem(con,intcustnum, better1);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description6=tt.getQDescription();
		ptotal=0.00;
		totinvestment6=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,better1);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment6=totinvestment6+ptotal;
		}
	}

// Get quote information here #7
	vv = UniQuotes.getIndItem(con,intcustnum, better2);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description7=tt.getQDescription();
		ptotal=0.00;
		totinvestment7=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,better2);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment7=totinvestment7+ptotal;
		}
	}

// Get quote information here #8
	vv = UniQuotes.getIndItem(con,intcustnum, better3);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description8=tt.getQDescription();
		ptotal=0.00;
		totinvestment8=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,better3);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment8=totinvestment8+ptotal;
		}
	}

// Get quote information here #9
	vv = UniQuotes.getIndItem(con,intcustnum, better4);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description9=tt.getQDescription();
		ptotal=0.00;
		totinvestment9=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,better4);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment9=totinvestment9+ptotal;
		}
	}

// Get quote information here #10
	vv = UniQuotes.getIndItem(con,intcustnum, better5);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description10=tt.getQDescription();
		ptotal=0.00;
		totinvestment10=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,better5);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment10=totinvestment10+ptotal;
		}
	}

	bettertotal=totinvestment6+totinvestment7+totinvestment8+totinvestment9+totinvestment10;
// Get quote information here #11
	vv = UniQuotes.getIndItem(con,intcustnum, good1);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description11=tt.getQDescription();
		ptotal=0.00;
		totinvestment11=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,good1);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment11=totinvestment11+ptotal;
		}
	}

// Get quote information here #12
	vv = UniQuotes.getIndItem(con,intcustnum, good2);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description12=tt.getQDescription();
		ptotal=0.00;
		totinvestment12=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,good2);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment12=totinvestment12+ptotal;
		}
	}

// Get quote information here #13
	vv = UniQuotes.getIndItem(con,intcustnum, good3);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description13=tt.getQDescription();
		ptotal=0.00;
		totinvestment13=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,good3);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment13=totinvestment13+ptotal;
		}
	}

// Get quote information here #14
	vv = UniQuotes.getIndItem(con,intcustnum, good4);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description14=tt.getQDescription();
		ptotal=0.00;
		totinvestment14=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,good4);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment14=totinvestment14+ptotal;
		}
	}

// Get quote information here #15
	vv = UniQuotes.getIndItem(con,intcustnum, good5);
	for (int i = 0 ; i < vv.size(); i++)
	{
		UniQuotes tt = (UniQuotes) vv.elementAt(i);
		description15=tt.getQDescription();
		ptotal=0.00;
		totinvestment15=0.00;
		Vector vp;
		vp = UniQuoteParts.getAllItems(con,good5);
		for (int j = 0 ; j < vp.size(); j++)
		{
			UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
			ptotal=tp.getQuoteTotal();
			totinvestment15=totinvestment15+ptotal;
		}
	}


	goodtotal=totinvestment11+totinvestment12+totinvestment13+totinvestment14+totinvestment15;
		
	
	out.println("<html><head><title>Proposal</title>");
	doStyleSheet(req, res, out, session, username);
	out.println("</head><body>");
	doMHeader(req, res, out, session, username); 
	out.println("<h2 align=CENTER>Package Comparison</h2>");
	out.println("<center><table border=1  width=\"95%\" >");
	out.println("<tr>");
	// Best Package Here
	if (bestdesc.length()>1) {
	out.println("<td width=\"33%\" valign=\"top\">");
	out.println("<p align=\"left\"><b>"+bestdesc+"</b></p><ul>");
	if (description1.length()>1) {
	out.println("<p align=\"left\">"+description1+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment1)+"</p><br>");
				}
	if (description2.length()>1) {
	out.println("<p align=\"left\">"+description2+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment2)+"</p><br>");
			}
	if (description3.length()>1) {
	out.println("<p align=\"left\">"+description3+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment3)+"</p><br>");
			}
	if (description4.length()>1) {
	out.println("<p align=\"left\">"+description4+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment4)+"</p><br>");
			}
	if (description5.length()>1) {
	out.println("<p align=\"left\">"+description5+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment5)+"</p><br></ul>");
			}
	out.println("<b><p align=\"left\">Package Total Price:&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(besttotal)+"</p></b><br>");
	out.println("</td>");
		}	
	// Better Package Here
	if (betterdesc.length()>1) {
	out.println("<td width=\"33%\"  valign=\"top\">");
	out.println("<p align=\"left\"><b>"+betterdesc+"</b></p><ul>");

	if (description6.length()>1) {
	out.println("<p align=\"left\">"+description6+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment6)+"</p><br>");
				}
	if (description7.length()>1) {
	out.println("<p align=\"left\">"+description7+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment7)+"</p><br>");
			}
	if (description8.length()>1) {
	out.println("<p align=\"left\">"+description8+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment8)+"</p><br>");
			}
	if (description9.length()>1) {
	out.println("<p align=\"left\">"+description9+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment9)+"</p><br>");
			}
	if (description10.length()>1) {
	out.println("<p align=\"left\">"+description10+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment10)+"</p><br></ul>");
			}
	out.println("<b><p align=\"left\">Package Total Price:&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(bettertotal)+"</p></b><br>");
	out.println("</td>");
		}

	// Good Package Here
	if (gooddesc.length()>1) {
	out.println("<td width=\"33%\"  valign=\"top\">");
	out.println("<p align=\"left\"><b>"+gooddesc+"</b></p><ul>");
	if (description11.length()>1) {
	out.println("<p align=\"left\">"+description11+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment11)+"</p><br>");
				}
	if (description12.length()>1) {
	out.println("<p align=\"left\">"+description12+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment12)+"</p><br>");
			}
	if (description13.length()>1) {
	out.println("<p align=\"left\">"+description13+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment13)+"</p><br>");
			}
	if (description14.length()>1) {
	out.println("<p align=\"left\">"+description14+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment14)+"</p><br>");
			}
	if (description15.length()>1) {
	out.println("<p align=\"left\">"+description15+"&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(totinvestment15)+"</p><br></ul>");
			}
	out.println("<b><p align=\"left\">Package Total Price:&nbsp;&nbsp;&nbsp;&nbsp;"+NumberFormat.getCurrencyInstance().format(goodtotal)+"</p></b><br>");
	out.println("</td>");
	}
	out.println("</tr>");

}

	return "true";
}
	
	public static String doGetTechInfo_init(String iusername)
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
