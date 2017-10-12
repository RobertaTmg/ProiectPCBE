package game;

public class SexualCell extends Cell {
	private boolean inARelationship = true;

	public SexualCell(int x, int y) {
		super(x, y);
	}

	public void setRelationshipStatus(boolean value) {
		inARelationship = value;
	}
	
	public boolean getRelationshipStatus() {
		return inARelationship;
	}
	
	public void eat() {
		super.eat();
		if(this.eatCount >= 10)
			inARelationship = false;
	}
	
	@Override
	public void reproduce() {
		Object find;
		int i, j;
		int xFirst = 0, yFirst = 0, xSecond = 0, ySecond = 0;
		int children = 0;
		for(i=-2; i<=2; i++)
			for(j=-2; j<=2; j++) {
				find = map.get(xLocation+i, yLocation+j);
				if(find instanceof SexualCell) {
					if(((SexualCell) find).getRelationshipStatus() == false) {
						map.makeLoveNotWar(this, (SexualCell)find);
						int []location = ((SexualCell)find).getLocation();
						map.set(location[0], location[1], new SexualCell(location[0], location[1]));
						//die both old cells
					}
				}
			}
	}

}
