package game;

import java.util.concurrent.Semaphore;

public class AsexualCell extends Cell {
	
	private Semaphore semaphore;
	public AsexualCell(int x, int y) {
		super(x, y);
		semaphore = new Semaphore(1,true);
		System.out.println("Asexuate Cell " + this.number + " is created");
	}

	@Override
	public void reproduce() throws InterruptedException {
		System.out.println("Asexuate Cell " + this.number + " is reproducing");
		Object find;
		int i, j;
		int children = 0;
		for (i = -2; i <= 2; i++)
			for (j = -2; j <= 2; j++) {
				semaphore.acquire();
				find = map.get(xLocation + i, yLocation + j);
				if (find == null) {
					if (children < 2) {
						children++;
						AsexualCell child =  new AsexualCell(xLocation + i, yLocation + j);
						map.set(xLocation + i, yLocation + j, child);
						child.start();
					}
					else if (children == 2) {
						break;
					}
				}
				semaphore.release();
			}
		
		if (children == 0) {
			throw new IllegalStateException("Not enough space for both childrens");
		}
		this.kill();
	}

}
