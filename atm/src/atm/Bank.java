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
		System.out.println("1. ȸ������");
		System.out.println("2. ȸ��Ż��");
		System.out.println("3. ���½�û");
		System.out.println("4. ����öȸ");
		System.out.println("5. �α���");
		System.out.println("6. �α׾ƿ�");
		System.out.println("7. ��ŷ");
		System.out.println("0. ����");
		System.out.println("-----------------");
	}
	
	private void printSubMenu() {
		System.out.println("1. �Ա��ϱ�");
		System.out.println("2. ����ϱ�");
		System.out.println("3. ��ȸ�ϱ�");
		System.out.println("4. ��ü�ϱ�");
		System.out.println("5. �ڷΰ���");
	}
	
	

	private boolean isLoggedIn() {
		if (log != -1) {
			return true;
		}
		return false;
	}

	private int inputNumber() {
		int number = -1;

		System.out.print("�޴� : ");
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
			System.out.print("ȸ������ ID : ");
			String id = scan.next();
			System.out.print("ȸ������ PW : ");
			String password = scan.next();
			System.out.print("�̸� : ");
			String name = scan.next();

			User user = new User(id, password, name);
			if (this.um.addUser(user) != null) {
				System.out.println("[ȸ������ ����]");
			} else {
				System.out.println("[�ߺ��� ���̵� �����մϴ�.]");
			}
		} else {
			System.out.println("[�α����� �Ǿ� �ֽ��ϴ�.]");
		}
	}

	private void leaveUser() {
		if (isLoggedIn()) {
			System.out.println("[Ż���Ͻðڽ��ϱ�? 1.y 2.n]");
			int leaveNumber = scan.nextInt();
			if (leaveNumber == 1) {
				um.deleteUser(log);
				log = -1;
				System.out.println("[Ż�� �Ϸ�Ǿ����ϴ�.]");
			}
		} else {
			System.out.println("[�α����� �Ǿ� ���� �ʽ��ϴ�.]");
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
					System.out.println("[���°� �ʰ��Ǿ����ϴ�.]");
				}
			} else {
				System.out.println("[��й�ȣ�� ��ġ���� �ʽ��ϴ�.]");
			}
		} else {
			System.out.println("[ȸ�� ������ Ȯ���ϼ���.]");
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
				System.out.println("[öȸ�� ���¸� ������ �ּ���.]");
				int deleteNumber = scan.nextInt();
				am.deleteAccount(deleteNumber);
				System.out.println("[öȸ�� �Ϸ�Ǿ����ϴ�.]");
			} else {
				System.out.println("[��й�ȣ�� ��ġ���� �ʽ��ϴ�.]");
			}
		} else {
			System.out.println("[ȸ�� ������ Ȯ���ϼ���.]");
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
					System.out.println("[ " + this.um.getUser(this.log).getName() + " �� ȯ���մϴ�.]");
				} else {
					System.out.println("[ȸ�� ������ Ȯ���ϼ���.]");
				}
			} else {
				System.out.println("[���� ������ �����ϴ�.]");
			}

		} else {
			System.out.println("[�̹� �α��εǾ� �ֽ��ϴ�.]");
		}
	}

	private void logout() {
		if (isLoggedIn()) {
			this.log = -1;
			System.out.println("[ " + um.getUser(log).getName() + " �� �α׾ƿ��Ǿ����ϴ�.]");
		} else {
			System.out.println("[�α����� �Ǿ� ���� �ʽ��ϴ�.]");
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
		System.out.println("[�ý����� ����Ǿ����ϴ�.]");
	}

}
