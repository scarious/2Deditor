package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;

public class SaveFileDialog implements ActionListener {

    main.Paint2d paint;
    JFrame frame;
    JFileChooser fc;
    FileFilter fileFilter;
    JTextPane textWidth, textHeight;
    Graphics2D g2;

    public SaveFileDialog(main.Paint2d paint) {
        this.paint = paint;
        this.frame = paint.getFrame();
        fc = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        String button = e.getActionCommand();
        int val;
        boolean exists;
        //set the current directory the application's current directory  
        try {
            //create a file object containing the cannonical path of the desired file   
            File f = new File(new File("").getCanonicalPath());
            //set the selected file  
            fc.setSelectedFile(f);
        } catch (IOException ex) {
        }
        if (button.equals("Save")) {
            //show the dialog; wait until dialog is closed  
            val = fc.showSaveDialog(null);
            //Approve(Save was clicked)  
            if (val == JFileChooser.APPROVE_OPTION) {
                //get the currently selected file  
                File thefile = fc.getSelectedFile();
                String nameOfFile = "";
                nameOfFile = thefile.getPath();
                //check if the file exists  
                exists = (new File(nameOfFile)).exists();
                if (!exists) {

                    try {
                       
                         BufferedImage bi = new BufferedImage(paint.getWidth(), paint.getHeight(), BufferedImage.TYPE_INT_ARGB);

                         
                       
                        ImageIO.write(bi, "jpg", new File(nameOfFile));

                    } catch (IOException ex1) {
                    }
                } 
            }
        }
    }
               
        
    //end of ActionPerformed method  
    //end of action listener  

    private class myFileFilter2 extends FileFilter {

        @Override
        public boolean accept(File arg0) {
            if (arg0.isDirectory()) {
                return true;
            }

            String extension;
            try {
                extension = arg0.getCanonicalPath();
                if (extension != null) {
                    if (extension.endsWith(".tiff")
                            || extension.endsWith(".tif")
                            || extension.endsWith(".gif")
                            || extension.endsWith(".jpeg")
                            || extension.endsWith(".jpg")
                            || extension.endsWith(".png")
                            || extension.endsWith(".bmp")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            return false;
        }

        @Override
        public String getDescription() {
            return "All Images";
        }
    }
}