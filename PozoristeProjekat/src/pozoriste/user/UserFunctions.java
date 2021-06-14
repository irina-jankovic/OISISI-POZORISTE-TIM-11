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

	public static void logout(String username, String password) {
		currentUser = null;
	}

	//da ne mogu biti dva ista useraname-a
	public static boolean register(User u) {
		initUsers();
		for (User user : users)
			if (user.getUsername().equals(u)) {
				return false;
			}
		users.add(u);
		// TODO napisati kod za cuvanj
		return true;
	}

	private static void initUsers() {
		if (users == null) {
			// TODO load from file
			users = new LinkedList<User>();
			User u = new User();
			u.setPassword("admin");
			u.setUsername("admin");
			users.add(u);
		}
	}

	public static User getCurrentUser() {
		return currentUser;
	}

}
