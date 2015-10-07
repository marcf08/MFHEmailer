package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

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
	

	//Next button and exit buttons, along with their labels
	private JButton btnSave;
	private JButton btnTest;
	private JButton btnExit;
	private static final String SAVE = "Save";
	private static final String TEST = "Send Test Email";
	private static final String EXIT = "Exit";

	//This JPanel is for the button strip at the bottom.
	private JPanel pnlButtons;
	
	//The string consists of all labels used.
	private String [] settingsLabels = {"SMTP Server: ", "SMTP Port Number: ", "SMTP Enable: ", "SMTP Set True: ", "SMTP Set False: ", "Test Email Address: "}; 
	
	/**
	 * The constructor sets up the SettingsGUI
	 */
	public SettingsGUI() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(EmailerGUIMain.PRG_NAME);
		setLayout(new BorderLayout());
		
		//Setup the grid and pack it
		setupGrid();
		setupButtons();
		pack();
		
		
		setLocationRelativeTo(null);
		
	}
	
	
	/**
	 * This method sets up the grid, the borders, and populates them with the
	 * add label method below it.
	 */
	public void setupGrid() {
		
		JPanel settingsPanel = new JPanel();
	    Border border = settingsPanel.getBorder();
	    Border margin = new EmptyBorder(10, 10, 10, 10);
	    settingsPanel.setBorder(new CompoundBorder(border, margin));

	    GridBagLayout panelGridBagLayout = new GridBagLayout();
	    panelGridBagLayout.columnWidths = new int[] { 86, 86, 0 };
	    panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
	    panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	    panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	    settingsPanel.setLayout(panelGridBagLayout);

	    addLabel(settingsLabels[0], 0, settingsPanel);
	    addLabel(settingsLabels[1], 1, settingsPanel);
	    addLabel(settingsLabels[2], 2, settingsPanel);
	    addLabel(settingsLabels[3], 3, settingsPanel);
	    addLabel(settingsLabels[4], 4, settingsPanel);
	
	    add(settingsPanel, BorderLayout.CENTER);
	    validate();
	}
	
	
	/**
	 * This method adds the labels to the grid. It also sets the grid constraints
	 * for the settings used to configure the email server. 
	 * @param labelText
	 * @param yPos
	 * @param settingsPanel
	 */
	public void addLabel(String labelText, int yPos, Container settingsPanel) {
		JLabel faxLabel = new JLabel(labelText);
	    GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
	    gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
	    gridBagConstraintForLabel.gridx = 0;
	    gridBagConstraintForLabel.gridy = yPos;
	    settingsPanel.add(faxLabel, gridBagConstraintForLabel);

	    JTextField textField = new JTextField();
	    GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
	    gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
	    gridBagConstraintForTextField.gridx = 1;
	    gridBagConstraintForTextField.gridy = yPos;
	    settingsPanel.add(textField, gridBagConstraintForTextField);
	    textField.setColumns(10);
	
	}
	
	/**
	 * This method instantiates the button and adds them to the
	 * button strip panel. This panel is then added to the main
	 * panel.
	 */
	
	public void setupButtons() {
		btnExit = new JButton(EXIT);
		btnSave = new JButton(SAVE);
		btnTest = new JButton(TEST);
		
		pnlButtons = new JPanel(new FlowLayout());
		
		pnlButtons.add(btnExit);
		pnlButtons.add(btnTest);
		pnlButtons.add(btnSave);
		
		
		add(pnlButtons, BorderLayout.SOUTH);
		validate();
	}
	
	/**
	 * This method sets up the actions for the buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExit) {
			System.exit(0);
		}
		if (e.getSource() == btnTest) {
			
		}
		if (e.getSource() == btnSave) {
			
		}
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
	


