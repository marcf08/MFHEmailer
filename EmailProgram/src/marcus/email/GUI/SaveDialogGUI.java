package marcus.email.GUI;

import java.awt.EventQueue;


import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDialogGUI {

	private JDialog frmSaveTitle;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtName;


	/**
	 * Create the application.
	 */
	public SaveDialogGUI() {
		initialize();
		setupCancelAndSave();
		frmSaveTitle.setLocationRelativeTo(null);
		frmSaveTitle.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSaveTitle = new JDialog(EmailGUI.contentPanel, "Save Template", true);
		frmSaveTitle.setTitle("Save Template");
		frmSaveTitle.setBounds(100, 100, 500, 125);
		frmSaveTitle.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmSaveTitle.getContentPane().add(panel, BorderLayout.SOUTH);

		btnCancel = new JButton("Cancel");
		panel.add(btnCancel);

		btnSave = new JButton("OK");
		panel.add(btnSave);

		JPanel panel_1 = new JPanel();
		frmSaveTitle.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblName = new JLabel("Template Name:");
		panel_1.add(lblName);

		txtName = new JTextField();
		panel_1.add(txtName);
		txtName.setColumns(20);
	}

	/**
	 * This method sets up the cancel and save buttons.
	 */
	public void setupCancelAndSave() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCancel) {
					frmSaveTitle.dispose();
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSave) {
					if (exists()) {
						JFrame frmOverwrite = new JFrame("Overwrite");
						int response = JOptionPane.showConfirmDialog(frmOverwrite, "Template already exists. Overwrite?");
		 				if (response == JOptionPane.CANCEL_OPTION) {
							frmOverwrite.dispose();
							frmSaveTitle.dispose();
						}
						if (response == JOptionPane.OK_OPTION) {
							EmailerClientGUI.emailStorage.modifyTemplateByName(txtName.getText(), EmailGUI.getHtmlContent());
							frmOverwrite.dispose();
							EmailGUI.contentPanel.dispose();
						}
						if (response == JOptionPane.NO_OPTION) {
							frmOverwrite.dispose();
							
						}
				} else {
					saveNewTemplate();
				}
				}}});
	}

	/**
	 * This method sees if a template with this name already exists
	 */
	public boolean exists() {
		String [] templateNames = EmailerClientGUI.emailStorage.getTemplateNames(); 
		for (int i = 0; i < templateNames.length; i++) {
			if (txtName.getText().equals(templateNames[i])) {
				return true;
			};
		}
		return false;
	}

	/**
	 * This method saves a new template if a previously existing template was not
	 * edited.
	 */
	public void saveNewTemplate() {
		EmailerClientGUI.emailStorage.addTemplate(txtName.getText(), EmailGUI.getHtmlContent(), EmailGUI.getSubject());
		EmailerClientGUI.updateCombo();
		EmailerClientGUI.validate();
		EmailGUI.contentPanel.dispose();
	}

	/**
	 * This method disposes of the window--to be used by the client.
	 */
	public void closeAndDispose() {
		frmSaveTitle.dispose();
	}

	/**
	 * This method retrieves the content of the text from the save dialog.
	 * @return the string text to be used as the name of the template
	 */
	public String getSaveText() {
		return txtName.getText();
	}
}
