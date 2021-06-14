package pozoriste.ticket;

import java.util.LinkedList;
import java.util.List;

public class TicketFunctions {
	private static List<Ticket> tickets;

	private static void initShows() {
		if (tickets == null) {
			// TODO load from file
			tickets = new LinkedList<Ticket>();
		}
	}

	public static void addTicket(Ticket t) {
		initShows();
		t.setId((long) tickets.size());
		tickets.add(t);
		// TODO Save to file
	}

	public static List<Ticket> getTickets() {
		initShows();
		return tickets;
	}

}
