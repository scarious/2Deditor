/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class ElipseDiagram implements GraphicsObject, Serializable {

	private static final long serialVersionUID = -8616786407829105724L;
	int[] coordinates = new int[4]; //0,1 - X, Y          2,3 - width, height
    int startX, startY, currentX, currentY;
    int swapX, swapY, width, height;
    private int originalX, originalY;
    private int FixReleasedCoordY;
    private int FixReleasedCoordX;
    private int FixStartCoordX;
    private int FixStartCoordY;
    boolean isVisible = true;
    private int copiedFrom = -1;
    private boolean isActive;
    private Color ColorDefault= Color.WHITE;
    private boolean shadE;
    private Color borderColor= Color.BLACK;

    public ElipseDiagram(int x1, int y1, int x2, int y2) {
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
//        if (originalY > currentY) {	//startCoordY < releasedCoordY
//            startY = currentY;
//            currentY = originalY;
//        } else {
            startY = originalY;
//        }
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
        coordinates[3] = width/2;
        return coordinates;
    }


    /**
     * @return the FixStartCoordX
     */@Override
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

    // @Override
    @Override
    public void setFixReleasedCoordX(int x) {
        FixReleasedCoordX = x;

    }

    //@Override
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
	public void setOrigin(int copiedFrom){
		this.copiedFrom = copiedFrom;
	}
	
	@Override
	public int getOrigin(){
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
    
     @Override
    public void setColor(Color color) {
        ColorDefault= color;
    }

    @Override
    public Color getColor() {
       return ColorDefault;
    }
    
    @Override
    public boolean isShade() {
        return shadE;
    }

    @Override
    public void setShade(boolean shade) {
        shadE = shade;
    }
    
    
    @Override
    public void setBorderColor(Color color) {
        borderColor=color;
    }

    @Override
    public Color getBorderColor() {
       return borderColor;
    }
}
