package com.pandmservices; 
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import com.pandmservices.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;

public class CustUnlock
{
	private String userlogin;
	private String custnum;
	private String sitenum;
	private String callslip;
	private String ndate;
	private String expiredate;
	private int recnum;

        public CustUnlock (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from locktable where recnum='"+recnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.userlogin=rs.getString("userlogin");
		this.custnum= rs.getString("custnum");
		this.sitenum=rs.getString("sitenum");
		this.callslip=rs.getString("callslip");
		this.ndate=rs.getString("ndate");
		this.expiredate=rs.getString("expiredate");
		this.recnum=rs.getInt("recnum");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM locktable;");
		while(rs.next())
		{
			CustUnlock t = new CustUnlock(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

		public static Vector getSingleItem(Connection c, String custsite, String sitenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM locktable where custnum='"+custsite+"' and sitenum='"+sitenum+"';");
		while(rs.next())
		{
			CustUnlock t = new CustUnlock(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	
	public static void deleteCustomerInfo(Connection con, String custsite, String sitenum, int crecnum2)
		throws SQLException, TodoException
	{	
		// Select all callslip - delete inventory and charges and completion codes from each.
		
	     Vector vc;
                vc = UniCallslip.getAllItems(con, crecnum2);
                for (int ic = 0 ; ic < vc.size(); ic++)
                {
                        UniCallslip tc = (UniCallslip) vc.elementAt(ic);
                        String callslip  = tc.getCallslip();
			InvUse.deleteAllItems(con, callslip);
			UniSvcCharges.deleteAllItems(con, callslip);
		}
		
                vc = UniInspection.getAllItems(con, crecnum2);
                for (int ii = 0 ; ii < vc.size(); ii++)
                {
                        UniInspection ti = (UniInspection) vc.elementAt(ii);
                        String callslip  = ti.getCallslip();
			InvUse.deleteAllItems(con, callslip);
			UniSvcCharges.deleteAllItems(con, callslip);
		}
		
		
	// Now find Callslips for this customer and delete.
	UniCallslip.deleteCustItems(con, crecnum2);
	
	// Now find Callslip Notes and Delete
	CallslipNotes.deleteAllCustSiteItems(con, custsite, sitenum);
	
	// Now find Inspection Notes and Delete.
		
	InspectNotes.deleteAllCustSiteItems(con, custsite, sitenum);
	
	// Now find equipment for this customer and delete.
	
	UniEquip.deleteCustItems(con, crecnum2);
	
	// Now find heatloads for this customer and delete
	
	HeatLoad.deleteCustItems(con, custsite, sitenum);
	
	// Now find inspections for this customer and delete
	UniInspection.deleteCustItems(con, crecnum2);
	
	// Now find worksheets for this customer and delete.
	
	//UniWorksheet.deleteAllCustItems(con, custsite, sitenum);
	UniWorksheet.deleteAllNoCustSite(con);
	

	// Now find service proposals for this customer and delete
	

                vc = ServQuotes.getAllItems(con, crecnum2);
                for (int ii = 0 ; ii < vc.size(); ii++)
                {
                        ServQuotes tsq = (ServQuotes) vc.elementAt(ii);
                        int quotenum  = tsq.getQuoteNum();
			ServQuotes.deleteItem(con, ""+quotenum+"");
		}
	
	// Now find sales proposals for this customer and delete
	
                vc = UniQuotes.getAllItems(con, crecnum2);
                for (int ii = 0 ; ii < vc.size(); ii++)
                {
                        UniQuotes tsq = (UniQuotes) vc.elementAt(ii);
                        int quotenum  = tsq.getQuoteNum();
			UniQuotes.deleteItem(con, ""+quotenum+"");
		}	
	
	
	// Now find Proposal compare forms and delete
	PackCompare.deleteAllItems(con, custsite, sitenum);
	
	// Now find customer forms and delete
                vc = CustFormList.getAllItems(con, custsite, sitenum);
                for (int ifl = 0 ; ifl < vc.size(); ifl++)
                {
                        CustFormList tf = (CustFormList) vc.elementAt(ifl);
                        int formnum  = tf.getFormNum();
			CustFormParts.DeleteItem(con, ""+formnum+"");
			CustFormList.DeleteItem(con,""+formnum+"");
			
		}
		
	
	// Now find site surveys and delete
	Survey.DeleteAllItem(con, crecnum2);
	//out.println("Delete Survey Records<br>");
	
	// Now find equipment check records and delete.
	UniCheckMe.deleteAllCustItems(con, crecnum2);
	//out.println("Delete Checkme Records<br>");
	
	// Now finally delete the customer
	UniCustomer.deleteItem(con, crecnum2);
		
	}

	
	
	public static Vector getAllLoginItems(Connection c, String userlogin)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM locktable where userlogin='"+userlogin+"';");
		while(rs.next())
		{
			
			CustUnlock t = new CustUnlock(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}
	

	public static Vector getLoginOpenItems(Connection c, String userlogin, String todaysdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM locktable where userlogin='"+userlogin+"' and expiredate >='"+todaysdate+"';");
		while(rs.next())
		{
			
			CustUnlock t = new CustUnlock(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}
	

	public static void AddItem(Connection con, String userlogin, String custnum, String sitenum, String callslip, String ndate, String expiredate)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("insert into locktable (userlogin,custnum,sitenum, callslip, ndate,expiredate) values ('"+userlogin+"','"+custnum+"','"+sitenum+"','"+callslip+"','"+ndate+"','"+expiredate+"');");
      	}

        public static void CustUnlockDownloadInfo (Connection con, Connection conu, String userlogin, String todaydate)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmtu = conu.createStatement();
		ResultSet rsu = stmtu.executeQuery("select * from locktable where userlogin='"+userlogin+"' and expiredate>='"+todaydate+"';");
		int result225a1 = stmt.executeUpdate("drop table if exists locktable;");
		int result225a2 = stmt.executeUpdate("create table locktable (recnum int(11) NOT NULL auto_increment,userlogin text, custnum text, sitenum text, callslip text, ndate date, expiredate date,PRIMARY KEY  (recnum), UNIQUE KEY recnum (recnum));");
		
		
	while (rsu.next())
		{
		userlogin=rsu.getString("userlogin");
		String custnum= rsu.getString("custnum");
		String sitenum=rsu.getString("sitenum");
		String callslip=rsu.getString("callslip");
		String ndate=rsu.getString("ndate");
		String expiredate=rsu.getString("expiredate");
		int recnum=rsu.getInt("recnum");
		stmt.executeUpdate("insert into locktable (userlogin,custnum,sitenum, callslip, ndate,expiredate) values ('"+userlogin+"','"+custnum+"','"+sitenum+"','"+callslip+"','"+ndate+"','"+expiredate+"');");
		}
		
	}

	
	
        public String getUserLogin() { return userlogin; }
        public String getCustNum() { return custnum; }
        public String getSiteNum() { return sitenum; }
        public String getCallslip() { return callslip; }
        public String getNDate() { return ndate; }
        public String getExpireDate() { return expiredate; }
	public int getRecNum() { return recnum; }
}
