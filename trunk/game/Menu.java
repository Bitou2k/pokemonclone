package game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.*;

//this would be easier if Java had closures.....

class Menu extends Presenter {

	static Menu createStartMenu(){
		Menu m = new Menu();
		m.addChoice("Continue");
		m.addChoice("New");
		m.addChoice("Quit");
		return m;
	}

	List<String> choices = new ArrayList<String>();
	int selected=1;

	void addChoice(String x){
		choices.add(x);
	}
	
	public void drawOn(Graphics2D g){
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,choices.size()*10);
		
		g.setColor(Color.BLACK);
		for(int i=0; i<choices.size(); i++){
			g.drawString(choices.get(i),0,i*10+10);
		}
	}
	
	public void buttonPressed(Button b){}
	public void step(int ms){}
}