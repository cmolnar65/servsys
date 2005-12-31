package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;

public class UniBills
{
        private String billguid;
	private String duedate;
	private String paydate;
	private String payee;
	private String memo;
	private double amount;

        private String description="";     //default value to null
        private boolean done = false;      //default value to false
    

        public UniBills (Connection c, String billguid)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT billguid,to_char(paydate,'YYYY-MM-DD') as fduedate,to_char(paydate,'YYYY-MM-DD') as fpaydate, payee, memo, amountnum, amountdenom  FROM tblbills WHERE billguid='" + billguid + "';");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + billguid);
		}
		this.billguid = rs.getString("billguid");
		this.memo = rs.getString("memo");
		this.payee = rs.getString("payee");
		double amntnum = rs.getInt("amountnum");
		double amntden = rs.getInt("amountdenom");
		this.paydate = rs.getString("fpaydate");
		this.duedate = rs.getString("fduedate");
		this.amount = amntnum/amntden;
	}



	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT billguid, duedate FROM tblbills ORDER BY duedate;");
		while(rs.next())
		{
			
			UniBills t = new UniBills(c,rs.getString("billguid"));
			V.addElement(t);
		}
		return V;
	}


	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From tblbills Where billguid='" + d + "';");
	}
	  

	public static void changeItem(Connection con, int id)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update todo Set Done=true Where ID=" + id + ";");
      	}

	public static void UpdateItem(Connection con, String duedate, String paydate, String payee, double damount, String memo, String inputid)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		double entryamount=damount*100;
      		stmt.executeUpdate("Update tblbills Set duedate='" +duedate+ "', paydate='" +paydate+ "', payee='" +payee+ "', memo='" +memo+ "', amountnum=" +entryamount+ " Where billguid='" + inputid + "';");
      	}


	public static void addBillItem(Connection con, String duedate, String paydate, String payee, double damount, String memo)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();

			Calendar now = Calendar.getInstance();
                        int hour = now.get(Calendar.HOUR_OF_DAY);
			int second = now.get(Calendar.SECOND);
			int year = now.get(Calendar.YEAR);
			int minute = now.get(Calendar.MINUTE);
			int millisecond = now.get(Calendar.MILLISECOND);
		        String billguid=UniMD5.doMakeMD5("b"+hour+minute+second+millisecond+year+UniCash.getRandomNumber(damount*2));

			double entryamount = damount * 100;

			stmt.executeUpdate("INSERT INTO tblbills (billguid, duedate, paydate, payee, memo, amountnum, amountdenom) Values ('" + billguid + "','"+duedate+"','" + paydate +"','"+payee+"','"+memo+"',"+entryamount+",100)");

		        }


        //public boolean getDone() { return done; }


		public String getBillguid() { return billguid;}
		public String getMemo() { return memo; }
		public String getPayee() { return payee; }
		public String getPaydate() { return paydate; }
		public String getDuedate() { return duedate; }
		public double getAmount() { return amount; }


//        public void setId(int id) { this.id = id; }
        public void setDescription(String desc) { this.description = desc; }
        public void setDone(boolean done) { this.done = done; }
}
