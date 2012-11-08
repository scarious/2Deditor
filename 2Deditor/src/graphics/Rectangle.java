package graphics;

public class Rectangle implements GraphicsObject {

<<<<<<< HEAD
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
=======
int[] coordinates = new int[4]; //0,1 - X, Y          2,3 - width, height
int originalX, originalY, startX, startY, currentX, currentY;
int swapX, swapY, width, height;
	
	public Rectangle(int x1, int y1, int x2, int y2){
		originalX = x1; //startCoordX
		originalY = y1; //startCoordY
		startX = x1;
		startY = x1;
		width = 0;
		height = 0;
	}
	
	@Override
	public void setFinalCoordinates(int x2, int y2) {
		currentX = x2; //aktualne x
		currentY = y2; //aktualne y
		
		if (originalX > currentX) { 
			startX = currentX;
			currentX = originalX;
		} else {
			startX = originalX;
		}
		
        if (originalY > currentY) {	//startCoordY < releasedCoordY
        	startY = currentY;
			currentY = originalY;
        } else {
        	startY = originalY;
>>>>>>> 52bc1389a4b4d56ec6ecca8ecdb9d360a97e7d33
        }

<<<<<<< HEAD
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
=======
	@Override
	public int[] getStartEndXY() {
		if( (currentX - startX > 0) || (currentY - startY > 0) ){
			width = currentX - startX;
			height = currentY - startY;
		} else {
			width = 0;
			height = 0;
		}
		coordinates[0] = startX;
		coordinates[1] = startY;	
		coordinates[2] = width;
		coordinates[3] = height;
		return coordinates;
	}
>>>>>>> 52bc1389a4b4d56ec6ecca8ecdb9d360a97e7d33

    @Override
    public void setFixReleasedCoordY(int x) {
        this.FixReleasedCoordY=x;
    }
}
