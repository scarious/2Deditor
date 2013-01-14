package graphics;

import java.awt.Image;
import java.io.Serializable;

public class Picture implements GraphicsObject, Serializable{

	private static final long serialVersionUID = 2560992972815265877L;
	Image image;
	private int copiedFrom = -1;
	boolean isVisible = true;
	
	public Picture(Image img){
		this.image = img;
	}
	
	public Picture(Image img, int width, int height){
		this.image = img.getScaledInstance(width, height, 0);
	}
	
	@Override
	public void setFinalCoordinates(int x2, int y2) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int[] getStartEndXY() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Image getImage(){
		return image;
	}

    @Override
    public void changeCoord(int x1, int x2, int y1, int y2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixStartCoordX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixStartCoordX(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixStartCoordY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixStartCoordY(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixReleasedCoordX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixReleasedCoordX(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixReleasedCoordY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixReleasedCoordY(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isActive() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   

  

}
