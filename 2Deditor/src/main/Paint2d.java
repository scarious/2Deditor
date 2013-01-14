package main;

import graphics.GraphicsObject;
import graphics.Line;
import graphics.Pencil;
import graphics.Picture;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Paint2d extends JPanel {

    int i = 0;
    private Color mainColor = new Color(167, 167, 167);
    private Color shadowColor = new Color(30, 30, 30);
    private static final long serialVersionUID = -2657788641031074891L;
    private Graphics2D g2;
    private ArrayList<GraphicsObject> drawList = new ArrayList<GraphicsObject>();
    private ArrayList<GraphicsObject> redoDrawList = new ArrayList<GraphicsObject>();
    JTextArea textArea = new JTextArea();
    private static JFrame frame;
    //String[] shapes = {"line", "rectangle"};
    public static String defaultShape = "pencil";
    Directions resizeDirection;
    static JLabel statusLabel;
    static JPanel panel;
    static JComponent drawingArea;
    static MouseAdapter mouseEventsHandler;
    static KeyListener listener;
    private boolean moving = false;
    private boolean isObject = false;
    private boolean resizeDrawingArea = true, drawing = false, gridVisible = false, brightColor = true;
    private int objectIndex = -1;
    private int arrayIndex = 0, redoArrayIndex = 0;
    private int newStartCoordX, newStartCoordY, newFinalCoordX, newFinalCoordY;
    private int startCoordX, startCoordY, releasedCoordX, releasedCoordY, editCoordX, editCoordY;
    Menu menu;
    GraphicsObject gObject;
    private Color shapecolor = Color.WHITE;
    graphics.TextArea text;

    private enum Directions {

        HORIZONTAL,
        VERTICAL,
        BOTH
    }

    public Paint2d() {

        mouseEventsHandler = new Mouse();

    }

    public void setBrightColor(boolean color) {
        brightColor = color;
    }

    public boolean isBrightRGBColor() {
        return brightColor;
    }

    public void setColor(Color color) {
        shapecolor = color;
    }

    public Color getColor() {
        return shapecolor;
    }

    public void setGridVisible(boolean isVisible) {
        gridVisible = isVisible;
    }

    public boolean isGridVisible() {
        return gridVisible;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2 = (Graphics2D) g;
        setBackground(Color.white);

        if (isGridVisible()) {
            g2.setColor(Color.LIGHT_GRAY);
            showGrid();
        }
        for (GraphicsObject go : drawList) {
            if (go.visible()) {
                if (go instanceof Line) {

                    g2.setColor(go.getColor());
                    if (isBrightRGBColor()) {
                        g2.setColor(Color.BLACK);
                    }
                    int[] a = go.getStartEndXY();
                    g2.drawLine(a[0], a[1], a[2], a[3]);

                }
                if (go instanceof graphics.TextArea) {

                    int[] a = go.getStartEndXY();
                    g2.setColor(Color.BLACK);
                    text = (graphics.TextArea) go;
                    panel.add(text.getScroll());
                    text.getScroll().setBounds(a[0] + 5, a[1] + 5, a[2] - 10, a[3] - 10);

                    text.getScroll().setAutoscrolls(true);
                    text.getTextArea().setForeground(go.getColor());
                    text.getTextArea().repaint();
                    text.getScroll().repaint();

                }
                if (go instanceof graphics.Rectangle) {

                    int[] a = go.getStartEndXY();
                    if (go.isShade()) {
                        g2.setColor(mainColor);
                        g2.fillRoundRect(a[0] + 5, a[1] + 5, a[2], a[3], 10, 10);
                    }

                    g2.setColor(go.getBorderColor());
                    g2.drawRect(a[0], a[1], a[2], a[3]);
                    g2.setColor(go.getColor());
                    g2.fillRect(a[0] + 1, a[1] + 1, a[2] - 1, a[3] - 1);


                    if (go.isActive()) {
                        g2.setColor(Color.GREEN);
                        g2.drawRoundRect(a[0] - 10, a[1] - 10, a[2] + 20, a[3] + 20, 10, 10);
                    }

                }

                if (go instanceof Pencil) {
                    g2.setColor(go.getColor());

                    java.util.List<Integer> list = ((Pencil) go).getValues();
                    int a[] = new int[list.size()];
                    int j = 0;
                    for (Integer entry : ((Pencil) go).getValues()) {
                        a[j] = entry;
                        j++;
                    }
                    for (int n = 0; n < a.length - 1; n++) {
                        if (n > 2) {
                            if ((Math.abs(a[n] - a[n - 2]) > 1)
                                    || (Math.abs(a[n + 1] - a[n - 1]) > 1)) {
                                g2.drawLine(a[n - 2], a[n - 1], a[n], a[++n]);
                            } else {
                                g2.drawRect(a[n], a[++n], 0, 0);
                            }
                        } else {
                            g2.drawRect(a[n], a[++n], 0, 0);
                        }
                    }
                }
                if (go instanceof graphics.Elipse) {


                    int[] a = go.getStartEndXY();
                    g2.setColor(go.getColor());

                    g2.drawOval(a[0], a[1], a[2], a[3]);

                    if (go.isActive()) {
                        g2.setColor(Color.GREEN);
                        g2.drawOval(a[0] - 10, a[1] - 10, a[2] + 20, a[3] + 20);
                    }
                }
                if (go instanceof graphics.ElipseDiagram) {

                    int[] a = go.getStartEndXY();
                    if (go.isShade()) {
                        g2.setColor(mainColor);
                        g2.fillOval(a[0] + 5, a[1] + 3, a[2], a[3]);
                    }
                    g2.setColor(go.getColor());
                    g2.fillOval(a[0] + 1, a[1] + 1, a[2] - 2, a[3] - 2);
                    g2.setColor(go.getBorderColor());
                    g2.drawOval(a[0], a[1], a[2], a[3]);

                    if (go.isActive()) {
                        g2.setColor(Color.GREEN);
                        g2.drawOval(a[0] - 10, a[1] - 10, a[2] + 20, a[3] + 20);
                    }
                }
                if (go instanceof graphics.Diamond) {
                    g2.setColor(Color.BLACK);
                    int[] a = go.getStartEndXY();
                    int xx = (a[0] + a[2]) / 2;
                    int yy = (a[1] + a[3]) / 2;

                    Polygon p = new Polygon();
                    Polygon q=new Polygon();
                    double radius, radiusY;
                    radius = Math.abs((go.getStartEndXY()[0] - go.getStartEndXY()[2]) / 2);
                    radiusY = Math.abs((go.getStartEndXY()[1] - go.getStartEndXY()[3]) / 2);
                    int centerX = xx;
                    int centerY = yy;

                    if(go.isShade()){
                      for (int t = 0; t < 4; t++) {
                        q.addPoint((int) (centerX+5 + radius * Math.cos(t * 2 * Math.PI / 4)),
                               (int) (centerY+5 + radiusY * Math.sin(t * 2 * Math.PI / 4)));
                    }   
                    
                    g2.setColor(mainColor);
                    g2.fillPolygon(q);
                    }
                    
                    for (int t = 0; t < 4; t++) {
                        p.addPoint((int) (centerX + radius * Math.cos(t * 2 * Math.PI / 4)),
                                (int) (centerY + radiusY * Math.sin(t * 2 * Math.PI / 4)));
                    }
                    g2.setColor(go.getColor());
                    g2.fillPolygon(p);
                    

                    g2.setColor(go.getBorderColor());

                    g2.drawLine(a[0], yy, xx, a[1]);
                    g2.drawLine(xx, a[1], a[2], yy);
                    g2.drawLine(a[2], yy, xx, a[3]);
                    g2.drawLine(xx, a[3], a[0], yy);

                    if (go.isActive()) {
                        g2.setColor(Color.GREEN);
                        g2.drawLine(a[0] - 13, yy, xx, a[1] - 13);
                        g2.drawLine(xx, a[1] - 13, a[2] + 13, yy);
                        g2.drawLine(a[2] + 13, yy, xx, a[3] + 13);
                        g2.drawLine(xx, a[3] + 13, a[0] - 13, yy);
                    }

                }
                if (go instanceof Picture) {
                    g2.drawImage(((Picture) go).getImage(), 0, 0, null);//.getScaledInstance(frame.getContentPane().getWidth(), frame.getContentPane().getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
                }
            }
        }

    }

    /**
     * Zobrazenie mriezky
     */
    public void showGrid() {

        for (int a = 2; a <= drawingArea.getHeight(); a += 40) {
            this.g2.drawLine(0, a, drawingArea.getWidth(), a);
        }
        for (int j = 2; j <= drawingArea.getWidth(); j += 40) {
            this.g2.drawLine(j, 0, j, drawingArea.getHeight());
        }

    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Simple 2D Image Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 560);
        Paint2d paint = new Paint2d();
        //Create and set up the content pane.
        panel = new JPanel();
        panel.addMouseListener(mouseEventsHandler);
        panel.addMouseMotionListener(mouseEventsHandler);

        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
        drawingArea = paint;
        Border drawingAreaBorder = new LineBorder(Color.RED, 1);
        drawingArea.setBorder(drawingAreaBorder);
        panel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        drawingArea.setSize(720, 480);
        drawingArea.setOpaque(true); //content panes must be opaque
        panel.add(drawingArea);
        frame.add(panel);
        drawingArea.setRequestFocusEnabled(true);
        drawingArea.setFocusable(true);
        //Vytvorenie menu
        Menu menu = new Menu(paint);
        menu.createMenu();

        //Bottom status bar
        JPanel statusBar = new JPanel();
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(statusBar, BorderLayout.SOUTH);
        statusBar.setPreferredSize(new Dimension(frame.getWidth(), 18));
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
        statusLabel = new JLabel("Welcome!");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.add(statusLabel);
        //Display the window.      
        frame.addKeyListener(listener);
        panel.addKeyListener(listener);
        panel.setFocusable(true);
        frame.pack();
        frame.setBackground(Color.white);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setAutoRequestFocus(true);
        frame.setVisible(true);
        frame.requestFocusInWindow();

    }

    public static void main(String[] argS) {
        createAndShowGUI();
    }

    public void clearDrawlist() {
        drawList.clear();
        redoDrawList.clear();
        redoArrayIndex = 0;
        arrayIndex = 0;
        repaint();
    }

    public void addToDrawList(GraphicsObject graphicsObject) {
        drawList.add(graphicsObject);
        arrayIndex++;
        repaint();
    }

    public void undoAction() {
        if (arrayIndex > 0) { //ak je aspon nieco nakreslene
            redoDrawList.add(drawList.get(arrayIndex - 1)); //prida do zalozneho zoznamu posledny objekt v zozname
            if (drawList.get(arrayIndex - 1).getOrigin() != -1) { //ak je posledny prvok len presunuty original
                drawList.get(drawList.get(arrayIndex - 1).getOrigin()).setVisible(true); //nastavi sa povodny objekt na viditelny
                drawList.remove(arrayIndex - 1); //kopia sa odstrani 
            } else { //ak nema original
                drawList.remove(arrayIndex - 1);//vymaze sa z hlavneho zoznamu
            }

            redoArrayIndex++; //pocet objektov v zaloznom zozname sa zvysil
            arrayIndex--; //pocet objektov v hlavnom zozname sa znizil
        }

        repaint();
    }

    public void redoAction() {
        if (redoArrayIndex > 0) {
            if (redoDrawList.get(redoArrayIndex - 1).getOrigin() != -1) {
                drawList.get(redoDrawList.get(redoArrayIndex - 1).getOrigin()).setVisible(false);
                drawList.add(redoDrawList.get(redoArrayIndex - 1));
            } else {
                drawList.add(redoDrawList.get(redoArrayIndex - 1));
            }
            arrayIndex++;

            redoDrawList.remove(redoArrayIndex - 1);

            redoArrayIndex--;
            repaint();
        }
    }

    private boolean mouseInAreaCheck(int x, int y) {
        int width = drawingArea.getWidth();
        int height = drawingArea.getHeight();
        if ((x >= width && x <= width + 2) && (y >= height && y <= height + 2)) {
            resizeDirection = Directions.BOTH;
            panel.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
            return true;
        } else if (x >= width && x <= width + 2) {
            resizeDirection = Directions.HORIZONTAL;
            panel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            return true;
        } else if (y >= height && y <= height + 2) {
            resizeDirection = Directions.VERTICAL;
            panel.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
            return true;
        } else {
            panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            return false;
        }

    }

    public JFrame getFrame() {
        return frame;
    }

    static JComponent getDrawingArea() {
        return drawingArea;
    }

    public OwnFormat getOwnFormat() {
        return new OwnFormat(drawList, arrayIndex);
    }

    public void openOwnFormat(OwnFormat own) {
        clearDrawlist();
        this.drawList = own.getDrawList();
        this.arrayIndex = own.getArrayIndex();
        repaint();
    }

    private class Mouse extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();
            statusLabel.setText("Cursor position X: " + x + "px Y: " + y + "px");
            resizeDrawingArea = mouseInAreaCheck(x, y);

            editObject(x, y);
            if (isObject && defaultShape.equalsIgnoreCase("edit")) {
                panel.setCursor(new Cursor(Cursor.MOVE_CURSOR));

            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            releasedCoordX = e.getX();
            releasedCoordY = e.getY();
            if (drawing == true) {
                drawList.get(arrayIndex).setFinalCoordinates(releasedCoordX, releasedCoordY);

            } else if (resizeDrawingArea) {
                switch (resizeDirection) {
                    case HORIZONTAL:
                        drawingArea.setSize(releasedCoordX, drawingArea.getHeight());
                        break;
                    case VERTICAL:
                        drawingArea.setSize(drawingArea.getWidth(), releasedCoordY);
                        break;
                    case BOTH:
                        drawingArea.setSize(releasedCoordX, releasedCoordY);
                        break;
                }
            } else if (moving && isObject) {

                newStartCoordX = (drawList.get(objectIndex).getFixStartCoordX()) + (releasedCoordX - editCoordX);
                newStartCoordY = (drawList.get(objectIndex).getFixStartCoordY()) + (releasedCoordY - editCoordY);
                newFinalCoordX = (drawList.get(objectIndex).getFixReleasedCoordX()) + (releasedCoordX - editCoordX);
                newFinalCoordY = (drawList.get(objectIndex).getFixReleasedCoordY()) + (releasedCoordY - editCoordY);



                drawList.get(objectIndex).changeCoord(newStartCoordX, newStartCoordY, newFinalCoordX, newFinalCoordY);

            }
            statusLabel.setText("Cursor position X: " + releasedCoordX + "px Y: " + releasedCoordY + "px");
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getButton() == MouseEvent.BUTTON3) {
                editObject(e.getX(), e.getY());
                if (isObject) {
                    if (gObject.isShade()) {
                        gObject.setShade(false);
                    } else {
                        gObject.setShade(true);
                    }
                }
            } else {

                editObject(startCoordX, startCoordY);
                if (isObject) {

                    if (!gObject.isActive()) {

                        gObject.setActive(true);

                    }
                }
            }

            repaint();

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO BLA BLA BLA
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        public boolean isPointOfDiamond(GraphicsObject go, int EditX, int EditY) {


            double xx = Math.round((go.getFixStartCoordX() + go.getFixReleasedCoordX()) / 2);
            double yy = Math.round((go.getFixStartCoordY() + go.getFixReleasedCoordY()) / 2);
            double z = (((yy - go.getFixStartCoordY()) / (go.getFixStartCoordX() - xx)) * EditX)
                    + ((yy - ((yy - go.getFixStartCoordY()) / (go.getFixStartCoordX() - xx)) * go.getFixStartCoordX()));

            double z1 = (((yy - go.getFixReleasedCoordY()) / (go.getFixReleasedCoordX() - xx)) * EditX)
                    + ((yy - ((yy - go.getFixReleasedCoordY()) / (go.getFixReleasedCoordX() - xx)) * go.getFixReleasedCoordX()));

            double z2 = (((go.getFixStartCoordY() - yy) / (xx - go.getFixReleasedCoordX())) * EditX)
                    + ((go.getFixStartCoordY() - (((go.getFixStartCoordY() - yy) / (xx - go.getFixReleasedCoordX())) * xx)));

            double z3 = (((go.getFixReleasedCoordY() - yy) / (xx - go.getFixStartCoordX())) * EditX)
                    + ((go.getFixReleasedCoordY() - (((go.getFixReleasedCoordY() - yy) / (xx - go.getFixStartCoordX())) * xx)));

            if (z < EditY && z1 > EditY && z2 < EditY && z3 > EditY) {
                return true;
            } else {
                return false;
            }
        }
        /*
         * vypocita ci bod na ktory som klikol patri objektu go, teda priamke
         */

        public boolean isPointOfLine(GraphicsObject go, int EditX, int EditY) {

            /*
             * telo if-u mi vypliva z urcenia rovnice priamky. pocita to dobre akurat ked zacnem
             * kreslit od vacsich suradnic tak nieco nehraje, mozno kapacita intu to si este odsledujem
             */
            if ((((-(((go.getFixReleasedCoordY() - go.getFixStartCoordY()) * go.getFixStartCoordX())
                    - (go.getFixReleasedCoordX() - go.getFixStartCoordX()) * go.getFixStartCoordY())
                    + (((go.getFixReleasedCoordY() - go.getFixStartCoordY()) * EditX)
                    - ((go.getFixReleasedCoordX() - go.getFixStartCoordX()) * EditY))) <= 550)
                    && ((-(((go.getFixReleasedCoordY() - go.getFixStartCoordY()) * go.getFixStartCoordX())
                    - (go.getFixReleasedCoordX() - go.getFixStartCoordX()) * go.getFixStartCoordY())
                    + (((go.getFixReleasedCoordY() - go.getFixStartCoordY()) * EditX)
                    - ((go.getFixReleasedCoordX() - go.getFixStartCoordX()) * EditY))) >= -550))) {

                if (go.getFixStartCoordX() > go.getFixReleasedCoordX() && EditX < go.getFixStartCoordX() && EditX > go.getFixReleasedCoordX()) {
                    return true;
                } else if (go.getFixStartCoordX() < go.getFixReleasedCoordX() && EditX > go.getFixStartCoordX() && EditX < go.getFixReleasedCoordX()) {
                    return true;
                } else if ((go.getFixStartCoordY() > go.getFixReleasedCoordY() && EditY < go.getFixStartCoordY() && EditY > go.getFixReleasedCoordY())) {
                    return true;
                } else if ((go.getFixStartCoordY() < go.getFixReleasedCoordY() && EditY > go.getFixStartCoordY() && EditY < go.getFixReleasedCoordY())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }

        public void editObject(int CoordX, int CoordY) {
            isObject = false;
            objectIndex = drawList.size() - 1;
            for (int i = drawList.size() - 1; i >= 0; i--) {
                //for (GraphicsObject go : drawList) {
                GraphicsObject go = drawList.get(i);

                if (go instanceof graphics.Rectangle && go.visible()) {
                    if ((CoordX >= go.getStartEndXY()[0]
                            && CoordX <= (go.getStartEndXY()[0] + go.getStartEndXY()[2]))
                            && (CoordY >= go.getStartEndXY()[1]
                            && (CoordY <= (go.getStartEndXY()[1] + go.getStartEndXY()[3])))) {
                        isObject = true;

                        gObject = go;
                        //   gObject.setActive(true);

                        break;
                    }
                } else if (go instanceof graphics.Line && go.visible()) {

                    if (isPointOfLine(go, CoordX, CoordY)) {
                        //   System.out.println("Je to ciara");
                        isObject = true;
                        gObject = go;
                        break;
                    }
                } else if ((go instanceof graphics.Elipse && go.visible()) || (go instanceof graphics.ElipseDiagram && go.visible())) {

                    //if (isInElipse(CoordX, CoordY, go)) {
                    if ((CoordX >= go.getStartEndXY()[0]
                            && CoordX <= (go.getStartEndXY()[0] + go.getStartEndXY()[2]))
                            && (CoordY >= go.getStartEndXY()[1]
                            && (CoordY <= (go.getStartEndXY()[1] + go.getStartEndXY()[3])))) {
                        isObject = true;
                        gObject = go;
                        break;
                    }

                } else if (go instanceof graphics.Diamond && go.visible()) {
                    if (isPointOfDiamond(go, CoordX, CoordY)) {
                        isObject = true;
                        gObject = go;
                        break;
                    }
                }
                go.setActive(false);
                //  System.out.println("ta kdeeee");
                objectIndex--;
            }

            for (int i = objectIndex - 1; i >= 0; i--) {
                //for (GraphicsObject go : drawList) {
                GraphicsObject go = drawList.get(i);
                go.setActive(false);

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (e.getButton() == MouseEvent.BUTTON3) {
                startCoordX = editCoordX = e.getX();
                startCoordY = editCoordY = e.getY();
            } else if (e.getButton() == MouseEvent.BUTTON1) {

                startCoordX = editCoordX = e.getX();
                startCoordY = editCoordY = e.getY();

                if (mouseInAreaCheck(startCoordX, startCoordY) == false) {
                    drawing = true;
                    panel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                    //  System.out.println(defaultShape);
                    switch (defaultShape) {
                        case "line":
                            drawing = true;
                            moving = false;
                            drawList.add(new Line(startCoordX, startCoordY, startCoordX, startCoordY));
                            if (!isBrightRGBColor()) {
                                drawList.get(drawList.size() - 1).setColor(getColor());

                            } else {

                                drawList.get(drawList.size() - 1).setColor(Color.BLACK);
                            }
                            break;
                        case "rectangle":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.Rectangle(startCoordX, startCoordY, 1, 1));
                            if (isBrightRGBColor()) {
                                drawList.get(drawList.size() - 1).setColor(getColor());
                                drawList.get(drawList.size() - 1).setBorderColor(Color.BLACK);
                            } else {
                                drawList.get(drawList.size() - 1).setColor(getColor());
                                drawList.get(drawList.size() - 1).setBorderColor(Color.WHITE);
                            }
                            break;
                        case "pencil":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.Pencil(startCoordX, startCoordY, 0, 0));

                            drawList.get(drawList.size() - 1).setColor(getColor());

                            break;
                        case "circle":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.Elipse(startCoordX, startCoordY, 0, 0));
                            if (!isBrightRGBColor()) {
                                drawList.get(drawList.size() - 1).setColor(getColor());

                            } else {
                                drawList.get(drawList.size() - 1).setColor(Color.BLACK);
                            }

                            break;
                        case "Elipse":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.ElipseDiagram(startCoordX, startCoordY, 0, 0));
                            if (isBrightRGBColor()) {
                                drawList.get(drawList.size() - 1).setColor(getColor());
                                drawList.get(drawList.size() - 1).setBorderColor(Color.BLACK);
                            } else {
                                drawList.get(drawList.size() - 1).setColor(getColor());
                                drawList.get(drawList.size() - 1).setBorderColor(Color.WHITE);
                            }


                            break;
                        case "Diamond":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.Diamond(startCoordX, startCoordY, startCoordX, startCoordY));
                            drawList.get(drawList.size() - 1).setColor(getColor());
                            if (isBrightRGBColor()) {
                                drawList.get(drawList.size() - 1).setColor(getColor());
                                drawList.get(drawList.size() - 1).setBorderColor(Color.BLACK);
                            } else {
                                drawList.get(drawList.size() - 1).setColor(getColor());
                                drawList.get(drawList.size() - 1).setBorderColor(Color.WHITE);
                            }
                            break;
                        case "TextArea":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.TextArea(startCoordX, startCoordY, 0, 0));
                            if (!isBrightRGBColor()) {
                                drawList.get(drawList.size() - 1).setColor(getColor());
                            } else {
                                drawList.get(drawList.size() - 1).setColor(Color.BLACK);
                            }
                            break;
                        case "edit":
                            drawing = false;
                            moving = true;

                            editObject(startCoordX, startCoordY);
                            if (objectIndex > -1 && isObject) { //ak je vybraty nejaky objekt ide dalej
                                drawList.get(objectIndex).setActive(true);
                                panel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                                switch (drawList.get(objectIndex).getClass().getCanonicalName().substring(9)) {

                                    case "Rectangle":

                                        int[] positionOfCopiedRectangle = drawList.get(objectIndex).getStartEndXY();
                                        drawList.get(objectIndex).setVisible(false);
                                        drawList.add(new graphics.Rectangle(positionOfCopiedRectangle[0], positionOfCopiedRectangle[1], positionOfCopiedRectangle[2], positionOfCopiedRectangle[3]));
                                        drawList.get(arrayIndex).setFinalCoordinates(positionOfCopiedRectangle[0], positionOfCopiedRectangle[1]);
                                        drawList.get(arrayIndex).setOrigin(objectIndex);
                                        drawList.get(arrayIndex).setActive(drawList.get(objectIndex).isActive());
                                        drawList.get(arrayIndex).setShade(drawList.get(objectIndex).isShade());
                                        drawList.get(arrayIndex).setColor(drawList.get(objectIndex).getColor());
                                        drawList.get(arrayIndex).setBorderColor(drawList.get(objectIndex).getBorderColor());
                                        objectIndex = arrayIndex;
                                        arrayIndex++;

                                        break;
                                    //TODO: treba este doplnit pre fungovanie s ostatnymi objektmi

                                    case "Elipse":
                                        int[] positionOfCopiedElipse = drawList.get(objectIndex).getStartEndXY();
                                        drawList.get(objectIndex).setVisible(false);
                                        drawList.add(new graphics.Elipse(positionOfCopiedElipse[0], positionOfCopiedElipse[1], positionOfCopiedElipse[2], positionOfCopiedElipse[3]));
                                        drawList.get(arrayIndex).setFinalCoordinates(positionOfCopiedElipse[0], positionOfCopiedElipse[1]);
                                        drawList.get(arrayIndex).setOrigin(objectIndex);

                                        drawList.get(arrayIndex).setActive(drawList.get(objectIndex).isActive());
                                        drawList.get(arrayIndex).setShade(drawList.get(objectIndex).isShade());
                                        drawList.get(arrayIndex).setColor(drawList.get(objectIndex).getColor());
                                        drawList.get(arrayIndex).setBorderColor(drawList.get(objectIndex).getBorderColor());
                                        objectIndex = arrayIndex;
                                        arrayIndex++;

                                        break;
                                    case "ElipseDiagram":
                                        int[] positionOfCopiedElipseD = drawList.get(objectIndex).getStartEndXY();
                                        drawList.get(objectIndex).setVisible(false);
                                        drawList.add(new graphics.ElipseDiagram(positionOfCopiedElipseD[0], positionOfCopiedElipseD[1], positionOfCopiedElipseD[2], positionOfCopiedElipseD[3]));
                                        drawList.get(arrayIndex).setFinalCoordinates(positionOfCopiedElipseD[0], positionOfCopiedElipseD[1]);
                                        drawList.get(arrayIndex).setOrigin(objectIndex);
                                        drawList.get(arrayIndex).setActive(drawList.get(objectIndex).isActive());
                                        drawList.get(arrayIndex).setShade(drawList.get(objectIndex).isShade());
                                        drawList.get(arrayIndex).setColor(drawList.get(objectIndex).getColor());
                                        drawList.get(arrayIndex).setBorderColor(drawList.get(objectIndex).getBorderColor());
                                        objectIndex = arrayIndex;
                                        arrayIndex++;

                                        break;
                                    case "Line":
                                        int[] positionOfCopiedLine = drawList.get(objectIndex).getStartEndXY();
                                        drawList.get(objectIndex).setVisible(false);
                                        drawList.add(new graphics.Line(positionOfCopiedLine[0], positionOfCopiedLine[1], positionOfCopiedLine[2], positionOfCopiedLine[3]));
                                        drawList.get(arrayIndex).setOrigin(objectIndex);
                                        drawList.get(arrayIndex).setActive(drawList.get(objectIndex).isActive());
                                        drawList.get(arrayIndex).setShade(drawList.get(objectIndex).isShade());
                                        drawList.get(arrayIndex).setColor(drawList.get(objectIndex).getColor());
                                        drawList.get(arrayIndex).setBorderColor(drawList.get(objectIndex).getBorderColor());
                                        objectIndex = arrayIndex;
                                        arrayIndex++;
                                        break;
                                    case "Pencil":
                                        break;
                                    case "Picture":
                                        break;
                                    case "Diamond":
                                        int[] positionOfCopiedDiamond = drawList.get(objectIndex).getStartEndXY();
                                        drawList.get(objectIndex).setVisible(false);
                                        drawList.add(new graphics.Diamond(positionOfCopiedDiamond[0], positionOfCopiedDiamond[1], positionOfCopiedDiamond[2], positionOfCopiedDiamond[3]));
                                        drawList.get(arrayIndex).setOrigin(objectIndex);
                                        drawList.get(arrayIndex).setActive(drawList.get(objectIndex).isActive());
                                        drawList.get(arrayIndex).setShade(drawList.get(objectIndex).isShade());
                                        drawList.get(arrayIndex).setColor(drawList.get(objectIndex).getColor());
                                        drawList.get(arrayIndex).setBorderColor(drawList.get(objectIndex).getBorderColor());
                                        objectIndex = arrayIndex;
                                        arrayIndex++;
                                        break;
                                }
                            }

                            break;

                        default:
                            drawList.add(new Line(startCoordX, startCoordY, startCoordX, startCoordY));
                            break;
                    }
                } else {
                    drawing = false;
                    resizeDrawingArea = true;
                }
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (drawing) {
                    releasedCoordX = e.getX();
                    releasedCoordY = e.getY();

                    if ((Math.abs(releasedCoordX - startCoordX) > 2 || Math.abs(releasedCoordY - startCoordY) > 2) && (!(drawList.get(drawList.size() - 1) instanceof graphics.Rectangle)
                            || !(drawList.get(drawList.size() - 1) instanceof graphics.Elipse)
                            || !(drawList.get(drawList.size() - 1) instanceof graphics.ElipseDiagram)
                            || !(drawList.get(drawList.size() - 1) instanceof graphics.Diamond))) {
                        redoDrawList.clear();
                        redoArrayIndex = 0;


                        drawList.get(arrayIndex).setFinalCoordinates(releasedCoordX, releasedCoordY);
                        drawList.get(arrayIndex).setFixReleasedCoordX(releasedCoordX);

                        drawList.get(arrayIndex).setFixReleasedCoordY(releasedCoordY);
                        drawList.get(arrayIndex).setFixStartCoordX(startCoordX);
                        drawList.get(arrayIndex).setFixStartCoordY(startCoordY);

                        arrayIndex++;
                        drawing = false;
                    } else {
                        drawList.get(drawList.size() - 1).setVisible(false);
                        drawList.remove(drawList.size() - 1);
                        repaint();
                    }
                } else if (resizeDrawingArea) {
                    resizeDrawingArea = false;
                }

                if (moving && isObject) {
                    redoDrawList.clear();
                    redoArrayIndex = 0;
                    drawList.get(objectIndex).setFixStartCoordX(newStartCoordX);
                    drawList.get(objectIndex).setFixStartCoordY(newStartCoordY);
                    drawList.get(objectIndex).setFixReleasedCoordX(newFinalCoordX);
                    drawList.get(objectIndex).setFixReleasedCoordY(newFinalCoordY);
                    isObject = false;
                    moving = false;

                    objectIndex = -1;

                }
                //panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
}
