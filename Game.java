import java.awt.*;
import java.util.*;

class Game {

	static Game newGame(){
		return null;
	}
	static Game oldGame(String file){
		return null;
	}
	
	//all the areas in a game
	private java.util.List<Area> areas; 
	
	//the current area, battle, or menu that is the root to be displayed and receive key events
	private ScreenOwner currentScreenOwner; 
	
	//the player
	private Player ash;
	
	
	
	void drawOn(Graphics2D g){
		currentScreenOwner.drawOn(g);
	}
	
	void keyPressed(char key){
		currentScreenOwner.keyPressed(key);
	}
	
	void step(){
		currentScreenOwner.step();
	}
}