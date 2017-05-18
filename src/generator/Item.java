package generator;

public enum Item {
	
	EMPTY		(" "),
	WALL		("X"),
	CORRIDOR	(" ");
	
	private String key;
	
	Item(String key) {
		this.key = key;
	}
	
	public String toString() {
		return key;
	}
	
	

}
