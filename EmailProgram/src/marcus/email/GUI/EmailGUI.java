package marcus.email.GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EtchedBorder;

import marcus.email.util.EmailTemplate;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

public class EmailGUI extends JDialog {
	//Swing data members
	public JDialog contentPanel;
	private SaveDialogGUI save;
	private JButton btnTest;
	private JScrollPane scrollPane;
	private JTextArea textPane;
	private JButton cancelButton;
	private JButton btnSave;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField txtSubject;
	
	//Information needed
	private EmailTemplate templateToLoad;
	private String instructions = "<!-- Type/paste your html message here. Click parse below to preview."
			+ " Use the var tag to enter user data."
			+ "\nFor example, to insert the patron's first name anywhere in the "
			+ "email, use the following empty tags:-->";

	private String instrLastName = "<!-- \"<var id = \"lastName\"></var> -->";
	private String instrFirstName = "<!-- \"<var id = \"firstName\"></var> -->";
	private String instrSubject = "<!-- Use #first and/or #last to use a patron's name in the subject line -->";
	
	/**
	 * Create the dialog.
	 * @param htmlToLoad the html (if any) to load -- leave null if opening an add new template window
	 */
	public EmailGUI(EmailTemplate template, boolean isEdit) {
		this.templateToLoad = template;
		contentPanel = new JDialog(EmailerClientGUI.frmMfhEmailer, "Edit Email", true);
		contentPanel.setTitle("Edit HTML");
		contentPanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setBounds(100, 100, 1200, 800);
		contentPanel.getContentPane().setLayout(new BoxLayout(contentPanel.getContentPane(), BoxLayout.Y_AXIS));
		
		panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPanel.getContentPane().add(panel_1);
		
		lblNewLabel = new JLabel("Subject Line:");
		panel_1.add(lblNewLabel);
		
		txtSubject = new JTextField();
		panel_1.add(txtSubject);
		txtSubject.setColumns(50);
		JPanel panel = new JPanel();
		contentPanel.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane);

		textPane = new JTextArea();
		scrollPane.setViewportView(textPane);
		textPane.setLineWrap(true);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		buttonPane.setLayout(new FlowLayout());
		contentPanel.getContentPane().add(buttonPane);

		btnSave = new JButton("Save");
		btnSave.setActionCommand("OK");
		buttonPane.add(btnSave);

		btnTest = new JButton("Send Test (Preview)");
		buttonPane.add(btnTest);

		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setupCancel();
		
		if (!isEdit) {
			setInstructions();
		} else {
			txtSubject.setText(template.getSubject());	
			textPane.setText(template.getHtmlContent());
		}
		
		setupParseAndPanes();
		setupSave();
		contentPanel.setLocationRelativeTo(null);
		contentPanel.setVisible(true);
	}

	/**
	 * This method sets up the parse button.
	 */
	public void setupParseAndPanes() {
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnTest) {

				}
			}
		});
	}

	/**
	 * This method sets up the text instructions in the text pane.
	 */
	public void setInstructions() {
		textPane.setText(instructions);
		textPane.append("\n\n");
		textPane.append(instrLastName);
		textPane.append("\n");
		textPane.append(instrFirstName);
		textPane.append("\n");
		textPane.append(instrSubject);
	}

	/**
	 * This method sets up the cancel button.
	 */
	public void setupCancel() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == cancelButton) {
					contentPanel.dispose();
				}
			}
		});
	}

	/**
	 * This method builds the save button.
	 */
	public void setupSave() {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSave) {
					save = new SaveDialogGUI(new EmailTemplate(null, textPane.getText()));
					//TODO: Start here to figure it out
				}
			}
		});
	}
	
	/**
	 * This method allows the class to reference
	 * @return
	 */
	public EmailGUI outer() {
		   return this;
		}

	/**
	 * This method returns the html content from the text pane
	 * @return the html content
	 */
	public String getHtmlContent() {
		return textPane.getText();
	}
	
	/**
	 * This method returns the subject content from it's field.
	 * @return the subject line
	 */
	public String getSubject() {
		 return txtSubject.getText();
	}

	/**
	 * This method disposes of this instance of the frame.
	 */
	public void closeAndDisepose() {
		contentPanel.dispose();
	}
}



