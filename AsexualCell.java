package game;

public class AsexualCell extends Cell {

	public AsexualCell(int x, int y) {
		super(x, y);
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
				if(find == null) {
					if(children == 0) {
						map.set(xLocation+i, yLocation+j, new Between());
						xFirst = xLocation+i;
						yFirst = yLocation+j;
						children = 1;
					}
					else if(children == 1) {
						map.set(xLocation+i, yLocation+j, new Between());
						xSecond = xLocation+i;
						ySecond = yLocation+j;
						children = 2;
						break;
					}
				}
			}
		if(children == 2) {
			map.set(xFirst, yFirst, new AsexualCell(xFirst, yFirst));
			map.set(xSecond, ySecond, new AsexualCell(xSecond, ySecond));
			//die
		}
		else if(children == 1) {
			map.set(xFirst, yFirst, null);
		}
	}

}
