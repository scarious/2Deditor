package graphics;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pencil implements GraphicsObject, Serializable {

    private static final long serialVersionUID = 5706226038264555875L;
    //int[] coordinates = new int[];
    List<Integer> coordinates = new ArrayList<>();
    int x = 0, y = 1;
    private int copiedFrom = -1;
    boolean isVisible = true;
    private Color ColorDefault;
    private boolean shadE;
    private Color borderColor;

    public Pencil(int x1, int y1, int x2, int y2) {
        coordinates.add(x1);
        coordinates.add(y1);
        ColorDefault=Color.BLACK;
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

    public List<Integer> getValues() {
        return coordinates;
    }

    @Override
    public void changeCoord(int x1, int x2, int y1, int y2) {
    }

    @Override
    public int getFixStartCoordX() {
        return 0;
    }

    @Override
    public void setFixStartCoordX(int x) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixStartCoordY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixStartCoordY(int x) {
        //  throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixReleasedCoordX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixReleasedCoordX(int x) {
        //  throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFixReleasedCoordY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFixReleasedCoordY(int x) {
        // throw new UnsupportedOperationException("Not supported yet.");
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
    public void setOrigin(int copiedFrom) {
        this.copiedFrom = copiedFrom;
    }

    @Override
    public int getOrigin() {
        return copiedFrom;
    }

    @Override
    public void setActive(boolean active) {
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setColor(Color color) {
        ColorDefault = color;
    }

    @Override
    public Color getColor() {
        return ColorDefault;
    }

    @Override
    public boolean isShade() {
        return shadE;
    }

    @Override
    public void setShade(boolean shade) {
        shadE = shade;
    }
    
    @Override
    public void setBorderColor(Color color) {
        borderColor=color;
    }

    @Override
    public Color getBorderColor() {
       return borderColor;
    }
    
}
