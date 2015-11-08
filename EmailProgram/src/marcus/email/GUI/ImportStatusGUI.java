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

	private static JDialog frmStatus;
	private JLabel lblStatus;
	private JButton btnOK;
	
	private Task task;
	
	//These data members are passed into the labels to report on the status of the import
	private static String complete = "Import Complete";
	private String status;
	
	//This integer is used by the progress bar to know it's maximum status.
	private Integer total;
	
	private static JLabel lblNumber;
	private static JLabel lblComplete;
	public static JProgressBar progressBar;
	private boolean done;
	private JButton btnStart;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportStatusGUI window = new ImportStatusGUI();
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
	public ImportStatusGUI() {
		initialize();
		setupBar();
		done = false;
		setupStart();
		
		
		
		frmStatus.setVisible(true);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStatus = new JDialog(ImportGUI.frmMain, "Status", true);
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
		
		btnOK = new JButton("OK");
		panel_2.add(btnOK);
		
		lblComplete = new JLabel("");
		lblComplete.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblComplete, BorderLayout.CENTER);
	}
	
	/**
	 * This method sets up the ok button. It is initially not enabled.
	 * It will be enabled whenever the import is complete.
	 */
	public void setupOK() {
		btnOK.setEnabled(false);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailerClientGUI.alphabetize();
				frmStatus.dispose();
				ImportGUI.frmMain.dispose();
			}
		});
	}
	
	/**
	 * This enables the OK button once the import is complete.
	 */
	private void enableOK() {
		btnOK.setEnabled(true);
	}
	
	/**
	 * This method sets up the progress bar. It does not update it.
	 */
	public void setupBar() {
		total = ImportGUI.logic.size();
		progressBar.setMaximum(total);
	}

	/**
	 * This method sets up the start import button, which in turn begins the import.
	 */
	public void setupStart() {
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					lblStatus.setText("Importing Patrons...");
					ImportGUI.logic.addPatronList();
					setupOK();
					enableOK();
					lblComplete.setText(complete);
					btnStart.setEnabled(false);
			}
		});
	
	}

}
