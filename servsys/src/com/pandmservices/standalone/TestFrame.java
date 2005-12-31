package com.pandmservices;
import com.pandmservices.core.*;
import com.pandmservices.web.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.sql.*;

class TestFrame
	extends JFrame
{
	private JPanel topPanel;
	private JTree tree;
	private JScrollPane scrollPane;

	public TestFrame()
	{
		setTitle ("Advanced Tree Application");
		setSize(300,100);

		topPanel=new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add(topPanel);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Customers");

		DefaultMutableTreeNode itemA = new DefaultMutableTreeNode("A");
		root.add (itemA);
		
		DefaultMutableTreeNode itemB = new DefaultMutableTreeNode("B");
		root.add (itemB);
		
		DefaultMutableTreeNode itemC = new DefaultMutableTreeNode("C");
		root.add (itemC);
		
		DefaultTreeModel treeModel = new DefaultTreeModel (root);
		tree = new JTree (treeModel);

		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(tree);
		topPanel.add(scrollPane, BorderLayout.CENTER);

		this.addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				});
	}

	public void addCustomer ( DefaultMutableTreeNode begins )
	{
		


	}
		public static void main(String args[])
			throws SQLException
		{
			Connection con=null;
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/memco", "root", "uffda");

			TestFrame mainFrame=new TestFrame();
			mainFrame.setVisible(true);
		}
}
