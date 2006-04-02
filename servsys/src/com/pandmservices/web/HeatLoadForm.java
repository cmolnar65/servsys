package com.pandmservices.web;
import java.util.Date;
import java.math.*;
import java.text.*;
import java.lang.String;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import com.pandmservices.*;
import com.pandmservices.support.*;
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

public class HeatLoadForm
{
	int propnum=0;
	int qcustnum=0;
	String pdate=null;
	String psummary="";
	String qstatus="";
	static String mbody="";
		static int ceilingsqft=0;
		static int wallsqft=0;
		static int recnum=0;
		static int nwinsqft=0;
		static int swinsqft=0;
		static int ewinsqft=0;
		static int wwinsqft=0;
		static int nsldoorsqft=0;
		static int nsldoor=0;
		static int ssldoor=0;
		static int wsldoor=0;
		static int esldoor=0;
		static int ssldoorsqft=0;
		static int esldoorsqft=0;
		static int wsldoorsqft=0;
		static int doorsqft=0;
		static int totglasssqft=0;
		static int nframewall=0;
		static int nmasawall=0;
		static int nmasbwall=0;
		static int basfloorsqft=0;
		static int slabnoperim=0;
		static int slabwperim=0;
		static int flooruncond=0;
		static int mechvent=0;
		static int mechventcfm=0;
		static int numpeople=0;
		static int bldlength=0;
		static int bldwidth=0;
		static int bldheight=0;
		static String custsitenum = "-";
		static String sitenum = "-";
		static String ceiltype = "-";
		static String walltype = "-";
		static String nwintype = "-";
		static String swintype = "-";
		static String ewintype = "-";
		static String wwintype = "-";
		static String nsldoortype = "-";
		static String ssldoortype = "-";
		static String esldoortype = "-";
		static String wsldoortype = "-";
		static String doortype = "-";
		static String nframewalltype = "-";
		static String nmasawalltype = "-";
		static String nmasbwalltype = "-";
		static String slabnoperimtype = "-";
		static String slabwperimtype = "-";
		static String flooruncondtype = "-";
		static String loaddate="-";

	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, String tcustnum, String custsitenum, String sitenum, String packdate, String trecnum)
	throws SQLException, TodoException, Exception
{
	mbody="";
	String action=req.getParameter("action");
	
	int custnum=Integer.parseInt(tcustnum);
	
		
	if (action.equalsIgnoreCase("editheatload")) 
	{
	int irecnum=Integer.parseInt(trecnum);
	Vector vh;
	vh = HeatLoad.getIndItems(con,irecnum, 0);
	for (int ih = 0 ; ih < vh.size(); ih++)
	{
		HeatLoad th = (HeatLoad) vh.elementAt(ih);		
		loaddate=doFormatDate(getDate(th.loaddate()));
		ceilingsqft=th.ceilingsqft();
		wallsqft=th.wallsqft();
		recnum=th.recnum();
		nwinsqft=th.nwinsqft();
		swinsqft=th.swinsqft();
		ewinsqft=th.ewinsqft();
		wwinsqft=th.wwinsqft();
		nsldoor=th.nsldoorsqft();
		ssldoor=th.ssldoorsqft();
		esldoor=th.esldoorsqft();
		wsldoor=th.wsldoorsqft();
		doorsqft=th.doorsqft();
		totglasssqft=th.totglasssqft();
		nframewall=th.nframewall();
		nmasawall=th.nmasawall();
		nmasbwall=th.nmasbwall();
		basfloorsqft=th.basfloorsqft();
		slabnoperim=th.slabnoperim();
		slabwperim=th.slabwperim();
		flooruncond=th.flooruncond();
		mechventcfm=th.mechvent();
		numpeople=th.numpeople();
		bldlength=th.bldlength();
		bldwidth=th.bldwidth();
		bldheight=th.bldheight();
		custsitenum = th.custsitenum();
		sitenum = th.sitenum();
		ceiltype = th.ceiltype();
		walltype = th.walltype();
		nwintype = th.nwintype();
		swintype = th.swintype();
		ewintype = th.ewintype();
		wwintype = th.wwintype();
		nsldoortype = th.nsldoortype();
		ssldoortype = th.ssldoortype();
		esldoortype = th.esldoortype();
		wsldoortype = th.wsldoortype();
		doortype = th.doortype();
		nframewalltype = th.nframewalltype();
		nmasawalltype = th.nmasawalltype();
		nmasbwalltype = th.nmasbwalltype();
		slabnoperimtype = th.slabnoperimtype();
		slabwperimtype = th.slabwperimtype();
		flooruncondtype = th.flooruncondtype();
		mbody=combinestring(mbody,""+irecnum+"");
	}
		
} else if (action.equalsIgnoreCase("addheatload")) 
{
	// placeholder
}
	
	
	mbody=combinestring(mbody,"");
	if (action.equalsIgnoreCase("editheatload")) {
	mbody=combinestring(mbody,"<form method=\"post\" action=\""+classdir+"UniCash?action=updateheatload&recnum="+recnum+" \" name=\"addheatload\">");
	} else
	{
		mbody=combinestring(mbody,"<form method=\"post\" action=\""+classdir+"UniCash?action=saveheatload \" name=\"addheatload\">");
	}
	mbody=combinestring(mbody,"<html><body><table border=1 width=\"90%\">");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Area        </td><td></td><td align=\"left\">Value            </td><td align=\"left\">Type</td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Ceiling Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"ceilingsqft\" value=\""+ceilingsqft+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"ceiltype\">");
                Vector vp = SupCeilType.getAllItems(con);
		
		mbody=combinestring(mbody,"<option value=\""+ceiltype+"\">"+ceiltype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SupCeilType t = (SupCeilType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SupCeilingType()+">"+t.SupCeilingType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Wall Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"wallsqft\" value=\""+wallsqft+"\"></td></tr>");
		mbody=combinestring(mbody,"<td align=\"left\"></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">North Window Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"nwinsqft\" value=\""+nwinsqft+"\"></td>");
mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"nwintype\">");	
        vp = SupWinType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+nwintype+"\">"+nwintype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SupWinType t = (SupWinType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SupWinType()+">"+t.SupWinType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">South Window Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"swinsqft\" value=\""+swinsqft+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"swintype\">");
	        vp = SupWinType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+swintype+"\">"+swintype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SupWinType t = (SupWinType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SupWinType()+">"+t.SupWinType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">East Window Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"ewinsqft\" value=\""+ewinsqft+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"ewintype\">");
	        vp = SupWinType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+ewintype+"\">"+ewintype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SupWinType t = (SupWinType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SupWinType()+">"+t.SupWinType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">West Window Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"wwinsqft\" value=\""+wwinsqft+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"wwintype\">");
	        vp = SupWinType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+wwintype+"\">"+wwintype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SupWinType t = (SupWinType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SupWinType()+">"+t.SupWinType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Total Glass Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"totglasssqft\" value=\""+totglasssqft+"\"></td>");
		mbody=combinestring(mbody,"<td align=\"left\"></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">North Slider Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"nsldoor\" value=\""+nsldoor+"\"></td>");
		mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"nsldoortype\">");
		vp = SupWinType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+nsldoortype+"\">"+nsldoortype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SupWinType t = (SupWinType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SupWinType()+">"+t.SupWinType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
		mbody=combinestring(mbody,"<tr><td align=\"left\">South Slider Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"ssldoor\" value=\""+ssldoor+"\"></td>");
		mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"ssldoortype\">");
                vp = SupWinType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+ssldoortype+"\">"+ssldoortype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SupWinType t = (SupWinType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SupWinType()+">"+t.SupWinType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">East Slider Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"esldoor\" value=\""+esldoor+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"esldoortype\">");
                vp = SupWinType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+esldoortype+"\">"+esldoortype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SupWinType t = (SupWinType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SupWinType()+">"+t.SupWinType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">West Slider Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"wsldoor\" value=\""+wsldoor+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"wsldoortype\">");
                vp = SupWinType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+wsldoortype+"\">"+wsldoortype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SupWinType t = (SupWinType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SupWinType()+">"+t.SupWinType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Door Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"doorsqft\" value=\""+doorsqft+"\"></td>");
		mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"doortype\">");
                vp = DoorType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+doortype+"\">"+doortype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                DoorType t = (DoorType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.DoorType()+">"+t.DoorType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Net Frame Wall Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"nframewall\" value=\""+nframewall+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"nframewalltype\">");
                vp = FWallType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+nframewalltype+"\">"+nframewalltype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                FWallType t = (FWallType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.FWallType()+">"+t.FWallType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Net Masonary Wall Sq. Ft.<br>Above Grade</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"nmasawall\" value=\""+nmasawall+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"nmasawalltype\">");
                vp = MasAWallType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+nmasawalltype+"\">"+nmasawalltype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                MasAWallType t = (MasAWallType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.MasAWallType()+">"+t.MasAWallType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Net Masonary Wall Sq. Ft.<br>Below Grade</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"nmasbwall\" value=\""+nmasbwall+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"nmasbwalltype\">");
                vp = MasBWallType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+nmasbwalltype+"\">"+nmasbwalltype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                MasBWallType t = (MasBWallType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.MasBWallType()+">"+t.MasBWallType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Basement Floor Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"basfloorsqft\" value=\""+basfloorsqft+"\"></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Slab W/O Perimiter Linear Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"slabnoperim\" value=\""+slabnoperim+"\"></td>");
	mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"slabnoperimtype\">");
                vp = SlabNoType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+slabnoperimtype+"\">"+slabnoperimtype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SlabNoType t = (SlabNoType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SlabNoType()+">"+t.SlabNoType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Slab W/ Perimeter Linear Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"slabwperim\" value=\""+slabwperim+"\"></td>");
		mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"slabwperimtype\">");
                vp = SlabPerimType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+slabwperimtype+"\">"+slabwperimtype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                SlabPerimType t = (SlabPerimType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.SlabPerimType()+">"+t.SlabPerimType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Floor Over Unconditioned<br>Space Sq. Ft.</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"flooruncond\" value=\""+flooruncond+"\"></td>");
		mbody=combinestring(mbody,"<td align=\"left\"><select width=\"50\" name=\"flooruncondtype\">");
                vp = UncondFloorType.getAllItems(con);
		mbody=combinestring(mbody,"<option value=\""+flooruncondtype+"\">"+flooruncondtype+"</option>");
		mbody=combinestring(mbody,"<option value=\"-\">-</option>");
                for (int i = 0 ; i < vp.size(); i++)
                {
                UncondFloorType t = (UncondFloorType) vp.elementAt(i);
		mbody=combinestring(mbody,"<option value="+t.UncondFloorType()+">"+t.UncondFloorType()+"</option>");
		}
		mbody=combinestring(mbody,"</select></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Mechanical Ventilation CFM</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"mechventcfm\" value=\""+mechventcfm+"\"></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Number of People</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"numpeople\" value=\""+numpeople+"\"></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Bulding Length</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"bldlength\" value=\""+bldlength+"\"></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Building Width</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"bldwidth\" value=\""+bldwidth+"\"></td></tr>");
	mbody=combinestring(mbody,"<tr><td align=\"left\">Building Height</td><td align=\"left\"> </td><td align=\"left\"><input type=\"text\" name=\"bldheight\" value=\""+bldheight+"\"></td></tr>");
	mbody=combinestring(mbody,"</table>");
	mbody=combinestring(mbody,"<p> <CENTER><INPUT TYPE=\"hidden\" name=\"custnum\" value=\""+tcustnum+"\">");
	mbody=combinestring(mbody,"<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"Save\">");
	mbody=combinestring(mbody,"<INPUT TYPE=\"reset\">");
	mbody=combinestring(mbody,"</CENTER></form></html>");
	mbody=combinestring(mbody,"</html>");
	return mbody;
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
