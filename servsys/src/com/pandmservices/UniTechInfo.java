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


public class UniTechInfo
{
	private String lusername;
	private String tech_name;
	private String tech_init;
	private String truck_num;
	private String nate_id;
	private String allow_delete;
	private String mod_install;
		private String mod_airbal;
		private String mod_servadmin;
		private String serv_update;
		private String create_worksheet;
		private String edit_compphonelist;
		private String servsync;
		private String transmit;
		private String department;

        public UniTechInfo (Connection c, String tech_init)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery("select * from tech_table where techinit='"+tech_init+"';");
	
		if (!rs.next())
		{
			throw new TodoException("Record not found, id = " + tech_init);
		}
		this.tech_init =rs.getString("techinit");
		this.lusername =rs.getString("username");
		this.tech_name = rs.getString("tech_name");
		this.truck_num=rs.getString("truck_num");
		this.nate_id=rs.getString("nate_id");
		this.allow_delete=rs.getString("allow_delete");
		this.mod_install=rs.getString("mod_install");
		this.mod_airbal=rs.getString("mod_airbal");
		this.mod_servadmin=rs.getString("mod_servadmin");
		this.serv_update=rs.getString("serv_update");
		this.create_worksheet=rs.getString("create_worksheet");
		this.edit_compphonelist=rs.getString("edit_compphonelist");
		this.servsync=rs.getString("servsync");
		this.transmit=rs.getString("transmit");
		this.department=rs.getString("department");
	}


	public static Vector getAllItems(Connection c, String lusername)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM tech_table where username='"+lusername+"';");
		while(rs.next())
		{
			
			UniTechInfo t = new UniTechInfo(c,rs.getString("techinit"));
			V.addElement(t);
		}
		return V;
	}

	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM tech_table");
		while(rs.next())
		{
			
			UniTechInfo t = new UniTechInfo(c,rs.getString("techinit"));
			V.addElement(t);
		}
		return V;
	}


	public static Vector getAllTransmitItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM tech_table where transmit=1 order by username");
		while(rs.next())
		{
			
			UniTechInfo t = new UniTechInfo(c,rs.getString("techinit"));
			V.addElement(t);
		}
		return V;
	}

	public static void deleteItem(Connection con, String d)
		throws SQLException
	{
		int x = Integer.parseInt(d);
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Delete From Todo Where ID=" + x + ";");
	}
	  

	public static void AddItem(Connection con, String tech_name, String tech_init, String truck_num, String nate_id, String allow_delete, String mod_airbal, String mod_install, String mod_servadmin, String serv_update, String create_worksheet, String edit_compphonelist, String servsync, String lusername, String transmit, String department)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("INSERT INTO tech_table (tech_name, techinit, truck_num, nate_id, allow_delete, mod_install, mod_airbal, mod_servadmin, serv_update, create_worksheet, edit_compphonelist, servsync, username, transmit, department) values ('" +tech_name+ "' ,'"+tech_init+"' ,'"+truck_num+"','"+nate_id+"','"+allow_delete+"','"+mod_install+"','"+mod_airbal+"','"+mod_servadmin+"','"+serv_update+"','"+create_worksheet+"','"+edit_compphonelist+"','"+servsync+"','"+lusername+"','"+transmit+"','"+department+"');");
      	}

	public static void UpdateItem(Connection con, String tech_name, String tech_init, String truck_num, String nate_id, String allow_delete, String mod_airbal, String mod_install, String mod_servadmin, String serv_update, String create_worksheet, String edit_compphonelist, String servsync, String lusername, String transmit, String department)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update tech_table Set tech_name='" +tech_name+ "' ,techinit='"+tech_init+"' , truck_num='"+truck_num+"', nate_id='"+nate_id+"', allow_delete='"+allow_delete+"', mod_install='"+mod_install+"', mod_airbal='"+mod_airbal+"', mod_servadmin='"+mod_servadmin+"', serv_update='"+serv_update+"', create_worksheet='"+create_worksheet+"', edit_compphonelist='"+edit_compphonelist+"', servsync='"+servsync+"', transmit='"+transmit+"', department='"+department+"' where username like '"+lusername+"';");
      	}

        public String getTechInit() { return tech_init; }
        public String getUserName() { return lusername; }
	public String getTransmit() { return transmit; }
	public String getDepartment() { return department; }

        public String getTechName() { return tech_name; }
        public String getTruckNum() { return truck_num; }
        public String getNateID() { return nate_id; }
		public String getAllowDelete() { return allow_delete; }
		public String getModServAdmin() { return mod_servadmin; }
		public String getModAirBal() { return mod_airbal; }
		public String getModInstall() { return mod_install; }
		public String getServUpdate() { return serv_update; }
	public String getCreateWorksheet() { return create_worksheet; }
	public String getEditCompPhoneList() { return edit_compphonelist; }
	public String getServSync() { return servsync; }
}
