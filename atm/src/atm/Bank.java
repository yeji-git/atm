package atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {
	
	private UserManager um;
	private AccountManager am;
	
	private Scanner scan;
	private Random ran;
	
	private int log;
	private int select;
	
	// Banking ���� �޼ҵ�
	
	public Bank() {
		um = new UserManager();
		am = new AccountManager();
		this.scan = new Scanner(System.in);
		this.log = -1;
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
		select = scan.nextInt();
	}
	
	private void join() {
		System.out.print("ȸ������ ID : ");
		String joinId = scan.next();
		System.out.print("ȸ������ PW : ");
		String joinPw = scan.next();
		
		if(!isDuplId(joinId)) {
			System.out.print("�̸� : ");
			String joinName = scan.next();
			
			User user = new User(joinId, joinPw, joinName);
			um.addUser(user);
			
			System.out.println("ȸ������ �Ϸ�!");
		}
		else {
			System.out.println("�亹�� ���̵��Դϴ�.");
		}
	}
	
	private boolean isDuplId(String joinId) {
		boolean dupl = false;
		for (int i = 0; i < um.getList().size(); i++) {
			ArrayList<User> user = um.getList();
			if (user.get(i).equals(joinId)) {
				dupl = true;
			}
		}
		return dupl;
	}
	
	private void login() {
		System.out.print("�α��� ID : ");
		String loginId = scan.next();
		System.out.print("�α��� PW : ");
		String loginPw = scan.next();
		
		for (int i = 0; i < um.getList().size(); i++) {
			User user = user.getList;
			if (user.getId().equals(loginPw)) {
				
			}
		}
	}
	
	public void run() {
		while (true) {
			printMenu();
			selectMenu();
			if (select == 1) {
				join();
			}
		}
	}
	
}
