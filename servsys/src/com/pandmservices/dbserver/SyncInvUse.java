package com.pandmservices.dbserver; 
import com.pandmservices.core.*;
import com.pandmservices.*;
import com.pandmservices.web.*;
import java.sql.*;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.Vector;
import java.util.Date;
import java.security.*;


public class SyncInvUse
{
        private int transnum;
	private int recnum;
	private String tdate;
	private String callslip;
	private String notes;
	private String quantity;
	private String itemname;
	private String keycode;
	private String descript;
	private String techid;
	private int servsync;

        public SyncInvUse (Connection con, Connection conu)
		throws SQLException, TodoException
	{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
	ResultSet rs = stmt.executeQuery("select * from inv_use where servsync=0 or servsync is NULL;");
	
		 while (rs.next())
		{
		this.quantity = rs.getString("quant");
		this.callslip = rs.getString("callslip");
		this.tdate = rs.getString("date");
		this.notes = rs.getString("notes");
		this.itemname = rs.getString("descript");
		this.keycode = rs.getString("keycode");
		this.descript = rs.getString("descript");
		this.recnum = rs.getInt("recnum");
		this.techid = rs.getString("techid");
		this.servsync = rs.getInt("servsync");

				String tnotes="";
                                if (notes!=null) {
                                tnotes = notes.replaceAll("'","''");
                              }
				String tdescript="";
                                if (descript!=null) {
                                tdescript = descript.replaceAll("'","''");
                              }

			Statement stmtu = conu.createStatement();
	                stmtu.executeUpdate("INSERT INTO inv_use ( date, callslip, keycode, quant, descript, notes, techid, servsync) Values ('" +tdate+ "','"+callslip+"','"+keycode+"','"+quantity+"','"+descript+"','"+tnotes+"', '"+techid+"','"+servsync+"')");
//	                System.out.println("INSERT INTO inv_use ( date, callslip, keycode, quant, descript, notes, techid, servsync) Values ('" +tdate+ "','"+callslip+"','"+keycode+"','"+quantity+"','"+descript+"','"+notes+"', '"+techid+"','"+servsync+"')\n");
// NOW TURN ON SYNCED FLAG FOR ORIGIONAL CUSTOMER
                stmt2.executeUpdate("Update inv_use Set servsync=2 Where recnum='" + recnum + "';");

		        }

	}
}
