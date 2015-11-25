package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
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

public class EmailGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnParse;
	private JFXPanel jfxPanel; // Scrollable JCompenent
	private JPanel pnlView;
	private boolean first = true;
	private JScrollPane scrollPane;
	private JTextArea textPane;

	private String instructions = "<!-- Type/paste your html message here. Click parse below to preview."
			+ " Use the var tag to enter user data."
			+ "\nFor example, to insert the patron's first name anywhere in the "
			+ "email, use the following empty tags:-->";

	private String instrLastName = "<!-- \"<var id = \"lastName\"></var> -->";
	private String instrFirstName = "<!-- \"<var id = \"firstName\"></var> -->";
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmailGUI dialog = new EmailGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmailGUI() {
		setTitle("Edit HTML");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 2, 0, 0));
		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane);

		textPane = new JTextArea();
		scrollPane.setViewportView(textPane);
		textPane.setLineWrap(true);

		pnlView = new JPanel();
		panel.add(pnlView);
		pnlView.setLayout(new BorderLayout(0, 0));

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton Save = new JButton("Save");
		Save.setActionCommand("OK");
		buttonPane.add(Save);
		getRootPane().setDefaultButton(Save);

		btnParse = new JButton("Parse HTML (Preview)");
		buttonPane.add(btnParse);

		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setupCancel();
		setInstructions();
		setupParseAndPanes();
		
	}

	/**
	 * This method sets up the parse button.
	 */
	public void setupParseAndPanes() {
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnParse) {
					drawPreview(textPane.getText());
				}
			}
		});
	}

	/**
	 * This method draws the preview. 
	 * @param textOrHtml the text or html to render
	 */
	public void drawPreview(String textOrHtml) {
		JFXPanel jfxPanel = new JFXPanel();
		pnlView.removeAll(); //Clear the remnants of any existing threads
		Platform.runLater( () -> { // FX components need to be managed by JavaFX
			WebView webView = new WebView();
			webView.getEngine().loadContent(textOrHtml);
			Scene scene = new Scene(webView);
			jfxPanel.setScene(scene);
			pnlView.add(jfxPanel, BorderLayout.CENTER);
			contentPanel.validate();
		})
		;}

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
					dispose();
				}
			}
		});
	}

}



