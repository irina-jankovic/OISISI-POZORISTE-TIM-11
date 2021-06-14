package pozoriste.show;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import pozoriste.MainWindow;
import pozoriste.report.Report;
import pozoriste.report.ReportTable;
import pozoriste.ticket.Seat;
import pozoriste.ticket.Ticket;
import pozoriste.ticket.TicketFunctions;
import pozoriste.user.UserFunctions;

public class ShowDetails extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2379583815311076207L;
	private List<Seat> seatsList = new LinkedList<Seat>();

	public ShowDetails(Show s) {

		Color backgroundColor = new Color(240, 240, 240);// for inputs
		setLayout(new GridBagLayout());
		JPanel fields = new JPanel(new GridLayout(5, 2));
		fields.setPreferredSize(new Dimension(400, 150));
		fields.setBackground(Color.lightGray);// for small panel

		setBackground(new Color(200, 200, 200));
		String userType = UserFunctions.getCurrentUser().getType();
		JButton addBtn = new JButton(userType.equals("admin") ? "Izvestaj" : "Rezervisi");
		addBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		addBtn.setBackground(backgroundColor);

		JLabel nameLabel = new JLabel("Naziv:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(nameLabel);

		JLabel nameField = new JLabel(s.getName());
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameField.setBackground(backgroundColor);
		fields.add(nameField);

		JLabel descriptionLable = new JLabel("Opis:");
		descriptionLable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(descriptionLable);

		JTextArea descriptionField = new JTextArea();
		descriptionField.setText(s.getDescription());
		descriptionField.setEditable(false);
		descriptionField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descriptionField.setBackground(backgroundColor);
		fields.add(new JScrollPane(descriptionField));

		JLabel priceLabel = new JLabel("Cena:");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(priceLabel);

		JLabel priceField = new JLabel("" + s.getPrice());
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priceField.setBackground(backgroundColor);
		fields.add(priceField);

		JLabel dateLabel = new JLabel("Datum:");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fields.add(dateLabel);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		JLabel dateField = new JLabel(df.format(s.getDate()));
		fields.add(dateField);

		JButton back = new JButton("Nazad");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.back();
			}
		});
		fields.add(back);

		fields.add(addBtn);
		add(fields);

		JPanel seats = new JPanel(new GridLayout(6, 5, 10, 10));
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				Seat seat = new Seat(i + 1, j + 1, s.getSeats().get(i * 5 + j));
				seatsList.add(seat);
				seats.add(seat);
			}

		}
		JPanel blank = new JPanel();
		blank.setPreferredSize(new Dimension(10, 10));
		add(blank);
		blank.setBackground(Color.lightGray);
		add(seats);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (userType.equals("admin")) {
					// izvestaji
					Report r = new Report();
					float totalPrice = 0;// ukupna cena karata

					for (Ticket t : TicketFunctions.getTickets()) {
						if (t.getShow().getId() == s.getId()) {
							totalPrice += t.getPrice();
							r.getIds().add(t.getId());
							r.getPrices().add(t.getPrice());
						}
					}
					MainWindow.setView(new ReportTable(s.getName() + " (" + s.getId() + ")", r, totalPrice));

				} else {
					List<Seat> selectedSeats = new LinkedList<Seat>();

					for (Seat s : seatsList) {
						if (s.isSelected())
							selectedSeats.add(s);
					}
					if (selectedSeats.size() == 0) {
						JOptionPane.showMessageDialog(null, "Nije izabrano nijedno sediste");
						return;
					}

					for (Seat se : selectedSeats) {
						s.getSeats().put(se.getRow() * 5 + se.getColumn(), true);
						Ticket t = new Ticket();
						t.setColumn(se.getColumn());
						t.setIssuedTo(UserFunctions.getCurrentUser());
						t.setPrice(s.getPrice());
						t.setRow(se.getRow());
						t.setShow(s);
						TicketFunctions.addTicket(t);
					}
					int no = 0;
					// update if all sold
					for (boolean b : s.getSeats().values()) {
						if (b)
							no++;
					}
					if (no == 30) {
						s.setSold(true);
						ShowFunctions.update(s);
					}
					MainWindow.back();
				}
			}
		});
	}

}
