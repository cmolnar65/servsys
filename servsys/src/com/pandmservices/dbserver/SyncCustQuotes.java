package com.pandmservices.dbserver;
import java.sql.*;
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class SyncCustQuotes
{
        private int id;
        private int quotenum=0;;     //default value to null
        private int crecnum=0;;     //default value to null
        private String qdate=null;     //default value to null
        private String qdescription=null;     //default value to null
        private String qpayterms=null;     //default value to null
        private String qnotes=null;     //default value to null
        private String qdisc=null;     //default value to null
        private String qadditionalserv=null;     //default value to null
        private String qstatus=null;     //default value to null
	private String solddate=null;
	private String jobnum=null;
	private String antstart=null;
	private String custsite=null;
	private String sitenum=null;
	private String techid=null;
	private String remotecrecnum=null;
	private int custnum=0;
	private int servsync;
	private int combineopts=0;
    

        public SyncCustQuotes (Connection con, Connection conu, String custsite, String sitenum)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmtu = conu.createStatement();


		ResultSet rs2 = stmt.executeQuery("SELECT * FROM quotes WHERE custsite='"+custsite+"' and sitenum='"+sitenum+"';");
		while (rs2.next())
		{
		this.quotenum = rs2.getInt("quotenum");
		this.crecnum = rs2.getInt("crecnum");
		this.qdate = rs2.getString("qdate");
		this.qdescription = rs2.getString("qdescription");
		this.qpayterms = rs2.getString("qpayterms");
		this.qnotes = rs2.getString("qnotes");
		this.qdisc = rs2.getString("qdisc");
		this.qadditionalserv = rs2.getString("qadditionalserv");
		this.qstatus = rs2.getString("status");
		this.solddate = rs2.getString("solddate");
		this.jobnum = rs2.getString("jobnum");
		this.antstart = rs2.getString("antstart");
		this.custsite=rs2.getString("custsite");
		this.sitenum=rs2.getString("sitenum");
		this.techid=rs2.getString("techid");
		this.servsync=rs2.getInt("servsync");
		this.combineopts=rs2.getInt("combineopts");


                                //////////////////////////////////////////////
                                // get customer rec from server
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
String keyColumn[] = {"quotenum"}; 
String ID=null;

			String acolumns= new String("quotenum");
			String tqnotes = qnotes.replaceAll("'","''");
	       int recordset = stmtu.executeUpdate("INSERT INTO quotes (crecnum, qdate, qdescription, qpayterms, qnotes, qdisc, qadditionalserv, status, solddate, antstart, custsite, sitenum, techid, servsync,combineopts, jobnum) Values ('"+remotecrecnum+"','" +qdate+ "','" +qdescription+ "','"+qpayterms+"','"+tqnotes+"', '"+qdisc+"','"+qadditionalserv+"', '"+qstatus+"', '"+solddate+"', '"+antstart+"', '"+custsite +"','"+sitenum +"','"+techid +"', '"+servsync+"','"+combineopts+"','"+jobnum+"')",keyColumn);

		ResultSet rsKeyColumn = stmtu.getGeneratedKeys(); 
		if(rsKeyColumn.next()) { // ADD THIS LINE! IMPORTANT! 
     		ID = rsKeyColumn.getString(1); 
						}

        //////////////////////////////////////////////
        // Now  - Do the Quote Parts
        /////////////////////////////////////////////
	Vector vp;
 	vp = UniQuoteParts.getAllItems(con,quotenum);
	for (int j = 0 ; j < vp.size(); j++)
                {
                UniQuoteParts tp = (UniQuoteParts) vp.elementAt(j);
                String itemname=tp.getItemName();
                String mannum=tp.getManNum();
                int itemquant=tp.getItemQuant();
                String investment=tp.getInvestment();
		UniQuoteParts.AddItem(conu, Integer.parseInt(ID), itemname, mannum, itemquant, Double.parseDouble(investment), custsite, sitenum, techid, 0);
		}
	
        //////////////////////////////////////////////
        // Set ServSync Flag
        /////////////////////////////////////////////

        stmt2.executeUpdate("Update quote_parts set servsync='2' where quotenum='"+quotenum+"';");
		}
		
        stmt2.executeUpdate("Update quotes set servsync='2' where quotenum='"+quotenum+"';");

	}
	}

}
