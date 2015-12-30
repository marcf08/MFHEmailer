package marcus.email.GUI;

import java.awt.EventQueue;


import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.EtchedBorder;

import marcus.email.util.EmailTemplate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDialogGUI {
	//Swing Components
	private JDialog frmSaveTitle;
	private JButton btnCancel;
	private JButton btnSave;
	private JTextField txtName;
	private EmailGUI parent;

	//Email Template data member
	EmailTemplate templateToSave;

	/**
	 * Create the application.
	 */
	public SaveDialogGUI(EmailTemplate templateToSave) {
		//this.parent = parent;
		this.templateToSave = templateToSave;
		initialize();
		setupCancelAndSave();
		frmSaveTitle.setLocationRelativeTo(null);
		frmSaveTitle.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSaveTitle = new JDialog(frmSaveTitle, Dialog.DEFAULT_MODALITY_TYPE);
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
					closeAndDispose();
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSave) {
					//If it's null, then we know it does not exist and can use other methods
					if (exists() != null) {
						JFrame frmOverwrite = new JFrame("Overwrite");
						int response = JOptionPane.showConfirmDialog(frmOverwrite, "Template already exists. Overwrite?");
						if (response == JOptionPane.CANCEL_OPTION || response == JOptionPane.NO_OPTION) {
							frmOverwrite.dispose();
							frmSaveTitle.dispose();
						}
						if (response == JOptionPane.OK_OPTION) {
							System.out.println("LINE 102" + templateToSave.getSubject());
							EmailerClientGUI.emailStorage.modifyTemplateByName(exists().getName(), templateToSave.getHtmlContent(), templateToSave.getSubject());
							frmOverwrite.dispose();
							frmSaveTitle.dispose();
						}
					} else {
						saveNewTemplate();
					}
				}}});
	}

	/**
	 * This method sees if a template with this name already exists. If this method returns null,
	 * then the template with the given name does not exist. If it exists, this method returns 
	 * the name of the template. This effectively combines two methods into one.
	 */
	private EmailTemplate exists() {
		EmailTemplate [] templates = EmailerClientGUI.emailStorage.getAll();
		for (int i = 0; i < templates.length; i++) {
			if (txtName.getText().equals(templates[i].getName())) {
				return templates[i];
			}
		}
		return null;
	}

	/**
	 * This method saves a new template if a previously existing template was not
	 * edited.
	 */
	public void saveNewTemplate() {
		EmailerClientGUI.emailStorage.addTemplate(txtName.getText(), templateToSave.getHtmlContent(), templateToSave.getSubject());
		EmailerClientGUI.updateCombo();
		EmailerClientGUI.validate();
		closeAndDispose();
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
