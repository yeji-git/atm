package atm;

import java.util.ArrayList;

public class AccountManager {
	
	private static ArrayList<Account> list = new ArrayList<Account>();
	
	// Account ø° ¥Î«—

	// Create
	public void addAccount(Account account) {
		this.list.add(account);
	}
	
	// Read
	public Account getAccount(int index) {
		Account account = this.list.get(index);
		
		Account reqAccs = new Account();
		return reqAccs;
	}
	
	// Update
	public void setAccount(int index, Account account) {
		this.list.set(index, account);
	}
	
	// Delete
	public void deleteAccount(int index) {
		this.list.remove(index);
	}

}
