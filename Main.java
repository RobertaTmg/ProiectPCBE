package game;

public class Main {

	public static void main(String[] args) {
		Map map = Map.getInstance();
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				map.set(i, j, new Free());
			}
		}
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				map.set(i, j, new FoodResource(1));
			}
		}
		Cell cell = new AsexualCell(5, 5);
		Cell cell1 = new AsexualCell(5, 6);
		map.set(5, 5, cell);
		map.set(5, 6, cell1);
		cell.start();
		cell1.start();
	}

}
