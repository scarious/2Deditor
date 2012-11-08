package graphics;

public class Rectangle implements GraphicsObject {

    int[] coordinates = new int[6];
    int swapX, swapY;
    private int FixStartCoordX;
    private int FixStartCoordY;
    private int FixReleasedCoordX;
    private int FixReleasedCoordY;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.FixStartCoordX = this.coordinates[0] = x1; //startCoordX
        this.FixStartCoordY = this.coordinates[1] = y1; //startCoordY
        this.FixReleasedCoordX = this.coordinates[2] = x2; //releasedCoordX
        this.FixReleasedCoordY = this.coordinates[3] = y2; //releasedCoordY
        coordinates[4] = x1; //swapX - clickedCoordX
        coordinates[5] = y2; //swapY - clickedCoordY
    }

    @Override
    public void setFinalCoordinates(int x2, int y2) {
        this.coordinates[2] = x2;
        this.coordinates[3] = y2;

        if (this.coordinates[0] > this.coordinates[2]) { //startCoordX < releasedCoordX
            coordinates[4] = this.coordinates[2];	//clickedCoordX = releasedCoordX
            this.coordinates[2] = this.coordinates[0];	//releasedCoordX = startCoordX
        }

        if (this.coordinates[1] > this.coordinates[3]) {	//startCoordY < releasedCoordY
            coordinates[5] = this.coordinates[3];	//clickedCoordY = releasedCoordY
            this.coordinates[3] = this.coordinates[1];	//releasedCoordY = startCoordY
        }

    }

    @Override
    public int[] getStartEndXY() {
        return this.coordinates;
    }

    @Override
    public void changeCoord(int x1, int y1, int x2, int y2) {
        this.coordinates[0] = x1;
        this.coordinates[1] = y1;
        this.coordinates[2] = x2;
        this.coordinates[3] = y2;
    }

    /**
     * @return the FixStartCoordX
     */
    public int getFixStartCoordX() {
        return this.FixStartCoordX;
    }

    /**
     * @return the FixStartCoordY
     */
    public int getFixStartCoordY() {
        return this.FixStartCoordY;
    }

    /**
     * @return the FixReleasedCoordX
     */
    public int getFixReleasedCoordX() {
        return this.FixReleasedCoordX;
    }

    /**
     * @return the FixReleasedCoordY
     */
    public int getFixReleasedCoordY() {
        return this.FixReleasedCoordY;
    }


    @Override
    public void setFixReleasedCoordX(int x) {
       this.FixReleasedCoordX=x;
       
    }

    @Override
    public void setFixReleasedCoordY(int x) {
        this.FixReleasedCoordY=x;
    }
}
