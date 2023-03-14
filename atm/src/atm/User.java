package atm;

import java.util.ArrayList;

public class User {

	private String id, password, name;
	private ArrayList<Account> accs;
	
	public User(String id, String password, String name, ArrayList<Account> accs) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.accs = accs;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Account> getAccs() {
		return this.accs;
	}
	
}
