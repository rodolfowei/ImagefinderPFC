package FindMethod;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	
	public Mat getDescriptors()
	{
		return product_descriptors;
	}
	
	public void setBufimage(String path_prod)
	{
		File bufim = new File(path_prod);
		try
		{
		imagenproducto = ImageIO.read(bufim);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public BufferedImage obtainBuffimage()
	{
		return imagenproducto;
	}
	
	public String getPath()
	{
		return imagepath;
	}
	
	public void setName(String path_prod)
	{
		File f = new File(imagepath);
		productname = f.getName();
	}
	
	public String getName()
	{
		return productname;
	}
	
	int getSimilaridad()
	{
		return similaridad;
	}
	
	void setSimilaridad(int similaridad)
	{
		this.similaridad = similaridad;
	}
	
	public int compareTo(Producto prod)
	{
		if(similaridad < prod.similaridad)
			return -1;
		
		if(similaridad > prod.similaridad) 
			return 1;
		
		return 0;
		
	}
	
	
	
	
	
	
	
	
	

}

