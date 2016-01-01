package marcus.email.GUI;

import marcus.email.util.EmailTemplate;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;

import marcus.email.util.SendPromo;

import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RunPromoGUI {

	//Swing members
	private JDialog frmRunPromotionalEmail;
	private JButton btnSendPromo;
	private JRadioButton rdbtnSecondAuth;
	private JRadioButton rdbtnFirstAuth;
	private JButton btnCancel;
	
	//Data member used to initialize the GUI
	private SendPromo sp;
	private String results;

	/**
	 * Create the application. The second authorization and send buttons are
	 * initially disabled.
	 */
	public RunPromoGUI() {
		initialize();
		configureCancel();
		rdbtnSecondAuth.setEnabled(false);
		configureFirstRadio();
		configureSecondRadio();
		configureSendButton();
		configureSender();
		btnSendPromo.setEnabled(false);
		frmRunPromotionalEmail.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRunPromotionalEmail = new JDialog(EmailerClientGUI.frmMfhEmailer, "Run Promotional Email", true);
		frmRunPromotionalEmail.setBounds(100, 100, 650, 300);
		frmRunPromotionalEmail.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmRunPromotionalEmail.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWarningZero = new JLabel("WARNING: Promotional emails are sent to the ENTIRE database. Emails cannot be unsent.");
		lblWarningZero.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblWarningZero, BorderLayout.NORTH);
		
		JLabel lblWarningOne = new JLabel("Make sure you have selected the correct template and tested it.");
		lblWarningOne.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblWarningOne, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblStatus = new JLabel("Status: Ready");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblStatus, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblFirstAuth = new JLabel("I have read the warning above:");
		panel_4.add(lblFirstAuth);
		
		rdbtnFirstAuth = new JRadioButton("Yes");
		panel_4.add(rdbtnFirstAuth);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		
		JLabel lblSecondAutho = new JLabel("The email template is correct and ready to send:");
		panel_5.add(lblSecondAutho);
		
		rdbtnSecondAuth = new JRadioButton("Yes");
		panel_5.add(rdbtnSecondAuth);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		panel_6.add(btnCancel);
		
		btnSendPromo = new JButton("SEND");
		panel_6.add(btnSendPromo);
	}
	
	/**
	 * This sets up the data member used to send the promotional email.
	 */
	public void configureSender() {
		sp = new SendPromo();
		sp.defaultSetup(FileConstants.CONFIG_LOC);
		EmailTemplate t = null;
		t = EmailerClientGUI.emailStorage.getTemplate(EmailerClientGUI.getSelectedTemplate());
		sp.setTemplate(t);
	}
	
	/**
	 * This method sends the promo.
	 * @return the string of results
	 */
	public void sendPromo() {
		sp.setPatrons(EmailerClientGUI.dblogic.getAlphabetic());
		results = sp.send();
		showResults();
	}
	
	/**
	 * This method shows the results.
	 */
	private void showResults() {
		JOptionPane.showConfirmDialog(new JFrame(), results);
	}
	
	
	/**
	 * This method configures the property change listener for the
	 * second radio button.
	 */
	public void configureFirstRadio() {
		rdbtnFirstAuth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					rdbtnSecondAuth.setEnabled(true);
				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					rdbtnSecondAuth.setEnabled(false);
				}
			}
		});
	}
	
	/**
	 * This method configures the item change listener for the send button.
	 */
	public void configureSecondRadio() {
		rdbtnSecondAuth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					btnSendPromo.setEnabled(true);
				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					btnSendPromo.setEnabled(false);
				}
			}
		});
	}
	
	/**
	 * This method configures the send button.
	 */
	public void configureSendButton() {
		btnSendPromo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSendPromo) {
					sendPromo();
				}
				
			}
		});
	}
	
	/**
	 * This method configures the cancel button
	 */
	public void configureCancel() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCancel) {
					frmRunPromotionalEmail.dispose();
				}
			}
		});
	}

}
