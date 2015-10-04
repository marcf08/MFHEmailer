package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import marcus.email.exceptions.EmailException;
import marcus.email.exceptions.PasswordException;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


/**
 * The admin settings class will display if the user opens the window.
 * @author Marcus
 *
 */
public class SetupGUI extends JFrame implements ActionListener {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	//To maintain MVC, the verify data member checks the passwords and emails
	//to see if they match.
	private VerifyGUI verify;

	//The logic data member writes the password hash to the config file
	private GUILogic logic;

	//This is the main window for components
	private JPanel pnlMain;
	
	//Length and width constants
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	
	//The middle panel is for the password and email information
	private JPanel pnlMiddle;

	//The text area panel keeps the text area bounded
	private JPanel pnlTextArea;
	
	//JPanels for passwords panels and password labels/prompts
	private JPanel pnlPassword;
	private JPanel pnlPasswordInitial;
	private JPanel pnlPasswordConfirm;
	private JLabel lblInitial;
	private JLabel lblConfirm;
	public static final String INITIAL = "Enter Password: ";
	public static final String CONFIRM = "Confirm Password: ";
	private JPasswordField txtPasswordInitial;
	private JPasswordField txtPasswordConfirm;
	private static final int PW_LENGTH = 25;
	
	//JPanels for the email panels and email labels/prompts
	private JPanel pnlEmail;
	private JPanel pnlEmailInitial;
	private JPanel pnlEmailConfirm;
	private JLabel lblEInitial;
	private JLabel lblEConfirm;
	public static final String INITIAL_EMAIL = "Enter Email: ";
	public static final String CONFIRM_EMAIL = "Confirm Email: ";
	private JTextField txtEmailInitial;
	private JTextField txtEmailConfirm;
	
	//These strings prompt the user for unmatched passwords/emails
	private static final String N_PW_MATCH = "Passwords do not match. Try again.";
	private static final String N_EM_MATCH = "Email fields do not match. Try again.";
	
	
		
	//Next and exit buttons for doing through setup
	//Also, contains string constants for their labels
	private JButton btnExit;
	private JButton btnNext;
	private static final String NEXT = "Next";
	private static final String EXIT = "Exit";
	
	//The lower button strip is for the navigation buttons
	private JPanel pnlButtonStrip;
	
	//Introductory message/prompt and config settings
	private JTextArea txtIntroMessage;
	public static final int TXT_ROWS = 3;
	public static final int TXT_COLUMNS = 50;
	private static final String MSG_PART1 = "It looks like this is the first time launching the Emailer application.";
	private static final String MSG_PART2 = "Please set an administrative password and email.";

	/**
	 * The constructor opens the admin settings window. It throws exceptions
	 * for now until try/catch is fully implemented. We need the stack trace for
	 * debugging.
	 * @throws IOException for now until try/catch are implemented 
	 */
	public SetupGUI() throws IOException {
		verify = new VerifyGUI();
		logic = new GUILogic();
		pnlMain = new JPanel();
		pnlMain.setLayout(new BorderLayout());
		pnlMain.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(pnlMain);
		this.setVisible(true);
		this.setSize(WIDTH, HEIGHT);
				
		setIntroMessage();
		setupMiddlePanel();
		setupPasswordPanel();
		setupEmailPanel();
		
		setupLowerPanel();
		registerListeners();
	}
	
	/**
	 * The set intro method simply sets the introductory message.
	 * It also adds it to the main panel.
	 */
	public void setIntroMessage() {
		pnlTextArea = new JPanel();
		
		//Configure text
		txtIntroMessage = new JTextArea(TXT_ROWS, TXT_COLUMNS);
		txtIntroMessage.setWrapStyleWord(true);
		txtIntroMessage.setText(MSG_PART1 + "\n");
		txtIntroMessage.append(MSG_PART2);
		txtIntroMessage.setEditable(false);
		txtIntroMessage.setBackground(pnlTextArea.getBackground());
		
		//Add text to panel
		pnlTextArea.add(txtIntroMessage);
		pnlMain.add(pnlTextArea, BorderLayout.NORTH);
		validate();
	}
	

	/**
	 * This method creates the middle panel for the password fields.
	 */
	public void setupPasswordPanel() {
		//Instantiate the labels
		lblInitial = new JLabel(INITIAL);
		lblConfirm = new JLabel(CONFIRM);

		txtPasswordInitial = new JPasswordField(PW_LENGTH);
		txtPasswordConfirm = new JPasswordField(PW_LENGTH);
			
		//Set the panel for passwords
		pnlPassword = new JPanel(new BorderLayout());
		
		//Instantiate the separate JPanels for JPanels
		pnlPasswordInitial = new JPanel(new FlowLayout());
		pnlPasswordConfirm = new JPanel(new FlowLayout());
		
		pnlPasswordInitial.add(lblInitial);
		pnlPasswordInitial.add(txtPasswordInitial);
		txtPasswordInitial.setEditable(true);
		
		pnlPasswordConfirm.add(lblConfirm);
		pnlPasswordConfirm.add(txtPasswordConfirm);
		txtPasswordConfirm.setEditable(true);
		
		pnlPassword.add(pnlPasswordInitial, BorderLayout.NORTH);
		pnlPassword.add(pnlPasswordConfirm, BorderLayout.CENTER);
		
		pnlMiddle.add(pnlPassword, BorderLayout.NORTH);
		
		validate();
	}
	
	/**
	 * The email panel sets up the email confirmation in the exact
	 * same manner as the password fields. 
	 * 
	 */
	public void setupEmailPanel() {
		//Instantiate the labels
		lblEInitial = new JLabel(INITIAL_EMAIL);
		lblEConfirm = new JLabel(CONFIRM_EMAIL);
		
		txtEmailInitial = new JTextField(PW_LENGTH);
		txtEmailConfirm = new JTextField(PW_LENGTH);

		pnlEmail = new JPanel(new BorderLayout());
		
		pnlEmailInitial = new JPanel(new FlowLayout());
		pnlEmailConfirm = new JPanel(new FlowLayout());
		
		pnlEmailInitial.add(lblEInitial);
		txtEmailInitial.setEditable(true);
		pnlEmailInitial.add(txtEmailInitial);
		
		pnlEmailConfirm.add(lblEConfirm);
		txtEmailConfirm.setEditable(true);
		pnlEmailConfirm.add(txtEmailConfirm);
		
		pnlEmail.add(pnlEmailInitial, BorderLayout.NORTH);
		pnlEmail.add(pnlEmailConfirm, BorderLayout.CENTER);
		
		pnlMiddle.add(pnlEmail, BorderLayout.CENTER);
		
		validate();
	}
	
	/**
	 * The middle panel set up allows the middle panel to contain
	 * the middle panel information, such as the password and email fields.
	 */
	public void setupMiddlePanel() {
		pnlMiddle = new JPanel(new BorderLayout());
		pnlMain.add(pnlMiddle, BorderLayout.CENTER);
		
	}	
	
	
	/**
	 * The lower panel is for the bottom of the admin settings page.
	 * It allows the user to continue the program's setup or exit.
	 */
	public void setupLowerPanel() {
		pnlButtonStrip = new JPanel(new FlowLayout());
		btnNext = new JButton(NEXT);
		btnExit = new JButton(EXIT);
		
		pnlButtonStrip.add(btnExit);
		pnlButtonStrip.add(btnNext);
		
		pnlMain.add(pnlButtonStrip, BorderLayout.SOUTH);
		
		validate();
	}
	
	/**
	 * The action listeners are for the JButtons.
	 */
	public void registerListeners() {
		btnNext.addActionListener(this);
		btnExit.addActionListener(this);
	}



	/**
	 * The action performed method maps the actions
	 * to each individual button. The try/catch is set, but we
	 * still need to configure an error class to handle the errors.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExit) {
			System.exit(0);
		}
		if (e.getSource() == btnNext) {
			try {
				verify.passwordMatch(txtPasswordInitial.getPassword(), txtPasswordConfirm.getPassword()); 
			} catch (PasswordException pw) {
				showDialog(N_PW_MATCH);
			}
			
			try {
				verify.emailMatch(txtEmailInitial.getText(), txtEmailConfirm.getText());
			} catch (EmailException em) {
				showDialog(N_EM_MATCH);
			}
			try {
				logic.storePasswordHash(txtPasswordConfirm.getPassword());
				logic.storeEmail(txtEmailConfirm.getText());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidKeySpecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * This method shows a JOption pane in the event the passwords/emails do not match.
	 * It also sets the cursor to the password or email boxes.
	 * @param msg a message for the dialog box
	 */
	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(new JFrame(), msg);
		if (msg.equals(N_PW_MATCH)) {
			txtPasswordConfirm.requestFocus();
		}
		if (msg.equals(N_EM_MATCH)) {
			txtEmailConfirm.requestFocus();
		}
	}
	
	
	/**
	 * Run the program. This can throw exceptions for testing.
	 * @param args none
	 * @throws IOException a problem reading to the output file 
	 */
	public static void main(String[] args) throws IOException {
			new SetupGUI();
	}


	
}
	


