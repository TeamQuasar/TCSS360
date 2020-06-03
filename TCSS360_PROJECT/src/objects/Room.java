package objects;

import java.util.HashSet;
import java.util.Set;

public class Room {

		Set<Room> mySubRooms;
		Set<HomeFile> myFiles;
		String myRoomName;
		//SearchEngine mySearchEngine;
		
		public Room(String theRoomName) {
			myRoomName = theRoomName;
			mySubRooms = new HashSet<Room>();
			myFiles = new HashSet<HomeFile>();
			//TODO make search engine
		}
		
		public void addRoom(String theRoomName) {
			mySubRooms.add(new Room(theRoomName));
		}
		
		public Room findRoom(String theRoomName) {
			for(Room r : mySubRooms) {
				if(r.getRoomName().equals(theRoomName)) {
					return r;
				}
			}
			return null;
		}
		
		public Set<Room> getSubRooms() {
			return mySubRooms;
		}
		
		public Set<HomeFile> getFiles() {
			return myFiles;
		}
		
		public String getRoomName() {
			return myRoomName;
		}
		
		public void findFile() {
			//TODO
			//SearchEngine
		}
		
		public void exportFile() {
			//TODO
		}
		
		public void addFile(HomeFile theFile) {
			myFiles.add(theFile);
		}
		
		@Override
		public String toString() {
			return myRoomName;
		}
		
}
