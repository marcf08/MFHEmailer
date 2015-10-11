package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


/**
 * This package contains the main email client. After logging on, the user
 * will be presented with this page in order to add and remove patrons, monitor
 * the status, and control email campaigns.
 * @author Marcus
 *
 */

public class EmailerClientGUI extends JFrame implements ActionListener {
	//Menu bar and settings
	private JMenuBar jmuFileBar;
	private String [] jmuText = {"File", "Settings", "Database", "Help"};
	private JMenuItem [] jmuItems = new JMenuItem[jmuText.length];
	
	//The middle panel uses two sub-panels, one for the main (pnlMiddle) and
	//one for the text area. The sub middle and console are for both views. 
	private JPanel pnlMiddle;
	private JPanel pnlTextArea;
	private JPanel pnlSubMiddle;
	private JPanel pnlConsole;
	
	//This layout is for the middle panel. It uses a main card layout.
	private CardLayout layout;
	
	//This JTextArea corresponds to the "console" that logs information.
	private JTextArea txtConsole;
	
	//The combo box is for the view selector
	private JComboBox<JButton> viewSelector;
	private JButton btnEmailer;
	private JButton btnConsole;
	
	/**
	 * The constructor builds the main client screen.
	 */
	public EmailerClientGUI() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(EmailerGUIMain.PRG_NAME);
		setLayout(new BorderLayout());
		setMenuBar();
		setupMiddle();
		setupConsole();
	
		pack();
		
		setLocationRelativeTo(null);
	}
	
	/**
	 * The menu bar stores the file, settings, edit, etc.
	 */
	private void setMenuBar() {
		jmuFileBar = new JMenuBar();
		jmuFileBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		for (int i = 0; i < jmuItems.length; i++) {
			jmuItems[i] = new JMenuItem(jmuText[i]);
			jmuFileBar.add(jmuItems[i]);
		}
		add(jmuFileBar, BorderLayout.NORTH);
	}
	
	/**
	 * This method sets up the middle panel. There is a middle panel within
	 * this middle panel that is controlled by the view selector.
	 */
	public void setupMiddle() {
		pnlMiddle = new JPanel(new BorderLayout());
		add(pnlMiddle);
	}
	
	/**
	 * This method sets up the view selector. It's a drop-down menu with the
	 * two views.
	 */
	public void setupViewSelector() {
		btnEmailer = new JButton("Emailer");
		btnConsole = new JButton("Console");
		
		viewSelector = new JComboBox<>();
		viewSelector.addItem(btnEmailer);
		viewSelector.addItem(btnConsole);
		
	}
	
	/**
	 * This method sets up the "inner middle panel" that is responsible for
	 * displaying the patron information.
	 */
	public void setupSubMiddle() {
		pnlSubMiddle = new JPanel(new BorderLayout());
		
	}
	
	/**
	 * This method sets up the 
	 */
	
	/**
	 * This method sets up the text area.
	 */
	public void setupConsole() {
		pnlTextArea = new JPanel(new BorderLayout());
		
		txtConsole = new JTextArea();
		txtConsole.setEditable(false);
		txtConsole.setLineWrap(true);
		txtConsole.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(pnlMiddle);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pnlTextArea.add(scroll, BorderLayout.EAST);
		pnlTextArea.add(txtConsole);
		add(pnlTextArea);
		
		validate();
	}
	
	/**
	 * This method sets up the timer at the bottom of the screen.
	 */
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Runs the client--for testing only.
	 * @param args
	 */
	public static void main(String [] args) {
		new EmailerClientGUI();
	}
	
	
}
