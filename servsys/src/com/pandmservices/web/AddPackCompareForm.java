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

public class AddPackCompareForm
{
	static String desc1="";
	static String desc2="";
	static String desc3="";
	static int best1=0;
	static int best2=0;
	static int best3=0;
	static int best4=0;
	static int best5=0;
	static int better1=0;
	static int better2=0;
	static int better3=0;
	static int better4=0;
	static int better5=0;
	static int good1=0;
	static int good2=0;
	static int good3=0;
	static int good4=0;
	static int good5=0;
	static int best6=0;
	static int best7=0;
	static int best8=0;
	static int better6=0;
	static int better7=0;
	static int better8=0;
	static int good6=0;
	static int good7=0;
	static int good8=0;	
	static String description="-";

	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, String tcustnum, String custsitenum, String sitenum, String packdate)
	throws SQLException, TodoException, Exception
{
	String action=req.getParameter("action");
	String tpropnum=req.getParameter("propnum");
	int custnum=Integer.parseInt(tcustnum);
	int propnum=0;
	int qcustnum=0;
	String pdate=null;
	String psummary="";
	String qstatus="";
	double totinvestment=0.00;
	double ptotal=0.00;
	int counter=0;
	if (action.equalsIgnoreCase("editpackcompare")) {
		propnum=Integer.parseInt(tpropnum);
	Vector vp1;
	vp1 = PackCompare. getIndItems(con,propnum);
	counter=0;
	for (int t = 0 ; t < vp1.size(); t++)
	{
		PackCompare tp1 = (PackCompare) vp1.elementAt(t);
		 int recnum=tp1.getRecNum();
		//wsdate=doFormatDate(getDate(tp.getPackDate()));
		description=tp1.getDescription();
		best1=tp1.getBest1();
		best2=tp1.getBest2();
		best3=tp1.getBest3();
		best4=tp1.getBest4();
		best5=tp1.getBest5();
		best6=tp1.getBest6();
		best7=tp1.getBest7();
		best8=tp1.getBest8();
		better1=tp1.getBetter1();
		better2=tp1.getBetter2();
		better3=tp1.getBetter3();
		better4=tp1.getBetter4();
		better5=tp1.getBetter5();
		better6=tp1.getBetter6();
		better7=tp1.getBetter7();
		better8=tp1.getBetter8();
		good1=tp1.getGood1();
		good2=tp1.getGood2();
		good3=tp1.getGood3();
		good4=tp1.getGood4();
		good5=tp1.getGood5();
		good6=tp1.getGood6();
		good7=tp1.getGood7();
		good8=tp1.getGood8();
		desc1=tp1.getDesc1();
		desc2=tp1.getDesc2();
		desc3=tp1.getDesc3();
	}
	}
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Add Package Compare Form</title>");
	out.println("</head><body><h1>Package Compare Form</h1><br><br>");
	out.println("<table><tr><td><h3>Sales Proposals</h3>");
	Vector vv;
	vv = UniQuotes.getAllItems(con,custnum);
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
		
		out.println("</tr>");
	}
	out.println("</table></td><td><h3>Proposals to Compare</h3>");
	if (action.equalsIgnoreCase("editpackcompare")) {
	out.println("<form method=\"post\" action=\""+classdir+"UniCash?action=updatepackagecompareform&propnum="+tpropnum+"\" name=\"addform\">");
	} else {
		out.println("<form method=\"post\" action=\""+classdir+"UniCash?action=savepackagecompareform\" name=\"addform\">");
	}
	out.println("<table size=100% border=1>");
	out.println("<tr><td>Description:</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"description\" VALUE=\""+description+"\"></td></tr>");
	out.println("<tr><td>Best Description</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"desc1\" VALUE=\""+desc1+"\"></td></tr>");
	out.println("<tr><td>Best 1</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"best1\" VALUE=\""+best1+"\"></td></tr>");
	out.println("<tr><td>Best 2</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"best2\" VALUE=\""+best2+"\"></td></tr>");
	out.println("<tr><td>Best 3</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"best3\" VALUE=\""+best3+"\"></td></tr>");
	out.println("<tr><td>Best 4</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"best4\" VALUE=\""+best4+"\"></td></tr>");
	out.println("<tr><td>Best 5</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"best5\" VALUE=\""+best5+"\"></td></tr>");
	out.println("<tr><td>Best 6</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"best6\" VALUE=\""+best6+"\"></td></tr>");
	out.println("<tr><td>Best 7</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"best7\" VALUE=\""+best7+"\"></td></tr>");
	out.println("<tr><td>Best 8</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"best8\" VALUE=\""+best8+"\"></td></tr>");
	out.println("<tr><td>Better Description</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"desc2\" VALUE=\""+desc2+"\"></td></tr>");
	out.println("<tr><td>Better 1</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"better1\" VALUE=\""+better1+"\"></td></tr>");
	out.println("<tr><td>Better 2</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"better2\" VALUE=\""+better2+"\"></td></tr>");
	out.println("<tr><td>Better 3</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"better3\" VALUE=\""+better3+"\"></td></tr>");
	out.println("<tr><td>Better 4</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"better4\" VALUE=\""+better4+"\"></td></tr>");
	out.println("<tr><td>Better 5</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"better5\" VALUE=\""+better5+"\"></td></tr>");
	out.println("<tr><td>Better 6</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"better6\" VALUE=\""+better6+"\"></td></tr>");
	out.println("<tr><td>Better 7</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"better7\" VALUE=\""+better7+"\"></td></tr>");
	out.println("<tr><td>Better 8</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"better8\" VALUE=\""+better8+"\"></td></tr>");
	out.println("<tr><td>Good Description</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"desc3\" VALUE=\""+desc3+"\"></td></tr>");
	out.println("<tr><td>Good 1</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"good1\" VALUE=\""+best1+"\"></td></tr>");
	out.println("<tr><td>Good 2</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"good2\" VALUE=\""+good2+"\"></td></tr>");
	out.println("<tr><td>Good 3</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"good3\" VALUE=\""+good3+"\"></td></tr>");
	out.println("<tr><td>Good 4</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"good4\" VALUE=\""+good4+"\"></td></tr>");
	out.println("<tr><td>Good 5</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"good5\" VALUE=\""+good5+"\"></td></tr>");
	out.println("<tr><td>Good 6</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"good6\" VALUE=\""+good6+"\"></td></tr>");
	out.println("<tr><td>Good 7</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"good7\" VALUE=\""+good7+"\"></td></tr>");
	out.println("<tr><td>Good 8</td><td>");
	out.println("<INPUT TYPE=\"text\" NAME=\"good8\" VALUE=\""+good8+"\"></td></tr>");
	out.println("</table>");
	out.println("<p> <CENTER>");
	out.println("<INPUT TYPE=\"hidden\" NAME=\"custsitenum\" VALUE=\""+custsitenum+"\">");
	out.println("<input type=\"hidden\" NAME=\"sitenum\" VALUE=\""+sitenum+"\">");
	out.println("<input type=\"hidden\" NAME=\"packdate\" VALUE=\""+packdate+"\">");
	out.println("<INPUT TYPE=\"hidden\" NAME=\"custnum\" VALUE=\""+tcustnum+"\">");
	out.println("<INPUT TYPE=\"submit\" NAME=\"save\" VALUE=\"Save\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER></td></tr></table>");
	out.println("</body></html>");
	return "true";
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
