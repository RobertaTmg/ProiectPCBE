package game;

import java.util.concurrent.Semaphore;

public class AsexualCell extends Cell {
	
	private Semaphore semaphore;
	public AsexualCell(int x, int y) {
		super(x, y);
		semaphore = new Semaphore(1,true);
	}

	@Override
	public void reproduce() throws InterruptedException {
		Object find;
		int i, j;
		int children = 0;
		// semaphore start
		for (i = -2; i <= 2; i++)
			for (j = -2; j <= 2; j++) {
				semaphore.acquire();
				find = map.get(xLocation + i, yLocation + j);
				if (find == null) {
					if (children < 2) {
						children++;
						map.set(xLocation + i, yLocation + j, new AsexualCell(xLocation + i, yLocation + j));
					}
					if (children == 2) {
						break;
					}
				}
				semaphore.release();
			}
		
		// semaphore stop
		if (children < 2) {
			throw new IllegalStateException("Not enough space for childrens");

		}
		this.kill();
	}

}
