package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;


public class SaveFileDialog implements ActionListener {
    private Paint2d painted;

    main.Paint2d paint;
    JFrame frame;
    JFileChooser fc;
    FileFilter fileFilter;
    JTextPane textWidth, textHeight;
    Graphics2D g2;
    JComponent drawingArea;

    public SaveFileDialog(main.Paint2d paint) {
        this.paint = paint;
        this.frame = paint.getFrame();
        fc = new JFileChooser();
        drawingArea = paint.getDrawingArea();//JComponent na ktory sa kresli
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //typ dialogoveho okna = ukladanie suborov
        fc.setDialogType(JFileChooser.SAVE_DIALOG);
        //v prehlade suborov sa budu zobrazovat aj subory aj adresare
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        PngFilter pngFilter = new PngFilter(); 
        JpgFilter jpgFilter = new JpgFilter();
        //vyber typov suborov
        fc.addChoosableFileFilter(pngFilter);
        fc.addChoosableFileFilter(jpgFilter);
        fc.setFileFilter(pngFilter);
        
        //vsetko v if(...) sa vykona ak kliknes na Save tlacitko
        if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
              BufferedImage bi = new BufferedImage(drawingArea.getWidth()-1, drawingArea.getHeight()-1, BufferedImage.TYPE_INT_ARGB);
              
              //treba dat prec najskor to cervene ohranicenie inac to s nim uklada, potom nizsie sa to ohranici naspat
              //vidiet to nikto nebude lebo sa nevola repaint 
              drawingArea.setBorder(null); 
              drawingArea.paint(bi.getGraphics());
              try {
<<<<<<< HEAD
            	  ImageIO.write(bi, fc.getFileFilter().getDescription(), new File(fc.getSelectedFile().getCanonicalPath() + "." + fc.getFileFilter().getDescription())); 
=======
            	  ImageIO.write(bi, pngFilter.getExtension(), new File(fc.getSelectedFile().getCanonicalPath() + "." + pngFilter.getExtension())); 
>>>>>>> master
            	//ulozenie obrazku na cestu ktoru dostane z okna na ukladanie  
			} catch (IOException e1) {
			}
             drawingArea.setBorder(new LineBorder(Color.RED, 1));  
        }
    }
    
    private class PngFilter extends FileFilter{

		@Override
		public boolean accept(File arg0) {
			if (arg0.isDirectory()) {
		        return true;
		    }

		    String extension;
			try {
				extension = arg0.getCanonicalPath();
				if (extension != null) {
			        if (extension.endsWith(".png")) {
			                return true;
			        } else {
			            return false;
			        }
			    }
			} catch (IOException e) {
				
			}
		    

		    return false;
		}

		@Override
		public String getDescription() {
<<<<<<< HEAD
			return "png";
		}
=======
			return "*.png";
		}
		
		public String getExtension(){
			return "png";
		}
		
>>>>>>> master
	}

    private class JpgFilter extends FileFilter{

		@Override
		public boolean accept(File arg0) {
			if (arg0.isDirectory()) {
		        return true;
		    }

		    String extension;
			try {
				extension = arg0.getCanonicalPath();
				if (extension != null) {
			        if (extension.endsWith(".jpg")) {
			                return true;
			        } else {
			            return false;
			        }
			    }
			} catch (IOException e) {
				
			}
		    

		    return false;
		}

		@Override
		public String getDescription() {
<<<<<<< HEAD
			return "jpg";
		}
=======
			return "*.jpg";
		}
		
		public String getExtension(){
			return "jpg";
		}
		
>>>>>>> master
	}

}