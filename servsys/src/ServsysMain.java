//
//  ServsysMain.java
//  servsys
//
//  Created by Christopher Molnar on 3/5/06.
//  Copyright 2006 __MyCompanyName__. All rights reserved.
//
import java.util.*;
import com.pandmservices.*;
import com.pandmservices.core.*;
import com.pandmservices.support.*;
import com.pandmservices.dbserver.*;
import com.pandmservices.web.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Vector;
import java.text.*;
import java.lang.*;
import java.lang.Integer;
import java.util.Properties;
import java.lang.Math.*;
import java.lang.String;
import java.lang.Float;
import java.lang.Double;
import java.math.*;
import java.util.Date;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ServsysMain extends JFrame implements ActionListener 
	{
		private JPanel topPanel;
		private JMenuBar menuBar;
		private JMenu menuTime;
		private JMenu menuCustomer;
		private JMenu menuReport;
		private JMenu menuUpdate;
		private JMenu menuHelp;
	
		public ServsysMain()
		{
			setTitle("Servsys Main Window");
			setSize(1124, 810);
			
			topPanel = new JPanel();
			
			topPanel.setLayout(new BorderLayout());
			getContentPane().add(topPanel);
			
			menuBar = new JMenuBar();
			
			setJMenuBar(menuBar);
			
			menuTime = new JMenu("Time");
			menuBar.add(menuTime);
			
			menuCustomer = new JMenu("Customer");
			menuBar.add(menuCustomer);
			
			menuReport = new JMenu("Reports");
			menuBar.add(menuReport);
			
			menuUpdate = new JMenu("Update");
			menuBar.add(menuUpdate);
			
			menuHelp = new JMenu("Help");
			menuBar.add(menuHelp);				

		this.addWindowListener (new WindowAdapter()
								{
						public void windowClosing (WindowEvent e)
								{
								servsys.loginFrame.setVisible(true);
							// place holder
								}	
								}); // end window listening
		
		}

	public void actionPerformed(ActionEvent event)
			{

			}
			
	

	}
