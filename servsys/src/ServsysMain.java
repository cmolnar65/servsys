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
		private final int ITEM_PLAIN = 0;
		private final int ITEM_CHECK = 1;
		private final int ITEM_RADIO = 2;

		private JPanel topPanel;
		private JMenuBar menuBar;
		
		private JMenu menuFile;
		private JMenuItem menuQuit;

		private JMenu menuTime;
		private JMenuItem menuTimeSheet;
		private JMenuItem menuTimeTransmit;
		
		private JMenu menuCustomer;
		private JMenu menuReport;
		private JMenu menuUpdate;
		private JMenu menuHelp;
		private JMenuItem menuAbout;
	
		public ServsysMain()
		{
			setTitle("Servsys Main Window");
			setSize(1124, 810);
			topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			getContentPane().add(topPanel);
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			menuFile=new JMenu("File");
			menuBar.add(menuFile);
			menuFile.addSeparator();
			menuQuit = CreateMenuItem( menuFile, ITEM_PLAIN, "Quit", "Quit Program");
			menuTime = new JMenu("Time");
			menuBar.add(menuTime);
			menuTime.addSeparator();
			menuTimeSheet = CreateMenuItem(menuTime, ITEM_PLAIN, "Time Sheet", "Edit Time Sheet");
			menuTimeTransmit = CreateMenuItem(menuTime, ITEM_PLAIN, "Transmit", "Transmit Daily Reports");
			
			
			menuCustomer = new JMenu("Customer");
			menuBar.add(menuCustomer);
			
			menuReport = new JMenu("Reports");
			menuBar.add(menuReport);
			
			menuUpdate = new JMenu("Update");
			menuBar.add(menuUpdate);
			
			menuHelp = new JMenu("Help");
			menuBar.add(menuHelp);				
			menuHelp.addSeparator();
			menuAbout = CreateMenuItem(menuHelp, ITEM_PLAIN, "About", "Show About Box");

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
			
				System.out.println(event);
				if (event.getSource() == menuQuit )
				{

				servsys.loginFrame.setVisible(true);
				setVisible(false);
				} else if (event.getSource() == menuAbout )
				{
				try {
				AboutBox aboutwindow = new AboutBox();
				aboutwindow.setVisible(true);
			    			} catch (java.lang.Exception e) {
						System.out.println("error on opening window\n"+e.getMessage()+"\n");
								}
				}

			}
			
	
	public JMenuItem CreateMenuItem( JMenu menu, int iType, String sText, String sToolTip)
	{
		JMenuItem menuItem;
		switch (iType)
		{
			case ITEM_RADIO:
				menuItem = new JRadioButtonMenuItem();
				break;
			case ITEM_CHECK:
				menuItem = new JCheckBoxMenuItem();
				break;
			default:
				menuItem = new JMenuItem();
				break;
		}
		menuItem.setText( sText);
		if (sToolTip!=null) menuItem.setToolTipText(sToolTip);
		menuItem.addActionListener(this);
		menu.add(menuItem);
		return menuItem;
	}



	}
