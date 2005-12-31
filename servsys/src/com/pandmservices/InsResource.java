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


public class InsResource
{
        private int recnum;
	private String name;
	private String skillset;
	private String leadhelp;

        public InsResource (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from resources where recnum='"+recnum+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.name = rs.getString("rname");
		this.recnum = rs.getInt("recnum");
		this.skillset = rs.getString("skill");
		this.leadhelp = rs.getString("leadhelp");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM resources order by rname");
		while(rs.next())
		{
			
			InsResource t = new InsResource(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From resources Where recnum=" + x + ";");
	}
	  

	public static void UpdateItem(Connection con, int recnum, String name, String skillset, String leadhelp)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update resources Set rname='" +name+ "', skill ='"+skillset +"',leadhelp  ='"+leadhelp +"' Where recnum=" + recnum + ";");
      	}

public static void addResource(Connection con, String name, String skillset, String leadhelp)
                throws SQLException
		        {
	                Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO resources (rname, skill, leadhelp) Values ('" + name + "','" +skillset+ "','"+ leadhelp + "')");
		        }

        public int getRecNum() { return recnum; }

        public String getSkillSet() { return skillset; }
	public String getLeadHelp() { return leadhelp; }
	public String getResName() { return name; }
}
