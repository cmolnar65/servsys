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

public class servsys implements ActionListener {

			static JFrame loginFrame;
			JPanel loginPanel;
			JTextField loginID;
			JPasswordField loginPassword;
			JButton loginOK;
			JButton exitNOW;
			JLabel IDLabel, PasswordLabel;
			static Connection con = null;
			static String dbserver=null;
			static String dbuser=null;
			static String dbpassword=null;
			static Properties properties;
			static String propertiesDescription = "servsys.properties";
			
	public servsys() {
		loginFrame = new JFrame("Servsys Login");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(new Dimension(120, 40));

		loginPanel = new JPanel(new GridLayout(3,2));

		addWidgets();

		loginFrame.getRootPane().setDefaultButton(loginOK);

		loginFrame.getContentPane().add(loginPanel, BorderLayout.CENTER);

		loginFrame.pack();
		loginFrame.setVisible(true);
	}


	private static void createAndShowGUI() 
			{
				try {
				OpenConnection(); } catch (java.lang.Exception e) {
								System.out.println("error on opening connection\n");
							}
				JFrame.setDefaultLookAndFeelDecorated(true);
				servsys servsyslogin = new servsys();
			}

	private void addWidgets()
			{
				loginID = new JTextField(12);
				loginPassword = new JPasswordField(12);
				loginPassword.setEchoChar('*');
				
				IDLabel = new JLabel("User ID", SwingConstants.LEFT);
				PasswordLabel = new JLabel ("Password",SwingConstants.LEFT);

				loginOK = new JButton("Login");
				exitNOW = new JButton("Quit");

				loginOK.addActionListener(this);
				exitNOW.addActionListener(this);
				
				loginPanel.add(loginID);
				loginPanel.add(IDLabel);
				loginPanel.add(loginPassword);
				loginPanel.add(PasswordLabel);
				loginPanel.add(loginOK);
				loginPanel.add(exitNOW);

				IDLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
				PasswordLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			}
	

	private int doCheckUserLogin(String username, String password)
                throws Exception
        {
        UniLogin t = new UniLogin(con, username);
        String dbpasswd = t.getPasswd();
        String dbIdCode = t.getIdCode();
        String dbRole = t.getRole();
        if (dbpasswd.equals(password))
                                       {
                                      System.out.println("<p>Authentication APPROVED");
				      return 1;
                                      }
                               else {
                                      //System.out.println("<p>Authentication DENIED");
				      JOptionPane.showMessageDialog(loginFrame, "Authentication Denied");
                                     return 0;
                                  }
                        }

	public void actionPerformed(ActionEvent event)
			{
		int x=0;
				if (event.getSource() == loginOK )
						{
						String password = loginPassword.getText();
						String username = loginID.getText();
						try {
						x = doCheckUserLogin(username, password);
							} catch (java.lang.Exception e) {
								System.out.println("error on user check\n");
							}
						if (x==1) {
							try {
								ServsysMain servsysmainwindow = new ServsysMain();
								servsysmainwindow.setVisible(true);
								loginFrame.setVisible(false);
						       			} catch (java.lang.Exception e) {
									System.out.println("error on opening window\n"+e.getMessage()+"\n");
								}
							
						}
						}
				if (event.getSource()== exitNOW)
						{
						try {
						con.close();
						System.exit(0); } catch (java.lang.Exception e) {
							System.out.println("error on exit\n");
						}
						}
			}
	protected static void setDefaults(Properties defaults) {
		defaults.put( "dbuser","-");
		defaults.put("dbserver","-");
		defaults.put("dbpassword","-");
			}

	protected static void getParameters(String filename)
			{
				Properties defaults = new Properties();
				FileInputStream in = null;
				setDefaults(defaults);

				properties= new Properties(defaults);
				try {
					
				in = new FileInputStream(filename);
				properties.load(in);
				
				} catch (java.io.FileNotFoundException e) {
					in = null;
					System.out.println("File Not Found \n using defaults\n ");
				} catch (java.io.IOException e) {
					System.out.println("Can't read properties file\nUsing Defaults\n");		
				} finally {
					if (in!=null) {
						try { in.close(); } catch (java.io.IOException e) { }
						in=null;
					}
				}
			}
	
	protected static void saveParameters(String filename)
			{
				FileOutputStream out = null;
				
				try {
					out = new FileOutputStream(filename);
					properties.save(out, propertiesDescription);
					} catch (java.io.IOException e) {
					out =null;
					System.out.println("File Not Found \n No big deal\n ");
				} finally {
					if ( out!=null) {
						try { out.close(); } catch (java.io.IOException e) { }
						out = null;
					}
				}
	
			}
	public static void OpenConnection()
			throws Exception
			{
			Class.forName(properties.getProperty("dbdriver"));
			con = DriverManager.getConnection(properties.getProperty("dbprotocol")+":"+properties.getProperty("dbsubprotocol")+"://"+properties.getProperty("dbhost")+"/"+properties.getProperty("dbname")+"?autoReconnect=true", properties.getProperty("dbuser"), properties.getProperty("dbpassword"));
			}

    	public static void main (String args[]) 
		throws Exception
			{
				getParameters(args[0]);
				saveParameters(args[0]);

				javax.swing.SwingUtilities.invokeLater (new Runnable() { public void run() {
					createAndShowGUI();
					}
				});

		    	}

}
