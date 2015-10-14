package marcus.email.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddPatronDialog extends JDialog {

	private static final JPanel contentPanel = new JPanel();
	private final JOptionPane optionPane = new JOptionPane(
            JOptionPane.NO_OPTION,
            JOptionPane.YES_NO_OPTION);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddPatronDialog dialog = new AddPatronDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddPatronDialog() {
		optionPane.addPropertyChangeListener(
			    new PropertyChangeListener() {
			        public void propertyChange(PropertyChangeEvent e) {
			            String prop = e.getPropertyName();

			            if (getContentPane().isVisible() 
			             && (e.getSource() == optionPane)
			             && (prop.equals(JOptionPane.YES_OPTION))) {
			                //If you were going to check something
			                //before closing the window, you'd do
			                //it here.
			                getContentPane().setVisible(false);
			            }
			        }
	    });
		
		contentPanel.add(optionPane);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EmailerClientGUI.enableMainGUI();
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
