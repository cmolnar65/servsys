package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class ServQuotes
{
        private int id;
        private int quotenum=0;;     //default value to null
        private int crecnum=0;;     //default value to null
        private String qdate=null;     //default value to null
        private String qdescription=null;     //default value to null
        private String qpayterms=null;     //default value to null
        private String qnotes=null;     //default value to null
        private String qdisc=null;     //default value to null
        private String qadditionalserv=null;     //default value to null
        private String qstatus=null;     //default value to null
	private String solddate=null;
	private String jobnum=null;
	private String antstart=null;
    

        public ServQuotes (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM squotes WHERE quotenum=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.quotenum = rs.getInt("quotenum");
		this.crecnum = rs.getInt("crecnum");
		this.qdate = rs.getString("qdate");
		this.qdescription = rs.getString("qdescription");
		this.qpayterms = rs.getString("qpayterms");
		this.qnotes = rs.getString("qnotes");
		this.qdisc = rs.getString("qdisc");
		this.qadditionalserv = rs.getString("qadditionalserv");
		this.qstatus = rs.getString("status");
		this.solddate = rs.getString("solddate");
		this.jobnum = rs.getString("jobnum");
		this.antstart = rs.getString("antstart");
	}



	public static Vector getAllItems(Connection c, int crecnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quotenum  FROM squotes where crecnum='"+crecnum+"' ORDER BY qdate;");
		while(rs.next())
		{
			
			ServQuotes t = new ServQuotes(c,rs.getInt("quotenum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getMaxItem(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quotenum,max(quotenum)  FROM squotes GROUP BY quotenum;");
		while(rs.next())
		{
			
			ServQuotes t = new ServQuotes(c,rs.getInt("quotenum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllDateItems(Connection c, String qdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quotenum  FROM squotes where qdate='"+qdate+"' ORDER BY qdate;");
		while(rs.next())
		{
			
			ServQuotes t = new ServQuotes(c,rs.getInt("quotenum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getIndItem(Connection c, int crecnum, int quotenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quotenum  FROM squotes where crecnum='"+crecnum+"' and quotenum="+quotenum+"  ORDER BY qdate;");
		while(rs.next())
		{
			
			ServQuotes t = new ServQuotes(c,rs.getInt("quotenum"));
			V.addElement(t);
		}
		return V;
	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From squotes Where quotenum=" + x + ";");
		stmt.executeUpdate("Delete From squote_parts Where quotenum=" + x + ";");
	}
	  

	public static void UpdateItem(Connection con, int quotenum, int crecnum, String qdate, String qdescription, String qpayterms, String qnotes, String qdisc, String qadditionalserv, String qstatus, String solddate, String jobnum,String antstart )
		throws SQLException
	{
		Statement stmt = con.createStatement();
	String tqnotes = qnotes.replaceAll("'","''");
      		stmt.executeUpdate("Update squotes Set crecnum='" +crecnum+ "',qdate = '"+qdate+"',qdescription = '"+qdescription +"',qpayterms = '"+qpayterms +"',qnotes = '"+tqnotes +"', qdisc = '"+qdisc+"',qadditionalserv='"+qadditionalserv+"', status='"+qstatus+"', solddate='"+solddate+"', jobnum='"+jobnum+"', antstart='"+antstart+"' Where quotenum=" + quotenum + ";");
      	}

public static void AddItem(Connection con, int crecnum, String qdate, String qdescription, String qpayterms, String qnotes, String qdisc ,String qadditionalserv, String qstatus, String solddate, String antstart)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
			String tqnotes = qnotes.replaceAll("'","''");
	                stmt.executeUpdate("INSERT INTO squotes (crecnum, qdate, qdescription, qpayterms, qnotes, qdisc, qadditionalserv, status, solddate, antstart) Values ('"+crecnum+"','" +qdate+ "','" +qdescription+ "','"+qpayterms+"','"+tqnotes+"','"+qdisc+"','"+qadditionalserv+"', '"+qstatus+"', '"+solddate+"', '"+antstart+"')");
		        }

        public int getId() { return id; }

        public int getQuoteNum() { return quotenum; }
        public int getCrecNum() { return crecnum; }
        public String getQDate() { return qdate; }
        public String getQDescription() { return qdescription; }
        public String getQPayterms() { return qpayterms; }
        public String getQNotes() { return qnotes; }
        public String getQDisc() { return qdisc; }
        public String getQStatus() { return qstatus; }
        public String getQAdditionalServ() { return qadditionalserv; }
        public String getQSoldDate() { return solddate; }
        public String getQJobNum() { return jobnum; }
        public String getQAntStart() { return antstart; }
}
