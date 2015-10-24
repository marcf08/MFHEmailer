package marcus.email.GUI;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BackupGUI {

	private JDialog frmDatabaseBackup;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblInstructions;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTextField textField;
	private JLabel lblEmail;
	private JButton btnSendBackup;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblInstructionsTwo;
	private JButton btnBrowse;
	private JPanel panel_6;
	private JButton btnCancel;

	/**
	 * Create the application.
	 */
	public BackupGUI() {
		initialize();
		setupCancel();
		setupBrowse();
		frmDatabaseBackup.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatabaseBackup = new JDialog();
		frmDatabaseBackup.setTitle("Database Backup");
		frmDatabaseBackup.setBounds(100, 100, 650, 300);
		frmDatabaseBackup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmDatabaseBackup.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		frmDatabaseBackup.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_1);
		
		lblInstructions = new JLabel("You can back up the patron database in two ways.");
		panel_1.add(lblInstructions);
		
		panel_2 = new JPanel();
		frmDatabaseBackup.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		
		lblEmail = new JLabel("Email Address:");
		panel_3.add(lblEmail);
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(25);
		
		btnSendBackup = new JButton("Create & Send Backup");
		panel_3.add(btnSendBackup);
		
		panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		
		lblInstructionsTwo = new JLabel("Save the database file to the PC or removable media:");
		panel_5.add(lblInstructionsTwo);
		
		btnBrowse = new JButton("Browse");
		panel_5.add(btnBrowse);
		
		panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.add(panel_6, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		panel_6.add(btnCancel);
	}
	
	/**
	 * This method sets up the cancel button.
	 */
	public void setupCancel() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDatabaseBackup.dispose();
			}
		});
	}
	
	/**
	 * This method sets up a file chooser for the browse button.
	 */
	public void setupBrowse() {
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				if (e.getSource() == btnBrowse) {
			        int returnVal = fc.showOpenDialog(null);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            //This is where a real application would open the file.
			            //TODO: create the method that accepts this file as a parameter
			        }
				}
	
			}
		
		});
	}

}
