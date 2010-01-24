package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniWsItems
{
        private int id;
        private int wsrec=0;     //default value to null
        private int itemrec=0;;     //default value to null
        private String item=null;     //default value to null
        private String keycode=null;     //default value to null
        private int quantity=0;     //default value to null
        private double cost=0;     //default value to null
        private double laborcost=0;     //default value to null
	private double subcost=0;
	private int laborhours=0;
	private int shophours=0;
    

        public UniWsItems (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM wsitem WHERE itemrec=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.wsrec = rs.getInt("wsrec");
		this.itemrec = rs.getInt("itemrec");
		this.item = rs.getString("item");
		this.keycode = rs.getString("keycode");
		this.cost = rs.getDouble("cost");
		this.quantity = rs.getInt("quantity");
		this.laborhours = rs.getInt("laborhours");
		this.shophours = rs.getInt("shophours");
		this.laborcost = rs.getDouble("laborcost");
		this.subcost = rs.getDouble("subcost");

	}



	public static Vector getIndividualItem(Connection c, int itemrec)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT itemrec  FROM wsitem where itemrec="+itemrec+" ORDER BY itemrec;");
		while(rs.next())
		{
			
			UniWsItems t = new UniWsItems(c,rs.getInt("itemrec"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c, int wsrec)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT itemrec  FROM wsitem where wsrec="+wsrec+" order by itemrec;");
		while(rs.next())
		{
			
			UniWsItems t = new UniWsItems(c,rs.getInt("itemrec"));
			V.addElement(t);
		}
		return V;
	}


	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From wsitem Where itemrec=" + x + ";");
	}
	  

	public static void UpdateItem(Connection con, int itemrec, int wsrec, String item, String keycode, int quantity, String cost, int laborhours, String laborcost, int shophours, String subcost )
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update wsitem Set wsrec='" +wsrec+ "',item = '"+item+"', keycode = '"+keycode +"',quantity = '"+quantity +"',cost = '"+cost +"', laborhours =  '"+laborhours+"', laborcost = '"+laborcost+"', shophours = '"+shophours+"', subcost='"+subcost+"'  Where itemrec=" + itemrec + ";");
      	}

public static void AddItem(Connection con, int wsrec, String item, String keycode, int quantity, String cost, int laborhours, String laborcost, int shophours, String subcost)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                //System.out.println("INSERT INTO wsitem (wsrec, item, keycode, quantity, cost, laborhours, laborcost, shophours) Values ('" +wsrec+ "','" +item + "','"+keycode +"','"+quantity +"','"+cost+"','"+laborhours+"','"+laborcost+"','"+shophours+"')");
	                stmt.executeUpdate("INSERT INTO wsitem (wsrec, item, keycode, quantity, cost, laborhours, laborcost, shophours, subcost) Values ('" +wsrec+ "','" +item + "','"+keycode +"','"+quantity +"','"+cost+"','"+laborhours+"','"+laborcost+"','"+shophours+"', '"+subcost+"')");
		        }

        public int getId() { return id; }

        public int getItemRec() { return itemrec; }
        public int getWsRec() { return wsrec; }
        public String getWsItem() { return item; }
        public String getWsKeyCode() { return keycode; }
        public int getWsQuant() { return quantity; }
        public double getWsCost() { return cost; }
	public double getWsLaborCost() { return laborcost; }
        public int getWsLaborHours() { return laborhours; }
	public double getWsSubCost() { return subcost; }
        public int getWsShopHours() { return shophours; }


}
