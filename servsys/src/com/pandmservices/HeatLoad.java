package com.pandmservices; 
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class HeatLoad
{
        private int recnum;
	private int ceilingsqft;
	private int wallsqft;
	private int nwinsqft;
	private int swinsqft;
	private int ewinsqft;
	private int wwinsqft;
	private int nsldoorsqft;
	private int ssldoorsqft;
	private int esldoorsqft;
	private int wsldoorsqft;
	private int doorsqft;
	private int totglasssqft;
	private int nframewall;
	private int nmasawall;
	private int nmasbwall;
	private int basfloorsqft;
	private int slabnoperim;
	private int slabwperim;
	private int mechvent;
	private int numpeople;
	private int flooruncond;
	private int bldlength;
	private int bldwidth;
	private int bldheight;
	
	private String custsitenum;
	private String sitenum;
	private String ceiltype;
	private String walltype;
	private String nwintype;
	private String swintype;
	private String ewintype;
	private String wwintype;
	private String nsldoortype;
	private String ssldoortype;
	private String esldoortype;
	private String wsldoortype;
	private String doortype;
	private String nframewalltype;
	private String nmasawalltype;
	private String nmasbwalltype;
	private String slabnoperimtype;
	private String slabwperimtype;
	private String flooruncondtype;
	private String loaddate;
	
        public HeatLoad(Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from heatloads where recnum="+recnum+";");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.ceilingsqft=rs.getInt("ceilingsqft");
		this.wallsqft=rs.getInt("wallsqft");
		this.recnum=rs.getInt("recnum");
		this.nwinsqft=rs.getInt("nwinsqft");
		this.swinsqft=rs.getInt("swinsqft");
		this.ewinsqft=rs.getInt("ewinsqft");
		this.wwinsqft=rs.getInt("wwinsqft");
		this.nsldoorsqft=rs.getInt("nsldoorsqft");
		this.ssldoorsqft=rs.getInt("ssldoorsqft");
		this.esldoorsqft=rs.getInt("esldoorsqft");
		this.wsldoorsqft=rs.getInt("wsldoorsqft");
		this.doorsqft=rs.getInt("doorsqft");
		this.totglasssqft=rs.getInt("totglasssqft");
		this.nframewall=rs.getInt("nframewall");
		this.nmasawall=rs.getInt("nmasawall");
		this.nmasbwall=rs.getInt("nmasbwall");
		this.basfloorsqft=rs.getInt("basfloorsqft");
		this.slabnoperim=rs.getInt("slabnoperim");
		this.slabwperim=rs.getInt("slabwperim");
		this.flooruncond=rs.getInt("flooruncond");
		this.mechvent=rs.getInt("mechvent");
		this.numpeople=rs.getInt("numpeople");
		this.bldlength=rs.getInt("bldlength");
		this.bldwidth=rs.getInt("bldwidth");
		this.bldheight=rs.getInt("bldheight");
		
		this.custsitenum = rs.getString("custsitenum");
		this.sitenum = rs.getString("sitenum");
		this.ceiltype = rs.getString("ceiltype");
		this.walltype = rs.getString("walltype");
		this.nwintype = rs.getString("nwintype");
		this.swintype = rs.getString("swintype");
		this.ewintype = rs.getString("ewintype");
		this.wwintype = rs.getString("wwintype");
		this.nsldoortype = rs.getString("nsldoortype");
		this.ssldoortype = rs.getString("ssldoortype");
		this.esldoortype = rs.getString("esldoortype");
		this.wsldoortype = rs.getString("wsldoortype");
		this.doortype = rs.getString("doortype");
		this.nframewalltype = rs.getString("nframewalltype");
		this.nmasawalltype = rs.getString("nmasawalltype");
		this.nmasbwalltype = rs.getString("nmasbwalltype");
		this.slabnoperimtype = rs.getString("slabnoperimtype");
		this.slabwperimtype = rs.getString("slabwperimtype");
		this.flooruncondtype = rs.getString("flooruncondtype");
		this.loaddate=rs.getString("loaddate");
	}


	public static Vector getAllItems(Connection c, String custsite, String sitenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM heatloads where custsitenum='"+custsite+"' and sitenum='"+sitenum+"'");
		while(rs.next())
		{
			HeatLoad t = new HeatLoad(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

        	public static Vector getIndItems(Connection c, int recnum, int fake)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM heatloads where recnum="+recnum+"");
		while(rs.next())
		{
			
			HeatLoad t = new HeatLoad(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}
	
                
public static void UpdateItem(Connection con, String recnum, String CustSite, String SiteNum, String LoadDate, String ceilingsqft, String wallsqft, String nwinsqft, String swinsqft, String ewinsqft, String wwinsqft, String nsldoorsqft, String ssldoorsqft, String esldoorsqft, String wsldoorsqft, String doorsqft, String totglasssqft, String nframewall, String nmasawall, String nmasbwall, String basfloorsqft, String slabnoperim, String slabwperim, String flooruncond, String mechvent, String numpeople, String ceiltype, String walltype, String nwintype, String swintype, String ewintype, String wwintype, String nsldoortype, String ssldoortype, String esldoortype, String wsldoortype, String doortype, String nframewalltype, String nmasawalltype, String nmasbwalltype, String slabnoperimtype, String slabwperimtype, String flooruncondtype, String bldlength, String bldwidth, String bldheight)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update heatloads Set ceilingsqft='"+ceilingsqft+"', wallsqft='"+wallsqft+"', nwinsqft='"+nwinsqft+"', swinsqft='"+swinsqft+"', wwinsqft='"+wwinsqft+"', ewinsqft='"+ewinsqft+"', nsldoorsqft='"+nsldoorsqft+"', ssldoorsqft='"+ssldoorsqft+"', esldoorsqft='"+esldoorsqft+"', wsldoorsqft='"+wsldoorsqft+"', doorsqft='"+doorsqft+"', totglasssqft='"+totglasssqft+"', nframewall='"+nframewall+"', nmasawall='"+nmasawall+"', nmasbwall='"+nmasbwall+"', basfloorsqft='"+basfloorsqft+"', slabnoperim='"+slabnoperim+"', slabwperim='"+slabwperim+"', flooruncond='"+flooruncond+"', mechvent='"+mechvent+"', numpeople='"+numpeople+"', ceiltype='"+ceiltype+"', walltype='"+walltype+"', nwintype='"+nwintype+"', swintype='"+swintype+"', wwintype='"+wwintype+"', ewintype='"+ewintype+"', nsldoortype='"+nsldoortype+"', ssldoortype='"+ssldoortype+"', wsldoortype='"+wsldoortype+"', esldoortype='"+esldoortype+"', doortype='"+doortype+"', nframewalltype='"+nframewalltype+"', nmasawalltype='"+nmasbwalltype+"', slabnoperimtype='"+slabnoperimtype+"', slabwperimtype='"+slabwperimtype+"', flooruncondtype='"+flooruncondtype+"', bldlength='"+bldlength+"', bldwidth='"+bldwidth+"', bldheight='"+bldheight+"'  where recnum='"+recnum+"';");
      	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From heatloads Where recnum='" + x + "';");
	}
	
        public static void deleteCustItems(Connection con, String custsite, String sitenum)
                throws SQLException
        {
                
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From heatloads  Where custsitenum='" + custsite + "' and sitenum='"+sitenum+"';");
        }
	
	
public static void AddItem(Connection con, String CustSite, String SiteNum, String LoadDate, String ceilingsqft, String wallsqft, String nwinsqft, String swinsqft, String ewinsqft, String wwinsqft, String nsldoorsqft, String ssldoorsqft, String esldoorsqft, String wsldoorsqft, String doorsqft, String totglasssqft, String nframewall, String nmasawall, String nmasbwall, String basfloorsqft, String slabnoperim, String slabwperim, String flooruncond, String mechvent, String numpeople, String ceiltype, String walltype, String nwintype, String swintype, String ewintype, String wwintype, String nsldoortype, String ssldoortype, String esldoortype, String wsldoortype, String doortype, String nframewalltype, String nmasawalltype, String nmasbwalltype, String slabnoperimtype, String slabwperimtype, String flooruncondtype, String bldlength, String bldwidth, String bldheight)
                throws SQLException
		        {
	                Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO heatloads(custsitenum, sitenum, loaddate, ceilingsqft,wallsqft,nwinsqft,swinsqft,ewinsqft,wwinsqft,nsldoorsqft,ssldoorsqft,esldoorsqft,wsldoorsqft,doorsqft,totglasssqft,nframewall,nmasawall,nmasbwall,basfloorsqft,slabnoperim,slabwperim,flooruncond,mechvent,numpeople,ceiltype,walltype, nwintype,swintype,ewintype,   wwintype,nsldoortype,ssldoortype,esldoortype,wsldoortype,doortype,nframewalltype,nmasawalltype,nmasbwalltype,slabnoperimtype,slabwperimtype,flooruncondtype, bldlength, bldwidth, bldheight  ) Values ( '"+CustSite+"', '"+SiteNum+"', '"+LoadDate+"','"+ceilingsqft+"', '"+wallsqft+"', '"+nwinsqft+"', '"+swinsqft+"', '"+ewinsqft+"', '"+wwinsqft+"', '"+nsldoorsqft+"', '"+ssldoorsqft+"', '"+esldoorsqft+"', '"+wsldoorsqft+"', '"+doorsqft+"', '"+totglasssqft+"', '"+nframewall+"', '"+nmasawall+"', '"+nmasbwall+"', '"+basfloorsqft+"', '"+slabnoperim+"', '"+slabwperim+"', '"+flooruncond+"', '"+mechvent+"', '"+numpeople+"', '"+ceiltype+"', '"+walltype+"', '"+ nwintype+"', '"+swintype+"', '"+ewintype+"', '"+wwintype+"', '"+nsldoortype+"', '"+ssldoortype+"', '"+esldoortype+"', '"+wsldoortype+"', '"+doortype+"', '"+nframewalltype+"', '"+nmasawalltype+"', '"+nmasbwalltype+"', '"+slabnoperimtype+"', '"+slabwperimtype+"', '"+flooruncondtype+"', '"+bldlength+"', '"+bldwidth+"', '"+bldheight+"');");
		        }
			
		public int ceilingsqft() { return ceilingsqft; }
		public int wallsqft() { return wallsqft; }
		public int recnum() { return recnum; }
		public int nwinsqft() { return nwinsqft; }
		public int swinsqft() { return swinsqft; }
		public int ewinsqft() { return ewinsqft; }
		public int wwinsqft() { return wwinsqft; }
		public int nsldoorsqft() { return nsldoorsqft; }
		public int ssldoorsqft() { return ssldoorsqft; }
		public int esldoorsqft() { return esldoorsqft; }
		public int wsldoorsqft() { return wsldoorsqft; }
		public int doorsqft() { return doorsqft; }
		public int totglasssqft() { return totglasssqft; }
		public int nframewall() { return nframewall; }
		public int nmasawall() { return nmasawall; }
		public int nmasbwall() { return nmasbwall; }
		public int basfloorsqft() { return basfloorsqft; }
		public int slabnoperim() { return slabnoperim; }
		public int slabwperim() { return slabwperim; }
		public int flooruncond() { return flooruncond; }
		public int mechvent() { return mechvent; }
		public int numpeople() { return numpeople; }
		public int bldlength() { return bldlength; }
		public int bldwidth() { return bldwidth; }
		public int bldheight() { return bldheight; }
		
		public String  custsitenum() { return custsitenum; }
		public String sitenum() { return sitenum; }
		public String ceiltype() { return ceiltype; }
		public String walltype() { return walltype; }
		public String nwintype() { return nwintype; }
		public String swintype() { return swintype; }
		public String ewintype() { return ewintype; }
		public String wwintype() { return wwintype; }
		public String nsldoortype() { return nsldoortype; }
		public String ssldoortype() { return ssldoortype; }
		public String esldoortype() { return esldoortype; }
		public String wsldoortype() { return wsldoortype; }
		public String doortype() { return doortype; }
		public String nframewalltype() { return nframewalltype; }
		public String nmasawalltype() { return nmasawalltype; }
		public String nmasbwalltype() { return nmasbwalltype; }
		public String slabnoperimtype() { return slabnoperimtype; }
		public String slabwperimtype() { return slabwperimtype; }
		public String flooruncondtype() { return flooruncondtype; }
		public String loaddate() { return loaddate; }
			
}
