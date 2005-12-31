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


public class UniFlatRate
{
        private int recnum;
	private String service;
	private String tm_primary;
	private String tm_add;
	private String sa_primary;
	private String sa_add;
	private String jcode;
	private double jtime;
	private String keycode;
	private int specitem;
	private double psaddmult=0.00;
	private double psprimmult=0.00;
	private double tmaddmult=0.00;

	
        public UniFlatRate (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Vector vt;
                vt = FrConfig.getAllItems(c);
                int counter=0;
                double tmadditional = 0.00;
                for (int i = 0 ; i < vt.size(); i++)
                {
                        FrConfig t = (FrConfig) vt.elementAt(i);
                        tmaddmult = t.getTmAdditional();
			psaddmult = t.getPsAdditional();
			psprimmult = t.getPsPrimary();
                }

		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select recnum,service,tm_primary,round(tm_primary*"+tmaddmult+",2) as tm_add,round(tm_primary*"+psprimmult+",2) as sa_primary,round(tm_primary*"+psaddmult+",2) as sa_add,jtime,jcode,keycode,specitem from flat_rate where recnum='"+recnum+"' order by service;");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recnum=rs.getInt("recnum");
		this.specitem=rs.getInt("specitem");
		if (this.specitem==9) {
		this.tm_primary = rs.getString("tm_primary");
		this.tm_add = rs.getString("tm_primary");
		this.sa_primary=rs.getString("tm_primary");
		this.sa_add=rs.getString("tm_primary");
				     } else {
		this.tm_primary = rs.getString("tm_primary");
		this.tm_add = rs.getString("tm_add");
		this.sa_primary=rs.getString("sa_primary");
		this.sa_add=rs.getString("sa_add");
					}
		this.service = rs.getString("service");
		this.jtime=rs.getDouble("jtime");
		this.jcode=rs.getString("jcode");
		this.keycode=rs.getString("keycode");

	}


	public static Vector getAllItems(Connection c, String servicestart, String servicestop)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM flat_rate where service between '"+servicestart+"' and '"+servicestop+"' order by service");
		while(rs.next())
		{
			
			UniFlatRate t = new UniFlatRate(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c, String servicestart)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM flat_rate where service >= '"+servicestart+"' order by service");
		while(rs.next())
		{
			
			UniFlatRate t = new UniFlatRate(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}
        
	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM flat_rate order by service");
		while(rs.next())
		{
			
			UniFlatRate t = new UniFlatRate(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

        	public static Vector getIndItems(Connection c, int recnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM flat_rate where recnum= '"+recnum+"'");
		while(rs.next())
		{
			
			UniFlatRate t = new UniFlatRate(c,rs.getInt("recnum"));
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

	
	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From Todo Where ID=" + x + ";");
	}
	  
	public static void UpdateItem(Connection con, int recnum, String service, String tm_primary, String jtime, String jcode, String keycode, int specitem)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update flat_rate Set service='" +service+ "', tm_primary ='"+tm_primary +"',jtime='"+jtime+"', jcode='"+jcode+"', keycode='"+keycode+"', specitem='"+specitem+"' where recnum="+recnum+";");
      	}

public static void AddItem(Connection con, String service, String tm_primary, String jtime, String jcode, String keycode, int specitem)
                throws SQLException
		        {
	                Statement stmt = con.createStatement();
			String tservice = service.replaceAll("'","''");
	                stmt.executeUpdate("INSERT INTO flat_rate (service, tm_primary, jtime, jcode, keycode, specitem) Values ('" + tservice + "','" +tm_primary+ "','"+jtime+"','"+jcode+"','"+keycode+"','"+specitem+"')");
		        }

        public int getRecNum() { return recnum; }
        public int getSpecItem() { return specitem; }

        public String getService() { return service; }
	public String getTmPrimary() { return tm_primary; }
	public String getTmAdd() { return tm_add; }
	public String getSaPrimary() { return sa_primary; }
	public String getSaAdd() { return sa_add; }
	public double getJTime() { return jtime; }
	public String getJCode() { return jcode; }
	public String getKeycode() { return keycode; }
	

        public void setId(int recnum) { this.recnum = recnum; }

       
}
