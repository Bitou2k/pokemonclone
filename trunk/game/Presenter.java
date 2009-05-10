package game;

import java.awt.*;

/**
 *An area, battle, or menu that is the root to be displayed and receiver of key events
 */
abstract class Presenter {
	
	private PokemonGame shell; //the shell i'm in
	
	/**
	 *Hand over focus to another presenter.
	 */
	public void enterPresenter(Presenter newPresenter)
	{
		shell.enterPresenter(newPresenter);
	}
	
	/**
	 *Don't use this.
	 */
	public void setShell(PokemonGame shell){
		this.shell=shell;
	}
	
	/**
	 *Draw this presenter to the screen,  DO NOTHING ELSE.
	 */
	public abstract void drawOn(Graphics2D g);
	
	/**
	 *Respond to a key stroke.
	 */
	public abstract void keyPressed(char key);
	
	/**
	 *Called once a second, do things like having citizens walk around.
	 */
	public abstract void step();
}