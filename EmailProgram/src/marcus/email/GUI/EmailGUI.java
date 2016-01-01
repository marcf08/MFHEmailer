package marcus.email.GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EtchedBorder;

import marcus.email.util.EmailTemplate;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

public class EmailGUI extends JDialog {
	//Swing data members
	public JDialog contentPanel;
	private JButton btnTest;
	private JScrollPane scrollPane;
	private JTextArea txtHtmlContent;
	private JButton cancelButton;
	private JButton btnSave;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JTextField txtTemplateSubject;
	
	//Information needed to load a template.
	private EmailTemplate templateToLoad;
	private String instructions = "<!-- Type/paste your html message here. Click parse below to preview."
			+ " Use the var tag to enter user data."
			+ "\nFor example, to insert the patron's first name anywhere in the "
			+ "email, use the following empty tags:-->";

	private String instrLastName = "<!-- \"<var id = \"lastName\"></var> -->";
	private String instrFirstName = "<!-- \"<var id = \"firstName\"></var> -->";
	private String instrSubject = "<!-- Use #first and/or #last to use a patron's name in the subject line -->";
	private JPanel panel_2;
	private JLabel lblTemplateName;
	private JPanel panel_3;
	private JTextField txtTemplateName;
	
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
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.getContentPane().add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_3);
		
		lblTemplateName = new JLabel("Template Name:");
		panel_3.add(lblTemplateName);
		
		txtTemplateName = new JTextField();
		panel_3.add(txtTemplateName);
		txtTemplateName.setColumns(15);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		lblNewLabel = new JLabel("Subject Line:");
		panel_2.add(lblNewLabel);
		
		txtTemplateSubject = new JTextField();
		panel_2.add(txtTemplateSubject);
		txtTemplateSubject.setColumns(50);
		JPanel panel = new JPanel();
		contentPanel.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane);

		txtHtmlContent = new JTextArea();
		scrollPane.setViewportView(txtHtmlContent);
		txtHtmlContent.setLineWrap(true);
		
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
			txtTemplateName.setText(template.getName());
			txtTemplateSubject.setText(template.getSubject());	
			txtHtmlContent.setText(template.getHtmlContent());
			
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
		txtHtmlContent.setText(instructions);
		txtHtmlContent.append("\n\n");
		txtHtmlContent.append(instrLastName);
		txtHtmlContent.append("\n");
		txtHtmlContent.append(instrFirstName);
		txtHtmlContent.append("\n");
		txtHtmlContent.append(instrSubject);
	}

	//Cancel Button
	public void setupCancel() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == cancelButton) {
					contentPanel.dispose();
				}
			}
		});
	}

	//Save button
	public void setupSave() {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSave) {
					try {
						EmailerClientGUI.emailStorage.addTemplate(txtTemplateName.getText(), txtHtmlContent.getText(), txtTemplateSubject.getText());
						updateMainGUI();
						contentPanel.dispose();
					} catch (IllegalArgumentException eae) {
						int response = JOptionPane.showConfirmDialog(new JFrame(), eae.getMessage() + " Overwrite?");
						doOverWriteLogic(response);
					}
				}
			}
		});
	}	
	
	/**
	 * This method overwrites the existing template,  if the user desires to do so.
	 * @param response the user's response to the confirm dialog question
	 */
	public void doOverWriteLogic(int response) {
		if (response == JOptionPane.YES_OPTION) {
			EmailerClientGUI.emailStorage.modifyTemplateByName(txtTemplateName.getText(), txtHtmlContent.getText(), txtTemplateSubject.getText());
			contentPanel.dispose();
		}
	}
	
	//Updates the main gui to reload the combo  box to show a new template
	private void updateMainGUI() {
		EmailerClientGUI.updateCombo();
		EmailerClientGUI.validate();
	}

}
	


