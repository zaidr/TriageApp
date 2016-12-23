package csc.PIII.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import android.content.Context;

public class Login {
	private final String LOGINDATA = "passwords.txt";

	/** Map of usernames to passwords */
	private Map<String, User> users;
	
	/**
	 * Constructs a Login object to handle usernames and passwords, that have
	 * been saved to file.
	 * @param dir a File that is the directory to where the users database
	 * 	will be found
	 * @throws IOException
	 */
	public Login(File dir) throws IOException{
		this.users = new HashMap<String, User>();
		
		// Populates the users Map from file, if it exists
		File file = new File(dir, LOGINDATA);
		if (file.exists()) {
			loadUsers(file.getPath());
		} else {
			file.createNewFile();
		}
	}
	
	/**
	 * Loads the Users from file.
	 * @param filepath the path where the save file exists
	 * @throws FileNotFoundException if file is not found at time of loading
	 */
	private void loadUsers(String filepath) throws FileNotFoundException{
		Scanner scanner = new Scanner(new FileInputStream(filepath));
		String[] userdata;
		while(scanner.hasNextLine()) {
			userdata = scanner.nextLine().split(",");
	        users.put(userdata[0], new User(userdata[0],
	        		userdata[1], userdata[2]));
	    }
	    scanner.close();
	}
	
	/**
	 * Saves the shit Users to file.
	 * @param context context showing which directory to use
	 * @throws FileNotFoundException if file is not present at time of saving
	 */
	public void save(Context context) throws FileNotFoundException {
		FileOutputStream output = context.openFileOutput(LOGINDATA, 
				Context.MODE_PRIVATE);
		for (String user : users.keySet()) {
			User currUser = users.get(user);
			try {
				output.write((currUser.getUsername() + "," + 
						currUser.getUsername() + "," +
						currUser.getUserType() + "\n").getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks the supplied username and password combo against
	 * saved username and password of existing Users. 
	 * @param username the username to be checked
	 * @param password the password to be checked
	 * @return true when username exists in record, and password is the same as
	 * 			the one provided
	 * 			false otherwise
	 */
	public boolean validCredentials(String username, String password) {
        if (users.get(username) != null) {
        	User currUser = users.get(username);
        	return currUser.getPassword().equals(password);
        }
        return false;
	}
	
	public void addUser(String username, String password, String userType) {
		users.put(username, new User(username, password, userType));
	}
	
	/**
	 * Returns true if the user exists in the User database
	 * @param username the username of the user to check for
	 * @return  true if the user exists in the user database
	 * 			false otherwise
	 */
	public boolean userExists(String username) {
		for (String user : users.keySet()) {
			if (user.equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public String getUserType(String username) {
		return users.get(username).getUserType();
	}
}
