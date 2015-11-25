package marcus.email.GUI;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.Box;
import org.joda.time.IllegalFieldValueException;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The add patron gui allows the user to add the patron to the database.
 * @author Marcus
 *
 */
public class AddPatronGUI {

	private JDialog frmAddPatron;
	private JTextField txtFirst;
	private JTextField txtLast;
	private JTextField txtEmail;
	private JTextField txtBirth;
	private JTextField txtAnniv;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AddPatronGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddPatronGUI() {
		initialize();
		frmAddPatron.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddPatron = new JDialog(EmailerClientGUI.frmMfhEmailer, "Add Patron", true);
		frmAddPatron.setBounds(100, 100, 450, 350);
		frmAddPatron.getContentPane().setLayout(new BorderLayout(0, 0));
		frmAddPatron.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		frmAddPatron.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnDiscardAndExit = new JButton("Cancel");
		panel_3.add(btnDiscardAndExit);
		btnDiscardAndExit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frmAddPatron.dispose();
			}
		});
	
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.NORTH);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.gridwidth = 5;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 0;
		panel_4.add(panel_6, gbc_panel_6);
		
		JLabel lblEnterThePatrons = new JLabel("Enter the patron's information below, and click Save.");
		lblEnterThePatrons.setToolTipText("YYYY/MM/DD");
		lblEnterThePatrons.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblEnterThePatrons);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.gridwidth = 8;
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 1;
		panel_4.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		panel_4.add(lblNewLabel, gbc_lblNewLabel);
		
		txtFirst = new JTextField();
		GridBagConstraints gbc_txtFirst = new GridBagConstraints();
		gbc_txtFirst.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirst.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirst.gridx = 5;
		gbc_txtFirst.gridy = 2;
		panel_4.add(txtFirst, gbc_txtFirst);
		txtFirst.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 3;
		panel_4.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtLast = new JTextField();
		GridBagConstraints gbc_txtLast = new GridBagConstraints();
		gbc_txtLast.insets = new Insets(0, 0, 5, 5);
		gbc_txtLast.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLast.gridx = 5;
		gbc_txtLast.gridy = 3;
		panel_4.add(txtLast, gbc_txtLast);
		txtLast.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 4;
		panel_4.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 5;
		gbc_txtEmail.gridy = 4;
		panel_4.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.gridwidth = 3;
		gbc_horizontalStrut.gridheight = 3;
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 5;
		panel_4.add(horizontalStrut, gbc_horizontalStrut);
		
		JLabel lblBirthday = new JLabel("Birthday:");
		GridBagConstraints gbc_lblBirthday = new GridBagConstraints();
		gbc_lblBirthday.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthday.gridx = 3;
		gbc_lblBirthday.gridy = 5;
		panel_4.add(lblBirthday, gbc_lblBirthday);
		
		txtBirth = new JTextField();
	
		//Set up the balloon tip
		BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE, Color.BLUE);
			BalloonTip myBalloonTip = new BalloonTip(txtBirth, new JLabel("Use YYYY/MM/DD as a format with slashes."),
			edgedLook, Orientation.RIGHT_BELOW, AttachLocation.ALIGNED, 10, 10, true);
		
		GridBagConstraints gbc_txtBirth = new GridBagConstraints();
		gbc_txtBirth.insets = new Insets(0, 0, 5, 5);
		gbc_txtBirth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBirth.gridx = 5;
		gbc_txtBirth.gridy = 5;
		panel_4.add(txtBirth, gbc_txtBirth);
		txtBirth.setColumns(10);
		
		JLabel lblAnniv = new JLabel("Anniversary:");
		GridBagConstraints gbc_lblAnniv = new GridBagConstraints();
		gbc_lblAnniv.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnniv.gridx = 3;
		gbc_lblAnniv.gridy = 6;
		panel_4.add(lblAnniv, gbc_lblAnniv);
		
		txtAnniv = new JTextField();
		txtAnniv.setText("");
		GridBagConstraints gbc_txtAnniv = new GridBagConstraints();
		gbc_txtAnniv.insets = new Insets(0, 0, 5, 5);
		gbc_txtAnniv.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAnniv.gridx = 5;
		gbc_txtAnniv.gridy = 6;
		panel_4.add(txtAnniv, gbc_txtAnniv);
		txtAnniv.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		panel_3.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String first = txtFirst.getText();
					String last = txtLast.getText();
					String email = txtEmail.getText();
					String birth = txtBirth.getText();
					String anniv = txtAnniv.getText();
					if (checkEmail(email)) {
						JOptionPane.showMessageDialog(new JFrame(), "Invalid email. Check the email and try again.");
						txtEmail.requestFocus();
						txtEmail.selectAll();
					} else {
						EmailerClientGUI.dblogic.addPatronToDB(first, last,
								email, birth, anniv);
					}
					frmAddPatron.dispose();
				} catch (IllegalFieldValueException i) {
					JOptionPane.showMessageDialog(new JFrame(), "The date of birth is incorrect. Try again.");
				}
			}

		});
	}
	
	/**
	 * This method checks the patron email. If it doesn't throw an error, it will serve as a 
	 * unique identifier.
	 * @param email the email
	 */
	public boolean checkEmail(String email) {
		try {
			      InternetAddress emailAddr = new InternetAddress(email);
			      emailAddr.validate();
			      return true;
			   } catch (AddressException ex) {
				   return false;
			   }
	}
	

}
