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

	public void banking() {
		if (isLoggedIn()) {
			while (true) {
				printSubMenu();
				int sel = inputNumber();

				if (sel == 1)
					bankDeposit();
				else if (sel == 2)
					bankWithdraw();
				else if (sel == 3)
					bankInformation();
				else if (sel == 4)
					bankTransfer();
				else if (sel == 5)
					break;
			}
		} else {
			System.out.println("[�α����� �Ǿ� ���� �ʽ��ϴ�.]");
		}
	}

	private void bankDeposit() {
		System.out.println("--- �Ա� ---");
		User user = this.um.getUser(log);
		if (user.getAccountSize() > 0) {
			for (int i = 0; i < user.getAccountSize(); i++) {
				System.out.printf("[%d. %s]\n", i, am.getAccount(i).getAccNum());
			}
			System.out.println("[�Ա��Ͻ� ���¸� ������ �ּ���.]");
			int depositAcc = scan.nextInt();

			System.out.println("[�Ա��Ͻ� �ݾ��� �Է��� �ּ���.]");
			int depositMoney = scan.nextInt();

			if (depositMoney > 0) {
				Account account = this.am.getAccount(depositAcc);
				account.setMoney(depositMoney);
				System.out.println("[�ԱݵǾ����ϴ�.]");
			} else {
				System.out.println("[�ݾ��� Ȯ���� �ּ���.]");
			}
		} else {
			System.out.println("[���¸� ���� ������ �ּ���.]");
		}
	}

	private void bankWithdraw() {
		System.out.println("--- ��� ---");
		User user = this.um.getUser(log);
		if (user.getAccountSize() > 0) {
			for (int i = 0; i < user.getAccountSize(); i++) {
				System.out.printf("[%d. %s]\n", i, am.getAccount(i).getAccNum());
			}
			System.out.println("[����Ͻ� ���¸� ������ �ּ���.]");
			int withdrawAcc = scan.nextInt();
	
			System.out.println("[����Ͻ� �ݾ��� �Է��� �ּ���.]");
			int withdrawMoney = scan.nextInt();
	
			if (withdrawMoney > 0 && withdrawMoney <= am.getAccount(withdrawAcc).getMoney()) {
				Account account = this.am.getAccount(withdrawAcc);
				account.setMoney(-withdrawMoney);
				System.out.println("[��ݵǾ����ϴ�.]");
			} else {
				System.out.println("[�ܾ��� �����մϴ�.]");
			}
		} else {
			System.out.println("[���¸� ���� ������ �ּ���.]");
		}
	}

	private void bankInformation() {
		System.out.println("--- ��ȸ ---");
		User user = this.um.getUser(log);
		if (user.getAccountSize() > 0) {
			for (int i = 0; i < user.getAccountSize(); i++) {
				System.out.printf("[%d. %s %d��]\n", i, am.getAccount(i).getAccNum(), am.getAccount(i).getMoney());
			}			
		} else {
			System.out.println("[���¸� ���� ������ �ּ���.]");
		}
	}

	private void bankTransfer() {
		System.out.println("--- ��ü ---");
		User user = this.um.getUser(log);
		if (user.getAccountSize() > 0) {
			for (int i = 0; i < user.getAccountSize(); i++) {
				System.out.printf("[%d. %s]\n", i, am.getAccount(i).getAccNum());
			}
			System.out.println("[��ü�Ͻ� ���¸� ������ �ּ���.]");
			int withdrawAcc = scan.nextInt();
	
			System.out.println("[��ü�Ͻ� �ݾ��� �Է��� �ּ���.]");
			int withdrawMoney = scan.nextInt();
	
			if (withdrawMoney > 0 && withdrawMoney <= am.getAccount(withdrawAcc).getMoney()) {
				Account account = this.am.getAccount(withdrawAcc);
				account.setMoney(-withdrawMoney);
				System.out.println("[��ü�Ǿ����ϴ�.]");
			} else {
				System.out.println("[�ܾ��� �����մϴ�.]");
			}
		} else {
			System.out.println("[���¸� ���� ������ �ּ���.]");
		}
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
		System.out.println("--- ȸ������ ---");
		if (!isLoggedIn()) {
			System.out.print("ȸ������ ID : ");
			String id = scan.next();
			System.out.print("ȸ������ PW : ");
			String password = scan.next();
			System.out.print("�̸� : ");
			String name = scan.next();

			User user = new User(id, password, name);
			if (this.um.addUser(user) != null) {
				System.out.println("[ȸ������ �Ǿ����ϴ�.]");
			} else {
				System.out.println("[�ߺ��� ���̵� �����մϴ�.]");
			}
		} else {
			System.out.println("[�α����� �Ǿ� �ֽ��ϴ�.]");
		}
	}

	private void leaveUser() {
		System.out.println("--- ȸ��Ż�� ---");
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
		System.out.println("--- ���½�û ---");
		if (isLoggedIn()) {
			User user = this.um.getUser(log);

			if (user.getAccountSize() < Account.LIMIT) {
				String id = user.getId();
				Account account = this.am.createAccount(new Account(id));
				this.um.setUser(user, account, Account.ADD);
				System.out.println("[���� ������ �Ϸ�Ǿ����ϴ�.]");
			} else {
				System.out.println("[���� ������ �ʰ��Ǿ����ϴ�.]");
			}
		} else {
			System.out.println("[�α����� �Ǿ� ���� �ʽ��ϴ�.]");
		}
	}

	private void deleteAccount() {
		System.out.println("--- ����öȸ ---");
		if (isLoggedIn()) {
			User user = this.um.getUser(log);

			for (int i = 0; i < user.getAccountSize(); i++) {
				System.out.printf("[%d. %s]\n", i, this.am.getAccount(i).getAccNum());
			}
			System.out.println("[öȸ�� ���¸� ������ �ּ���.]");
			int deleteNumber = scan.nextInt();
			this.am.deleteAccount(deleteNumber);
			Account delAcc = user.getAccount(deleteNumber);
			this.um.setUser(user, delAcc, Account.DELETE);
			System.out.println("[öȸ�� �Ϸ�Ǿ����ϴ�.]");
		} else {
			System.out.println("[�α����� �Ǿ� ���� �ʽ��ϴ�.]");
		}
	}

	private void login() {
		System.out.println("--- �α��� ---");
		if (!isLoggedIn()) {
			if (this.um.getUserSize() != 0) {
				System.out.print("id : ");
				String id = scan.next();
				System.out.print("pw : ");
				String password = scan.next();

				for (int i = 0; i < this.um.getUserSize(); i++) {
					if (this.um.getUser(i).getId().equals(id) && this.um.getUser(i).getPassword().equals(password)) {
						this.log = i;
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
		System.out.println("--- �α׾ƿ� ---");
		if (isLoggedIn()) {
			this.log = -1;
			System.out.println("[ " + this.um.getUser(log).getName() + " �� �α׾ƿ��Ǿ����ϴ�.]");
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
				banking();
			else if (sel == 0)
				break;
		}
		System.out.println("[�ý����� ����Ǿ����ϴ�.]");
	}

}
