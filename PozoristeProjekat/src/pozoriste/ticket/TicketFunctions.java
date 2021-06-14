package pozoriste.ticket;

import pozoriste.Main;
import pozoriste.show.ShowFunctions;

import java.util.LinkedList;
import java.util.List;

public class TicketFunctions {
	private static List<Ticket> tickets;

	private static void initShows() {
		if (tickets == null) {
			tickets = (List<Ticket>) Main.openFromFile("tickets.data");
			if (tickets == null)
			tickets = new LinkedList<Ticket>();
		}
	}

	public static void addTicket(Ticket t) {
		initShows();
		t.setId((long) tickets.size());
		tickets.add(t);
		ShowFunctions.update(null);
		Main.saveToFile(tickets, "tickets.data");
	}

	public static List<Ticket> getTickets() {
		initShows();
		return tickets;
	}

}
