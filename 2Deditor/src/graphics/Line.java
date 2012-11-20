package graphics;

public class Line implements GraphicsObject {

    int[] coordinates = new int[4];
    private int startX;
    private int startY;
    private int currentX;
    private int currentY;
    private int FixReleasedCoordY;
    private int FixReleasedCoordX;
    private int FixStartCoordX;
    private int FixStartCoordY;
    boolean isVisible = true;
    private int copiedFrom = -1;

    public Line(int x1, int y1, int x2, int y2) {
        coordinates[0] = x1; //startCoordX
        coordinates[1] = y1; //startCoordY
        startX = x1;
        startY = y1;
        currentX = x2;// - startX;
        currentY = y2;
        FixStartCoordX = x1;
        FixStartCoordY = y1;
        FixReleasedCoordX = x2;
        FixReleasedCoordY = y2;
    }

    @Override
    public void setFinalCoordinates(int x2, int y2) {

        currentX = x2;// - startX;
        currentY = y2;//- startY;
    }

    @Override
    public int[] getStartEndXY() {
        coordinates[0] = startX;
        coordinates[1] = startY;
        coordinates[2] = currentX;
        coordinates[3] = currentY;

        return coordinates;
    }
    /*
     * opravitto treba lebo to nieje dobre, zatial mi to este nejde
     */

    @Override
    public void changeCoord(int x1, int y1, int x2, int y2) {
        startX = x1;
        startY = y1;
        currentX = x2;
        currentY = y2;
    }

    @Override
    public int getFixStartCoordX() {

        return FixStartCoordX;
    }

    @Override
    public void setFixStartCoordX(int x) {
        FixStartCoordX = x;
    }

    @Override
    public int getFixStartCoordY() {
        return FixStartCoordY;
    }

    @Override
    public void setFixStartCoordY(int x) {
        FixStartCoordY = x;
    }

    @Override
    public int getFixReleasedCoordX() {
        return FixReleasedCoordX;
    }

    @Override
    public void setFixReleasedCoordX(int x) {
        FixReleasedCoordX = x;
    }

    @Override
    public int getFixReleasedCoordY() {
        return FixReleasedCoordY;
    }

    @Override
    public void setFixReleasedCoordY(int x) {
        FixReleasedCoordY = x;
    }

    @Override
	public boolean visible() {
		return isVisible;
	}



	@Override
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public void setOrigin(int copiedFrom){
		this.copiedFrom = copiedFrom;
	}
	
	@Override
	public int getOrigin(){
		return copiedFrom;
	}
}
