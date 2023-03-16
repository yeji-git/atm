package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager {

	private FileWriter filewriter;
	private FileReader filereader;
	private BufferedReader bufferedreader;
	
	private String userFileName;
	private String accountFileName;
	
	private File userFile;
	private File accountFile;
	
	private UserManager um;
	private AccountManager am;
	
	public FileManager() {
		this.um = new UserManager();
		this.am = new AccountManager();
		
		userFileName = "user.txt";
		accountFileName = "account.txt";
		
		userFile = new File(userFileName);
		accountFile = new File(accountFileName);
	}
	
	public void sava(User user, Account account) {
		saveUser(user);
		saveAccount(account);
	}
	
	public void saveUser(User user) {
		String data = "";
		
		if (user != null) {
			for (int i = 0; i < um.getUserSize(); i++) {
				User users = um.getUser(i);
				
				data += users.getId() + "/";
				data += users.getPassword() + "/";
				data += users.getName() + "/";
				
				if (i != um.getUserSize() - 1) {
					data += "\n";
				}
			}
			
			try {
				this.filewriter = new FileWriter(userFile);
				this.filewriter.write(data);
				
				this.filewriter.close();
				System.out.println("유저 정보 저장 완료");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유저 정보 저장 실패");
			}
		}
	}
	
	public void saveAccount(Account account) {
		String data = "";
		
		if (account != null) {
			for (int i = 0; i < um.getUserSize(); i++) {
				for (int j = 0; j < um.getUser(i).getAccountSize(); j++) {
					Account accounts = am.getAccount(j);
					
					data += accounts.getAccNum() + "/";
				}
				
				if (i != um.getUserSize() - 1) {
					data += "\n";
				}
			}
			
			try {
				this.filewriter = new FileWriter(accountFile);
				this.filewriter.write(data);
				
				this.filewriter.close();
				System.out.println("계좌 정보 저장 완료");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유저 정보 저장 실패");
			}
		}
	}
	
	public User loadUser() {
		User user = null;
		
		if (this.userFile.exists()) {
			try {
				this.filereader = new FileReader(userFile);
				this.bufferedreader = new BufferedReader(this.filereader);
				
				while (this.bufferedreader.ready()) {
					String[] data = this.bufferedreader.readLine().split("/");
					
					String id = data[0];
					String password = data[1];
					String name = data[3];
					
					User users = new User(id, password, name);
				}
				
				this.filereader.close();
				this.bufferedreader.close();
				
				System.out.println("유저 정보 파일로드 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("유저 정보 파일로드 실패");
			}
		}
	}
	
	public Account loadAccount() {
		Account account = null;
		int index = 0;
		int[] accounts = new int[3];
		
		if (accountFile.exists()) {
			try {
				this.filereader = new FileReader(accountFile);
				this.bufferedreader = new BufferedReader(this.filereader);
				
				while (this.bufferedreader.ready()) {
					String[] data = this.bufferedreader.readLine().split("/");
					
					for (int i = 0; i < um.getUserSize(); i++) {
						for (int j = 0; j < um.getUser(i).getAccountSize(); j++) {
							accounts[i] = Integer.parseInt(data[j]);
						}
					}
					
					Account accounts = new Account(accounts[0], accounts[1], accounts[2]);
				}
				
				this.filereader.close();
				this.bufferedreader.close();
				System.out.println("계좌 정보 파일로드 성공");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("계좌 정보 파일로드 실패");
			}
		}
		
	}
	
}
