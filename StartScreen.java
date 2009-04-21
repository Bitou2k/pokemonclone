
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

class StartScreen extends Presenter {

	static ArrayList<ImageIcon> iis = new ArrayList<ImageIcon>();
	static int current=0;
	
	static{
	try{
		File folder = new File("./icons");
		for(File f: folder.listFiles())
		{
			ImageIcon ii = new ImageIcon(f.toString());
			iis.add(ii);
		}
		}catch(Exception e){}
	}
	
	void drawOn(Graphics2D g){	
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,240);
		
		iis.get(current).paintIcon(null,g,50,50);
		
	}
	
	void keyPressed(char key){}
	void step(){
		current++;
	}
}