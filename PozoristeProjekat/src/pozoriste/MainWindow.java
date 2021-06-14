package pozoriste;

import java.awt.*;

import javax.swing.*;

import pozoriste.show.ShowsTable;
import pozoriste.user.LoginPanel;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -1907333788484152047L;
	private static MainWindow instance;
	private static Component old;

	public MainWindow() {
		instance = this;
		setLayout(new BorderLayout());
		add(new LoginPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(500, 500));
		setVisible(true);
	}

	public static void setView(ShowsTable p) {
		old = instance.getContentPane().getComponent(0);
		instance.getContentPane().removeAll();
		instance.getContentPane().add(p);
		instance.getContentPane().revalidate();
		instance.getContentPane().repaint();
	}

	public static void back() {
		setView((JPanel) old);
	}

}