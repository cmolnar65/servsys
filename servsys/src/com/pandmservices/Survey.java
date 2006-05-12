package com.pandmservices; 
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class Survey
{
        private int recnum;
        	private int custnum=0;
        	private int ennum=0;
        	private String brand=null;
        	private String modelnum=null;
        	private String serialnum=null;
        	private String filter=null;
        	private String notes=null;
		private String gpipingsize="-";
		private String dwaterfeedsize="-";
		private String dwatersupplysize="-";
		private String bwatersupplysize="-";
		private String bwaterreturnsize="-";
		private String supplymanifold="-";
		private String returnmanifold="-";
		private String numzones="-";
		private String zonecontrol="-";
		private String nfloors="-";
		private String olength="-";
		private String owidth="-";
		private String oheight="-";
		private String buse="-";
		private String bage="-";
		private String bventalation="-";
		private String bhumidification="-";
		private String bcontrols="-";
		private String ecurrentvoltage="-";
		private String ecurrentphase="-";
		private String evoltageneeded="-";
		private String ephaseneeded="-";
		private String epanbrand="-";
		private String epanelroom="-";
		private String epanlocation="-";
		private String eaddwork="-";
		private String fueltype="-";
		private String furntype="-";
		private String windowtype="-";
		private String glasstype="-";
		private String stormwindows="-";
		private String doortype="-";
		private String infiltration="-";
		private String sdate="-";
//		String ="-";

        public Survey (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from custsurvey where recnum='"+recnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recnum=rs.getInt("recnum");
		this.custnum=rs.getInt("custnum");
		this.gpipingsize = rs.getString("gpipingsize");
		this.dwaterfeedsize = rs.getString("dwaterfeedsize");
		this.dwatersupplysize = rs.getString("dwatersupplysize");
		this.bwatersupplysize = rs.getString("bwatersupplysize");
		this.bwaterreturnsize = rs.getString("bwaterreturnsize");
		this.supplymanifold = rs.getString("supplymanifold");
		this.returnmanifold = rs.getString("returnmanifold");
		this.numzones = rs.getString("numzones");
		this.zonecontrol = rs.getString("zonecontrol");
		this.nfloors = rs.getString("nfloors");
		this.olength = rs.getString("olength");
		this.owidth = rs.getString("owidth");
		this.oheight = rs.getString("oheight");
		this.buse = rs.getString("buse");
		this.bage = rs.getString("bage");
		this.bventalation = rs.getString("bventalation");
		this.bcontrols = rs.getString("bcontrols");
		this.bhumidification= rs.getString("bhumidification");
		this.ecurrentvoltage = rs.getString("ecurrentvoltage");
		this.ecurrentphase = rs.getString("ecurrentphase");
		this.evoltageneeded = rs.getString("evoltageneeded");
		this.ephaseneeded = rs.getString("ephaseneeded");
		this.epanbrand = rs.getString("epanbrand");
		this.epanelroom = rs.getString("epanelroom");
		this.epanlocation = rs.getString("epanlocation");
		this.eaddwork = rs.getString("eaddwork");
		this.fueltype = rs.getString("fueltype");
		this.furntype = rs.getString("furntype");
		this.windowtype = rs.getString("windowtype");
		this.glasstype = rs.getString("glasstype");
		this.stormwindows = rs.getString("stormwindows");
		this.doortype = rs.getString("doortype");
		this.infiltration = rs.getString("infiltration");
		this.sdate = rs.getString("sdate");
	}


	public static Vector getIndItem(Connection c, int recnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM custsurvey where recnum="+recnum+";");
		while(rs.next())
		{
			Survey t = new Survey(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM custsurvey where custnum="+custnum+";");
		while(rs.next())
		{
			Survey t = new Survey(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static void UpdateItem( Connection con, int recnum, int custnum, String gpipingsize, String dwaterfeedsize, String dwatersupplysize, String bwatersupplysize, String bwaterreturnsize, String supplymanifold, String returnmanifold, String numzones, String zonecontrol, String nfloors, String olength, String owidth, String oheight, String buse, String bage, String bventalation, String bhumidification, String bcontrols, String ecurrentvoltage,String ecurrentphase, String evoltageneeded, String ephaseneeded, String epanbrand, String epanelroom, String epanlocation, String eaddwork, String fueltype, String furntype, String windowtype, String glasstype, String stormwindows, String doortype, String infiltration,String sdate)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("UPDATE custsurvey set custnum='"+custnum+"', gpipingsize='"+gpipingsize+"',dwaterfeedsize='"+dwaterfeedsize+"',dwatersupplysize='"+dwatersupplysize+"',bwatersupplysize='"+bwatersupplysize+"',bwaterreturnsize='"+bwaterreturnsize+"',supplymanifold='"+supplymanifold+"', returnmanifold='"+returnmanifold+"', numzones='"+numzones+"', zonecontrol='"+zonecontrol+"', nfloors='"+nfloors+"', olength='"+olength+"', owidth='"+owidth+"', oheight='"+oheight+"', buse='"+buse+"', bage='"+bage+"', bventalation='"+bventalation+"', bhumidification='"+bhumidification+"', bcontrols='"+bcontrols+"', ecurrentvoltage='"+ecurrentvoltage+"', ecurrentphase='"+ecurrentphase+"', evoltageneeded='"+evoltageneeded+"', ephaseneeded='"+ephaseneeded+"', epanbrand='"+epanbrand+"', epanelroom='"+epanelroom+"', epanlocation='"+epanlocation+"', eaddwork='"+eaddwork+"', fueltype='"+fueltype+"', furntype='"+furntype+"', windowtype='"+windowtype+"', glasstype='"+glasstype+"', stormwindows='"+stormwindows+"', doortype='"+doortype+"', infiltration='"+infiltration+"', sdate='"+sdate+"'");
			}



	public static void AddItem( Connection con, int custnum, String gpipingsize, String dwaterfeedsize, String dwatersupplysize, String bwatersupplysize, String bwaterreturnsize, String supplymanifold, String returnmanifold, String numzones, String zonecontrol, String nfloors, String olength, String owidth, String oheight, String buse, String bage, String bventalation, String bhumidification, String bcontrols, String ecurrentvoltage, String ecurrentphase, String evoltageneeded, String ephaseneeded, String epanbrand, String epanelroom, String epanlocation, String eaddwork, String fueltype, String furntype, String windowtype, String glasstype, String stormwindows, String doortype, String infiltration, String sdate ) 
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO custsurvey (custnum, gpipingsize, dwaterfeedsize, dwatersupplysize, bwatersupplysize, bwaterreturnsize, supplymanifold, returnmanifold, numzones, zonecontrol, nfloors, olength, owidth, oheight, buse, bage, bventalation, bhumidification, bcontrols, ecurrentvoltage, ecurrentphase, evoltageneeded, ephaseneeded, epanbrand, epanelroom, epanlocation, eaddwork, fueltype, furntype, windowtype, glasstype, stormwindows, doortype, infiltration, sdate) values ('"+custnum+"', '"+gpipingsize+"','"+dwaterfeedsize+"','"+dwatersupplysize+"','"+bwatersupplysize+"','"+bwaterreturnsize+"','"+supplymanifold+"', '"+returnmanifold+"', '"+numzones+"', '"+zonecontrol+"', '"+nfloors+"', '"+olength+"', '"+owidth+"', '"+oheight+"', '"+buse+"', '"+bage+"', '"+bventalation+"', '"+bhumidification+"', '"+bcontrols+"', '"+ecurrentvoltage+"', '"+ecurrentphase+"', '"+evoltageneeded+"', '"+ephaseneeded+"', '"+epanbrand+"', '"+epanelroom+"', '"+epanlocation+"', '"+eaddwork+"', '"+fueltype+"', '"+furntype+"', '"+windowtype+"', '"+glasstype+"', '"+stormwindows+"', '"+doortype+"', '"+infiltration+"','"+sdate+"')");
		        }

	public static void DeleteAllItem( Connection con, int custnum) 
                throws SQLException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("delete from custsurvey where custnum="+custnum+";");
		        }

        public int getRecnum() { return recnum; }
        public int getCustnum() { return custnum; }
        	public String getBrand() { return brand ;}
        	public String getModelnum() { return modelnum ;}
        	public String getSerialnum() { return serialnum ;}
        	public String getFilter() { return filter ;}
        	public String getNotes() { return notes ;}
		public String getPipingsize() { return gpipingsize ;}
		public String getDwaterfeedsize() { return dwaterfeedsize ;}
		public String getDwatersupplysize() { return dwatersupplysize ;}
		public String getBwatersupplysize() { return bwatersupplysize ;}
		public String getBwaterreturnsize() { return bwaterreturnsize ;}
		public String getSupplymanifold() { return supplymanifold ;}
		public String getReturnmanifold() { return returnmanifold ;}
		public String getNumzones() { return numzones ;}
		public String getZonecontrol() { return zonecontrol ;}
		public String getNfloors() { return nfloors ;}
		public String getOlength() { return olength ;}
		public String getOwidth() { return owidth ;}
		public String getOheight() { return oheight ;}
		public String getBuse() { return buse ;}
		public String getBage() { return bage ;}
		public String getBventalation() { return bventalation ;}
		public String getBhumidification() { return bhumidification ;}
		public String getBcontrols() { return bcontrols ;}
		public String getEcurrentvoltage(){ return ecurrentvoltage ;}
		public String getEcurrentphase() { return ecurrentphase ;}
		public String getEvoltageneeded() { return evoltageneeded ;}
		public String getEphaseneeded() { return ephaseneeded ;}
		public String getEpanbrand() { return epanbrand ;}
		public String getEpanelroom() { return epanelroom ;}
		public String getEpanlocation() { return epanlocation ;}
		public String getEaddwork() { return eaddwork ;}
		public String getFueltype() { return fueltype ;}
		public String getFurntype() { return furntype ;}
		public String getWindowtype() { return windowtype ;}
		public String getGlasstype() { return glasstype ;}
		public String getStormwindows() { return stormwindows ;}
		public String getDoortype() { return doortype ;}
		public String getInfiltration() { return infiltration ;}
		public String getSdate() { return sdate ;}
//		String ="-";
}
