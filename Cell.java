package game;

public abstract class Cell {
	private int T_full;
	private int T_starve;
	private int eatCount;
	private int xLocation;
	private int yLocation;
	
	public Cell(int x, int y) {
		T_full = 0;
		T_starve = 120; //miliseconds
		eatCount = 0;
		xLocation = x;
		yLocation = y;
	}
	
	public int[] getLocation() {
		int[] location = {xLocation, yLocation};
		return location;
	}
	
	public void eat(){
		T_full = 120; //miliseconds
		eatCount++;
		if(eatCount >= 10)
			this.reproduce();
		else searchFood();
	}
	
	public void move (int x, int y) {
		//map.set(xLocation, yLocation, between)
		//map.set(x, y, this)
		//map.set(xLocation, yLocation, null)
		
		//map.move(xLocation, yLocation, x, y, this)
		xLocation = x;
		yLocation = y;
		
	}
	
	public void searchFood() {
		//search [-2,2]
		//if found check if is not busy and set food busy and move
		//else move random
	}
	
	public abstract void reproduce();
	
}
