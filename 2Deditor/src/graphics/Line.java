package graphics;

public class Line implements GraphicsObject {

    int[] coordinates = new int[4];
    private int startX;
    private int startY;
    private int currentX;
    private int currentY;
    private final int originalX;
    private final int originalY;
    private int FixReleasedCoordY;
    private int FixReleasedCoordX;
    private int FixStartCoordX;
    private int FixStartCoordY;

    public Line(int x1, int y1, int x2, int y2) {
        coordinates[0] = originalX = x1; //startCoordX
        coordinates[1] = originalY = y1; //startCoordY
        startX = x1;
        startY = y1;
        coordinates[2] = x2;
        coordinates[3] = y2;
    }

    @Override
    public void setFinalCoordinates(int x2, int y2) {
        coordinates[2] = x2;
        coordinates[3] = y2;
    }

    @Override
    public int[] getStartEndXY() {
        
        return coordinates;
    }
/*
     * opravitto treba lebo to nieje dobre, zatial mi to este nejde
     */
    @Override
    public void changeCoord(int x1, int x2, int y1, int y2) {
        coordinates[0] = startX = x1;
        coordinates[1] = startY = y1;

        currentX = coordinates[2] = x2;
        currentY = coordinates[3] = y2;
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
}
