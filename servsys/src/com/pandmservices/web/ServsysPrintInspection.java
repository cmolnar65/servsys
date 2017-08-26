/*
 * ServsysProposal.java
 *
 * Created on March 24, 2006, 6:42 PM
 */

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
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Christopher Molnar
 * @version
 */
public class ServsysPrintInspection extends UniCash
{
    
  	public static String getIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir)
	throws SQLException, TodoException, Exception
        {

            String mbody="";

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
	String action = req.getParameter("action");
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
	String techid=null;
	String altphone=null;
	String cust_notes=null;
	String custsite=null;
	String sitenum=null;
	String cemail=null;
	String etype = "";
	String parts=null;

                String callslip = null;
                String idate = null;
                int equip1 = 0;
                int equip2 = 0;
                int equip3 = 0;
                int equip4 = 0;
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

	String sucttemp=null;
	String liqtemp=null;

	String r_temp=null;
	String s_temp=null;
	String rw_temp=null;
	String mcfm=null;
	String out_temp=null;
	String custtype=null;
	int servsync=0;
		String  im_cleanbin=null;
		String  im_limeaway=null;
		String  im_bleach=null;
		String  im_watercomp=null;
		String  im_icecomp=null;
		String  im_filter=null;
		String  im_float=null;
		String  im_wpump=null;
		String  im_binsensor=null;
		String  im_testdump=null;
		String  im_electrical=null;
		String  im_comprated=null;
		String  im_compactual=null;
		String  ref_condcoilcond=null;
		String  ref_condcoilcleaned=null;
		String  ref_evapcoilcond=null;
		String  ref_evapcoilcleaned=null;
		String  ref_fanbladecond=null;
		String  ref_fanbladecleaned=null;
		String  ref_condensateline=null;
		String  ref_condfan=null;
		String  ref_doorgasket=null;
		String  ref_doorsclose=null;
		String  ref_productboxcond=null;
		String  ref_checkscrews=null;
		String  ref_evaptempin=null;
		String  ref_evaptempout=null;
		String  ref_boxtemp=null;
		String  ref_sightglasslevel=null;
		String  ref_refrigerant=null;
		String  ref_defrostclock=null;
		String  ref_defrosttimes=null;
		String  ref_doorheater=null;
		String  ref_electrical=null;
		String  ref_compamprated=null;
		String  ref_compampactual=null;

	String tech_init = doGetTechInfo_init(username);
	String tech_name = doGetTechInfo_name(username);
	String nate_id=doGetTechInfo_nateid(username);
	int nateid=Integer.parseInt(nate_id);
	String tech_truck = doGetTechInfo_truck(username);

//////////////////////////////////////////////////////
// Print Header Information
/////////////////////////////////////////////////////

	mbody=combinestring(mbody,"<html><basefont size=2>");
	if (!action.equalsIgnoreCase("vinspectprint")) {
	mbody=combinestring(mbody,"<head><title>Inspection Report</title></head><body>");
	mbody=doMHeaderString(req, res, out, session, username, mbody);
			}


////////////////////////////////////////////////////////
// Get callslip information
///////////////////////////////////////////////////////

        Vector v;
        v = UniInspection.getIndItems(con, custnum, crecnum);
		
                for (int i = 0 ; i < v.size(); i++)
                {
                UniInspection t = (UniInspection) v.elementAt(i);
		crecnum=t.getCrecnum();
		custnum=t.getCustnum();
                callslip=t.getCallslip();
                idate=t.getIdate();
                equip1=t.getEquip1();
                equip2=t.getEquip2();
                equip3=t.getEquip3();
                equip4=t.getEquip4();
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
		parts=t.getParts();
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
		liqtemp=t.getLiqTemp();
		sucttemp=t.getSuctTemp();
		r_temp=t.getRTemp();
		s_temp=t.getSTemp();
		rw_temp=t.getRWTemp();
		mcfm=t.getMCfm();
		out_temp=t.getOutTemp();
		servsync=t.getServSync();
		techid=t.getTechID();
		im_cleanbin=t.im_cleanbin();
		im_limeaway=t.im_limeaway();
		im_bleach=t.im_bleach();
		im_watercomp=t.im_watercomp();
		im_icecomp=t.im_icecomp();
		im_filter=t.im_filter();
		im_float=t.im_float();
		im_wpump=t.im_wpump();
		im_binsensor=t.im_binsensor();
		im_testdump=t.im_testdump();
		im_electrical=t.im_electrical();
		im_comprated=t.im_comprated();
		im_compactual=t.im_compactual();
		ref_condcoilcond=t.ref_condcoilcond();
		ref_condcoilcleaned=t.ref_condcoilcleaned();
		ref_evapcoilcond=t.ref_evapcoilcond();
		ref_evapcoilcleaned=t.ref_evapcoilcleaned();
		ref_fanbladecond=t.ref_fanbladecond();
		ref_fanbladecleaned=t.ref_fanbladecleaned();
		ref_condensateline=t.ref_condensateline();
		ref_condfan=t.ref_condfan();
		ref_doorgasket=t.ref_doorgasket();
		ref_doorsclose=t.ref_doorsclose();
		ref_productboxcond=t.ref_productboxcond();
		ref_checkscrews=t.ref_checkscrews();
		ref_evaptempin=t.ref_evaptempin();
		ref_evaptempout=t.ref_evaptempout();
		ref_boxtemp=t.ref_boxtemp();
		ref_sightglasslevel=t.ref_sightglasslevel();
		ref_refrigerant=t.ref_refrigerant();
		ref_defrostclock=t.ref_defrostclock();
		ref_defrosttimes=t.ref_defrosttimes();
		ref_doorheater=t.ref_doorheater();
		ref_electrical=t.ref_electrical();
		ref_compamprated=t.ref_compamprated();
		ref_compampactual=t.ref_compampactual();
		sitenum=t.getSiteNum();
		custsite=t.getCustSite();
		}

//////////////////////////////////////////
// customer and inventory information
/////////////////////////////////////////

	mbody=combinestring(mbody,"<br>");
	mbody=combinestring(mbody,"<table border=0 width=\"95%\" align=\"center\"><td width=\"50%\">");
	mbody=combinestring(mbody,"<b align=Left>Service Invoice:</b><br>&nbsp;&nbsp;&nbsp;&nbsp;Call Slip: "+callslip+"<br>&nbsp;&nbsp;&nbsp;&nbsp;Call Date: "+doFormatDate(getDate(idate))+"");
	mbody=combinestring(mbody,"</td>");

        Vector vc;
        vc = UniCustomer.getIndItem(con, custnum);
		
                for (int ic = 0 ; ic < vc.size(); ic++)
                {
                UniCustomer tc = (UniCustomer) vc.elementAt(ic);
        	custtype = tc.getCustType();
		cname= tc.getCustomerName();
		address1=tc.getAddress1();
		address2=tc.getAddress2();
		city =tc.getCity();
		state=tc.getState();
		zip=tc.getZip();
		homephone=tc.getHomePhone();
		altphone=tc.getAltPhone();
		cust_notes=tc.getCustomerNotes();
		cemail=tc.getCEmail();
		sitenum=tc.getSiteNum();
		custsite=tc.getCustSite();
		}

mbody=combinestring(mbody,"<td width=\"30%\">");
mbody=combinestring(mbody,"<b>Customer:</b><br>&nbsp;&nbsp;&nbsp;&nbsp;"+cname+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+address1+"<br>&nbsp;&nbsp;&nbsp;&nbsp;"+city+","+state+"<br>&nbsp;&nbsp;&nbsp;"+cemail+"");
mbody=combinestring(mbody,"</td>");

mbody=combinestring(mbody,"<td width=\"30%\">");
mbody=combinestring(mbody,"Customer Number / Site:&nbsp;&nbsp;&nbsp;"+custsite+"&nbsp;/&nbsp;"+sitenum+"<br>Home Phone:&nbsp;&nbsp;&nbsp;"+homephone+"<br>Alt. Phone:&nbsp;&nbsp;&nbsp;"+altphone+"");
mbody=combinestring(mbody,"</td>");
	mbody=combinestring(mbody,"</table>");

	mbody=combinestring(mbody,"<table width=\"100%\" align=\"center\" border=0><th width=\"50%\" >Parts Used</th><th>Equipment List</th><tr><td>");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<tr><td>");
		mbody=combinestring(mbody,"<table width=\"100%\" align=\"center\" border=1>");
	mbody=combinestring(mbody,"<font size=1>");
mbody=combinestring(mbody,"<th>Key</th><th>Part</th><th>Quantity</th><th>Date</th>");
Vector ci;
ci = InvUse.getAllCallslipItems(con, callslip, idate);
for (int cc = 0 ; cc < ci.size(); cc++)
{
	InvUse ti = (InvUse) ci.elementAt(cc);
	String keycode = ti.getKeyCode();
	String itemname = ti.getItemName();
	String pquant = ti.getQuantity();
	String iidate = doFormatDate(getDate(ti.getTDate()));
	mbody=combinestring(mbody,"<tr><td>"+keycode+"</td><td>"+itemname+"</td><td>"+pquant+"</td><td>"+iidate+"</td></tr>");
	}
		mbody=combinestring(mbody,"</font>");
		mbody=combinestring(mbody,"</table>");
	mbody=combinestring(mbody,"</td><td>");
		mbody=combinestring(mbody,"<table width=\"95%\" align=\"center\" border=1>");
		mbody=combinestring(mbody,"<th>Type</th><th>Make</th><th>Model</th><th>Serial</th><th>Filter</th><th>Notes</th>");
		mbody=combinestring(mbody,"<font size=1>");
 	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM  equipment where enum='"+equip1+"' or enum='"+equip2+"' or enum='"+equip3+"' or enum='"+equip4+"';");
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

	mbody=combinestring(mbody,"<tr><td>"+etype+"</td><td>"+brand+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+efilter+"</td><td>"+notes+"</tr>");
		}
		mbody=combinestring(mbody,"</font>");
		mbody=combinestring(mbody,"</table>");
		mbody=combinestring(mbody,"<font size=1>");
		mbody=combinestring(mbody,"</td></tr></table>");

//////////////////////////////////////////////////////
// Print Air Handler
/////////////////////////////////////////////////////
if ((!mbearing.equalsIgnoreCase("-"))||!(mblades.equalsIgnoreCase("-"))||(!ecoil.equalsIgnoreCase("-")))
        {
mbody=combinestring(mbody,"<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Air Handler</h3>");
mbody=combinestring(mbody,"<font size=1>");
mbody=combinestring(mbody,"<table width=\"95%\" align=center border=1 height=5>");
mbody=combinestring(mbody,"<font size=1>");
mbody=combinestring(mbody,"<tr><td width=\"25%\"><b>Motor Bearings</td><td width=\"25%\">"+mbearing+"</td>");
mbody=combinestring(mbody,"<td width=\"25%\"><b>Motor Blades</td><td width=\"25%\">"+mblades+"</td></tr>");
mbody=combinestring(mbody,"<tr><td><b>Evaporator Coil</td><td>"+ecoil+"</td>");
mbody=combinestring(mbody,"<td><b>Drain Line</td><td>"+dline+"</td></tr>");
mbody=combinestring(mbody,"<tr><td><b>Drain Pan</td><td>"+dpan+"</td>");
mbody=combinestring(mbody,"<td><b>Electrical</td><td>"+ielect+"</td></tr>");
mbody=combinestring(mbody,"<tr><td><b>Motor Capacitor</td><td>"+mcap+"</td>");
mbody=combinestring(mbody,"<td><b>Filter</td><td>"+filter+"</td></tr>");
mbody=combinestring(mbody,"<tr><td><b>Humidifier</td><td>"+humidifier+"</td>");
mbody=combinestring(mbody,"<td><b>Temp Split</td><td width=\"25%\">"+tempsplit+"</td></tr>");
mbody=combinestring(mbody,"<tr><td><b>Supply Db Temp</td><td>"+s_temp+"</td>");
mbody=combinestring(mbody,"<td><b>Return Db Temp</td><td>"+r_temp+"</td></tr>");
mbody=combinestring(mbody,"<tr><td><b>Return Wb Temp</td><td>"+rw_temp+"</td>");
mbody=combinestring(mbody,"</font>");
mbody=combinestring(mbody,"</table>");
mbody=combinestring(mbody,"</font>");
}

////////////////////////////////////////////////////////
// Air Diagnostics Section
///////////////////////////////////////////////////////
if ((!spres_rated.equalsIgnoreCase("-"))||!(spres_return.equalsIgnoreCase("-"))||(!spres_rated.equalsIgnoreCase("-"))||!(rductsize.equalsIgnoreCase("-"))||!(airflow.equalsIgnoreCase("-")))
        {
	mbody=combinestring(mbody,"<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Air Diagnostics</h3>");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<table width=\"95%\" align=center border=1 height=5>");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<tr><td><b>Supply Pressure</td><td>"+spres_supply+"</td>");
	mbody=combinestring(mbody,"<td><b>Return Pressure</td><td>"+spres_return+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Rated Static Pressue</td><td>"+spres_rated+"</td>");
	mbody=combinestring(mbody,"<td><b>Air Flow</td><td>"+airflow+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Supply Duct Size</td><td>"+sductsize+"</td>");
	mbody=combinestring(mbody,"<td><b>Return Duct Size</td><td>"+rductsize+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Measured CFM</td><td>"+mcfm+"</td></tr>");
	
	mbody=combinestring(mbody,"</font>");
	mbody=combinestring(mbody,"</table>");
	mbody=combinestring(mbody,"</font>");
	}


////////////////////////////////////////////////////////
// Print Heating Section
///////////////////////////////////////////////////////
if ((!hstrips.equalsIgnoreCase("-"))||!(burners.equalsIgnoreCase("-"))||(!flame.equalsIgnoreCase("-")))
        {
	mbody=combinestring(mbody,"<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Heating</h3>");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<table width=\"95%\" align=center border=1 height=5>");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<tr><td width=\"25%\"><b>Heat Strips</td><td width=\"25%\">"+hstrips+"</td>");
	mbody=combinestring(mbody,"<td width=\"25%\"><b>Gas Pressure</td><td width=\"25%\">"+gpreassures+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Ignition</td><td>"+ignition+"</td>");
	mbody=combinestring(mbody,"<td><b>Burners</td><td>"+burners+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Limits</td><td>"+limits+"</td>");
	mbody=combinestring(mbody,"<td><b>Flame</td><td>"+flame+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Draft Inducer</td><td>"+dinducer+"</td>");
	mbody=combinestring(mbody,"<td><b>Stack Temp</td><td>"+stacktemp+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Start CO</td><td>"+startco+"</td>");
	mbody=combinestring(mbody,"<td><b>Run CO</td><td>"+runco+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Vent Pipe</td><td>"+ventpipe+"</td></tr>");
	mbody=combinestring(mbody,"</font>");
mbody=combinestring(mbody,"</font>");
	mbody=combinestring(mbody,"</table>");
}

/////////////////////////////////////////////////////////
// Print Condensor section
////////////////////////////////////////////////////////

if ((!atemp.equalsIgnoreCase("-"))||!(crlar.equalsIgnoreCase("-"))||(!lpres.equalsIgnoreCase("-")))
        {
	mbody=combinestring(mbody,"<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Condensor</h3>");
	mbody=combinestring(mbody,"<table width=\"95%\" align=center border=1 height=5>");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<tr><td width=\"25%\"><b>Suction Line Temp</td><td width=\"25%\">"+sucttemp+"</td>");
	mbody=combinestring(mbody,"<td width=\"25%\"><b>Liquid Line Temp</td><td width=\"25%\">"+liqtemp+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td width=\"25%\"><b>Approach Temp</td><td width=\"25%\">"+atemp+"</td>");
	mbody=combinestring(mbody,"<td><b>Outdoor Temp</td><td>"+out_temp+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Comp. Rated Amps</td><td>"+crlar+"</td>");
	mbody=combinestring(mbody,"<td><b>Comp. Actual Amps</td><td>"+crlaa+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Comp. Cap Actual</td><td>"+ccapa+"</td>");
	mbody=combinestring(mbody,"<td><b>Comp. Cap Rated</td><td>"+ccapr+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Fan Amps Rated</td><td>"+frlar+"</td>");
	mbody=combinestring(mbody,"<td><b>Fan Amps Actual</td><td>"+frlaa+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Fan Cap. Actual</td><td>"+fcapa+"</td>");
	mbody=combinestring(mbody,"<td><b>Fan Cap. Rated</td><td>"+fcapr+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Coil Conditions</td><td>"+coilcond+"</td>");
	mbody=combinestring(mbody,"<td><b>Cleaned Coil</td><td>"+cleancoil+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Start Capacitor</td><td>"+scap+"</td>");
	mbody=combinestring(mbody,"<td><b>Time Delay</td><td>"+ctimedelay+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Electrical</td><td>"+oelectrical+"</td>");
	mbody=combinestring(mbody,"<td><b>Condensor Pad</td><td>"+comppad+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Contactor</td><td>"+contactor+"</td>");
	mbody=combinestring(mbody,"<td><b>Fan Bearings</td><td>"+fbearing+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Low Pressure</td><td>"+lpres+"</td>");
	mbody=combinestring(mbody,"<td><b>High Pressure</td><td>"+hpres+"</td></tr>");
	mbody=combinestring(mbody,"</font>");
	mbody=combinestring(mbody,"</table>");
	}

/////////////////////////////////////////////////////////
// Print Oil Heat section
////////////////////////////////////////////////////////

if ((!opump.equalsIgnoreCase("-"))||(!oprimesafety.equalsIgnoreCase("-"))||(!osafetime.equalsIgnoreCase("-"))||(!oco2.equalsIgnoreCase("-"))||(!ofulemix.equalsIgnoreCase("-")))
        {
	mbody=combinestring(mbody,"<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Oil Heat</h3>");
	mbody=combinestring(mbody,"<table width=\"95%\" align=center border=1 height=5>");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<tr><td width=\"25%\"><b>Oil Leaks</td><td width=\"25%\">"+oleaks+"</td>");  
	mbody=combinestring(mbody,"<td width=\"25%\"><b>Chimney and Flue</td><td width=\"25%\">"+ochimney+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Pump Pressure</td><td>"+opump+"</td>");
	mbody=combinestring(mbody,"<td><b>Controls</td><td>"+ocontrols+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Thermostat</td><td>"+otstat+"</td>");
	mbody=combinestring(mbody,"<td><b>Primary Control Safety</td><td>"+oprimesafety+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Safety Timing</td><td>"+osafetime+"</td>");
	mbody=combinestring(mbody,"<td><b>Ignition Transformer</td><td>"+oigntrans+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Lubricate Motors</td><td>"+olubemotors+"</td>");
	mbody=combinestring(mbody,"<td><b>Fuel/Air Mix</td><td>"+ofulemix+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Nozzle Size</td><td>"+onozzle+"</td>");
	mbody=combinestring(mbody,"<td><b>Gross Stack Temp</td><td>"+ogross+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Net Stack Temp</td><td>"+onet+"</td>");
	mbody=combinestring(mbody,"<td><b>Smoke</td><td>"+osmoke+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>CO2</td><td>"+oco2+"</td>");
	mbody=combinestring(mbody,"<td><b>O2</td><td>"+oo2+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>CO</td><td>"+oco+"</td>");
	mbody=combinestring(mbody,"<td><b>Excess Air</td><td>"+oexcessair+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Breech Draft</td><td>"+obreachdraft+"</td>");
	mbody=combinestring(mbody,"<td><b>Overfire Draft</td><td>"+ofiredraft+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Efficiency</td><td>"+oeffic+"</td>");
	mbody=combinestring(mbody,"<td><b>Rating (Excellent, Good, Fair, Poor)</td><td>"+orating+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Chimney/Power Vent</td><td>"+opower+"</td>");
	mbody=combinestring(mbody,"<td><b>Tank Size</td><td>"+otank+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Tank Condition</td><td>"+otcond+"</td>");
	mbody=combinestring(mbody,"<td><b>Domestic Hot Water</td><td>"+odheat+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Combustion Chamber</td><td>"+ocombustion+"</td>");
	mbody=combinestring(mbody,"<td><b>Electrodes</td><td>"+oelectrodes+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Boiler/Furnace Brush/Vacuum</td><td>"+obrush+"</td>");
	mbody=combinestring(mbody,"<td><b>Filters</td><td>"+ofilters+"</td></tr>");
	mbody=combinestring(mbody,"</font>");
	mbody=combinestring(mbody,"</table>");
}


/////////////////////////////////////////////////////////
// Print Geothermal section
////////////////////////////////////////////////////////

if ((!g_looppres.equalsIgnoreCase("-"))||!(g_filter.equalsIgnoreCase("-"))||(!g_electrical.equalsIgnoreCase("-")))
        {
	mbody=combinestring(mbody,"<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Geothermal</h3>");
	mbody=combinestring(mbody,"<table width=\"95%\" align=center border=1 height=5>");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<tr><td width=\"25%\"><b>Filters</td><td width=\"25%\">"+g_filter+"</td>");  
	mbody=combinestring(mbody,"<td width=\"25%\"><b>Electrical</td><td width=\"25%\">"+g_electrical+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Loop Pressure </td><td>"+g_looppres +"</td>");
	mbody=combinestring(mbody,"<td><b>Cleaned Coil</td><td>"+g_cleancoil +"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Cleaned Drainline</td><td>"+g_cleandrain +"</td>");
	mbody=combinestring(mbody,"<td><b>Check Pan Sensor </td><td>"+g_pansensor +"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Clean Compartments </td><td>"+g_cleancomp +"</td>");
	mbody=combinestring(mbody,"<td><b>Clean Unit </td><td>"+g_cleanunit +"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Oil/Check Blower </td><td>"+g_oilblower +"</td>");
	mbody=combinestring(mbody,"<td><b>Clean Condensate Pump </td><td>"+g_cleanpump +"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Temp Split </td><td>"+g_tsplit +"</td>");
	mbody=combinestring(mbody,"<td><b>Pressure Drop </td><td>"+g_pdrop +"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Pump Amps Rated </td><td>"+g_pampr +"</td>");
	mbody=combinestring(mbody,"<td><b>Pump Amps Actual </td><td>"+g_pampa +"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Compressor Amps Rated </td><td>"+g_compar +"</td>");
	mbody=combinestring(mbody,"<td><b>Compressor Amps Actual </td><td>"+g_compaa +"</td></tr>");
	mbody=combinestring(mbody,"<tr><td><b>Blower Amps Rated </td><td>"+g_bampr +"</td>");
	mbody=combinestring(mbody,"<td><b>Blower Amps Actual </td><td>"+g_bampa +"</td></tr>");
	mbody=combinestring(mbody,"</font>");
	mbody=combinestring(mbody,"</table>");
	}
	
if ((!im_watercomp.equalsIgnoreCase("-"))||!(im_cleanbin.equalsIgnoreCase("-"))||(!im_electrical.equalsIgnoreCase("-")))
        {
			
	mbody=combinestring(mbody,"<table size=100% border=1>");
	mbody=combinestring(mbody,"<th><h3>Ice Machine</h3></th><th></th>");
	mbody=combinestring(mbody,"<tr><td>Clean Ice Bin</td><td>"+im_cleanbin+"</td>");
	mbody=combinestring(mbody,"<td>Clean With LimeAway</td><td>"+im_limeaway+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Clean With Bleach</td><td>"+im_bleach+"</td>");
	mbody=combinestring(mbody,"<td>Clean All Water Components</td><td>"+im_watercomp+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Clean All Ice Components</td><td>"+im_icecomp+"</td>");
	mbody=combinestring(mbody,"<td>Clean / Check Water Filter</td><td>"+im_filter+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Clean Float</td><td>"+im_float+"</td>");
	mbody=combinestring(mbody,"<td>Clean Water Pump</td><td>"+im_wpump+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Check Bin Sensor</td><td>"+im_binsensor+"</td>");
	mbody=combinestring(mbody,"<td>Run 3 Batches and Dump</td><td>"+im_testdump+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Check Electrical</td><td>"+im_electrical+"</td>");
	mbody=combinestring(mbody,"<td>Compressor Amps Rated</td><td>"+im_comprated+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Compressor Amps Actual</td><td>"+im_compactual+"</td></tr>");
	mbody=combinestring(mbody,"</table>");
	mbody=combinestring(mbody,"<p></p><p></p>");
	}
if ((!ref_condcoilcond.equalsIgnoreCase("-"))||!(ref_condcoilcleaned.equalsIgnoreCase("-"))||(!ref_electrical.equalsIgnoreCase("-")))
        {

	mbody=combinestring(mbody,"<table size=100% border=1>");
	mbody=combinestring(mbody,"<th><h3>Refrigeration</h3></th><th></th>");
	mbody=combinestring(mbody,"<tr><td>Condensor Coil Condition</td><td>"+ref_condcoilcond+"</td>");
	mbody=combinestring(mbody,"<td>Condensor Coil Cleaned</td><td>"+ref_condcoilcleaned+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Evaperator Coil Condition</td><td>"+ref_evapcoilcond+"</td>");
	mbody=combinestring(mbody,"<td>Evaperator Coil Cleaned</td><td>"+ref_evapcoilcleaned+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Fan Blade Condition</td><td>"+ref_fanbladecond+"</td>");
	mbody=combinestring(mbody,"<td>Fan Blade Cleaned</td><td>"+ref_fanbladecleaned+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Cleaned Condensate Line</td><td>"+ref_condensateline+"</td>");
	mbody=combinestring(mbody,"<td>Condensor Fan</td><td>"+ref_condfan+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Door Gaskets</td><td>"+ref_doorgasket+"</td>");
	mbody=combinestring(mbody,"<td>Doors Close Properly</td><td>"+ref_doorsclose+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Product Box Condition</td><td>"+ref_productboxcond+"</td>");
	mbody=combinestring(mbody,"<td>Check Screws</td><td>"+ref_checkscrews+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Evaporator Air Temp In</td><td>"+ref_evaptempin+"</td>");
	mbody=combinestring(mbody,"<td>Evaporator Air Temp Out</td><td>"+ref_evaptempout+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Box Temp</td><td>"+ref_boxtemp+"</td>");
	mbody=combinestring(mbody,"<td>Sight Glass Level</td><td>"+ref_sightglasslevel+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Refrigerant</td><td>"+ref_refrigerant+"</td>");
	mbody=combinestring(mbody,"<td>Defrost Clock</td><td>"+ref_defrostclock+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Defrost Times</td><td>"+ref_defrosttimes+"</td>");
	mbody=combinestring(mbody,"<td>Check Electrical</td><td>"+ref_electrical+"</td></tr>");
	mbody=combinestring(mbody,"<tr><td>Compressor Amp Rated</td><td>"+ref_compamprated+"</td>");
	mbody=combinestring(mbody,"<td>Compressor Amp Actual</td><td>"+ref_compampactual+"</td></tr>");	
	mbody=combinestring(mbody,"</table>");
	mbody=combinestring(mbody,"<p></p><p></p>");
	}	

/////////////////////////////////////////////////////////
// Print Comments and Notes section
////////////////////////////////////////////////////////
if (services.length()>1||recommendations.length()>1||parts.length()>1||notes.length()>1) {
mbody=combinestring(mbody,"<br>");
mbody=combinestring(mbody,"<table size=95% width=\"95%\" align=center border=1>");
mbody=combinestring(mbody,"<font size=1>");
if (services.length()>1) {
mbody=combinestring(mbody,"<tr><td><b>Services</td></tr><tr><td>"+services);
mbody=combinestring(mbody,"</td></tr>");
}
if (parts.length()>1) {
mbody=combinestring(mbody,"<tr><td><b>Parts Needed</td></tr><tr><td>"+parts);
mbody=combinestring(mbody,"</td></tr>");
}

if (recommendations.length()>1) {
mbody=combinestring(mbody,"<P></P><br>");
		mbody=combinestring(mbody,"<tr><td><h4>Our Trained Technician Recommends</h4></td></tr><tr><td>"+recommendations+"");
		mbody=combinestring(mbody,"<table><font size=1><tr><td>Customer Accepts Recomendations</td><td>______________</td><td>Customer Declines Recommendations</td><td>________________</td></tr></font></table></tr>");
		mbody=combinestring(mbody,"</td></tr>");
}
if (action.equalsIgnoreCase("vinspectprint")) {
mbody=combinestring(mbody,"<tr><td><b>NOTES: DO NOT PRINT FOR CUSTOMER!!</td></tr><tr><td>"+notes);
mbody=combinestring(mbody,"</td></tr><tr><td>");

	mbody=combinestring(mbody,"<h3>Notes</h3>");

	Vector vn;
        vn = InspectNotes.getAllItems(con,custsite, sitenum, callslip);
        mbody=combinestring(mbody,"<table border=1 width=100%>");
        mbody=combinestring(mbody,"<th>Date</th><th>User</th><th>Note</th>");
	String note="";
	String ndate="";
	String ncallslip="";
	String nuserlogin="";
	String nnote="";
	int nrecnum=0;
	int nservsync=0;
		
                for (int in = 0 ; in < vn.size(); in++)
                {
                InspectNotes tn = (InspectNotes) vn.elementAt(in);
		nnote=tn.getNote();
		ncallslip=tn.getCallslip();
		nuserlogin=tn.getUserLogin();
		nrecnum=tn.getRecNum();
		ndate=tn.getNDate();
		nservsync=tn.getServsync();
                mbody=combinestring(mbody,"<tr><td>"+ndate+"</td><td>"+nuserlogin+"</td><td>"+nnote+"</td></tr>");
                }
		mbody=combinestring(mbody,"</table></td></tr>");
	
}
mbody=combinestring(mbody,"</font>");
mbody=combinestring(mbody,"</table>");
mbody=combinestring(mbody,"");
}


/////////////////////////////////////////////////////////
// Print footer and signature section
////////////////////////////////////////////////////////

       int frcode=0; 
        v = UniSvcCharges.getAllItems(con,callslip);
	if ((v.size()>0)) {
	mbody=combinestring(mbody,"<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Charges</h4>");
        mbody=combinestring(mbody,"<table border=1 width=95% align=\"center\">");
		mbody=combinestring(mbody,"<font size=1>");
        mbody=combinestring(mbody,"<th>Code</th><th>Quantity</th><th>Description</th><th>Price</th><th>Sub Total</th>");
		
                for (int i = 0 ; i < v.size(); i++)
                {
                UniSvcCharges t = (UniSvcCharges) v.elementAt(i);
		recnum=t.getRecnum();
		callslip=t.getCallslip();
		quant=t.getQuant();
		descript=t.getDescript();
		price=t.getPrice();
		frcode = t.getFrcode();
		total=t.getTotal();
		totalcharge=totalcharge+total;
                mbody=combinestring(mbody,"<tr><td>"+frcode+"</td><td>"+quant+"</td><td>"+descript+"</td><td>"+NumberFormat.getCurrencyInstance().format(price)+"</td><td>"+NumberFormat.getCurrencyInstance().format(total)+"</td></tr>");
		}
	mbody=combinestring(mbody,"</font>");
        mbody=combinestring(mbody,"</table>");
		}
	mbody=combinestring(mbody,"</font>");
	String itech_name = doGetTechInfo_name(techid);
	mbody=combinestring(mbody,"<table width=\"95%\" align=\"center\">");
	mbody=combinestring(mbody,"<font size=1>");
	mbody=combinestring(mbody,"<tr>");
	if (totalcharge>0) {
		mbody=combinestring(mbody,"<td>____________________________________</td><td>"+itech_name+"</td><td>Amount Due</td><td><b>"+NumberFormat.getCurrencyInstance().format(totalcharge)+"</b></td>");

	} else {
		mbody=combinestring(mbody,"<td>____________________________________</td><td>"+itech_name+"</td><td>Amount Due</td><td><b>_________________</b></td>");
	}
	mbody=combinestring(mbody,"<P></p><br><br>");
mbody=combinestring(mbody,"</tr><tr><td><h5>Customer Signature</h5></td><td><h5>Service Tech</h5></td><td>Amount Paid</td><td>_________________</td>");
mbody=combinestring(mbody,"</tr></font></table>");

// Put the discount information in here...

if (!action.equalsIgnoreCase("vinspectprint")) {
	mbody=combinestring(mbody,"<table width=\"95%\" align=\"center\">");
	mbody=combinestring(mbody,"<font size=1>");
                        if (nateid==0) {
                        if (custtype.equalsIgnoreCase("T")) {

		mbody=combinestring(mbody,"<tr><td></td><td><b>Please ask our technician how you could save money on this call with a Planned Service Agreement!</b></td></tr>");
                        }
			else if (custtype.equalsIgnoreCase("F")) {

		mbody=combinestring(mbody,"<tr><td></td><td><b>Thank you for calling "+doGetCompanyName()+"</b> </td></tr>");
			}
                        else {
		mbody=combinestring(mbody,"<tr><td></td><td><b>Thank you for calling "+doGetCompanyName()+"</b> </td></tr>");
			}
			//NO NATE ID - DO NOT PRINT NATE INFO
			} else {
	mbody=combinestring(mbody,"<tr><td> ");
        //NEED TO MAKE THIS CONFIGURABLE
	mbody=combinestring(mbody,"<IMG SRC=\"http://192.168.2.225:8080/servsys/natelogo.gif\" NAME=\"NateLogo\" ALIGN=LEFT WIDTH=60 HEIGHT=60 BORDER=0><BR CLEAR=LEFT>");
	mbody=combinestring(mbody,"</td>");
                        if (custtype.equalsIgnoreCase("T")) {

		mbody=combinestring(mbody,"<td>Your Service Technician is NATE Certified - ID: "+nate_id+"<br><b>Please ask our technician how you could save money on this call with a Planned Service Agreement!</b></td></tr>");
                        }
			else if (custtype.equalsIgnoreCase("F")) {

		mbody=combinestring(mbody,"<td>Your Service Technician is NATE Certified - ID: "+nate_id+"<br><b>Thank you for calling "+doGetCompanyName()+"</b> </td></tr>");
			}
                        else {
		mbody=combinestring(mbody,"<td>Your Service Technician is NATE Certified - ID: "+nate_id+"<br><b>Thank you for calling "+doGetCompanyName()+"</b> </td></tr>");
			}
			}
}

mbody=combinestring(mbody,"</font></table>");

mbody=combinestring(mbody,"</body>");
mbody=combinestring(mbody,"</html>");
		con.close();
		if (action.equalsIgnoreCase("vinspectprint")) 
				{
                mbody=combinestring(mbody,"<br><br><a href="+classdir+"UniCash?action=showcustdetail&csection=3&custnum="+custnum+">Click here to continue</a>");
				}

	    
		out.println(mbody);
		//con.close();
				return mbody;
        }
    
  	public static String mailIndividualItem (Connection con, HttpServletRequest req, HttpServletResponse res, PrintWriter out, HttpSession session, String username, String classdir, int crecnum, int custnum)
	throws SQLException, TodoException, Exception
        {

            String mbody="";
	       String brand=null;
        String modelnum=null;
        String serialnum=null;
        String filter=null;
        String enotes=null;
        String etype=null;
	    int csrec = crecnum;
                String callslip = null;
                String idate = null;
                int equip1 = 0;
                int equip2 = 0;
                int equip3 = 0;
                int equip4 = 0;
                String mbearing = null;
                String mblades = null;
                String ecoil = null;
                String dline = null;
                String dpan = null;
                String ielect = null;
                String mcap = null;
                String hstrips = null;
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

	String sductsize = null;
	String rductsize = null;
	String r_temp = null;
	String s_temp = null;
	String rw_temp = null;
	String mcfm=null;
	String out_temp=null;
	int servsync=0;
			String  im_cleanbin=null;
		String  im_limeaway=null;
		String  im_bleach=null;
		String  im_watercomp=null;
		String  im_icecomp=null;
		String  im_filter=null;
		String  im_float=null;
		String  im_wpump=null;
		String  im_binsensor=null;
		String  im_testdump=null;
		String  im_electrical=null;
		String  im_comprated=null;
		String  im_compactual=null;
		String  ref_condcoilcond=null;
		String  ref_condcoilcleaned=null;
		String  ref_evapcoilcond=null;
		String  ref_evapcoilcleaned=null;
		String  ref_fanbladecond=null;
		String  ref_fanbladecleaned=null;
		String  ref_condensateline=null;
		String  ref_condfan=null;
		String  ref_doorgasket=null;
		String  ref_doorsclose=null;
		String  ref_productboxcond=null;
		String  ref_checkscrews=null;
		String  ref_evaptempin=null;
		String  ref_evaptempout=null;
		String  ref_boxtemp=null;
		String  ref_sightglasslevel=null;
		String  ref_refrigerant=null;
		String  ref_defrostclock=null;
		String  ref_defrosttimes=null;
		String  ref_doorheater=null;
		String  ref_electrical=null;
		String  ref_compamprated=null;
		String  ref_compampactual=null;
		String sitenum=null;
		String custsite=null;

		String cname=null;
		String address1=null;
		String address2=null;
		String city=null;
		String state=null;
		String zip=null;
		String liqtemp=null;
		String parts=null;
		String sucttemp=null;
		String tech_init = doGetTechInfo_init(username);
		String tech_name = doGetTechInfo_name(username);
		String tech_truck = doGetTechInfo_truck(username);
                Vector v;
		v = UniInspection.getIndItems(con,custnum, csrec);

                for (int i = 0 ; i < v.size(); i++)
                {
                       	UniInspection t = (UniInspection) v.elementAt(i);
			crecnum=t.getCrecnum();
			custnum=t.getCustnum();
               		callslip=t.getCallslip();
                	idate=t.getIdate();
                	equip1=t.getEquip1();
                	equip2=t.getEquip2();
                	equip3=t.getEquip3();
                	equip4=t.getEquip4();
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
		rductsize = t.getRDuctSize();
		sductsize = t.getSDuctSize();
		r_temp = t.getRTemp();
		rw_temp = t.getRWTemp();
		s_temp = t.getSTemp();
		mcfm=t.getMCfm();
		out_temp=t.getOutTemp();
		servsync=t.getServSync();
		parts=t.getParts();
		im_cleanbin=t.im_cleanbin();
		im_limeaway=t.im_limeaway();
		im_bleach=t.im_bleach();
		im_watercomp=t.im_watercomp();
		im_icecomp=t.im_icecomp();
		im_filter=t.im_filter();
		im_float=t.im_float();
		im_wpump=t.im_wpump();
		im_binsensor=t.im_binsensor();
		im_testdump=t.im_testdump();
		im_electrical=t.im_electrical();
		im_comprated=t.im_comprated();
		im_compactual=t.im_compactual();
		ref_condcoilcond=t.ref_condcoilcond();
		ref_condcoilcleaned=t.ref_condcoilcleaned();
		ref_evapcoilcond=t.ref_evapcoilcond();
		ref_evapcoilcleaned=t.ref_evapcoilcleaned();
		ref_fanbladecond=t.ref_fanbladecond();
		ref_fanbladecleaned=t.ref_fanbladecleaned();
		ref_condensateline=t.ref_condensateline();
		ref_condfan=t.ref_condfan();
		ref_doorgasket=t.ref_doorgasket();
		ref_doorsclose=t.ref_doorsclose();
		ref_productboxcond=t.ref_productboxcond();
		ref_checkscrews=t.ref_checkscrews();
		ref_evaptempin=t.ref_evaptempin();
		ref_evaptempout=t.ref_evaptempout();
		ref_boxtemp=t.ref_boxtemp();
		ref_sightglasslevel=t.ref_sightglasslevel();
		ref_refrigerant=t.ref_refrigerant();
		ref_defrostclock=t.ref_defrostclock();
		ref_defrosttimes=t.ref_defrosttimes();
		ref_doorheater=t.ref_doorheater();
		ref_electrical=t.ref_electrical();
		ref_compamprated=t.ref_compamprated();
		ref_compampactual=t.ref_compampactual();
		custsite=t.getCustSite();
		sitenum=t.getSiteNum();

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
		}	

////////////////////////////////////////////////////
//Print Inspection Data 
///////////////////////////////////////////////////	
mbody=combinestring(mbody,"<br>------------------------------<br>Callslip: "+callslip+"<br>");
mbody=combinestring(mbody,"Customer: "+cname+"<br>");
mbody=combinestring(mbody,"Address1: "+address1+"<br>");
mbody=combinestring(mbody,"City: "+city+"<br>");
mbody=combinestring(mbody,"State: "+state+"<br>");
mbody=combinestring(mbody,"Zip: "+zip+"<br>");


	///////////////////////////////////////////////////
	//Print Equipment Data
	///////////////////////////////////////////////////
 	stmt = con.createStatement();
	rs = stmt.executeQuery("SELECT * FROM  equipment where enum='"+equip1+"' or enum='"+equip2+"' or enum='"+equip3+"' or enum='"+equip4+"';");
	mbody=combinestring(mbody,"<br>Equipment Information:<br>-----------------<br>");
	mbody=combinestring(mbody,"<table border=1 width=\"100%\"><th>Type</th><th>Brand</th><th>Model</th><th>Serial</th><th>Filter</th><th>Notes</th>");

		 while(rs.next())
                {
		brand = rs.getString("brand");
                modelnum = rs.getString("modelnum");
                serialnum = rs.getString("serialnum");
                filter = rs.getString("filter");
                enotes = rs.getString("notes");
		etype = rs.getString("etype");
if (etype==null) { 
                etype = "-";
                }
	mbody=combinestring(mbody,"<tr><td>"+etype+"</td><td>"+brand+"</td><td>"+modelnum+"</td><td>"+serialnum+"</td><td>"+filter+"</td><td>"+enotes+"</td></tr>");
	}

	mbody=combinestring(mbody,"</table>");

	///////////////////////////////////////////////////
	//Print Inventory Data
	///////////////////////////////////////////////////
		mbody=combinestring(mbody,"<br><br>Parts Used<br>--------------------------<br>");
	mbody=combinestring(mbody,"<table width=\"100%\" border=1><th>Key Code</th><th>Item</th><th>Quantity</th><th>Date</th>");
Vector ci;
ci = InvUse.getAllCallslipItems(con, callslip, idate);
for (int cc = 0 ; cc < ci.size(); cc++)
{
	InvUse ti = (InvUse) ci.elementAt(cc);
	String keycode = ti.getKeyCode();
	String itemname = ti.getItemName();
	String pquant = ti.getQuantity();
	String iidate = doFormatDate(getDate(ti.getTDate()));
	mbody=combinestring(mbody,"<tr><td>"+keycode+"</td><td>"+itemname+"</td><td>"+pquant+"</td><td>"+iidate+"</td></tr>");
	}
	mbody=combinestring(mbody,"</table>");

////////////////////////////////////////////////////
//Print Services
///////////////////////////////////////////////////	
if (services.length()>1) {
mbody=combinestring(mbody,"<br>"+services+"<br>");
}


////////////////////////////////////////////////////
//Print parts
///////////////////////////////////////////////////	
if (parts.length()>1) {
mbody=combinestring(mbody,"<br>Parts Needed:<br>"+parts+"<br>");
}

////////////////////////////////////////////////////
//Print Air Handler Data 
///////////////////////////////////////////////////	
if ((!mbearing.equalsIgnoreCase("-"))||!(mblades.equalsIgnoreCase("-"))||(!ecoil.equalsIgnoreCase("-")))
        {
mbody=combinestring(mbody,"<br>Checked Air Handler -  Motor Bearings: "+mbearing+", Motor Blades: "+mblades+", Evaperator Coil: "+ecoil+", Drain Line: "+dline+", Drain Pan: "+dpan+", Electrical: "+ielect+", Blower Capacitor: "+mcap+", Filter: "+filter+", Humidifier: "+humidifier+".<br>");
	}

////////////////////////////////////////////////////
//Print Air Conditioning Data 
///////////////////////////////////////////////////	

if ((!atemp.equalsIgnoreCase("-"))||!(crlar.equalsIgnoreCase("-"))||(!lpres.equalsIgnoreCase("-")))
        {
	mbody=combinestring(mbody,"Checked outside unit (condensor) - Approach Temp: "+atemp+", ");
	mbody=combinestring(mbody,"Liquid Line Temp: "+liqtemp+", ");
	mbody=combinestring(mbody,"Suction Line Temp: "+sucttemp +", ");
	mbody=combinestring(mbody,"Temperature Split(Supply vs return air): "+tempsplit+", ");
	mbody=combinestring(mbody,"Compressor Rated Amps: "+crlar +", ");
	mbody=combinestring(mbody,"Compressor Actual Amps: "+crlaa +", ");
	mbody=combinestring(mbody,"Compressor Capacitor Rating: "+ccapr +", ");
	mbody=combinestring(mbody,"Compressor Capacitor Actual: "+ccapa +", ");
	mbody=combinestring(mbody,"Fan Amps Rated: "+frlar +", ");
	mbody=combinestring(mbody,"Fan Amps Actual: "+frlaa +", ");
	mbody=combinestring(mbody,"Fan Capacitor Rating: "+fcapr +", ");
	mbody=combinestring(mbody,"Fan Capacitor Actual: "+fcapa +", ");
	mbody=combinestring(mbody,"Coil Condition: "+coilcond +", ");
	mbody=combinestring(mbody,"Cleaned Coil: "+cleancoil +", ");
	mbody=combinestring(mbody,"Start Capacitor: "+scap +", ");
	mbody=combinestring(mbody,"Time Delay: "+ctimedelay +", ");
	mbody=combinestring(mbody,"Electrical: "+oelectrical +", ");
	mbody=combinestring(mbody,"Condensor Pad: "+comppad +", ");
	mbody=combinestring(mbody,"Contactor: "+contactor +", ");
	mbody=combinestring(mbody,"Fan Bearings: "+fbearing +", ");
	mbody=combinestring(mbody,"Low Side Pressure: "+lpres +", ");
	mbody=combinestring(mbody,"High Side Pressure: "+hpres +",<br> ");
}

////////////////////////////////////////////////////
//Print Gas Heat Data 
///////////////////////////////////////////////////	
if ((!hstrips.equalsIgnoreCase("-"))||!(burners.equalsIgnoreCase("-"))||(!flame.equalsIgnoreCase("-")))
        {
mbody=combinestring(mbody,"<br>Checked heating system - Heat Strips: "+hstrips+", Gas Pressure: "+gpreassures+", Ignition: "+ignition+", Burners: "+burners+", Limits: "+limits+", Flame: "+flame+", Draft Inducer: "+dinducer+", Stack Temp: "+stacktemp+", Start Carbon Monoxide: "+startco+", Run Carbon Monoxide: "+runco+", Vent Pipe: "+ventpipe+"<br>");
}

////////////////////////////////////////////////////
//Print Oil Heat Data 
///////////////////////////////////////////////////	

if ((!opump.equalsIgnoreCase("-"))||(!oprimesafety.equalsIgnoreCase("-"))||(!osafetime.equalsIgnoreCase("-"))||(!oco2.equalsIgnoreCase("-"))||(!ofulemix.equalsIgnoreCase("-")))
        {
	mbody=combinestring(mbody,"Oil Leaks: "+oleaks+", ");  
	mbody=combinestring(mbody,"Chimney and Flue: "+ochimney+", ");
	mbody=combinestring(mbody,"Pump Pressure: "+opump+", ");
	mbody=combinestring(mbody,"Controls: "+ocontrols+", ");
	mbody=combinestring(mbody,"Thermostat: "+otstat+", ");
	mbody=combinestring(mbody,"Primary Control Safety: "+oprimesafety+", ");
	mbody=combinestring(mbody,"Safety Timing: "+osafetime+", ");
	mbody=combinestring(mbody,"Ignition Transformer: "+oigntrans+", ");
	mbody=combinestring(mbody,"Lubricate Motors: "+olubemotors+", ");
	mbody=combinestring(mbody,"Fuel/Air Mix: "+ofulemix+", ");
	mbody=combinestring(mbody,"Nozzle Size: "+onozzle+", ");
	mbody=combinestring(mbody,"Gross Stack Temp: "+ogross+", ");
	mbody=combinestring(mbody,"Net Stack Temp: "+onet+", ");
	mbody=combinestring(mbody,"Smoke: "+osmoke+", ");
	mbody=combinestring(mbody,"Carbon Dioxide: "+oco2+", ");
	mbody=combinestring(mbody,"Oxygen Content: "+oo2+", ");
	mbody=combinestring(mbody,"Carbon Monoxide: "+oco+", ");
	mbody=combinestring(mbody,"Excess Air: "+oexcessair+", ");
	mbody=combinestring(mbody,"Breech Draft: "+obreachdraft+", ");
	mbody=combinestring(mbody,"Overfire Draft: "+ofiredraft+", ");
	mbody=combinestring(mbody,"Efficiency: "+oeffic+", ");
	mbody=combinestring(mbody,"Rating (Excellent, Good, Fair, Poor): "+orating+", ");
	mbody=combinestring(mbody,"Chimney/Power Vent: "+opower+", ");
	mbody=combinestring(mbody,"Tank Size: "+otank+", ");
	mbody=combinestring(mbody,"Tank Condition: "+otcond+", ");
	mbody=combinestring(mbody,"Domestic Hot Water: "+odheat+", ");
	mbody=combinestring(mbody,"Combustion Chamber: "+ocombustion+", ");
	mbody=combinestring(mbody,"Electrodes: "+oelectrodes+", ");
	mbody=combinestring(mbody,"Boiler/Furnace Brush/Vacuum: "+obrush+", ");
	mbody=combinestring(mbody,"Oil Filters: "+ofilters+", <br>");
	}

/////////////////////////////////////////////////////////
// Print Geothermal section
////////////////////////////////////////////////////////

if ((!g_looppres.equalsIgnoreCase("-"))||!(g_filter.equalsIgnoreCase("-"))||(!g_electrical.equalsIgnoreCase("-")))
        {
	mbody=combinestring(mbody,"Geothermal Section<br>");
	mbody=combinestring(mbody,"Filters: "+g_filter+", <br>");
	mbody=combinestring(mbody,"Electrical: "+g_electrical+", <br>");
	mbody=combinestring(mbody,"Loop Pressure: "+g_looppres+", <br>");
	mbody=combinestring(mbody,"Cleaned Coil: "+g_cleancoil+", <br>");
	mbody=combinestring(mbody,"Cleaned Drainline: "+g_cleandrain+", <br>");
	mbody=combinestring(mbody,"Check Pan Sensor: "+g_pansensor+", <br>");
	mbody=combinestring(mbody,"Clean Compartments: "+g_cleancomp+", <br>");
	mbody=combinestring(mbody,"Clean Unit: "+g_cleanunit+", <br>");
	mbody=combinestring(mbody,"Oil/Check Blower: "+g_oilblower+", <br>");
	mbody=combinestring(mbody,"Clean Condensate Pump: "+g_cleanpump+", <br>");
	mbody=combinestring(mbody,"Temp Split: "+g_tsplit+", <br>");
	mbody=combinestring(mbody,"Pressure Drop: "+g_pdrop+", <br>");
	mbody=combinestring(mbody,"Pump Amps Rated: "+g_pampr+", <br>");
	mbody=combinestring(mbody,"Pump Amps Acutal: "+g_pampa+", <br>");
	mbody=combinestring(mbody,"Compressor Amps Rated: "+g_compar+", <br>");
	mbody=combinestring(mbody,"Compressor Amps Actual: "+g_compaa+", <br>");
	mbody=combinestring(mbody,"Blower Amps Rated: "+g_bampr+", <br>");
	mbody=combinestring(mbody,"Blower Amps Actual: "+g_bampa+", <br>");
}

if ((!im_watercomp.equalsIgnoreCase("-"))||!(im_cleanbin.equalsIgnoreCase("-"))||(!im_electrical.equalsIgnoreCase("-")))
        {		
	mbody=combinestring(mbody,"Ice Machine Checked: <br>");
	mbody=combinestring(mbody,"Clean Ice Bin: "+im_cleanbin+"<br>");
	mbody=combinestring(mbody,"Clean With LimeAway: "+im_limeaway+"<br>");
	mbody=combinestring(mbody,"Clean With Bleach: "+im_bleach+"<br>");
	mbody=combinestring(mbody,"Clean All Water Components: "+im_watercomp+"<br>");
	mbody=combinestring(mbody,"Clean All Ice Components: "+im_icecomp+"<br>");
	mbody=combinestring(mbody,"Clean / Check Water Filter: "+im_filter+"<br>");
	mbody=combinestring(mbody,"Clean Float: "+im_float+"<br>");
	mbody=combinestring(mbody,"Clean Water Pump: "+im_wpump+"<br>");
	mbody=combinestring(mbody,"Check Bin Sensor: "+im_binsensor+"<br>");
	mbody=combinestring(mbody,"Run 3 Batches and Dump: "+im_testdump+"<br>");
	mbody=combinestring(mbody,"Check Electrical: "+im_electrical+"<br>");
	mbody=combinestring(mbody,"Compressor Amps Rated: "+im_comprated+"<br>");
	mbody=combinestring(mbody,"Compressor Amps Actual: "+im_compactual+"<br>");
	mbody=combinestring(mbody,"<br>");
	}
if ((!ref_condcoilcond.equalsIgnoreCase("-"))||!(ref_condcoilcleaned.equalsIgnoreCase("-"))||(!ref_electrical.equalsIgnoreCase("-")))
        {

	mbody=combinestring(mbody,"<th><h3>Refrigeration</h3></th><th></th>");
	mbody=combinestring(mbody,"Condensor Coil Condition: "+ref_condcoilcond+"<br>");
	mbody=combinestring(mbody,"Condensor Coil Cleaned: "+ref_condcoilcleaned+"<br>");
	mbody=combinestring(mbody,"Evaperator Coil Condition: "+ref_evapcoilcond+"<br>");
	mbody=combinestring(mbody,"Evaperator Coil Cleaned: "+ref_evapcoilcleaned+"<br>");
	mbody=combinestring(mbody,"Fan Blade Condition: "+ref_fanbladecond+"<br>");
	mbody=combinestring(mbody,"Fan Blade Cleaned: "+ref_fanbladecleaned+"<br>");
	mbody=combinestring(mbody,"Cleaned Condensate Line: "+ref_condensateline+"<br>");
	mbody=combinestring(mbody,"Condensor Fan: "+ref_condfan+"<br>");
	mbody=combinestring(mbody,"Door Gaskets: "+ref_doorgasket+"<br>");
	mbody=combinestring(mbody,"Doors Close Properly: "+ref_doorsclose+"<br>");
	mbody=combinestring(mbody,"Product Box Condition: "+ref_productboxcond+"<br>");
	mbody=combinestring(mbody,"Check Screws: "+ref_checkscrews+"<br>");
	mbody=combinestring(mbody,"Evaporator Air Temp In: "+ref_evaptempin+"<br>");
	mbody=combinestring(mbody,"Evaporator Air Temp Out: "+ref_evaptempout+"<br>");
	mbody=combinestring(mbody,"Box Temp: "+ref_boxtemp+"<br>");
	mbody=combinestring(mbody,"Sight Glass Level: "+ref_sightglasslevel+"<br>");
	mbody=combinestring(mbody,"Refrigerant: "+ref_refrigerant+"<br>");
	mbody=combinestring(mbody,"Defrost Clock: "+ref_defrostclock+"<br>");
	mbody=combinestring(mbody,"Defrost Times: "+ref_defrosttimes+"<br>");
	mbody=combinestring(mbody,"Check Electrical: "+ref_electrical+"<br>");
	mbody=combinestring(mbody,"Compressor Amp Rated: "+ref_compamprated+"<br>");
	mbody=combinestring(mbody,"Compressor Amp Actual: "+ref_compampactual+"<br>");	
	}	


////////////////////////////////////////////////////
//Print Recommendations 
///////////////////////////////////////////////////	
if (recommendations.length()>1) {
mbody=combinestring(mbody,"The following recommendations are made by our service technician: "+recommendations+"<br>");
}

	////////////////////////////////////////////////////
	//Print Notes
	///////////////////////////////////////////////////
	if (notes.length()>1) {
	mbody=combinestring(mbody,"<br>---------------------------<br>OFFICE: The following notes are OFFICE ONLY - NOT FOR CUSTOMER:<br>"+notes+"<br>");
	}
	mbody=combinestring(mbody,"<h3>Notes</h3>");

	Vector vn;
        vn = InspectNotes.getAllItems(con,custsite, sitenum, callslip);
        mbody=combinestring(mbody,"<table border=1 width=100%>");
        mbody=combinestring(mbody,"<th>Date</th><th>User</th><th>Note</th>");
	String note="";
	String ndate="";
	String ncallslip="";
	String nuserlogin="";
	String nnote="";
	int nrecnum=0;
	int nservsync=0;
		
                for (int in = 0 ; in < vn.size(); in++)
                {
                InspectNotes tn = (InspectNotes) vn.elementAt(in);
		nnote=tn.getNote();
		ncallslip=tn.getCallslip();
		nuserlogin=tn.getUserLogin();
		nrecnum=tn.getRecNum();
		ndate=tn.getNDate();
		nservsync=tn.getServsync();
                mbody=combinestring(mbody,"<tr><td>"+ndate+"</td><td>"+nuserlogin+"</td><td>"+nnote+"</td></tr>");
                }
		mbody=combinestring(mbody,"</table>");
	
////////////////////////////////////////////////////////
// Here is where we add timesheet information
////////////////////////////////////////////////////////

	mbody=combinestring(mbody,"<br>------------------------------<br>Times:<br>");
        Vector tsd;
        tsd = UniTimeSheet.getAllCallslipItems(con,idate,callslip, username);
		
                for (int h = 0 ; h < tsd.size(); h++)
                {
		UniTimeSheet ts = (UniTimeSheet) tsd.elementAt(h);
		String TimeIn = ts.TimeIn();
		String TimeOut = ts.TimeOut();
		String DispatchTime = ts.DispatchTime();
		String Amount = ts.Amount();
		String AmountCollected = ts.AmountCollected();
		String Commision = ts.Commision();
		String CItemSold = ts.CItemSold();
		String CAmount = ts.CAmount();
		String ItemSold = ts.ItemSold();
		String paytype = ts.TPayType();

	mbody=combinestring(mbody,"<br>Dispatched Time: "+DispatchTime+"<br>Time In: "+TimeIn+"<br>Time Out: "+TimeOut+"<br><br>");	
	mbody=combinestring(mbody,"Item Sold: "+ItemSold+"<br>Amount Sold: "+Amount+"<br>Commision Item Sold: "+CItemSold+"<br>Commision Amount Sold: "+CAmount+"<br>Amount Collected: "+AmountCollected+"<br>Commision: "+Commision+"<br>Paid With: "+paytype+"<br>");
		}


int recnum;
double quant=0.00;
String descript="";
double price=0.00;
double total=0.00;
double totalcharge=0.00;
int frcode=0;
////////////////////////////////////////////////////
// Add Charges 
///////////////////////////////////////////////////

	mbody=combinestring(mbody,"<br>------------------------------<br>Charges:<br>");
        Vector u;
        u = UniSvcCharges.getAllItems(con,callslip);
		
                for (int h = 0 ; h < u.size(); h++)
                {
                UniSvcCharges q = (UniSvcCharges) u.elementAt(h);
		recnum=q.getRecnum();
		callslip=q.getCallslip();
		quant=q.getQuant();
		descript=q.getDescript();
		price=q.getPrice();
		frcode=q.getFrcode();
		total=q.getTotal();
		totalcharge=totalcharge+total;
                mbody=combinestring(mbody,"Code: "+frcode+"  Quantity: "+quant+"   Service: "+descript+"  Price: "+NumberFormat.getCurrencyInstance().format(price)+"  Total: "+NumberFormat.getCurrencyInstance().format(total)+"<br>");
		}
////////////////////////////////////////////////////
// End of the Inspection Getting Loop
///////////////////////////////////////////////////	
/////////////////////////////////////////////////////////
// Here is where we end the http headers
////////////////////////////////////////////////////////
	emailserver = doGetSmtpServer(username);
	emailsendaddress=doGetIns_Email(username);
	techemailaddress=doGetTech_Email(username);	
      String smtpuser = doGetSmtpUser(username);
      String smtppassword = doGetSmtpPassword(username);
        doMailSend(emailserver, emailsendaddress, techemailaddress, "Inspection: "+callslip+" - "+cname+" - "+idate+" - "+ tech_name , mbody, smtpuser, smtppassword);
		}
	return mbody;    
	    
	}
	
}
