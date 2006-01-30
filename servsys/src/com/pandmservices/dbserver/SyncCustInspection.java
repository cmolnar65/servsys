package com.pandmservices.dbserver;
import java.sql.*;
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class SyncCustInspection
{
		private int crecnum = 0;
		private int custnum = 0;
		private String CustSite=null;
		private String SiteNum=null;
		private String callslip = null;
		private String idate = null;
		private int equip1 = 0;
		private int equip2 = 0;
		private int equip3 = 0;
		private int equip4 = 0;
		private String mbearing = null;
		private String mblades = null;
		private String ecoil = null;
		private String dline = null;
		private String dpan = null;
		private String ielect = null;
		private String mcap = null;
		private String hstrips = null;
		private String filter = null;
		private String gpreassures = null;
		private String ignition = null;
		private String burners = null;
		private String limits = null;
		private String flame = null;
		private String dinducer = null;
		private String humidifier = null;
		private String atemp = null;
		private String tempsplit = null;
		private String crlaa = null;
		private String crlar = null;
		private String ccapr = null;
		private String ccapa = null;
		private String frlaa = null;
		private String frlar = null;
		private String fcapr = null;
		private String fcapa = null;
		private String fbearing = null;
		private String coilcond = null;
		private String cleancoil = null;
		private String contactor = null;
		private String scap = null;
		private String ctimedelay = null;
		private String oelectrical = null;
		private String comppad = null;
		private String recommendations = null;
		private String services = null;
		private String dueamount = null;
		private String paidamount = null;
		private String notes = null;
		private String lpres = null;
		private String hpres = null;
		private String startco = null;
		private String runco = null;
		private String stacktemp = null;
		private String ventpipe = null;
		private String oleaks = null;
		private String ochimney = null;
		private String opump = null;
		private String ocontrols = null;
		private String otstat = null;
		private String oprimesafety = null;
		private String osafetime = null;
		private String remotecrecnum=null;
		private String oigntrans = null;
		private String olubemotors = null;
		private String ofulemix = null;
		private String onozzle = null;
		private String ogross = null;
		private String onet = null;
		private String osmoke = null;
		private String oco2 = null;
		private String oo2 = null;
		private String oco = null;
		private String oexcessair = null;
		private String obreachdraft = null;
		private String ofiredraft = null;
		private String oeffic = null;
		private String orating = null;
		private String opower = null;
		private String otank = null;
		private String otcond = null;
		private String odheat = null;
		private String ocombustion = null;
		private String oelectrodes = null;
		private String obrush = null;
		private String ofilters = null;
		private int followup = 0;
		private String airflow = null;
		private String spres_rated = null;
		private String spres_supply = null;
		private String spres_return = null;
	        private String g_filter = null;
        	private String g_electrical = null;
        	private String g_looppres=null;
        	private String g_cleancoil=null;
        	private String g_cleandrain=null;
        	private String g_pansensor=null;
        	private String g_cleancomp=null;
        	private String g_cleanunit=null;
        	private String g_oilblower=null;
        	private String g_cleanpump=null;
        	private String g_tsplit=null;
        	private String g_pampr=null;
        	private String g_pampa=null;
        	private String g_compar=null;
        	private String g_compaa=null;
        	private String g_bampr=null;
        	private String g_bampa=null;
        	private String g_pdrop=null;
        	private String sductsize=null;
        	private String rductsize=null;
		private String liqtemp=null;
		private String sucttemp=null;
		private String r_temp=null;
		private String s_temp=null;
		private String rw_temp=null;
		private String mcfm=null;
		private String out_temp=null;
		private String expansion=null;
		private String ahage=null;
		private String conage=null;
		private String techid=null;
		private String sitenum=null;
		private String custsite=null;
		private int servsync=0;
		private String parts=null;
		private int id=0;



        public SyncCustInspection (Connection con, Connection conu, String custsitenum, String sitenum)
		throws SQLException, TodoException
	{
		Statement stmt = conu.createStatement();
		Statement stmtl = con.createStatement();
		Statement stmt2 = conu.createStatement();
		Statement stmtu = con.createStatement();
		Statement stmt2u = con.createStatement();
		ResultSet rs= stmt2.executeQuery("SELECT * FROM inspection where custsite='"+custsitenum+"' and sitenum='"+sitenum+"' ORDER BY crecnum;");
		while(rs.next())
		{
		this.crecnum = rs.getInt("crecnum");
		this.custnum = rs.getInt("custnum");
		this.callslip = rs.getString("callslip");
		this.idate = rs.getString("idate");
		this.equip1 = rs.getInt("equip1");
		this.equip2 = rs.getInt("equip2");
		this.equip3 = rs.getInt("equip3");
		this.equip4 = rs.getInt("equip4");
		this.mbearing = rs.getString("mbearing");
		this.mblades = rs.getString("mblades");
		this.ecoil = rs.getString("ecoil");
		this.dline = rs.getString("dline");
		this.dpan = rs.getString("dpan");
		this.ielect = rs.getString("ielect");
		this.mcap = rs.getString("mcap");
		this.hstrips = rs.getString("hstrips");
		this.filter = rs.getString("filter");
		this.gpreassures = rs.getString("gpreassures");
		this.ignition = rs.getString("ignition");
		this.burners = rs.getString("burners");
		this.limits = rs.getString("limits");
		this.flame = rs.getString("flame");
		this.dinducer = rs.getString("dinducer");
		this.humidifier = rs.getString("humidifier");
		this.atemp = rs.getString("atemp");
		this.tempsplit = rs.getString("tempsplit");
		this.crlaa = rs.getString("crlaa");
		this.crlar = rs.getString("crlar");
		this.ccapr = rs.getString("ccapr");
		this.ccapa = rs.getString("ccapa");
		this.frlaa = rs.getString("frlaa");
		this.frlar = rs.getString("frlar");
		this.fcapr = rs.getString("fcapr");
		this.fcapa = rs.getString("fcapa");
		this.fbearing = rs.getString("fbearing");
		this.coilcond = rs.getString("coilcond");
		this.cleancoil = rs.getString("cleancoil");
		this.contactor = rs.getString("contactor");
		this.scap = rs.getString("scap");
		this.ctimedelay = rs.getString("ctimedelay");
		this.oelectrical = rs.getString("oelectrical");
		this.comppad = rs.getString("comppad");
		this.recommendations = rs.getString("recommendations");
		this.services = rs.getString("services");
		this.dueamount = rs.getString("dueamount");
		this.paidamount = rs.getString("paidamount");
		this.notes = rs.getString("notes");
		this.lpres = rs.getString("lpres");
		this.hpres = rs.getString("hpres");
		this.startco = rs.getString("startco");
		this.runco = rs.getString("runco");
		this.stacktemp = rs.getString("stacktemp");
		this.ventpipe = rs.getString("ventpipe");
		this.oleaks = rs.getString("oleaks");
		this.ochimney = rs.getString("ochimney");
		this.opump = rs.getString("opump");
		this.ocontrols = rs.getString("ocontrols");
		this.otstat = rs.getString("otstat");
		this.oprimesafety = rs.getString("oprimesafety");
		this.osafetime = rs.getString("osafetime");
		this.oigntrans = rs.getString("oigntrans");
		this.olubemotors = rs.getString("olubemotors");
		this.ofulemix = rs.getString("ofulemix");
		this.onozzle = rs.getString("onozzle");
		this.ogross = rs.getString("ogross");
		this.onet = rs.getString("onet");
		this.osmoke = rs.getString("osmoke");
		this.oco2 = rs.getString("oco2");
		this.oo2 = rs.getString("oo2");
		this.oco = rs.getString("oco");
		this.oexcessair = rs.getString("oexcessair");
		this.obreachdraft = rs.getString("obreachdraft");
		this.ofiredraft = rs.getString("ofiredraft");
		this.oeffic = rs.getString("oeffic");
		this.orating = rs.getString("orating");
		this.opower = rs.getString("opower");
		this.otank = rs.getString("otank");
		this.otcond = rs.getString("otcond");
		this.odheat = rs.getString("odheat");
		this.ocombustion = rs.getString("ocombustion");
		this.oelectrodes = rs.getString("oelectrodes");
		this.obrush = rs.getString("obrush");
		this.ofilters = rs.getString("ofilters");
		this.followup = rs.getInt("followup");
		this.airflow = rs.getString("airflow");
		this.spres_rated = rs.getString("spres_rated");
		this.spres_supply = rs.getString("spres_supply");
		this.spres_return = rs.getString("spres_return");
		this.g_filter = rs.getString("g_filter");
		this.g_electrical = rs.getString("g_electrical");
		this.g_looppres = rs.getString("g_looppres");
		this.g_cleancoil = rs.getString("g_cleancoil");
		this.g_cleandrain = rs.getString("g_cleandrain");
		this.g_pansensor = rs.getString("g_pansensor");
		this.g_cleancomp = rs.getString("g_cleancomp");
		this.g_cleanunit = rs.getString("g_cleanunit");
		this.g_oilblower = rs.getString("g_oilblower");
		this.g_cleanpump = rs.getString("g_cleanpump");
		this.g_tsplit = rs.getString("g_tsplit");
		this.g_pampr = rs.getString("g_pampr");
		this.g_pampa = rs.getString("g_pampa");
		this.g_compar = rs.getString("g_compar");
		this.g_compaa = rs.getString("g_compaa");
		this.g_bampr = rs.getString("g_bampr");
		this.g_bampa = rs.getString("g_bampa");
		this.g_pdrop = rs.getString("g_pdrop");
		this.sductsize = rs.getString("sductsize");
		this.rductsize = rs.getString("rductsize");
		this.liqtemp=rs.getString("liqtemp");
		this.sucttemp=rs.getString("sucttemp");
		this.r_temp=rs.getString("r_temp");
		this.s_temp=rs.getString("s_temp");
		this.rw_temp=rs.getString("rw_temp");
		this.mcfm=rs.getString("mcfm");
		this.out_temp=rs.getString("out_temp");
		this.expansion=rs.getString("expansion");
		this.ahage=rs.getString("ahage");
		this.conage=rs.getString("conage");
		this.techid=rs.getString("techid");
		this.custsite=rs.getString("custsite");
		this.sitenum=rs.getString("sitenum");
		this.servsync=rs.getInt("servsync");
		this.parts=rs.getString("parts");
	
		
		////////////////////////////////////////////////////////////////
		// Now check local machine for match on callslip, idate, techid
		///////////////////////////////////////////////////////////////
		//
		ResultSet rsl = stmtl.executeQuery("Select idate from inspection where custsite='"+custsitenum+"' and sitenum='"+sitenum+"' and callslip='"+callslip+"' and idate='"+idate+"' and techid='"+techid+"' order by crecnum;");

		if (!rsl.first()) {
		
		String renum1="0";
		String renum2="0";
		String renum3="0";
		String renum4="0";

		if (equip1>0) {
		ResultSet lse1 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+equip1+"';");
		while (lse1.next()) {
		String tserialnum=lse1.getString("serialnum");
		String modelnum=lse1.getString("modelnum");
		String tbrand=lse1.getString("brand");
		ResultSet rse1 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse1.next()) {
		renum1=rse1.getString("enum");
		}}
		}	

		if (equip2>0) {
		ResultSet lse2 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+equip2+"';");
		while (lse2.next()) {
		String tserialnum=lse2.getString("serialnum");
		String modelnum=lse2.getString("modelnum");
		String tbrand=lse2.getString("brand");
		ResultSet rse2 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse2.next()) {
		renum2=rse2.getString("enum");
		}}
		}	

		if (equip3>0) {
		ResultSet lse3 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+equip3+"';");
		while (lse3.next()) {
		String tserialnum=lse3.getString("serialnum");
		String modelnum=lse3.getString("modelnum");
		String tbrand=lse3.getString("brand");
		ResultSet rse3 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse3.next()) {
		renum3=rse3.getString("enum");
		}}
		}	

		if (equip4>0) {
		ResultSet lse4 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+equip4+"';");
		while (lse4.next()) {
		String tserialnum=lse4.getString("serialnum");
		String modelnum=lse4.getString("modelnum");
		String tbrand=lse4.getString("brand");
		ResultSet rse4 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse4.next()) {
		renum4=rse4.getString("enum");
		}}
		}	
			Vector vc;
			vc = UniCustomer.getCustNumSite(con,custsite,sitenum);
			if (vc.size()>0) {
				for (int ic = 0 ; ic < vc.size(); ic++)
				{
					UniCustomer tc = (UniCustomer) vc.elementAt(ic);
					remotecrecnum = tc.getCusNum();


		paidamount="0.00";
		dueamount="0.00";
			String tservices = services.replaceAll("'","''");
		String tparts="";
	if (parts!=null) {
	        tparts=parts.replaceAll("'","''");
	}

                        String trecommendations = recommendations.replaceAll("'","''");

	                //System.out.println("INSERT INTO inspection (custnum, callslip, idate, equip1, equip2, equip3, equip4, mbearing, mblades, ecoil, dline, dpan, ielect, mcap, hstrips, filter, gpreassures, ignition, burners, limits, flame, dinducer, humidifier, atemp, tempsplit, crlaa, crlar, ccapr, ccapa, frlaa, frlar, fcapr, fcapa, fbearing, coilcond, cleancoil, contactor, scap, ctimedelay, oelectrical, comppad, recommendations,services, dueamount, paidamount,notes,lpres, hpres, startco, runco, stacktemp, ventpipe, oleaks, ochimney, opump, ocontrols, otstat, oprimesafety, osafetime, oigntrans, olubemotors, ofulemix, onozzle,ogross, osmoke, onet, oco2, oo2, oco, oexcessair, obreachdraft, ofiredraft, oeffic, orating, opower,otank, otcond, odheat, ocombustion, oelectrodes, obrush, ofilters, followup, airflow, spres_rated, spres_supply, spres_return,g_filter,g_electrical,g_looppres,g_cleancoil,g_cleandrain,g_pansensor,g_cleancomp,g_cleanunit,g_oilblower,g_cleanpump,g_tsplit,g_pampr,g_pampa,g_compar,g_compaa,g_bampr,g_bampa,g_pdrop, sductsize, rductsize, liqtemp, sucttemp, r_temp, s_temp, rw_temp, mcfm, out_temp, custsite, sitenum, expansion, ahage, conage, techid, servsync) VALUES ('"+remotecrecnum+"','"+callslip+"','"+idate+"',"+renum1+" ,"+renum2+","+renum3+" ,"+renum4+",'"+mbearing+"','"+mblades+"','"+ecoil+"','"+dline+"','"+dpan+"','"+ielect+"','"+mcap+"','"+hstrips+"' ,'"+filter+"','"+gpreassures+"','"+ignition+"','"+burners+"','"+limits+"','"+flame+"','"+dinducer+"','"+humidifier+"','"+atemp+"','"+tempsplit+"','"+crlaa+"','"+crlar+"','"+ccapr+"','"+ccapa+"','"+frlaa+"','"+frlar+"','"+fcapr+"','"+fcapa+"','"+fbearing+"','"+coilcond+"','"+cleancoil+"','"+contactor+"','"+scap+"','"+ctimedelay+"','"+oelectrical+"','"+comppad+"','"+trecommendations+"','"+tservices+"','"+dueamount+"','"+paidamount+"','"+notes+"','"+lpres+"','"+hpres+"','"+startco+"','"+runco+"','"+stacktemp+"','"+ventpipe+"', '"+oleaks+"', '"+ochimney+"', '"+opump+"', '"+ocontrols+"', '"+otstat+"', '"+oprimesafety+"', '"+osafetime+"', '"+oigntrans+"', '"+olubemotors+"', '"+ofulemix+"', '"+onozzle+"', '"+ogross+"', '"+osmoke+"', '"+onet+"', '"+oco2+"', '"+oo2+"', '"+oco+"', '"+oexcessair+"', '"+obreachdraft+"', '"+ofiredraft+"', '"+oeffic+"', '"+orating+"', '"+opower+"', '"+otank+"', '"+otcond+"', '"+odheat+"', '"+ocombustion+"', '"+oelectrodes+"', '"+obrush+"', '"+ofilters+"',"+followup+",'"+airflow+"','"+spres_rated+"','"+spres_supply+"','"+spres_return+"','"+g_filter+"','"+g_electrical+"','"+g_looppres+"','"+g_cleancoil+"','"+g_cleandrain+"','"+g_pansensor+"','"+g_cleancomp+"','"+g_cleanunit+"','"+g_oilblower+"','"+g_cleanpump+"','"+g_tsplit+"','"+g_pampr+"','"+g_pampa+"','"+g_compar+"','"+g_compaa+"','"+g_bampr+"','"+g_bampa+"','"+g_pdrop+"', '"+sductsize+"', '"+rductsize+"','"+liqtemp+"','"+sucttemp+"', '"+r_temp+"', '"+s_temp+"', '"+rw_temp+"', '"+mcfm+"','"+out_temp+"','"+custsite+"','"+sitenum+"', '"+expansion+"','"+ahage+"', '"+conage+"', '"+techid+"', '"+servsync+"')\n");
	                stmtu.executeUpdate("INSERT INTO inspection (custnum, callslip, idate, equip1, equip2, equip3, equip4, mbearing, mblades, ecoil, dline, dpan, ielect, mcap, hstrips, filter, gpreassures, ignition, burners, limits, flame, dinducer, humidifier, atemp, tempsplit, crlaa, crlar, ccapr, ccapa, frlaa, frlar, fcapr, fcapa, fbearing, coilcond, cleancoil, contactor, scap, ctimedelay, oelectrical, comppad, recommendations,services, dueamount, paidamount,notes,lpres, hpres, startco, runco, stacktemp, ventpipe, oleaks, ochimney, opump, ocontrols, otstat, oprimesafety, osafetime, oigntrans, olubemotors, ofulemix, onozzle,ogross, osmoke, onet, oco2, oo2, oco, oexcessair, obreachdraft, ofiredraft, oeffic, orating, opower,otank, otcond, odheat, ocombustion, oelectrodes, obrush, ofilters, followup, airflow, spres_rated, spres_supply, spres_return,g_filter,g_electrical,g_looppres,g_cleancoil,g_cleandrain,g_pansensor,g_cleancomp,g_cleanunit,g_oilblower,g_cleanpump,g_tsplit,g_pampr,g_pampa,g_compar,g_compaa,g_bampr,g_bampa,g_pdrop, sductsize, rductsize, liqtemp, sucttemp, r_temp, s_temp, rw_temp, mcfm, out_temp, custsite, sitenum, expansion, ahage, conage, techid, servsync,parts) VALUES ('"+remotecrecnum+"','"+callslip+"','"+idate+"',"+renum1+" ,"+renum2+","+renum3+" ,"+renum4+",'"+mbearing+"','"+mblades+"','"+ecoil+"','"+dline+"','"+dpan+"','"+ielect+"','"+mcap+"','"+hstrips+"' ,'"+filter+"','"+gpreassures+"','"+ignition+"','"+burners+"','"+limits+"','"+flame+"','"+dinducer+"','"+humidifier+"','"+atemp+"','"+tempsplit+"','"+crlaa+"','"+crlar+"','"+ccapr+"','"+ccapa+"','"+frlaa+"','"+frlar+"','"+fcapr+"','"+fcapa+"','"+fbearing+"','"+coilcond+"','"+cleancoil+"','"+contactor+"','"+scap+"','"+ctimedelay+"','"+oelectrical+"','"+comppad+"','"+trecommendations+"','"+tservices+"','"+dueamount+"','"+paidamount+"','"+notes+"','"+lpres+"','"+hpres+"','"+startco+"','"+runco+"','"+stacktemp+"','"+ventpipe+"', '"+oleaks+"', '"+ochimney+"', '"+opump+"', '"+ocontrols+"', '"+otstat+"', '"+oprimesafety+"', '"+osafetime+"', '"+oigntrans+"', '"+olubemotors+"', '"+ofulemix+"', '"+onozzle+"', '"+ogross+"', '"+osmoke+"', '"+onet+"', '"+oco2+"', '"+oo2+"', '"+oco+"', '"+oexcessair+"', '"+obreachdraft+"', '"+ofiredraft+"', '"+oeffic+"', '"+orating+"', '"+opower+"', '"+otank+"', '"+otcond+"', '"+odheat+"', '"+ocombustion+"', '"+oelectrodes+"', '"+obrush+"', '"+ofilters+"','0','"+airflow+"','"+spres_rated+"','"+spres_supply+"','"+spres_return+"','"+g_filter+"','"+g_electrical+"','"+g_looppres+"','"+g_cleancoil+"','"+g_cleandrain+"','"+g_pansensor+"','"+g_cleancomp+"','"+g_cleanunit+"','"+g_oilblower+"','"+g_cleanpump+"','"+g_tsplit+"','"+g_pampr+"','"+g_pampa+"','"+g_compar+"','"+g_compaa+"','"+g_bampr+"','"+g_bampa+"','"+g_pdrop+"', '"+sductsize+"', '"+rductsize+"','"+liqtemp+"','"+sucttemp+"', '"+r_temp+"', '"+s_temp+"', '"+rw_temp+"', '"+mcfm+"','"+out_temp+"','"+custsite+"','"+sitenum+"', '"+expansion+"','"+ahage+"', '"+conage+"', '"+techid+"', '2','"+tparts+"')");
		        }
			}
		}
		}
  }
}
