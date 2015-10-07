package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
	public static final String PRG_NAME = "MFH Emailer";

	//Main panel for components
	private JPanel pnlMain;
	
	//The middle panel is for the login buttons
	private JPanel pnlMiddle;
	
	//This top panel is for the image
	private JPanel pnlTop;
	
	//This JText area is for the welcome greeting
	//The constants specify the rows and columns
	//to format the text.
	private JLabel lblWelcome;
	public static final int TXT_ROWS = 3;
	public static final int TXT_COLUMNS = 50;
	private static final String WELCOME_MSG = "Welcome to the MFH Emailer Application!";
	
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
		this.setTitle(PRG_NAME);
		this.setLayout(new BorderLayout());
		setupMainLoginPanel();
		setupMiddlePanel();
		setHomePageButtons();
		addButtonsToHomePage();
		registerListeners();
		setupTopPanel();
		setIntroMessage();
		
		pack();
		
		setLocationRelativeTo(null);
	
	}
	
	/**
	 * This method sets up the introductory message/greeting.
	 */
	public void setIntroMessage() {
		
		//Configure text
		lblWelcome = new JLabel();
		lblWelcome.setText(WELCOME_MSG);
		lblWelcome.setBackground(this.getBackground());
		lblWelcome.setHorizontalAlignment(JLabel.CENTER);
		
		//Add text to main panel
		this.add(lblWelcome);
		
		validate();
	}
	
	
	/**
	 * This method sets up the main JPanel and adds it to the
	 * home page.
	 */
	public void setupMainLoginPanel() {
		pnlMain = new JPanel();
		pnlMain.setVisible(true);
		pnlMain.setLayout(new BorderLayout());
		this.add(pnlMain, BorderLayout.SOUTH);
	}
	
	/**
	 * This method sets up the middle panel for the login buttons.
	 */
	public void setupMiddlePanel() {
		pnlMiddle = new JPanel(new FlowLayout());
		pnlMain.add(pnlMiddle);
	}
	
	/**
	 * This method sets up the top panel with the illustration.
	 */
	public void setupTopPanel() {
		pnlTop = new JPanel();
		BufferedImage logo = null;
		try {
			logo = ImageIO.read(new File("C:/Users/Marcus/Desktop/mfhLogo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(logo));
		pnlTop.add(picLabel);
		this.add(pnlTop, BorderLayout.NORTH);
		validate();
		
		
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
		pnlMiddle.add(btnExit);
		pnlMiddle.add(btnLogin);
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
	


