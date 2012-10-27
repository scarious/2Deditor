package graphics;

public class Rectangle implements GraphicsObject {

int[] coordinates = new int[6];
int swapX, swapY;
	
	public Rectangle(int x1, int y1, int x2, int y2){
		coordinates[0] = x1; //startCoordX
		coordinates[1] = y1; //startCoordY
		coordinates[2] = x2; //releasedCoordX
		coordinates[3] = y2; //releasedCoordY
		coordinates[4] = x1; //swapX - clickedCoordX
		coordinates[5] = y2; //swapY - clickedCoordY
	}
	
	@Override
	public void setFinalCoordinates(int x2, int y2) {
		coordinates[2] = x2; 
		coordinates[3] = y2;
		
		if (coordinates[0] > coordinates[2]) { //startCoordX < releasedCoordX
			coordinates[4] = coordinates[2];	//clickedCoordX = releasedCoordX
            coordinates[2] = coordinates[0];	//releasedCoordX = startCoordX
        }

        if (coordinates[1] > coordinates[3]) {	//startCoordY < releasedCoordY
        	coordinates[5] = coordinates[3];	//clickedCoordY = releasedCoordY
        	coordinates[3] = coordinates[1];	//releasedCoordY = startCoordY
        }
		
	}

	@Override
	public int[] getStartEndXY() {
		return coordinates;
	}

}
