package game;

import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

class StartScreen extends Presenter {

	static int current=0;
	
	synchronized void drawOn(Graphics2D g){	
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,240);
		
		Pokemon p = Pokemon.prototypes.get(current);
		p.image.paintIcon(null,g,0,0);
		g.setColor(Color.BLACK);
		g.drawString(""+p.pokedexNumber,0,50);
		g.drawString(p.name,0,60);
		g.drawString(p.description,0,70);
	}
	
	void keyPressed(char key){}
	synchronized void step(){
		current++;
		if(current>=Pokemon.prototypes.size()) current=0;
	}
}