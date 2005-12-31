package com.pandmservices; 
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class UniAcct
{
        private int id;
	private String atype;
	private String aguid;
        private String description="";     //default value to null
        private boolean done = false;      //default value to false
        private String accountname="";     //default value to null

        public UniAcct (Connection c, String aguid)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select gncaccount.accountname, gncaccount.accountguid, gncaccount.description, gncaccount.type from gncaccount where gncaccount.accountguid='"+aguid+"' Group By gncaccount.accountname, gncaccount.description, gncaccount.type, gncaccount.accountguid order by gncaccount.accountname;");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.accountname = rs.getString("accountname");
		this.description = rs.getString("description");
		this.atype=rs.getString("type");
		this.aguid=rs.getString("accountguid");
	}

        public String getAccountBalance(Connection c)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select count as id gncaccount.accountname, gncaccount.description, gncaccount.type, sum(gncentry.amountnum)/avg(gncentry.amountdenom) as balance from gncaccount where gncaccount.type='BANK' and gncentry.accountguid = gncaccount.accountguid GROUP By gncaccount.accountname, gncaccount.description, gncaccount.type;");

		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		String accountname = rs.getString("accountname");
		return accountname;
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT accountguid, accountname  FROM gncaccount ORDER BY accountname;");
		while(rs.next())
		{
			
			UniAcct t = new UniAcct(c,rs.getString("accountguid"));
			V.addElement(t);
		}
		return V;
	}

	public static int getMaxID(Connection c)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id FROM todo ORDER BY id;");
		int idlarge = 0;	
		while(rs.next())
		  {
			idlarge = rs.getInt("id");
		  }

		return idlarge;

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

        public int getId() { return id; }

        public String getDescription() { return description; }
	public String getAType() { return atype; }
	public String getAGuId() { return aguid; }
	public String getAccountName() { return accountname; }
        public boolean getDone() { return done; }


        public void setId(int id) { this.id = id; }
        public void setDescription(String desc) { this.description = desc; }
        public void setDone(boolean done) { this.done = done; }
}
