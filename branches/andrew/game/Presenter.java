package game;

import java.awt.*;

/**
 *An area, battle, or menu that is the root to be displayed and receiver of key events
 */
abstract class Presenter {
	
	private Game game;
	
	/**
	 *Hand over focus to another presenter.
	 */
	public void enterPresenter(Presenter newPresenter)
	{
		game.enterPresenter(newPresenter);
	}
	
	public boolean isDown(Button b)
	{
		return game.isDown(b);
	}
	
	/**
	 *Convience on game().player()
	 */
	public Player player()
	{
		return game().player();
	}
	
	/**
	*Request repaint on the receiver.
	*/
	public void repaint()
	{
		game.repaint();
	}
	
	/**
	 *Don't touch this.
	 */
	public void initGame(Game g){
		game=g;
	}
	public Game game(){
		return game;
	}
	
	/**
	 *Draw this presenter to the screen,  DO NOTHING ELSE.
	 */
	public abstract void drawOn(Graphics2D g);
	
	/**
	 *Respond to a button push.
	 */
	public abstract void buttonPressed(Button b);
	
	/**
	 *Called at 10Hz, do things like having citizens walk around.
	 */
	public abstract void step(int ms);
}