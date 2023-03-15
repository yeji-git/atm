package atm;

import java.util.Scanner;

public class Bank {

	private String brandName;

	private UserManager um;
	private AccountManager am;

	private Scanner scan;
	private int log;

	public Bank(String brandName) {
		this.brandName = brandName;
		this.scan = new Scanner(System.in);
		this.um = new UserManager();
		this.am = new AccountManager();
		this.log = -1;
	}

	private void printMainMenu() {
		System.out.println("--- " + this.brandName + " ---");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 계좌신청");
		System.out.println("4. 계좌철회");
		System.out.println("5. 로그인");
		System.out.println("6. 로그아웃");
		System.out.println("7. 뱅킹");
		System.out.println("0. 종료");
		System.out.println("-----------------");
	}
	
	private void printSubMenu() {
		System.out.println("1. 입금하기");
		System.out.println("2. 출금하기");
		System.out.println("3. 조회하기");
		System.out.println("4. 이체하기");
		System.out.println("5. 뒤로가기");
	}
	
	

	private boolean isLoggedIn() {
		if (log != -1) {
			return true;
		}
		return false;
	}

	private int inputNumber() {
		int number = -1;

		System.out.print("메뉴 : ");
		String input = this.scan.next();

		try {
			number = Integer.parseInt(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return number;
	}

	private void joinUser() {
		if (!isLoggedIn()) {
			System.out.print("회원가입 ID : ");
			String id = scan.next();
			System.out.print("회원가입 PW : ");
			String password = scan.next();
			System.out.print("이름 : ");
			String name = scan.next();

			User user = new User(id, password, name);
			if (this.um.addUser(user) != null) {
				System.out.println("[회원가입 성공]");
			} else {
				System.out.println("[중복된 아이디가 존재합니다.]");
			}
		} else {
			System.out.println("[로그인이 되어 있습니다.]");
		}
	}

	private void leaveUser() {
		if (isLoggedIn()) {
			System.out.println("[탈퇴하시겠습니까? 1.y 2.n]");
			int leaveNumber = scan.nextInt();
			if (leaveNumber == 1) {
				um.deleteUser(log);
				log = -1;
				System.out.println("[탈퇴가 완료되었습니다.]");
			}
		} else {
			System.out.println("[로그인이 되어 있지 않습니다.]");
		}
	}

	private void createAccount() {
		System.out.print("id : ");
		String id = this.scan.next();
		System.out.print("pw : ");
		String password = this.scan.next();

		User user = this.um.getUserById(id);

		if (user != null) {
			if (user.getPassword().equals(password)) {
				if (user.getAccountSize() < Account.LIMIT) {
					Account account = this.am.createAccount(new Account(id));
					this.um.setUser(user, account);
				} else {
					System.out.println("[계좌가 초과되었습니다.]");
				}
			} else {
				System.out.println("[비밀번호가 일치하지 않습니다.]");
			}
		} else {
			System.out.println("[회원 정보를 확인하세요.]");
		}
	}

	private void deleteAccount() {
		System.out.print("id : ");
		String id = this.scan.next();
		System.out.print("pw : ");
		String password = this.scan.next();

		User user = this.um.getUserById(id);

		if (user != null) {
			if (user.getPassword().equals(password)) {
				for (int i = 0; i < user.getAccountSize(); i++) {
					System.out.printf("%d. %s\n", i, am.getAccount(i).getAccNum());
				}
				System.out.println("[철회할 계좌를 선택해 주세요.]");
				int deleteNumber = scan.nextInt();
				am.deleteAccount(deleteNumber);
				System.out.println("[철회가 완료되었습니다.]");
			} else {
				System.out.println("[비밀번호가 일치하지 않습니다.]");
			}
		} else {
			System.out.println("[회원 정보를 확인하세요.]");
		}
	}

	private void login() {
		if (!isLoggedIn()) {
			if (um.getUserSize() != 0) {
				System.out.print("id : ");
				String id = scan.next();
				System.out.print("pw : ");
				String password = scan.next();

				for (int i = 0; i < this.um.getUserSize(); i++) {
					if (this.um.getUser(i).getId().equals(id) && this.um.getUser(i).getPassword().equals(password)) {
						log = i;
					}
				}
				if (this.log != -1) {
					System.out.println("[ " + this.um.getUser(this.log).getName() + " 님 환영합니다.]");
				} else {
					System.out.println("[회원 정보를 확인하세요.]");
				}
			} else {
				System.out.println("[가입 정보가 없습니다.]");
			}

		} else {
			System.out.println("[이미 로그인되어 있습니다.]");
		}
	}

	private void logout() {
		if (isLoggedIn()) {
			this.log = -1;
			System.out.println("[ " + um.getUser(log).getName() + " 님 로그아웃되었습니다.]");
		} else {
			System.out.println("[로그인이 되어 있지 않습니다.]");
		}
	}

	public void run() {
		while (true) {
			printMainMenu();
			int sel = inputNumber();

			if (sel == 1)
				joinUser();
			else if (sel == 2)
				leaveUser();
			else if (sel == 3)
				createAccount();
			else if (sel == 4)
				deleteAccount();
			else if (sel == 5)
				login();
			else if (sel == 6)
				logout();
			else if (sel == 7)
				printSubMenu();
			else if (sel == 0)
				break;
		}
		System.out.println("[시스템이 종료되었습니다.]");
	}

}
