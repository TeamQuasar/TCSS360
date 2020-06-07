package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import objects.SearchEngine;
import objects.Room;
import objects.HomeFile;

/*
 * A suite of test cases for the SearchEngine class.
 * @author Evan McAllister
 * @version 1.0
 */
class SearchEngineTest 
{
	private SearchEngine search;
	private HashSet<Room> rooms;
	
	//Setup method
	@BeforeEach
	void setup()
	{
		Room room1 = new Room("one");
		Room room2 = new Room("two");
		
		//Make a few HomeFiles to add to Room #1.
		room1.addFile(new HomeFile("abc"));
		room1.addFile(new HomeFile("def"));
		room1.addFile(new HomeFile("abcdef"));
		
		//Make a few HomeFiles to add to Room #2.
		room2.addFile(new HomeFile("ABC"));
		room2.addFile(new HomeFile("DEF"));
		room2.addFile(new HomeFile("ABCDEF"));
		
		//Add subs to Rooms
		room1.addRoom("sub1a");
		room1.addRoom("sub1b");
		room2.addRoom("sub2a");
		room2.addRoom("sub2b");
		
		Integer i = 1;
		
		//Add files to subRooms
		for (Room r: room1.getSubRooms())
		{
			r.addFile(new HomeFile("g" + i.toString()));
			i++;
		}
		
		for (Room r: room2.getSubRooms())
		{
			r.addFile(new HomeFile("g" + i.toString()));
			i++;
		}
		
		//Make a set of Rooms
		rooms = new HashSet<Room>();
		rooms.add(room1);
		rooms.add(room2);
		
		//Add to SearchEngine
		search = new SearchEngine(rooms);
	}
	
	//Test that a SearchEngine is constructed. 
	@Test
	void constructorTest() 
	{
		assertNotNull(search);
	}
	
	//Test for the SearchEngine's set setter. 
	@Test
	void changeSetTest() 
	{
		assertEquals(2, search.getDataSize());
		search.changeSet(new HashSet<Room>());
		assertEquals(0, search.getDataSize());
	}
	
	//Test for the SearchEngine's set getter. 
	@Test
	void getSetTest() 
	{
		HashSet<Room> rooms = search.getSet();
		assertEquals(rooms.size(), 2);
	}
	
	//Test for the SearchEngine's set copy getter. 
	@Test
	void setCopyTest() 
	{
		HashSet<Room> roomCopy = search.getSetCopy();
		HashSet<Room> roomActual = search.getSet();
		rooms.add(new Room("new"));
		assertEquals(roomCopy.size(), 2);
		assertEquals(roomActual.size(), 3);
	}
	
	@Test
	void searchTest() 
	{
		/*List of file names in default search:
			Room1: abc, def, abcdef
				sub1a: g1
				sub1b: g2
			Room2: ABC, DEF, ABCDEF
				sub2a: g3
				sub2b: g4
		*/
		
		//Should match: abc, abcdef, ABC, ABCDEF
		HashSet<HomeFile> results1 = search.searchMe("a");
		
		//Should match: abcdef, ABCDEF
		HashSet<HomeFile> results2 = search.searchMe("cd");
		
		//Should match: g2
		HashSet<HomeFile> results3 = search.searchMe("2");
		
		//Should match: g1, g2, g3, g4
		HashSet<HomeFile> results4 = search.searchMe("g");
		
		assertEquals(4, results1.size());
		assertEquals(2, results2.size());
		assertEquals(1, results3.size());
		assertEquals(4, results4.size());
	}

}
