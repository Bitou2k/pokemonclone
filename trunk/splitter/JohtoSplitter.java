
package splitter;
import game.*;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.stream.*;
import javax.imageio.*;

public class JohtoSplitter
{
	static ArrayList<File> files = new ArrayList<File>();	
	static LinkedList<JohtoTile> JohtoTiles = new LinkedList<JohtoTile>();
	static int width,height;
	
	public static void main(String[] a) throws Exception
	{
		for(File f: new File("../JohtoTileImages").listFiles())
		{
			if(f.toString().startsWith("johto") && f.toString().endsWith(".gif")) files.add(f);
		}
	
		ImageIcon ii = new ImageIcon(a[0]);
		BufferedImage bi = new BufferedImage(ii.getIconWidth(),ii.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = bi.createGraphics();
		ii.paintIcon(null,g,0,0);
		
		width = bi.getWidth()/16;
		height = bi.getHeight()/16;
		
		System.out.println("loaded image, "+width*height+" cells");
		
		int no=0;
		a:
		for(int x=0; x<width; x++)
		{
			b:
			for(int y=0; y<height; y++)
			{
				no++;
				File f = write( bi.getSubimage(x*16,y*16,16,16), nextFreeFile() );
								
				JohtoTile t = new JohtoTile(x,y);
				t.image = f;
				JohtoTiles.add(t);
				
				c:
				for(File possibleReplacement: files)
				{
					if(areDuplicates(f,possibleReplacement))
					{
						t.image = possibleReplacement;
						f.getCanonicalFile().delete();
						continue b;
					}
				}
				//no duplicate
				files.add(f);
			}
			System.out.print(" "+no+"("+files.size()+")");
			save();
		}
		
		
		save();
	}
	
	static void save() throws Exception
			{
			Node mapNode = new Node("map");
			mapNode.addSubnode("width",""+width);
			mapNode.addSubnode("height",""+height);
			mapNode.addSubnode("name","johto");
			for(JohtoTile t: JohtoTiles)
				mapNode.addSubnode( t.asNode() );
							
			OutputStream os = new FileOutputStream("JJJJJJJJJJJJ.nml");
			mapNode.tryWriteOn(os); 
			os.flush();
			os.close();
		}
	

	static boolean areDuplicates(File f1, File f2) throws Exception
	{
		InputStream i1=null,i2=null;
		try
		{
			if(f1.equals(f2)) return false;
			if(!f1.exists()) return false;
			if(!f2.exists()) return false;
			
			i1 = new BufferedInputStream(new FileInputStream(f1),(int)f1.length());
			i2 = new BufferedInputStream(new FileInputStream(f2),(int)f2.length());
			
			int b1, b2;
			while( (b1=i1.read())!=-1 && (b2=i2.read())!=-1 )
			{
				if(b1!=b2) return false;
			}
			
			
			return true;
		}catch(Exception e){
			return false;
		}finally{
			if(i1!=null)i1.close();
			if(i2!=null)i2.close();
		}
	}
	
	static File nextFreeFile()
	{
		int no=1;
		while( new File("../JohtoTileImages/johto"+no+".gif").exists() ) no++;
		return new File("../JohtoTileImages/johto"+no+".gif");
	}
	
	static File write(BufferedImage bi, File file) throws Exception
	{
		Iterator writers = ImageIO.getImageWritersByFormatName("gif");
		ImageWriter writer = (ImageWriter)writers.next();

		File f = file;
		ImageOutputStream ios = ImageIO.createImageOutputStream(f);
		writer.setOutput(ios);		
		writer.write(bi);
		writer.setOutput(null);
		
		ios.close();
		
		return f;
	}
}