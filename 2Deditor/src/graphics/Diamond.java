/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Lukas
 */
public class Diamond implements GraphicsObject, Serializable{

	private static final long serialVersionUID = 7971658281737692629L;
	int[] coordinates = new int[4];
    private int originalX, originalY;
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
    private boolean isActive;
    private Color ColorDefault=Color.WHITE;
    private boolean shadE;
    private Color borderColor = Color.BLACK;

    public Diamond(int x1, int y1, int x2, int y2) {
        originalX = x1; //startCoordX
        originalY = y1; //startCoordY
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
//        currentX = x2;// - startX;
//        currentY = y2;//- startY;
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
        coordinates[0] = startX;
        coordinates[1] = startY;
        coordinates[2] = currentX;
        coordinates[3] = currentY;

        return coordinates;
    }

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
