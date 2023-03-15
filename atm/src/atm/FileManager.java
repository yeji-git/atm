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
	
	public FileManager() {
		userFileName = "user.txt";
		accountFileName = "account.txt";
		
		userFile = new File(userFileName);
		accountFile = new File(accountFileName);
	}
	
	public void sava(ArrayList<User>, ArrayList) {}
	
}
