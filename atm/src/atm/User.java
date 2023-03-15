package atm;

import java.util.ArrayList;

public class User {

	private String id, password, name;

	// new ��ü�� �ƴ� -> AccountManager.list �ȿ� �ִ� �ν��Ͻ�
	private ArrayList<Account> accs;

	public User(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.accs = new ArrayList<Account>();
	}

	public User(String id, String password, String name, ArrayList<Account> accs) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.accs = accs;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public int getAccountSize() {
		return this.accs.size();
	}

	public void addAccount(Account account) {
		this.accs.add(account);
	}

	public Account getAccount(int index) {
		return this.accs.get(index);
	}
	
	public void deleteAccount(Account account) {
		this.accs.remove(account);
	}

	public ArrayList<Account> getAccountList() {
		return (ArrayList<Account>) this.accs.clone(); 
	}

}