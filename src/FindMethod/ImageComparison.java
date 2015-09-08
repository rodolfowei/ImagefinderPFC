package FindMethod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays; //Only if we implement the similarity order method
import java.util.Collections; // Only if we implement the similarity order method
import java.util.List;

import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;



public class ImageComparison {
	
	static final File ruta = new File("C:/Users/CARLOSR/Desktop/Imagenes/Muestra");

	
	// This class will allow to implement a method that can compare an input images with
	//a folder full of images and determine which image is the most similar one
	
	FeatureDetector detector;
	int detection_method = FeatureDetector.SIFT; //Multiple options
	
	DescriptorExtractor extractor;
	int extraction_method = DescriptorExtractor.OPPONENT_SIFT; //Multiple options
	
	DescriptorMatcher matcher;
	int matching_method = DescriptorMatcher.FLANNBASED;
	
	public ImageComparison() 
	{
		this.detector = FeatureDetector.create(detection_method);
		this.extractor = DescriptorExtractor.create(extraction_method);
		this.matcher = DescriptorMatcher.create(matching_method);
	}

	//This method returns the input image descriptors
	
	public Mat obtaindescriptors(String imagepath) 
	{
		System.out.println("Analizando in_image");
		
		Mat in_image = Imgcodecs.imread(imagepath);
		MatOfKeyPoint in_keypoints = new MatOfKeyPoint();
		Mat in_descriptors = new Mat();
		detector.detect(in_image, in_keypoints);
		extractor.compute(in_image, in_keypoints, in_descriptors);
		
		File toname = new File(imagepath);
		System.out.println("imagen Analizada: "+toname.getName() + "\n");
		
		return in_descriptors;
		
	}
	public Producto obtainbestmatch(String imagepath)
	{
		
		int similaridad = -1;
		Producto topmatcher = new Producto();
		Mat in_descriptors = obtaindescriptors(imagepath);
		
		//Here prepare an array to allocate all the products on the DB
		ArrayList<Producto> productosDB = new ArrayList<Producto>();
		
		ArrayList<String> imagepaths;
		try{
			imagepaths = FoldertoImagearray.getImages();
			
			for (int i = 0; i < imagepaths.size(); i++) 
			{
				Producto temp = new Producto();
				temp.imagepath = imagepaths.get(i);
				temp.setDescriptors(temp.imagepath);
				temp.setName(temp.imagepath);
				productosDB.add(temp);
			}
		} catch (IOException e){
			System.out.println("Error creando los productos");
			e.printStackTrace();
		}
		
		
		for(Producto prod : productosDB)
		{
			System.out.print("Analizando imagen " + prod.productname);
			MatOfDMatch matches = new MatOfDMatch();
			matcher.match(in_descriptors, prod.getDescriptors(),matches);
			
			
			
			List<DMatch> listofmatches = matches.toList();
			int numberofcoincidences = 0;
			for (DMatch dm : listofmatches)
			{
				if(dm.distance < 300)
				{
					numberofcoincidences++;
				}
			}
			
			System.out.print("\t Number of matches: " + numberofcoincidences + "\n");
			
			if(numberofcoincidences > similaridad)
            {
            	similaridad = numberofcoincidences;
            	topmatcher = prod;
            	System.out.println("new top matcher: " + topmatcher.imagepath);
            }

						
		}
		
		//If there is no match with any product in the data base
	
		if(similaridad == 0)
			System.out.println(" No matches have been found ");
		else
			System.out.println(" The product is : " + topmatcher.getName());
		
		return topmatcher;
	}
			

}
