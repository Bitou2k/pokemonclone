package game;

import java.awt.*;

/**
 *An area, battle, or menu that is the root to be displayed and receiver of key events
 */
abstract class Presenter {
	
	private PokemonGame shell; //the shell i'm in
	
	/**
	 *Returns the containing shell; a presenter may hand over focus to another presenter using shell().enterPresenter(anotherPresenter)
	 */
	public PokemonGame shell(){return shell;}
	public void setShell(PokemonGame shell){
		this.shell=shell;
	}
	
	abstract void drawOn(Graphics2D g);
	abstract void keyPressed(char key);
	abstract void step();
}