package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniInspection
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
		private int servsync=0;
		private String parts=null;
		private String  im_cleanbin=null;
		private String  im_limeaway=null;
		private String  im_bleach=null;
		private String  im_watercomp=null;
		private String  im_icecomp=null;
		private String  im_filter=null;
		private String  im_float=null;
		private String  im_wpump=null;
		private String  im_binsensor=null;
		private String  im_testdump=null;
		private String  im_electrical=null;
		private String  im_comprated=null;
		private String  im_compactual=null;
		private String  ref_condcoilcond=null;
		private String  ref_condcoilcleaned=null;
		private String  ref_evapcoilcond=null;
		private String  ref_evapcoilcleaned=null;
		private String  ref_fanbladecond=null;
		private String  ref_fanbladecleaned=null;
		private String  ref_condensateline=null;
		private String  ref_condfan=null;
		private String  ref_doorgasket=null;
		private String  ref_doorsclose=null;
		private String  ref_productboxcond=null;
		private String  ref_checkscrews=null;
		private String  ref_evaptempin=null;
		private String  ref_evaptempout=null;
		private String  ref_boxtemp=null;
		private String  ref_sightglasslevel=null;
		private String  ref_refrigerant=null;
		private String  ref_defrostclock=null;
		private String  ref_defrosttimes=null;
		private String  ref_doorheater=null;
		private String  ref_electrical=null;
		private String  ref_compamprated=null;
		private String  ref_compampactual=null;
		private String custsite=null;
		private String sitenum=null;
		

        public UniInspection (Connection c, int crecnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inspection WHERE crecnum=" + crecnum+ ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + crecnum);
		}
		this.crecnum = rs.getInt("crecnum");
		this.custnum = rs.getInt("custnum");
		this.callslip = rs.getString("callslip");
		this.custsite=rs.getString("custsite");
		this.sitenum=rs.getString("sitenum");
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
		this.servsync=rs.getInt("servsync");
		this.parts=rs.getString("parts");
		this.im_cleanbin=rs.getString("im_cleanbin");
		this.im_limeaway=rs.getString("im_limeaway");
		this.im_bleach=rs.getString("im_bleach");
		this.im_watercomp=rs.getString("im_watercomp");
		this.im_icecomp=rs.getString("im_icecomp");
		this.im_filter=rs.getString("im_filter");
		this.im_float=rs.getString("im_float");
		this.im_wpump=rs.getString("im_wpump");
		this.im_binsensor=rs.getString("im_binsensor");
		this.im_testdump=rs.getString("im_testdump");
		this.im_electrical=rs.getString("im_electrical");
		this.im_comprated=rs.getString("im_comprated");
		this.im_compactual=rs.getString("im_compactual");
		this.ref_condcoilcond=rs.getString("ref_condcoilcond");
		this.ref_condcoilcleaned=rs.getString("ref_condcoilcleaned");
		this.ref_evapcoilcond=rs.getString("ref_evapcoilcond");
		this.ref_evapcoilcleaned=rs.getString("ref_evapcoilcleaned");
		this.ref_fanbladecond=rs.getString("ref_fanbladecond");
		this.ref_fanbladecleaned=rs.getString("ref_fanbladecleaned");
		this.ref_condensateline=rs.getString("ref_condensateline");
		this.ref_condfan=rs.getString("ref_condfan");
		this.ref_doorgasket=rs.getString("ref_doorgasket");
		this.ref_doorsclose=rs.getString("ref_doorsclose");
		this.ref_productboxcond=rs.getString("ref_productboxcond");
		this.ref_checkscrews=rs.getString("ref_checkscrews");
		this.ref_evaptempin=rs.getString("ref_evaptempin");
		this.ref_evaptempout=rs.getString("ref_evaptempout");
		this.ref_boxtemp=rs.getString("ref_boxtemp");
		this.ref_sightglasslevel=rs.getString("ref_sightglasslevel");
		this.ref_refrigerant=rs.getString("ref_refrigerant");
		this.ref_defrostclock=rs.getString("ref_defrostclock");
		this.ref_defrosttimes=rs.getString("ref_defrosttimes");
		this.ref_doorheater=rs.getString("ref_doorheater");
		this.ref_electrical=rs.getString("ref_electrical");
		this.ref_compamprated=rs.getString("ref_compamprated");
		this.ref_compampactual=rs.getString("ref_compampactual");
	}


	public static Vector getInspectionFollowup(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM inspection where followup=1 order by idate ");
		while(rs.next())
		{
			
			UniInspection t = new UniInspection(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}
	public static Vector getCustInspectionFollowup(Connection c, String custsite, String sitenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM inspection where followup=1 and custsite='"+custsite+"' and sitenum='"+sitenum+"' order by idate ");
		while(rs.next())
		{
			
			UniInspection t = new UniInspection(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM inspection where custnum='"+custnum+"' order by idate ");
		while(rs.next())
		{
			
			UniInspection t = new UniInspection(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllDateItems(Connection c, String listdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM inspection where idate='"+listdate+"' order by callslip");
		while(rs.next())
		{
			
			UniInspection t = new UniInspection(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getIncludedDateItems(Connection c, String startdate, String enddate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM inspection where idate>='"+startdate+"' and  idate<='"+enddate+"' order by callslip");
		while(rs.next())
		{
			
			UniInspection t = new UniInspection(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndItems(Connection c, int custnum, int crecnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM inspection where crecnum='"+crecnum+"' ORDER BY idate;");
		while(rs.next())
		{
			
			UniInspection t = new UniInspection(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

public static void UpdateItem(Connection con, int crecnum, int custnum, String callslip, String idate, int equip1, int equip2, int equip3, int equip4, String mbearing, String mblades, String ecoil, String dline, String dpan, String ielect, String mcap, String hstrips, String filter, String gpreassures, String ignition, String burners, String limits, String flame, String dinducer, String humidifier, String atemp, String tempsplit, String crlaa, String crlar, String ccapr, String ccapa, String frlaa, String frlar, String fcapr, String fcapa, String fbearing, String coilcond, String cleancoil, String contactor, String scap, String ctimedelay, String oelectrical, String comppad, String recommendations, String services, String dueamount, String paidamount,String notes,String lpres, String hpres, String startco, String runco, String stacktemp, String ventpipe, String oleaks, String ochimney, String opump, String ocontrols, String otstat, String oprimesafety, String osafetime, String oigntrans, String olubemotors, String ofulemix, String onozzle, String ogross, String osmoke, String onet, String oco2, String oo2, String oco, String oexcessair, String obreachdraft, String ofiredraft, String oeffic, String orating, String opower,String otank, String otcond, String odheat, String ocombustion, String oelectrodes, String obrush, String ofilters,int followup, String airflow, String spres_rated, String spres_supply, String spres_return, String g_filter, String g_electrical, String g_looppres, String g_cleancoil, String g_cleandrain, String g_pansensor, String g_cleancomp,String g_cleanunit,String g_oilblower,String g_cleanpump,String g_tsplit,String g_pampr,String g_pampa,String g_compar,String g_compaa,String g_bampr,String g_bampa,String g_pdrop, String sductsize, String rductsize, String liqtemp, String sucttemp, String r_temp, String s_temp, String rw_temp, String mcfm, String out_temp, String CustSite, String SiteNum, String expansion, String ahage, String conage, String techid, int servsync, String parts, String im_cleanbin, String im_limeaway, String im_bleach, String im_watercomp, String im_icecomp, String im_filter, String im_float, String im_wpump, String im_binsensor, String im_testdump, String im_electrical, String im_comprated, String im_compactual, String ref_condcoilcond, String ref_condcoilcleaned, String ref_evapcoilcond, String ref_evapcoilcleaned, String ref_fanbladecond, String ref_fanbladecleaned, String ref_condensateline, String ref_condfan, String ref_doorgasket, String ref_doorsclose, String ref_productboxcond, String ref_checkscrews, String ref_evaptempin, String ref_evaptempout, String ref_boxtemp, String ref_sightglasslevel, String ref_refrigerant, String ref_defrostclock, String ref_defrosttimes, String ref_doorheater, String ref_electrical, String ref_compamprated, String ref_compampactual)

		throws SQLException
	{
		paidamount="0.00";
		dueamount="0.00";
		Statement stmt = con.createStatement();
		String tservices = services.replaceAll("'","''");
                        String trecommendations = recommendations.replaceAll("'","''");
			servsync=9;
	                stmt.executeUpdate("UPDATE inspection SET custnum="+custnum+",callslip='"+callslip+"',idate='"+idate+"',equip1="+equip1+" ,equip2="+equip2+",equip3="+equip3+" ,equip4="+equip4+", mbearing='"+mbearing+"',mblades='"+mblades+"',ecoil='"+ecoil+"',dline='"+dline+"',dpan='"+dpan+"',ielect='"+ielect+"',mcap='"+mcap+"',hstrips='"+hstrips+"' ,filter='"+filter+"',gpreassures='"+gpreassures+"',ignition='"+ignition+"',burners='"+burners+"',limits='"+limits+"',flame='"+flame+"',dinducer='"+dinducer+"',humidifier='"+humidifier+"',atemp='"+atemp+"',tempsplit='"+tempsplit+"',crlaa='"+crlaa+"',crlar='"+crlar+"',ccapr='"+ccapr+"',ccapa='"+ccapa+"',frlaa='"+frlaa+"',frlar='"+frlar+"',fcapr='"+fcapr+"',fcapa='"+fcapa+"',fbearing='"+fbearing+"',coilcond='"+coilcond+"',cleancoil='"+cleancoil+"',contactor='"+contactor+"',scap='"+scap+"',ctimedelay='"+ctimedelay+"',oelectrical='"+oelectrical+"',comppad='"+comppad+"',recommendations='"+trecommendations+"',services='"+tservices+"',dueamount='"+dueamount+"',paidamount='"+paidamount+"',notes='"+notes+"',lpres='"+lpres+"',hpres='"+hpres+"',startco='"+startco+"',runco='"+runco+"',stacktemp='"+stacktemp+"',ventpipe='"+ventpipe+"', oleaks='"+oleaks+"', ochimney='"+ochimney+"', opump='"+opump+"', ocontrols='"+ocontrols+"', otstat='"+otstat+"', oprimesafety='"+oprimesafety+"', osafetime='"+osafetime+"', oigntrans='"+oigntrans+"', olubemotors='"+olubemotors+"', ofulemix='"+ofulemix+"', onozzle='"+onozzle+"', ogross='"+ogross+"', osmoke='"+osmoke+"', onet='"+onet+"', oco2='"+oco2+"', oo2='"+oo2+"', oco='"+oco+"',oexcessair= '"+oexcessair+"', obreachdraft='"+obreachdraft+"', ofiredraft='"+ofiredraft+"', oeffic='"+oeffic+"', orating='"+orating+"', opower='"+opower+"', otank='"+otank+"', otcond='"+otcond+"', odheat='"+odheat+"', ocombustion='"+ocombustion+"', oelectrodes='"+oelectrodes+"', obrush='"+obrush+"', ofilters='"+ofilters+"',followup="+followup+", airflow='"+airflow+"', spres_rated='"+spres_rated+"', spres_supply='"+spres_supply+"', spres_return='"+spres_return+"', g_filter='"+g_filter+"', g_electrical='"+g_electrical+"', g_looppres='"+g_looppres+"', g_cleancoil='"+g_cleancoil+"', g_cleandrain='"+g_cleandrain+"', g_pansensor='"+g_pansensor+"', g_cleancomp='"+g_cleancomp+"', g_cleanunit='"+g_cleanunit+"', g_oilblower='"+g_oilblower+"', g_cleanpump='"+g_cleanpump+"', g_tsplit='"+g_tsplit+"', g_pampr='"+g_pampr+"', g_pampa='"+g_pampa+"', g_compar='"+g_compar+"', g_compaa='"+g_compaa+"', g_bampr='"+g_bampr+"', g_bampa='"+g_bampa+"', g_pdrop='"+g_pdrop+"', sductsize='"+sductsize+"', rductsize='"+rductsize+"', liqtemp='"+liqtemp+"', sucttemp='"+sucttemp+"', r_temp='"+r_temp+"', s_temp='"+s_temp+"', rw_temp='"+rw_temp+"', mcfm='"+mcfm+"', out_temp='"+out_temp+"', custsite='"+CustSite+"', sitenum='"+SiteNum+"', expansion='"+expansion+"', ahage='"+ahage+"', conage='"+conage+"', techid='"+techid+"', servsync ='"+servsync+"', parts='"+parts+"', im_cleanbin='"+im_cleanbin+"', im_limeaway='"+im_limeaway+"', im_bleach='"+im_bleach+"',  im_watercomp='"+im_watercomp+"',  im_icecomp='"+im_icecomp+"',  im_filter='"+im_filter+"',  im_float='"+im_float+"',  im_wpump='"+im_wpump+"',  im_binsensor='"+im_binsensor+"',  im_testdump='"+im_testdump+"',  im_electrical='"+im_electrical+"',  im_comprated='"+im_comprated+"',  im_compactual='"+im_compactual+"',  ref_condcoilcond='"+ref_condcoilcond+"',  ref_condcoilcleaned='"+ref_condcoilcleaned+"',  ref_evapcoilcond='"+ref_evapcoilcond+"',  ref_evapcoilcleaned='"+ref_evapcoilcleaned+"',  ref_fanbladecond='"+ref_fanbladecond+"',  ref_fanbladecleaned='"+ref_fanbladecleaned+"',  ref_condensateline='"+ref_condensateline+"',  ref_condfan='"+ref_condfan+"',  ref_doorgasket='"+ref_doorgasket+"',  ref_doorsclose='"+ref_doorsclose+"',  ref_productboxcond='"+ref_productboxcond+"',  ref_checkscrews='"+ref_checkscrews+"',  ref_evaptempin='"+ref_evaptempin+"',  ref_evaptempout='"+ref_evaptempout+"',  ref_boxtemp='"+ref_boxtemp+"',  ref_sightglasslevel='"+ref_sightglasslevel+"',  ref_refrigerant='"+ref_refrigerant+"',  ref_defrostclock='"+ref_defrostclock+"',  ref_defrosttimes='"+ref_defrosttimes+"',  ref_doorheater='"+ref_doorheater+"',  ref_electrical='"+ref_electrical+"',  ref_compamprated='"+ref_compamprated+"',  ref_compampactual='"+ref_compampactual+"'  where crecnum="+crecnum+";");
      	}

public static void AddItem(Connection con, int custnum, String callslip, String idate, int equip1, int equip2, int equip3, int equip4,String mbearing, String mblades, String ecoil, String dline, String dpan, String ielect, String mcap, String hstrips, String filter, String gpreassures, String ignition, String burners, String limits, String flame, String dinducer, String humidifier, String atemp, String tempsplit, String crlaa, String crlar, String ccapr, String ccapa, String frlaa, String frlar, String fcapr, String fcapa, String fbearing, String coilcond, String cleancoil, String contactor, String scap, String ctimedelay, String oelectrical, String comppad, String recommendations, String services, String dueamount, String paidamount,String notes,String lpres, String hpres, String startco, String runco, String stacktemp, String ventpipe, String oleaks, String ochimney, String opump, String ocontrols, String otstat, String oprimesafety, String osafetime, String oigntrans, String olubemotors, String ofulemix, String onozzle, String ogross, String osmoke, String onet, String oco2, String oo2, String oco, String oexcessair, String obreachdraft, String ofiredraft, String oeffic, String orating, String opower,String otank, String otcond, String odheat, String ocombustion, String oelectrodes, String obrush, String ofilters,int followup, String airflow, String spres_rated, String spres_supply, String spres_return, String g_filter , String g_electrical, String g_looppres,String g_cleancoil,String g_cleandrain,String g_pansensor,String g_cleancomp,String g_cleanunit,String g_oilblower,String g_cleanpump,String g_tsplit,String g_pampr,String g_pampa,String g_compar,String g_compaa,String g_bampr,String g_bampa,String g_pdrop, String sductsize, String rductsize, String liqtemp, String sucttemp, String r_temp, String s_temp, String rw_temp, String mcfm, String out_temp, String CustSite, String SiteNum, String expansion, String ahage, String conage, String techid, int servsync, String parts, String im_cleanbin, String im_limeaway, String im_bleach, String im_watercomp, String im_icecomp, String im_filter, String im_float, String im_wpump, String im_binsensor, String im_testdump, String im_electrical, String im_comprated, String im_compactual, String ref_condcoilcond, String ref_condcoilcleaned, String ref_evapcoilcond, String ref_evapcoilcleaned, String ref_fanbladecond, String ref_fanbladecleaned, String ref_condensateline, String ref_condfan, String ref_doorgasket, String ref_doorsclose, String ref_productboxcond, String ref_checkscrews, String ref_evaptempin, String ref_evaptempout, String ref_boxtemp, String ref_sightglasslevel, String ref_refrigerant, String ref_defrostclock, String ref_defrosttimes, String ref_doorheater, String ref_electrical, String ref_compamprated, String ref_compampactual)
                throws SQLException
		        {
		paidamount="0.00";
		dueamount="0.00";
	       	        Statement stmt = con.createStatement();
			String tservices = services.replaceAll("'","''");
                        String trecommendations = recommendations.replaceAll("'","''");

	                stmt.executeUpdate("INSERT INTO inspection (custnum, callslip, idate, equip1, equip2, equip3, equip4, mbearing, mblades, ecoil, dline, dpan, ielect, mcap, hstrips, filter, gpreassures, ignition, burners, limits, flame, dinducer, humidifier, atemp, tempsplit, crlaa, crlar, ccapr, ccapa, frlaa, frlar, fcapr, fcapa, fbearing, coilcond, cleancoil, contactor, scap, ctimedelay, oelectrical, comppad, recommendations,services, dueamount, paidamount,notes,lpres, hpres, startco, runco, stacktemp, ventpipe, oleaks, ochimney, opump, ocontrols, otstat, oprimesafety, osafetime, oigntrans, olubemotors, ofulemix, onozzle,ogross, osmoke, onet, oco2, oo2, oco, oexcessair, obreachdraft, ofiredraft, oeffic, orating, opower,otank, otcond, odheat, ocombustion, oelectrodes, obrush, ofilters, followup, airflow, spres_rated, spres_supply, spres_return,g_filter,g_electrical,g_looppres,g_cleancoil,g_cleandrain,g_pansensor,g_cleancomp,g_cleanunit,g_oilblower,g_cleanpump,g_tsplit,g_pampr,g_pampa,g_compar,g_compaa,g_bampr,g_bampa,g_pdrop, sductsize, rductsize, liqtemp, sucttemp, r_temp, s_temp, rw_temp, mcfm, out_temp, custsite, sitenum, expansion, ahage, conage, techid, servsync, parts, im_cleanbin, im_limeaway, im_bleach, im_watercomp, im_icecomp, im_filter, im_float, im_wpump, im_binsensor, im_testdump, im_electrical, im_comprated, im_compactual, ref_condcoilcond, ref_condcoilcleaned, ref_evapcoilcond, ref_evapcoilcleaned, ref_fanbladecond, ref_fanbladecleaned, ref_condensateline, ref_condfan, ref_doorgasket, ref_doorsclose, ref_productboxcond, ref_checkscrews, ref_evaptempin, ref_evaptempout, ref_boxtemp, ref_sightglasslevel, ref_refrigerant, ref_defrostclock, ref_defrosttimes, ref_doorheater, ref_electrical, ref_compamprated, ref_compampactual) VALUES ('"+custnum+"','"+callslip+"','"+idate+"',"+equip1+" ,"+equip2+","+equip3+" ,"+equip4+",'"+mbearing+"','"+mblades+"','"+ecoil+"','"+dline+"','"+dpan+"','"+ielect+"','"+mcap+"','"+hstrips+"' ,'"+filter+"','"+gpreassures+"','"+ignition+"','"+burners+"','"+limits+"','"+flame+"','"+dinducer+"','"+humidifier+"','"+atemp+"','"+tempsplit+"','"+crlaa+"','"+crlar+"','"+ccapr+"','"+ccapa+"','"+frlaa+"','"+frlar+"','"+fcapr+"','"+fcapa+"','"+fbearing+"','"+coilcond+"','"+cleancoil+"','"+contactor+"','"+scap+"','"+ctimedelay+"','"+oelectrical+"','"+comppad+"','"+trecommendations+"','"+tservices+"','"+dueamount+"','"+paidamount+"','"+notes+"','"+lpres+"','"+hpres+"','"+startco+"','"+runco+"','"+stacktemp+"','"+ventpipe+"', '"+oleaks+"', '"+ochimney+"', '"+opump+"', '"+ocontrols+"', '"+otstat+"', '"+oprimesafety+"', '"+osafetime+"', '"+oigntrans+"', '"+olubemotors+"', '"+ofulemix+"', '"+onozzle+"', '"+ogross+"', '"+osmoke+"', '"+onet+"', '"+oco2+"', '"+oo2+"', '"+oco+"', '"+oexcessair+"', '"+obreachdraft+"', '"+ofiredraft+"', '"+oeffic+"', '"+orating+"', '"+opower+"', '"+otank+"', '"+otcond+"', '"+odheat+"', '"+ocombustion+"', '"+oelectrodes+"', '"+obrush+"', '"+ofilters+"',"+followup+",'"+airflow+"','"+spres_rated+"','"+spres_supply+"','"+spres_return+"','"+g_filter+"','"+g_electrical+"','"+g_looppres+"','"+g_cleancoil+"','"+g_cleandrain+"','"+g_pansensor+"','"+g_cleancomp+"','"+g_cleanunit+"','"+g_oilblower+"','"+g_cleanpump+"','"+g_tsplit+"','"+g_pampr+"','"+g_pampa+"','"+g_compar+"','"+g_compaa+"','"+g_bampr+"','"+g_bampa+"','"+g_pdrop+"', '"+sductsize+"', '"+rductsize+"','"+liqtemp+"','"+sucttemp+"', '"+r_temp+"', '"+s_temp+"', '"+rw_temp+"', '"+mcfm+"','"+out_temp+"','"+CustSite+"','"+SiteNum+"', '"+expansion+"','"+ahage+"', '"+conage+"', '"+techid+"', '"+servsync+"', '"+parts+"', '"+im_cleanbin+"','"+im_limeaway+"','"+im_bleach+"','"+im_watercomp+"','"+im_icecomp+"','"+im_filter+"','"+im_float+"','"+im_wpump+"','"+im_binsensor+"','"+im_testdump+"','"+im_electrical+"','"+im_comprated+"','"+im_compactual+"','"+ref_condcoilcond+"','"+ref_condcoilcleaned+"','"+ref_evapcoilcond+"','"+ref_evapcoilcleaned+"','"+ref_fanbladecond+"','"+ref_fanbladecleaned+"','"+ref_condensateline+"','"+ref_condfan+"','"+ref_doorgasket+"','"+ref_doorsclose+"','"+ref_productboxcond+"','"+ref_checkscrews+"','"+ref_evaptempin+"','"+ref_evaptempout+"','"+ref_boxtemp+"','"+ref_sightglasslevel+"','"+ref_refrigerant+"','"+ref_defrostclock+"','"+ref_defrosttimes+"','"+ref_doorheater+"','"+ref_electrical+"','"+ref_compamprated+"','"+ref_compampactual+"')");
		        }


        public static void deleteItem(Connection con, String d)
                throws SQLException
        {
                int x = Integer.parseInt(d);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From inspection  Where crecnum=" + x + ";");
        }

	
	        public static void deleteCustItems(Connection con, int d)
                throws SQLException
        {
                
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From inspection  Where custnum=" + d + ";");
        }
	
		public int getCrecnum()  { return crecnum ;}
		public int getCustnum()  { return custnum ;}
		public String getCustSite() { return custsite;}
		public String getSiteNum() { return sitenum;}
		public String getCallslip()  { return callslip ;}
		public String getIdate()  { return idate ;}
		public int getEquip1()  { return equip1 ;}
		public int getEquip2() { return equip2 ;}
		public int getEquip3()  { return equip3 ;}
		public int getEquip4() { return equip4 ;}
		public String getMbearing()  { return mbearing ;}
		public String getMblades()  { return mblades  ;}
		public String getEcoil()  { return ecoil ;}
		public String getDline()  { return dline ;}
		public String getDpan()  { return dpan ;}
		public String getIelect()  { return ielect ;}
		public String getMcap()  { return mcap ;}
		public String getHstrips()  { return hstrips ;}
		public String getFilter()  { return filter ;}
		public String getGpreassures()  { return gpreassures ;}
		public String getIgnition()  { return ignition ;}
		public String getBurners()  { return burners ;}
		public String getLimits()  { return limits ;}
		public String getFlame()  { return  flame;}
		public String getDinducer()  { return dinducer ;}
		public String getHumidifier()  { return humidifier ;}
		public String getAtemp()  { return atemp  ;}
		public String getTempsplit()  { return tempsplit ;}
		public String getCrlaa()  { return crlaa ;}
		public String getCrlar()  { return crlar ;}
		public String getCcapr()  { return ccapr ;}
		public String getCcapa()  { return ccapa ;}
		public String getFrlaa()  { return frlaa ;}
		public String getFrlar()  { return frlar ;}
		public String getFcapr()  { return fcapr ;}
		public String getFcapa()  { return fcapa ;}
		public String getFbearing()  { return fbearing ;}
		public String getCoilcond()  { return coilcond ;}
		public String getCleancoil()  { return cleancoil  ;}
		public String getContactor()  { return contactor ;}
		public String getScap()  { return scap ;}
		public String getCtimedelay()  { return ctimedelay ;}
		public String getOelectrical()  { return oelectrical ;}
		public String getComppad()  { return comppad ;}
		public String getRecommendations()  { return recommendations  ;}
		public String getServices()  { return services ;}
		public String getDueamount()  { return dueamount  ;}
		public String getPaidamount()  { return paidamount ;}
		public String getNotes()  { return notes ;}
		public String getLpres()  { return lpres  ;}
		public String getHpres()  { return hpres ;}
		public String getStartco()  { return startco ;}
		public String getRunco()  { return runco ;}
		public String getStacktemp()  { return stacktemp ;}
		public String getVentpipe() { return ventpipe ;}
		public String getOleaks()  { return oleaks ;}
		public String getOchimney()  { return ochimney ;}
		public String getOpump()  { return opump ;}
		public String getOcontrols()  { return ocontrols ;}
		public String getOtstat()  { return otstat ;}
		public String getOprimesafety()  { return oprimesafety ;}
		public String getOsafetime()  { return osafetime ;}
		public String getOigntrans()  { return oigntrans ;}
		public String getOlubemotors()  { return olubemotors ;}
		public String getOfulemix()  { return ofulemix  ;}
		public String getOnozzle()  { return onozzle ;}
		public String getOgross()  { return ogross ;}
		public String getOnet()  { return onet ;}
		public String getOsmoke()  { return osmoke ;}
		public String getOco2()  { return  oco2;}
		public String getOo2()  { return oo2 ;}
		public String getOco()  { return oco ;}
		public String getOexcessair()  { return  oexcessair;}
		public String getObreachdraft()  { return obreachdraft ;}
		public String getOfiredraft()  { return ofiredraft ;}
		public String getOeffic()  { return oeffic ;}
		public String getOrating()  { return orating ;}
		public String getOpower()  { return opower ;}
		public String getOtank()  { return otank ;}
		public String getOtcond()  { return otcond ;}
		public String getOdheat()  { return  odheat;}
		public String getOcombustion()  { return ocombustion ;}
		public String getOelectrodes()  { return oelectrodes ;}
		public String getObrush()  { return obrush ;}
		public String getOfilters()  { return ofilters ;}
		public int getFollowup()  { return  followup;}
		public String getAirflow()  { return airflow;}
		public String getSpresRated()  { return spres_rated;}
		public String getSpresSupply()  { return spres_supply;}
		public String getSpresReturn()  { return spres_return;}
       		public String getGFilter()	{return g_filter;}
        	public String getGElectrical()	{return g_electrical;}
        	public String getGLooppres()	{return g_looppres;}
        	public String getGCleanCoil()	{return g_cleancoil;}
        	public String getGCleanDrain()	{return g_cleandrain;}
        	public String getGPanSensor()	{return g_pansensor;}
        	public String getGCleanComp()	{return g_cleancomp;}
        	public String getGCleanUnit()	{return g_cleanunit;}
        	public String getGOilBlower()	{return g_oilblower;}
        	public String getGCleanPump()	{return g_cleanpump;}
        	public String getGTsplit()	{return g_tsplit;}
        	public String getGPampr()	{return g_pampr;}
        	public String getGPampa()	{return g_pampa;}
        	public String getGCompar()	{return g_compar;}
        	public String getGCompaa()	{return g_compaa;}
        	public String getGBampr()	{return g_bampr;}
        	public String getGBampa()	{return g_bampa;}
        	public String getGPdrop()	{return g_pdrop;}
        	public String getRDuctSize()	{return rductsize;}
        	public String getSDuctSize()	{return sductsize;}
        	public String getLiqTemp()	{return liqtemp;}
        	public String getSuctTemp()	{return sucttemp;}
		public String getRTemp()	{return r_temp;}
		public String getSTemp()	{return s_temp;}
		public String getRWTemp()	{return rw_temp;}
		public String getMCfm()		{return mcfm;}
		public String getOutTemp()	{return out_temp;}
		public String getExpansion()		{return expansion;}
		public String getAHAge()		{return ahage;}
		public String getConAge()		{return conage;}
		public String getTechID()		{return techid;}
		public int getServSync()		{return servsync;}
		public String getParts()	{return parts;}
		public String im_cleanbin() {return im_cleanbin; }
		public String im_limeaway() {return im_limeaway; }
		public String im_bleach() {return im_bleach; }
		public String im_watercomp() {return im_watercomp; }
		public String im_icecomp() {return im_icecomp; }
		public String im_filter() {return im_filter; }
		public String im_float() {return im_float; }
		public String im_wpump() {return im_wpump; }
		public String im_binsensor() {return im_binsensor; }
		public String im_testdump() {return im_testdump; }
		public String im_electrical() {return im_electrical; }
		public String im_comprated() {return im_comprated; }
		public String im_compactual() {return im_compactual; }
		public String ref_condcoilcond() {return ref_condcoilcond; }
		public String ref_condcoilcleaned() {return ref_condcoilcleaned; }
		public String ref_evapcoilcond() {return ref_evapcoilcond; }
		public String ref_evapcoilcleaned() {return ref_evapcoilcleaned; }
		public String ref_fanbladecond() {return ref_fanbladecond; }
		public String ref_fanbladecleaned() {return ref_fanbladecleaned; }
		public String ref_condensateline() {return ref_condensateline; }
		public String ref_condfan() {return ref_condfan; }
		public String ref_doorgasket() {return ref_doorgasket; }
		public String ref_doorsclose() {return ref_doorsclose; }
		public String ref_productboxcond() {return ref_productboxcond; }
		public String ref_checkscrews() {return ref_checkscrews; }
		public String ref_evaptempin() {return ref_evaptempin; }
		public String ref_evaptempout() {return ref_evaptempout; }
		public String ref_boxtemp() {return ref_boxtemp; }
		public String ref_sightglasslevel() {return ref_sightglasslevel; }
		public String ref_refrigerant() {return ref_refrigerant; }
		public String ref_defrostclock() {return ref_defrostclock; }
		public String ref_defrosttimes() {return ref_defrosttimes; }
		public String ref_doorheater() {return ref_doorheater; }
		public String ref_electrical() {return ref_electrical; }
		public String ref_compamprated() {return ref_compamprated; }
		public String ref_compampactual() {return ref_compampactual; }

}
