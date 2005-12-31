package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.security.*;

public class UniEntry
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
        private String accountguid="";     //default value to null

        public UniEntry (Connection c, String tranid)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select gnctransaction.transguid, gncaccount.accountname, gnctransaction.description, to_char(gnctransaction.date_posted,'YYYY-MM-DD') as date_posted, gnctransaction.num, gncaccount.type,gncentry.amountnum,  gncentry.amountdenom, gncaccount.accountguid from gncaccount where gncentry.entryguid='"+tranid+"' and gncentry.accountguid = gncaccount.accountguid and gnctransaction.transguid=gncentry.transguid GROUP By gncaccount.accountname, gncaccount.type, gncentry.amountnum, gncentry.amountdenom, gnctransaction.description, gnctransaction.num, gnctransaction.date_posted, gnctransaction.transguid, gncaccount.accountguid order by gnctransaction.date_posted DESC;");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + tranid);
		}
		this.transguid = rs.getString("transguid");
		this.transnum = rs.getString("num");
		this.date_posted = rs.getString("date_posted");
		this.amountnum = rs.getInt("amountnum");
		this.amountdenom = rs.getInt("amountdenom");
		this.description = rs.getString("description");
		this.accountname = rs.getString("accountname");
		this.accountguid = rs.getString("accountguid");
	}


	public static Vector getAllItems(Connection c, String actguid)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select gncentry.entryguid, gnctransaction.transguid from gncentry where gnctransaction.transguid='"+actguid+"' and gncentry.transguid=gnctransaction.transguid;");
		while(rs.next())
		{
			UniEntry t = new UniEntry(c,rs.getString("entryguid"));
			V.addElement(t);
		}
		return V;
	}

        public int getId() { return id; }
        public int getAmountNum() { return amountnum; }
        public int getAmountDenom() { return amountdenom; }

        public String getDescription() { return description; }
	public String getAType() { return atype; }
	public String getTransGuId() { return transguid; }
	public String getAccountGuId() { return accountguid; }
	public String getTDatePosted() { return date_posted; }
	public String getAccountName() { return accountname; }
	public String getTNum() { return transnum; }
        public boolean getDone() { return done; }


        public void setId(int id) { this.id = id; }
        public void setDescription(String desc) { this.description = desc; }
        public void setDone(boolean done) { this.done = done; }
}
