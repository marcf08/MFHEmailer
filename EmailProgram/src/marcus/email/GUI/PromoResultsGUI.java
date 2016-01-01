package marcus.email.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Desktop.Action;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class PromoResultsGUI {
	//Swing members
	private JFrame frmPromotionalResults;
	private JButton btnOk;
	private JLabel lblInstructions;
	private JScrollPane jsp;
	private JTextArea txtResults;
	
	//Swing members for right click
	private JPopupMenu pop;
	private Action copyAction;
	private Action selectAllAction;
	private enum Actions { COPY, SELECT_ALL };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromoResultsGUI window = new PromoResultsGUI();
					window.frmPromotionalResults.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PromoResultsGUI() {
		initialize();
		setupTextArea();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPromotionalResults = new JFrame();
		frmPromotionalResults.setTitle("Results");
		frmPromotionalResults.setBounds(100, 100, 450, 300);
		frmPromotionalResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmPromotionalResults.getContentPane().add(panel, BorderLayout.SOUTH);
		
		btnOk = new JButton("OK");
		panel.add(btnOk);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmPromotionalResults.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		lblInstructions = new JLabel("Results of Sending Promotional Batch");
		panel_1.add(lblInstructions);
	}
	
	/**
	 * This method builds the text area.
	 */
	public void setupTextArea() {
		txtResults = new JTextArea();
		jsp = new JScrollPane();
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setViewportView(txtResults);
		txtResults.setLineWrap(true);
		txtResults.setEditable(false);
		frmPromotionalResults.getContentPane().add(jsp, BorderLayout.CENTER);
	}
	
	/**
	 * This method enables the right click
	 */
	public void setupRightClick() {
	
		
	}
	

}
