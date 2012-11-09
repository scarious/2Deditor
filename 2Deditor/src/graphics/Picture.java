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
<<<<<<< HEAD
    public int getFixStartCoordY() {
=======
    public void setFixStartCoordX(int x) {
>>>>>>> master
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
<<<<<<< HEAD
    public int getFixReleasedCoordX() {
=======
    public int getFixStartCoordY() {
>>>>>>> master
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
<<<<<<< HEAD
    public void setFixReleasedCoordX(int x) {
=======
    public void setFixStartCoordY(int x) {
>>>>>>> master
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
<<<<<<< HEAD
    public int getFixReleasedCoordY() {
=======
    public int getFixReleasedCoordX() {
>>>>>>> master
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
<<<<<<< HEAD
    public void setFixReleasedCoordY(int x) {
=======
    public void setFixReleasedCoordX(int x) {
>>>>>>> master
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
<<<<<<< HEAD
    public void setFixStartCoordX(int x) {
=======
    public int getFixReleasedCoordY() {
>>>>>>> master
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
<<<<<<< HEAD
    public void setFixStartCoordY(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

=======
    public void setFixReleasedCoordY(int x) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   

  

>>>>>>> master
}
