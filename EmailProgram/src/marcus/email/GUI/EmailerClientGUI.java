package marcus.email.GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import marcus.email.database.Patron;
import marcus.email.util.EmailStorage;
import marcus.email.util.PatronDBLogic;
import marcus.email.util.time.AutoSender;

import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JTable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.BoxLayout;
import java.awt.Component;

/**
 * This class is the main GUI for the program. It consists of a tabbed pane and the
 * main panels that add the user information.
 * @author Marcus
 */
public class EmailerClientGUI {

	public EmailGUI g;
	public static JFrame frmMfhEmailer;
	public static EmailStorage emailStorage;
	private JTextField txtSearch;
	private static JTable table;
	public static final int COL_COUNT = 6;
	private static DefaultTableModel tableModel;
	public static PatronDBLogic dblogic = new PatronDBLogic();
	protected static Properties prop;
	
	private AutoSender theSender;
	
	//Template strings
	private String templateBirthday;
	private String templateAnniv;
	private String templatePromo;

	//Tray icon dismissal
	private Dismiss dismiss;

	//This controls how the user can search
	private boolean searchEmails = false;
	private static final String srchPatrons = "Search: Patrons";
	private static final String srchEmails = "Search: Emails";
	private JLabel lblNewLabel_1;
	private JButton btnEditSelected;

	//Menu selections
	private JMenuItem mnuServerSettings;
	private JMenuItem mnuCredentials;
	private JMenuItem mnuBackup;
	private JMenuItem mnuImport;

	//The string contains the column names/headers
	public static final String[] columnNames = {"Last Name", "First Name", "Email", "Birthday", "Date Added", "Anniversary"};

	//These constants are for accessing the column headers
	public static final int COL_LAST = 0;
	public static final int COL_FIRST = 1;
	public static final int COL_EMAIL = 2;
	public static final int COL_BIRTH = 3;
	public static final int COL_ADDED = 4;
	public static final int COL_ANNIV = 5;
	private JLabel lblTimeDate;
	private JButton btnEdit;
	private static JComboBox<String> comboEmails;
	private JButton btnNewTemplate;
	private JButton btnDelete;
	private JButton btnPromo;
	private JPanel pnlTime;
	private JMenu mnNewMenu;
	private JMenuItem mnuExit;
	private JButton btnSetBirth;
	private JButton btnSetAnniv;
	private JButton btnSetPromo;
	private JLabel lblTemplateBirthday;
	private JLabel lblTemplateAnniv;
	private JLabel lblPromoTemplate;
	private JToggleButton tglbtnBirthdays;
	private JToggleButton tglbtnAnniv;
	

	//Auto-generated run menu
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new EmailerClientGUI();
					EmailerClientGUI.frmMfhEmailer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Basic load sequence method calls
	public EmailerClientGUI() {
		//dblogic.load();
		setupEmailStorage();
		initialize();
		setupFrameSaveFeature();
		setupServerSettings();
		setupCredentials();
		setupBackup();
		setupImport();
		setupEditAndTemplate();
		configurePromo();
		setupTimer();
		setupDismiss();
		setupExit();
		setTemplateButtons();
		configureEnableButtons();
		
		setupSender();
	
	}
	

	
	
	
	/**
	 * This method starts and initializes the autosender.
	 */
	private void setupSender() {
		theSender = new AutoSender();
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
		frmMfhEmailer.setBounds(100, 100, 1000, 800);
		frmMfhEmailer.setDefaultCloseOperation(JFrame.ICONIFIED);

		JMenuBar menuBar = new JMenuBar();
		frmMfhEmailer.setJMenuBar(menuBar);

		mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		mnuExit = new JMenuItem("Exit");
		mnNewMenu.add(mnuExit);

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

		mnuImport = new JMenuItem("Import");
		mnNewMenu_2.add(mnuImport);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane.addTab("Emails", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(null);
		panel_7.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_8.add(panel_11, BorderLayout.SOUTH);

		JPanel panel_15 = new JPanel();
		panel_8.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new BorderLayout(0, 0));

		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_15.add(panel_16, BorderLayout.NORTH);
		panel_16.setLayout(new BorderLayout(0, 0));

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(null);
		panel_16.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));

		JLabel lblNewLabel = new JLabel("Promotional Emails:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(lblNewLabel);

		JPanel panel_19 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_19.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel_9.add(panel_19);
		
		lblPromoTemplate = new JLabel("Using Template:");
		panel_19.add(lblPromoTemplate);

		btnPromo = new JButton("Run Promo");
		panel_19.add(btnPromo);
		for (int i = 0; i < emailStorage.size(); i++) {
			comboEmails.addItem(emailStorage.get(i).toString());
		}
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_15.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_27 = new JPanel();
		panel_12.add(panel_27, BorderLayout.NORTH);
		panel_27.setLayout(new BoxLayout(panel_27, BoxLayout.X_AXIS));
		
		JLabel lblTempConfig = new JLabel("Template Configuration:");
		panel_27.add(lblTempConfig);
				
				JPanel panel_30 = new JPanel();
				panel_27.add(panel_30);
				panel_30.setLayout(new BoxLayout(panel_30, BoxLayout.X_AXIS));
				
				JPanel panel_29 = new JPanel();
				panel_30.add(panel_29);
				FlowLayout flowLayout_7 = (FlowLayout) panel_29.getLayout();
				flowLayout_7.setAlignment(FlowLayout.RIGHT);
		
				comboEmails = new JComboBox<String>();
				panel_29.add(comboEmails);
						
								btnNewTemplate = new JButton("Add New Template");
								panel_29.add(btnNewTemplate);
						
								btnEdit = new JButton("Edit Selected");
								panel_29.add(btnEdit);
				
						btnDelete = new JButton("Delete Selected");
						panel_29.add(btnDelete);
						
						JPanel panel_18 = new JPanel();
						panel_12.add(panel_18, BorderLayout.CENTER);
						panel_18.setLayout(new BorderLayout(0, 0));
						
						JPanel panel_22 = new JPanel();
						panel_18.add(panel_22, BorderLayout.NORTH);
						panel_22.setLayout(new BoxLayout(panel_22, BoxLayout.X_AXIS));
						
						JLabel lblSetSelected = new JLabel("Set Selected Template As:");
						panel_22.add(lblSetSelected);
						
						JPanel panel_28 = new JPanel();
						FlowLayout flowLayout_3 = (FlowLayout) panel_28.getLayout();
						flowLayout_3.setAlignment(FlowLayout.RIGHT);
						panel_22.add(panel_28);
						
						btnSetBirth = new JButton("BirthdayTemplate");
						panel_28.add(btnSetBirth);
						
						btnSetAnniv = new JButton("Anniversary Template");
						panel_28.add(btnSetAnniv);
						
						btnSetPromo = new JButton("Promo Template");
						panel_28.add(btnSetPromo);

		JPanel panel_17 = new JPanel();
		panel_17.setBorder(null);
		panel_5.add(panel_17, BorderLayout.NORTH);
		panel_17.setLayout(new BoxLayout(panel_17, BoxLayout.Y_AXIS));
		
		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_17.add(panel_21);
				panel_21.setLayout(new BoxLayout(panel_21, BoxLayout.X_AXIS));
		
				JPanel panel_20 = new JPanel();
				panel_21.add(panel_20);
										panel_20.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
								
										JLabel lblStatus = new JLabel("Currently Auto-Sending Birthday Emails:");
										panel_20.add(lblStatus);
										lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
																				
																				JPanel panel_23 = new JPanel();
																				FlowLayout flowLayout_2 = (FlowLayout) panel_23.getLayout();
																				flowLayout_2.setAlignment(FlowLayout.RIGHT);
																				panel_21.add(panel_23);
																						
																						lblTemplateBirthday = new JLabel("Using Template:");
																						panel_23.add(lblTemplateBirthday);
																				
																				
																						tglbtnBirthdays = new JToggleButton("Status");
																						panel_23.add(tglbtnBirthdays);
																						
																						JPanel panel_24 = new JPanel();
																						panel_24.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
																						panel_17.add(panel_24);
																						panel_24.setLayout(new BoxLayout(panel_24, BoxLayout.X_AXIS));
																						
																						JPanel panel_25 = new JPanel();
																						FlowLayout flowLayout_5 = (FlowLayout) panel_25.getLayout();
																						flowLayout_5.setAlignment(FlowLayout.LEFT);
																						panel_24.add(panel_25);
																						
																						JLabel lblAnniv = new JLabel("Currently Auto-Sending Anniversary Emails:");
																						panel_25.add(lblAnniv);
																						
																						JPanel panel_26 = new JPanel();
																						FlowLayout flowLayout_6 = (FlowLayout) panel_26.getLayout();
																						flowLayout_6.setAlignment(FlowLayout.RIGHT);
																						panel_24.add(panel_26);
																						
																						lblTemplateAnniv = new JLabel("Using Template:");
																						panel_26.add(lblTemplateAnniv);
																						
																						tglbtnAnniv = new JToggleButton("Status");
																						panel_26.add(tglbtnAnniv);

		pnlTime = new JPanel();
		frmMfhEmailer.getContentPane().add(pnlTime, BorderLayout.SOUTH);
		pnlTime.setLayout(new BoxLayout(pnlTime, BoxLayout.X_AXIS));

		JPanel panel_14 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_14.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlTime.add(panel_14);

		lblNewLabel_1 = new JLabel(srchPatrons);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(lblNewLabel_1);

		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_13.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnlTime.add(panel_13);

		lblTimeDate = new JLabel();
		panel_13.add(lblTimeDate);
	}
	
	/**
	 * This method configures the set template buttons to set the labels to whatever the template
	 * is selected by the user.
	 */
	public void setTemplateButtons() {
		btnSetBirth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSetBirth) {
					lblTemplateBirthday.setText("Using Template: " + comboEmails.getSelectedItem());
					templateBirthday = (String) comboEmails.getSelectedItem();
				}
			}
		});
		btnSetAnniv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSetAnniv) {
					lblTemplateAnniv.setText("Using Template: " + comboEmails.getSelectedItem());
					templateAnniv = (String) comboEmails.getSelectedItem();
				}
			}
		});
		btnSetPromo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSetPromo) {
					lblPromoTemplate.setText("Using Template: " + comboEmails.getSelectedItem());
					templatePromo = (String) comboEmails.getSelectedItem();
				}
			}
		});
	
	}
	
	
	
	
	/**
	 * This method returns the get selected template from the combo emails.
	 * @return
	 */
	public static String getSelectedTemplate() {
		return (String) comboEmails.getSelectedItem();
	}

	//Launches import GUI method
	public void setupImport() {
		mnuImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ImportGUI();
			}
		});
	}

	//Launches server settings method
	public void setupServerSettings() {
		mnuServerSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmailSettingsGUI();
			}
		});
	}

	//Kills the application
	public void setupExit() {
		mnuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == mnuExit) {
					System.exit(0);
				}
			}
		});
	}

	//Password change menu
	public void setupCredentials() {
		mnuCredentials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CredentialsGUI();
			}
		});
	}

	//Database backup operation
	public void setupBackup() {
		mnuBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BackupGUI();
			}
		});
	}

	//Saves when the window closes
	//TODO: Reenable for final testing
	public void setupFrameSaveFeature() {
		frmMfhEmailer.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {	}
			public void windowClosing(WindowEvent e) {
				//dblogic.save();
			}
			public void windowClosed(WindowEvent e) {	}
			public void windowActivated(WindowEvent e) {	}
		});;
	}

	//Switches the search logic by emails or patrons
	public void searchSwitch() {
		if (searchEmails) {
			searchEmails = false;
			lblNewLabel_1.setText(srchPatrons);
		} else {
			searchEmails = true;
			lblNewLabel_1.setText(srchEmails);
		}
	}

	//The swing timer updates the actual timer instantiated via the 
	// Quartz timer class
	public void setupTimer() {
		marcus.email.util.time.Timer actualTime = new marcus.email.util.time.Timer();
		actualTime.start();
		ActionListener l = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTimeDate.setText(actualTime.getCurrentTime());
			}
		};
		//This is the Swing timer class that updates the actual timer
		Timer t = new Timer(100, l);
		t.start();
	}

	//Shuts the whole thing down--from dismiss menu
	public static void exitAll() {
		System.exit(0);
	}

	//Sets visible--for tray menus
	public static void setInvisible() {
		frmMfhEmailer.setVisible(false);
	}

	//Sets invisible--for tray menus
	public static void setVisible() {
		frmMfhEmailer.setVisible(true);
	}

	public void setupDismiss() {
		dismiss = new Dismiss();
	}


	public void doDismiss() {
		dismiss.sendToTray();
	}

	//Initializes email storage
	public void setupEmailStorage() {
		emailStorage = new EmailStorage();
	}

	//Assigns action listeners
	public void setupEditAndTemplate() {
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnEdit) {
					String templateName = (String)comboEmails.getSelectedItem();
					if (templateName != null) {
						//TODO: Fix this--argument needed is not NULL but the template subject is needed
						g = new EmailGUI(emailStorage.getTemplateByName(templateName), true);
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "Nothing selected.");
					}
				}
			}
		});
		//New Template button
		btnNewTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewTemplate) {
					g = new EmailGUI(null, false);
				}
			}
		});
		//Delete template button
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnDelete && comboEmails.getSelectedItem() != null) {
					JFrame frmConfirm = new JFrame("Confirm Deletetion");
					int input = JOptionPane.showConfirmDialog(frmConfirm, "Are you sure?");
					if (input == JOptionPane.CANCEL_OPTION) {
						frmConfirm.dispose();
					}
					if (input == JOptionPane.NO_OPTION) {
						frmConfirm.dispose();
					}
					if (input == JOptionPane.YES_OPTION) {
						String templateName = (String) comboEmails.getSelectedItem();
						emailStorage.deleteTemplateByName(templateName);
						updateCombo();
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Nothing selected.");
				}
			}
		});
	}


	//Promo button setup
	private void configurePromo() {
		btnPromo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnPromo) {
					new RunPromoGUI();
				}
			}
		});
	}
	
	//Configures the enable buttons and their logic
	private void configureEnableButtons() {
		tglbtnBirthdays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == tglbtnBirthdays) {
					if (tglbtnBirthdays.isSelected()) {
						tglbtnBirthdays.setText("Disable");
						theSender.setBirthdayTemplate(emailStorage.getTemplate(templateBirthday));
						theSender.runBirthdaySetUp();
						theSender.resumeSendingBirthdays();
					}
					if (!tglbtnBirthdays.isSelected()) {
						tglbtnBirthdays.setText("Enable");
						theSender.stopSendingBirthdays();
					}
				}
				
			}
		});
		tglbtnAnniv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == tglbtnAnniv) {
					if (tglbtnAnniv.isSelected()) {
						tglbtnAnniv.setText("Disable");
						theSender.setAnnivTemplate(emailStorage.getTemplate(templateAnniv));
						theSender.runAnnivSetUp();
						theSender.resumeSendingAnniv();
					}
					if (!tglbtnAnniv.isSelected()) {
						tglbtnAnniv.setText("Enable");
						theSender.stopSendingAnniv();
					}
				}
				
			}
		});
		
	}

	//Updates combo box
	public static void updateCombo() {
		comboEmails.removeAllItems();
		String [] templateNames = emailStorage.getTemplateNames();
		for (int i = 0; i < emailStorage.size(); i++) {
			comboEmails.addItem(templateNames[i]);
		}
	}

	//*****TABLE LOGIC FOLLOWS BELOW*****//	
	//Sets up the table
	public static void buildTable() {
		//This method keeps the user from editing the table
		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				//All cells false
				return false;
			}
		};

		tableModel.setNumRows(dblogic.getSize());
		tableModel.setColumnCount(COL_COUNT);
		tableModel.setColumnIdentifiers(columnNames);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setDragEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(tableModel);

		//Build rows
		for (int i = 0; i < dblogic.getSize(); i++) {
			table.setValueAt(dblogic.getPatronFromDB(i).getLastName(), i, COL_LAST);
			table.setValueAt(dblogic.getPatronFromDB(i).getFirstName(), i, COL_FIRST);
			table.setValueAt(dblogic.getPatronFromDB(i).getPatronEmail(), i, COL_EMAIL);
			table.setValueAt(dblogic.getPatronFromDB(i).getDOB(), i, COL_BIRTH);
			table.setValueAt(dblogic.getPatronFromDB(i).getPatronSinceString(), i, COL_ADDED);
			table.setValueAt(dblogic.getPatronFromDB(i).getAnniv().toString(), i, COL_ANNIV);
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
			table.setValueAt(partialEmail.get(i).getLastName(), i, COL_LAST);
			table.setValueAt(partialEmail.get(i).getFirstName(), i, COL_FIRST);
			table.setValueAt(partialEmail.get(i).getPatronEmail(), i, COL_EMAIL);
			table.setValueAt(partialEmail.get(i).getPatronDOB(), i, COL_BIRTH);
			table.setValueAt(partialEmail.get(i).getPatronSince(), i, COL_ADDED);
			table.setValueAt(partialEmail.get(i).getAnniv().toString(), i, COL_ANNIV);
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
			table.setValueAt(partialLastList.get(i).getLastName(), i, COL_LAST);
			table.setValueAt(partialLastList.get(i).getFirstName(), i, COL_FIRST);
			table.setValueAt(partialLastList.get(i).getPatronEmail(), i, COL_EMAIL);
			table.setValueAt(partialLastList.get(i).getPatronDOB(), i, COL_BIRTH);
			table.setValueAt(partialLastList.get(i).getPatronSince(), i, COL_ADDED);
			table.setValueAt(partialLastList.get(i).getAnniv().toString(), i, COL_ANNIV);
		}
	}

	//Alphabetizes the names in the table
	public static void alphabetize() {
		ArrayList<Patron> alphabetic = dblogic.getAlphabetic();
		resetTable();
		for (int i = 0; i < alphabetic.size(); i++) {
			table.setValueAt(alphabetic.get(i).getLastName(), i, COL_LAST);
			table.setValueAt(alphabetic.get(i).getFirstName(), i, COL_FIRST);
			table.setValueAt(alphabetic.get(i).getPatronEmail(), i, COL_EMAIL);
			table.setValueAt(alphabetic.get(i).getPatronDOB(), i, COL_BIRTH);
			table.setValueAt(alphabetic.get(i).getPatronSince(), i, COL_ADDED);
			table.setValueAt(alphabetic.get(i).getAnniv().toString(), i, COL_ANNIV);
		}
	}

	//Rebuild/updates the table
	public static void resetTable() {
		tableModel.setRowCount(0); //Clear the table
		tableModel.setNumRows(dblogic.getSize());
		tableModel.setColumnCount(COL_COUNT);
		table.setRowSelectionAllowed(true);
		table.setModel(tableModel);
	}

}
