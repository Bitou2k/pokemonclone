
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.stream.*;
import javax.imageio.*;

public class Splitter
{
	static ArrayList<File> delete = new ArrayList<File>();
	
	public static void main(String[] a) throws Exception
	{
		ImageIcon ii = new ImageIcon(a[0]);
		BufferedImage bi = new BufferedImage(ii.getIconWidth(),ii.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = bi.createGraphics();
		ii.paintIcon(null,g,0,0);
		
		ArrayList<File> files = new ArrayList<File>();
		
		int no=0;
		for(int x=0; x<bi.getWidth(); x+=16)
			for(int y=0; y<bi.getHeight(); y+=16)
			{
				no++;
				
				File f = write( bi.getSubimage(x,y,16,16), ""+no );
				//System.out.println("wrote section "+no);
				
				files.add(f);
				for(File f1: files) removeDup(f,f1);
				
			}
	
	
		FileWriter w = new FileWriter("merge.bat");
		for(File f: delete)
			w.write("del "+f+"\n");
		w.close();
		
	}
	

	static void removeDup(File f1, File f2)
	{
		try
		{
			if(f1.equals(f2)) return;
			if(!f1.exists()) return;
			if(!f2.exists()) return;
			if(delete.contains(f1)||delete.contains(f2)) return;
			
			InputStream i1 = new FileInputStream(f1);
			InputStream i2 = new FileInputStream(f2);
			
			int b1, b2;
			while( (b1=i1.read())!=-1 && (b2=i2.read())!=-1 )
			{
				if(b1!=b2) return;
			}
			
			
			i1.close();i2.close();
			
			delete.add(f1);
		}catch(Exception e){}
	}
	static File write(BufferedImage bi, String file) throws Exception
	{
		Iterator writers = ImageIO.getImageWritersByFormatName("gif");
		ImageWriter writer = (ImageWriter)writers.next();

		File f = new File("./"+file+".gif"); //.getCanonicalFile();
		ImageOutputStream ios = ImageIO.createImageOutputStream(f);
		writer.setOutput(ios);

		
		writer.write(bi);
		
		ios.close();
		
		return f;
	}
}