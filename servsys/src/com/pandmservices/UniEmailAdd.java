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


public class UniEmailAdd
{
	private String lusername;
	private String tech_email;
	private String ins_email;
	private String svc_email;
	private String prop_email;
	private String time_email;
	private String stock_email;
	private String stock_mail_cat;
	private String smtpserver;
	private String smtpuser;
	private String smtppassword;

        public UniEmailAdd (Connection c, String tech_email)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from configemail where tech_email='"+tech_email+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + tech_email);
		}
		this.tech_email =rs.getString("tech_email");
		this.time_email = rs.getString("time_email");
		this.svc_email=rs.getString("svc_email");
		this.ins_email=rs.getString("ins_email");
		this.prop_email=rs.getString("prop_email");
		this.smtpserver=rs.getString("smtp_server");
		this.stock_email=rs.getString("stock_email");
		this.stock_mail_cat=rs.getString("semail_cat");
		this.smtpuser=rs.getString("smtp_user");
		this.smtppassword=rs.getString("smtp_password");
		this.lusername=rs.getString("username");
	}


	public static Vector getAllItems(Connection c, String lusername)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM configemail where username='"+lusername+"';");
		while(rs.next())
		{
			
			UniEmailAdd t = new UniEmailAdd(c,rs.getString("tech_email"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM configemail");
		while(rs.next())
		{
			
			UniEmailAdd t = new UniEmailAdd(c,rs.getString("tech_email"));
			V.addElement(t);
		}
		return V;
	}


	public static void AddItem(Connection con, String tech_email, String time_email, String svc_email, String ins_email, String smtpserver, String prop_email, String stock_email, String stock_mail_cat, String smtpuser, String smtppassword, String lusername)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("insert into configemail (tech_email, time_email, svc_email, ins_email, smtp_server, prop_email, stock_email, semail_cat, smtp_user, smtp_password, username) values ('" +tech_email+ "' ,'"+time_email+"' ,'"+svc_email+"','"+ins_email+"','"+smtpserver+"', '"+prop_email+"', '"+stock_email+"', '"+stock_mail_cat+"',  '"+smtpuser+"', '"+smtppassword+"', '"+lusername+"');");
      	}


	public static void UpdateItem(Connection con, String tech_email, String time_email, String svc_email, String ins_email, String smtpserver, String prop_email, String stock_email, String stock_mail_cat, String smtpuser, String smtppassword, String lusername)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update configemail Set tech_email='" +tech_email+ "' ,time_email='"+time_email+"' , svc_email='"+svc_email+"', ins_email='"+ins_email+"', smtp_server='"+smtpserver+"', prop_email='"+prop_email+"', stock_email='"+stock_email+"', semail_cat ='"+stock_mail_cat+"',  smtp_user='"+smtpuser+"',  smtp_password='"+smtppassword+"' where username='"+lusername+"';");
      	}


        public String getUserName() { return lusername; }
        public String getTechEmail() { return tech_email; }
        public String getSmtpServer() { return smtpserver; }
        public String getSmtpUser() { return smtpuser; }
        public String getSmtpPassword() { return smtppassword; }
        public String getTimeEmail() { return time_email; }
        public String getSvcEmail() { return svc_email; }
        public String getInsEmail() { return ins_email; }
	public String getPropEmail() { return prop_email; }
	public String getStockEmail() { return stock_email; }
	public String getStockMailCat() { return stock_mail_cat; }
}
