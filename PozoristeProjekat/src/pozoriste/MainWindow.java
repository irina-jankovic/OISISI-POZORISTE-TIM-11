package pozoriste;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import pozoriste.show.NewShowPage;
import pozoriste.show.ShowsTable;
import pozoriste.user.LoginPanel;

public class MainWindow extends JFrame {
	public MainWindow() {
		setLayout(new BorderLayout());
		add(new ShowsTable());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(500,500));
		setVisible(true);
	}
}
