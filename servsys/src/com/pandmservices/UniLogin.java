package com.pandmservices;
import java.sql.*;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.lang.*;
import java.util.*;
import java.util.Vector;
import java.security.*;

public class UniLogin
{
        private int id;
        private String description="";     //default value to null
        private String idcode="";     //default value to null
        private String passwd="";     //default value to null
        private String role="";     //default value to null
        private String ulogin="";     //default value to null
        private boolean done = false;      //default value to false
    

        public UniLogin(Connection c, String idcode, String tt)
		throws SQLException, TodoException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE idcode='" + idcode + "';");
		if (!rs.next())
		{
			throw new TodoException("Record not found, idcode = " + idcode);
		}
		this.idcode = rs.getString("idcode");
		this.role = rs.getString("role");
		this.passwd = rs.getString("passwd");
		this.ulogin = rs.getString("login");
	}


        public UniLogin(Connection c, String login)
		throws SQLException, TodoException, NoSuchAlgorithmException, NoSuchProviderException
	{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE login='" + login + "';");
		if (!rs.next())
		{
			//throw new TodoException("Record not found, login = " + login);
			this.role = "denied";
			this.idcode="xxx";
			this.passwd="--";
			return;
		}
		this.role = rs.getString("role");
		this.idcode = rs.getString("idcode");
		this.passwd = rs.getString("passwd");
	}


	public static Vector getAllItems(Connection c)
		throws SQLException, TodoException
	{	
		Vector V = new Vector();
		String tt="fake";
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT idcode FROM users ORDER BY idcode;");
		while(rs.next())
		{
			
			UniLogin t = new UniLogin(c,rs.getString("idcode"), tt);
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
	  

	public static void UpdateItem(Connection con, int id, String desc)
		throws SQLException
	{
		Statement stmt = con.createStatement();
      		stmt.executeUpdate("Update todo Set description='" +desc+ "' Where ID=" + id + ";");
      	}

	public static void updateUser(Connection con, String uinit, String ulogin, String role, String upasswd)
		throws SQLException
	{
		Statement stmt = con.createStatement();
	      	stmt.executeUpdate("update users set idcode='"+uinit+"', passwd='"+upasswd+"', role='" + role + "' where login='" + ulogin + "'");
      	}


	public static void addUser(Connection con, String uinit, String ulogin, String role, String upasswd)
		throws SQLException
	{
		Statement stmt = con.createStatement();
	      	stmt.executeUpdate("INSERT INTO users (login, passwd, role, idcode) Values ('" + ulogin + "','"+upasswd+"','" + role + "','"+uinit+"')");
      	}


        public int getId() { return id; }
        public String getRole() { return role; }
        public String getPasswd() { return passwd; }
        public String getULogin() { return ulogin; }
        public String getIdCode() { return idcode; }

        public void setId(int id) { this.id = id; }
}
