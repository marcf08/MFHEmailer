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
import javax.swing.table.DefaultTableModel;

import marcus.email.database.Patron;
import marcus.email.util.PatronDBLogic;

import java.awt.Dimension;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTable;

import java.awt.List;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import java.awt.Component;
import javax.swing.Box;


public class EmailerClientGUI {

	public static JFrame frmMfhEmailer;
	private JTextField txtSearch;
	private JTextField textField_1;
	private static JTable table;
	public static final int COL_COUNT = 5;
	private static DefaultTableModel tableModel;
	protected static PatronDBLogic dblogic = new PatronDBLogic();

	//This controls how the user can search
	private boolean searchEmails = false;
	private static final String srchPatrons = "Search: Patrons";
	private static final String srchEmails = "Search: Emails";
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

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
		//Was 100,100,450,300
		frmMfhEmailer.setBounds(100, 100, 800, 400);
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
		
		txtSearch.addKeyListener(new KeyListener() {
			//Search for names when a key is typed
			public void keyTyped(KeyEvent e) {
				int k = e.getKeyCode();
				//Use alpha numeric to search
				if (((k >= 65)&&( k <= 90))||((k >= 97) && (k <= 122))) {
					String partialLast = txtSearch.getText();
					searchLogic(partialLast);
					table.validate();
				}
					
			}
			//Search if the user deletes/backspaces
			public void keyReleased(KeyEvent e) {
				int k = e.getKeyCode();
				if (k == KeyEvent.VK_ALT) {
					searchSwitch();
				}
				if (((k >= 65)&&( k <= 90))||((k >= 97) && (k <= 122))) {
						if (!searchEmails) {
							String partialLast = txtSearch.getText();
							searchLogic(partialLast);
							table.validate();
						}
						if (searchEmails) {
							String partialEmail = txtSearch.getText();
							searchByEmail(partialEmail);
							table.validate();
						}
					}
					if (k == KeyEvent.VK_DELETE || k == KeyEvent.VK_BACK_SPACE) {
						if (!searchEmails) {
							String partialLast = txtSearch.getText();
							searchLogic(partialLast);
							table.validate();
						}
						if (searchEmails) {
							String partialEmail = txtSearch.getText();
							searchByEmail(partialEmail);
							table.validate();
						}
					}
					
				
			
			}
			//Sort the list at the user's command
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				if (k == KeyEvent.VK_CONTROL) {
					alphabetize();
					table.validate();
				}
			}
		});;


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
					new AddPatronGUI();		
					buildTable();
					alphabetize();
				}
		}});

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
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_12.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		frmMfhEmailer.getContentPane().add(panel_12, BorderLayout.SOUTH);
		
		JPanel panel_14 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_14.getLayout();
		panel_12.add(panel_14);
		
		lblNewLabel_1 = new JLabel(srchPatrons);
		panel_14.add(lblNewLabel_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(425);
		panel_12.add(horizontalStrut);
		
		lblNewLabel_2 = new JLabel("New label");
		panel_12.add(lblNewLabel_2);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//*****TABLE LOGIC FOLLOWS BELOW*****//	
	/**
	 * This method sets up the table.
	 */
	public void buildTable() {
		//This method keeps the user from editing the table
		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		tableModel.setNumRows(dblogic.getSize());
		tableModel.setColumnCount(COL_COUNT);

		table.setRowSelectionAllowed(true);
		table.setTableHeader(null);
		table.setModel(tableModel);
		
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
		
	}
	/**
	 * The search logic is used for when the user types in the search field
	 * @param partialLast the partial last name
	 */
	public void searchByEmail(String partialE) {
		ArrayList<Patron> partialEmail = dblogic.getSortedByEmails(partialE);
		resetTable();
		for (int i = 0; i < partialEmail.size(); i++) {
			table.setValueAt(partialEmail.get(i).getLastName(), i, 0);
		}
		
		for (int i = 0; i < partialEmail.size(); i++) {
			table.setValueAt(partialEmail.get(i).getFirstName(), i, 1);
		}
		
		for (int i = 0; i < partialEmail.size(); i++) {
			table.setValueAt(partialEmail.get(i).getPatronEmail(), i, 2);
		}
		
		
		for (int i = 0; i < partialEmail.size(); i++) {
			table.setValueAt(partialEmail.get(i).getPatronDOB(), i, 3);
		}
		
		for (int i = 0; i < partialEmail.size(); i++) {
			table.setValueAt(partialEmail.get(i).getPatronSince(), i, 4);
		}
		
	}
	
	/**
	 * The search logic is used for when the user types in the search field
	 * @param partialLast the partial last name
	 */
	public void searchLogic(String partialLast) {
		ArrayList<Patron> partialLastList = dblogic.getMatching(partialLast);
		resetTable();
		for (int i = 0; i < partialLastList.size(); i++) {
			table.setValueAt(partialLastList.get(i).getLastName(), i, 0);
		}
		
		for (int i = 0; i < partialLastList.size(); i++) {
			table.setValueAt(partialLastList.get(i).getFirstName(), i, 1);
		}
		
		for (int i = 0; i < partialLastList.size(); i++) {
			table.setValueAt(partialLastList.get(i).getPatronEmail(), i, 2);
		}
		
		
		for (int i = 0; i < partialLastList.size(); i++) {
			table.setValueAt(partialLastList.get(i).getPatronDOB(), i, 3);
		}
		
		for (int i = 0; i < partialLastList.size(); i++) {
			table.setValueAt(partialLastList.get(i).getPatronSince(), i, 4);
		}
		
	}
	
	/**
	 * This method alphabetizes the names in the JTable.
	 */
	public void alphabetize() {
		ArrayList<Patron> alphabetic = dblogic.getAlphabetic();
		resetTable();
		for (int i = 0; i < alphabetic.size(); i++) {
			table.setValueAt(alphabetic.get(i).getLastName(), i, 0);
		}
		
		for (int i = 0; i < alphabetic.size(); i++) {
			table.setValueAt(alphabetic.get(i).getFirstName(), i, 1);
		}
		
		for (int i = 0; i < alphabetic.size(); i++) {
			table.setValueAt(alphabetic.get(i).getPatronEmail(), i, 2);
		}
		
		for (int i = 0; i < alphabetic.size(); i++) {
			table.setValueAt(alphabetic.get(i).getPatronDOB(), i, 3);
		}
		
		for (int i = 0; i < alphabetic.size(); i++) {
			table.setValueAt(alphabetic.get(i).getPatronSince(), i, 4);
		}
		
	}
	
	/**
	 * This method resets the table.
	 */
	public void resetTable() {
		tableModel.setRowCount(0); //Clear the table
		tableModel.setNumRows(dblogic.getSize());
		tableModel.setColumnCount(COL_COUNT);
		table.setRowSelectionAllowed(true);
		table.setTableHeader(null);
		table.setModel(tableModel);
	}
	
	/**
	 * This method controls whether to search emails or patrons.
	 */
	public void searchSwitch() {
		if (searchEmails) {
			searchEmails = false;
			lblNewLabel_1.setText(srchPatrons);
		} else {
			searchEmails = true;
			lblNewLabel_1.setText(srchEmails);
		}
	}
		

}
