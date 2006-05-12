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


public class UniCompConfig
{
	private String image;
	private String imagewidth;
	private String imagehight;
	private String coname;
	private String cologo;
	private String coaddress;
	private String cophone;
	private String useletterhead;
	private String yearenddate;
	private String enabcustomer;
	private String reportemail;
	private String laptopretentiontime;
	private String nocustonlaptop;

        public UniCompConfig (Connection c, String image)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from configcompany where image='"+image+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + image);
		}
		this.image=rs.getString("image");
		this.imagewidth= rs.getString("imagewidth");
		this.imagehight=rs.getString("imagehight");
		this.coname=rs.getString("coname");
		this.cologo=rs.getString("cologo");
		this.coaddress=rs.getString("coaddress");
		this.cophone=rs.getString("cophone");
		this.useletterhead=rs.getString("useletterhead");
		this.yearenddate=rs.getString("yearenddate");
		this.enabcustomer=rs.getString("enabcustomer");
		this.reportemail=rs.getString("reportemail");
		this.laptopretentiontime=rs.getString("laptopretentiontime");
		this.nocustonlaptop=rs.getString("nocustonlaptop");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM configcompany");
		while(rs.next())
		{
			
			UniCompConfig t = new UniCompConfig(c,rs.getString("image"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, String image, String imagewidth, String imagehight, String coname, String cologo, String coaddress, String cophone, String useletterhead, String enabcustomer, String reportemail, String yearenddate, String laptopretentiontime, String nocustonlaptop)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update configcompany Set image='" +image+ "' ,imagehight='"+imagehight+"' , imagewidth='"+imagewidth+"', coname='"+coname+"', cologo='"+cologo+"',coaddress='"+coaddress+"',cophone='"+cophone+"', useletterhead='"+useletterhead+"', enabcustomer='"+enabcustomer+"', reportemail='"+reportemail+"', yearenddate='"+yearenddate+"', laptopretentiontime='"+laptopretentiontime+"', nocustonlaptop='"+nocustonlaptop+"';");
      	}


	public static void AddItem(Connection con, String image, String imagewidth, String imagehight, String coname, String cologo, String coaddress, String cophone, String useletterhead, String enabcustomer, String reportemail, String yearenddate)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS configcompany;");
		stmt.executeUpdate("create table configcompany (image text, imagewidth text, imagehight text, coname text, cologo text, coaddress text, cophone text, useletterhead text not null, yearenddate date default '2005-09-31', enabcustomer text not null, reportemail text not null);");
      		stmt.executeUpdate("insert into configcompany (image,imagehight, imagewidth, coname, cologo,coaddress,cophone, useletterhead, enabcustomer, reportemail, yearenddate) values ('"+image+"','"+imagehight+"','"+imagewidth+"','"+coname+"','"+cologo+"','"+coaddress+"','"+cophone+"','"+useletterhead+"', '"+enabcustomer+"', '"+reportemail+"', '"+yearenddate+"');");
      	}


        public String getImage() { return image; }
        public String getImageWidth() { return imagewidth; }
        public String getImageHight() { return imagehight; }
        public String getCoName() { return coname; }
        public String getCoLogo() { return cologo; }
        public String getCoAddress() { return coaddress; }
        public String getCoPhone() { return cophone; }
        public String getUseLetterHead() { return useletterhead; }
        public String getYearEndDate() { return yearenddate; }
	public String getEnabCustomer() { return enabcustomer; }
	public String getReportEmail() { return reportemail; }
	public String getLaptopRetentionTime() { return laptopretentiontime; }
	public String getNoCustOnLaptop() { return nocustonlaptop; }
	
}
