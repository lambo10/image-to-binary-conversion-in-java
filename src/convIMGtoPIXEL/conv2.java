package convIMGtoPIXEL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.PixelGrabber;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.lang.Object;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class conv2 {
  
	public static void main (String [] args){
		
		   Image source;
		   int ImgHighet;
		    int ImgWidth;
		    ImageIcon sid = new ImageIcon("icesid.png");
			 source = sid.getImage();
		   ImgHighet = source.getHeight(null);
			 ImgWidth = source.getWidth(null);
			 int Pixelarray = ImgHighet * ImgWidth;
		   int[] pixelH = new int[ ImgWidth  *  ImgHighet ];
		   PixelGrabber pg = new PixelGrabber(source, 0, 0, ImgWidth, ImgHighet, pixelH, 0, ImgWidth);
		   try {
		        pg.grabPixels();
		      } catch (InterruptedException e) {
		        throw new IllegalStateException("Error: Interrupted Waiting for Pixels");
		      }
		int fg = 0; 
		int newpxA =  Pixelarray - 1;
	while(fg <= newpxA){
		
		System.out.println(fg+" :"+pixelH[fg]);
		fg++;
	}
	
	}
}
