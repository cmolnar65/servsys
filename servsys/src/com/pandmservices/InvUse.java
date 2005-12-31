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


public class InvUse
{
        private int transnum;
	private int recnum;
	private String tdate;
	private String callslip;
	private String notes;
	private String quantity;
	private String itemname;
	private String keycode;
	private String descript;
	private String techid;
	private int servsync;

        public InvUse (Connection c, int transnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from inv_use where recnum='"+transnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + transnum);
		}
		this.quantity = rs.getString("quant");
		this.callslip = rs.getString("callslip");
		this.tdate = rs.getString("date");
		this.notes = rs.getString("notes");
		this.itemname = rs.getString("descript");
		this.keycode = rs.getString("keycode");
		this.descript = rs.getString("descript");
		this.recnum = rs.getInt("recnum");
		this.techid = rs.getString("techid");
		this.servsync = rs.getInt("servsync");
		rs.close();

	}


	public static Vector getAllCallslipItems(Connection c, String callslip, String cdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_use where callslip='"+callslip+"' and date='"+cdate+"' order by date  DESC");
		while(rs.next())
		{
			
			InvUse t = new InvUse(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		rs.close();
		return V;
	}

	public static Vector getAllItems(Connection c, String keycode)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_use where keycode='"+keycode+"' order by date DESC");
		while(rs.next())
		{
			
			InvUse t = new InvUse(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		rs.close();
		return V;
	}
	
        public static Vector getIndividualItems(Connection c, int recnum)
                throws SQLException, TodoException
        {
                Vector V = new Vector();
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM inv_use where recnum="+recnum+"");
                while(rs.next())
                {

                        InvUse t = new InvUse(c,rs.getInt("recnum"));
                        V.addElement(t);
                }
                rs.close();
                return V;
        }

	public static void UpdateItem(Connection con, int transnum, String callslip, String notes, String quantity, String keycode, String descript)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update inv_use Set quant ='" +quantity + "', callslip='"+callslip +"', notes='"+notes+"', descript='"+descript+"', keycode='"+keycode+"' Where recnum=" + transnum + ";");
      	}

	public static void addInvTrans( Connection con, String callslip, String tdate, String quantity, String keycode, String descript, String notes, String techid, int servsync)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO inv_use ( date, callslip, keycode, quant, descript, notes, techid, servsync) Values ('" +tdate+ "','"+callslip+"','"+keycode+"','"+quantity+"','"+descript+"','"+notes+"', '"+techid+"','"+servsync+"')");
		        }


public static void deleteItem(Connection con, int transnum)
                throws SQLException
        {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From inv_use Where recnum =" + transnum + ";");
        }


        public int getTransnum() { return recnum; }
        public int getItemnum() { return recnum; }
	public int getServSync() { return servsync; }
	public String getTechId() { return techid; }

        public String getCallslip() { return callslip; }
		public String getItemName() { return itemname; }
		public String getKeyCode() { return keycode; }
        public String getNotes() { return notes; }
        public String getQuantity() { return quantity; }
        public String getTDate() { return tdate; }

        public void setTransnum(int transnum) { this.transnum = transnum; }

}
