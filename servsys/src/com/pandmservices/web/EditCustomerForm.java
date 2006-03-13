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

public class EditCustomerForm
{
	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, String tcustnum, String custstart, String custstop, String action, String csection, String formnum, String questionnum)
	throws SQLException, TodoException, Exception
{
			int recnum=0;
			String formanswer=null;
			if (questionnum==null) {
				questionnum="0";
			}
                		Vector vf;
                		vf = CustFormList.getIndItem(con, formnum);
                       			CustFormList tf = (CustFormList) vf.elementAt(0);
                        		String formname = tf.getFormName();

			int i = Integer.parseInt(questionnum);
			int c=0;
			String formquestion=null;
                		Vector v;
                		v = CustFormParts.getAllItems(con, formnum);
			if (v.size()==0)
			{
			out.println("<html>Form has no questions - use your back button - delete and re-add<br></html>");
			} else {
                       			CustFormParts t = (CustFormParts) v.elementAt(i);
                        		formquestion = t.getFormQuestion();
					formanswer=t.getFormAnswer();
					recnum = t.getRecNum();
	out.println("<html>");
	//out.println("Form Number: "+formnum+"<br>Question Number: "+i+"<br>");
	out.println("<head>");
	out.println("<title>Edit Form Question</title>");
	out.println("</head><body><h1>Customer Form: "+formname+"  <br>Question: "+(i+1)+" of "+v.size()+"</h1><br><br>");
	out.println("<form method=\"post\" action=\""+classdir+"UniCash?action=updatecustformquestion\" name=\"addform\">");
	out.println("<table size=100% border=1>");
	out.println("<tr><td>Form Question:</td><td>");
	out.println(""+formquestion+"</td></tr>");
	out.println("<tr><td>Answer:</td><td>");
	out.println("<textarea name=\"formanswer\" cols=\"60\" rows=\"8\" wrap=\"VIRTUAL\" style=\"width: 500px\">"+formanswer+"</textarea></td></tr>");
	//out.println("<input type=\"text\" name=\"formanswer\" value=\""+formanswer+"\" size=\"60\"></td></tr>");
	out.println("</table>");
	out.println("<p> <CENTER>");
	out.println("<INPUT TYPE=\"hidden\" NAME=\"formnum\" VALUE=\""+formnum+"\">");
	out.println("<input type=\"hidden\" NAME=\"formquestion\" VALUE=\""+formquestion+"\">");
	out.println("<INPUT TYPE=\"hidden\" NAME=\"recnum\" VALUE=\""+recnum+"\">");
	out.println("<INPUT TYPE=\"hidden\" NAME=\"custnum\" VALUE=\""+tcustnum+"\">");
	out.println("<INPUT TYPE=\"hidden\" NAME=\"questionnum\" VALUE=\""+questionnum+"\">");
			if ((Integer.parseInt(questionnum)+1)<v.size()) {
	out.println("<INPUT TYPE=\"submit\" NAME=\"save_next\" VALUE=\"Save-Next\">");
				}
			if (Integer.parseInt(questionnum)>0) {
	out.println("<INPUT TYPE=\"submit\" NAME=\"save_next\" VALUE=\"Save-Prev\">");
						}
	out.println("<INPUT TYPE=\"submit\" NAME=\"save_next\" VALUE=\"Exit\">");
	out.println("<INPUT TYPE=\"reset\">");
	out.println("</CENTER>");
	out.println("</body></html>");
		}
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
