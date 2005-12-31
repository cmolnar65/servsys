package com.pandmservices.dbserver;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;

public class SyncSvcCharges
{
        private int recnum;
        private String callslip=null;     //default value to null
        private String descript=null;     //default value to null
   	private double quant=0;
	private double price=0; 
	private double total=0; 
	private int servsync;
	private int frcode;
	private String techid=null;

        public SyncSvcCharges (Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmtu = conu.createStatement();
		Statement stmt2 = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM svc_charges WHERE servsync=0 or servsync is NULL;");
		while(rs.next())
		{
		this.recnum = rs.getInt("recnum");
		this.servsync = rs.getInt("servsync");
		this.callslip = rs.getString("callslip");
		this.descript = rs.getString("descript");
		this.techid = rs.getString("techid");
		this.quant = rs.getDouble("quant");
		this.price = rs.getDouble("price");
		this.frcode=rs.getInt("frcode");
		this.total = this.quant*this.price;

	                stmtu.executeUpdate("INSERT INTO svc_charges (callslip, descript, quant, price, techid, servsync, frcode) Values ('" + callslip + "','" + descript + "','"+quant+"','"+price+"', '"+techid+"', '"+servsync+"', '"+frcode+"')");
		stmt2.executeUpdate("Update svc_charges set servsync=2 where recnum="+recnum+";");

	}
	}

}
