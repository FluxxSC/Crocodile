package game;

public enum Commands {
	
	HELLO("0"),
	ID("1"),
	CHAT("2"),
	UNKNOWN("-1");
	
	private String key;
	
	Commands(String key) {
		this.key = key;
	}
	
	public String toString() {
		return key;
	}
	
	public static Commands is(String key) {
		switch (key) {
		case "0":
			return Commands.HELLO;
		case "1":
			return Commands.ID;
		case "2":
			return Commands.ID;
		default:
			return Commands.UNKNOWN;
		}
	}

}
