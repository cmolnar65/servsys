package com.pandmservices.dbserver;
import java.sql.*;
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class SyncCustWorksheets
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
        private int wsrec=0;     //default value to null
        private int crec=0;     //default value to null
        private String wsdate=null;     //default value to null
        private String wsdesc=null;     //default value to null
        private String wsmult=null;
        private String subcost=null;

 

        public SyncCustWorksheets (Connection con, Connection conu, String custsite, String sitenum)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmtu = conu.createStatement();


		ResultSet rs2 = stmt.executeQuery("SELECT * FROM worksheet WHERE custsite='"+custsite+"' and sitenum='"+sitenum+"';");
		while (rs2.next())
		{
                this.wsrec = rs2.getInt("wsrec");
                this.crec = rs2.getInt("crec");
                this.wsmult = rs2.getString("wsmult");
                this.wsdate = rs2.getString("wsdate");
                this.wsdesc = rs2.getString("wsdesc");
		this.custsite=rs2.getString("custsite");
		this.sitenum=rs2.getString("sitenum");
		this.techid=rs2.getString("techid");
		this.servsync=rs2.getInt("servsync");
  

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
String keyColumn[] = {"wsrec"}; 
String ID=null;

			String acolumns= new String("quotenum");
			///String tqnotes = qnotes.replaceAll("'","''");
System.out.println("\nINSERT INTO worksheet (crec, wsdate, wsdesc, wsmult) Values ('"+remotecrecnum+"','" +wsdate+ "','" +wsdesc+ "', '"+wsmult+"')\n");
int recordset=stmtu.executeUpdate("INSERT INTO worksheet (crec, wsdate, wsdesc, wsmult) Values ('"+remotecrecnum+"','" +wsdate+ "','" +wsdesc+ "', '"+wsmult+"')",keyColumn);
		ResultSet rsKeyColumn = stmtu.getGeneratedKeys(); 
		if(rsKeyColumn.next()) { // ADD THIS LINE! IMPORTANT! 
     		ID = rsKeyColumn.getString(1); 
						}

        //////////////////////////////////////////////
        // Now  - Do the wsitems
        /////////////////////////////////////////////
               Vector vp;
                vp = UniWsItems.getAllItems(con,wsrec);
                for (int j = 0 ; j < vp.size(); j++)
                {
                UniWsItems tp = (UniWsItems) vp.elementAt(j);

                wsrec = tp.getWsRec();
                int itemrec = tp.getItemRec();
                String item = tp.getWsItem();
                String keycode = tp.getWsKeyCode();
                int quantity = tp.getWsQuant();
                double cost = tp.getWsCost();
                int laborhours = tp.getWsLaborHours();
                int shophours = tp.getWsShopHours();
                double laborcost = tp.getWsLaborCost();
		String scost=""+cost+"";
		String slaborcost=""+laborcost+"";
			String titem = item.replaceAll("'","''");

	UniWsItems.AddItem(conu,Integer.parseInt(ID),titem,keycode,quantity,scost,laborhours,slaborcost,shophours,subcost);

		}

	
        //////////////////////////////////////////////
        // Set ServSync Flag
        /////////////////////////////////////////////

        stmt2.executeUpdate("Update worksheet set servsync='2' where wsrec='"+wsrec+"';");
        //stmt2.executeUpdate("Update quote_parts set servsync='1' where quotenum='"+quotenum+"';");

		}
        stmt2.executeUpdate("Update worksheet set servsync='2' where wsrec='"+wsrec+"';");
		

	}
	}

}
