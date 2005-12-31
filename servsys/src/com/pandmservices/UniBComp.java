package com.pandmservices; 
import java.sql.*;
import java.lang.*;
import java.util.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;

public class UniBComp
{
	private String customerguid;
	private String customercode;
	private String customername;
	private String customeradd1;
        private String customeradd2;   
	private String customercity;
	private String customerstate;
	private String customerpostalcode;
	private String paymentterms;
	private String incomeaccount;
	private String companyphone;
	private String companyfax;
	private String customernotes;
	private String customerweb;

        public UniBComp (Connection c, String customerguid)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT customerguid, customercode, customername, customeradd1, customeradd2, customercity, customerstate, customerpostalcode, paymentterms, incomeaccount, companyphone, companyfax, customernotes, customerweb FROM buscompany WHERE customerguid='" + customerguid + "';");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + customerguid);
		}
		this.customerguid = rs.getString("customerguid");
		this.customercode = rs.getString("customercode");
		this.customername = rs.getString("customername");
		this.customeradd1 = rs.getString("customeradd1");
		this.customeradd2 = rs.getString("customeradd2");
		this.customercity = rs.getString("customercity");
		this.customerstate = rs.getString("customerstate");
		this.customerpostalcode = rs.getString("customerpostalcode");
		this.paymentterms = rs.getString("paymentterms");
		this.incomeaccount = rs.getString("incomeaccount");
		this.companyphone = rs.getString("companyphone");
		this.companyfax = rs.getString("companyfax");
		this.customernotes = rs.getString("customernotes");
		this.customerweb = rs.getString("customerweb");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT customerguid, customername  FROM buscompany ORDER BY customername;");
		while(rs.next())
		{
			
			UniBComp t = new UniBComp(c,rs.getString("customerguid"));
			V.addElement(t);
		}
		return V;
	}


	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From buscompany Where customerguid='" + d + "';");
	}
	  

	public static void UpdateItem(Connection con, String customerguid, String customercode, String customername, String customeradd1, String customeradd2, String customercity, String customerstate, String customerpostalcode, String paymentterms, String incomeaccount, String companyphone, String companyfax, String customernotes, String customerweb)
	
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update buscompany Set customername='" + customername +"',companyphone='"+companyphone+"',companyfax='"+companyfax+"',custtomeradd1='"+customeradd1 +"',customeradd2='"+customeradd2+"', customercity='"+customercity+"', customerstate='"+customerstate+"', customerpostalcode='"+customerpostalcode+"', paymentterms='"+paymentterms+"', incomeaccount='"+incomeaccount+"', customernotes='"+customernotes+"',customercode='"+customercode+"',customerweb='"+customerweb+"' Where customerguid='" + customerguid + "';");
      	}

	public static void addItem(Connection con, String customercode, String customername, String customeradd1, String customeradd2, String customercity, String customerstate, String customerpostalcode, String paymentterms, String incomeaccount, String companyphone, String companyfax, String customernotes, String customerweb)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();

			Calendar now = Calendar.getInstance();
                        int hour = now.get(Calendar.HOUR_OF_DAY);
			int second = now.get(Calendar.SECOND);
			int year = now.get(Calendar.YEAR);
			int minute = now.get(Calendar.MINUTE);
			int millisecond = now.get(Calendar.MILLISECOND);
		        String customerguid=UniMD5.doMakeMD5("c"+hour+minute+second+millisecond+year+UniCash.getRandomNumber(33));

			stmt.executeUpdate("INSERT INTO buscompany (customerguid, customername, companyphone, companyfax, customeradd1, customeradd2, customercity, customerstate, customerpostalcode, paymentterms, incomeaccount,customernotes,customercode,customerweb) Values ('"+ customerguid +"','" + customername +"','"+companyphone+"','"+companyfax+"','"+customeradd1 +"','"+customeradd2+"','"+customercity+"','"+customerstate+"','"+customerpostalcode+"','"+paymentterms+"','"+incomeaccount+"','"+customernotes+"','"+customercode+"','"+customerweb+"' )");

		        }

		public String getCustomerguid() { return customerguid;}
		public String getCustomercode() { return customercode;}
		public String getCustomername() { return customername;}
		public String getCustomeradd1() { return customeradd1;}
		public String getCustomeradd2() { return customeradd2;}
		public String getCustomercity() { return customercity;}
		public String getCustomerstate() { return customerstate;}
		public String getCustomerpostalcode() { return customerpostalcode;}
		public String getPaymentTerms() { return paymentterms;}
		public String getIncomeAccount() { return incomeaccount;}
		public String getCompanyPhone() { return companyphone;}
		public String getCompanyFax() { return companyfax;}
		public String getCustomerNotes() { return customernotes;}
		public String getCustomerWeb() { return customerweb;}

}
