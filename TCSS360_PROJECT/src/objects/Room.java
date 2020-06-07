package objects;

import java.util.HashSet;
import java.util.Set;

/**
 * Class (Room object) to create a new file. Each file
 * has an import date, name, and if necessary notes.
 * All of these fields are retrievable via getter methods.
 * 
 * @author Tshiorny Romi, 
 * @author Istanbul Idris
 * @version 1.0
 *
 */
public class Room {

		/** Set to hold the subRooms. */
		Set<Room> mySubRooms;
		
		/** Set to hold the files for current room. */
		Set<HomeFile> myFiles;
		
		/** Current Room name (folder name). */
		String myRoomName;
		//SearchEngine mySearchEngine;
		
		/**
		 * Constructor to create the Room object.
		 * 
		 * @author Tshiorny Romi
		 * 
		 * @param theRoomName - Name for the room (folder name).
		 */
		public Room(String theRoomName) {
			myRoomName = theRoomName;
			mySubRooms = new HashSet<Room>();
			myFiles = new HashSet<HomeFile>();
			//TODO make search engine
		}
		
		/**
		 * Method that will add a room to the current room.
		 * 
		 * @author Idris Istanbul
		 * 
		 * @param theRoomName - The name of the room to be added.
		 */
		public void addRoom(String theRoomName) {
			mySubRooms.add(new Room(theRoomName));
		}
		
		/**
		 * Method will search thru the subRooms Set to find 
		 * a specific room.
		 * 
		 * @author Idris Istanbul
		 * 
		 * @param theRoomName - Name of the room to search for.
		 * @return r - Room that was specified to be searched.
		 */
		public Room findRoom(String theRoomName) {
			for(Room r : mySubRooms) {
				if(r.getRoomName().equals(theRoomName)) {
					return r;
				}
			}
			return null;
		}
		
		/**
		 * Accessor method for the subRooms set.
		 * 
		 * @author Tshiorny Romi
		 * @return - SubRoom set.
		 */
		public Set<Room> getSubRooms() {
			return mySubRooms;
		}
		
		/**
		 * Accessor method for the files for current room.
		 * 
		 * @author Tshiorny Romi
		 * @return - Files for current room.
		 */
		public Set<HomeFile> getFiles() {
			return myFiles;
		}
		
		/**
		 * Accessor method for the room name.
		 * 
		 * @author Tshiorny Romi
		 * 
		 * @return - Current room name.
		 */
		public String getRoomName() {
			return myRoomName;
		}
		
		/**
		 * Method to find a file.
		 */
		public void findFile() {
			//TODO
			//SearchEngine
		}
		
		/**
		 * Method to export files.
		 * 
		 */
		public void exportFile() {
			//TODO
		}
		
		/**
		 * Method to add files to current room.
		 * 
		 * @author Tshiorny Romi
		 * 
		 * @param theFile - The file to be added.
		 */
		public void addFile(HomeFile theFile) {
			myFiles.add(theFile);
		}
		
		/**
		 * Removes a file from the room.
		 * 
		 * @author Collin Nguyen
		 * @param theFile - The file to be removed.
		 */
		public void removeFile(HomeFile theFile) {
			myFiles.remove((HomeFile) theFile);
		}
		
		/**
		 * Return string
		 * 
		 * @author Collin Nguyen
		 */
		@Override
		public String toString() {
			return myRoomName;
		}
		
}
