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

    @Override
    public void changeCoord(int x1, int x2, int y1, int y2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixStartCoordX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixStartCoordY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixReleasedCoordX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixReleasedCoordX(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixReleasedCoordY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixReleasedCoordY(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixStartCoordX(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixStartCoordY(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
