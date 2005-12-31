package com.pandmservices.dbserver;
import java.sql.*;
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class SyncSingleCallslip
{
        private int crecnum;
        private int custnum=0;     //default value to null
	private int equip1=0;
	private int equip2=0;
	private int equip3=0;
	private int equip4=0;
	private int followup=0;
	private int servsync=0;
	private String remotecrecnum=null;
   	private String  callslip=null; 
   	private String  cdate=null; 
   	private String  reason=null; 
   	private String  services=null; 
   	private String  recommendations=null; 
   	private String  rscheduled=null; 
   	private String  charges=null; 
   	private String  collected=null; 
   	private String  notes=null; 
	private String custsite=null;
	private String sitenum=null;
	private String crectype=null;
	private String techid=null;

        public SyncSingleCallslip (Connection con, Connection conu, String crecnum)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt99 = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmtu = conu.createStatement();
		Statement stmtu2 = conu.createStatement();
		ResultSet rs99 = stmt99.executeQuery("SELECT *  FROM callslip where servsync=0 or servsync is NULL and crecnum='"+crecnum+"' and custsite is not NULL and sitenum is not NULL ORDER BY crecnum;");
		while(rs99.next())
		{
		this.crecnum = rs99.getInt("crecnum");
		this.custnum = rs99.getInt("custnum");
		this.equip1 = rs99.getInt("equip1");
		this.equip2 = rs99.getInt("equip2");
		this.equip3 = rs99.getInt("equip3");
		this.equip4 = rs99.getInt("equip4");
		this.followup = rs99.getInt("followup");
		this.callslip = rs99.getString("callslip");
		this.cdate = rs99.getString("cdate");
		this.reason = rs99.getString("reason");
		this.services = rs99.getString("services");
		this.recommendations = rs99.getString("recommendations");
		this.rscheduled = rs99.getString("rscheduled");
		this.charges = rs99.getString("charges");
		this.collected = rs99.getString("collected");
		this.collected = "0.00";
		this.notes = rs99.getString("notes");
		this.crectype = rs99.getString("crectype");
		this.techid=rs99.getString("techid");
		this.custsite=rs99.getString("custsite");
		this.sitenum=rs99.getString("sitenum");
		this.servsync=rs99.getInt("servsync");
		
		//////////////////////////////////////////////
		// Now check for server exact match
		/////////////////////////////////////////////
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
			vc = UniCustomer.getCustNumSite(conu,custsite,sitenum);
			if (vc.size()>0) {
				for (int ic = 0 ; ic < vc.size(); ic++)
				{
					UniCustomer tc = (UniCustomer) vc.elementAt(ic);
					remotecrecnum = tc.getCusNum();
				}
String tservices="";
if (services!=null) {
tservices = services.replaceAll("'","''");
}
		
String trecommendations="";
if (recommendations!=null) {
trecommendations = recommendations.replaceAll("'","''");
}
				//////////////////////////////////////////////
				// Add to server
	                System.out.println("INSERT INTO callslip (custnum, callslip, cdate, equip1, equip2, equip3, equip4,reason, services, recommendations, rscheduled, charges, collected, notes, followup, custsite, sitenum, crectype, techid, servsync) Values ('" + remotecrecnum + "','" + callslip + "','"+cdate+"','"+renum1+"','"+renum2+"','"+renum3+"','"+renum4+"','"+reason+"','"+tservices+"','"+trecommendations+"','"+rscheduled+"','"+charges+"','"+collected+"', '"+notes+"','"+followup+"', '"+custsite+"','"+sitenum+"', '"+crectype+"','"+techid+"', 0)");
	                stmtu2.executeUpdate("INSERT INTO callslip (custnum, callslip, cdate, equip1, equip2, equip3, equip4,reason, services, recommendations, rscheduled, charges, collected, notes, followup, custsite, sitenum, crectype, techid, servsync) Values ('" + remotecrecnum + "','" + callslip + "','"+cdate+"','"+renum1+"','"+renum2+"','"+renum3+"','"+renum4+"','"+reason+"','"+tservices+"','"+trecommendations+"','"+rscheduled+"','"+charges+"','"+collected+"', '"+notes+"','"+followup+"', '"+custsite+"','"+sitenum+"', '"+crectype+"','"+techid+"', 0)");
			}
			stmt.executeUpdate("update callslip set servsync=2 where crecnum="+crecnum+";");
		}
	

	}

}
