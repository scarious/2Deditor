package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class Menu {

    JFrame frame;
    private Paint2d paint;
    JButton pencil, line, rectangle, edit, circle, elipse, diamond, red, yel, textArea;
    JCheckBox grid;

    public Menu(Paint2d paint) {

        this.paint = paint;
        this.frame = paint.getFrame();

    }

    public JCheckBox checkbox() {
        return grid;
    }

    public void createMenu() {
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

        menuItem = new JMenuItem("Save as...");
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
        menuItem.addActionListener(actionUndo);
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

            }
        };

        menuItem.addActionListener(actionLine);
        menu.add(menuItem);

        menuItem = new JMenuItem("Rectangle []");

        ActionListener actionRectangle = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paint2d.defaultShape = "rectangle";
                rectangle.setBackground(Color.LIGHT_GRAY);

            }
        };

        menuItem.addActionListener(actionRectangle);
        menu.add(menuItem);

        menuItem = new JMenuItem("Circle");

        ActionListener actionCircle = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paint2d.defaultShape = "circle";
                circle.setBackground(Color.LIGHT_GRAY);

            }
        };
        menuItem.addActionListener(actionCircle);
        menu.add(menuItem);

        menuItem = new JMenuItem("Elipse");

        ActionListener actionElipse = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paint2d.defaultShape = "Elipse";
                elipse.setBackground(Color.LIGHT_GRAY);

            }
        };
        menuItem.addActionListener(actionElipse);
        menu.add(menuItem);

        menuItem = new JMenuItem("Diamond <>");

        ActionListener actionDiamond = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paint2d.defaultShape = "Diamond";
                diamond.setBackground(Color.LIGHT_GRAY);

            }
        };
        menuItem.addActionListener(actionDiamond);
        menu.add(menuItem);


        
         menuItem = new JMenuItem("TextArea");

        ActionListener actionTextArea = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paint2d.defaultShape = "TextArea";
                textArea.setBackground(Color.LIGHT_GRAY);

            }
        };
        menuItem.addActionListener(actionTextArea);
        menu.add(menuItem);

        
        

        menuItem = new JMenuItem("Edit");

        ActionListener actionEdit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paint2d.defaultShape = "edit";
                edit.setBackground(Color.LIGHT_GRAY);

            }
        };


        menuItem.addActionListener(actionEdit);
        menu.add(menuItem);


        //Pridanie menu do menu-baru
        menuBar.add(menu);
        //pridanie menubaru do okna
        frame.setJMenuBar(menuBar);
//********************************************/
        //Toolbar s ikonami
        JToolBar toolBar = new JToolBar("ToolBar", JToolBar.HORIZONTAL);


        //  JToolBar toolBarV = new JToolBar("ToolBar", JToolBar.VERTICAL);
//        toolBarV.setAlignmentX(JToolBar.VERTICAL);


        URL url = Paint2d.class.getClassLoader().getResource("undoIcon.gif");
        ImageIcon icon = new ImageIcon(url);
        JButton undo = new JButton(icon);
        undo.addActionListener(actionUndo);
        undo.setToolTipText("Undo last step");
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

        url = Paint2d.class.getClassLoader().getResource("circleIcon.gif");
        icon = new ImageIcon(url);
        circle = new JButton(icon);
        circle.addActionListener(shapeButtonsListener);
        circle.addActionListener(actionCircle);
        toolBar.add(circle);


        url = Paint2d.class.getClassLoader().getResource("diagramElipse.gif");
        icon = new ImageIcon(url);
        elipse = new JButton(icon);
        elipse.addActionListener(shapeButtonsListener);
        elipse.addActionListener(actionElipse);
        toolBar.add(elipse);

        url = Paint2d.class.getClassLoader().getResource("Diamond.gif");
        icon = new ImageIcon(url);
        diamond = new JButton(icon);
        diamond.addActionListener(shapeButtonsListener);
        diamond.addActionListener(actionDiamond);
        toolBar.add(diamond);


        url = Paint2d.class.getClassLoader().getResource("targetIcon.gif");
        icon = new ImageIcon(url);
        edit = new JButton(icon);
        edit.addActionListener(shapeButtonsListener);
        edit.addActionListener(actionEdit);
        toolBar.add(edit);
   
        url = Paint2d.class.getClassLoader().getResource("textareaIcon.gif");
        icon = new ImageIcon(url);
        textArea = new JButton(icon);
        textArea.addActionListener(shapeButtonsListener);
        textArea.addActionListener(actionTextArea);
        toolBar.add(textArea);
        
        edit.setToolTipText("Moving object");
        diamond.setToolTipText("Drawing If-Else Block");
        elipse.setToolTipText("Drawing Elipse");
        rectangle.setToolTipText("Drawing rectangle");
        circle.setToolTipText("Drawing Circle");
        textArea.setToolTipText("TextArea");
        
        url = Paint2d.class.getClassLoader().getResource("2.gif");
        icon = new ImageIcon(url);
        red = new JButton(icon);
        red.setSize(new Dimension(30, 35));
        red.setBorder(null);
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paint.setColor(Color.RED);
            }
        });
        toolBar.add(red);

        url = Paint2d.class.getClassLoader().getResource("1.gif");
        icon = new ImageIcon(url);
        yel = new JButton(icon);
        yel.setSize(new Dimension(30, 35));
        yel.setBorder(null);
        yel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paint.setColor(Color.YELLOW);
            }
        });
        toolBar.add(yel);

//        for (int i=0; i<=100; i=+10){
//            toolBar.add(new )
//        }



        grid = new JCheckBox("Grid", false);
        toolBar.add(grid);
        grid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (checkbox().isSelected()) {
                    paint.setGridVisible(true);

                } else {
                    paint.setGridVisible(false);

                }
                paint.repaint();
            }
        });
       


        checkedButtonColor();

        frame.add(toolBar, BorderLayout.NORTH);
    }

    private void checkedButtonColor() {
        switch (Paint2d.defaultShape) {
            case "pencil":
                pencil.setBackground(Color.LIGHT_GRAY);
                line.setBackground(Color.WHITE);
                rectangle.setBackground(Color.WHITE);
                circle.setBackground(Color.WHITE);
                edit.setBackground(Color.WHITE);
                elipse.setBackground(Color.WHITE);
                diamond.setBackground(Color.WHITE);
                textArea.setBackground(Color.WHITE);
                        
                break;
            case "rectangle":
                rectangle.setBackground(Color.LIGHT_GRAY);
                line.setBackground(Color.WHITE);
                pencil.setBackground(Color.WHITE);
                circle.setBackground(Color.WHITE);
                edit.setBackground(Color.WHITE);
                elipse.setBackground(Color.WHITE);
                diamond.setBackground(Color.WHITE);
                textArea.setBackground(Color.WHITE);
                break;
            case "circle":
                circle.setBackground(Color.LIGHT_GRAY);
                line.setBackground(Color.WHITE);
                pencil.setBackground(Color.WHITE);
                rectangle.setBackground(Color.WHITE);
                edit.setBackground(Color.WHITE);
                elipse.setBackground(Color.WHITE);
                diamond.setBackground(Color.WHITE);
                textArea.setBackground(Color.WHITE);
                break;
            case "Elipse":
                elipse.setBackground(Color.LIGHT_GRAY);
                line.setBackground(Color.WHITE);
                pencil.setBackground(Color.WHITE);
                rectangle.setBackground(Color.WHITE);
                edit.setBackground(Color.WHITE);
                circle.setBackground(Color.WHITE);
                diamond.setBackground(Color.WHITE);
                textArea.setBackground(Color.WHITE);
                break;
            case "edit":
                edit.setBackground(Color.LIGHT_GRAY);
                line.setBackground(Color.WHITE);
                pencil.setBackground(Color.WHITE);
                rectangle.setBackground(Color.WHITE);
                circle.setBackground(Color.WHITE);
                elipse.setBackground(Color.WHITE);
                diamond.setBackground(Color.WHITE);
                textArea.setBackground(Color.WHITE);
                break;
            case "Diamond":
                diamond.setBackground(Color.LIGHT_GRAY);
                edit.setBackground(Color.WHITE);
                line.setBackground(Color.WHITE);
                pencil.setBackground(Color.WHITE);
                rectangle.setBackground(Color.WHITE);
                circle.setBackground(Color.WHITE);
                elipse.setBackground(Color.WHITE);
                textArea.setBackground(Color.WHITE);
            case "TextArea":
               diamond.setBackground(Color.WHITE);
                edit.setBackground(Color.WHITE);
                line.setBackground(Color.WHITE);
                pencil.setBackground(Color.WHITE);
                rectangle.setBackground(Color.WHITE);
                circle.setBackground(Color.WHITE);
                elipse.setBackground(Color.WHITE);
                textArea.setBackground(Color.LIGHT_GRAY);
                break;

            default:
                line.setBackground(Color.LIGHT_GRAY);
                pencil.setBackground(Color.WHITE);
                rectangle.setBackground(Color.WHITE);
                edit.setBackground(Color.WHITE);
                elipse.setBackground(Color.WHITE);
                circle.setBackground(Color.WHITE);
                diamond.setBackground(Color.WHITE);
                textArea.setBackground(Color.WHITE);
                break;
        }
    }
}
