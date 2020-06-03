package objects;

import java.io.Serializable;
import info.UserSettings;

/**
 * User class for controlling permissions and access
 * @author Romi Tshiorny
 *
 */
public class User implements Serializable{
	
	/**
	 * Generated serialVersion ID
	 */
	private static final long serialVersionUID = 1348706745575000814L;

	/**
	 * Boolean for if the user is an admin or not
	 */
	private boolean adminStatus;
	
	/**
	 * String for the users' login username
	 */
	private String myUsername;
	
	/**
	 * String for the users' login password
	 */
	private String myPassword;
	
	/**
	 * User-settings for each user
	 */
	private UserSettings mySettings;

	/**
	 * Constructor for creating a user, the first user made should
	 * always be an admin.
	 * @author Romi Tshiorny
	 * @param username desired Username
	 * @param password desired Password
	 * @param isAdmin admin status
	 */
	public User(String username, String password, boolean isAdmin) {
		adminStatus = isAdmin;
		myUsername = username;
		myPassword = password;		
	}
	
	/**
	 * Constructor for creating a default user, admin status set to false
	 * @author Romi Tshiorny
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this(username, password, false);
	}
	
	/**
	 * Getter for adminStatus
	 * @author Romi Tshiorny
	 * @return true if the user is an admin false otherwise
	 */
	public boolean getAdminStat() {
		return adminStatus;
	}
	
	/**
	 * Method for checking if an entered username and password match the users'
	 * @author Romi Tshiorny
	 * @param enteredUsername username for login
	 * @param enteredPassword password for login
	 * @return True if the username and password match the user
	 */
	public boolean verifyCredentials(String enteredUsername, String enteredPassword) {
		return (myUsername.equals(enteredUsername)) && (myPassword.equals(enteredPassword));
	}
	
	/**
	 * Getter method for the user settings
	 * Base settings should be set on user creation
	 * @author Romi Tshiorny
	 * @return the UserSettings object for the user
	 */
	public UserSettings getSettings() {
		return mySettings;
	}
	
	/**
	 * Setter method for the user settings
	 * Base settings should be set on user creation
	 * @author Romi Tshiorny
	 */
	public void setSettings(UserSettings settings) {
		mySettings = settings;
	}
	
	/**
	 * Getter for myUsername
	 * @author Romi Tshiorny
	 * @return the Username
	 */
	public String getUsername() {
		return myUsername;
	}
	
}
