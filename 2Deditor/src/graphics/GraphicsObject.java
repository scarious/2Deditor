package graphics;

public interface GraphicsObject {
	
	
	public void setFinalCoordinates(int x2, int y2);
	
	public int[] getStartEndXY();
        
        public void changeCoord(int x1, int x2, int y1, int y2);
        
       /**
     * @return the FixStartCoordX
     */
    public int getFixStartCoordX();

    /**
     * @return the FixStartCoordY
     */
    public int getFixStartCoordY();
   

    /**
     * @return the FixReleasedCoordX
     */
    public int getFixReleasedCoordX();
    public void setFixReleasedCoordX(int x);

    /**
     * @return the FixReleasedCoordY
     */
    public int getFixReleasedCoordY();
    public  void setFixReleasedCoordY(int x);
}
