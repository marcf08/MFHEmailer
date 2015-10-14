package marcus.email.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Label;

import javax.swing.JPanel;
import javax.swing.JList;


import javax.swing.JScrollPane;
import javax.swing.JTextField;


import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;

import marcus.email.util.PatronDBLogic;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTable;

import java.awt.List;
import javax.swing.JScrollBar;


public class EmailerClientGUI {

	private static JFrame frmMfhEmailer;
	private JTextField txtSearch;
	private JTextField textField_1;
	private JList<String> list;
	private static JTable table;
	
	private PatronDBLogic dblogic = new PatronDBLogic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailerClientGUI window = new EmailerClientGUI();
					window.frmMfhEmailer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmailerClientGUI() {
		initialize();
	}
	
	/**
	 * This method is an enable feature for use
	 * when opening other JFrames.
	 */
	public static void enableMainGUI() {
		frmMfhEmailer.setEnabled(true);
	}
	
	/**
	 * This method is an enable feature for use
	 * when opening other JFrames.
	 */
	public static void disableMainGUI() {
		frmMfhEmailer.setEnabled(false);
	}
	
	/**
	 * This method validates the static frame.
	 */
	public static void validate() {
		frmMfhEmailer.validate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMfhEmailer = new JFrame();
		frmMfhEmailer.setTitle("MFH Emailer");
		frmMfhEmailer.setBounds(100, 100, 450, 300);
		frmMfhEmailer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMfhEmailer.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Suspend");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Settings");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Application Settings");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Credentials");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Server Settings");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("Database");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Backup");
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Export");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_3 = new JMenu("Help");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("View Help");
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		Label label = new Label("Network Time:");
		label.setAlignment(Label.RIGHT);
		frmMfhEmailer.getContentPane().add(label, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMfhEmailer.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Patrons", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		txtSearch = new JTextField();
		txtSearch.setText("Search Patrons");
		txtSearch.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				txtSearch.setText("Search Patrons");
			}
			public void focusGained(FocusEvent e) {
				txtSearch.setText("");
			}
		});
		
		txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(txtSearch, BorderLayout.NORTH);
		txtSearch.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New Patron");
		panel_3.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
		//Opens the add patron section	
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton) {
					AddPatronDialog d = new AddPatronDialog();
					d.setVisible(true);
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Edit Selected");
		panel_3.add(btnNewButton_1);

		table = new JTable(dblogic.getSize(), 5);
		buildTable();
		panel_2.add(table, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Status", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_6.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		TextField textField = new TextField();
		panel_10.add(textField, BorderLayout.CENTER);
		
		Button button = new Button("Export Log");
		panel_10.add(button, BorderLayout.SOUTH);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Import");
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane.addTab("Emails", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Current Email Template:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		panel_9.add(lblNewLabel);
		
		textField_1 = new JTextField();
		panel_9.add(textField_1);
		textField_1.setColumns(15);
		
		JButton btnNewButton_2 = new JButton("Browse");
		panel_9.add(btnNewButton_2);
		
		JPanel panel_11 = new JPanel();
		panel_8.add(panel_11, BorderLayout.SOUTH);
		
		JButton btnNewButton_3 = new JButton("New Promotion Email");
		panel_11.add(btnNewButton_3);
	}
	
	public void buildTable() {
		table.setRowSelectionAllowed(true);
		table.setTableHeader(null);
		
		for (int i = 0; i < dblogic.getSize(); i++) {
			table.setValueAt(dblogic.getPatronFromDB(i).getLast(), i, 0);
		}
		
		for (int i = 0; i < dblogic.getSize(); i++) {
			table.setValueAt(dblogic.getPatronFromDB(i).getFirst(), i, 1);
		}
		
		for (int i = 0; i < dblogic.getSize(); i++) {
			table.setValueAt(dblogic.getPatronFromDB(i).getPatronEmail(), i, 2);
		}
		
		
		for (int i = 0; i < dblogic.getSize(); i++) {
			table.setValueAt(dblogic.getPatronFromDB(i).getDOB(), i, 3);
		}
		
		for (int i = 0; i < dblogic.getSize(); i++) {
			table.setValueAt(dblogic.getPatronFromDB(i).getPatronSinceString(), i, 4);
		}
		
	
		
		
		//for (int i = 0; i < dblogic.getSize(); i++) {
		//	table.setValueAt(dblogic.getPatronFromDB(i).getPatronSince(), i, 4);
		//}
	}
	
		

}
