package game;

import java.util.concurrent.Semaphore;

public class SexualCell extends Cell {
	private boolean isBusy = true;

	public SexualCell(int x, int y) {
		super(x, y);
	}

	public void setIsBusy(boolean value) {
		isBusy = value;
	}
	
	public boolean getBusy() {
		return isBusy;
	}
	
	public void eat() {
		super.eat();
		if(this.eatCount >= 10)
			isBusy = false;
	}
	
	@Override
	public void reproduce() throws InterruptedException {
		Object find;
		int i, j;
		for(i=-2; i<=2; i++) {
			for(j=-2; j<=2; j++) {
				Semaphore semaphore = new Semaphore(1);
				semaphore.acquire();
				find = map.get(xLocation+i, yLocation+j);
				if(find instanceof SexualCell) {
					if(((SexualCell) find).getBusy() == false) {
						map.setIsBusy(this, (SexualCell)find);
						int []location = ((SexualCell)find).getLocation();
						map.set(location[0], location[1], new SexualCell(location[0], location[1]));
						this.kill();
						((Cell)find).kill();
					}
				}
				semaphore.release();
			}
		}
	}

}
