package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;
import java.security.*;

public class UniBnkHist
{
        private String description="";     //default value to null
        private String aguid="";     //default value to null
        private String accountname="";     //default value to null
        private double balance;      //default value to false
    

        public UniBnkHist(Connection c, String idcode)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		String sqlstring ="select DISTINCT gncaccount.accountname, gncaccount.description, gncaccount.type, gncaccount.accountguid, sum(gncentry.amountnum)/avg(gncentry.amountdenom) as balance from gncaccount WHERE gncaccount.accountguid='"+idcode+"' and gncentry.accountguid = gncaccount.accountguid GROUP By gncaccount.accountname, gncaccount.description, gncaccount.type, gncaccount.accountguid;";
		ResultSet rs = stmt.executeQuery(sqlstring);
		if (!rs.next())
		{
			throw new TodoException("Record not found, idcode = " + idcode);
		}
		this.description = rs.getString("description");
		this.aguid = rs.getString("accountguid");
		this.accountname = rs.getString("accountname");
		this.balance = rs.getDouble("balance");

	}

        public UniBnkHist(Connection c, String idcode, String tyear)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		String sqlstring ="select DISTINCT gncaccount.accountname, gncaccount.description, gncaccount.type, gncaccount.accountguid, sum(gncentry.amountnum)/avg(gncentry.amountdenom) as balance from gncaccount WHERE gncaccount.accountguid='"+idcode+"' and gncentry.accountguid = gncaccount.accountguid and gnctransaction.transguid=gncentry.transguid and extract(YEAR from gnctransaction.date_posted)='"+tyear+"' GROUP By gncaccount.accountname, gncaccount.description, gncaccount.type, gncaccount.accountguid";
		ResultSet rs = stmt.executeQuery(sqlstring);
		if (!rs.next())
		{
			throw new TodoException("Record not found, idcode = " + idcode);
		}
		this.description = rs.getString("description");
		this.aguid = rs.getString("accountguid");
		this.accountname = rs.getString("accountname");
		this.balance = rs.getDouble("balance");

	}

	public static Vector getAllItems(Connection c, String trantype)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT DISTINCT accountguid, type, accountname FROM gncaccount where type='"+trantype+"' and gncaccount.accountguid=gncentry.accountguid ORDER BY accountname;");
		while(rs.next())
		{
			
			UniBnkHist t = new UniBnkHist(c,rs.getString("accountguid"));
			V.addElement(t);
		}
		return V;
	}
	
	public static Vector getAllItems(Connection c, String trantype, String tyear)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT DISTINCT accountguid, type, accountname FROM gncaccount where type='"+trantype+"' and gncaccount.accountguid=gncentry.accountguid and gnctransaction.transguid=gncentry.transguid and extract(YEAR from gnctransaction.date_posted)=extract(YEAR from CURRENT_TIMESTAMP) ORDER BY accountname");
		while(rs.next())
		{
			
			UniBnkHist t = new UniBnkHist(c,rs.getString("accountguid"),tyear);
			V.addElement(t);
		}
		return V;
	}

        public static double getIndBalance(Connection c, String idcode)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		String sqlstring ="select DISTINCT gncaccount.accountname, gncaccount.description, gncaccount.type, gncaccount.accountguid, sum(gncentry.amountnum)/avg(gncentry.amountdenom) as balance from gncaccount WHERE gncaccount.accountguid='"+idcode+"' and gncentry.accountguid = gncaccount.accountguid GROUP By gncaccount.accountname, gncaccount.description, gncaccount.type, gncaccount.accountguid;";
		ResultSet rs = stmt.executeQuery(sqlstring);
		if (!rs.next())
		{
			throw new TodoException("Record not found, idcode = " + idcode);
		}
		return  rs.getDouble("balance");

	}

        public String getDescription() { return description; }
        public String getAccountName() { return accountname; }
        public String getAccountGuId() { return aguid; }
        public double getAccountBalance() { return balance; }


}
