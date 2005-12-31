package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniPagreement
{

       		 private int contnum =0; 
		private int servsync=0;
               private int eenum=0;
                private int ecustnum=0;
                private String brand=null;
                private String modelnum=null;
                private String serialnum=null;
                private String filter=null;
                private String enotes=null;
                private String type=null;
                private int enum1 =0;
                private int enum2 = 0;
                private int enum3 = 0;
                private int enum4 = 0;
                private int enum5 = 0;
                private int enum6 = 0;
                private int enum7 = 0;
                private int enum8 = 0;
                private int enum9 = 0;
                private int enum10 =0;
                private String aservice  = null;
                private String startdate = null;
                private String enddate = null;
                private int term = 0;
                private String cost = null;
                private String notes = null;
                private String agrdate = null;
                private int vperyear = 0;
		private String visit1=null;
		private String visit2=null;
		private String visit3=null;
		private String visit4=null;
		private String visit5=null;
		private String visit6=null;
		private String cname=null;
		private String address1=null;
		private String address2=null;
		private String city =null;
		private String state=null;
		private String zip=null;
		private String homephone=null;
		private String altphone=null;
		private String cust_notes=null;
		private String custsite=null;
		private String sitenum=null;
		private String techid=null;

        private int id;
        private int custnum=0;     //default value to null
    

        public UniPagreement (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM pagreement WHERE contnum=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.enum1=rs.getInt("enum1");
		this.enum2=rs.getInt("enum2");
		this.enum3=rs.getInt("enum3");
		this.enum4=rs.getInt("enum4");
		this.enum5=rs.getInt("enum5");
		this.enum6=rs.getInt("enum6");
		this.enum7=rs.getInt("enum7");
		this.enum8=rs.getInt("enum8");
		this.enum9=rs.getInt("enum9");
		this.enum10=rs.getInt("enum10");
		this.aservice=rs.getString("aservice");
		this.visit1=rs.getString("visit1");
		this.visit2=rs.getString("visit2");
		this.visit3=rs.getString("visit3");
		this.visit4=rs.getString("visit4");
		this.visit5=rs.getString("visit5");
		this.visit6=rs.getString("visit6");
		this.startdate=rs.getString("startdate");
		this.enddate=rs.getString("enddate");
		this.term=rs.getInt("term");
		this.cost=rs.getString("cost");
		this.notes=rs.getString("notes");
		this.agrdate=rs.getString("agrdate");
		this.vperyear=rs.getInt("vperyear");
		this.servsync=rs.getInt("servsync");
		this.custnum=rs.getInt("custnum");
		this.custsite=rs.getString("custsite");
		this.sitenum=rs.getString("sitenum");
		this.techid=rs.getString("techid");
	}


	public static Vector getAllItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT contnum FROM pagreement where custnum='"+custnum+"' ORDER BY contnum;");
		while(rs.next())
		{
			
			UniPagreement t = new UniPagreement(c,rs.getInt("enum"));
			V.addElement(t);
		}
		return V;
	}


        public static Vector getUnSyncedItems(Connection c)
                throws SQLException, TodoException
        {
                Vector V = new Vector();
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT contnum FROM pagreement where servsync=0 or servsync is NULL ORDER BY contnum;");
                while(rs.next())
                {
                        UniPagreement t = new UniPagreement(c,rs.getInt("contnum"));
                        V.addElement(t);
                }
                return V;
        }



	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From equipment Where enum=" + x + ";");
	}
	
	public static void UpdateServSyncOn(Connection con, int contnum)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Updage pagreement set servsync='1' where contnum='"+contnum+"';");
	}
	  

	public static void UpdateItem(Connection con, int contnum, int custnum, String enum1,String enum2,String enum3,String enum4,String enum5,String enum6,String enum7,String enum8,String enum9,String enum10, String aservice, String startdate, String enddate, int term, String cost, String notes, String agrdate, int vperyear, String visit1, String visit2, String visit3, String visit4, String visit5, String visit6)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update pagreement Set custnum='" +custnum+ "',enum1 = '"+enum1 +"',enum2 = '"+enum2 +"',enum3 = '"+enum3 +"',enum4 = '"+enum4 +"',enum5 = '"+enum5 +"',enum6 = '"+enum6 +"',enum7 = '"+enum7 +"',enum8 = '"+enum8 +"',enum9 = '"+enum9 +"',enum10 = '"+enum10 +"',aservice = '"+aservice +"',startdate = '"+startdate +"',enddate = '"+enddate +"',term = '"+term +"',cost = '"+cost +"',agrdate = '"+agrdate +"',vperyear = '"+vperyear +"', visit1='"+visit1+"', visit2='"+visit2+"', visit3='"+visit3+"', visit4='"+visit4+"', visit5='"+visit5+"', visit6='"+visit6+"' Where contnum=" + contnum + ";");
      	}

public static void AddItem(Connection con, int custnum, String enum1,String enum2,String enum3,String enum4,String enum5,String enum6,String enum7,String enum8,String enum9,String enum10, String aservice, String startdate, String enddate, int term, String cost, String notes, String agrdate, int vperyear, String visit1, String visit2, String visit3, String visit4, String visit5, String visit6, int servsync , String custsite, String sitenum, String techid)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO pagreement (custnum, enum1, enum2, enum3, enum4, enum5, enum6, enum7, enum8, enum9, enum10, aservice, startdate, enddate, term, cost, notes, agrdate, vperyear,visit1, visit2, visit3, visit4, visit5, visit6, servsync) Values ('" + custnum + "','" +enum1  + "','"+enum2 +"','"+enum3 +"','"+enum4 +"','"+enum5 +"','"+enum6 +"','"+enum7 +"','"+enum8 +"','"+enum9 +"','"+enum10 +"','"+aservice +"','"+startdate +"','"+enddate +"','"+term +"','"+cost +"','"+notes +"','"+agrdate +"','"+vperyear +"','"+visit1+"','"+visit2+"','"+visit3+"','"+visit4+"','"+visit5+"','"+visit6+"', '"+servsync+"')");
		        }
        public int getContNum() { return contnum; }
        public int getCustnum() { return custnum ; }
		public int getEnum1() { return enum1;}
		public int getEnum2() { return enum2;}
		public int getEnum3() { return enum3;}
		public int getEnum4() { return enum4;}
		public int getEnum5() { return enum5;}
		public int getEnum6() { return enum6;}
		public int getEnum7() { return enum7;}
		public int getEnum8() { return enum8;}
		public int getEnum9() { return enum9;}
		public int getEnum10() { return enum10;}
		public String getAservice() { return aservice;}
		public String getVisit1() { return visit1;}
		public String getVisit2() { return visit2;}
		public String getVisit3() { return visit3;}
		public String getVisit4() { return visit4;}
		public String getVisit5() { return visit5;}
		public String getVisit6() { return visit6;}
		public String getStartdate() { return startdate;}
		public String getEnddate() { return enddate;}
		public int getTerm() { return term;}
		public String getCost() { return cost;}
		public String getNotes() { return notes;}
		public String getAgrdate() { return agrdate;}
		public int getVperyear() { return vperyear;}
		public int getServsync() { return servsync; }
		public String getTechId() { return techid;}
		public String getCustSite() { return custsite;}
		public String getSiteNum() { return sitenum;}

}
