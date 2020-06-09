package info;
import java.io.IOException;
import java.net.*;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A short class for storing information about Homeowner's Manual Pro.
 * 
 * @author Evan McAllister
 * @author Romi Tshiorny
 * @author Collin Nguyen
 * @author Idris Istanbul
 * @version 1.8
 */
public class About extends JOptionPane
{
	/**
	 * Serial Verison UID
	 */
	private static final long serialVersionUID = -1591965767072803047L;

	/*
	 * The name of the piece of software. 
	 */
	private final String SOFTWARE_NAME = "Homeowner's Manual PRO";
	
	/*
	 * String array containing the names of all the developers of the software. 
	 */
	private final String[] DEVELOPERS = {"Romi Tshiorny", "Evan McAllister", "Collin Nguyen", "Idris Istanbul"};
	
	/*
	 * String representation of the current software version number.
	 */
	private String myVersion;
	
	/*
	 * The class constructor. 
	 */
	public About() throws IOException
	{
		/* This piece of code retrieves the HTML code as a String and finds the version being displayed.
		 * Temporary solution to a problem we really aren't sure how to solve yet.
		 */
		URL repository = new URL("https://github.com/TeamQuasar/TCSS360/blob/master/TCSS360_PROJECT/src/info/About.java");
		Scanner html = new Scanner(repository.openStream());
		String version = "";
		while(html.hasNext()) {
			version = html.nextLine() +"\n";
			if(version.contains("@version")) {
				version = version.substring(version.indexOf("@"));
				version = version.substring(version.indexOf(" "));
				version = version.substring(1, version.indexOf("<"));
				break;
			}
		}
		myVersion = version;
		html.close();
	}
	
	/*
	 * Getter method for the array containing the names of the software developers.
	 * 
	 * @return Array of names of the software developers.  
	 */
	public String[] getAuthors()
	{
		return DEVELOPERS;
	}
	
	/*
	 * Getter method for the String representation of the name of the software. 
	 * 
	 * @return Name of the software. 
	 */
	public String getName()
	{
		return SOFTWARE_NAME;
	}
	
	/*
	 * Getter method for the String representation of the software version.
	 * 
	 * @return Current software version. 
	 */
	public String getVersion()
	{
		return myVersion; 
	}
	
	/**
	 * Shows the dialog box
	 */
	public void show() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel name = new JLabel(getName());
		JLabel version = new JLabel("Version: " + getVersion());
		JLabel authors = new JLabel(Arrays.toString(getAuthors()));
		name.setAlignmentX(CENTER_ALIGNMENT);
		version.setAlignmentX(CENTER_ALIGNMENT);
		authors.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(name);
		panel.add(version);
		panel.add(authors);
		JOptionPane.showMessageDialog(null, panel, "About", JOptionPane.DEFAULT_OPTION);
	}
		
}
