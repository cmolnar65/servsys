package com.pandmservices; 
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class PackCompare
{
        private int recnum;
	private int best1;
	private int best2;
	private int best3;
	private int best4;
	private int best5;
	private int best6;
	private int best7;
	private int best8;
	private int better1;
	private int better2;
	private int better3;
	private int better4;
	private int better5;
	private int better6;
	private int better7;
	private int better8;
	private int good1;
	private int good2;
	private int good3;
	private int good4;
	private int good5;
	private int good6;
	private int good7;
	private int good8;

	private String desc1;
	private String desc2;
	private String desc3;
	private String description;
	private String custsite;
	private String sitenum;
	private String username;
	private String packdate;
	
        public PackCompare(Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from packcompare where recnum='"+recnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recnum=rs.getInt("recnum");
		this.best1=rs.getInt("best1");
		this.best2=rs.getInt("best2");
		this.best3=rs.getInt("best3");
		this.best4=rs.getInt("best4");
		this.best5=rs.getInt("best5");
		this.best6=rs.getInt("best6");
		this.best7=rs.getInt("best7");
		this.best8=rs.getInt("best8");
		this.better1=rs.getInt("better1");
		this.better2=rs.getInt("better2");
		this.better3=rs.getInt("better3");
		this.better4=rs.getInt("better4");
		this.better5=rs.getInt("better5");
		this.better6=rs.getInt("better6");
		this.better7=rs.getInt("better7");
		this.better8=rs.getInt("better8");
		this.good1=rs.getInt("good1");
		this.good2=rs.getInt("good2");
		this.good3=rs.getInt("good3");
		this.good4=rs.getInt("good4");
		this.good5=rs.getInt("good5");
		this.good6=rs.getInt("good6");
		this.good7=rs.getInt("good7");
		this.good8=rs.getInt("good8");
		this.description = rs.getString("description");
		this.custsite = rs.getString("custsitenum");
		this.sitenum = rs.getString("sitenum");
		this.packdate = rs.getString("packdate");
		this.username = rs.getString("username");
		this.desc1=rs.getString("desc1");
		this.desc2=rs.getString("desc2");
		this.desc3=rs.getString("desc3");
	}


	public static Vector getAllItems(Connection c, String custsite, String sitenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM packcompare where custsitenum='"+custsite+"' and sitenum='"+sitenum+"' order by packdate, description");
		while(rs.next())
		{
			PackCompare t = new PackCompare(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

        	public static Vector getIndItems(Connection c, int recnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM packcompare where recnum= '"+recnum+"'");
		while(rs.next())
		{
			
			PackCompare t = new PackCompare(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}
                
public static void UpdateItem(Connection con, String recnum, String description, String best1, String best2, String best3, String best4, String best5, String better1, String better2, String better3, String better4, String better5, String good1, String good2, String good3, String good4, String good5, String username, String custsitenum, String sitenum, String packdate, String desc1, String desc2, String desc3, String best6, String best7, String best8, String better6, String better7, String better8, String good6, String good7, String good8)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update packcompare Set description='"+description+"',best1='"+best1+"',best2='"+best2+"',best3='"+best3+"',best4='"+best4+"',best5='"+best5+"',better1='"+better1+"',better2='"+better2+"',better3='"+better3+"',better4='"+better4+"',better5='"+better5+"',good1='"+good1+"',good2='"+good2+"',good3='"+good3+"',good4='"+good4+"',good5='"+good5+"',best6='"+best6+"',best7='"+best7+"',best8='"+best8+"',better6='"+better6+"',better7='"+better7+"',better8='"+better8+"',good6='"+good6+"',good7='"+good7+"',good8='"+good8+"',username='"+username+"',custsitenum='"+custsitenum+"',sitenum='"+sitenum+"',packdate='"+packdate+"', desc1='"+desc1+"', desc2='"+desc2+"', desc3='"+desc3+"' where recnum='"+recnum+"';");
      	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From packcompare Where recnum='" + x + "';");
	}

public static void AddItem(Connection con, String description, String best1, String best2, String best3, String best4, String best5, String better1, String better2, String better3, String better4, String better5, String good1, String good2, String good3, String good4, String good5, String username, String custsitenum, String sitenum, String packdate, String desc1, String desc2, String desc3, String best6, String best7, String best8, String better6, String better7, String better8, String good6, String good7, String good8)
                throws SQLException
		        {
	                Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO packcompare (description, best1, best2, best3, best4, best5, better1, better2, better3, better4, better5, good1, good2, good3, good4, good5, best6, best7, best8, better6, better7, better8, good6, good7, good8, username,custsitenum, sitenum, packdate, desc1, desc2, desc3 ) Values ('" +description + "','" +best1 + "','" +best2 + "','" +best3 + "','" +best4 + "','" +best5 + "','" +better1 + "','" +better2 + "','" +better3 + "','" +better4 + "','" +better5 + "','" +good1 + "','" +good2 + "','" +good3 + "','" +good4 + "','" +good5 + "','"+best6+"','"+best7+"','"+best8+"','"+better6+"','"+better7+"','"+better8+"','"+good6+"','"+good7+"','"+good8+"','" +username + "','" +custsitenum + "','" +sitenum + "','" +packdate + "','" +desc1 + "','" +desc2 + "','" +desc3 + "')");
		        }
			
		public int getRecNum() { return recnum; }
		public int getBest1() { return best1; }
		public int getBest2() { return best2; }
		public int getBest3() { return best3; }
		public int getBest4() { return best4; }
		public int getBest5() { return best5; }
		public int getBest6() { return best6; }
		public int getBest7() { return best7; }
		public int getBest8() { return best8; }
		public int getBetter1() { return better1; }
		public int getBetter2() { return better2; }
		public int getBetter3() { return better3; }
		public int getBetter4() { return better4; }
		public int getBetter5() { return better5; }
		public int getBetter6() { return better6; }
		public int getBetter7() { return better7; }
		public int getBetter8() { return better8; }		
		public int getGood1() { return good1; }
		public int getGood2() { return good2; }
		public int getGood3() { return good3; }
		public int getGood4() { return good4; }
		public int getGood5() { return good5; }
		public int getGood6() { return good6; }
		public int getGood7() { return good7; }
		public int getGood8() { return good8; }		

		public String getDescription() { return description; }
		public String getCustSiteNum() { return custsite; }
		public String getSiteNum() { return sitenum; }
		public String getPackDate() { return packdate; }
		public String getUserName() { return username; }
		public String getDesc1() { return desc1; }
		public String getDesc2() { return desc2; }
		public String getDesc3() { return desc3; }
	
}
