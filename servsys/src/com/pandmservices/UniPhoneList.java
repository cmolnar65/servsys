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


public class UniPhoneList
{
        private int id;
	private String name;
	private String home_number;
	private String cell_number;
	private String direct_connect;
	private String truck_number;
	private String additional_number;
	private int record_number;

        public UniPhoneList (Connection c, String record_number)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from phone_list where record_number='"+record_number+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + record_number);
		}
		this.name=rs.getString("name");
		this.home_number = rs.getString("home_number");
		this.cell_number = rs.getString("cell_number");
		this.direct_connect = rs.getString("direct_connect");
		this.truck_number = rs.getString("truck_number");
		this.additional_number = rs.getString("additional_number");
		this.record_number = rs.getInt("record_number");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM phone_list order by name");
		while(rs.next())
		{
			
			UniPhoneList t = new UniPhoneList(c,rs.getString("record_number"));
			V.addElement(t);
		}
		return V;
	}
	
	public static Vector getIndividualItems(Connection c, int record_number)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM phone_list where record_number='"+record_number+"' ");
		while(rs.next())
		{
			
			UniPhoneList t = new UniPhoneList(c,rs.getString("record_number"));
			V.addElement(t);
		}
		return V;
	}


	public static void deleteItem(Connection con, int record_number)
		throws SQLException
	{
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From phone_list Where record_number=" + record_number + ";");
	}
	  

	public static void UpdateItem(Connection con, int record_number, String name, String home_number, String cell_number, String direct_connect, String truck_number, String additional_number)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update phone_list Set name ='" +name + "', home_number='"+home_number +"', cell_number='"+cell_number +"', direct_connect='"+direct_connect +"', truck_number='"+truck_number +"', additional_number='"+additional_number +"' Where record_number=" + record_number + ";");
      	}

	public static void AddItem(Connection con, String name, String home_number, String cell_number, String direct_connect, String truck_number, String additional_number)
                throws SQLException, NoSuchAlgorithmException, NoSuchProviderException
		        {
			Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO phone_list (name, home_number, cell_number, direct_connect, truck_number, additional_number) Values ('" + name + "','" +home_number+ "','"+ cell_number+ "','"+ direct_connect + "','" + truck_number + "','"+ additional_number + "')");
		        }

        public int getId() { return record_number; }

        public String getName() { return name; }
        public String getHomeNumber() { return home_number; }
        public String getCellNumber() { return cell_number; }
        public String getDirectConnect() { return direct_connect; }
        public String getTruckNumber() { return truck_number; }
        public String getAdditionalNumber() { return additional_number; }

        public void setId(int id) { this.id = id; }

}
