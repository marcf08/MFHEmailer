package marcus.email.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGrid.Response;
import com.sendgrid.SendGridException;
/**
 * This method sends an email. 
 * @author Marcus
 *
 */
public class TestPreviewGUI {

	private JFrame frmMain;
	private JButton btnCancel;
	private JButton btnOk;
	private String htmlContent;
	private JTextField txtEmail;

	/**
	 * Create the application.
	 */
	public TestPreviewGUI(String htmlContent) {
		this.htmlContent = htmlContent;
		initialize();
		setupCancel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setTitle("Send Preview");
		frmMain.setBounds(100, 100, 450, 150);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmMain.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblEmail = new JLabel("Email Address:");
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		panel.add(txtEmail);
		txtEmail.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmMain.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		
		btnOk = new JButton("OK");
		panel_1.add(btnOk);
	}
	
	/**
	 * This method sets up the send button.
	 */
	public void setupSend() {
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnOk) {
					send();
				}
			}
		});
	}

	
	/**
	 * This method receives the html from the data member and
	 * sends it off. 
	 */
	public void send() {
		FileInputStream fos;
		try {
			fos = new FileInputStream(FileConstants.CONFIG_LOC);
			Properties prop = new Properties();
			prop.load(fos);
			SendGrid s = new SendGrid(prop.getProperty(FileConstants.CONFIG_API));
			Email e = new Email();
			e.addTo(txtEmail.getText());
			e.setHtml(htmlContent);
			Response r = s.send(e); 
			JOptionPane.showMessageDialog(new JFrame(), "Response: " + r.getMessage());
		} catch (IOException | SendGridException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method sets up the cancel button.
	 */
	public void setupCancel() {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnCancel) {
					frmMain.dispose();
				}
			}
		});
	}
	
		

}
