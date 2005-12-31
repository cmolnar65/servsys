package com.pandmservices.support;
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class SupJobStatus
{
	private String answer;

        public SupJobStatus (Connection c, String answer)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from status where answer='"+answer+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + answer);
		}
		this.answer = rs.getString("answer");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM status order by answer");
		while(rs.next())
		{
			
			SupJobStatus t = new SupJobStatus(c,rs.getString("answer"));
			V.addElement(t);
		}
		return V;
	}
	
	
        public String getAnswer() { return answer; }

}
