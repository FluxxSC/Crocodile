package generator;

import java.util.Random;

public class Room {
	
	private final int MAX_WIDTH = 5;
	private final int MAX_HEIGHT = 5;
	private final int MIN_WIDTH = 3;
	private final int MIN_HEIGHT = 4;
	
	public int width, height;
	public int x, y;
	
	public Room(int x, int y, int width, int height) {
		if (width > 0) {
			this.x = x;
			this.width = width;
		} else {
			this.x = x + width;
			this.width = - width;
		}
		
		if (height > 0) {
			this.y = y;
			this.height = height;
		} else {
			this.y = y + height;
			this.height = - height;
		}		
	}
	
	public Room(int mapWidth, int mapHeight) {
		Random r = new Random();
		width = MIN_WIDTH + r.nextInt(MAX_WIDTH-MIN_WIDTH + 1);
		height = MIN_HEIGHT + r.nextInt(MAX_HEIGHT-MIN_HEIGHT + 1);
		
		x = r.nextInt(mapWidth - width - 1) + 1;
		y = r.nextInt(mapHeight - height - 1) + 1;
	}
	
	public String toString() {
		return "(x:" + x + "y:" + y + " w:" + width + "h:" + height + ")"; 
	}
	
	public Room[] connectTo(Room r) {
		Room[] out = new Room[2];
		
		if (! isConnectedTo(r)) {
			//System.out.println("NO: " + this + r);
			int[] start = getTile();
			int[] end = r.getTile();
			//System.out.println("start: (" + start[0] + "," + start[1] + " end: " + end[0] + "," + end[1] + ")");
			
			if (start[0] != end[0]) {
				out[0] = new Room(start[0], start[1], end[0]-start[0], 1);
			}
			if (start[1] != end[1]) {
				out[1] = new Room(end[0], start[1], 1, end[1]-start[1]);
			}
			
			
		} else {
			//System.out.println("YE: " + this + r);
		}
		
		return out;
	}
	
	public boolean isConnectedTo(Room r) {
		if (((x >= r.x && x <= r.x+r.width) || 
			(x + width >= r.x && x + width <= r.x + r.width)) &&
			((y >= r.y && y <= r.y+r.height) || 
			(y + height >= r.y && y + height <= r.y + r.height))) {
			return true;
		}
		return false;
	}
	
	public int[] getTile() {
		Random r = new Random();
		int[] out = new int[2];
		out[0] = x + r.nextInt(width);
		out[1] = y + r.nextInt(height);
		return out;
	}

}
