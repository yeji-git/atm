package atm;

import java.util.ArrayList;

public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();
	
	// User �� ����
	
	// Create
	public void addUser(User user) {
		this.list.add(user);
	}
	
	// Read
	public User getUser(int index) {
		User user = this.list.get(index);
		
		// �纻 ����
		User reqObj = new User();
		return reqObj;
	}
	public User getUserById(String id) {
		int index = -1; // ?
		return getUser(index);
	}
	
	// Update
	public void setUser(int index, User user) {
		this.list.set(index, user);
	}
	
	// Delete
	public void deleteUser(int index) {
		this.list.remove(index);
	}
	public void deleteUserById(String id) {
		// 
	}
	
}
