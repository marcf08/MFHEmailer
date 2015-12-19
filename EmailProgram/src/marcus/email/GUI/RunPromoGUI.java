package marcus.email.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RunPromoGUI {

	private JFrame frmRunPromotionalEmail;
	private JButton btnSendPromo;
	private JRadioButton rdbtnSecondAuth;
	private JRadioButton rdbtnFirstAuth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunPromoGUI window = new RunPromoGUI();
					window.frmRunPromotionalEmail.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. The second authorization and send buttons are
	 * initially disabled.
	 */
	public RunPromoGUI() {
		initialize();
		rdbtnSecondAuth.setEnabled(false);
		btnSendPromo.setEnabled(false);
		configureFirstRadio();
		configureSecondRadio();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRunPromotionalEmail = new JFrame();
		frmRunPromotionalEmail.setTitle("Run Promotional Email");
		frmRunPromotionalEmail.setBounds(100, 100, 640, 360);
		frmRunPromotionalEmail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		btnSendPromo = new JButton("SEND");
		panel_6.add(btnSendPromo);
	}
	
	
	/**
	 * This method verifies that both authorization radio buttons are active.
	 * @return true if both radio buttons are active and false otherwise
	 */
	public boolean confirmSend() {
		if (rdbtnFirstAuth.isSelected() && rdbtnSecondAuth.isSelected()) {
			return true;
		}
		return false;
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

}
