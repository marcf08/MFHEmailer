package marcus.email.GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;

/**
 * This class enables the user to view and add patrons from a file.
 * @author Marcus
 *
 */
public class ImportGUI {

	private JDialog frmMain;
	private JPanel pnlSimple;
	private JButton btnCancel;
	private JButton btnFinalize;
	private JTextField textField;
	private JButton btnBrowse;
	private JProgressBar progressBar;
	private JPanel panel_5;
	
	protected static ImportLogic logic;

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

		
		
		
		frmMain.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JDialog();
		frmMain.setBounds(100, 100, 500, 300);
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
		
		JLabel lblInstructionsFour = new JLabel("Use a tab-delimited text file. Format: Last, First, Email, Birthday (YYYY/MM/DD)");
		panel_1.add(lblInstructionsFour);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.add(panel_2, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		
		btnFinalize = new JButton("Finalize Import");
		panel_2.add(btnFinalize);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(textField);
		textField.setColumns(30);
		
		btnBrowse = new JButton("Browse");
		panel_4.add(btnBrowse);
		
		progressBar = new JProgressBar();
		panel_3.add(progressBar, BorderLayout.SOUTH);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
	}

}
