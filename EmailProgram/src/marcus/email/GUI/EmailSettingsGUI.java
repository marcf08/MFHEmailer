package marcus.email.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;
import javax.swing.JButton;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;

import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ShutdownChannelGroupException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;

import org.joda.time.LocalDate;

public class EmailSettingsGUI {

	private JDialog frmSmtpSettings;
	private JTabbedPane tabbedPane;
	private JPanel panelSendGrid;
	private JPanel panel_2;
	private JLabel lblApiKey;
	private JTextField pwAPI;
	private JButton btnCancel;
	private JButton btnEdit;
	private JButton btnTest;
	private JButton btnSave;
	private JLabel lblTest;
	private JTextField txtToAddress;
	private Component verticalStrut;
	private Component horizontalStrut;
	private JPanel panel_1;
	private static JPanel pnlButtons;
	
	private static EmailSettingsLogic logic;
	private JLabel lblFrom;
	private JTextField txtFrom;
	

	/**
	 * Create the application.
	 */
	public EmailSettingsGUI() {
		logic = new EmailSettingsLogic();
	
		initialize();
		setupWindowClose();
		test();
		load();
		enableDisableFields(false);
		
		setupEditButton();
		setupCancelButton();
		setupSaveButton();
		
		frmSmtpSettings.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSmtpSettings = new JDialog(EmailerClientGUI.frmMfhEmailer, "SMTP Settings", true);
		frmSmtpSettings.setTitle("SMTP Settings");
		frmSmtpSettings.setBounds(100, 100, 450, 300);
		frmSmtpSettings.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmSmtpSettings.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panelSendGrid = new JPanel();
		tabbedPane.addTab("API Settings", null, panelSendGrid, null);
		panelSendGrid.setLayout(new BorderLayout(0, 0));
		
		pnlButtons = new JPanel();
		pnlButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelSendGrid.add(pnlButtons, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		pnlButtons.add(btnCancel);
		
		btnEdit = new JButton("Edit Settings");
		pnlButtons.add(btnEdit);
		
		btnTest = new JButton("Test");
		pnlButtons.add(btnTest);
		
		btnSave = new JButton("Save");
		pnlButtons.add(btnSave);
		
		panel_2 = new JPanel();
		panelSendGrid.add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.gridwidth = 6;
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 0;
		panel_2.add(verticalStrut, gbc_verticalStrut);
		
		lblApiKey = new JLabel("API Key:");
		GridBagConstraints gbc_lblApiKey = new GridBagConstraints();
		gbc_lblApiKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblApiKey.anchor = GridBagConstraints.WEST;
		gbc_lblApiKey.gridx = 1;
		gbc_lblApiKey.gridy = 1;
		panel_2.add(lblApiKey, gbc_lblApiKey);
		
		pwAPI = new JPasswordField();
		pwAPI.setColumns(25);
		GridBagConstraints gbc_pwAPI = new GridBagConstraints();
		gbc_pwAPI.gridwidth = 2;
		gbc_pwAPI.anchor = GridBagConstraints.WEST;
		gbc_pwAPI.insets = new Insets(0, 0, 5, 5);
		gbc_pwAPI.gridx = 2;
		gbc_pwAPI.gridy = 1;
		panel_2.add(pwAPI, gbc_pwAPI);
		
		lblTest = new JLabel("Test Address:");
		GridBagConstraints gbc_lblTest = new GridBagConstraints();
		gbc_lblTest.anchor = GridBagConstraints.WEST;
		gbc_lblTest.insets = new Insets(0, 0, 5, 5);
		gbc_lblTest.gridx = 1;
		gbc_lblTest.gridy = 2;
		panel_2.add(lblTest, gbc_lblTest);
		
		txtToAddress = new JTextField();
		GridBagConstraints gbc_txtToAddress = new GridBagConstraints();
		gbc_txtToAddress.gridwidth = 2;
		gbc_txtToAddress.anchor = GridBagConstraints.WEST;
		gbc_txtToAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtToAddress.gridx = 2;
		gbc_txtToAddress.gridy = 2;
		panel_2.add(txtToAddress, gbc_txtToAddress);
		txtToAddress.setColumns(25);
		
		lblFrom = new JLabel("Test From:");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.anchor = GridBagConstraints.WEST;
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 1;
		gbc_lblFrom.gridy = 3;
		panel_2.add(lblFrom, gbc_lblFrom);
		
		txtFrom = new JTextField();
		GridBagConstraints gbc_txtFrom = new GridBagConstraints();
		gbc_txtFrom.anchor = GridBagConstraints.WEST;
		gbc_txtFrom.insets = new Insets(0, 0, 5, 5);
		gbc_txtFrom.gridx = 2;
		gbc_txtFrom.gridy = 3;
		panel_2.add(txtFrom, gbc_txtFrom);
		txtFrom.setColumns(25);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridheight = 7;
		gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 4;
		panel_2.add(horizontalStrut, gbc_horizontalStrut);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 4;
		panel_2.add(panel_1, gbc_panel_1);
	}
	
	
	/**
	 * This method configures the save button.
	 */
	public void setupSaveButton() {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				enableDisableFields(false);
				JOptionPane.showMessageDialog(new JFrame(), "Settings saved.");
				frmSmtpSettings.dispose();
			}
		});
	}

	/**
	 * The edit button will make the fields editable.
	 */
	public void setupEditButton() {
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enableDisableFields(true);
			}
		});
	}
	
	/**
	 * The cancel button will just dispose the window.
	 */
	public void setupCancelButton() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSmtpSettings.dispose();
			}
		});
	}
	
	
	
	
	/**
	 * This method saves the settings to the properties file.
	 */
	public void save() {
		logic.setKey(pwAPI.getText());
		logic.setEmailKey(txtFrom.getText());
	}
		
	/**
	 * This method loads properties if any exist.
	 */
	public void load() {
		pwAPI.setText(logic.getKey());
		txtFrom.setText(logic.getEmailKey());
	}
	
	/**
	 * The window close event detects whether the window closes. If it does,
	 * it saves the information to the properties file.
	 */
	public void setupWindowClose() {
		frmSmtpSettings.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
			}
			public void windowIconified(WindowEvent e) {
			}
			public void windowDeiconified(WindowEvent e) {
			}
			public void windowDeactivated(WindowEvent e) {
			}
			public void windowClosing(WindowEvent e) {
				save();
			}
			public void windowClosed(WindowEvent e) {
			}
			public void windowActivated(WindowEvent e) {
			}
		});;
	}
	

	
	
	
	
	
	/**
	 * This method colors the fields grey and sets them to disabled.
	 * It will change depending on if the user hits "Edit Settings."
	 */
	public void enableDisableFields(boolean b) {
		pwAPI.setEnabled(b);
		txtFrom.setEnabled(b);
		txtToAddress.setEnabled(b);
	}
	
	/**
	 * This runs a quick test email.
	 */
	public void test() {
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (pwAPI.getText() != null) {
					SendGrid send = new SendGrid(pwAPI.getText());
					Email email = new Email();
					email.addTo(txtToAddress.getText());
					email.setText("Test From Development " + LocalTime.now().toString() + "\n Success.");
					email.setSubject("Test from MFHEmailer " + LocalDate.now().toString());
					email.setFrom(txtFrom.getText());
					email.setHtml("<html><head>HELLO TEST</head><body><strong>TEST BODY</strong></body></html>");
					try {
						SendGrid.Response response = send.send(email);
						JOptionPane.showMessageDialog(new JFrame(), "Server replied: " + response.getMessage());	
					} catch (SendGridException e1) {
						JOptionPane.showMessageDialog(new JFrame(), "Error sending mail. Please try again.");
					}
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "Error. Please check the credentials and try again.");
			}
			}
		});
	}


}
