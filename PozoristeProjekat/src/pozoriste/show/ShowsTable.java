package pozoriste.show;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import pozoriste.MainWindow;
import pozoriste.user.UserFunctions;

public class ShowsTable extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 4455631963210013570L;

	public ShowsTable() {
		setLayout(new BorderLayout());
		Color backgroundColor = new Color(240, 240, 240);

		JButton logout = new JButton("Odjava");
		logout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		logout.setBackground(backgroundColor);

		JPanel lp = new JPanel();
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserFunctions.logout();
			}
		});
		lp.add(logout);

		if (UserFunctions.getCurrentUser().getType().equals("admin")) {
			JButton addnew = new JButton("Dodaj");
			addnew.setFont(new Font("Tahoma", Font.PLAIN, 16));
			addnew.setBackground(backgroundColor);
			addnew.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					MainWindow.setView(new NewShowPage(null));

				}
			});
			lp.add(addnew);
		}

		add(lp, BorderLayout.NORTH);
		JTable table = new JTable();
		table.setRowHeight(22);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new TableModelForShows());
		table.getTableHeader().setFont(new Font("arial", Font.BOLD, 14));
		add(new JScrollPane(table));
		table.setAutoCreateRowSorter(true);

		if (UserFunctions.getCurrentUser().getType().equals("admin")) {

			Action edit = new AbstractAction() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1585299714856884984L;

				public void actionPerformed(ActionEvent e) {
					int modelRow = Integer.valueOf(e.getActionCommand());
					MainWindow.setView(new NewShowPage(ShowFunctions.getShows().get(modelRow)));

				}
			};

			new ButtonColumn(table, edit, 5);
		}

		// more details
		Action details = new AbstractAction() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				int modelRow = Integer.valueOf(e.getActionCommand());
				MainWindow.setView(new ShowDetails(ShowFunctions.getShows().get(modelRow)));
			}
		};
		new ButtonColumn(table, details, 4);

	}

}