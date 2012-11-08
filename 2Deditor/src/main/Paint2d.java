package main;

import graphics.GraphicsObject;
import graphics.Pencil;
import graphics.Picture;
import graphics.Line;

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
    private boolean resizeDrawingArea = false, drawing = false;
    static MouseAdapter mouseEventsHandler;
    private boolean moving = false;
    private boolean isRectangle = false;
    private graphics.Rectangle rect;
    private int objectIndex, coordStartXbeforeEdit, coordStartYbeforeEdit, coordFinalYbeforeEdit, coordFinalXbeforeEdit;

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
        for (GraphicsObject go : drawList) {
            if (go instanceof Line) {
                int[] a = go.getStartEndXY();
                g2.drawLine(a[0], a[1], a[2], a[3]);

            }

            if (go instanceof graphics.Rectangle) {
                int[] a = go.getStartEndXY();
                g2.drawRect(a[0], a[1], a[2], a[3]);
            }

            if (go instanceof Pencil) {
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

    private class Mouse extends MouseAdapter  {

        @Override
        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            statusLabel.setText("Cursor position X: " + x + "px Y: " + y + "px");
            mouseInAreaCheck(x, y);
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
            }

            if (moving) {
           //     System.out.println(drawList.get(objectIndex).getFixReleasedCoordX());
                drawList.get(objectIndex).changeCoord(
                        (drawList.get(objectIndex).getFixStartCoordX() + (releasedCoordX - editCoordX)),
                        (drawList.get(objectIndex).getFixStartCoordY() + (releasedCoordY - editCoordY)),
                        (drawList.get(objectIndex).getFixReleasedCoordX() + (releasedCoordX - editCoordX)),
                        (drawList.get(objectIndex).getFixReleasedCoordY() + (releasedCoordY - editCoordY)));

                
             //   System.out.println("Lavy horny roh" + (drawList.get(objectIndex).getFixStartCoordX() + (e.getX() - editCoordX)));
               // System.out.println("Pravy horny roh" + (drawList.get(objectIndex).getFixReleasedCoordX() + (e.getX() - editCoordX)));
//                System.out.println("Lavy dolny roh"+ (drawList.get(objectIndex).getStartEndXY()[1] + (e.getX() - editCoordX)));

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

        public void editObject(int CoordX, int CoordY) {
            isRectangle = false;
            objectIndex = 0;


            for (GraphicsObject go : drawList) {
                if (go instanceof graphics.Rectangle) {
                    if ((CoordX >= go.getStartEndXY()[0]
                            && CoordX <= go.getStartEndXY()[2])
                            && (CoordY >= go.getStartEndXY()[1]
                            && (CoordY <= go.getStartEndXY()[3]))) {
//                        coordStartXbeforeEdit = go.getStartEndXY()[0];
//                        coordStartYbeforeEdit = go.getStartEndXY()[1];
//                        coordFinalXbeforeEdit = go.getStartEndXY()[2];
//                        coordFinalYbeforeEdit = go.getStartEndXY()[3];

                        isRectangle = true;
                        rect = (graphics.Rectangle) drawList.get(objectIndex);
                        break;
                    }
                    objectIndex++;
                }
            }


            if (isRectangle) {
                System.out.println("Je to obldznik");
            } else {
                System.out.println("Nieje to obdlznik");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
<<<<<<< HEAD
                clickedCoordX = e.getX();
                clickedCoordY = e.getY();
                startCoordX = clickedCoordX;
                startCoordY = clickedCoordY;
                if (!mouseInAreaCheck(clickedCoordX, clickedCoordY)) {

=======
            	startCoordX = e.getX();
            	startCoordY = e.getY();
            	if (!mouseInAreaCheck(clickedCoordX, clickedCoordY)) {
                    drawing = true;
>>>>>>> 52bc1389a4b4d56ec6ecca8ecdb9d360a97e7d33
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
                        case "edit":
                            drawing = false;
                            moving = true;
                            editCoordX = e.getX();
                            editCoordY = e.getY();
                            editObject(clickedCoordX, clickedCoordY);
                           // drawList.remove(objectIndex);
                            System.out.println(objectIndex);
                            break;
                            



                        default:
                            drawList.add(new Line(startCoordX, startCoordY, startCoordX, startCoordY));
                            break;
                    }
                } else {
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
                repaint();
                arrayIndex++;
                drawing = false;
            } else if (resizeDrawingArea) {
                resizeDrawingArea = false;
            }

        }
    }
}