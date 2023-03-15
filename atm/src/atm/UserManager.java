package atm;

import java.util.ArrayList;

public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();

	// Create
	public User addUser(User user) {
		// ���� �� add
		User check = getUserById(user.getId());
		if (check == null) {
			list.add(user);
			return user;
		}
		return null;
	}

	// Read
	public User getUser(int index) {
		User user = list.get(index);

		User reqObj = new User(user.getId(), user.getPassword(), user.getName(), user.getAccountList());
		return reqObj;
	}

	public User getUserById(String id) {
		User user = null;

		int index = indexOfById(id);
		if (index != -1)
			user = getUser(index);

		return user;
	}

	private int indexOfById(String id) {
		int index = -1;
		for (User user : list) {
			if (user.getId().equals(id))
				index = list.indexOf(user);
		}
		return index;
	}

	// Update
	public void setUser(int index, User user) {
		list.set(index, user);
	}

	public void setUser(User user, Account account) {
		int index = indexOfById(user.getId());

		list.get(index).addAccount(account);
	}

	// Delete
	public void deleteUser(int index) {
		list.remove(index);
	}

	public void deleteUserById(String id) {
		//
	}

	public int getUserSize() {
		return list.size();
	}

}