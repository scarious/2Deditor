package graphics;

public class Rectangle implements GraphicsObject {

    int[] coordinates = new int[4]; //0,1 - X, Y          2,3 - width, height
    int originalX, originalY, startX, startY, currentX, currentY;
    int swapX, swapY, width, height;
    private int FixReleasedCoordY;
    private int FixReleasedCoordX;
    private int FixStartCoordX;
    private int FixStartCoordY;

    public Rectangle(int x1, int y1, int x2, int y2) {
        originalX = x1; //startCoordX
        originalY = y1; //startCoordY
        startX = x1;
        startY = y1;
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
    public void changeCoord(int x1, int y1, int x2, int y2) {
        coordinates[0] =startX= x1;
        coordinates[1] = startY=y1;
      
         coordinates[2] =currentX = x2;
         coordinates[3] = currentY =y2;
        
      //   setFinalCoordinates(x2, y2);


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

    @Override
    public int[] getStartEndXY() {
        if ((currentX - startX > 0) || (currentY - startY > 0)) {
         //   System.out.println(currentX + "   " + startX);
            width = currentX - startX;
            height = currentY - startY;
          //  System.out.println(width + "   " + height);
        } 
//        else {
//            width = 0;    
//            height = 0;
//        }
        
        coordinates[0] = startX;
        coordinates[1] = startY;
        coordinates[2] = width;
        coordinates[3] = height;
//        System.out.println(coordinates[2] + "    " +  coordinates[3]);
//       System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        System.out.println("aa" + coordinates[0] + " " + coordinates[1] + " " + coordinates[2]+ " " + coordinates[3]);
//        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return coordinates;
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
}
