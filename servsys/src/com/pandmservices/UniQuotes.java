package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class UniQuotes
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
	private String custsite=null;
	private String sitenum=null;
	private String techid=null;
	private int servsync;
    

        public UniQuotes (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM quotes WHERE quotenum=" + id + ";");
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
		this.custsite=rs.getString("custsite");
		this.sitenum=rs.getString("sitenum");
		this.techid=rs.getString("techid");
		this.servsync=rs.getInt("servsync");
	}



	public static Vector getAllItems(Connection c, int crecnum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quotenum  FROM quotes where crecnum='"+crecnum+"' ORDER BY qdate;");
		while(rs.next())
		{
			
			UniQuotes t = new UniQuotes(c,rs.getInt("quotenum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getMaxItem(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quotenum,max(quotenum)  FROM quotes GROUP BY quotenum;");
		while(rs.next())
		{
			
			UniQuotes t = new UniQuotes(c,rs.getInt("quotenum"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllDateItems(Connection c, String qdate)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quotenum  FROM quotes where qdate='"+qdate+"' ORDER BY qdate;");
		while(rs.next())
		{
			
			UniQuotes t = new UniQuotes(c,rs.getInt("quotenum"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getIndItem(Connection c, int crecnum, int quotenum)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT quotenum  FROM quotes where crecnum='"+crecnum+"' and quotenum="+quotenum+"  ORDER BY qdate;");
		while(rs.next())
		{
			
			UniQuotes t = new UniQuotes(c,rs.getInt("quotenum"));
			V.addElement(t);
		}
		return V;
	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From quotes Where quotenum=" + x + ";");
		stmt.executeUpdate("Delete From quote_parts Where quotenum=" + x + ";");
	}
	  

	public static void UpdateItem(Connection con, int quotenum, int crecnum, String qdate, String qdescription, String qpayterms, String qnotes,String qdisc, String qadditionalserv, String qstatus, String solddate, String jobnum,String antstart )
		throws SQLException
	{
		Statement stmt = con.createStatement();
	String tqnotes = qnotes.replaceAll("'","''");
      		stmt.executeUpdate("Update quotes Set crecnum='" +crecnum+ "',qdate = '"+qdate+"',qdescription = '"+qdescription +"',qpayterms = '"+qpayterms +"',qnotes = '"+tqnotes +"', qdisc = '"+qdisc+"', qadditionalserv='"+qadditionalserv+"', status='"+qstatus+"', solddate='"+solddate+"', jobnum='"+jobnum+"', antstart='"+antstart+"', servsync='9' Where quotenum=" + quotenum + ";");
      	}

public static void AddItem(Connection con, int crecnum, String qdate, String qdescription, String qpayterms, String qnotes, String qdisc, String qadditionalserv, String qstatus, String solddate, String antstart, String custsite, String sitenum, String techid, int servsync)
                throws SQLException
		        {
	       	        Statement stmt = con.createStatement();
			String tqnotes = qnotes.replaceAll("'","''");
	                stmt.executeUpdate("INSERT INTO quotes (crecnum, qdate, qdescription, qpayterms, qnotes, qdisc, qadditionalserv, status, solddate, antstart, custsite, sitenum, techid, servsync) Values ('"+crecnum+"','" +qdate+ "','" +qdescription+ "','"+qpayterms+"','"+tqnotes+"', '"+qdisc+"','"+qadditionalserv+"', '"+qstatus+"', '"+solddate+"', '"+antstart+"', '"+custsite +"','"+sitenum +"','"+techid +"', '"+servsync+"' )");
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
	public String getCustSite() { return custsite; }
	public String getSiteNum() { return sitenum; }
	public String getTechId() { return techid; }
	public int getServSync() { return servsync; }
}
