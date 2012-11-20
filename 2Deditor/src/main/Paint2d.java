package main;

import graphics.GraphicsObject;
import graphics.Pencil;
import graphics.Picture;
import graphics.Line;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
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

    private Color mainColor = new Color(167, 167, 167);
    private Color shadowColor = new Color(30, 30, 30);
    private static final long serialVersionUID = -2657788641031074891L;
    private Graphics2D g2;
    private int startCoordX, startCoordY, clickedCoordX, clickedCoordY, releasedCoordX, releasedCoordY, editCoordX, editCoordY;
    private ArrayList<GraphicsObject> drawList = new ArrayList<GraphicsObject>();
    private ArrayList<GraphicsObject> redoDrawList = new ArrayList<GraphicsObject>();
    JTextArea textArea;
    private static JFrame frame;
    //String[] shapes = {"line", "rectangle"};
    public static String defaultShape = "pencil";
    int arrayIndex = 0, redoArrayIndex = 0;
    Directions resizeDirection;
    static JLabel statusLabel;
    static JPanel panel;
    static JComponent drawingArea;
    private boolean resizeDrawingArea = true, drawing = false;
    static MouseAdapter mouseEventsHandler;
    private boolean moving = false;
    private boolean isObject = false;
    private int objectIndex;
    private int newStartCoordX, newStartCoordY, newFinalCoordX, newFinalCoordY;

    private enum Directions {

        HORIZONTAL,
        VERTICAL,
        BOTH
    }

    public Paint2d() {
        mouseEventsHandler = new Mouse();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        // g2.getPaint().
        setBackground(Color.white);
        g2.setPaint(Color.black);
//        for (int i=2; i<=drawingArea.getHeight(); i+=5){
//            g2.drawLine(0,i, drawingArea.getWidth(), i);
//        }
//        for (int i=2; i<=drawingArea.getWidth(); i+=5){
//            g2.drawLine(i,0, i, drawingArea.getHeight());
//        }
//        
        for (GraphicsObject go : drawList) {
            if (go instanceof Line) {
                g2.setColor(Color.BLACK);
                int[] a = go.getStartEndXY();
               g2.translate(10, -2);
               g2.scale(10, 10);
                g2.rotate(Math.toRadians(20), 100 / 2, 100 / 2);
             g2.translate(10, 10);
                g2.drawLine(a[0], a[1], a[2], a[3]);
                

            }
            if (go instanceof graphics.Rectangle) {



                int[] a = go.getStartEndXY();
                g2.setColor(mainColor);

                g2.fillRoundRect(a[0] + 5, a[1] + 5, a[2], a[3], 8, 8);
                //g2.setColor(Color.BLACK);      
                //g2.setPaintMode();
                //g2.drawRect(a[0], a[1], a[2], a[3]);
                g2.setColor(shadowColor);
                g2.fillRect(a[0], a[1], a[2], a[3]);


                // g2.fillRect(a[0] - 5, a[1] - 5, a[2], a[3]);


            }
            if (go instanceof Pencil) {
                g2.setColor(Color.BLACK);

                java.util.List<Integer> list = ((Pencil) go).getValues();
                int a[] = new int[list.size()];
                int j = 0;
                for (Integer entry : ((Pencil) go).getValues()) {
                    a[j] = entry;
                    j++;
                }
                for (int i = 0; i < a.length - 1; i++) {
                    if (i > 2) {
                        if ((Math.abs(a[i] - a[i - 2]) > 1)
                                || (Math.abs(a[i + 1] - a[i - 1]) > 1)) {
                            g2.drawLine(a[i - 2], a[i - 1], a[i], a[++i]);
                        } else {
                            g2.drawRect(a[i], a[++i], 0, 0);
                        }
                    } else {
                        g2.drawRect(a[i], a[++i], 0, 0);
                    }
                }
            }
            if (go instanceof graphics.Elipse) {
                g2.setColor(Color.BLACK);
                int[] a = go.getStartEndXY();
                g2.drawOval(a[0], a[1], a[2], a[3]);
            }
            if (go instanceof graphics.ElipseDiagram) {
                g2.setColor(Color.BLACK);
                int[] a = go.getStartEndXY();
                g2.fillOval(a[0] + 2, a[1] + 2, a[2] + 2, a[3] + 2);
                g2.drawOval(a[0], a[1], a[2], a[3]);
                g2.setColor(Color.WHITE);
                g2.fillOval(a[0] + 1, a[1] + 1, a[2] - 2, a[3] - 2);


            }
            if (go instanceof Picture) {
                g2.drawImage(((Picture) go).getImage(), 0, 0, null);//.getScaledInstance(frame.getContentPane().getWidth(), frame.getContentPane().getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
            }
        }
       
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Simple 2D Image Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 480);
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

        drawingArea.setSize(480, 320);
        drawingArea.setOpaque(true); //content panes must be opaque
        panel.add(drawingArea);
        frame.add(panel);

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
        frame.pack();
        frame.setBackground(Color.white);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
        if (arrayIndex > 0) {
            //pred krokom naspat prida odstranovany objekt do druheho zoznamu
            redoDrawList.add(drawList.get(arrayIndex - 1));
            redoArrayIndex++;

            drawList.remove(arrayIndex - 1);
            arrayIndex--;
            repaint();
        }
    }

    public void redoAction() {
        if (redoArrayIndex > 0) {
            drawList.add(redoDrawList.get(redoArrayIndex - 1));
            arrayIndex++;

            redoDrawList.remove(redoArrayIndex - 1);
            redoArrayIndex--;
            repaint();
        }
    }

    private boolean mouseInAreaCheck(int x, int y) {
        int width = drawingArea.getWidth();
        int height = drawingArea.getHeight();
        if ((x >= width && x <= width + 1) && (y >= height && y <= height + 1)) {
            resizeDirection = Directions.BOTH;
            panel.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
            return true;
        } else if (x >= width && x <= width + 1) {
            resizeDirection = Directions.HORIZONTAL;
            panel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            return true;
        } else if (y >= height && y <= height + 1) {
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

    private class Mouse extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();
            statusLabel.setText("Cursor position X: " + x + "px Y: " + y + "px");
            resizeDrawingArea = mouseInAreaCheck(x, y);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            releasedCoordX = e.getX();
            releasedCoordY = e.getY();
            int change = 0;
            if (drawing == true) {
                drawList.get(arrayIndex).setFinalCoordinates(releasedCoordX, releasedCoordY);


            }  else if (resizeDrawingArea) {
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
            }

else
            if (moving && isObject) {
                /*
                 * pre zjednodusenie ulozim si do "lokalnych" premennych nove suradnice po pohnuti s objektom
                 * neviem preco newStart/finalCoordX/Y su privatne moze sa to neskor zmenit
                 */
                if (drawList.get(objectIndex).getFixStartCoordX() > drawList.get(objectIndex).getFixReleasedCoordX()) {
                    change = drawList.get(objectIndex).getFixReleasedCoordX();
                    drawList.get(objectIndex).setFixReleasedCoordX(drawList.get(objectIndex).getFixStartCoordX());
                    drawList.get(objectIndex).setFixStartCoordX(change);
                }
                if (drawList.get(objectIndex).getFixStartCoordY() > drawList.get(objectIndex).getFixReleasedCoordY()) {
                    change = drawList.get(objectIndex).getFixReleasedCoordY();
                    drawList.get(objectIndex).setFixReleasedCoordY(drawList.get(objectIndex).getFixStartCoordY());
                    drawList.get(objectIndex).setFixStartCoordY(change);
                }

                newStartCoordX = (drawList.get(objectIndex).getFixStartCoordX()) + (releasedCoordX - editCoordX);
                newStartCoordY = (drawList.get(objectIndex).getFixStartCoordY()) + (releasedCoordY - editCoordY);
                newFinalCoordX = (drawList.get(objectIndex).getFixReleasedCoordX()) + (releasedCoordX - editCoordX);
                newFinalCoordY = (drawList.get(objectIndex).getFixReleasedCoordY()) + (releasedCoordY - editCoordY);



                //System.out.println("mousedrag  " + newStartCoordX + " " + newStartCoordY + " " + newFinalCoordX + " " + newFinalCoordY);
                drawList.get(objectIndex).changeCoord(newStartCoordX, newStartCoordY, newFinalCoordX, newFinalCoordY);

            }


            statusLabel.setText("Cursor position X: " + releasedCoordX + "px Y: " + releasedCoordY + "px");
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO BLA BLA BLA
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
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
                    - ((go.getFixReleasedCoordX() - go.getFixStartCoordX()) * EditY))) <= 400)
                    && ((-(((go.getFixReleasedCoordY() - go.getFixStartCoordY()) * go.getFixStartCoordX())
                    - (go.getFixReleasedCoordX() - go.getFixStartCoordX()) * go.getFixStartCoordY())
                    + (((go.getFixReleasedCoordY() - go.getFixStartCoordY()) * EditX)
                    - ((go.getFixReleasedCoordX() - go.getFixStartCoordX()) * EditY))) >= -400))) {

                return true;
            } else {
                return false;
            }

        }

        public boolean isInElipse(int editCoordX, int editCoordY, GraphicsObject go) {
            if ((((editCoordX * editCoordX) / ((go.getStartEndXY()[2] / 2) * (go.getStartEndXY()[2] / 2))
                    + (editCoordY * editCoordY) / ((go.getStartEndXY()[3] / 2) * (go.getStartEndXY()[3]) / 2))) <= 1) {

                return true;
            } else {
                return false;
            }
        }

        public void editObject(int CoordX, int CoordY) {
            isObject = false;

            objectIndex = 0;

            for (GraphicsObject go : drawList) {
                if (go instanceof graphics.Rectangle) {
                    if ((CoordX >= go.getStartEndXY()[0]
                            && CoordX <= (go.getStartEndXY()[0] + go.getStartEndXY()[2]))
                            && (CoordY >= go.getStartEndXY()[1]
                            && (CoordY <= (go.getStartEndXY()[1] + go.getStartEndXY()[3])))) {

                        isObject = true;
                        System.out.println("Je to obdlznik");


                        break;
                    }
                } else if (go instanceof graphics.Line) {

                    if (isPointOfLine(go, CoordX, CoordY)) {


                        System.out.println("Je to ciara");
                        isObject = true;
                        break;
                    }
                } else if (go instanceof graphics.Elipse || go instanceof graphics.ElipseDiagram) {


                    if ((CoordX >= go.getStartEndXY()[0]
                            && CoordX <= (go.getStartEndXY()[0] + go.getStartEndXY()[2]))
                            && (CoordY >= go.getStartEndXY()[1]
                            && (CoordY <= (go.getStartEndXY()[1] + go.getStartEndXY()[3])))) {

                        isObject = true;
                        break;
                    }

                }
                objectIndex++;
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {


            if (e.getButton() == MouseEvent.BUTTON1) {

                startCoordX = editCoordX = e.getX();
                startCoordY = editCoordY = e.getY();

                if (!mouseInAreaCheck(startCoordX, startCoordY)) {
                    drawing = true;

                    System.out.println(defaultShape);
                    switch (defaultShape) {
                        case "line":
                            drawing = true;
                            moving = false;
                            drawList.add(new Line(startCoordX, startCoordY, startCoordX, startCoordY));

                            break;
                        case "rectangle":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.Rectangle(startCoordX, startCoordY, startCoordX, startCoordY));
                            System.out.println("Kreslim obdlznik");
                            break;
                        case "pencil":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.Pencil(startCoordX, startCoordY, 0, 0));
                            System.out.println("Kreslim ceruzkou");
                            break;
                        case "circle":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.Elipse(startCoordX, startCoordY, 0, 0));
                            System.out.println("Kreslim elipsu");
                            break;
                        case "elipse":
                            drawing = true;
                            moving = false;
                            drawList.add(new graphics.ElipseDiagram(startCoordX, startCoordY, 0, 0));
                            System.out.println("Kreslim elipsu Diagramu");
                            break;

                        case "edit":

                            drawing = false;
                            moving = true;

                            editObject(startCoordX, startCoordY);
                            // drawList.remove(objectIndex);
                            //   System.out.println(objectIndex);
                            break;




                        default:
                            drawList.add(new Line(startCoordX, startCoordY, startCoordX, startCoordY));
                            break;
                    }
                } else {
                    drawing=false;
                    resizeDrawingArea = true;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (drawing) {
                releasedCoordX = e.getX();
                releasedCoordY = e.getY();

                drawList.get(arrayIndex).setFinalCoordinates(releasedCoordX, releasedCoordY);
                drawList.get(arrayIndex).setFixReleasedCoordX(releasedCoordX);

                drawList.get(arrayIndex).setFixReleasedCoordY(releasedCoordY);
                drawList.get(arrayIndex).setFixStartCoordX(startCoordX);
                drawList.get(arrayIndex).setFixStartCoordY(startCoordY);
                arrayIndex++;
                drawing = false;
            } else if (resizeDrawingArea) {
                resizeDrawingArea = false;
            }

            if (moving && isObject) {



                drawList.get(objectIndex).setFixStartCoordX(newStartCoordX);
                drawList.get(objectIndex).setFixStartCoordY(newStartCoordY);
                drawList.get(objectIndex).setFixReleasedCoordX(newFinalCoordX);
                drawList.get(objectIndex).setFixReleasedCoordY(newFinalCoordY);

            }

        }
    }
}