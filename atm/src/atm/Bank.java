package atm;

import java.util.Random;
import java.util.Scanner;

public class Bank {
	
	private UserManager um;
	private AccountManager am;
	
	private Scanner scan;
	private Random ran;
	
	// Banking 관련 메소드
	
	public Bank() {
		this.scan = new Scanner(System.in);
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
		int select = scan.nextInt();
	}
	
	private void join() {
		System.out.print("회원가입 ID : ");
		String joinId = scan.next();
		System.out.print("회원가입 PW : ");
		String joinPw = scan.next();
	}
	
	public void run() {}
	
}
