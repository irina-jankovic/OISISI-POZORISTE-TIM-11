package pozoriste.show;

import pozoriste.Main;

import java.util.LinkedList;
import java.util.List;

public class ShowFunctions {
	private static List<Show> shows;

	private static void initShows() {
		if (shows == null) {
			shows = (List<Show>) Main.openFromFile("shows.data");
			if (shows == null)
				shows = new LinkedList<Show>();
		}
	}

	public static void addShow(Show s) {
		initShows();
		s.setId((long) shows.size());
		shows.add(s);
		Main.saveToFile(shows, "shows.data");
	}

	public static List<Show> getShows() {
		initShows();
		return shows;
	}

	public static void update(Show s) {
		Main.saveToFile(shows, "shows.data");
	}

}
