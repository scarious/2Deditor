package graphics;

import java.awt.Image;

public class Picture implements GraphicsObject{
	Image image;
	
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

   

  

}
