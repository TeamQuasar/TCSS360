package info;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class for info stored about a user that can be imported/exported.
 * @author Romi Tshiorny
 * @version 1.0
 */
public class UserSettings implements Serializable 
{

	//Serial ID for this class. 
	private static final long serialVersionUID = 3469195591373160702L;
	
	//Field to hold user's first name. 
	private String firstName;
	
	//Field to hold user's email address. 
	private String email;
	
	/**
	 * Constructor for settings when making a new user from scratch.
	 * @param firstName user inputed first name
	 * @param email user inputed email
	 */
	public UserSettings(String firstName, String email) {
		this.firstName = firstName;
		this.email = email;
	}
	/**
	 * Constructor for importing user settings.
	 * @param importFile file to import data from
	 */
	public UserSettings(File importFile) throws IOException
	{
		importSettings(importFile);
	}	
	
	/**
	 * Setter for firstName.
	 * @param firstName user inputed first name
	 */
	public void updateFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Setter for email.
	 * @param email user inputed first name
	 */
	public void updateEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Getter for firstName.
	 * @return the first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Getter for email.
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Method for importing settings from a file.
	 * @param importFile file to import
	 */
	public void importSettings(File importFile) throws IOException
	{
		Scanner importer = new Scanner(importFile);
		firstName = importer.nextLine();
		email = importer.nextLine();
		importer.close();
	}
	
	/**
	 * Method for exporting settings to a file.
	 * @param fileName desired name for export file
	 */
	public void exportSettings(String fileName) throws FileNotFoundException
	{
		String outputLocation = System.getProperty("user.dir") 
				+ System.getProperty("file.separator") + "client files" + System.getProperty("file.separator")
				+ fileName + ".txt";

		File exportFile =  new File(outputLocation);
		PrintWriter writer = new PrintWriter(exportFile);
		writer.println(firstName);
		writer.println(email);
		writer.close();
	}
}
