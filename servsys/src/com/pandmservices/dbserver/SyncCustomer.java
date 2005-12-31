package com.pandmservices.dbserver; 
import com.pandmservices.core.*;
import com.pandmservices.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class SyncCustomer
{
        private int id;
	private String caddress1;
	private String caddress2;
	private String custname;
	private String cusnum;
	private String ccity;
	private String cstate;
	private String czip;
	private String chphone;
	private String caltphone;
	private String cust_notes;
	private String aguid;
        private String description="";     //default value to null
        private boolean done = false;      //default value to false
        private String accountname="";     //default value to null
	private String cemail;
	private String custsite;
	private String sitenum;
	private String custtype;
	private String custnotes;
	private int servsync;

        public SyncCustomer (Connection con, Connection conu)

		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
	ResultSet rs1 = stmt.executeQuery("select * from customers where (servsync=0 or servsync is NULL) and custsite IS not NULL and length(custsite)>1;");	
		while (rs1.next())
		{
		this.cusnum=rs1.getString("custnum");
		this.custname = rs1.getString("cname");
		this.caddress1 = rs1.getString("address1");
		this.caddress2 = rs1.getString("address2");
		this.ccity=rs1.getString("city");
		this.cstate=rs1.getString("state");
		this.czip=rs1.getString("zip");
		this.chphone=rs1.getString("homephone");
		this.caltphone=rs1.getString("altphone");
		this.cemail=rs1.getString("cemail");
		this.custsite=rs1.getString("custsite");
		this.sitenum=rs1.getString("sitenum");
		this.custnotes=rs1.getString("cust_notes");
		this.custtype=rs1.getString("custtype");
		this.servsync=rs1.getInt("servsync");

// NOW CHECK SERVER FOR CUSTOMER
		Vector vc;
                vc = UniCustomer.getCustNumSite(conu,custsite,sitenum);
                	if (vc.size()>0) {
                				for (int ic = 0 ; ic < vc.size(); ic++)
                				{
// CUSTOMER FOUND
                        			UniCustomer tc = (UniCustomer) vc.elementAt(ic);
                				}
                         		} else {
// CUSTOMER NOT FOUND - SO ADD
				String tcustnotes="";
				if (custnotes!=null) {
				tcustnotes = custnotes.replaceAll("'","''");
						}
				String tcustname="";
				if (custname!=null) {
				tcustname = custname.replaceAll("'","''");
						}
				String tcaddress1="";
				if (caddress1!=null) {
				tcaddress1 = caddress1.replaceAll("'","''");
						}
				String tcaddress2="";
				if (caddress2!=null) {
				tcaddress2 = caddress2.replaceAll("'","''");
						}
			    UniCustomer.addCustomer(conu, tcustname, tcaddress1, tcaddress2, ccity, cstate, czip, chphone, caltphone, tcustnotes, cemail, custsite, sitenum, custtype);
                			}
// NOW TURN ON SYNCED FLAG FOR ORIGIONAL CUSTOMER
      		stmt2.executeUpdate("Update customers Set servsync=2 Where custnum='" + cusnum + "';");
		}
	}
}
