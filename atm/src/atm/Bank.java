package atm;

import java.util.Random;
import java.util.Scanner;

public class Bank {
	
	private UserManager um;
	private AccountManager am;
	
	private Scanner scan;
	private Random ran;
	
	private int count;
	private int log;
	private int select;
	
	// Banking 관련 메소드
	
	public Bank() {
		this.scan = new Scanner(System.in);
		this.log = -1;
	}
	
	private void printMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 계좌신청");
		System.out.println("4. 계좌철회");
		System.out.println("5. 로그아웃");
		System.out.println("6. 회원탈퇴");
	}
	
	private void selectMenu() {
		System.out.print("메뉴 : ");
		select = scan.nextInt();
	}
	
	private void join() {
		System.out.print("회원가입 ID : ");
		String joinId = scan.next();
		System.out.print("회원가입 PW : ");
		String joinPw = scan.next();
		
		if(!isDuplId(joinId)) {
			System.out.print("이름 : ");
			String joinName = scan.next();
			
			User user = new User(joinId, joinPw, joinName, null);
			this.um.addUser(user);
			
			this.count++;
		}
		else {
			System.out.println("쭝복된 아이디입니다.");
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
	
	private void login() {
		System.out.print("로그인 ID : ");
		String loginId = scan.next();
		System.out.print("로그인 PW : ");
		String loginPw = scan.next();
		
		for (int i = 0; i < this.count; i++) {
//			User user = user.getId();
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
