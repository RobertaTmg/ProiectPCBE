package game;

public abstract class Cell implements Runnable{
	protected int T_full;
	protected int T_starve;
	protected int eatCount;
	protected int xLocation;
	protected int yLocation;

	protected Map map = Map.getInstance();
	private FoodResource food = null;

	public Cell(int x, int y) {
		T_full = 0;
		T_starve = 5;
		eatCount = 0;
		xLocation = x;
		yLocation = y;
	}

	public int[] getLocation() {
		int[] location = { xLocation, yLocation };
		return location;
	}

	public void eat() {
		T_full = 5;
		eatCount++;
	}

	public void move(int x, int y) {
		map.set(xLocation, yLocation, new Between());
		map.set(x, y, this);
		map.set(xLocation, yLocation, food);
		xLocation = x;
		yLocation = y;
		food = null;
	}

	public void moveRandom() {
		int i = 0;
		int j = 0;
		while (i == 0 && j == 0) {
			i = (int) Math.floor(Math.random() * 5 - 2);
			j = (int) Math.floor(Math.random() * 5 - 2);
		}
		this.move(xLocation + i, yLocation + j);
	}

	public void searchFood() { // trebuie facut sub semafor?
		Object find;
		int i, j;
		for (i = -2; i <= 2; i++)
			for (j = -2; j <= 2; j++) {
				find = map.get(xLocation + i, yLocation + j);
				if (find instanceof FoodResource) {
					this.move(xLocation + i, yLocation + j);
					this.eat();
					food = (FoodResource) find;
					((FoodResource) find).eat();
				}
			}
	}

	public abstract void reproduce();

	public void run() {
		// semaphore.up
		if(eatCount >= 10)
			this.reproduce();
		if (T_full <= 0)
			this.searchFood();
		this.moveRandom();
		// semaphore.down
		if (T_full > 0)
			T_full--;
		else if (T_starve > 0)
			T_starve--;
		// else die
	}

}
