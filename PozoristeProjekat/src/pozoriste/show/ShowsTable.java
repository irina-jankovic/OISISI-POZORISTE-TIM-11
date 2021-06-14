package pozoriste.show;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import pozoriste.MainWindow;
import pozoriste.report.Report;
import pozoriste.report.ReportTable;
import pozoriste.ticket.Ticket;
import pozoriste.ticket.TicketFunctions;
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

			JButton report = new JButton("Izvestaj za sve predstave");
			report.setFont(new Font("Tahoma", Font.PLAIN, 16));
			report.setBackground(backgroundColor);
			report.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Report r = new Report();
					float totaPrice = 0;
					for (Show s : ShowFunctions.getShows()) {
						float totaPriceForShow = 0;

						r.getIds().add(s.getId());
						for (Ticket t : TicketFunctions.getTickets()) {
							if (t.getShow().getId() == s.getId()) {
								totaPrice += t.getPrice();
								totaPriceForShow += t.getPrice();
							}

						}
						r.getPrices().add(totaPriceForShow);

					}

					MainWindow.setView(new ReportTable("Izvestaj za sve predsave", r, totaPrice));

				}
			});
			lp.add(report);

		}

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

		JPanel searchAndButtons = new JPanel();
		searchAndButtons.setLayout(new BoxLayout(searchAndButtons, BoxLayout.Y_AXIS));

		searchAndButtons.add(lp);
		//searchAndButtons.add(new SearchPanel(table));

		add(searchAndButtons, BorderLayout.NORTH);

	}

}