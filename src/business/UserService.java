package business;

import java.util.Map;

import business.representations.UserTO;
import business.services.Encryption;

public class UserService {

	public static String existUsername (String username) {
		for (Map.Entry<String, UserTO> user : User.getUsers().entrySet()) {
			if (user.getValue().getUsername().equals(username))
				return user.getValue().getId();
		}
		
		return null;
	}
	
	public static boolean validUser (String id, String password) {
		return User.getUser(id).getPassword().equals(Encryption.encrypt(password));
	}
}
