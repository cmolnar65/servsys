package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniSvcCharges
{
        private int recnum;
        private String callslip=null;     //default value to null
        private String descript=null;     //default value to null
   	private double quant=0;
	private double price=0; 
	private double total=0; 
	private int servsync;
	private int frcode;
	private String techid=null;

        public UniSvcCharges (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM svc_charges WHERE recnum=" + recnum + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recnum = rs.getInt("recnum");
		this.servsync = rs.getInt("servsync");
		this.callslip = rs.getString("callslip");
		this.descript = rs.getString("descript");
		this.techid = rs.getString("techid");
		this.quant = rs.getDouble("quant");
		this.price = rs.getDouble("price");
		this.frcode = rs.getInt("frcode");
		this.total = this.quant*this.price;
	}



	public static Vector getAllItems(Connection c, String callslip)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM svc_charges where callslip='"+callslip+"' ORDER BY descript;");
		while(rs.next())
		{
			UniSvcCharges t = new UniSvcCharges(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndItem(Connection c, int crec)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum FROM svc_charges where recnum='"+crec+"';");
		while(rs.next())
		{
			UniSvcCharges t = new UniSvcCharges(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, int recnum, String callslip, String descript, String quant, String price, int frcode)

		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update svc_charges Set callslip='" +callslip+ "', descript='"+descript+"', quant='"+quant+"', price='"+price+"', frcode='"+frcode+"' Where recnum=" + recnum + ";");
      	}

        
        public static void deleteItem(Connection con, int recnum)
		throws SQLException
	{
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From svc_charges Where recnum=" + recnum + ";");
	}
  
public static void deleteAllItems(Connection con, String callslip)
                throws SQLException
        {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From svc_charges Where callslip ='" + callslip + "';");
        }
	
	public static void AddItem(Connection con, String callslip, String descript, String quant, String price, String techid, int servsync, int frcode)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO svc_charges (callslip, descript, quant, price, techid, servsync, frcode) Values ('" + callslip + "','" + descript + "','"+quant+"',round('"+price+"',2), '"+techid+"', '"+servsync+"', '"+frcode+"')");
		        }

        public int getRecnum() { return recnum; }
	public int getServsync() { return servsync; }
	public String getTechId() { return techid; }
        public String getCallslip() { return callslip; }
        public String getDescript() { return descript ; }
        public double getQuant() { return quant ; }
	public double getPrice() { return price ; }
	public double getTotal() { return total ; }
	public int getFrcode() { return frcode ; }

}
