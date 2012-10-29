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

public class Paint2d extends JPanel{

    
	private static final long serialVersionUID = -2657788641031074891L;
	private Graphics2D g2;
    private int startCoordX, startCoordY, clickedCoordX, clickedCoordY, releasedCoordX, releasedCoordY;
    private ArrayList<GraphicsObject> drawList = new ArrayList<GraphicsObject>();
    private ArrayList<GraphicsObject> redoDrawList = new ArrayList<GraphicsObject>();
    JTextArea textArea;
    private static JFrame frame;
    //String[] shapes = {"line", "rectangle"};
    public static String defaultShape = "pencil";
    int arrayIndex = 0, redoArrayIndex = 0;
    static JLabel statusLabel;
    
    public Paint2d() {        
        addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				statusLabel.setText("Cursor position X: " + e.getX() + "px Y: " + e.getY() + "px");
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				releasedCoordX = e.getX();
                releasedCoordY = e.getY();
                System.out.println("Motion X: " + releasedCoordX + " Motion Y: "
                		+ releasedCoordY);
                drawList.get(arrayIndex).setFinalCoordinates(releasedCoordX, releasedCoordY);
                repaint();
				
			}
		});

        addMouseListener(new Mouse());
        setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        //setSize(new Dimension(720, 480));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;

        setBackground(Color.white);
        g2.setPaint(Color.black);

        for (GraphicsObject go : drawList) {
            if(go instanceof Line){
            	int[] a = go.getStartEndXY();
            	g2.drawLine(a[0], a[1], a[2], a[3]);
            	
            }
            
            if (go instanceof graphics.Rectangle){
            	int[] a = go.getStartEndXY();
            	g2.drawRect(a[4], a[5], a[2]-a[4], a[3]-a[5]);
            }
            
            if (go instanceof Pencil){
            	java.util.List<Integer> list = ((Pencil) go).getValues();
            	int a[] = new int[list.size()];
            	int j = 0;
            	for(Integer entry: ((Pencil) go).getValues()){
            		a[j] = entry;
            		j++;
            	}
            	for(int i = 0; i < a.length-1; i++){
            		if(i>2){
                			if( (Math.abs(a[i]-a[i-2])>1) ||
                					(Math.abs(a[i+1]-a[i-1])>1)){
                				g2.drawLine(a[i-2], a[i-1], a[i], a[++i]);
                			} else {
                				g2.drawRect(a[i], a[++i], 0, 0);
    						}
                		} else {
                			g2.drawRect(a[i], a[++i], 0, 0);
                		}
            	}
            }
            
            if (go instanceof Picture){
            	g2.drawImage(((Picture) go).getImage().getScaledInstance(frame.getContentPane().getWidth(), frame.getContentPane().getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
            }
        }
    }

   
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Simple 2D Image Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 480);
        Paint2d paint = new Paint2d();
        //Vytvorenie menu
        Menu menu = new Menu(frame, paint);
        menu.createMenu();
        //Create and set up the content pane.
        JComponent newContentPane = paint;
        newContentPane.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(newContentPane);
        frame.add(newContentPane);
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
    
    public void clearDrawlist(){
    	drawList.clear();
    	
    	redoDrawList.clear();
    	redoArrayIndex = 0;
    	arrayIndex = 0;
    	repaint();
    }
    
    public void addToDrawList(GraphicsObject graphicsObject){
    	drawList.add(graphicsObject);
    	arrayIndex++;
    	repaint();
    }
    
    public void undoAction(){
    	if(arrayIndex > 0){
    		//pred krokom naspat prida odstranovany objekt do druheho zoznamu
    		redoDrawList.add(drawList.get(arrayIndex-1));
    		redoArrayIndex++;
    		
    		drawList.remove(arrayIndex-1);
    		arrayIndex--;
        	repaint();
    	}
    }
    
    public void redoAction(){
    	if(redoArrayIndex > 0){
    		drawList.add(redoDrawList.get(redoArrayIndex-1));
    		arrayIndex++;
    		
    		redoDrawList.remove(redoArrayIndex-1);
    		redoArrayIndex--;
    		repaint();
    	}
    }
    
    private class Mouse implements MouseListener{

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

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
	            startCoordX = clickedCoordX = e.getX();
	            startCoordY = clickedCoordY = e.getY();
	            System.out.println(defaultShape);
	            switch (defaultShape) {
				case "line":
					drawList.add(new Line(startCoordX, startCoordY, startCoordX, startCoordY));
					break;
				case "rectangle":
					drawList.add(new graphics.Rectangle(startCoordX, startCoordY, startCoordX, startCoordY));
					System.out.println("Kreslim obdlznik");
					break;
				case "pencil":
					drawList.add(new graphics.Pencil(startCoordX, startCoordY, 0, 0));
					System.out.println("Kreslim ceruzkou");
					break;
				default:
					drawList.add(new Line(startCoordX, startCoordY, startCoordX, startCoordY));
					break;
				}   
	        }
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
	            releasedCoordX = e.getX();
	            releasedCoordY = e.getY();
	            drawList.get(arrayIndex).setFinalCoordinates(releasedCoordX, releasedCoordY);
	            repaint();
	            arrayIndex++;
	        }
		}
    	
    }
}