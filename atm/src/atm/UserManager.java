package atm;

import java.util.ArrayList;

public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();
	
	// Create
	public void addUser(User user) {
		list.add(user);
	}
	
	// Read
	public User getUser(int index) {
		User user = list.get(index);
		
		User reqObj = new User(user.getId(), user.getPassword(), user.getName(), user.getAccs());
		return reqObj;
	}
	public User getUserById(String id) {
		int index = -1; // ?
		return getUser(index);
	}
	
	// Update
	public void setUser(int index, User user) {
		list.set(index, user);
	}
	
	// Delete
	public void deleteUser(int index) {
		list.remove(index);
	}
	
}
