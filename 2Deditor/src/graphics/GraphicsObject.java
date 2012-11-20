package graphics;

public interface GraphicsObject {

    public void setFinalCoordinates(int x2, int y2);

    public int[] getStartEndXY();


      public void changeCoord(int x1, int x2, int y1, int y2);
     /**
     * @return the FixStartCoordX
     */
    public int getFixStartCoordX();

    public void setFixStartCoordX(int x);

    /**
     * @return the FixStartCoordY
     */
    public int getFixStartCoordY();

    public void setFixStartCoordY(int x);

    /**
     * @return the FixReleasedCoordX
     */
    public int getFixReleasedCoordX();

    public void setFixReleasedCoordX(int x);

    /**
     * @return the FixReleasedCoordY
     */
    public int getFixReleasedCoordY();

    public void setFixReleasedCoordY(int x);


    public boolean visible();
    public void setVisible(boolean isVisible);
    
    public void setOrigin(int copiedFrom);
    public int getOrigin();
   
}
