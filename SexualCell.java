package game;

public class SexualCell extends Cell {
	private boolean inARelationship = false;

	public SexualCell(int x, int y) {
		super(x, y);
	}

	public void setRelationshipStatus(boolean value) {
		inARelationship = value;
	}
	
	public boolean getRelationshipStatus() {
		return inARelationship;
	}
	
	@Override
	public void reproduce() {
		//search for partner
		
		//map.makeLoveNotWar(me, partner)
		
		//map.makeChild(partner)
		
		//die
	}

}
