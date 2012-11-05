package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
public class Menu {
	
	JFrame	frame;
	Paint2d paint;
	
	JButton pencil, line, rectangle;
	 
	public Menu(Paint2d paint){
		
		this.paint = paint;
		this.frame = paint.getFrame();
	}


	public void createMenu(){
		//Where the GUI is created:
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();

		//menu File
		menu = new JMenu("File");
		menuBar.add(menu);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(new OpenFileDialog(paint));
		menu.add(menuItem);
		menu.addSeparator();
                
                menuItem = new JMenuItem("Save");
                menuItem.addActionListener(new SaveFileDialog(paint));
                
                menu.add(menuItem);
		menu.addSeparator();
                
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.clearDrawlist();
			}
		});
		
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		menu.add(menuItem);

		//menu Edit
		menu = new JMenu("Edit");
		menuBar.add(menu);
		
		
		//UNDO button and action
		ActionListener actionUndo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.undoAction();
			}
		};
		menuItem = new JMenuItem("Undo <<");
		menu.add(menuItem);
		
		ActionListener actionRedo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paint.redoAction();
			}
		};
		
		menuItem = new JMenuItem("Redo >>");
		menu.add(menuItem);
		menuItem.addActionListener(actionRedo);
		
		//Build second menu in the menu bar.
		menu = new JMenu("Shapes");
		menuItem = new JMenuItem("Pencil :^:");
		
		ActionListener actionPencil = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Paint2d.defaultShape = "pencil";
				System.out.println("Drawing: pencil");
                                
                          
			}
		};
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Line |");
		
		ActionListener actionLine = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Paint2d.defaultShape = "line";
				line.setBackground(Color.LIGHT_GRAY);
				
				System.out.println("Drawing: line");
			}
		};
		
		menuItem.addActionListener(actionLine);
		menu.add(menuItem);
		menuItem = new JMenuItem("Rectangle []");
		
		ActionListener actionRectangle = new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Paint2d.defaultShape = "rectangle";
				System.out.println("Drawing: rectangle");
			}
		};
		
		menuItem.addActionListener(actionRectangle);
		menu.add(menuItem);
		
		//Pridanie menu do menu-baru
		menuBar.add(menu);
		//pridanie menubaru do okna
		frame.setJMenuBar(menuBar);
		
		//Toolbar s ikonami
		JToolBar toolBar = new JToolBar("ToolBar", JToolBar.HORIZONTAL);
		
		URL url = Paint2d.class.getClassLoader().getResource("undoIcon.gif");
		ImageIcon icon = new ImageIcon(url);
		JButton undo = new JButton(icon);
        undo.addActionListener(actionUndo);       
        toolBar.add(undo);
        
        url = Paint2d.class.getClassLoader().getResource("redoIcon.gif");
		icon = new ImageIcon(url);
        JButton redo = new JButton(icon);
        redo.addActionListener(actionRedo); 
        redo.setToolTipText("Reverts step back");
        toolBar.add(redo);
        
		toolBar.addSeparator();
		
		ActionListener shapeButtonsListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkedButtonColor();
			}
		};
		
		url = Paint2d.class.getClassLoader().getResource("pencilIcon.gif");
		icon = new ImageIcon(url);
        pencil = new JButton(icon);
        pencil.addActionListener(shapeButtonsListener);
        pencil.addActionListener(actionPencil);       
        toolBar.add(pencil);
		
		url = Paint2d.class.getClassLoader().getResource("lineIcon.gif");
		icon = new ImageIcon(url);
        line = new JButton(icon);
        line.addActionListener(shapeButtonsListener);
        line.addActionListener(actionLine);       
        toolBar.add(line);
        

        url = Paint2d.class.getClassLoader().getResource("rectangleIcon.gif");
		icon = new ImageIcon(url);
        rectangle = new JButton(icon);
        rectangle.addActionListener(shapeButtonsListener);
        rectangle.addActionListener(actionRectangle);       
        toolBar.add(rectangle);
        
        checkedButtonColor();
        
        frame.add(toolBar,BorderLayout.NORTH);
	}

	private void checkedButtonColor(){
		if(Paint2d.defaultShape.equals("pencil")){
			pencil.setBackground(Color.LIGHT_GRAY);
			line.setBackground(Color.WHITE);
			rectangle.setBackground(Color.WHITE);
		} else if(Paint2d.defaultShape.equals("rectangle")){
			rectangle.setBackground(Color.LIGHT_GRAY);
			line.setBackground(Color.WHITE);
			pencil.setBackground(Color.WHITE);
		} else{
			line.setBackground(Color.LIGHT_GRAY);
			pencil.setBackground(Color.WHITE);
			rectangle.setBackground(Color.WHITE);
		}
	}
	
}
