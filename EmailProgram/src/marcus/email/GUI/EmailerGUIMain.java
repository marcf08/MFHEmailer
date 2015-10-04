package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.*;
import java.io.IOException;


/**
 * Emailer GUI is a simple GUI to make the emailer program simple.
 * @author Marcus
 *
 */
public class EmailerGUIMain extends JFrame implements ActionListener {
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
	/**
	 * Defaults for length and width.
	 */
	public static final int WIDTH = 600;
	public static final int HEIGHT = 400;
	
	/**
	 * The home page buttons include login and exit.
	 */
	private JButton btnLogin;
	private JButton btnExit;
	
	/**
	 * These are the constants for the login and -exit buttons
	 */
	public final String LOGIN = "Login";
	public final String EXIT = "Exit";
	
	/**
	 * This method is the main constructor for the emailer GUI.
	 */
	public EmailerGUIMain() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(PRG_NAME);
		setupMainLoginPanel();
		setupMiddlePanel();
		setHomePageButtons();
		addButtonsToHomePage();
		registerListeners();
	
	}
	
	/**
	 * This method sets up the main JPanel and adds it to the
	 * home page.
	 */
	public void setupMainLoginPanel() {
		pnlMain = new JPanel();
		pnlMain.setVisible(true);
		pnlMain.setLayout(new BorderLayout());
		pnlMain.setVisible(true);
		this.add(pnlMain);
	}
	
	/**
	 * This method sets up the middle panel for the login buttons.
	 */
	public void setupMiddlePanel() {
		pnlMiddle = new JPanel(new FlowLayout());
		pnlMain.add(pnlMiddle, BorderLayout.CENTER);
	}
	/**
	 * This method instantiates the button objects
	 * for the home page.
	 */
	public void setHomePageButtons() {
		btnLogin = new JButton(LOGIN);
		btnExit = new JButton(EXIT);
		
	}
	
	/**
	 * This method registers listeners for the JButtons.
	 */
	public void registerListeners() {
		btnLogin.addActionListener(this);
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
		if (e.getSource() == btnLogin) {
			try {
				new SetupGUI();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			closeAndDispose();
			
		}
		if (e.getSource() == btnExit) {
			System.exit(0);
		}
	}
	
	/**
	 * This method adds the exit and login buttons to the 
	 * main page.
	 */
	public void addButtonsToHomePage() {
		pnlMiddle.add(btnLogin);
		pnlMiddle.add(btnExit);
		validate();
	}
	
	/**
	 * Run the program.
	 * @param args
	 */
	public static void main(String[] args) {
			new EmailerGUIMain();
	}


	
		
}
	


