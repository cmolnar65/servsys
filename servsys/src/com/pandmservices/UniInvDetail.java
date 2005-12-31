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


public class UniInvDetail
{
        private int transnum;
	private int itemnum;
	private String tdate;
	private String callslip;
	private String notes;
	private String quantity;

        public UniInvDetail (Connection c, int transnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from inv_detail where transnum='"+transnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + transnum);
		}
		this.quantity = rs.getString("quantity");
		this.callslip = rs.getString("callslip");
		this.tdate = rs.getString("date");
		this.notes = rs.getString("notes");
		this.itemnum = rs.getInt("itemnum");
		this.transnum = rs.getInt("transnum");
		rs.close();
	}


	public static Vector getAllCallslipItems(Connection c, String callslip, String cdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_detail where callslip="+callslip+" and date='"+cdate+"' order by date DESC");
		while(rs.next())
		{
			
			UniInvDetail t = new UniInvDetail(c,rs.getInt("transnum"));
			V.addElement(t);
		}
		rs.close();
		return V;
	}

	public static Vector getAllItems(Connection c, int itemnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM inv_detail where itemnum="+itemnum+" order by date DESC");
		while(rs.next())
		{
			
			UniInvDetail t = new UniInvDetail(c,rs.getInt("transnum"));
			V.addElement(t);
		}
		rs.close();
		return V;
	}
	
        public static Vector getIndividualItems(Connection c, int transnum)
                throws SQLException, TodoException
        {
                Vector V = new Vector();
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM inv_detail where transnum="+transnum+"");
                while(rs.next())
                {

                        UniInvDetail t = new UniInvDetail(c,rs.getInt("transnum"));
                        V.addElement(t);
                }
                rs.close();
                return V;
        }

	public static void UpdateItem(Connection con, int transnum, String callslip, String notes, String quantity)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update inv_detail Set quantity ='" +quantity + "', callslip='"+callslip +"', notes='"+notes+"' Where transnum=" + transnum + ";");
      	}

	public static void addInvTrans( Connection con, String callslip, int itemnum, String tdate, String notes, String quantity)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO inv_detail (itemnum, date, callslip, quantity, notes) Values ('" + itemnum + "','" +tdate+ "','"+callslip+"','"+quantity+"','"+notes+"')");
		        }


public static void deleteItem(Connection con, int transnum)
                throws SQLException
        {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From inv_detail Where transnum =" + transnum + ";");
        }


        public int getTransnum() { return transnum; }
        public int getItemnum() { return itemnum; }

        public String getCallslip() { return callslip; }
        public String getNotes() { return notes; }
        public String getQuantity() { return quantity; }
        public String getTDate() { return tdate; }

        public void setTransnum(int transnum) { this.transnum = transnum; }

}
