package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * MainLoginGUI is the main login panel the user will see upon logging in.
 * @author Marcus
 *
 */
public class MainLoginGUI extends JFrame implements ActionListener {
	/**
	 * Simple serial.
	 */
	private static final long serialVersionUID = 1L;
	
	//String for name of the program
	public static final String PRG_NAME = "MFH Emailer Application";

	//Main panel for components
	private JPanel pnlMain;
	
	//The middle panel is for the login buttons
	private JPanel pnlMiddle;
	
	//This top panel is for the image
	private JPanel pnlTop;
	
	//This JText area is for the welcome greeting
	//The constants specify the rows and columns
	//to format the text.
	private JTextArea txtWelcome;
	public static final int TXT_ROWS = 3;
	public static final int TXT_COLUMNS = 50;
	private static final String WELCOME_MSG = "Welcome to the MFH Emailer Application!";
	
	/**
	 * Defaults for length and width.
	 */
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	
	//Panels, labels and text for username and password
	private JPanel pnlUserInfo;
	private JPanel pnlUserPw;
	private JLabel lblPassword;
	private JLabel lblUser;
	private JPasswordField txtPassword;
	private JTextField txtUserName;
	private static final String USER = "Username: ";
	private static final String PW = "Password: ";
	
	/**
	 * The home page buttons include login and exit.
	 * This section also adds a panel for the buttons.
	 */
	private JButton btnNext;
	private JButton btnExit;
	private JPanel pnlButtons;
	
	/**
	 * These are the constants for the login and -exit buttons
	 */
	public final String NEXT = "Next";
	public final String EXIT = "Exit";
	
	/**
	 * This method is the main constructor for the emailer GUI.
	 */
	public MainLoginGUI() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(PRG_NAME);
		this.setLayout(new BorderLayout());

		setupLoginPanel();
		setupMiddlePanel();		
		setHomePageButtons();
		setButtonPanel();

		registerListeners();
		setupUserInfo();
	}
	
	
	/**
	 * This method sets up the main JPanel and adds it to the
	 * home page.
	 */
	public void setupLoginPanel() {
		pnlMain = new JPanel(new BorderLayout());
		pnlMain.setVisible(true);
		this.add(pnlMain);
	}
	
	/**
	 * This method sets up the middle panel for the login buttons.
	 */
	public void setupMiddlePanel() {
		pnlMiddle = new JPanel(new BorderLayout());
		pnlMain.add(pnlMiddle, BorderLayout.CENTER);
		validate();
	}
	
	/**
	 * This method sets up the password field in the middle.
	 */
	public void setupUserInfo() {
		pnlUserInfo = new JPanel(new FlowLayout());
	
		lblUser = new JLabel(USER);
		lblPassword = new JLabel(PW);
		pnlUserInfo.add(lblUser);
		txtUserName = new JTextField();
		pnlUserInfo.add(txtUserName);
		
		pnlMiddle.add(pnlUserInfo, BorderLayout.CENTER);
		validate();
		
	}
	/**
	 * This method instantiates the button objects
	 * for the home page.
	 */
	public void setHomePageButtons() {
		btnNext = new JButton(NEXT);
		btnExit = new JButton(EXIT);	
	}
	
	/**
	 * This method creates the panel for the buttons.
	 */
	public void setButtonPanel() {
		pnlButtons = new JPanel(new FlowLayout());
		pnlButtons.add(btnExit);
		pnlButtons.add(btnNext);
		
		pnlMiddle.add(pnlButtons, BorderLayout.SOUTH);
		validate();
		
	}
	/**
	 * This method registers listeners for the JButtons.
	 */
	public void registerListeners() {
		btnNext.addActionListener(this);
		btnExit.addActionListener(this);
	}
	
	/**
	 * Close and dispose closes the login screen if the
	 * user correctly logs in.
	 */
	public void closeAndDispose() {
		this.setVisible(false);
		this.dispose();
	}
	
	/**
	 * This method sets the actions for pressing a button on the home page.
	 * @param e an action event
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNext) {
			//Call login settings
		}
		if (e.getSource() == btnExit) {
			System.exit(0);
		}
	}
	

	/**
	 * Run the program, for testing.
	 * @param args
	 */
	public static void main(String[] args) {
			new MainLoginGUI();
	}
	
}
	


