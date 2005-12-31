package com.pandmservices.dbserver;
import java.sql.*;
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class SyncWorksheets
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

 

        public SyncWorksheets (Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		Statement stmtu = conu.createStatement();

               //////////////////////////////////////////////
                // Check for any missing sitenum and custsite locally
                /////////////////////////////////////////////
                ResultSet rs = stmt.executeQuery("SELECT wsrec, crec  FROM worksheet where custsite is NULL or sitenum is NULL or length(sitenum)<1 or length(custsite)<2 ORDER BY crec;");
                while(rs.next())
                {
                        this.id = rs.getInt("wsrec");
                        this.custnum = rs.getInt("crec");

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
                                stmt2.executeUpdate("Update worksheet Set custsite ='"+custsite+"', sitenum='"+sitenum+"', servsync='0' Where wsrec=" + id + ";");
                        }
                }

		ResultSet rs2 = stmt.executeQuery("SELECT * FROM worksheet WHERE (servsync=0 or servsync is NULL) and custsite is not NULL and sitenum is not NULL and length(sitenum)>=2 and length(custsite)>=2  ORDER BY crec;");
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

	UniWsItems.AddItem(conu,Integer.parseInt(ID),titem,keycode,quantity,scost,laborhours,slaborcost,shophours);

		}

	
        //////////////////////////////////////////////
        // Set ServSync Flag
        /////////////////////////////////////////////

        stmt2.executeUpdate("Update worksheet set servsync='1' where wsrec='"+wsrec+"';");
        //stmt2.executeUpdate("Update quote_parts set servsync='1' where quotenum='"+quotenum+"';");

		}
        stmt2.executeUpdate("Update worksheet set servsync='1' where wsrec='"+wsrec+"';");
		

	}
	}

}
