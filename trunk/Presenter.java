
import java.awt.*;

//an area, battle, or menu that is the root to be displayed and receive key events
//screenowner has been renamed presenter in honor of Hopscotch
abstract class Presenter {
	
	PokemonGame shell; //the shell i'm in
	
	public void setShell(PokemonGame shell){
		this.shell=shell;
	}
	
	abstract void drawOn(Graphics2D g);
	abstract void keyPressed(char key);
	abstract void step();
}