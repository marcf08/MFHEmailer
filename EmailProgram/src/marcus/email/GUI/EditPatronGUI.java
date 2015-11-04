package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import java.awt.GridBagLayout;

import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JTextField;

public class EditPatronGUI {

	private JDialog contentPane;
	private JTextField txtEmail;
	private JTextField txtBirthday;
	private JTextField txtLast;
	private JTextField txtFirst;
	private JButton btnRemove;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtAnniv;

	/**
	 * Create the frame.
	 */
	public EditPatronGUI(String last, String first, String email, String birthday, String anniv) {
		init(last, first, email, birthday, anniv);
		configureButtons();
		contentPane.setVisible(true);
	}
	
	/**
	 * This method builds the panels and adds the buttons. 
	 */	
	public void init(String last, String first, String email, String dob, String anniv) {
		contentPane = new JDialog(EmailerClientGUI.frmMfhEmailer, "Edit Patron", true);
		contentPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane.setBounds(100, 100, 450, 300);
		contentPane.getContentPane().setLayout(new BorderLayout(0, 0));		
		
		JPanel panel = new JPanel();
		contentPane.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblModifyPatronAttributes = new JLabel("Edit the patron's attributes and click Save");
		lblModifyPatronAttributes.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblModifyPatronAttributes, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		btnRemove = new JButton("Remove from Database");
		panel_1.add(btnRemove);
		
		btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		
		btnSave = new JButton("Save");
		panel_1.add(btnSave);
		
		JPanel panel_2 = new JPanel();
		contentPane.getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 3;
		gbc_verticalStrut.gridy = 0;
		panel_2.add(verticalStrut, gbc_verticalStrut);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridwidth = 3;
		gbc_horizontalStrut.gridheight = 4;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 1;
		panel_2.add(horizontalStrut, gbc_horizontalStrut);
		
		JLabel lblFirst = new JLabel("First:");
		GridBagConstraints gbc_lblFirst = new GridBagConstraints();
		gbc_lblFirst.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirst.anchor = GridBagConstraints.WEST;
		gbc_lblFirst.gridx = 3;
		gbc_lblFirst.gridy = 1;
		panel_2.add(lblFirst, gbc_lblFirst);
		
		txtFirst = new JTextField();
		GridBagConstraints gbc_txtFirst = new GridBagConstraints();
		gbc_txtFirst.insets = new Insets(0, 0, 5, 0);
		gbc_txtFirst.gridx = 5;
		gbc_txtFirst.gridy = 1;
		panel_2.add(txtFirst, gbc_txtFirst);
		txtFirst.setColumns(25);
		
		JLabel lblLast = new JLabel("Last:");
		GridBagConstraints gbc_lblLast = new GridBagConstraints();
		gbc_lblLast.anchor = GridBagConstraints.WEST;
		gbc_lblLast.insets = new Insets(0, 0, 5, 5);
		gbc_lblLast.gridx = 3;
		gbc_lblLast.gridy = 2;
		panel_2.add(lblLast, gbc_lblLast);
		
		txtLast = new JTextField();
		GridBagConstraints gbc_txtLast = new GridBagConstraints();
		gbc_txtLast.insets = new Insets(0, 0, 5, 0);
		gbc_txtLast.gridx = 5;
		gbc_txtLast.gridy = 2;
		panel_2.add(txtLast, gbc_txtLast);
		txtLast.setColumns(25);
		
		JLabel lblDOB = new JLabel("Birthday:");
		GridBagConstraints gbc_lblDOB = new GridBagConstraints();
		gbc_lblDOB.anchor = GridBagConstraints.WEST;
		gbc_lblDOB.insets = new Insets(0, 0, 5, 5);
		gbc_lblDOB.gridx = 3;
		gbc_lblDOB.gridy = 3;
		panel_2.add(lblDOB, gbc_lblDOB);
		
		txtBirthday = new JTextField();
		GridBagConstraints gbc_txtBirthday = new GridBagConstraints();
		gbc_txtBirthday.insets = new Insets(0, 0, 5, 0);
		gbc_txtBirthday.gridx = 5;
		gbc_txtBirthday.gridy = 3;
		panel_2.add(txtBirthday, gbc_txtBirthday);
		txtBirthday.setColumns(25);
		
		JLabel lblNewLabel = new JLabel("Email (Permanent): ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 4;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.gridx = 5;
		gbc_txtEmail.gridy = 4;
		panel_2.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(25);
		
		txtAnniv = new JTextField();
		
		
		
		txtEmail.setText(email);
		txtLast.setText(last);
		txtFirst.setText(first);
		txtBirthday.setText(dob);
		txtAnniv.setText(anniv);
		
		
		
		
		
		
		txtEmail.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblAnniv = new JLabel("Anniversary:");
		GridBagConstraints gbc_lblAnniv = new GridBagConstraints();
		gbc_lblAnniv.anchor = GridBagConstraints.WEST;
		gbc_lblAnniv.insets = new Insets(0, 0, 0, 5);
		gbc_lblAnniv.gridx = 3;
		gbc_lblAnniv.gridy = 5;
		panel_2.add(lblAnniv, gbc_lblAnniv);
		
		GridBagConstraints gbc_txtAnniv = new GridBagConstraints();
		gbc_txtAnniv.anchor = GridBagConstraints.NORTH;
		gbc_txtAnniv.gridx = 5;
		gbc_txtAnniv.gridy = 5;
		panel_2.add(txtAnniv, gbc_txtAnniv);
		txtAnniv.setColumns(25);
	
	}
	
	/**
	 * This method configures the buttons.
	 */
	public void configureButtons() {
		btnCancel.addActionListener(new ActionListener() {
			//Close the frame if the cancel button is clicked.
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCancel) {
					contentPane.dispose();;
				}
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			//This will make the changes.
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSave) {
					
				}
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			//This will remove the selected patron.
			public void actionPerformed(ActionEvent e) {
				EmailerClientGUI.dblogic.remove(txtEmail.getText());
				EmailerClientGUI.buildTable();
				contentPane.dispose();
			}
		});
	}

	
	/**
	 * This method saves the changes.
	 * @param email the patron's email
	 */
	private void saveChanges(String first, String last, String dob, String email) {
		
		
	}

}
