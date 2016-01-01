package marcus.email.GUI;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Dismiss {

	private SystemTray tray;
	private PopupMenu trayMenu;
	private MenuItem open;
	private MenuItem close;
	private Image image;
	private TrayIcon icon;


	public Dismiss() {
		if (SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();
			buildPopup();
			buildImage();
			addTrayIcon();
			registerListeners();
		} else {
			throw new IllegalAccessError("System tray not acccessible.");
		}

	}
	
	//Builds an illustration to be used as an icon
	public void buildImage() {
		image = Toolkit.getDefaultToolkit().getImage("C:/Users/Marcus/git/MFHEmailer/EmailProgram/resources/Icon.png");
		icon = new TrayIcon(image, "MFH Emailer", trayMenu);
		icon.setImageAutoSize(true);
	}

	//Builds tray pop up menu
	public void buildPopup() {
		trayMenu = new PopupMenu();
		open = new MenuItem("Open");
		close = new MenuItem("Close");
		trayMenu.add(open);
		trayMenu.add(close);
	}

	public void registerListeners() {
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == open) {
					EmailerClientGUI.setVisible();
				}
			}
		});
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == close) {
					EmailerClientGUI.exitAll();
				}
			}});
		icon.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				if (EmailerClientGUI.frmMfhEmailer.isVisible()) {
					EmailerClientGUI.setInvisible();
				} else {
					EmailerClientGUI.setVisible();
				}
			}
		});
	}
	
	//Adds the tray icon upon starting the program
	public void addTrayIcon() {
		try {
			tray.add(icon);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	//Sends the icon to the system tray
	public void sendToTray() {
			EmailerClientGUI.setInvisible();
	}
	
	
}
