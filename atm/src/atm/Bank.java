package atm;

import java.util.Random;
import java.util.Scanner;

public class Bank {
	
	private UserManager um;
	private AccountManager am;
	
	private Scanner scan;
	private Random ran;
	
	private int count;
	
	// Banking ���� �޼ҵ�
	
	public Bank() {
		this.scan = new Scanner(System.in);
	}
	
	private void printMenu() {
		System.out.println("1. ȸ������");
		System.out.println("2. �α���");
		System.out.println("3. ���½�û");
		System.out.println("4. ����öȸ");
		System.out.println("5. �α׾ƿ�");
		System.out.println("6. ȸ��Ż��");
	}
	
	private void selectMenu() {
		System.out.print("�޴� : ");
		int select = scan.nextInt();
	}
	
	private void join() {
		System.out.print("ȸ������ ID : ");
		String joinId = scan.next();
		System.out.print("ȸ������ PW : ");
		String joinPw = scan.next();
		
		if(!isDuplId(joinId)) {
			User user = new User(joinId);
			UserManager.addUser(user);
		}
	}
	
	private boolean isDuplId(String joinId) {
		boolean dupl = false;
		for (int i = 0; i < this.count; i++) {
			User user = this.um.getUserById(joinId);
			if (user.getId().equals(joinId)) {
				dupl = true;
			}
		}
		return dupl;
	}
	
	public void run() {}
	
}
