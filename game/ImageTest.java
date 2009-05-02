package game;

import java.io.*;
import javax.swing.*;

class ImageTest {

	public static void main(String ar[]) throws Exception {
	
		File folder = new File("./icons");
		
		for(File f: folder.listFiles())
		{
			System.out.print(f);
			ImageIcon ii = new ImageIcon(f.toString());
			ii.getImage();
			
			System.out.print(ii);
			System.out.print(ii.getIconWidth());
			System.out.println(ii.getIconHeight());
			//System.out.println(ii.getImage());
		}
	}

}