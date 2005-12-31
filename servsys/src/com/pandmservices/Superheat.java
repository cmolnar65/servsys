package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class Superheat 
{
        private String superheat="0.00";     //default value to null
        private String returnwb="0.00";     //default value to null
        private String outsideair="0.00";     //default value to null
        private boolean done = false;      //default value to false


	public Superheat(Connection c, String outsideair, String returnwb)
		throws SQLException, TodoException
	{	
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT superheat, outsideair, returnwb FROM superheat where outsideair='"+outsideair+"' and returnwb='"+returnwb+"';");
		while(rs.next())
		{
			
		this.superheat = rs.getString("superheat");
		this.outsideair = rs.getString("outsideair");
		this.returnwb = rs.getString("returnwb");

		}

	}



        public String getReturnTempWb() { return returnwb; }
        public String getReturnTemOa() { return outsideair; }
        public String getSuperheat() { return superheat; }

}
