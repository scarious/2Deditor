package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;
/*
 * TREBA ESTE DOKONCIT KONTROLU A PREPOCET POMERU STRAN
 */
public class OpenFileDialog implements ActionListener {
	
	main.Paint2d paint;
	JFrame frame;
	JFileChooser fc;
	FileFilter fileFilter;
	JTextPane textWidth, textHeight;
	public OpenFileDialog(main.Paint2d paint) {
		this.paint = paint;
		this.frame = paint.getFrame();
		fc = new JFileChooser();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		 fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		 fc.setDialogType(JFileChooser.OPEN_DIALOG);
		 fc.setDialogTitle("Choose image to open");
		 
		 FileFilter filterAllImages = new myFileFilter(); 
		 fc.addChoosableFileFilter(filterAllImages);
		 fc.setFileFilter(filterAllImages);
		 
		 fc.setAccessory(new ImagePreview(fc));
		 
		 if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
					Image image = ImageIO.read(file);
					paint.addToDrawList(new graphics.Picture(image, Integer.parseInt(textWidth.getText()), Integer.parseInt(textHeight.getText())));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
            } else {
               
            }
           
	}
	
	private class ImagePreview extends JPanel implements PropertyChangeListener {
		private static final long serialVersionUID = 3698589451872740954L;
		ImageIcon thumbnail = null;
		File f = null;
		JPanel imageIconArea;
		int width, height;
		float imageRatio;
		Box sizeBox, mainBox;
		
		int initWidth = 200;
		//public boolean ratioBoxChecked = true;
		
		public ImagePreview(JFileChooser fc) {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(initWidth, 250));
			fc.addPropertyChangeListener(this);
			imageIconArea = new JPanel();
			add(imageIconArea, BorderLayout.CENTER);
			
						
			textWidth = new JTextPane();
			//textWidth.addFocusListener(focusListenerWidth);
			textHeight = new JTextPane();
			//textHeight.addFocusListener(focusListenerHeight);	
			
			mainBox = Box.createVerticalBox();
			
			sizeBox = Box.createHorizontalBox();
			sizeBox.add(Box.createHorizontalGlue());
			sizeBox.add(new JLabel("Width: "));
			sizeBox.add(textWidth);
			sizeBox.add(new JLabel("Height: "));
			sizeBox.add(textHeight);
			
			mainBox.add(sizeBox);
			
			final JCheckBox ratioCheckbox = new JCheckBox("Keep image proportions");
			ratioCheckbox.setSelected(true);
	
			mainBox.add(ratioCheckbox);
			add(mainBox, BorderLayout.SOUTH);
			mainBox.setVisible(false);
		}

		
		public void loadImage() {
			if (f == null || f.isDirectory()){
				mainBox.setVisible(false);
				//textHeight.setText("");
				//textWidth.setText("");
				return;
			}
			
			ImageIcon tmpIcon = new ImageIcon(f.getPath());
			width = tmpIcon.getIconWidth(); 
			height = tmpIcon.getIconHeight();
			mainBox.setVisible(true);
			if(height > 0 && width > 0){
				//calculateRatio(width, height);
				System.out.println("" + imageRatio);
				textWidth.setText(""+ width);
				textHeight.setText("" + height);
				//setImageDimensions(new Dimension(width, height));
				if (width > 90) {
					thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(150, -1, Image.SCALE_DEFAULT));
				} else {
					thumbnail = tmpIcon;
				}
			}
			
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			String prop = e.getPropertyName();
			if (prop.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
				f = (File) e.getNewValue();
				if(isShowing()) {
					loadImage();
					
					//textWidth.setText(""+width);
					repaint();
				}
			}
		}

		public void paint(Graphics g) {
			super.paint(g);
			if (thumbnail == null) {
				loadImage();
			}
			if (thumbnail != null) {
				int x = getWidth()/2 - thumbnail.getIconWidth()/2;
				int y = getHeight()/2 - thumbnail.getIconHeight()/2;

				if (y < 0) {
					y = 0;
				}

				if (x < 5) {
					x = 5;
				}
				thumbnail.paintIcon(imageIconArea, g, x, y);
			}
		}
}

	
	private class myFileFilter extends FileFilter{

		@Override
		public boolean accept(File arg0) {
			if (arg0.isDirectory()) {
		        return true;
		    }

		    String extension;
			try {
				extension = arg0.getCanonicalPath();
				if (extension != null) {
			        if (extension.endsWith(".tiff") ||
			        	extension.endsWith(".tif") ||
			        	extension.endsWith(".gif") ||
			        	extension.endsWith(".jpeg") ||
			        	extension.endsWith(".jpg") ||
			        	extension.endsWith(".png") ||
			        	extension.endsWith(".bmp")) {
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

	
} //end
