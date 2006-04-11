package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniMasterWorksheet
{
        private int id;
        private int wsrec=0;     //default value to null
        private int crec=0;     //default value to null
        private String wsdate=null;     //default value to null
        private String wsdesc=null;     //default value to null
	private String wsmult=null;
    

        public UniMasterWorksheet (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM masterworksheet WHERE wsrec=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.wsrec = rs.getInt("wsrec");
		this.wsmult = rs.getString("wsmult");
		this.wsdate = rs.getString("wsdate");
		this.wsdesc = rs.getString("wsdesc");
	}



	public static Vector getAllItems(Connection c, String wsstart, String wsstop)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM masterworksheet where wsdesc between '"+wsstart+"' and '"+wsstop+"' order by wsdesc");
		while(rs.next())
		{
			UniMasterWorksheet t = new UniMasterWorksheet(c,rs.getInt("wsrec"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllItems(Connection c, String wsstart)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM masterworksheet where wsdesc >= '"+wsstart+"' order by wsdesc");
		while(rs.next())
		{
			UniMasterWorksheet t = new UniMasterWorksheet(c,rs.getInt("wsrec"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT wsrec  FROM masterworksheet ORDER BY wsdesc;");
		while(rs.next())
		{
			
			UniMasterWorksheet t = new UniMasterWorksheet(c,rs.getInt("wsrec"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndItem(Connection c, int wsrec)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT wsrec  FROM masterworksheet where wsrec="+wsrec+";");
		while(rs.next())
		{
			
			UniMasterWorksheet t = new UniMasterWorksheet(c,rs.getInt("wsrec"));
			V.addElement(t);
		}
		return V;
	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From masterworksheet Where wsrec=" + x + ";");
		stmt.executeUpdate("Delete From masterwsitem Where wsrec=" + x + ";");
	}
	  

	public static void UpdateItem(Connection con, int wsrec, String wsdate, String wsdescription, String wsmult)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update masterworksheet Set wsdate = '"+wsdate+"',wsdesc = '"+wsdescription +"', wsmult='"+wsmult+"' Where wsrec=" + wsrec + ";");
      	}

public static void AddItem(Connection con, int wsrec, String wsdate, String wsdescription, String wsmult)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO masterworksheet (wsrec, wsdate, wsdesc, wsmult, wsnotes) Values ("+wsrec+", '" +wsdate+ "','" +wsdescription+ "', '"+wsmult+"','-')");
		        }


public static void AddItem(Connection con, String wsdate, String wsdescription, String wsmult)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO masterworksheet (wsdate, wsdesc, wsmult, wsnotes) Values ('" +wsdate+ "','" +wsdescription+ "', '"+wsmult+"','-')");
		        }

	public static Vector getMaxItem(Connection c)
		throws SQLException, TodoException
		{
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("Select wsrec,max(wsrec) from masterworksheet group by wsrec;");
		while(rs.next())
	{
		UniMasterWorksheet t=new UniMasterWorksheet(c,rs.getInt("wsrec"));
		V.addElement(t);
	}
		return V;
	}



        public int getId() { return id; }

        public int getWsRec() { return wsrec; }
        public String getWsMult() { return wsmult; }
        public String getWsDate() { return wsdate; }
        public String getWsDesc() { return wsdesc; }

}
