package graphics;

import java.io.Serializable;

import javax.swing.JTextArea;

public class TextArea implements GraphicsObject, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3826810746829941166L;
	int[] coordinates = new int[4]; //0,1 - X, Y          2,3 - width, height
    int originalX, originalY, startX, startY, currentX, currentY;
    int swapX, swapY, width, height;
    private int FixReleasedCoordY;
    private int FixReleasedCoordX;
    private int FixStartCoordX;
    private int FixStartCoordY;
    boolean isVisible = true, isActive=false;
    int copiedFrom = -1;
    JTextArea textArea;

    public TextArea(int x1, int y1, int x2, int y2) {
        originalX = x1; //startCoordX
        originalY = y1; //startCoordY
        startX = x1;
        startY = y1;
        width = x2;
        height = y2;
        FixStartCoordX = startX;
        FixStartCoordY = startY;
        FixReleasedCoordX = startX + width;
        FixReleasedCoordY = startY + height;
        textArea= new JTextArea();
 
    }
    
    public JTextArea getTextArea(){
        return textArea;
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
    public void changeCoord(int x1, int y1, int x2, int y2) {
        coordinates[0] = startX = x1;
        coordinates[1] = startY = y1;

        coordinates[2] = currentX = x2;
        coordinates[3] = currentY = y2;

    }
    
    @Override
    public int[] getStartEndXY() {
        if ((currentX - startX > 0) || (currentY - startY > 0)) {
            width = currentX - startX;
            height = currentY - startY;
        }
        coordinates[0] = startX;
        coordinates[1] = startY;
        coordinates[2] = width;
        coordinates[3] = height;
        return coordinates;
    }


    /**
     * @return the FixStartCoordX
     */
    @Override
    public int getFixStartCoordX() {
        return FixStartCoordX;
    }

    /**
     * @return the FixStartCoordY
     */
    @Override
    public int getFixStartCoordY() {
        return FixStartCoordY;
    }

    /**
     * @return the FixReleasedCoordX
     */
    @Override
    public int getFixReleasedCoordX() {
        return FixReleasedCoordX;
    }

    /**
     * @return the FixReleasedCoordY
     */
    @Override
    public int getFixReleasedCoordY() {
        return FixReleasedCoordY;
    }

    @Override
    public void setFixReleasedCoordX(int x) {
        FixReleasedCoordX = x;

    }

    @Override
    public void setFixReleasedCoordY(int x) {
        FixReleasedCoordY = x;
    }

    /**
     * @param FixStartCoordX the FixStartCoordX to set
     */
    @Override
    public void setFixStartCoordX(int FixStartCoordx) {
        FixStartCoordX = FixStartCoordx;
    }

    /**
     * @param FixStartCoordY the FixStartCoordY to set
     */
    @Override
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
    public void setOrigin(int copiedFrom) {
        this.copiedFrom = copiedFrom;
    }

    @Override
    public int getOrigin() {
        return copiedFrom;
    }

    @Override
    public void setActive(boolean active) {
      isActive=active;
    }

    @Override
    public boolean isActive() {
      return isActive;
    }
}
