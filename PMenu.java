
import java.awt.*;
import java.util.*;

//this would be easier if Java had closures.....

class PMenu extends Presenter {

	static PMenu createStartMenu(){
		PMenu m = new PMenu();
		m.addChoice("Continue");
		m.addChoice("New");
		m.addChoice("Quit");
		return m;
	}

	java.util.List<String> choices = new ArrayList<String>();
	int selected=1;

	void addChoice(String x){
		choices.add(x);
	}
	
	void drawOn(Graphics2D g){
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,choices.size()*10);
		
		g.setColor(Color.BLACK);
		for(int i=0; i<choices.size(); i++){
			g.drawString(choices.get(i),0,i*10+10);
		}
	}
	
	void keyPressed(char key){}
	void step(){}
}