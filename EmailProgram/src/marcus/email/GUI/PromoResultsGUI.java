package marcus.email.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class PromoResultsGUI {
	//Swing members
	private JDialog frmPromotionalResults;
	private JButton btnOk;
	private JLabel lblInstructions;
	private JScrollPane jsp;
	private JTextArea txtResults;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromoResultsGUI window = new PromoResultsGUI(null);
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
	public PromoResultsGUI(String results) {
		initialize();
		setupTextArea();
		setupRightClick();
		txtResults.setText(results);
		setupOK();
		frmPromotionalResults.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPromotionalResults = new JDialog();
		frmPromotionalResults.setTitle("Results");
		frmPromotionalResults.setModal(true);
		frmPromotionalResults.setBounds(100, 100, 450, 300);
		frmPromotionalResults.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
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
	private void setupTextArea() {
		txtResults = new JTextArea();
		jsp = new JScrollPane();
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setViewportView(txtResults);
		txtResults.setLineWrap(true);
		txtResults.setEditable(false);
		frmPromotionalResults.getContentPane().add(jsp, BorderLayout.CENTER);
	}
	
	//Enables right click
	private void setupRightClick() {
		txtResults.addMouseListener(new RightClickLogic());
	}
	
	//Enables the ok button
	private void setupOK() {
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnOk) {
					frmPromotionalResults.dispose();
				}
			}
		});
	}
	

}
