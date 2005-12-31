package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class TempSplit
{
        private String tempsplit="0.00";     //default value to null
        private String returnwb="0.00";     //default value to null
        private String returndb="0.00";     //default value to null
        private boolean done = false;      //default value to false


	public TempSplit(Connection c, String returndb, String returnwb)
		throws SQLException, TodoException
	{	
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ts FROM tempsplit where returndb='"+returndb+"' and returnwb='"+returnwb+"';");
		while(rs.next())
		{
			
		this.tempsplit = rs.getString("ts");

		}

	}



        public String getReturnTempWb() { return returnwb; }
        public String getReturnTempDb() { return returndb; }
        public String getTempSplit() { return tempsplit; }

}
