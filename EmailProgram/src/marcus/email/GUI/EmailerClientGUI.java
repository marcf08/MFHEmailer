package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


/**
 * This package contains the main email client. After logging on, the user
 * will be presented with this page in order to add and remove patrons, monitor
 * the status, and control email campaigns.
 * @author Marcus
 *
 */

public class EmailerClientGUI extends JFrame implements ActionListener {

	
	/**
	 * The constructor builds the main client screen.
	 */
	public EmailerClientGUI() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(EmailerGUIMain.PRG_NAME);
		setLayout(new BorderLayout());
		
		
		
		pack();
		
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Runs the client--for testing only.
	 * @param args
	 */
	public static void main(String [] args) {
		new EmailerClientGUI();
	}
	
	
}
