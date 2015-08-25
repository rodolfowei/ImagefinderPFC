package FindMethod;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;



public class Producto {
	
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	
	BufferedImage imagenproducto;
	String imagepath;
	String productname;
	Mat product_descriptors;
	FeatureDetector detector;
	DescriptorExtractor extractor;
	
	private int similaridad; //number of matches
	
	static int detecting_method = FeatureDetector.SIFT;
	static int extracting_method = DescriptorExtractor.OPPONENT_SIFT;
	
	public Producto()
	{
		this.detector = FeatureDetector.create(detecting_method);
		this.extractor = DescriptorExtractor.create(extracting_method);
	}
	
	public void SetPath(String path_prod)
	{
		imagepath = path_prod;
	}
	
	public void setDescriptors(String path_prod)
	{
		Mat img = Imgcodecs.imread(path_prod);
		Mat descriptors = new Mat();
		MatOfKeyPoint keypoints = new MatOfKeyPoint();
		detector.detect(img, keypoints);
		extractor.compute(img, keypoints, descriptors);
		product_descriptors = descriptors;
	}
	
	

}
