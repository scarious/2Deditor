package graphics;

import java.util.ArrayList;
import java.util.List;

public class Pencil implements GraphicsObject {

	//int[] coordinates = new int[];
	List<Integer> coordinates = new ArrayList<>();
	int x = 0, y = 1;
	
	public Pencil(int x1, int y1, int x2, int y2){
		coordinates.add(x1);
		coordinates.add(y1);
	} 
	
	@Override
	public void setFinalCoordinates(int x2, int y2) {
		coordinates.add(x2);
		coordinates.add(y2);
	}

	@Override
	public int[] getStartEndXY() {
		return null;
	}
	
	public List<Integer> getValues(){
		return coordinates;
	}

}
