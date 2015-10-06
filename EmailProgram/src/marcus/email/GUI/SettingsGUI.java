package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import marcus.email.exceptions.EmailContentException;
import marcus.email.exceptions.EmailException;
import marcus.email.exceptions.PasswordException;
import marcus.email.exceptions.PasswordLengthException;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


/**
 * This class allows the user to save settings for the email server.
 * @author Marcus
 *
 */
public class SettingsGUI extends JFrame implements ActionListener {
	
	

	//Constant for size of fields
	private static final int FLD_LEN = 25;
	
	//Constant for rows and columns
	private static final int ROWS = 6;
	private static final int COLS = 2;
	
	//Labels for instructions
	private JLabel lblInstructionsOne;
	private JLabel lblInstructionsTwo;
	private JLabel lblInstructionsThree;
	private JLabel lblInstructionsFour;
	private JLabel lblInstructionsFive;
	private JLabel lblInstructionsSix;
	private JLabel lblInstructionsSeven;
	
	//Instructions
	private static final String INSTR_ONE = "Please enter the server settings. ";
	private static final String INSTR_TWO = "A test email will be mailed to the email address. ";
	private static final String INSTR_THREE = "You can leave the test email blank. ";
	private static final String INSTR_FOUR = "However, it is strongly recommended to send a test email ";
	private static final String INSTR_FIVE = "in order to verify proper server settings.";
	private static final String INSTR_SIX = "These settings can be modified at any time ";
	private static final String INSTR_SEVEN = "from the settings menu.";
	
	//Labels for the settings
	private JLabel lblSmtp;
	private JLabel lblPortNumber;
	private JLabel lblSmtpEnable;
	private JLabel lblSetTrue;
	private JLabel lblSetFalse;
	private JLabel lblTestEmail;
	
	//Fields for the settings
	private JTextField txtSmtp;
	private JTextField txtPortNumber;
	private JTextField txtSmtpEnable;
	private JTextField txtSetTrue;
	private JTextField txtSetFalse;
	private JTextField txtTestEmail;
	
	//Panels for the settings
	private JPanel pnlSmtp;
	private JPanel pnlPortNumber;
	private JPanel pnlSmtpEnable;
	private JPanel pnlSetTrue;
	private JPanel pnlSetFalse;
	private JPanel pnlTestEmail;
	
	//This is the main panel for the fields
	private JPanel pnlMainFields;
	
	//Text for the settings
	private static final String SMTP = "SMTP Server: ";
	private static final String PORT_NO = "SMTP Port Number: ";
	private static final String SMTP_ENABLE = "SMTP Enable: ";
	private static final String SET_TRUE = "SMTP Set True: ";
	private static final String SET_FALSE = "SMTP Set False: ";
	private static final String TEST_EMAIL_ADDRESS = "Test Email Address: ";
	
	//Next button and exit buttons, along with their labels
	private JButton btnNext;
	private JButton btnTest;
	private JButton btnExit;
	private static final String NEXT = "Next";
	private static final String TEST = "Send Test Email";
	private static final String EXIT = "Exit";


	//This JPanel is for the button strip at the bottom.
	private JPanel pnlButtons;

	/**
	 * The constructor sets up the SettingsGUI
	 */
	public SettingsGUI() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(EmailerGUIMain.PRG_NAME);
		this.setLayout(new BorderLayout());
		
		setupButtonStrip();
		registerListeners();
		setupFields();
		setupFieldPanels();
		addFieldsToPanels();
		setMainFieldPanel();
		
		
		pack();

		
		
		setLocationRelativeTo(null);
		
	}
	
	
	
	/**
	 * This method sets up the main fields with their labels.
	 */
	public void setupFields() {
		txtSmtp = new JTextField(FLD_LEN);
		txtSmtpEnable = new JTextField(FLD_LEN);
		txtPortNumber = new JTextField(FLD_LEN);
		txtSetFalse = new JTextField(FLD_LEN);
		txtSetTrue = new JTextField(FLD_LEN);
		txtTestEmail = new JTextField(FLD_LEN);
		
		lblSmtp = new JLabel(SMTP);
		lblSmtpEnable = new JLabel(SMTP_ENABLE);
		lblPortNumber = new JLabel(PORT_NO);
		lblSetFalse = new JLabel(SET_FALSE);
		lblSetTrue = new JLabel(SET_TRUE);
		lblTestEmail = new JLabel(TEST_EMAIL_ADDRESS);
		
	}
	
	/**
	 * This method sets up the main fields in their panels.
	 */
	public void setupFieldPanels() {
		pnlSmtp = new JPanel(new FlowLayout());
		pnlPortNumber = new JPanel(new FlowLayout());
		pnlSmtpEnable = new JPanel(new FlowLayout());
		pnlSetTrue = new JPanel(new FlowLayout());
		pnlSetFalse = new JPanel(new FlowLayout());
		pnlTestEmail = new JPanel(new FlowLayout());
		
	}
	
	/**
	 * This method adds the main fields to their respective panels.
	 */
	public void addFieldsToPanels() {
		pnlSmtp.add(lblSmtp);
		pnlSmtp.add(txtSmtp);
		
		pnlPortNumber.add(lblPortNumber);
		pnlPortNumber.add(txtPortNumber);
		
		pnlSmtpEnable.add(lblSmtpEnable);
		pnlSmtpEnable.add(txtSmtpEnable);
		
		pnlSetTrue.add(lblSetTrue);
		pnlSetTrue.add(txtSetTrue);
		
		pnlSetFalse.add(lblSetFalse);
		pnlSetFalse.add(txtSetFalse);
		
		pnlTestEmail.add(lblTestEmail);
		pnlTestEmail.add(txtTestEmail);
	}
	
	
	/**
	 * This method consolidates all the panels into one main panel
	 * and adds it to the GUI.
	 */
	public void setMainFieldPanel() {
		pnlMainFields = new JPanel(new GridLayout(ROWS, COLS));
		
		pnlMainFields.add(pnlSmtp);
		pnlMainFields.add(pnlPortNumber);
		pnlMainFields.add(pnlSmtpEnable);
		pnlMainFields.add(pnlSetTrue);
		pnlMainFields.add(pnlSetFalse);
		pnlMainFields.add(pnlTestEmail);
		
		add(pnlMainFields, BorderLayout.CENTER);
		
		validate();
	}
	
	
	
	
	/**
	 * This method sets up the buttons and adds them to a lower
	 * panel.
	 */
		
	public void setupButtonStrip() {
		pnlButtons = new JPanel(new FlowLayout());
				
		btnNext = new JButton(NEXT);
		btnExit = new JButton(EXIT);
		btnTest = new JButton(TEST);
		
		pnlButtons.add(btnExit);
		pnlButtons.add(btnTest);
		pnlButtons.add(btnNext);
		
		add(pnlButtons, BorderLayout.SOUTH);
		
		validate();
		
		
	}
	
	
	/**
	 * This method registers the action listeners for the buttons.
	 */
	public void registerListeners() {
		btnNext.addActionListener(this);
		btnExit.addActionListener(this);
		btnTest.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
	

	/**
	 * Run the program. This can throw exceptions for testing.
	 * @param args none
	 * @throws IOException a problem reading to the output file 
	 */
	public static void main(String[] args) throws IOException {
			new SettingsGUI();
	}
	
}
	


