package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniCat
{
        private int id;
        private String description="";     //default value to null
        private boolean done = false;      //default value to false
    

        public UniCat (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM tblcat WHERE catid=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.id = rs.getInt("catid");
		this.description = rs.getString("catdesc");
	}



	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT catid  FROM tblcat ORDER BY catid;");
		while(rs.next())
		{
			
			UniCat t = new UniCat(c,rs.getInt("catid"));
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
	  

	public static void changeItem(Connection con, int id)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update todo Set Done=true Where ID=" + id + ";");
      	}

	public static void UpdateItem(Connection con, int id, String desc)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update todo Set description='" +desc+ "' Where ID=" + id + ";");
      	}

public static void addCatItem(Connection con, String desc, String id)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO tblcat (catid, catdesc) Values ('" + id + "','" + desc + "')");
		        }

        public int getId() { return id; }

        public String getDescription() { return description; }
        public boolean getDone() { return done; }


        public void setId(int id) { this.id = id; }
        public void setDescription(String desc) { this.description = desc; }
        public void setDone(boolean done) { this.done = done; }
}
