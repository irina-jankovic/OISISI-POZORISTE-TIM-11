package pozoriste.show;

import java.util.LinkedList;
import java.util.List;

public class ShowFunctions {
	private static List<Show> shows;

	private static void initShows() {
		if (shows == null) {
			// TODO load from file
			shows = new LinkedList<Show>();
		}
	}

	public static void addShow(Show s) {
		initShows();
		s.setId((long) shows.size());
		shows.add(s);
		// TODO Save to file
	}

	public static List<Show> getShows() {
		initShows();
		return shows;
	}

}
