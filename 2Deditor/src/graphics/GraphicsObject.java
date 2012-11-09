package graphics;

public interface GraphicsObject {
<<<<<<< HEAD
	
	
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

=======

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

>>>>>>> master
    /**
     * @return the FixReleasedCoordX
     */
    public int getFixReleasedCoordX();
<<<<<<< HEAD
=======

>>>>>>> master
    public void setFixReleasedCoordX(int x);

    /**
     * @return the FixReleasedCoordY
     */
    public int getFixReleasedCoordY();
<<<<<<< HEAD
    public  void setFixReleasedCoordY(int x);
=======

    public void setFixReleasedCoordY(int x);

   // public void changeCoord(int x1, int x2, int y1, int y2);
   
>>>>>>> master
}
