package pozoriste.user;

import java.util.LinkedList;
import java.util.List;

public class UserFunctions {

	private static List<User> users; // all users
	private static User currentUser; // logged in

	public static boolean login(String username, String password) {
		initUsers();
		for (User user : users)
			if (user.getUsername().equals(username) && password.equals(user.getPassword())) {
				currentUser = user;
				return true;
			}
		return false;
	}

	public static void logout() {
		currentUser = null;
		MainWindow.setView(new LoginPanel());
	}

	//da ne mogu biti dva ista useraname-a
	public static boolean register(User u) {
		initUsers();
		for (User user : users)
			if (user.getUsername().equals(u.getUsername())) {
				return false;
			}
		users.add(u);
		Main.saveToFile(users, "users.data");
		return true;
	}

	private static void initUsers() {
		if (users == null) {
			users = (List<User>) Main.openFromFile("users.data");
			if (users == null) {
				users = new LinkedList<User>();
				User u = new User();
				u.setType("admin");
				u.setPassword("admin");
				u.setUsername("admin");
				users.add(u);
			}
		}
	}

	public static User getCurrentUser() {
		return currentUser;
	}

}
