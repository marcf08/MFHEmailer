package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

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
	
	//This dimension is for centering the screen
	Dimension dimCenter;
	
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
	private static final String USER = "Username:";
	private static final String PW = "Password: ";
	private static final int PW_LENGTH = 15;
	
	//This panel is for the illustration
	private JPanel pnlTop;
	
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
		this.setLayout(new BorderLayout());
		this.setTitle(EmailerGUIMain.PRG_NAME);
		this.setLocationRelativeTo(null);
		
		setupLoginPanel();
		setHomePageButtons();
		setButtonPanel();

		registerListeners();
		setupUserInfo();
		setupTopPanel();

		pack();
		
		//Center the login face		
		setLocationRelativeTo(null);
		
	}
	
	
	/**
	 * This method sets up the main JPanel and adds it to the
	 * home page.
	 */
	public void setupLoginPanel() {
		this.setLayout(new BorderLayout());
	}
	
	/**
	 * This method sets up the password/email fields in the middle.
	 */
	public void setupUserInfo() {
		pnlMiddle = new JPanel(new BorderLayout());
		pnlUserInfo = new JPanel(new FlowLayout());
		pnlUserPw = new JPanel(new FlowLayout());
		
		lblUser = new JLabel(USER);
		lblPassword = new JLabel(PW);

		txtUserName = new JTextField(PW_LENGTH);
		txtPassword = new JPasswordField(PW_LENGTH);

		pnlUserInfo.add(lblUser);
		pnlUserInfo.add(txtUserName);
		
		pnlUserPw.add(lblPassword);
		pnlUserPw.add(txtPassword);
		
		pnlMiddle.add(pnlUserInfo, BorderLayout.NORTH);
		pnlMiddle.add(pnlUserPw, BorderLayout.CENTER);

	
		this.add(pnlMiddle, BorderLayout.CENTER);


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
		this.add(pnlButtons, BorderLayout.SOUTH);
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
	 * This method sets up the top panel with the illustration.
	 */
	public void setupTopPanel() {
		pnlTop = new JPanel();
		BufferedImage logo = null;
		try {
			logo = ImageIO.read(new File("C:/Users/Marcus/Desktop/mfhLogoTwo.png"));
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
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}

		new MainLoginGUI();
			
	}
	
}
	


