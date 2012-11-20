/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

/**
 *
 * @author Admin
 */
public class ElipseDiagram implements GraphicsObject {

    int[] coordinates = new int[4]; //0,1 - X, Y          2,3 - width, height
    int startX, startY, currentX, currentY;
    int swapX, swapY, width, height;
    private int originalX, originalY;
    private int FixReleasedCoordY;
    private int FixReleasedCoordX;
    private int FixStartCoordX;
    private int FixStartCoordY;
    private int copiedFrom = -1;
    boolean isVisible = true;

    public ElipseDiagram(int x1, int y1, int x2, int y2) {
        originalX = x1; //startCoordX
        originalY = y1; //startCoordY
        startX = x1;
        startY = y1;
        width = 0;
        height = 0;
    }

    @Override
    public void changeCoord(int x1, int y1, int x2, int y2) {
        coordinates[0] = startX = x1;
        coordinates[1] = startY = y1;

        currentX = coordinates[2] = x2;
        currentY = coordinates[3] = y2;
        // System.out.println(" pociatocne: (" + x1 +"," + y1 + ")" + "(" + x2 +"," + y2 + ")");
    }

    @Override
    public int[] getStartEndXY() {
        if ((currentX - startX > 0) || (currentY - startY > 0)) {
            width = Math.abs(currentX - startX);
            height = Math.abs(currentY - startY);

            height = width / 2;
        }



         else {
            width = 0;
            height = 0;
        }
        coordinates[0] = startX;
        coordinates[1] = startY;
        coordinates[2] = width;
        coordinates[3] = height;
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        System.out.println("aa" + coordinates[0] + " " + coordinates[1] + " " + coordinates[2]+ " " + coordinates[3]);
//        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return coordinates;
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

    /**
     * @return the FixStartCoordX
     */
    public int getFixStartCoordX() {
        return FixStartCoordX;
    }

    /**
     * @return the FixStartCoordY
     */
    public int getFixStartCoordY() {
        return FixStartCoordY;
    }

    /**
     * @return the FixReleasedCoordX
     */
    public int getFixReleasedCoordX() {
        return FixReleasedCoordX;
    }

    /**
     * @return the FixReleasedCoordY
     */
    public int getFixReleasedCoordY() {
        return FixReleasedCoordY;
    }

    // @Override
    public void setFixReleasedCoordX(int x) {
        FixReleasedCoordX = x;

    }

    //@Override
    public void setFixReleasedCoordY(int x) {
        FixReleasedCoordY = x;
    }

    /**
     * @param FixStartCoordX the FixStartCoordX to set
     */
    public void setFixStartCoordX(int FixStartCoordx) {
        FixStartCoordX = FixStartCoordx;
    }

    /**
     * @param FixStartCoordY the FixStartCoordY to set
     */
    public void setFixStartCoordY(int FixStartCoordy) {
        FixStartCoordY = FixStartCoordy;
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