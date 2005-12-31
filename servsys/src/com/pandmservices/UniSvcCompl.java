package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniSvcCompl
{
        private int recnum;
        private String callslip=null;     //default value to null
        private int codenum=0;     //default value to null
	private String complcode=null;
	private String compltext=null;

        public UniSvcCompl (Connection c, int recnum)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM callcompl,compl_codes  WHERE transnum=" + recnum + " and callcompl.codenum=compl_codes.codenum ;");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + recnum);
		}
		this.recnum = rs.getInt("transnum");
		this.callslip = rs.getString("callslip");
		this.codenum = rs.getInt("codenum");
	        this.complcode = rs.getString("complcode");
	        this.compltext = rs.getString("compltext");
	}



	public static Vector getAllItems(Connection c, String callslip)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT transnum FROM callcompl where callslip='"+callslip+"' ORDER BY codenum;");
		while(rs.next())
		{
			UniSvcCompl t = new UniSvcCompl(c,rs.getInt("transnum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getIndItem(Connection c, int crec)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT transnum FROM callcompl where transnum='"+crec+"';");
		while(rs.next())
		{
			UniSvcCompl t = new UniSvcCompl(c,rs.getInt("transnum"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, int recnum, String callslip, String codenum)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update callcompl Set callslip='" +callslip+ "', codenum='"+codenum+"' Where transnum=" + recnum + ";");
      	}

        
        public static void deleteItem(Connection con, int recnum)
		throws SQLException
	{
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From callcompl Where transnum=" + recnum + ";");
	}
        
	public static void AddItem(Connection con, String callslip, String codenum)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
	                stmt.executeUpdate("INSERT INTO callcompl (callslip, codenum) Values ('" + callslip + "','" + codenum + "')");
		        }

        public int getRecnum() { return recnum; }
        public String getCallslip() { return callslip; }
        public int  getCodeNum() { return codenum ; }
        public String getComplCode() { return complcode; }
        public String getComplText() { return compltext; }

}
