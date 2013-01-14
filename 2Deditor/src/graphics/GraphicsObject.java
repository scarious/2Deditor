package graphics;

import java.awt.Color;

/**
 *
 * @author Lukas
 */
public interface GraphicsObject {

    /**
     *
     * @param x2
     * @param y2
     */
    public void setFinalCoordinates(int x2, int y2);

    /**
     *
     * @return
     */
    public int[] getStartEndXY();

    /**
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    public void changeCoord(int x1, int x2, int y1, int y2);

    /**
     * @return the FixStartCoordX
     */
    public int getFixStartCoordX();

    /**
     *
     * @param x
     */
    public void setFixStartCoordX(int x);

    /**
     * @return the FixStartCoordY
     */
    public int getFixStartCoordY();

    /**
     *
     * @param x
     */
    public void setFixStartCoordY(int x);

    /**
     * @return the FixReleasedCoordX
     */
    public int getFixReleasedCoordX();

    /**
     *
     * @param x
     */
    public void setFixReleasedCoordX(int x);

    /**
     * @return the FixReleasedCoordY
     */
    public int getFixReleasedCoordY();

    /**
     *
     * @param x
     */
    public void setFixReleasedCoordY(int x);

    /**
     *
     * @return
     */
    public boolean visible();

    /**
     *
     * @param isVisible
     */
    public void setVisible(boolean isVisible);

    /**
     *
     * @param copiedFrom
     */
    public void setOrigin(int copiedFrom);

    /**
     *
     * @return
     */
    public int getOrigin();

    /**
     * Nastavi objekt na aktivny ak bol predtym neaktivny, alebo naopak.
     * @param active 
     */
    public void setActive(boolean active);

    /**
     * Vracia true ak je objekt aktivny, inac false
     * @return
     */
    public boolean isActive();
    
    /**
     * Vracia true ak ma objekt tienovanie, inac false
     * @return
     */
    public boolean isShade();
    
    /**
     * Nastavi objektu tienovanie, alebo ho skryje
     * @param shade
     */
    public void setShade(boolean shade);

    /**
     * Nastavi farbu objektu
     * @param color
     */
    public void setColor(Color color);

    /**
     * Vrati aktualnu farbu objektu
     * @return
     */
    public Color getColor();
    
    public void setBorderColor(Color color);
    
    public Color getBorderColor();
}
