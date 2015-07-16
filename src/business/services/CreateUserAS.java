package business.services;

import integration.DAOException;
import integration.UsersDAO;
import business.User;
import business.representations.UserTO;

public class CreateUserAS {

	public static boolean createUser(UserTO user) {

		try {
			UsersDAO usersDAO = new UsersDAO();
			user.setPassword(Encryption.encrypt(user.getPassword()));
			String userId;
			userId = usersDAO.create(user);
			user.setId(userId);
			user.setQuestions(null);
			User.addUser(user);
			return true;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}
