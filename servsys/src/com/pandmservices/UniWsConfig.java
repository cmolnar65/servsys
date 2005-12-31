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


public class UniWsConfig
{
	private double laborcost;
	private double partmult;
	private double markupdiviser;
	private double gptolabor;
	private double crewbillable;
	private double labmult;
	private String propprice;

        public UniWsConfig (Connection c, double laborcost)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from wsconfig where laborcost="+laborcost+";");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + laborcost);
		}
		this.laborcost=rs.getDouble("laborcost");
		this.partmult= rs.getDouble("partmult");
		this.markupdiviser=rs.getDouble("markupdiviser");
		this.gptolabor=rs.getDouble("gptolabor");
		this.crewbillable=rs.getDouble("crewbillable");
		this.labmult=rs.getDouble("labmult");
		this.propprice=rs.getString("propprice");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM wsconfig");
		while(rs.next())
		{
			
			UniWsConfig t = new UniWsConfig(c,rs.getDouble("laborcost"));
			V.addElement(t);
		}
		return V;
	}


	public static void UpdateItem(Connection con, String laborcost, String partmult, String markupdiviser, String gptolabor, String propprice, String crewbillable, String labmult)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update wsconfig Set laborcost=" +laborcost+ " ,partmult="+partmult+" , gptolabor="+gptolabor+", markupdiviser="+markupdiviser+", propprice='"+propprice+"', crewbillable='"+crewbillable+"', labmult='"+labmult+"';");
      	}


        public double getPartMult() { return partmult; }
        public double getLabMult() { return labmult; }
        public double getLaborCost() { return laborcost; }
        public double getGpToLabor() { return gptolabor; }
        public double getCrewBillable() { return crewbillable; }
	public String getPropPrice() { return propprice; }
        public double getMarkUpDiviser() { return markupdiviser; }
}
