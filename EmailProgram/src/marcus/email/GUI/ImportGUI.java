package marcus.email.GUI;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Component;

/**
 * This class enables the user to view and add patrons from a file.
 * @author Marcus
 *
 */
public class ImportGUI {

	public JDialog frmMain;
	private JPanel pnlSimple;
	private JButton btnCancel;
	private JButton btnPreview;
	private JTextField txtFile;
	private JButton btnBrowse;
	private JPanel panel_5;
	
	protected ImportLogic logic;
	private JLabel lblTheSimpleImport;
	private JButton btnFinalize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ImportGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImportGUI() {
		initialize();
		setupBrowse();
		setupCancel();
		setupPreviewButton();
		setupFinalize();
		frmMain.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JDialog(EmailerClientGUI.frmMfhEmailer, "Import", true);
		frmMain.setBounds(100, 100, 600, 330);
		frmMain.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMain.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		pnlSimple = new JPanel();
		tabbedPane.addTab("Simple Import", null, pnlSimple, null);
		pnlSimple.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		pnlSimple.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblInstructionsFour = new JLabel("Use a tab-delimited text file. Format: Last, First, Email, Birthday, Anniversary (YYYY/MM/DD)");
		lblInstructionsFour.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblInstructionsFour.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblInstructionsFour);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_2, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		
		btnPreview = new JButton("Preview Import");
		panel_2.add(btnPreview);
		
		btnFinalize = new JButton("Finalize Import");
		panel_2.add(btnFinalize);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		txtFile = new JTextField();
		txtFile.setEditable(false);
		txtFile.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(txtFile);
		txtFile.setColumns(30);
		
		btnBrowse = new JButton("Browse");
		panel_4.add(btnBrowse);
		
		panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		lblTheSimpleImport = new JLabel("The simple import merges a list of patrons with your existing database.");
		lblTheSimpleImport.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblTheSimpleImport, BorderLayout.CENTER);
	}
	
	/**
	 * This method sets up the browse button. If the user clicks the browse button,
	 * he/she can select a file to import. 
	 */
	public void setupBrowse() {
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = jfc.showOpenDialog(frmMain);
				if (result == JFileChooser.OPEN_DIALOG) {
						if (!jfc.getSelectedFile().getAbsolutePath().endsWith(".txt")) {
							JOptionPane.showMessageDialog(new JFrame(), "Must be a text file. Try again.");
						}
						if (!jfc.getSelectedFile().canRead()) {
							JOptionPane.showMessageDialog(new JFrame(), "Cannot read from file. Try again.");
						}
						if (jfc.getSelectedFile().canRead()) {
							txtFile.setText(jfc.getSelectedFile().getPath());
						}
				}
			}
		});
	}
	
	/**
	 * This sets up the preview button. The preview button passes the
	 * file path to the preview in order to gather the users prior to adding
	 * them to the database.
	 */
	public void setupPreviewButton() {
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtFile.getText() == null || txtFile.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "No file selected. Select a file.");
				} else {
					logic = new ImportLogic(new File(txtFile.getText()));
					new ImportPreviewGUI(logic);
					enableFinalize();
				}
			}
		});
	}
	
	/**
	 * This method sets up the cancel button.
	 */
	public void setupCancel() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMain.dispose();
			}
		});
	}
	
	/**
	 * This sets up the finalize button. It adds the list of patrons to the
	 * database.
	 */
	public void setupFinalize() {
		btnFinalize.setEnabled(false);
		btnFinalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ImportStatusGUI(logic);
				frmMain.dispose();
			}
		});
	}
	
	/**
	 * This method enables the finalize button. It can only be enabled
	 * after the user previews the list.
	 */
	public void enableFinalize() {
		btnFinalize.setEnabled(true);
	}
}
