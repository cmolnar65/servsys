package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class ServCustNotes
{
        private int notenum;
        private int custnum=0;     //default value to null
   	private String  notedate=null; 
   	private String  notesubj=null; 
   	private String  notedata=null; 

        public ServCustNotes (Connection c, int notenum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM custnotes WHERE notenum=" + notenum+ ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + notenum);
		}
		this.notenum = rs.getInt("notenum");
		this.custnum = rs.getInt("custnum");
		this.notedate = rs.getString("notedate");
		this.notesubj = rs.getString("notesubj");
		this.notedata = rs.getString("notedata");
	}



	public static Vector getAllItems(Connection c, int custnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT notenum  FROM custnotes where custnum='"+custnum+"' ORDER BY notedate;");
		while(rs.next())
		{
			ServCustNotes t = new ServCustNotes(c,rs.getInt("notenum"));
			V.addElement(t);
		}
		return V;
	}

        public static void deleteItem(Connection con, String d)
                throws SQLException
        {
                int x = Integer.parseInt(d);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Delete From custnotes  Where notenum=" + x + ";");
        }


	public static Vector getAllDateItems(Connection c, String listdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT notenum  FROM custnotes where notedate='"+listdate+"' ORDER BY notenum;");
		while(rs.next())
		{
			ServCustNotes t = new ServCustNotes(c,rs.getInt("notenum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getIndItems(Connection c, int custnum, int notenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT notenum  FROM custnotes where notenum='"+notenum+"' ORDER BY notedate;");
		while(rs.next())
		{
			
			ServCustNotes t = new ServCustNotes(c,rs.getInt("notenum"));
			V.addElement(t);
		}
		return V;
	}

	public static void UpdateItem(Connection con, int notenum, int custnum, String notedate, String notesubj, String notedata)
		throws SQLException
	{
		Statement stmt = con.createStatement();
			String tnotedata = notedata.replaceAll("'","''");
			String ttnotedata = tnotedata.replaceAll("\n","<br>");
      		stmt.executeUpdate("Update custnotes Set custnum='" +custnum+ "', notedate='"+notedate +"',notesubj ='"+notesubj +"',notedata ='"+ttnotedata +"' Where notenum =" + notenum + ";");
      	}

	public static void AddItem(Connection con, int custnum, String notedate, String notesubj, String notedata)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
			String tnotedata = notedata.replaceAll("'","''");
			String ttnotedata = tnotedata.replaceAll("\n","<br>");
	                stmt.executeUpdate("INSERT INTO custnotes (custnum, notedate, notesubj, notedata) Values ('" + custnum + "','" + notedate + "','"+notesubj+"','"+ttnotedata+"')");
		        }

        public int getNoteNum() { return notenum; }
        public int getCustnum() { return custnum ; }
        
	public String getNoteDate() { return notedate; }
	public String getNoteSubj() { return notesubj; }
	public String getNoteData() { return notedata; }
}
