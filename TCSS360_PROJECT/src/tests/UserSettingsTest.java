package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import info.UserSettings;

/**
 * Suite of tests for the UserSettings class. 
 * 
 * @author Evan McAllister
 * @version 1.0
 */
class UserSettingsTest 
{
	public File f;
	private UserSettings u;
	
	//Test that the UserSettings constructor for Strings parameters works. 
	@Test
	void testStringConstructor() 
	{
		String name = "John Smith";
		String email = "jsmith@email.com";
		u = new UserSettings(name, email);
		assertNotNull(u);
	}

	//Test that the UserSettings constructor for Files works. 
	@Test
	void testFileConstructor() throws IOException
	{
		f = new File("tempfile.txt");
		
		String firstName = "Fred";
		String email = "fred@email.com";
		

		PrintWriter writer = new PrintWriter(f);
		writer.println(firstName);
		writer.println(email);
		writer.close();
		
		u = new UserSettings(f);
		
		assertNotNull(u);
	}
	
	//Test that the setter / getter methods for the name parameter work.
	@Test
	void testNameSetGet() 
	{
		String name = "John Smith";
		String name2 = "D.B. Cooper";
		String email = "jsmith@email.com";
		u = new UserSettings(name, email);
		
		assertTrue(u.getFirstName().equals(name));
		
		u.updateFirstName(name2);
		
		assertTrue(u.getFirstName().equals(name2));
	}
	
	//Test that the setter / getter methods for the email parameter work.
	@Test
	void testEmailSetGet() 
	{
		String name = "John Smith";
		String email = "jsmith@email.com";
		String email2 = "johnny123@othermail.org";
		u = new UserSettings(name, email);
		
		assertTrue(u.getEmail().equals(email));
		
		u.updateEmail(email2);
		
		assertTrue(u.getEmail().equals(email2));
	}
	
	//Test that the UserSettings file import method works.
	@Test
	void testImport() throws IOException
	{
		f = new File("tempfile.txt");
		
		String name = "Fred";
		String email = "fred@email.com";
		
		String name2 = "Joe";
	    String email2 = "joe@email.com";
		
		PrintWriter writer = new PrintWriter(f);
		writer.println(name);
		writer.println(email);
		writer.close();

		u = new UserSettings(name2, email2);
		
		u.importSettings(f);
		
		assertTrue(u.getFirstName().equals(name));
		assertTrue(u.getEmail().equals(email)); 
	}
	
	//Test that the UserSettings file export method works.
	@Test
	void testExport() throws FileNotFoundException, IOException
	{
		String name = "Fred";
		String email = "fred@email.com";
		
		u = new UserSettings(name, email);
		
		//Note: if this fails, make sure the project has a "client files" folder. 
		u.exportSettings("tempfile");
		
		String outputLocation = System.getProperty("user.dir") 
				+ System.getProperty("file.separator") + "client files" + System.getProperty("file.separator")
				+ "tempfile.txt";
				
		Scanner importer = new Scanner(new File(outputLocation));
		String name2 = importer.nextLine();
		String email2 = importer.nextLine();
			
		assertTrue(u.getFirstName().equals(name2));
		assertTrue(u.getEmail().equals(email2));
		importer.close();
	}
}