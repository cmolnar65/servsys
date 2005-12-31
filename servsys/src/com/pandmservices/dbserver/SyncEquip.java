package com.pandmservices.dbserver;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.lang.Integer;
import java.util.Vector;

public class SyncEquip
{
        private int id;
        private int custnum=0;     //default value to null
        private String brand=null;     //default value to null
        private String modelnum=null;     //default value to null
        private String serialnum=null;     //default value to null
        private String filter=null;     //default value to null
        private String notes=null;     //default value to null
        private String etype=null;     //default value to null
	private String cseer=null;
	private String btuout=null;
	private String custsite=null;
	private String sitenum=null;
	private String remotecrecnum=null;
	private int servsync=0;
    

        public SyncEquip (Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmtu = conu.createStatement();
//////////////////////////////////////////////
// Check for any missing sitenum and custsite locally
/////////////////////////////////////////////
		ResultSet rs = stmt.executeQuery("SELECT enum, custnum  FROM equipment where custsite is NULL and sitenum is NULL or (length(custsite)<1 or length(sitenum)<1) ORDER BY custnum;");
                while(rs.next())
                {
		this.id = rs.getInt("enum");
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
		 //System.out.println("emum:"+id+"     custsite: "+custsite+"    sitenum: "+sitenum+"\n");
		 stmt2.executeUpdate("Update equipment Set custsite ='"+custsite+"', sitenum='"+sitenum+"', servsync='0' Where enum=" + id + ";");	
			}
	}

//////////////////////////////////////////////
// get unsynced equipment with custsite and sitenum
/////////////////////////////////////////////

		rs = stmt.executeQuery("SELECT * FROM equipment where servsync=0 or servsync is NULL and custsite is not NULL and sitenum is not NULL and length(custsite)>1 and length(sitenum)>1 ORDER BY custnum;");
		while(rs.next())
		{

		this.id = rs.getInt("enum");
		this.custnum = rs.getInt("custnum");
		this.brand = rs.getString("brand");
		this.modelnum = rs.getString("modelnum");
		this.serialnum = rs.getString("serialnum");
		this.filter = rs.getString("filter");
		this.notes = rs.getString("notes");
		this.etype = rs.getString("etype");
		this.cseer = rs.getString("cseer");
		this.btuout = rs.getString("btuout");
		this.custsite=rs.getString("custsite");
		this.sitenum=rs.getString("sitenum");
		this.servsync=rs.getInt("servsync");

//////////////////////////////////////////////
// Have local record
/////////////////////////////////////////////

//////////////////////////////////////////////
// Now check for server exact match
/////////////////////////////////////////////
String tserialnum="";
if (serialnum!=null) {                                
	tserialnum = serialnum.replaceAll("'","''");
                    }

		ResultSet rsu = stmtu.executeQuery("SELECT enum  FROM equipment where brand='"+brand+"' and modelnum like '"+modelnum+"' and serialnum like '"+tserialnum+"' and notes like '"+notes+"' and etype='"+etype+"' ORDER BY enum;");
		if (!rsu.first()) {
//////////////////////////////////////////////
// Not on server - find customer number on server
/////////////////////////////////////////////

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
				String tmodelnum="";
				if (modelnum!=null) {
				tmodelnum = modelnum.replaceAll("'","''");
						}
				String ttserialnum="";
				if (serialnum!=null) {
				ttserialnum = serialnum.replaceAll("'","''");
						}
		UniEquip.AddItem(conu,Integer.parseInt(remotecrecnum),brand,tmodelnum,ttserialnum, filter, notes, etype, cseer, btuout, custsite, sitenum, 2);
		
		 System.out.println("remotecustnum: "+ remotecrecnum +"      custsite: "+custsite+"    sitenum: "+sitenum+"\n");
//////////////////////////////////////////////
// Set ServSync Flag
/////////////////////////////////////////////
}
		}
		stmt2.executeUpdate("update equipment set servsync=1 Where enum=" +id+ ";");
		}
	}

}
