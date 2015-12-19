package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EtchedBorder;

import org.w3c.dom.Element;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.BoxLayout;

public class EmailGUI extends JDialog {

	public static JDialog contentPanel;
	public static SaveDialogGUI save;
	private JButton btnTest;
	private JScrollPane scrollPane;
	private static JTextArea textPane;
	private String htmlToLoad;

	//The data member is used to determine if this window edits an existing template or 
	//if it's a new template. If it's the latter, it will open with a set of instructions as seen below.
	private boolean isEdit;

	private String instructions = "<!-- Type/paste your html message here. Click parse below to preview."
			+ " Use the var tag to enter user data."
			+ "\nFor example, to insert the patron's first name anywhere in the "
			+ "email, use the following empty tags:-->";

	private String instrLastName = "<!-- \"<var id = \"lastName\"></var> -->";
	private String instrFirstName = "<!-- \"<var id = \"firstName\"></var> -->";
	private JButton cancelButton;
	private JButton btnSave;

	/**
	 * Create the dialog.
	 * @param htmlToLoad the html (if any) to load -- leave null if opening an add new template window
	 */
	public EmailGUI(String htmlToLoad, boolean isEdit) {
		//Set this to the argument, if any
		this.htmlToLoad = htmlToLoad;

		contentPanel = new JDialog(EmailerClientGUI.frmMfhEmailer, "Edit Email", true);
		contentPanel.setTitle("Edit HTML");
		contentPanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setBounds(100, 100, 1200, 800);
		contentPanel.getContentPane().setLayout(new BoxLayout(contentPanel.getContentPane(), BoxLayout.Y_AXIS));
		JPanel panel = new JPanel();
		contentPanel.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane);

		textPane = new JTextArea();
		scrollPane.setViewportView(textPane);
		textPane.setLineWrap(true);
		this.isEdit = isEdit;
		
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
		}
		if (this.htmlToLoad != null) {
			textPane.setText(this.htmlToLoad);
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
					save = new SaveDialogGUI();
				}
			}
		});
	}

	/**
	 * This method returns the html content from the text pane
	 * @return the html content
	 */
	public static String getHtmlContent() {
		return textPane.getText();
	}

	/**
	 * This method kills the current frame--to be used following a successful save.
	 */
	public static void closeAndDispose() {
		contentPanel.dispose();
	}

	/**
	 * The window needs to know if it's an edit or if the user is creating new text.
	 * For new text, it will need to show instructions. If not, it will simply load the
	 * existing html.
	 */
	public void isEdit() {

	}




}



