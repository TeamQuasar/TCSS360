package objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class (File object) to create a new file. Each file
 * has an import date, name, and if necessary notes.
 * All of these fields are retrievable via getter methods.
 * 
 * @author Istanbul Idris
 * @version 1.0
 *
 */
public class HomeFile 
{ 
	/** Fixture to store the file name. */
	private String myFileName;
	/** Fixture to store the file notes. */
	private String myFileNotes;
	/** Fixture to store the file import date. */
	private final String myImportDate;
	
	/**
	 * Constructor for creating a new file.
	 * @author Idris Istanbul
	 *  
	 * @param theFileName - The name of the new file.
	 * @param theUserNotes - Notes for the new file.
	 */
	public HomeFile(final String theFileName, final String theUserNotes)
	{
		myFileName = theFileName;
		myFileNotes = theUserNotes;
		
		//Date Format
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		//If needed to see the time, use the line below instead.
		//DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		
		myImportDate = df.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * Constructor for creating a File without notes by default
	 * @author Romi Tshiorny
	 * 
	 * @param theFileName The name of the new file.
	 */
	public HomeFile(final String theFileName) {
		this(theFileName, "");
	}
	
	/**
	 * Method to append notes to the current file notes fixture.
	 * @author Idris Istanbul
	 * 
	 * @param theNote - The note to add.
	 */
	public void addNote(final String theNote)
	{
		myFileNotes = new StringBuilder().append(myFileNotes).append("\n").
										 append(theNote).toString();
	}
	
	/**
	 * Method to clear out all notes for the file.
	 * @author Idris Istanbul
	 */
	public void clearNotes()
	{
		myFileNotes = "";
	}
	
	/**
	 * Getter for file name.
	 * @author Idris Istanbul
	 * 
	 * @return myFileName - The file name fixture.
	 */
	public String getFileName()
	{
		return myFileName;
	}
	
	/**
	 * Getter for file notes.
	 * @author Idris Istanbul
	 * 
	 * @return myFileNotes - The file notes fixture.
	 */
	public String getFileNotes()
	{
		return myFileNotes;
	}
	
	/**
	 * Getter for file import date.
	 * @author Idris Istanbul
	 * 
	 * @return myImportDate - The file import date.
	 */
	public String getImportDate()
	{
		return myImportDate;
	}
	
	/**
	 * This will replace the file name with a new one.
	 * @author Idris Istanbul
	 * 
	 * @param theNewFileName - The new file name.
	 */
	public void setNewFileName(final String theNewFileName)
	{
		myFileName = theNewFileName;
	}
	
	@Override
	public String toString() {
		return myFileName;
	}
	
	@Override
	public boolean equals(Object theObject) {
		HomeFile h = (HomeFile) theObject;
		return h.getFileName() == this.getFileName() &&
				h.getFileNotes() == this.getFileNotes() &&
				h.getImportDate() == this.getImportDate();
	}
	
}