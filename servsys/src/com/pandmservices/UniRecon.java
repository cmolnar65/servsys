package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class UniRecon
{
        private int id;
        private int amountnum;
        private int amountdenom;
	private String transnum="";
	private String atype="";
	private String transguid="";
	private String date_posted="";
        private String description="";     //default value to null
        private boolean done = false;      //default value to false
        private String accountname="";     //default value to null
    

        public UniRecon (Connection c, String tranid, String accountguid)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select gnctransaction.transguid, gncaccount.accountname, gnctransaction.description, to_char(gnctransaction.date_posted,'YYYY-MM-DD') as date_posted, gnctransaction.num, gncaccount.type,gncentry.amountnum,  gncentry.amountdenom  from gncaccount where gncaccount.accountguid='"+accountguid+"' and gnctransaction.transguid='"+tranid+"' and gncentry.accountguid=gncaccount.accountguid and gnctransaction.transguid=gncentry.transguid GROUP By gncaccount.accountname, gncaccount.type, gncentry.amountnum, gncentry.amountdenom, gnctransaction.description, gnctransaction.num, gnctransaction.date_posted, gnctransaction.transguid order by gnctransaction.date_posted DESC;");

	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.transguid = rs.getString("transguid");
		this.transnum = rs.getString("num");
		this.date_posted = rs.getString("date_posted");
		this.amountnum = rs.getInt("amountnum");
		this.amountdenom = rs.getInt("amountdenom");
		this.description = rs.getString("description");
	}


	public static Vector getAllItems(Connection c, String actguid, String cleared)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select gncentry.transguid, gnctransaction.date_posted where gncentry.reconciled='"+cleared+"' and gncentry.accountguid='"+actguid+"' and gncentry.transguid=gnctransaction.transguid ORDER BY gnctransaction.date_posted DESC;");
		while(rs.next())
		{
			UniTrans t = new UniTrans(c,rs.getString("transguid"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getSelectItems(Connection c, String actguid, int lcount)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select gncentry.transguid, gnctransaction.date_posted where gncentry.accountguid='"+actguid+"' and gncentry.transguid=gnctransaction.transguid ORDER BY gnctransaction.date_posted DESC limit "+lcount+";");
		while(rs.next())
		{
			UniTrans t = new UniTrans(c,rs.getString("transguid"));
			V.addElement(t);
		}
		return V;
	}

	public static String getAccountID(Connection c, String TAName)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT accountguid FROM gncaccount where accountname='"+TAName+"';");
		String accountguid = null;	
		while(rs.next())
		  {
			accountguid = rs.getString("accountguid");
		  }

		return accountguid;

	}


	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From Todo Where ID=" + x + ";");
	}
	  

	public static void changeItem(Connection con, int id)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update todo Set Done=true Where ID=" + id + ";");
      	}

	public static void UpdateItem(Connection con, int id, String desc)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update todo Set description='" +desc+ "' Where ID=" + id + ";");
      	}

public static void addAccntItem(Connection con, String desc, String name, String atype)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Calendar now = Calendar.getInstance();
                        int hour = now.get(Calendar.HOUR_OF_DAY);
                        int second = now.get(Calendar.SECOND);
                        int year = now.get(Calendar.YEAR);
                        int minute = now.get(Calendar.MINUTE);
                        int millisecond = now.get(Calendar.MILLISECOND);
			String pauid = "00000000000000000000000000000000";
			String acode = "";
			String anotes = "";
			String acommodity = "ISO4217::USD";
			String auid=UniMD5.doMakeMD5("a"+hour+minute+second+millisecond+year+UniCash.getRandomNumber(millisecond*2));
	                Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO gncaccount (accountguid, parentguid, accountname, accountcode, description, notes, type, commodity, version) Values ('" + auid + "','" +pauid+ "','"+ name + "','"+ acode + "','"+ desc + "','" + anotes + "','"+ atype + "','"+ acommodity +"', 1)");
		        }




		public static void addBankTrans(Connection con, String transguid, String TranPdate, String TranNum, String desc, String AccountIdString, String TranMemo, double transtot, String TAName)
                throws SQLException, TodoException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			// This one adds the transaction to gnctransaction table.
	                Statement stmt = con.createStatement();
			
	              stmt.executeUpdate("INSERT INTO gnctransaction (transguid, date_entered, date_posted, num, description, currency, version) Values ('" + transguid + "',CURRENT_TIMESTAMP,'" + TranPdate +"','"+TranNum+"','"+desc+"','ISO4217::USD',4)");
		       

			// Now do a select to get auid for the account name we passed in.

			String tauid = getAccountID(con, TAName);	
 
			// Now we need to call the addTransEntry routine for the gncentry table first subtract from account
			
			double entryamount = transtot *100; 		
	
			addTransEntry(con, transguid, AccountIdString, TranMemo, entryamount);
			
			// Now we need to call the addTransEntry routine for the gncentry table now add to  account

			entryamount = 0 - transtot *100;

			addTransEntry(con, transguid, tauid, TranMemo, entryamount);

			

			}

		public static void addTransEntry(Connection con, String transguid, String tauid, String TranMemo, double entryamount)
                throws SQLException, TodoException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			// This one adds the Entry to gncentry table.
			Calendar now = Calendar.getInstance();
			int hour = now.get(Calendar.HOUR_OF_DAY);
			int second = now.get(Calendar.SECOND);
			int year = now.get(Calendar.YEAR);
			int minute = now.get(Calendar.MINUTE);
			int millisecond = now.get(Calendar.MILLISECOND);
			
		
	                Statement stmt = con.createStatement();
			String entryid=UniMD5.doMakeMD5("w"+hour+minute+second+millisecond+year+UniCash.getRandomNumber(entryamount*2));
			
	              stmt.executeUpdate("INSERT INTO gncentry (transguid, accountguid, entryguid, memo, amountnum, amountdenom, valuenum, valuedenom) Values ('" + transguid + "','" + tauid +"','"+ entryid +"','"+TranMemo+"',"+entryamount+",100,"+entryamount+",100);");

			
			}		       

        public int getId() { return id; }
        public int getAmountNum() { return amountnum; }
        public int getAmountDenom() { return amountdenom; }

        public String getDescription() { return description; }
	public String getAType() { return atype; }
	public String getTransGuId() { return transguid; }
	public String getTDatePosted() { return date_posted; }
	public String getAccountName() { return accountname; }
	public String getTNum() { return transnum; }
        public boolean getDone() { return done; }


        public void setId(int id) { this.id = id; }
        public void setDescription(String desc) { this.description = desc; }
        public void setDone(boolean done) { this.done = done; }
}
