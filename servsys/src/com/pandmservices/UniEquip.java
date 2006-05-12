package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniEquip
{
        private int id;
        private int custnum=0;     //default value to null
        private String brand=null;     //default value to null
        private String modelnum=null;     //default value to null
        private String serialnum=null;     //default value to null
        private String filter=null;     //default value to null
        private String notes=null;     //default value to null
        private String etype=null;     //default value to null
	private String cseer=null;
	private String btuout=null;
	private String custsite=null;
	private String sitenum=null;
	private int servsync=0;
    

        public UniEquip (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM equipment WHERE enum=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.id = rs.getInt("enum");
		this.custnum = rs.getInt("custnum");
		this.brand = rs.getString("brand");
		this.modelnum = rs.getString("modelnum");
		this.serialnum = rs.getString("serialnum");
		this.filter = rs.getString("filter");
		this.notes = rs.getString("notes");
		this.etype = rs.getString("etype");
		this.cseer = rs.getString("cseer");
		this.btuout = rs.getString("btuout");
		this.custsite=rs.getString("custsite");
		this.sitenum=rs.getString("sitenum");
		this.servsync=rs.getInt("servsync");
	}



	public static Vector getAllItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT enum  FROM equipment where custnum='"+custnum+"' ORDER BY enum;");
		while(rs.next())
		{
			
			UniEquip t = new UniEquip(c,rs.getInt("enum"));
			V.addElement(t);
		}
		return V;
	}



	public static Vector getCustomerItems(Connection c, String custsite, String sitenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT enum  FROM equipment where custsite='"+custsite+"' and sitenum='"+sitenum+"' ORDER BY enum;");

		while(rs.next())
		{
			UniEquip t = new UniEquip(c,rs.getInt("enum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getUnSyncedItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT enum  FROM equipment where servsync=0 or servsync is NULL and custsite is not NULL and sitenum is not NULL ORDER BY custnum;");
		while(rs.next())
		{
			UniEquip t = new UniEquip(c,rs.getInt("enum"));
			V.addElement(t);
		}
		return V;
	}

	public static void UpdateServSyncOn(Connection con, int num)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("update equipment set servsync=1 Where enum=" +num + ";");
	}
	  

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From equipment Where enum=" + x + ";");
	}
	  
	
	        public static void deleteCustItems(Connection con, int d)
                throws SQLException
        {
                
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From equipment Where custnum=" + d + ";");
        }
	
	public static void UpdateItem(Connection con, int id, int custnum, String brand, String modelnum, String serialnum, String filter, String notes, String etype, String cseer, String btuout, String custsite, String sitenum, int servsync)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update equipment Set custnum='" +custnum+ "', brand='"+brand+"', modelnum='"+modelnum+"', serialnum='"+serialnum+"', filter='"+filter+"', notes='"+notes+"', etype='"+etype+"', cseer='"+cseer+"', btuout='"+btuout+"',custsite ='"+custsite+"', sitenum='"+sitenum+"', servsync='"+servsync+"' Where enum=" + id + ";");
      	}

public static void AddItem(Connection con, int custnum, String brand, String modelnum, String serialnum, String filter, String notes, String etype, String cseer, String btuout, String custsite, String sitenum, int servsync)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO equipment (custnum, brand, modelnum, serialnum, filter, notes, etype, cseer, btuout, custsite, sitenum, servsync) Values ('" + custnum + "','" + brand + "','"+modelnum+"','"+serialnum+"','"+filter+"','"+notes+"','"+etype+"', '"+cseer+"', '"+btuout+"','"+custsite+"','"+sitenum+"', '"+servsync+"')");
		        }

        public int getId() { return id; }

        public int getCustnum() { return custnum ; }
	public String getCustSite() { return custsite; }
	public String getSiteNum() { return sitenum; }
        public String getBrand() { return brand; }
        public String getModelnum() { return modelnum ; }
        public String getSerialnum() { return serialnum ; }
        public String getFilter() { return filter; }
        public String getNotes() { return notes; }
        public String getEtype() { return etype; }
        public String getCSeer() { return cseer; }
        public String getBtuOut() { return btuout; }
	public int getServSync() { return servsync; }
}
