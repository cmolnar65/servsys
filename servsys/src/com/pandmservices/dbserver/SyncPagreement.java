package com.pandmservices.dbserver;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class SyncPagreement
{

       		 private int contnum =0; 
		private int servsync=0;
               private int eenum=0;
                private int ecustnum=0;
                private String brand=null;
                private String modelnum=null;
                private String serialnum=null;
                private String filter=null;
                private String enotes=null;
                private String type=null;
                private int enum1 =0;
                private int enum2 = 0;
                private int enum3 = 0;
                private int enum4 = 0;
                private int enum5 = 0;
                private int enum6 = 0;
                private int enum7 = 0;
                private int enum8 = 0;
                private int enum9 = 0;
                private int enum10 =0;
                private String aservice  = null;
                private String startdate = null;
                private String enddate = null;
                private int term = 0;
                private String cost = null;
                private String notes = null;
                private String agrdate = null;
                private int vperyear = 0;
		private String visit1=null;
		private String visit2=null;
		private String visit3=null;
		private String visit4=null;
		private String visit5=null;
		private String visit6=null;
		private String cname=null;
		private String address1=null;
		private String address2=null;
		private String city =null;
		private String state=null;
		private String zip=null;
		private String homephone=null;
		private String altphone=null;
		private String cust_notes=null;
		private String custsite=null;
		private String sitenum=null;
		private String techid=null;
		private String remotecrecnum=null;

        private int id;
        private int custnum=0;     //default value to null
    

        public SyncPagreement (Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmt3 = con.createStatement();
		Statement stmtu = conu.createStatement();

		//////////////////////////////////////////////
		// Check for any missing sitenum and custsite locally
		/////////////////////////////////////////////
		ResultSet rs = stmt.executeQuery("SELECT contnum, custnum  FROM pagreement where custsite is NULL or sitenum is NULL or (length(sitenum)<1 or length(custsite)<2) ORDER BY custnum;");
		while(rs.next())
		{
			this.id = rs.getInt("contnum");
			this.custnum = rs.getInt("custnum");
			
			//////////////////////////////////////////////
			// Get sitenum and custnum
			/////////////////////////////////////////////
			Vector ve;
			ve = UniCustomer.getIndItem(con, custnum);
			int counter=0;
			for (int ie = 0 ; ie < ve.size(); ie++)
			{
				UniCustomer te = (UniCustomer) ve.elementAt(ie);
				custsite=te.getCustSite();
				sitenum=te.getSiteNum();
			}
			//////////////////////////////////////////////
			// Update with sitenum and custnum set servsync=0
			/////////////////////////////////////////////
			
			if ((custsite!="")&&(custsite!=null)&&(sitenum!=null)&&(sitenum!=""))
			{
				stmt2.executeUpdate("Update pagreement Set custsite ='"+custsite+"', sitenum='"+sitenum+"', servsync='0' Where contnum=" + id + ";");	
			}
		}
		
		//////////////////////////////////////////////
		// get unsynced preventatives with custsite and sitenum
		/////////////////////////////////////////////
		
		ResultSet rs2 = stmt3.executeQuery("SELECT * FROM pagreement where (servsync=0 or servsync is NULL) and custsite is not NULL and sitenum is not NULL and length(sitenum)>=2 and length(custsite)>=2  ORDER BY custnum;");
		while(rs2.next())
		{
		this.enum1=rs2.getInt("enum1");
		this.enum2=rs2.getInt("enum2");
		this.enum3=rs2.getInt("enum3");
		this.enum4=rs2.getInt("enum4");
		this.enum5=rs2.getInt("enum5");
		this.enum6=rs2.getInt("enum6");
		this.enum7=rs2.getInt("enum7");
		this.enum8=rs2.getInt("enum8");
		this.enum9=rs2.getInt("enum9");
		this.enum10=rs2.getInt("enum10");
		this.aservice=rs2.getString("aservice");
		this.visit1=rs2.getString("visit1");
		this.visit2=rs2.getString("visit2");
		this.visit3=rs2.getString("visit3");
		this.visit4=rs2.getString("visit4");
		this.visit5=rs2.getString("visit5");
		this.visit6=rs2.getString("visit6");
		this.startdate=rs2.getString("startdate");
		this.enddate=rs2.getString("enddate");
		this.term=rs2.getInt("term");
		this.cost=rs2.getString("cost");
		this.notes=rs2.getString("notes");
		this.agrdate=rs2.getString("agrdate");
		this.vperyear=rs2.getInt("vperyear");
		this.servsync=rs2.getInt("servsync");
		this.custnum=rs2.getInt("custnum");
		this.custsite=rs2.getString("custsite");
		this.sitenum=rs2.getString("sitenum");
		this.techid=rs2.getString("techid");
		this.contnum=rs2.getInt("contnum");
		//////////////////////////////////////////////
		// Have local record
		/////////////////////////////////////////////
		
		//////////////////////////////////////////////
		// Now check for server exact match
		/////////////////////////////////////////////
		String renum1="0";
		String renum2="0";
		String renum3="0";
		String renum4="0";
		String renum5="0";
		String renum6="0";
		String renum7="0";
		String renum8="0";
		String renum9="0";
		String renum10="0";

		if (enum1>0) {
		ResultSet lse1 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum1+"';");
		while (lse1.next()) {
		String tserialnum=lse1.getString("serialnum");
		String modelnum=lse1.getString("modelnum");
		String tbrand=lse1.getString("brand");
		ResultSet rse1 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse1.next()) {
		renum1=rse1.getString("enum");
		}}
		}	

		if (enum2>0) {
		ResultSet lse2 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum2+"';");
		while (lse2.next()) {
		String tserialnum=lse2.getString("serialnum");
		String modelnum=lse2.getString("modelnum");
		String tbrand=lse2.getString("brand");
		ResultSet rse2 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse2.next()) {
		renum2=rse2.getString("enum");
		}}
		}	

		if (enum3>0) {
		ResultSet lse3 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum3+"';");
		while (lse3.next()) {
		String tserialnum=lse3.getString("serialnum");
		String modelnum=lse3.getString("modelnum");
		String tbrand=lse3.getString("brand");
		ResultSet rse3 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse3.next()) {
		renum3=rse3.getString("enum");
		}}
		}	

		if (enum4>0) {
		ResultSet lse4 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum4+"';");
		while (lse4.next()) {
		String tserialnum=lse4.getString("serialnum");
		String modelnum=lse4.getString("modelnum");
		String tbrand=lse4.getString("brand");
		ResultSet rse4 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse4.next()) {
		renum4=rse4.getString("enum");
		}}
		}	
			

		if (enum5>0) {
		ResultSet lse5 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum5+"';");
		while (lse5.next()) {
		String tserialnum=lse5.getString("serialnum");
		String modelnum=lse5.getString("modelnum");
		String tbrand=lse5.getString("brand");
		ResultSet rse5 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse5.next()) {
		renum5=rse5.getString("enum");
		}}
		}	

		if (enum6>0) {
		ResultSet lse6 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum6+"';");
		while (lse6.next()) {
		String tserialnum=lse6.getString("serialnum");
		String modelnum=lse6.getString("modelnum");
		String tbrand=lse6.getString("brand");
		ResultSet rse6 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse6.next()) {
		renum6=rse6.getString("enum");
		}}
		}	

		if (enum7>0) {
		ResultSet lse7 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum7+"';");
		while (lse7.next()) {
		String tserialnum=lse7.getString("serialnum");
		String modelnum=lse7.getString("modelnum");
		String tbrand=lse7.getString("brand");
		ResultSet rse7 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse7.next()) {
		renum7=rse7.getString("enum");
		}}
		}	

		if (enum8>0) {
		ResultSet lse8 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum8+"';");
		while (lse8.next()) {
		String tserialnum=lse8.getString("serialnum");
		String modelnum=lse8.getString("modelnum");
		String tbrand=lse8.getString("brand");
		ResultSet rse8 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse8.next()) {
		renum8=rse8.getString("enum");
		}}
		}	

		if (enum9>0) {
		ResultSet lse9 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum9+"';");
		while (lse9.next()) {
		String tserialnum=lse9.getString("serialnum");
		String modelnum=lse9.getString("modelnum");
		String tbrand=lse9.getString("brand");
		ResultSet rse9 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse9.next()) {
		renum9=rse9.getString("enum");
		}}
		}	

		if (enum10>0) {
		ResultSet lse10 = stmt.executeQuery("SELECT brand, modelnum, serialnum from equipment where enum='"+enum10+"';");
		while (lse10.next()) {
		String tserialnum=lse10.getString("serialnum");
		String modelnum=lse10.getString("modelnum");
		String tbrand=lse10.getString("brand");
		ResultSet rse10 = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+tbrand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' ORDER BY enum;");
		while (rse10.next()) {
		renum10=rse10.getString("enum");
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
				//////////////////////////////////////////////
				// Add to server
				/////////////////////////////////////////////
			//System.out.println("INSERT INTO pagreement (custnum, enum1, enum2, enum3, enum4, enum5, enum6, enum7, enum8, enum9, enum10, aservice, startdate, enddate, term, cost, notes, agrdate, vperyear,visit1, visit2, visit3, visit4, visit5, visit6, servsync, sitenum, custsite, techid) Values ('" + remotecrecnum + "','" +renum1  + "','"+renum2 +"','"+renum3 +"','"+renum4 +"','"+renum5 +"','"+renum6 +"','"+renum7 +"','"+renum8 +"','"+renum9 +"','"+renum10 +"','"+aservice +"','"+startdate +"','"+enddate +"','"+term +"','"+cost +"','"+notes +"','"+agrdate +"','"+vperyear +"','"+visit1+"','"+visit2+"','"+visit3+"','"+visit4+"','"+visit5+"','"+visit6+"', '"+servsync+"', '"+ sitenum +"' ,'"+ custsite+"', '"+techid+"' )\n\n");
			stmtu.executeUpdate("INSERT INTO pagreement (custnum, enum1, enum2, enum3, enum4, enum5, enum6, enum7, enum8, enum9, enum10, aservice, startdate, enddate, term, cost, notes, agrdate, vperyear,visit1, visit2, visit3, visit4, visit5, visit6, servsync, sitenum, custsite, techid) Values ('" + remotecrecnum + "','" +renum1  + "','"+renum2 +"','"+renum3 +"','"+renum4 +"','"+renum5 +"','"+renum6 +"','"+renum7 +"','"+renum8 +"','"+renum9 +"','"+renum10 +"','"+aservice +"','"+startdate +"','"+enddate +"','"+term +"','"+cost +"','"+notes +"','"+agrdate +"','"+vperyear +"','"+visit1+"','"+visit2+"','"+visit3+"','"+visit4+"','"+visit5+"','"+visit6+"', '"+servsync+"', '"+ sitenum +"' ,'"+ custsite+"', '"+techid+"' )");

	//////////////////////////////////////////////
	// Set ServSync Flag
	/////////////////////////////////////////////

			}

		
	stmt2.executeUpdate("Update pagreement set servsync='1' where contnum='"+this.contnum+"';");
			
	}
	}
}
