package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class ServJobComp
{
        private int recnum;
        private int custnum=0;     //default value to null
	private int equip1=0;
	private int equip2=0;
	private int equip3=0;
	private int equip4=0;
	private int equip5=0;
	private int equip6=0;
	private int note1=0;
	private int note2=0;
	private int contnum=0;
   	private String compdate =null; 
   	private String qualdone =null; 
   	private String startupdone =null; 
   	private String customersat =null; 
   	private String turnoverok =null; 
   	private String jobnum =null; 

        public ServJobComp (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM jobcomplete WHERE recnum=" + recnum+ ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recnum = rs.getInt("recnum");
		this.custnum = rs.getInt("custnum");
		this.equip1 = rs.getInt("enum1");
		this.equip2 = rs.getInt("enum2");
		this.equip3 = rs.getInt("enum3");
		this.equip4 = rs.getInt("enum4");
		this.equip5 = rs.getInt("enum5");
		this.equip6 = rs.getInt("enum6");
		this.note1 = rs.getInt("note1");
		this.note2 = rs.getInt("note2");
		this.contnum = rs.getInt("contnum");
		this.compdate = rs.getString("compdate");
		this.qualdone = rs.getString("qualdone");
		this.startupdone = rs.getString("startupdone");
		this.customersat = rs.getString("customersat");
		this.turnoverok = rs.getString("turnoverok");
		this.jobnum = rs.getString("jobnum");
	}



	public static Vector getAllItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum  FROM jobcomplete where custnum='"+custnum+"' ORDER BY compdate;");
		while(rs.next())
		{
			ServJobComp t = new ServJobComp(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllJobItems(Connection c, int custnum, String jobnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum  FROM jobcomplete where custnum='"+custnum+"' and jobnum='"+jobnum+"' ORDER BY compdate;");
		while(rs.next())
		{
			ServJobComp t = new ServJobComp(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}
        public static void deleteItem(Connection con, String d)
                throws SQLException
        {
                int x = Integer.parseInt(d);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From jobcomplete  Where recnum=" + x + ";");
        }


	public static Vector getAllDateItems(Connection c, String compdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum  FROM jobcomplete where compdate='"+compdate+"' ORDER BY recnum;");
		while(rs.next())
		{
			ServJobComp t = new ServJobComp(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getCustItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum  FROM jobcomplete where custnum='"+custnum+"' ORDER BY compdate;");
		while(rs.next())
		{
			
			ServJobComp t = new ServJobComp(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getIndItems(Connection c, int custnum, int recnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT recnum  FROM jobcomplete where recnum='"+recnum+"' ORDER BY compdate;");
		while(rs.next())
		{
			
			ServJobComp t = new ServJobComp(c,rs.getInt("recnum"));
			V.addElement(t);
		}
		return V;
	}

	public static void UpdateItem(Connection con, int recnum, int custnum, String compdate, int equip1, int equip2, int equip3, int equip4, int equip5, int equip6, int note1, int note2, int contnum, String qualdone, String startupdone, String customersat, String turnoverok, String jobnum) 
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update jobcomplete Set custnum='"+custnum+"', compdate='"+compdate+"', enum1='"+equip1+"', enum2='"+equip2+"', enum3='"+equip3+"', enum4='"+equip4+"', enum5='"+equip5+"', enum6='"+equip6+"', note1='"+note1+"', note2='"+note2+"',contnum='"+contnum+"', qualdone='"+qualdone+"', startupdone='"+startupdone+"', customersat='"+customersat+"', turnoverok='"+turnoverok+"', jobnum='"+jobnum+"' Where recnum =" + recnum + ";");
      	}

	public static void AddItem(Connection con, int custnum, String compdate, int equip1, int equip2, int equip3, int equip4, int equip5, int equip6, int note1, int note2, int contnum, String qualdone, String startupdone, String customersat, String turnoverok, String jobnum) 
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO jobcomplete (custnum, compdate, enum1, enum2, enum3, enum4, enum5, enum6, note1, note2, contnum, qualdone, startupdone, customersat, turnoverok, jobnum) Values ('" + custnum + "','" + compdate + "','"+equip1+"','"+equip2+"','"+equip3+"','"+equip4+"','"+equip5+"','"+equip6+"','"+note1+"','"+note2+"','"+contnum+"','"+qualdone+"','"+startupdone+"','"+customersat+"', '"+turnoverok+"','"+jobnum+"')");
		        }

        public int getRecnum() { return recnum; }
        public int getEquip1() { return equip1; }
        public int getEquip2() { return equip2; }
        public int getEquip3() { return equip3; }
        public int getEquip4() { return equip4; }
        public int getEquip5() { return equip5; }
        public int getEquip6() { return equip6; }
        public int getNote1() { return note1; }
        public int getNote2() { return note2; }
        public int getContNum() { return contnum; }
        
	public String getQualDone() { return qualdone; }
	public String getCompDate() { return compdate; }
	public String getStartupDone() { return startupdone; }
	public String getCustomerSat() { return customersat; }
	public String getTurnoverOK() { return turnoverok; }
	public String getJobNum() { return jobnum; }
}
