
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

class StartScreen extends Presenter {

	static int current=0;
	
	synchronized void drawOn(Graphics2D g){	
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,240);
		
		Pokemon.prototypes.get(current).image.paintIcon(null,g,50,50);
		
	}
	
	void keyPressed(char key){}
	synchronized void step(){
		current++;
		if(current>=Pokemon.prototypes.size()) current=0;
	}
}