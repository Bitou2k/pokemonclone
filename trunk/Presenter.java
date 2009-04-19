
import java.awt.*;

//an area, battle, or menu that is the root to be displayed and receive key events
//screenowner has been renamed presenter in honor of Hopscotch
abstract class Presenter {
	
	Game game; //the game i'm in
	
	abstract void drawOn(Graphics2D g);
	abstract void keyPressed(char key);
	abstract void step();
}