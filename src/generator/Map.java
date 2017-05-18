package generator;

import java.util.ArrayList;

public class Map {
	
	private Item[][] map;
	private ArrayList<Room> rooms;
	private ArrayList<Room> corridors;
	
	private int width, height;
	
	public Map(int width, int height, int numRooms) {
		this.width = width;
		this.height = height;
		makeFilled();
		
		rooms = new ArrayList<Room>();
		corridors = new ArrayList<Room>();
		
		for (Room r : makeRooms(numRooms)) {
			addRoom(r);
		}
		
		connectRooms();
	}
	
	public void set(int x, int y, Item item) {
		map[y][x] = item;
	}
	
	public Item get(int x, int y) {
		return map[y][x];
	}
	
	public String toString() {
		String out = "";
		
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				out = out + get(i, j) + " ";
			}
			out = out + "\n";
		}
		
		return out;
	}
	
	private Room[] makeRooms(int num) {
		Room[] out = new Room[num];
		for (int i = 0; i < num; i++) {
			Room r = new Room(width, height);
			out[i] = r;
		}
		return out;
	}
	
	private void connectRooms() {
		for (int i = 0; i < rooms.size()-1; i++) {
			Room r1 = rooms.get(i);
			Room r2 = rooms.get(i+1);
			Room[] corridors = r1.connectTo(r2);
			for (Room corridor : corridors) {
				if (corridor != null) {
					addCorridor(corridor);
				}
				
			}
		}
	}
	
	private void addRoom(Room room) {
		rooms.add(room);
		for (int i = room.x; i < room.x + room.width; i++) {
			for (int j = room.y; j < room.y + room.height; j++) {
				set(i, j, Item.EMPTY);
			}
		}
	}
	
	private void addCorridor(Room corridor) {
		corridors.add(corridor);
		for (int i = corridor.x; i < corridor.x + corridor.width; i++) {
			for (int j = corridor.y; j < corridor.y + corridor.height; j++) {
				set(i, j, Item.CORRIDOR);
			}
		}
	}
	
	private void makeFilled() {
		map = new Item[height][width];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				set(i, j, Item.WALL);
			}
		}
	}
	
	

}
