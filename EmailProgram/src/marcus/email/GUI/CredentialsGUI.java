package marcus.email.GUI;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.swing.Box;

import marcus.email.admin.PasswordHash;

public class CredentialsGUI {

	private JDialog frmChangeCredentials;
	private JButton btnSave;
	private JButton btnCancel;
	private JLabel lblInstructions;
	private JLabel lblOldPassword;
	private JLabel lblNewPasswordConfirm;
	private JPasswordField pwOld;
	private JPasswordField pwNewInit;
	private JPasswordField pwNewConfirm;
	
	private static CredentialsLogic logic;
	private Component verticalStrut;

	/**
	 * Create the application.
	 */
	public CredentialsGUI() {
		logic = new CredentialsLogic();
		initialize();
		setupSaveButton();
		setupCancelButton();
		frmChangeCredentials.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChangeCredentials = new JDialog(EmailerClientGUI.frmMfhEmailer, "Change Credntials", true);
		frmChangeCredentials.setTitle("Change Credentials");
		frmChangeCredentials.setBounds(100, 100, 450, 300);
		frmChangeCredentials.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmChangeCredentials.getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		panel.add(btnCancel);
		
		btnSave = new JButton("Save");
		panel.add(btnSave);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmChangeCredentials.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		lblInstructions = new JLabel("Change the password, and click Save.");
		panel_1.add(lblInstructions);
		
		JPanel panel_2 = new JPanel();
		frmChangeCredentials.getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.gridwidth = 2;
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 4;
		gbc_verticalStrut.gridy = 0;
		panel_2.add(verticalStrut, gbc_verticalStrut);
		
		lblOldPassword = new JLabel("Old Password:");
		GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
		gbc_lblOldPassword.anchor = GridBagConstraints.WEST;
		gbc_lblOldPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblOldPassword.gridx = 4;
		gbc_lblOldPassword.gridy = 1;
		panel_2.add(lblOldPassword, gbc_lblOldPassword);
		
		pwOld = new JPasswordField();
		pwOld.setColumns(25);
		GridBagConstraints gbc_pwOld = new GridBagConstraints();
		gbc_pwOld.anchor = GridBagConstraints.WEST;
		gbc_pwOld.insets = new Insets(0, 0, 5, 0);
		gbc_pwOld.gridx = 5;
		gbc_pwOld.gridy = 1;
		panel_2.add(pwOld, gbc_pwOld);
		
		JLabel lblNewPasswordInit = new JLabel("New Password:");
		GridBagConstraints gbc_lblNewPasswordInit = new GridBagConstraints();
		gbc_lblNewPasswordInit.anchor = GridBagConstraints.WEST;
		gbc_lblNewPasswordInit.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPasswordInit.gridx = 4;
		gbc_lblNewPasswordInit.gridy = 2;
		panel_2.add(lblNewPasswordInit, gbc_lblNewPasswordInit);
		
		pwNewInit = new JPasswordField();
		pwNewInit.setHorizontalAlignment(SwingConstants.CENTER);
		pwNewInit.setColumns(25);
		GridBagConstraints gbc_pwNewInit = new GridBagConstraints();
		gbc_pwNewInit.anchor = GridBagConstraints.WEST;
		gbc_pwNewInit.insets = new Insets(0, 0, 5, 0);
		gbc_pwNewInit.gridx = 5;
		gbc_pwNewInit.gridy = 2;
		panel_2.add(pwNewInit, gbc_pwNewInit);
		
		lblNewPasswordConfirm = new JLabel("New Password (Confirm):");
		lblNewPasswordConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewPasswordConfirm = new GridBagConstraints();
		gbc_lblNewPasswordConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewPasswordConfirm.anchor = GridBagConstraints.EAST;
		gbc_lblNewPasswordConfirm.gridx = 4;
		gbc_lblNewPasswordConfirm.gridy = 3;
		panel_2.add(lblNewPasswordConfirm, gbc_lblNewPasswordConfirm);
		
		pwNewConfirm = new JPasswordField();
		pwNewConfirm.setColumns(25);
		GridBagConstraints gbc_pwNewConfirm = new GridBagConstraints();
		gbc_pwNewConfirm.anchor = GridBagConstraints.WEST;
		gbc_pwNewConfirm.gridx = 5;
		gbc_pwNewConfirm.gridy = 3;
		panel_2.add(pwNewConfirm, gbc_pwNewConfirm);
	}
	
	/**
	 * This method sets up the save button.
	 */
	public void setupSaveButton() {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSave) {
					
					char [] old = pwOld.getPassword();
					String oldPw = new String(old);
					
					char [] pwNewArray = pwNewInit.getPassword();
					String pwNewString = new String(pwNewArray);
					
					char [] pwNewConfirmArray = pwNewConfirm.getPassword();
					String pwNewConfirmString = new String(pwNewConfirmArray);
					
					try {
						logic.changePassword(PasswordHash.createHash(oldPw), pwNewString, pwNewConfirmString);
					} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}			
				}
			}
		});
	}
	
	/**
	 * This method sets up the cancel button.
	 */
	public void setupCancelButton() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmChangeCredentials.dispose();
			}
		});
	}
}
