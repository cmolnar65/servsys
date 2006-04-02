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

public class HeatLoadPrint extends UniCash
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
	}
		String cname="";
		String address1="";
		String address2="";
		String city="";
		String state="";
		String zip="";
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
			}
	
	mbody=combinestring(mbody,"");
	mbody=combinestring(mbody,"<html><head><title>Proposal</title>");
	mbody=doStyleSheetString(req, res, out, session, username,mbody);
	mbody=combinestring(mbody,"</head><body>");
	mbody=doMHeaderString(req, res, out, session, username,mbody); 
	mbody=combinestring(mbody,"<h2 align=CENTER>Heat Load</h2><br><br>"+cname+"<br>"+address1+"<br>"+address2+"<br>"+city+", "+state+"  "+zip+"<br>");
	
	mbody=combinestring(mbody,"<table border=1 width=\"90%\">");	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Area        </td><td></td><td align=\"left\">Value            </td><td align=\"left\">Type</td><td>Heating Load</td><td>Cooling Load</td></tr>");
	Vector vl1;
	Double ceilheat=0.00;
	Double ceilcool=0.00;
	//String heatvalue="";
	//String coolvalue="";
	vl1 = HeatLoadValues.getIndItem(con,"ceiltype", ceiltype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String heatvalue=th.heatvalue();
		String coolvalue=th.coolvalue();
		ceilheat=Double.parseDouble(heatvalue);
		ceilcool=Double.parseDouble(coolvalue);		
	}
	Double ceilcoolbtu=ceilcool*ceilingsqft;
	Double ceilheatbtu=ceilheat*ceilingsqft;	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Ceiling Sq. Ft.</td><td align=\"left\"></td><td align=\"left\">"+ceilingsqft+"</td><td>"+ceiltype+"</td><td>"+ceilheatbtu+"</td><td>"+ceilcoolbtu+"</td></tr>");

	Double nwinheat=0.00;
	Double nwincool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"nwintype", nwintype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		nwinheat=Double.parseDouble(heatvalue);
		nwincool=Double.parseDouble(coolvalue);		
	}
	Double nwincoolbtu=nwincool*nwinsqft;
	Double nwinheatbtu=nwinheat*nwinsqft;
	
	Double swinheat=0.00;
	Double swincool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"swintype", swintype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		swinheat=Double.parseDouble(heatvalue);
		swincool=Double.parseDouble(coolvalue);		
	}
	Double swincoolbtu=swincool*swinsqft;
	Double swinheatbtu=swinheat*swinsqft;

	Double ewinheat=0.00;
	Double ewincool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"ewintype", ewintype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		ewinheat=Double.parseDouble(heatvalue);
		ewincool=Double.parseDouble(coolvalue);		
	}
	Double ewincoolbtu=ewincool*ewinsqft;
	Double ewinheatbtu=ewinheat*ewinsqft;
	
	Double wwinheat=0.00;
	Double wwincool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"wwintype", wwintype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		wwinheat=Double.parseDouble(heatvalue);
		wwincool=Double.parseDouble(coolvalue);		
	}
	Double wwincoolbtu=wwincool*wwinsqft;
	Double wwinheatbtu=wwinheat*wwinsqft;

	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Wall Sq. Ft.</td><td align=\"left\"></td><td align=\"left\">"+wallsqft+"</td><td>"+walltype+"</td><td></td><td></td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">North Windows Sq. Ft.</td><td align=\"left\"></td><td align=\"left\">"+nwinsqft+"</td><td>"+nwintype+"</td><td>"+nwinheatbtu+"</td><td>"+nwincoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">South Windows Sq. Ft.</td><td align=\"left\"></td><td align=\"left\">"+swinsqft+"</td><td>"+swintype+"</td><td>"+swinheatbtu+"</td><td>"+swincoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">West Windows Sq. Ft.</td><td align=\"left\"></td><td align=\"left\">"+wwinsqft+"</td><td>"+wwintype+"</td><td>"+wwinheatbtu+"</td><td>"+wwincoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">East Windows Sq. Ft.</td><td align=\"left\"></td><td align=\"left\">"+ewinsqft+"</td><td>"+ewintype+"</td><td>"+ewinheatbtu+"</td><td>"+ewincoolbtu+"</td></tr>");
	
	
	Double nsldoorheat=0.00;
	Double nsldoorcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"nsldoor", nsldoortype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		nsldoorheat=Double.parseDouble(heatvalue);
		nsldoorcool=Double.parseDouble(coolvalue);		
	}
	Double nsldoorcoolbtu=nsldoorcool*nsldoor;
	Double nsldoorheatbtu=nsldoorheat*nsldoor;
	
	Double ssldoorheat=0.00;
	Double ssldoorcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"ssldoor", ssldoortype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		ssldoorheat=Double.parseDouble(heatvalue);
		ssldoorcool=Double.parseDouble(coolvalue);		
	}
	Double ssldoorcoolbtu=ssldoorcool*ssldoor;
	Double ssldoorheatbtu=ssldoorheat*ssldoor;

	Double esldoorheat=0.00;
	Double esldoorcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"esldoor", esldoortype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		esldoorheat=Double.parseDouble(heatvalue);
		esldoorcool=Double.parseDouble(coolvalue);		
	}
	Double esldoorcoolbtu=esldoorcool*esldoor;
	Double esldoorheatbtu=esldoorheat*esldoor;
	
	Double wsldoorheat=0.00;
	Double wsldoorcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"wsldoor", wsldoortype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		wsldoorheat=Double.parseDouble(heatvalue);
		wsldoorcool=Double.parseDouble(coolvalue);		
	}
	Double wsldoorcoolbtu=wsldoorcool*wsldoor;
	Double wsldoorheatbtu=wsldoorheat*wsldoor;

	
	mbody=combinestring(mbody,"<tr><td align=\"left\">North Sliding Doors</td><td align=\"left\"></td><td align=\"left\">"+nsldoor+"</td><td>"+nsldoortype+"</td><td>"+nsldoorheatbtu+"</td><td>"+nsldoorcoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">South Sliding Doors</td><td align=\"left\"></td><td align=\"left\">"+ssldoor+"</td><td>"+ssldoortype+"</td><td>"+ssldoorheatbtu+"</td><td>"+ssldoorcoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">West Sliding Doors</td><td align=\"left\"></td><td align=\"left\">"+wsldoor+"</td><td>"+wsldoortype+"</td><td>"+wsldoorheatbtu+"</td><td>"+wsldoorcoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">East Sliding Doors</td><td align=\"left\"></td><td align=\"left\">"+esldoor+"</td><td>"+esldoortype+"</td><td>"+esldoorheatbtu+"</td><td>"+esldoorcoolbtu+"</td></tr>");
	
	
	Double doorheat=0.00;
	Double doorcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"doortype", doortype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		doorheat=Double.parseDouble(heatvalue);
		doorcool=Double.parseDouble(coolvalue);		
	}
	Double doorcoolbtu=doorcool*doorsqft;
	Double doorheatbtu=doorheat*doorsqft;

	
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Door Sq. Ft.</td><td align=\"left\"></td><td align=\"left\">"+doorsqft+"</td><td>"+doortype+"</td><td>"+doorheatbtu+"</td><td>"+doorcoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Total Glass Area</td><td align=\"left\"></td><td align=\"left\">"+totglasssqft+"</td><td></td><td></td><td></td></tr>");
	Double nframewallheat=0.00;
	Double nframewallcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"nframewall", nframewalltype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		nframewallheat=Double.parseDouble(heatvalue);
		nframewallcool=Double.parseDouble(coolvalue);		
	}
	Double nframewallcoolbtu=nframewallcool*nframewall;
	Double nframewallheatbtu=nframewallheat*nframewall;

	mbody=combinestring(mbody,"<tr><td align=\"left\">Net Frame Wall Area</td><td align=\"left\"></td><td align=\"left\">"+nframewall+"</td><td>"+nframewalltype+"</td><td>"+nframewallheatbtu+"</td><td>"+nframewallcoolbtu+"</td></tr>");
	Double nmasawallheat=0.00;
	Double nmasawallcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"nmasawall", nmasawalltype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		nmasawallheat=Double.parseDouble(heatvalue);
		nmasawallcool=Double.parseDouble(coolvalue);		
	}
	Double nmasawallcoolbtu=nmasawallcool*nmasawall;
	Double nmasawallheatbtu=nmasawallheat*nmasawall;
	
	Double nmasbwallheat=0.00;
	Double nmasbwallcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"nmasbwall", nmasbwalltype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		nmasbwallheat=Double.parseDouble(heatvalue);
		nmasbwallcool=Double.parseDouble(coolvalue);		
	}
	Double nmasbwallcoolbtu=nmasbwallcool*nmasbwall;
	Double nmasbwallheatbtu=nmasbwallheat*nmasbwall;
	
	Double basfloorheat=0.00;
	Double basfloorcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"basfloor", "All");
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		basfloorheat=Double.parseDouble(heatvalue);
		basfloorcool=Double.parseDouble(coolvalue);		
	}
	Double basfloorcoolbtu=basfloorcool*basfloorsqft;
	Double basfloorheatbtu=basfloorheat*basfloorsqft;	
		
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Net Masonary Wall<br>Above Grade</td><td align=\"left\"></td><td align=\"left\">"+nmasawall+"</td><td>"+nmasawalltype+"</td><td>"+nmasawallheatbtu+"</td><td>"+nmasawallcoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Net Masonary Wall<br>Below Grade</td><td align=\"left\"></td><td align=\"left\">"+nmasbwall+"</td><td>"+nmasbwalltype+"</td><td>"+nmasbwallheatbtu+"</td><td>"+nmasbwallcoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Basement Floor Sq. Ft.</td><td align=\"left\"></td><td align=\"left\">"+basfloorsqft+"</td><td></td><td>"+basfloorheatbtu+"</td><td>"+basfloorcoolbtu+"</td></tr>");
	
	Double slabnoperimheat=0.00;
	Double slabnoperimcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"slabnoperim", slabnoperimtype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		slabnoperimheat=Double.parseDouble(heatvalue);
		slabnoperimcool=Double.parseDouble(coolvalue);		
	}
	Double slabnoperimcoolbtu=slabnoperimcool*slabnoperim;
	Double slabnoperimheatbtu=slabnoperimheat*slabnoperim;
	
	Double slabwperimheat=0.00;
	Double slabwperimcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"slabwperim", slabwperimtype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		slabwperimheat=Double.parseDouble(heatvalue);
		slabwperimcool=Double.parseDouble(coolvalue);		
	}
	Double slabwperimcoolbtu=slabwperimcool*slabwperim;
	Double slabwperimheatbtu=slabwperimheat*slabwperim;
	
	Double flooruncondheat=0.00;
	Double flooruncondcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"flooruncond", flooruncondtype);
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		flooruncondheat=Double.parseDouble(heatvalue);
		flooruncondcool=Double.parseDouble(coolvalue);		
	}
	Double flooruncondcoolbtu=flooruncondcool*flooruncond;
	Double flooruncondheatbtu=flooruncondheat*flooruncond;	

	

	mbody=combinestring(mbody,"<tr><td align=\"left\">Slab Floor Linear Ft<br>No Perimiter</td><td align=\"left\"></td><td align=\"left\">"+slabnoperim+"</td><td>"+slabnoperimtype+"</td><td>"+slabnoperimheatbtu+"</td><td>"+slabnoperimcoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Slab Floor Linear Ft<br>With Perimiter</td><td align=\"left\"></td><td align=\"left\">"+slabwperim+"</td><td>"+slabwperimtype+"</td><td>"+slabwperimheatbtu+"</td><td>"+slabwperimcoolbtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Floor over Unconditioned</td><td align=\"left\"></td><td align=\"left\">"+flooruncond+"</td><td>"+flooruncondtype+"</td><td>"+flooruncondheatbtu+"</td><td>"+flooruncondcoolbtu+"</td></tr>");


	Double mechventheat=0.00;
	Double mechventcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"mechventcfm", "All");
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		mechventheat=Double.parseDouble(heatvalue);
		mechventcool=Double.parseDouble(coolvalue);		
	}
	Double mechventcoolbtu=mechventcool*mechventcfm;
	Double mechventheatbtu=mechventheat*mechventcfm;	
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">CFM Mechanical Ventilation</td><td align=\"left\"></td><td align=\"left\">"+mechventcfm+"</td><td></td><td>"+mechventheatbtu+"</td><td>"+mechventcoolbtu+"</td></tr>");
	
	int peoplebtu=numpeople*530;
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Number People</td><td align=\"left\"></td><td align=\"left\">"+numpeople+"</td><td></td><td></td><td>"+peoplebtu+"</td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Blg. Length</td><td align=\"left\"></td><td align=\"left\">"+bldlength+"</td><td></td><td></td><td></td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Blg. Width</td><td align=\"left\"></td><td align=\"left\">"+bldwidth+"</td><td></td><td></td><td></td></tr>");
	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Blg. Height</td><td align=\"left\"></td><td align=\"left\">"+bldheight+"</td><td></td><td></td><td></td></tr>");
	
	Double infiltration = (((bldlength*bldwidth*bldheight)/60)*.5);
	Double infiltheat=0.00;
	Double infiltcool=0.00;
	vl1 = HeatLoadValues.getIndItem(con,"infiltration", "All");
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		infiltheat=Double.parseDouble(heatvalue);
		infiltcool=Double.parseDouble(coolvalue);		
	}
	Double infiltcoolbtu=infiltcool*infiltration;
	Double infiltheatbtu=infiltheat*infiltration;	
	mbody=combinestring(mbody,"<tr><td align=\"left\">CFM Infiltration</td><td align=\"left\"></td><td align=\"left\">"+infiltration+"</td><td></td><td>"+infiltheatbtu+"</td><td>"+infiltcoolbtu+"</td></tr>");	
	
	Double kitchenheat=0.00;
	Double kitchencool=0.00;
	int kitchencount=1;
	vl1 = HeatLoadValues.getIndItem(con,"kitchen", "All");
	for (int ih = 0 ; ih < vl1.size(); ih++)
	{
		HeatLoadValues th = (HeatLoadValues) vl1.elementAt(ih);		
		String coolvalue=th.coolvalue();
		String heatvalue=th.heatvalue();
		kitchenheat=Double.parseDouble(heatvalue);
		kitchencool=Double.parseDouble(coolvalue);		
	}
	Double kitchencoolbtu=kitchencool*kitchencount;
	Double kitchenheatbtu=kitchenheat*kitchencount;	
	mbody=combinestring(mbody,"<tr><td align=\"left\">Kitchen Allowance</td><td align=\"left\"></td><td align=\"left\">"+kitchencount+"</td><td></td><td>"+kitchenheatbtu+"</td><td>"+kitchencoolbtu+"</td></tr>");	
	
	Double infiltrationmech = infiltration+mechventcfm;
	Double infmechallowance=infiltrationmech*.68*35;
	mbody=combinestring(mbody,"<tr><td align=\"left\">Infilt+Mechanical Latent <br>Allowance</td><td align=\"left\"></td><td align=\"left\">-</td><td></td><td>0</td><td>"+infmechallowance+"</td></tr>");

	Double totalheatbtu=0.00;
	Double totalcoolbtu=0.00;
	
	totalheatbtu=ceilheatbtu+nwinheatbtu+swinheatbtu+wwinheatbtu+ewinheatbtu+nsldoorheatbtu+ssldoorheatbtu+esldoorheatbtu+wsldoorheatbtu+nframewallheatbtu+nmasawallheatbtu+nmasbwallheatbtu+basfloorheatbtu+slabnoperimheatbtu+slabwperimheatbtu+flooruncondheatbtu+mechventheatbtu+infiltheatbtu+kitchenheatbtu+doorheatbtu;
	totalcoolbtu=ceilcoolbtu+nwincoolbtu+swincoolbtu+wwincoolbtu+ewincoolbtu+nsldoorcoolbtu+ssldoorcoolbtu+esldoorcoolbtu+wsldoorcoolbtu+nframewallcoolbtu+nmasawallcoolbtu+nmasbwallcoolbtu+basfloorcoolbtu+slabnoperimcoolbtu+slabwperimcoolbtu+flooruncondcoolbtu+mechventcoolbtu+infiltcoolbtu+kitchencoolbtu+peoplebtu+infmechallowance+doorcoolbtu;
	mbody=combinestring(mbody,"<tr><td align=\"left\"><b>TOTALS</b></td><td align=\"left\"></td><td align=\"left\">-</td><td></td><td><b>"+totalheatbtu+"</b></td><td><b>"+totalcoolbtu+"</b></td></tr>");
	mbody=combinestring(mbody,"</table>");
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
