package main;

import graphics.GraphicsObject;

import java.io.Serializable;
import java.util.ArrayList;

public class OwnFormat implements Serializable {

	private static final long serialVersionUID = -4601148467748658584L;
	ArrayList<GraphicsObject> go;
	int goIndex;
	
	public OwnFormat(ArrayList<GraphicsObject> go, int goIndex) {
		this.go = go;
		this.goIndex = goIndex;
	}
	
	public ArrayList<GraphicsObject> getDrawList(){
		return this.go;
	}
	
	public int getArrayIndex(){
		return this.goIndex;
	}

}
