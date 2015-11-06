package marcus.email.GUI;

import java.awt.EventQueue;

//import javax.mail.Header;
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
import javax.swing.table.JTableHeader;

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
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import javax.swing.JTable;

import java.awt.List;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JScrollBar;
import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.Box;


public class EmailerClientGUI {
	//Set up the frame
	
	public static JFrame frmMfhEmailer;
	private JTextField txtSearch;
	private JTextField textField_1;
	private static JTable table;
	public static final int COL_COUNT = 6;
	private static DefaultTableModel tableModel;
	protected static PatronDBLogic dblogic = new PatronDBLogic();
	protected static Properties prop;

	//This controls how the user can search
	private boolean searchEmails = false;
	private static final String srchPatrons = "Search: Patrons";
	private static final String srchEmails = "Search: Emails";
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnEditSelected;
	private JMenuItem mnuServerSettings;
	private JMenuItem mnuCredentials;
	private JMenuItem mnuBackup;
	private static Vector<String> header;
	
	private static String[] columnNames = {"Last Name", "First Name", "Email", "Birthday", "Date Added", "Anniversary"};

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
		//dblogic.load();
		initialize();
		setupFrameSaveFeature();
		setupServerSettings();
		setupCredentials();
		setupBackup();
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
	 * This method starts the properties file.
	 */
	public void setupProp() {
		prop = new Properties();
		InputStream in = getClass().getResourceAsStream("C:/Users/Marcus/git/EmailProgram/EmailProgram/resources/config.properties");
		try {
			prop.load(in);
			FileInputStream fin = new FileInputStream("C:/Users/Marcus/git/EmailProgram/EmailProgram/resources/config.properties");
		    ObjectInputStream ois = new ObjectInputStream(fin);
		    dblogic = (PatronDBLogic) ois.readObject();
		    ois.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Settings");
		menuBar.add(mnNewMenu_1);
		
		mnuCredentials = new JMenuItem("Credentials");
		mnNewMenu_1.add(mnuCredentials);
		
		mnuServerSettings = new JMenuItem("Server Settings");
		mnNewMenu_1.add(mnuServerSettings);
		
		JMenu mnNewMenu_2 = new JMenu("Database");
		menuBar.add(mnNewMenu_2);
		
		mnuBackup = new JMenuItem("Backup");
		mnNewMenu_2.add(mnuBackup);
		
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

		btnEditSelected = new JButton("Edit Selected");
		panel_3.add(btnEditSelected);
		btnEditSelected.addActionListener(new ActionListener() {
		//Opens the edit patron section
			public void actionPerformed(ActionEvent e) {
				int rowEmail = table.getSelectedRow();
				if (rowEmail > -1) {
					new EditPatronGUI((String) table.getValueAt(rowEmail, 0),
							(String)table.getValueAt(rowEmail, 1),
							(String)table.getValueAt(rowEmail, 2), 
							(String)table.getValueAt(rowEmail, 3).toString(),
							(String)table.getValueAt(rowEmail, 5).toString());
				}
			}
		});

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
	
	
	
	
	
	/**
	 * This method configures the settings button. The button
	 * launches the settings page.
	 */
	public void setupServerSettings() {
		mnuServerSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmailSettingsGUI();
			}
		});
	}
	
	/**
	 * This method configures the credentials button. The button
	 * launches the credentials page.
	 */
	public void setupCredentials() {
		mnuCredentials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CredentialsGUI();
			}
		});
	}
	
	/**
	 * This method sets up the database backup button. The button
	 * launches the backup page.
	 */
	public void setupBackup() {
		mnuBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BackupGUI();
			}
		});
	}
	
	/**
	 * This method adds a window close event to the frame for saving.
	 */
	public void setupFrameSaveFeature() {
		frmMfhEmailer.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			public void windowClosing(WindowEvent e) {
				//dblogic.save();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});;
	}
	
	
//*****TABLE LOGIC FOLLOWS BELOW*****//	
	/**
	 * This method sets up the table.
	 */
	public static void buildTable() {
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
		tableModel.setColumnIdentifiers(columnNames);

		table.setRowSelectionAllowed(true);
		
		
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
		
		for (int i = 0; i < dblogic.getSize(); i++) {
			table.setValueAt(dblogic.getPatronFromDB(i).getAnniv().toString(), i, 5);
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
		
		for (int i = 0; i < partialEmail.size(); i++) {
			table.setValueAt(partialEmail.get(i).getAnniv().toString(), i, 5);
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
		
		for (int i = 0; i < partialLastList.size(); i++) {
			table.setValueAt(partialLastList.get(i).getAnniv().toString(), i, 5);
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
		
		for (int i = 0; i < alphabetic.size(); i++) {
			table.setValueAt(alphabetic.get(i).getAnniv().toString(), i, 5);
		}
		
	}
	
	/**
	 * This method resets the table.
	 */
	public static void resetTable() {
		tableModel.setRowCount(0); //Clear the table
		tableModel.setNumRows(dblogic.getSize());
		tableModel.setColumnCount(COL_COUNT);
		table.setRowSelectionAllowed(true);
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
