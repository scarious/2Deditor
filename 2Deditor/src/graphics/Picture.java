package graphics;

import java.awt.Image;

public class Picture implements GraphicsObject{
	Image image;
	public Picture(Image img){
		this.image = img;
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
