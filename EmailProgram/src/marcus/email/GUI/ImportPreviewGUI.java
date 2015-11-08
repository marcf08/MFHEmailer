package marcus.email.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
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
	private String[] anniv;
	private JButton btnConfirm;

	/**
	 * Create the application.
	 * @param file the file to preview
	 */
	public ImportPreviewGUI() {
		initialize();
		setupTableModel();
		initArrays();
		setupOK();
		setupCancel();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog(ImportGUI.frmMain, "Review Additions", true);
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);

		btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);

		btnConfirm = new JButton("OK");
		panel_2.add(btnConfirm);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);

		JLabel lblInstructions = new JLabel("Review the list below, and click OK to confirm the additions.");
		panel_1.add(lblInstructions);

		tblMain = new JTable();
		frame.getContentPane().add(tblMain, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(tblMain);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * This method sets up the table settings, including row/column
	 * information and the table model.
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
		model.setNumRows(ImportGUI.logic.size());
		model.setColumnIdentifiers(EmailerClientGUI.columnNames);
		tblMain.setModel(model);
		
	}

	/**
	 * This method builds the arrays using the file the user selected.
	 * It reaches back to the main import import gui class.
	 */
	public void initArrays() {
		lastNames = ImportGUI.logic.getLastNames();
		firstNames = ImportGUI.logic.getFirstNames();
		emails = ImportGUI.logic.getEmails();
		birthdays = ImportGUI.logic.getBirthdays();
		anniv = ImportGUI.logic.getAnniv();
		
		for (int i = 0; i < ImportGUI.logic.size(); i++) {
			tblMain.setValueAt(lastNames[i], i, 0);
			tblMain.setValueAt(firstNames[i], i, 1);
			tblMain.setValueAt(emails[i], i, 2);
			tblMain.setValueAt(birthdays[i], i, 3);
			tblMain.setValueAt(anniv[i], i, 5);
		}
	}
	
	/**
	 * This method sets up the OK button. If the user says all is well,
	 * he or she clicks okay. The ok button then enables the finalize import
	 * button in the import gui class.
	 */
	public void setupOK() {
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportGUI.enableFinalize();
				frame.dispose();
			}
		});
	}
	
	/**
	 * This method sets up the cancel button.
	 */
	public void setupCancel() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
	

}
