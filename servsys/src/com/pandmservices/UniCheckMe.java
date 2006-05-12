package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniCheckMe
{
	private String oa1= null;
	private String oa2= null;
	private String rwb1= null;
	private String rwb2= null;
	private String rdb1= null;
	private String rdb2= null;
	private String sdb1= null;
	private String sdb2= null;
	private String slt1= null;
	private String slt2= null;
	private String est1= null;
	private String est2= null;
	private String cst1= null;
	private String cst2= null;
	private String llt1= null;
	private String llt2= null;
	private String lp1= null;
	private String lp2= null;
	private String hp1= null;
	private String hp2= null;
	private String ss1= null;
	private String ss2= null;
	private String rs1= null;
	private String rs2= null;
	private String rfc1= null;
	private String rca1= null;
	private String afcor= null;
	private String csp= null;
	private String newins= null;
	private String acsuite= null;
	private String acn= null;
	private String comp= null;
	private String ttype= null;
	private String actype= null;
	private String manyear= null;
	private String min1= null;
	private String min2= null;
	private String rtype= null;
	private String trueflow= null;
	private String hport= null;
	private String mdevice= null;
	private String targetas= null;
	private String callslip=null;
	private String services=null;
        private int crecnum;
        private int custnum=0;     //default value to null
	private int equip1=0;
	private int equip2=0;
	private int equip3=0;
	private int equip4=0;
	private int followup=0;
   	private String  cdate=null; 
   	private String  reason=null; 
   	private String  recommendations=null; 
   	private String  rscheduled=null; 
   	private String  charges=null; 
   	private String  collected=null; 
   	private String  notes=null; 
	private String custsite=null;
	private String sitenum=null;
	private String crectype=null;
	private String techid=null;

        public UniCheckMe (Connection c, int crecnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM checkme WHERE crecnum=" + crecnum+ ";");
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

	this.oa1=rs.getString("oa1");
	this.oa2=rs.getString("oa2");
	this.rwb1=rs.getString("rwb1");
	this.rwb2=rs.getString("rwb2");
	this.rdb1=rs.getString("rdb1");
	this.rdb2=rs.getString("rdb2");
	this.sdb1=rs.getString("sdb1");
	this.sdb2=rs.getString("sdb2");
	this.slt1=rs.getString("slt1");
	this.slt2=rs.getString("slt2");
	this.est1=rs.getString("est1");
	this.est2=rs.getString("est2");
	this.cst1=rs.getString("cst1");
	this.cst2=rs.getString("cst2");
	this.llt1=rs.getString("llt1");
	this.llt2=rs.getString("llt2");
	this.lp1=rs.getString("lp1");
	this.lp2=rs.getString("lp2");
	this.hp1=rs.getString("hp1");
	this.hp2=rs.getString("hp2");
	this.ss1=rs.getString("ss1");
	this.ss2=rs.getString("ss2");
	this.rs1=rs.getString("rs1");
	this.rs2=rs.getString("rs2");
	this.rfc1=rs.getString("rfc1");
	this.rca1=rs.getString("rca1");
	this.afcor=rs.getString("afcor");
	this.csp=rs.getString("csp");
	this.newins=rs.getString("newins");
	this.acsuite=rs.getString("acsuite");
	this.acn=rs.getString("acn");
	this.comp=rs.getString("comp");
	this.ttype=rs.getString("ttype");
	this.actype=rs.getString("actype");
	this.manyear=rs.getString("manyear");
	this.min1=rs.getString("min1");
	this.min2=rs.getString("min2");
	this.rtype=rs.getString("rtype");
	this.trueflow=rs.getString("trueflow");
	this.hport=rs.getString("hport");
	this.mdevice=rs.getString("mdevice");
	this.targetas=rs.getString("targetas");
	}



	public static Vector getAllItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM checkme where custnum='"+custnum+"' ORDER BY cdate;");
		while(rs.next())
		{
			UniCheckMe t = new UniCheckMe(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

        public static void deleteItem(Connection con, String d)
                throws SQLException
        {
                int x = Integer.parseInt(d);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From checkme  Where crecnum=" + x + ";");
        }

        public static void deleteAllCustItems(Connection con, int d)
                throws SQLException
        {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From checkme  Where crecnum=" + d + ";");
        }


	public static Vector getIncludedDateItems(Connection c, String startdate, String enddate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM checkme where cdate>='"+startdate+"' and cdate<='"+enddate+"' ORDER BY crecnum;");
		while(rs.next())
		{
			UniCheckMe t = new UniCheckMe(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllDateItems(Connection c, String listdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM checkme where cdate='"+listdate+"' ORDER BY crecnum;");
		while(rs.next())
		{
			UniCheckMe t = new UniCheckMe(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getIndItems(Connection c, int custnum, int crecnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM checkme where crecnum='"+crecnum+"' ORDER BY cdate;");
		while(rs.next())
		{
			
			UniCheckMe t = new UniCheckMe(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getCallSlipFollowup(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT crecnum  FROM checkme where followup=1 order by cdate, crecnum;");
		while(rs.next())
		{
			
			UniCheckMe t = new UniCheckMe(c,rs.getInt("crecnum"));
			V.addElement(t);
		}
		return V;
	}

	public static void UpdateItem(Connection con, int crecnum, int custnum, String callslip, String cdate, String equip1, String equip2, String equip3, String equip4, String reason, String services, String recommendations, String rscheduled, String charges, String collected, String notes, int followup, String custsite, String sitenum, String crectype, String techid,  String oa1, String oa2, String rwb1, String rwb2, String rdb1, String rdb2, String sdb1, String sdb2, String slt1, String slt2, String est1, String est2, String cst1, String cst2, String llt1, String llt2, String lp1, String lp2, String hp1, String hp2, String ss1, String ss2, String rs1, String rs2, String rfc1, String rca1, String afcor, String csp, String newins, String acsuite, String acn, String comp, String ttype, String actype, String manyear, String min1, String min2, String rtype, String trueflow, String hport, String mdevice, String targetas)
		throws SQLException
	{
		Statement stmt = con.createStatement();
			String tservices = services.replaceAll("'","''");
			String trecommendations = recommendations.replaceAll("'","''");
      		stmt.executeUpdate("Update checkme Set custnum='" +custnum+ "', callslip='"+callslip +"',cdate ='"+cdate +"',equip1 ='"+equip1 +"',equip2 ='"+equip2 +"',equip3 ='"+equip3 +"',equip4 ='"+equip4 +"', reason ='"+reason +"',services ='"+tservices +"',recommendations ='"+trecommendations +"',rscheduled ='"+rscheduled +"',charges ='"+charges +"',collected ='"+collected +"',notes ='"+notes +"', followup='"+followup +"', custsite='"+custsite+"', sitenum='"+sitenum+"', crectype='"+crectype+"', techid='"+techid+"',oa1='"+oa1+"',oa2='"+oa2+"', rwb1='"+rwb1+"', rwb2='"+rwb2+"', rdb1='"+rdb1+"', rdb2='"+rdb2+"', sdb1='"+sdb1+"', sdb2='"+sdb2+"', slt1='"+slt1+"', slt2='"+slt2+"', est1='"+est1+"', est2='"+est2+"', cst1='"+cst1+"', cst2='"+cst2+"', llt1='"+llt1+"', llt2='"+llt2+"', lp1='"+lp1+"', lp2='"+lp2+"', hp1='"+hp1+"', hp2='"+hp2+"', ss1='"+ss1+"', ss2='"+ss2+"', rs1='"+rs1+"', rs2='"+rs2+"', rfc1='"+rfc1+"', rca1='"+rca1+"', afcor='"+afcor+"', csp='"+csp+"', newins='"+newins+"', acsuite='"+acsuite+"', acn='"+acn+"', comp='"+comp+"', ttype='"+ttype+"', actype='"+actype+"',manyear='"+ manyear+"', min1='"+min1+"', min2='"+min2+"', rtype='"+rtype+"', trueflow='"+trueflow+"', hport='"+hport+"', mdevice='"+mdevice+"', targetas='"+targetas+"'  Where crecnum =" + crecnum + ";");
      	}

public static void AddItem(Connection con, int custnum, String callslip, String cdate, String equip1, String equip2, String equip3, String equip4,String reason, String services, String recommendations, String rscheduled, String charges, String collected, String notes, int followup, String custsite, String sitenum, String crectype, String techid, String oa1, String oa2, String rwb1, String rwb2, String rdb1, String rdb2, String sdb1, String sdb2, String slt1, String slt2, String est1, String est2, String cst1, String cst2, String llt1, String llt2, String lp1, String lp2, String hp1, String hp2, String ss1, String ss2, String rs1, String rs2, String rfc1, String rca1, String afcor, String csp, String newins, String acsuite, String acn, String comp, String ttype, String actype, String manyear, String min1, String min2, String rtype, String trueflow, String hport, String mdevice, String targetas)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
			String tservices = services.replaceAll("'","''");
			String trecommendations = recommendations.replaceAll("'","''");
	                stmt.executeUpdate("INSERT INTO checkme (custnum, callslip, cdate, equip1, equip2, equip3, equip4,reason, services, recommendations, rscheduled, charges, collected, notes, followup, custsite, sitenum, crectype, techid, oa1, oa2, rwb1, rwb2, rdb1, rdb2, sdb1, sdb2, slt1, slt2, est1, est2, cst1, cst2, llt1, llt2, lp1, lp2, hp1, hp2, ss1, ss2, rs1, rs2, rfc1, rca1, afcor, csp, newins, acsuite, acn, comp, ttype, actype, manyear, min1, min2, rtype, trueflow, hport, mdevice, targetas) Values ('" + custnum + "','" + callslip + "','"+cdate+"','"+equip1+"','"+equip2+"','"+equip3+"','"+equip4+"','"+reason+"','"+tservices+"','"+trecommendations+"','"+rscheduled+"','"+charges+"','"+collected+"', '"+notes+"','"+followup+"', '"+custsite+"','"+sitenum+"', '"+crectype+"','"+techid+"','"+oa1+"', '"+oa2+"', '"+rwb1+"', '"+rwb2+"', '"+rdb1+"', '"+rdb2+"', '"+sdb1+"', '"+sdb2+"', '"+slt1+"', '"+slt2+"', '"+est1+"', '"+est2+"', '"+cst1+"', '"+cst2+"', '"+llt1+"', '"+llt2+"', '"+lp1+"', '"+lp2+"', '"+hp1+"', '"+hp2+"', '"+ss1+"', '"+ss2+"', '"+rs1+"', '"+rs2+"', '"+rfc1+"', '"+rca1+"', '"+afcor+"', '"+csp+"', '"+newins+"', '"+acsuite+"', '"+acn+"', '"+comp+"', '"+ttype+"', '"+actype+"','"+ manyear+"', '"+min1+"', '"+min2+"', '"+rtype+"', '"+trueflow+"', '"+hport+"', '"+mdevice+"', '"+targetas+"')");
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

	 public String getOa1() { return oa1; }
	 public String getOa2() { return oa2; }
	 public String getRwb1() { return rwb1; }
	 public String getRwb2() { return rwb2; }
	 public String getRdb1() { return rdb1; }
	 public String getRdb2() { return rdb2; }
	 public String getSdb1() { return sdb1; }
	 public String getSdb2() { return sdb2; }
	 public String getSlt1() { return slt1; }
	 public String getSlt2() { return slt2; }
	 public String getEst1() { return est1; }
	 public String getEst2() { return est2; }
	 public String getCst1() { return cst1; }
	 public String getCst2() { return cst2; }
	 public String getLlt1() { return llt1; }
	 public String getLlt2() { return llt2; }
	 public String getLp1() { return lp1; }
	 public String getLp2() { return lp2; }
	 public String getHp1() { return hp1; }
	 public String getHp2() { return hp2; }
	 public String getSs1() { return ss1; }
	 public String getSs2() { return ss2; }
	 public String getRs1() { return rs1; }
	 public String getRs2() { return rs2; }
	 public String getRfc1() { return rfc1; }
	 public String getRca1() { return rca1; }
	 public String getAfcor() { return afcor; }
	 public String getCsp() { return csp; }
	 public String getNewins() { return newins; }
	 public String getAcsuite() { return acsuite; }
	 public String getAcn() { return acn; }
	 public String getComp() { return comp; }
	 public String getTtype() { return ttype; }
	 public String getActype() { return actype; }
	 public String getManyear() { return manyear; }
	 public String getMin1() { return min1; }
	 public String getMin2() { return min2; }
	 public String getRtype() { return rtype; }
	 public String getTrueflow() { return trueflow; }
	 public String getHport() { return hport; }
	 public String  getMdevice() { return mdevice; }
	public String getTargetas() { return targetas; }
}
