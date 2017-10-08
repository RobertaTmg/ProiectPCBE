package game;

public class Map {
	private Object [][]matrix = new Object[100][100];
	private final Map instance = new Map();
	
	public Map getInstance() {
		return instance;
	}
	
	public void set(int x, int y, Object other) {
		matrix[x][y] = other;
	}
	
	public synchronized void makeLoveNotWar(SexualCell male, SexualCell female) {
		if(male.getRelationshipStatus() == false && female.getRelationshipStatus() == false) {
			male.setRelationshipStatus(true);
			female.setRelationshipStatus(true);
		}
	}
	
	public synchronized void makeChild(SexualCell partner) {
		int[] location = partner.getLocation();
		partner = null;
		matrix[location[0]][location[1]] = new SexualCell(location[0], location[1]);
	}

}
