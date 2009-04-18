
import java.awt.*;
import java.util.*;

//this would be easier if Java had closures.....

class PMenu extends ScreenOwner {


	static PMenu createStartMenu(){
		PMenu m = new PMenu();
		m.addChoice("Continue");
		m.addChoice("New");
		m.addChoice("Quit");
		return m;
	}
	

	java.util.List<String> choices = new ArrayList<String>();

	void addChoice(String x){
		choices.add(x);
	}
	
	void drawOn(Graphics2D g){
		
	}
	
	void keyPressed(char key){}
	void step(){}
}