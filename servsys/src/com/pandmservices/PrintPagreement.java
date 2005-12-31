package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class PrintPagreement
{

       		 private int contnum =0; 
		private int servsync=0;
               private int eenum=0;
		private String mbody=null;
                private int ecustnum=0;
                private String brand=null;
                private String modelnum=null;
                private String serialnum=null;
                private String filter=null;
                private String enotes=null;
                private String type=null;
                private int enum1 =0;
                private int enum2 = 0;
                private int enum3 = 0;
                private int enum4 = 0;
                private int enum5 = 0;
                private int enum6 = 0;
                private int enum7 = 0;
                private int enum8 = 0;
                private int enum9 = 0;
                private int enum10 =0;
                private String aservice  = null;
                private String startdate = null;
                private String enddate = null;
                private int term = 0;
                private String cost = null;
                private String notes = null;
                private String agrdate = null;
                private int vperyear = 0;
		private String visit1=null;
		private String visit2=null;
		private String visit3=null;
		private String visit4=null;
		private String visit5=null;
		private String visit6=null;
		private String cname=null;
		private String address1=null;
		private String address2=null;
		private String city =null;
		private String state=null;
		private String zip=null;
		private String homephone=null;
		private String altphone=null;
		private String cust_notes=null;

        private int id;
        private int custnum=0;     //default value to null
    

        public PrintPagreement (Connection c, int id)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM pagreement WHERE contnum=" + id + ";");
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + id);
		}
		this.enum1=rs.getInt("enum1");
		this.enum2=rs.getInt("enum2");
		this.enum3=rs.getInt("enum3");
		this.enum4=rs.getInt("enum4");
		this.enum5=rs.getInt("enum5");
		this.enum6=rs.getInt("enum6");
		this.enum7=rs.getInt("enum7");
		this.enum8=rs.getInt("enum8");
		this.enum9=rs.getInt("enum9");
		this.enum10=rs.getInt("enum10");
		this.aservice=rs.getString("aservice");
		this.visit1=rs.getString("visit1");
		this.visit2=rs.getString("visit2");
		this.visit3=rs.getString("visit3");
		this.visit4=rs.getString("visit4");
		this.visit5=rs.getString("visit5");
		this.visit6=rs.getString("visit6");
		this.startdate=rs.getString("startdate");
		this.enddate=rs.getString("enddate");
		this.term=rs.getInt("term");
		this.cost=rs.getString("cost");
		this.notes=rs.getString("notes");
		this.agrdate=rs.getString("agrdate");
		this.vperyear=rs.getInt("vperyear");
		this.servsync=rs.getInt("servsync");
		this.custnum=rs.getInt("custnum");








	}


        public static Vector getAllItems(Connection c, int custnum)
                throws SQLException, TodoException
        {
                Vector V = new Vector();
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT contnum FROM pagreement where custnum='"+custnum+"' ORDER BY contnum;");
                while(rs.next())
                {

                        UniPagreement t = new UniPagreement(c,rs.getInt("enum"));
                        V.addElement(t);
                }
                return V;
        }

		public String getAgreement() { return mbody; }

}
