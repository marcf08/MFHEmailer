package marcus.email.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.sun.jmx.snmp.tasks.Task;

import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class ImportStatusGUI {
	//Swing members
	private JLabel lblNumber;
	private JLabel lblComplete;
	public static JProgressBar progressBar;
	private boolean done;
	private JButton btnStart;
	private JDialog frmStatus;
	private JLabel lblStatus;
	private JButton btnOK;

	//These data members are passed into the labels to report on the status of the import
	private final String complete = "Import Complete";
	//For status
	private Integer total;
	private ImportLogic logic;
	private JButton btnCancel;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportStatusGUI window = new ImportStatusGUI(null);
					window.frmStatus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImportStatusGUI(ImportLogic logic) {
		this.logic = logic;
		initialize();
		setupBar();
		done = false;
		setupStart();
		setupCancel();
		frmStatus.setVisible(true);
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStatus = new JDialog(frmStatus, "Status", true);
		frmStatus.setBounds(100, 100, 450, 200);
		frmStatus.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmStatus.getContentPane().add(panel, BorderLayout.NORTH);
		
		lblStatus = new JLabel("Import Progress");
		panel.add(lblStatus);
		
		lblNumber = new JLabel("");
		panel.add(lblNumber);
		
		JPanel panel_1 = new JPanel();
		frmStatus.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
		panel_1.add(progressBar, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		btnStart = new JButton("Start Import");
		panel_2.add(btnStart);
		
		btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		
		btnOK = new JButton("OK");
		panel_2.add(btnOK);
		
		lblComplete = new JLabel("");
		lblComplete.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblComplete, BorderLayout.CENTER);
	}
	
	//Sets up the OK button
	public void setupOK() {
		btnOK.setEnabled(false);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailerClientGUI.alphabetize();
				frmStatus.dispose();
			}
		});
	}
	
	//Sets up the Cancel button
	public void setupCancel() {
		btnCancel.setEnabled(true);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmStatus.dispose();
			}
		});
	}
	
	//Enables OK when the import comples
	private void enableOK() {
		btnOK.setEnabled(true);
	}
	
	//Builds progress bar
	public void setupBar() {
		total = logic.size();
		progressBar.setMaximum(total);
	}

	//Sets up the start import button
	public void setupStart() {
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					lblStatus.setText("Importing Patrons...");
					logic.addPatronList();
					setupOK();
					enableOK();
					lblComplete.setText(complete);
					btnStart.setEnabled(false);
			}
		});
	
	}

}
