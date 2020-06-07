package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import objects.HomeFile;
import objects.Room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomTest 
{
	/** Fixture to hold the Room object. */
	private Room myRoom;
	
	/**
	 * Will create a new instance of a Room object before every test
	 * method.
	 * 
	 * @author Istanbul Idris
	 */
	@BeforeEach
	void setUp()
	{
		myRoom = new Room("TestRoom");
	}
	
	/**
	 * Test constructor to see if initialization of 
	 * fields works.
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testRoom() 
	{	
		String roomName = myRoom.getRoomName();
		
		//Test to see if constructor initializes Room Name field
		assertEquals(roomName, "TestRoom");
	}

	/**
	 * Test to addRoom() method, which adds 2 new rooms and
	 * and calls getSubRooms(). Which returns the subRooms field
	 * and checks to see that the size of that field is only 2 as only 2
	 * new rooms were added at the beginning.
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testAddRoom() 
	{
		//Adds new rooms
		myRoom.addRoom("TestSubRoom1");
		myRoom.addRoom("TestSubRoom2");
		
		//Should be true as there is 2 sub rooms in the Home Room
		assertTrue(myRoom.getSubRooms().size() == 2);
	}
	
	/**
	 * Test for removeRoom(), which adds 2 new rooms and then
	 * removes one of them. Checks that it works by checking if the
	 * removed room still exists.
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testRemoveRoom()
	{
		//Adds new rooms
		myRoom.addRoom("TestSubRoom1");
		myRoom.addRoom("TestSubRoom2");
		//Remove TestSubRoom1
		myRoom.removeRoom("TestSubRoom1");
		
		//Should be false; Creates an exact copy of room that was removed to check if it exists
		assertFalse(myRoom.getSubRooms().contains(new Room("TestSubRoom1")));
	}

	//@Test
	//void testSaveRoom()
	
	//@Test
	//void testLoadRoom() 
	
	/**
	 * Test for findRoom(), which adds 2 new rooms, and checks if
	 * findROom() returns the room that is specified. 
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testFindRoom() 
	{
		//Adds new rooms
		myRoom.addRoom("TestSubRoomRoom1");
		myRoom.addRoom("SubRoomFindMe!");
		
		//If not null then room was found
		assertNotNull(myRoom.findRoom("SubRoomFindMe!"));
	}

	/**
	 * Tests the getter getSubRooms(), by checking if it returns
	 * a correct instance.
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testGetSubRooms() 
	{	//Will check if it returns an instance of a Set as subRooms are stored in a Set	
		assertTrue(myRoom.getSubRooms() instanceof Set<?>);
	}
	
	/**
	 * Tests the getter getFiles(), by checking if it returns
	 * a correct instance.
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testGetFiles()
	{   //Will check if it returns an instance of a Set as files are stored in a Set	
		assertTrue(myRoom.getFiles() instanceof Set<?>);
	}
	
	/**
	 * Tests the getter getRoomName(), by checking if it returns
	 * the name of the Room that was setup in the constructor.
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testGetRoomName() 
	{
		assertTrue(myRoom.getRoomName().equals("TestRoom"));
	}

	/**
	 * Tests the filterFile(). This method creates a new sub room and then
	 * adds a new file to that sub room. Then it checks to see if specific
	 * keywords that exist in a file name string. If they do exist
	 * then it will return that file inside a Set.
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testFilterFile()
	{	//Creates a new room to store a file into
		myRoom.addRoom("SubRoomTest");
		
		//File to be stored in the room, which will be searched for later down
		HomeFile testFile = new HomeFile("TestFile", "");
		
		//Loops through the subRooms and adds a file to the room (only one room exists currently)
		for (Room r : myRoom.getSubRooms())
		{	//Add file
			r.addFile(testFile);
		}

		//Will return true if a file name contains the string "TestF"
		assertTrue(myRoom.filterFiles("TestF").contains(testFile));
	}

	/**
	 * Tests testFile(), This method creates a new file and adds it
	 * to the Room. Then it will check if that file exists in the
	 * myFiles field. 
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testAddFile()
	{
		myRoom.addRoom("TestSubRoom");
		
		Room subRoom = Room.loadRoom("TestSubRoom");
		
		HomeFile testFile = new HomeFile("testFile", "");
		//Adds new file to Room, no files exist currently
		subRoom.addFile(testFile);
		
		//Will check if the file was properly added to the Room
		assertTrue(subRoom.getFiles().contains(testFile));
	}

	/**
	 * Test to see if the toString() method returns the room name.
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testToString()
	{
		assertEquals(myRoom.toString(), myRoom.getRoomName());
	}
	
	/**
	 * Tests equals(), which checks if two rooms are the same.
	 * 
	 * @author Istanbul Idris
	 */
	@Test
	void testEquals()
	{	//Should be True; Checks if current Room equals other Room
		assertTrue(myRoom.equals(new Room("TestRoom")));
	}

}
