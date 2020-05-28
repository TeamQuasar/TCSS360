package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import objects.HomeFile;

class HomeFileTest 
{
	private HomeFile f; 
	
	@Test
	public void testFileCreation()
	{
		f = new HomeFile("filename", "loren ipsum");
		assertTrue(f != null);
	}
	
	@Test
	public void testGetName()
	{
		f = new HomeFile("filename", "loren ipsum");
		assertEquals("filename", f.getFileName());
	}
	
	@Test
	public void testGetNotes()
	{
		f = new HomeFile("filename", "loren ipsum");
		assertEquals("loren ipsum", f.getFileNotes());
	}
	
	@Test
	public void testGetDate()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String date = df.format(Calendar.getInstance().getTime());
		f = new HomeFile("filename", "loren ipsum");
		assertTrue(date.equals(f.getImportDate()));
	}
	
	@Test
	public void testAddNotes()
	{
		f = new HomeFile("filename", "loren ipsum");
		f.addNote("a");
		assertEquals("loren ipsum\na", f.getFileNotes());
		f.addNote(" b ");
		assertEquals("loren ipsum\na\n b ", f.getFileNotes());
		f.addNote("");
		assertEquals("loren ipsum\na\n b \n", f.getFileNotes());
	}
	
	@Test
	public void testClearNotes()
	{
		f = new HomeFile("filename", "loren ipsum");
		f.clearNotes();
		assertEquals("", f.getFileNotes());
	}
	
	@Test
	public void testChangeName()
	{
		f = new HomeFile("filename", "loren ipsum");
		f.setNewFileName("newname");
		assertEquals("newname", f.getFileName());
	}
}
