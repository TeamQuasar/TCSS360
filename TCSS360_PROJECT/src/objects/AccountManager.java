package objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Class that controls logging in/out and which user is currently 
 * accessing the program.
 * @author Romi Tshiorny
 *
 */
public  class AccountManager {
	
	
	/**
	 * Location for list of currently existing users
	 */
	public final static File SAVE_FILE = new File(System.getProperty("user.dir") 
			+ System.getProperty("file.separator") + "client files" + System.getProperty("file.separator") + "users.ser");
	/**
	 * List containing all available Users/accounts for the program
	 */
	public ArrayList<User> myUserList;
	
	/**
	 * The current user accessing the application
	 */
	private User currentUser;
	
	/**
	 * Default constructor, pulls the list of users from a file via deserialize() method.
	 * @author Romi Tshiorny
	 */
	public AccountManager() {
		myUserList = new ArrayList<User>();
		currentUser = null;
		deserialize();
	}
	
	/**
	 * Private method for storing the contents of userList into a a file via
	 * serialization turning it into a byte stream.
	 * @author Romi Tshiorny
	 */
	private void serialize() {
		try {
			FileOutputStream outFile = new FileOutputStream(SAVE_FILE);
			ObjectOutputStream writer = new ObjectOutputStream(outFile);
			writer.writeObject(myUserList);
			writer.close();
			outFile.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads in the byte stream data from a file to get update the current list of users
	 * @author Romi Tshiorny
	 */
	@SuppressWarnings("unchecked")
	private void deserialize() {
		try {
			FileInputStream inFile = new FileInputStream(SAVE_FILE);
			ObjectInputStream reader = new ObjectInputStream(inFile);
			myUserList = (ArrayList<User>) reader.readObject();
			reader.close();
			inFile.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
	}
	
	/**
	 * Getter for the current user
	 * @author Romi Tshiorny
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Creates an new user with the entered username and password and adds it to the list and data file.
	 * The first user created is always an admin.
	 * If a username is already in use an account is not made and false is returned
	 * @author Romi Tshiorny
	 * @param theUsername entered Username
	 * @param thePassword entered Password
	 * @return true if creation was successful, false otherwise
	 */
	public boolean createAccount(String theUsername, String thePassword) {
		for(User u: myUserList) {
			if(theUsername.equals(u.getUsername())) {
				return false; // USERNAME ALREADY EXISTS
			}
		}
		
		if(myUserList.size() == 0) {
			myUserList.add(new User(theUsername,thePassword, true)); //First user made is always administrator
		} 
		else {
			myUserList.add(new User(theUsername,thePassword));
		}
		
		serialize();
		return true;
	}
	
	/**
	 * Method for checking the list of users and seeing if the entered password/username match any of them
	 * If a match is found the user is logged in.
	 * @author Romi Tshiorny
	 * @param theUsername entered username
	 * @param thePassword entered password
	 * @return true if the credentials were successful, false otherwise.
	 */
	public boolean enterCredentials(String theUsername, String thePassword) {
		for(User u: myUserList) {
			if(u.verifyCredentials(theUsername, thePassword)) {
				login(u);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sets the current user to the user being logged into
	 * @author Romi Tshiorny
	 * @param theUser the user to be logged in
	 */
	private void login(User theUser) {
		currentUser  = theUser;
	}
	
	/**
	 * Logs the user out by setting the current user to null
	 * @author Romi Tshiorny
	 */
	public void logout() {
		currentUser = null;
	}
	
	/**
	 * Completely clears all the users from the both the program and internal data
	 */
	public void clearUsers() {
		logout();
		myUserList = new ArrayList<User>();
		serialize();
		
	}
	
//  //Just some code for testing the serialization process
//	public static void main(String args[]) {
//		System.out.println("Testing for serialization:");
//		AccountManager manager = new AccountManager();
//		//System.out.println("Creation Success:" + manager.createAccount("John Doe", "123"));
//		//System.out.println("Creation Success:" + manager.createAccount("Jane Doe", "123"));
//		//System.out.println("Creation Success:" + manager.createAccount("Bob Keener", "12345"));
//		//manager.clearUsers();
//		for(User u: manager.myUserList) {
//			System.out.println(u.getUsername() + " isAdmin = " + u.getAdminStat());
//		}
//	}
	
	
	

}
