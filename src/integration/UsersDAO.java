package integration;

import integration.entities.UserClientResource;
import integration.entities.UserListClientResource;
import business.representations.UserListTO;
import business.representations.UserTO;

public class UsersDAO {
	public UserTO find(String id) throws DAOException {
		UserClientResource users = new UserClientResource(id);
		UserTO user = null;

		try {
			user = users.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public UserListTO findAll() throws DAOException {
		UserListClientResource users = new UserListClientResource();
		UserListTO userList = null;

		try {
			userList = users.represent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}
	
	public String create(UserTO user) throws DAOException {
		UserListClientResource users = new UserListClientResource();
		UserTO userTO = null;

		try {
			userTO = users.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userTO.getId();
	}

	public void update(UserTO user) throws DAOException {
		UserClientResource users = new UserClientResource(user.getId());
		@SuppressWarnings("unused")
		UserTO userTO = null;

		try {
			userTO = users.store(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(String id) throws DAOException {
		UserClientResource users = new UserClientResource(id);

		try {
			users.remove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
