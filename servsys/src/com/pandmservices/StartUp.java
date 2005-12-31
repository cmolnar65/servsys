package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class StartUp
{
		private int crecnum = 0;
		private int custnum = 0;
		private String callslip = null;
		private String idate = null;
		private int equip1 = 0;
		private int equip2 = 0;
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



        public StartUp (Connection c, int crecnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM startup WHERE crecnum=" + crecnum+ ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + crecnum);
		}
		this.crecnum = rs.getInt("crecnum");
		this.custnum = rs.getInt("custnum");
		this.callslip = rs.getString("callslip");
		this.idate = rs.getString("idate");
		this.equip1 = rs.getInt("equip1");
		this.equip2 = rs.getInt("equip2");
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
	}



	public static Vector getAllItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM startup where custnum='"+custnum+"' order by idate ");
		while(rs.next())
		{
			
			StartUp t = new StartUp(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllDateItems(Connection c, String listdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM startup where idate='"+listdate+"' order by callslip");
		while(rs.next())
		{
			
			StartUp t = new StartUp(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndItems(Connection c, int custnum, int crecnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM startup where crecnum='"+crecnum+"' ORDER BY idate;");
		while(rs.next())
		{
			
			StartUp t = new StartUp(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

public static void UpdateItem(Connection con, int crecnum, int custnum, String callslip, String idate, int equip1, int equip2, String mbearing, String mblades, String ecoil, String dline, String dpan, String ielect, String mcap, String hstrips, String filter, String gpreassures, String ignition, String burners, String limits, String flame, String dinducer, String humidifier, String atemp, String tempsplit, String crlaa, String crlar, String ccapr, String ccapa, String frlaa, String frlar, String fcapr, String fcapa, String fbearing, String coilcond, String cleancoil, String contactor, String scap, String ctimedelay, String oelectrical, String comppad, String recommendations, String services, String dueamount, String paidamount,String notes,String lpres, String hpres, String startco, String runco, String stacktemp, String ventpipe, String oleaks, String ochimney, String opump, String ocontrols, String otstat, String oprimesafety, String osafetime, String oigntrans, String olubemotors, String ofulemix, String onozzle, String ogross, String osmoke, String onet, String oco2, String oo2, String oco, String oexcessair, String obreachdraft, String ofiredraft, String oeffic, String orating, String opower,String otank, String otcond, String odheat, String ocombustion, String oelectrodes, String obrush, String ofilters,int followup, String airflow, String spres_rated, String spres_supply, String spres_return, String g_filter, String g_electrical, String g_looppres, String g_cleancoil, String g_cleandrain, String g_pansensor, String g_cleancomp,String g_cleanunit,String g_oilblower,String g_cleanpump,String g_tsplit,String g_pampr,String g_pampa,String g_compar,String g_compaa,String g_bampr,String g_bampa,String g_pdrop, String sductsize, String rductsize)

		throws SQLException
	{
		paidamount="0.00";
		dueamount="0.00";
		Statement stmt = con.createStatement();
		String tservices = services.replaceAll("'","''");
                        String trecommendations = recommendations.replaceAll("'","''");

	                stmt.executeUpdate("UPDATE startup SET custnum="+custnum+",callslip='"+callslip+"',idate='"+idate+"',equip1="+equip1+" ,equip2="+equip2+",mbearing='"+mbearing+"',mblades='"+mblades+"',ecoil='"+ecoil+"',dline='"+dline+"',dpan='"+dpan+"',ielect='"+ielect+"',mcap='"+mcap+"',hstrips='"+hstrips+"' ,filter='"+filter+"',gpreassures='"+gpreassures+"',ignition='"+ignition+"',burners='"+burners+"',limits='"+limits+"',flame='"+flame+"',dinducer='"+dinducer+"',humidifier='"+humidifier+"',atemp='"+atemp+"',tempsplit='"+tempsplit+"',crlaa='"+crlaa+"',crlar='"+crlar+"',ccapr='"+ccapr+"',ccapa='"+ccapa+"',frlaa='"+frlaa+"',frlar='"+frlar+"',fcapr='"+fcapr+"',fcapa='"+fcapa+"',fbearing='"+fbearing+"',coilcond='"+coilcond+"',cleancoil='"+cleancoil+"',contactor='"+contactor+"',scap='"+scap+"',ctimedelay='"+ctimedelay+"',oelectrical='"+oelectrical+"',comppad='"+comppad+"',recommendations='"+trecommendations+"',services='"+tservices+"',dueamount='"+dueamount+"',paidamount='"+paidamount+"',notes='"+notes+"',lpres='"+lpres+"',hpres='"+hpres+"',startco='"+startco+"',runco='"+runco+"',stacktemp='"+stacktemp+"',ventpipe='"+ventpipe+"', oleaks='"+oleaks+"', ochimney='"+ochimney+"', opump='"+opump+"', ocontrols='"+ocontrols+"', otstat='"+otstat+"', oprimesafety='"+oprimesafety+"', osafetime='"+osafetime+"', oigntrans='"+oigntrans+"', olubemotors='"+olubemotors+"', ofulemix='"+ofulemix+"', onozzle='"+onozzle+"', ogross='"+ogross+"', osmoke='"+osmoke+"', onet='"+onet+"', oco2='"+oco2+"', oo2='"+oo2+"', oco='"+oco+"',oexcessair= '"+oexcessair+"', obreachdraft='"+obreachdraft+"', ofiredraft='"+ofiredraft+"', oeffic='"+oeffic+"', orating='"+orating+"', opower='"+opower+"', otank='"+otank+"', otcond='"+otcond+"', odheat='"+odheat+"', ocombustion='"+ocombustion+"', oelectrodes='"+oelectrodes+"', obrush='"+obrush+"', ofilters='"+ofilters+"',followup="+followup+", airflow='"+airflow+"', spres_rated='"+spres_rated+"', spres_supply='"+spres_supply+"', spres_return='"+spres_return+"', g_filter='"+g_filter+"', g_electrical='"+g_electrical+"', g_looppres='"+g_looppres+"', g_cleancoil='"+g_cleancoil+"', g_cleandrain='"+g_cleandrain+"', g_pansensor='"+g_pansensor+"', g_cleancomp='"+g_cleancomp+"', g_cleanunit='"+g_cleanunit+"', g_oilblower='"+g_oilblower+"', g_cleanpump='"+g_cleanpump+"', g_tsplit='"+g_tsplit+"', g_pampr='"+g_pampr+"', g_pampa='"+g_pampa+"', g_compar='"+g_compar+"', g_compaa='"+g_compaa+"', g_bampr='"+g_bampr+"', g_bampa='"+g_bampa+"', g_pdrop='"+g_pdrop+"', sductsize='"+sductsize+"', rductsize='"+rductsize+"' where crecnum="+crecnum+";");
      	}

public static void AddItem(Connection con, int custnum, String callslip, String idate, int equip1, int equip2, String mbearing, String mblades, String ecoil, String dline, String dpan, String ielect, String mcap, String hstrips, String filter, String gpreassures, String ignition, String burners, String limits, String flame, String dinducer, String humidifier, String atemp, String tempsplit, String crlaa, String crlar, String ccapr, String ccapa, String frlaa, String frlar, String fcapr, String fcapa, String fbearing, String coilcond, String cleancoil, String contactor, String scap, String ctimedelay, String oelectrical, String comppad, String recommendations, String services, String dueamount, String paidamount,String notes,String lpres, String hpres, String startco, String runco, String stacktemp, String ventpipe, String oleaks, String ochimney, String opump, String ocontrols, String otstat, String oprimesafety, String osafetime, String oigntrans, String olubemotors, String ofulemix, String onozzle, String ogross, String osmoke, String onet, String oco2, String oo2, String oco, String oexcessair, String obreachdraft, String ofiredraft, String oeffic, String orating, String opower,String otank, String otcond, String odheat, String ocombustion, String oelectrodes, String obrush, String ofilters,int followup, String airflow, String spres_rated, String spres_supply, String spres_return, String g_filter , String g_electrical, String g_looppres,String g_cleancoil,String g_cleandrain,String g_pansensor,String g_cleancomp,String g_cleanunit,String g_oilblower,String g_cleanpump,String g_tsplit,String g_pampr,String g_pampa,String g_compar,String g_compaa,String g_bampr,String g_bampa,String g_pdrop, String sductsize, String rductsize)
                throws SQLException
		        {
		paidamount="0.00";
		dueamount="0.00";
	       	        Statement stmt = con.createStatement();
			String tservices = services.replaceAll("'","''");
                        String trecommendations = recommendations.replaceAll("'","''");

	                stmt.executeUpdate("INSERT INTO startup (custnum, callslip, idate, equip1, equip2, mbearing, mblades, ecoil, dline, dpan, ielect, mcap, hstrips, filter, gpreassures, ignition, burners, limits, flame, dinducer, humidifier, atemp, tempsplit, crlaa, crlar, ccapr, ccapa, frlaa, frlar, fcapr, fcapa, fbearing, coilcond, cleancoil, contactor, scap, ctimedelay, oelectrical, comppad, recommendations,services, dueamount, paidamount,notes,lpres, hpres, startco, runco, stacktemp, ventpipe, oleaks, ochimney, opump, ocontrols, otstat, oprimesafety, osafetime, oigntrans, olubemotors, ofulemix, onozzle,ogross, osmoke, onet, oco2, oo2, oco, oexcessair, obreachdraft, ofiredraft, oeffic, orating, opower,otank, otcond, odheat, ocombustion, oelectrodes, obrush, ofilters, followup, airflow, spres_rated, spres_supply, spres_return,g_filter,g_electrical,g_looppres,g_cleancoil,g_cleandrain,g_pansensor,g_cleancomp,g_cleanunit,g_oilblower,g_cleanpump,g_tsplit,g_pampr,g_pampa,g_compar,g_compaa,g_bampr,g_bampa,g_pdrop, sductsize, rductsize) VALUES ('"+custnum+"','"+callslip+"','"+idate+"',"+equip1+" ,"+equip2+",'"+mbearing+"','"+mblades+"','"+ecoil+"','"+dline+"','"+dpan+"','"+ielect+"','"+mcap+"','"+hstrips+"' ,'"+filter+"','"+gpreassures+"','"+ignition+"','"+burners+"','"+limits+"','"+flame+"','"+dinducer+"','"+humidifier+"','"+atemp+"','"+tempsplit+"','"+crlaa+"','"+crlar+"','"+ccapr+"','"+ccapa+"','"+frlaa+"','"+frlar+"','"+fcapr+"','"+fcapa+"','"+fbearing+"','"+coilcond+"','"+cleancoil+"','"+contactor+"','"+scap+"','"+ctimedelay+"','"+oelectrical+"','"+comppad+"','"+trecommendations+"','"+tservices+"','"+dueamount+"','"+paidamount+"','"+notes+"','"+lpres+"','"+hpres+"','"+startco+"','"+runco+"','"+stacktemp+"','"+ventpipe+"', '"+oleaks+"', '"+ochimney+"', '"+opump+"', '"+ocontrols+"', '"+otstat+"', '"+oprimesafety+"', '"+osafetime+"', '"+oigntrans+"', '"+olubemotors+"', '"+ofulemix+"', '"+onozzle+"', '"+ogross+"', '"+osmoke+"', '"+onet+"', '"+oco2+"', '"+oo2+"', '"+oco+"', '"+oexcessair+"', '"+obreachdraft+"', '"+ofiredraft+"', '"+oeffic+"', '"+orating+"', '"+opower+"', '"+otank+"', '"+otcond+"', '"+odheat+"', '"+ocombustion+"', '"+oelectrodes+"', '"+obrush+"', '"+ofilters+"',"+followup+",'"+airflow+"','"+spres_rated+"','"+spres_supply+"','"+spres_return+"','"+g_filter+"','"+g_electrical+"','"+g_looppres+"','"+g_cleancoil+"','"+g_cleandrain+"','"+g_pansensor+"','"+g_cleancomp+"','"+g_cleanunit+"','"+g_oilblower+"','"+g_cleanpump+"','"+g_tsplit+"','"+g_pampr+"','"+g_pampa+"','"+g_compar+"','"+g_compaa+"','"+g_bampr+"','"+g_bampa+"','"+g_pdrop+"', '"+sductsize+"', '"+rductsize+"')");
		        }


        public static void deleteItem(Connection con, String d)
                throws SQLException
        {
                int x = Integer.parseInt(d);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From startup  Where crecnum=" + x + ";");
        }

		public int getCrecnum()  { return crecnum ;}
		public int getCustnum()  { return custnum ;}
		public String getCallslip()  { return callslip ;}
		public String getIdate()  { return idate ;}
		public int getEquip1()  { return equip1 ;}
		public int getEquip2() { return equip2 ;}
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

}
