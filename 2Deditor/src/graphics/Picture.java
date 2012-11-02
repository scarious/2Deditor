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
}
