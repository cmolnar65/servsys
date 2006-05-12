package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniCallslip
{
        private int crecnum;
        private int custnum=0;     //default value to null
	private int equip1=0;
	private int equip2=0;
	private int equip3=0;
	private int equip4=0;
	private int followup=0;
	private int servsync=0;
   	private String  callslip=null; 
   	private String  cdate=null; 
   	private String  reason=null; 
   	private String  services=null; 
   	private String  recommendations=null; 
   	private String  rscheduled=null; 
   	private String  charges=null; 
   	private String  collected=null; 
   	private String  notes=null; 
	private String custsite=null;
	private String sitenum=null;
	private String crectype=null;
	private String techid=null;
	private String parts=null;

        public UniCallslip (Connection c, int crecnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM callslip WHERE crecnum=" + crecnum+ ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + crecnum);
		}
		this.crecnum = rs.getInt("crecnum");
		this.custnum = rs.getInt("custnum");
		this.equip1 = rs.getInt("equip1");
		this.equip2 = rs.getInt("equip2");
		this.equip3 = rs.getInt("equip3");
		this.equip4 = rs.getInt("equip4");
		this.followup = rs.getInt("followup");
		this.callslip = rs.getString("callslip");
		this.cdate = rs.getString("cdate");
		this.reason = rs.getString("reason");
		this.services = rs.getString("services");
		this.recommendations = rs.getString("recommendations");
		this.rscheduled = rs.getString("rscheduled");
		this.charges = rs.getString("charges");
		this.collected = rs.getString("collected");
		this.collected = "0.00";
		this.notes = rs.getString("notes");
		this.crectype = rs.getString("crectype");
		this.techid=rs.getString("techid");
		this.custsite=rs.getString("custsite");
		this.sitenum=rs.getString("sitenum");
		this.servsync=rs.getInt("servsync");
		this.parts=rs.getString("parts");
	}

	public static Vector getAllItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM callslip where custnum='"+custnum+"' ORDER BY cdate;");
		while(rs.next())
		{
			UniCallslip t = new UniCallslip(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c, String custsite, String sitenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM callslip where custsite='"+custsite+"' and sitenum='"+sitenum+"' ORDER BY cdate;");
		while(rs.next())
		{
			UniCallslip t = new UniCallslip(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

		public static Vector getFollowupItems(Connection c, String custsite, String sitenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM callslip where custsite='"+custsite+"' and sitenum='"+sitenum+"' and followup=1 ORDER BY cdate;");
		while(rs.next())
		{
			UniCallslip t = new UniCallslip(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	
        public static void deleteItem(Connection con, String d)
                throws SQLException
        {
                int x = Integer.parseInt(d);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From callslip  Where crecnum=" + x + ";");
        }

        public static void deleteCustItems(Connection con, int custnum)
                throws SQLException
        {
                
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From callslip  Where custnum=" + custnum + ";");
        }
	
	public static Vector getIncludedDateItems(Connection c, String startdate, String enddate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM callslip where cdate>='"+startdate+"' and cdate<='"+enddate+"' ORDER BY crecnum;");
		while(rs.next())
		{
			UniCallslip t = new UniCallslip(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getUnSyncedItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM callslip where servsync=0 or servsync is NULL and custsite is not NULL and sitenum is not NULL ORDER BY crecnum;");
		while(rs.next())
		{
			UniCallslip t = new UniCallslip(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static void getServSync(Connection c, int crecnum)
		throws SQLException, TodoException
	{	
		Statement stmt = c.createStatement();
		stmt.executeUpdate("update callslip set servsync='1' where crecnum='"+crecnum+"';");
	}

	public static Vector getAllDateItems(Connection c, String listdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM callslip where cdate='"+listdate+"' ORDER BY crecnum;");
		while(rs.next())
		{
			UniCallslip t = new UniCallslip(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getIndItem(Connection c, String custnum, String techid, String cdate, String callslip )
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum, cdate  FROM callslip where custnum='"+custnum+"' and techid='"+techid+"' and cdate='"+cdate+"' and callslip='"+callslip+"' ORDER BY cdate;");
		while(rs.next())
		{
			
			UniCallslip t = new UniCallslip(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndItems(Connection c, int custnum, int crecnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM callslip where crecnum='"+crecnum+"' ORDER BY cdate;");
		while(rs.next())
		{
			
			UniCallslip t = new UniCallslip(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getCallSlipFollowup(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM callslip where followup=1 order by cdate, crecnum;");
		while(rs.next())
		{
			
			UniCallslip t = new UniCallslip(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static void UpdateItem(Connection con, int crecnum, int custnum, String callslip, String cdate, String equip1, String equip2, String equip3, String equip4, String reason, String services, String recommendations, String rscheduled, String charges, String collected, String notes, int followup, String custsite, String sitenum, String crectype, String techid, String parts)
		throws SQLException
	{
		Statement stmt = con.createStatement();
			String tservices = services.replaceAll("'","''");
			String trecommendations = recommendations.replaceAll("'","''");
      		stmt.executeUpdate("Update callslip Set custnum='" +custnum+ "', callslip='"+callslip +"',cdate ='"+cdate +"',equip1 ='"+equip1 +"',equip2 ='"+equip2 +"',equip3 ='"+equip3 +"',equip4 ='"+equip4 +"', reason ='"+reason +"',services ='"+tservices +"',recommendations ='"+trecommendations +"',rscheduled ='"+rscheduled +"',charges ='"+charges +"',collected ='"+collected +"',notes ='"+notes +"', followup='"+followup +"', custsite='"+custsite+"', sitenum='"+sitenum+"', crectype='"+crectype+"', techid='"+techid+"', servsync=9, parts='"+parts+"' Where crecnum =" + crecnum + ";");
      	}

public static void AddItem(Connection con, int custnum, String callslip, String cdate, String equip1, String equip2, String equip3, String equip4,String reason, String services, String recommendations, String rscheduled, String charges, String collected, String notes, int followup, String custsite, String sitenum, String crectype, String techid, String parts)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
			String tservices = services.replaceAll("'","''");
			String trecommendations = recommendations.replaceAll("'","''");
	                stmt.executeUpdate("INSERT INTO callslip (custnum, callslip, cdate, equip1, equip2, equip3, equip4,reason, services, recommendations, rscheduled, charges, collected, notes, followup, custsite, sitenum, crectype, techid, servsync, parts) Values ('" + custnum + "','" + callslip + "','"+cdate+"','"+equip1+"','"+equip2+"','"+equip3+"','"+equip4+"','"+reason+"','"+tservices+"','"+trecommendations+"','"+rscheduled+"','"+charges+"','"+collected+"', '"+notes+"','"+followup+"', '"+custsite+"','"+sitenum+"', '"+crectype+"','"+techid+"', 0, '"+parts+"')");
		        }

        public int getCrecnum() { return crecnum; }
        public int getEquip1() { return equip1; }
        public int getEquip2() { return equip2; }
        public int getEquip3() { return equip3; }
        public int getEquip4() { return equip4; }
        public int getFollowup() { return followup; }
        public int getCustnum() { return custnum ; }
        public String getCustSite() { return custsite ; }
        public String getSiteNum() { return sitenum ; }
        
	public String getCallslip() { return callslip; }
	public String getCrecType() { return crectype; }
	public String getCdate() { return cdate; }
	public String getReason() { return reason; }
	public String getServices() { return services; }
	public String getRecommendations() { return recommendations; }
	public String getRscheduled() { return rscheduled; }
	public String getCharges() { return charges; }
	public String getCollected() { return collected; }
	public String getNotes() { return notes; }
	public String getTechID() { return techid; }
	public String getParts() { return parts; }
}
