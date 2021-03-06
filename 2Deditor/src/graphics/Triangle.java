package graphics;

import java.awt.Color;
import java.io.Serializable;

public class Triangle implements GraphicsObject, Serializable {

	private static final long serialVersionUID = -26705300878522162L;
	int[] coordinates = new int[5];
    private int startX;
    private int startY;
    private int currentX;
    private int currentY;
    private int FixReleasedCoordY;
    private int FixReleasedCoordX;
    private int FixStartCoordX;
    private int FixStartCoordY;
    private int copiedFrom = -1;
    boolean isVisible = true;

    public Triangle (int x1, int y1, int x2, int y2, int x3, int y3) {
        coordinates[0] = x1; //startCoordX
        coordinates[1] = y1; //startCoordY
        startX = x1;
        startY = y1;

    }

    @Override
    public void setFinalCoordinates(int x2, int y2) {

        currentX = x2;// - startX;
        currentY = y2;//- startY;
    }

    @Override
    public int[] getStartEndXY() {
        coordinates[0] = startX;
        coordinates[1] = startY;
        coordinates[2] = currentX;
        coordinates[3] = currentY;

        return coordinates;
    }
    /*
     * opravitto treba lebo to nieje dobre, zatial mi to este nejde
     */

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisible(boolean isVisible) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isShade() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setShade(boolean shade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBorderColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getBorderColor() {
		// TODO Auto-generated method stub
		return null;
	}
}




//x[0]=100; x[1]=150; x[2]=50;
//y[0]=100; y[1]=150; y[2]=150;
//n = 3;
//
//Polygon p = new Polygon(x, y, n);  // This polygon represents a triangle with the above
//                                   //   vertices.
//
//g.fillPolygon(p); 