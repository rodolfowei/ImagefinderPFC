package FindMethod;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.opencv.core.Core;

//This class implements methods to build an array of
//images from a folder containing jpg images


public class FoldertoImagearray {
	
	static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME);};
	
	static final File ruta = new File("C:/Users/CARLOSR/Desktop/Imagenes/Muestra");
	
	public static ArrayList<String> getImages() throws IOException {
		
		if(!ruta.isDirectory())
			System.out.println("Not a directory");
		if(!ruta.exists())
			System.out.println("File doesn't exist");
		
		
		ArrayList<String> imageList = new ArrayList<String>(256);
		File[] f = ruta.listFiles();
		
		for (File file : f) 
		{
			if (file != null & file.getName().toLowerCase().endsWith(".jpg"))
			{
				imageList.add(file.getCanonicalPath());
			}
		
		}
		
		if(imageList.size() > 0)
			return imageList;
		else {
			return null;
		}
		
		
	}
	
	
	
	
	
}
