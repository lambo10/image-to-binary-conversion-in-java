package convIMGtoPIXEL;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class convH2  extends JFrame {
	 private void marchThroughImage(BufferedImage image,String workPicName) {
		    int w = image.getWidth();
		    int h = image.getHeight();
		    int backgroundColor = 0;
		    double backgroundColorValue = 0;
		    double ActualbackgroundColorValue = 0;
		    double[][] avePixel = new double[(w+1)][(h+1)];
		    int[][] dataSD = new int[(w+1)][(h+1)];
		    System.out.println("width, height: " + w + ", " + h);

		    

		    
		    for (int i = 0; i < h; i++) {
		      for (int j = 0; j < w; j++) {
		        int pixel = image.getRGB(j, i);
		        
		        int alpha = (pixel >> 24) & 0xff;
			    int red = (pixel >> 16) & 0xff;
			    int green = (pixel >> 8) & 0xff;
			    int blue = (pixel) & 0xff;
			    
			   avePixel[j][i]=(red + green + blue)/3;
		        
			   
		      }
		    }
		    
		   int sbh = 0;
		   double ApixelSum = 0; 
		   int Gsa = 0;
		   while (sbh < (h+1)){
			   int bgs = 0;
			   while (bgs < (w+1)){
				   ApixelSum = ApixelSum + avePixel[bgs][sbh];
				   Gsa++;
				bgs++;   
			   }
			   sbh++;
		   }
		   
		   double Threshold = (ApixelSum /Gsa);
		   
		   int SAVH = 0;
		   while (SAVH < (h+1)){
			   int VHSAO  = 0;
			   while (VHSAO < (w+1)){
				if(avePixel[VHSAO][SAVH] < Threshold){
					dataSD[VHSAO][SAVH]= 1;
				}else{
					dataSD[VHSAO][SAVH]= 0;
				}
				   VHSAO++;
			   }
			   SAVH++;
		   }
		  		   picOutFrame picOut = new picOutFrame();
		   picOut.setVisible(true);
		   
		    
		   
		   
		   int numOfActiveInput = 0;
		   int numOfInActiveInput = 0;
		    for (int i = 0; i < h; i++) {
			      for (int j = 0; j < w; j++) {
			    	  
			    	 if( dataSD[j][i] >= 1){
			    		 numOfActiveInput++;
			    		 picOut.getGraphics().drawLine(j, i, j, i);
			    	 }else{
			    		 numOfInActiveInput++;
			    		
			    	 }
			    	 
			      }
			      }
		    System.out.println("no of 1 :" +numOfActiveInput+" | no of 0 :" +numOfInActiveInput );
		   		    
		try{
			 File file = new File("A_traningData"+workPicName+".txt");
	            file.createNewFile();
	            FileWriter f0 = new FileWriter("A_traningData"+workPicName+".txt");
	          
	            f0.write("trainingSet.addRow(new DataSetRow(new double[]{");
	            int voh = 0;
	            while (voh < (h+1)){
	            	int vow = 0;
	            	while(vow < w+1){
	            		
	            		f0.write(dataSD[vow][voh]+",");
	            		
	            		vow++;
	            	}
	            	voh++;
	            }
	            f0.write("}, new double[]{1,0,0,0,0,0}));"+"\n");
	           
	            f0.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	
	 }
	 
		public convH2() {
	
		BufferedImage image = null;
	    try {
	    	 File folder = new File("testData");
	           File[] listOfFiles = folder.listFiles();
	           
	           int uuoi = 0;
	            while(uuoi < listOfFiles.length){
	            	if(listOfFiles[uuoi].isFile()){
	            		String WpicName = listOfFiles[uuoi].getName();
	      image = 
	        ImageIO.read(new File("testData/"+WpicName));
	      String  workPicName = listOfFiles[uuoi].getName();
	      marchThroughImage(image,workPicName);
	      System.out.println("File: "+listOfFiles[uuoi].getName());
	            	}
	            	uuoi++;
	            }
	    } catch (IOException e) {
	      System.err.println(e.getMessage());
	    }
	   
	  }
	
	
	 

}
