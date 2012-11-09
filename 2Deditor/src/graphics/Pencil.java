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
