package graphics;

public class Rectangle implements GraphicsObject {

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
        }
		
	}

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

}
