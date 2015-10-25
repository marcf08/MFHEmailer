package marcus.email.GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;

public class ImportPreviewGUI {

	private JDialog frame;
	private JButton btnCancel;
	private JTable tblMain;
	private DefaultTableModel model;
	
	//These arrays will be for the information in the table
	private String[] lastNames;
	private String[] firstNames;
	private String[] emails;
	private String[] birthdays;
	
	//This will be sent once the import logic counts the files
	private Integer size;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportPreviewGUI window = new ImportPreviewGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImportPreviewGUI() {
		initialize();
		initArrays();
	
		setupTableModel();
		
		
		
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		
		JButton btnConfirm = new JButton("OK");
		panel_2.add(btnConfirm);
		
		JProgressBar progressBar = new JProgressBar();
		panel.add(progressBar, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblInstructions = new JLabel("Review the list below, and click OK to confirm the additions.");
		panel_1.add(lblInstructions);
		
		tblMain = new JTable();
		frame.getContentPane().add(tblMain, BorderLayout.CENTER);
		
		JScrollBar scrollBar = new JScrollBar();
		frame.getContentPane().add(scrollBar, BorderLayout.EAST);
	}
	
	/**
	 * This method builds the table.
	 */
	public void setupTableModel() {
		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		model.setColumnCount(EmailerClientGUI.COL_COUNT);
		tblMain.setRowSelectionAllowed(true);
		tblMain.setTableHeader(null);
	}
	
	/**
	 * This method builds the arrays.
	 */
	public void initArrays() {
		lastNames = new String[ImportGUI.logic.size()];
		firstNames = new String[ImportGUI.logic.size()];
		emails = new String[ImportGUI.logic.size()];
		birthdays = new String[ImportGUI.logic.size()];
	}
	
	
	/**
	 * This method populates the tables.
	 */
	public void populateTables() {
		
	}
	
	

}
