package graphics;

public class Line implements GraphicsObject {

int[] coordinates = new int[4];
	
	public Line(int x1, int y1, int x2, int y2){
		coordinates[0] = x1;
		coordinates[1] = y1;
		coordinates[2] = x2;
		coordinates[3] = y2;
	}
	
	@Override
	public void setFinalCoordinates(int x2, int y2) {
		coordinates[2] = x2;
		coordinates[3] = y2;
	}

	@Override
	public int[] getStartEndXY() {
		return coordinates;
	}
}
