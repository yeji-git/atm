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
		this.ran = new Random();
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
			if (user.get(i).getId().equals(joinId)) {
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
			ArrayList<User> user = um.getList();
			if (loginId.equals(user.get(i).getId()) && loginPw.equals(user.get(i).getPassword())) {
				log = i;
			}
		}
		
		if (log != -1) {
			System.out.println("�α��� ����!");
		}
		else {
			System.out.println("�α��� ����");
		}
	}
	
	private void accJoin() {
		while (true) {
			int rNum = ran.nextInt(8999) +1000;
			
			for (int i = 0; i < am.getList().size(); i++) {
				if (rNum == Integer.parseInt(am.getList().get(i).getAccNum())) {
					i--;
				}
				else {
					String account = am.getAccount(log).getAccNum();
					account = Integer.toString(rNum);
					break;
				}
			}
		}
		System.out.println("���� ���� ����!");
		System.out.println("���¹�ȣ�� " + am.getAccount(log).getAccNum() + " �Դϴ�.");
	}
	
	private void accReave() {
		for (int i = 0; i < 3; i++) {
			Account aacount = 
			System.out.println(i + );
		}
	}
	
	private void logout() {
		log = -1;
		System.out.println("�α׾ƿ��Ǿ����ϴ�.");
	}
	
	private void reave() {}
	
	public void run() {
		while (true) {
			printMenu();
			selectMenu();
			if (select == 1) {
				join();
			}
			else if (select == 2) {
				login();
			}
			else if (select == 3) {
				accJoin();
			}
			else if (select == 4) {
				accReave();
			}
			else if (select == 5) {
				logout();
			}
			else if (select == 6) {
				leave();
			}
		}
	}
	
}
