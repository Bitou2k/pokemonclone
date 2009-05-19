package game;

import java.awt.*;

/**
 *An area, battle, or menu that is the root to be displayed and receiver of key events
 */
abstract class Presenter {
	
	private Game game;
	

	/**
	 *In constructors of presenters, you should not reference player(): when the construct runs it has not been connected yet.
	 */
	protected Presenter(){}

	/**
	 *Hand over focus to another presenter.
	 */
	public void enterPresenter(Presenter newPresenter)
	{
		game.enterPresenter(newPresenter);
	}
	
	/**
	 *Called after this presenter has been given control of the screen.
	 */
	public void gotFocus(){}
	/**
	 *Called after this presenter has just lost control of the screen.
	 */
	public void lostFocus(){}
	
	public boolean isDown(Button b)
	{
		return game.isDown(b);
	}
	
	/**
	 *Convience on game().player()
	 */
	public Player player()
	{
		return game.player();
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
//	public Game game(){
//		return game;
//	}
	
	/**
	*Display the message in a standard way, up to two lines at a time, blocking return until the user goes through it.
	*/
	public void showMessage(String message)
	{
		MessagePresenter mp = new MessagePresenter(message,this,this);
		
		enterPresenter(mp);
		
		synchronized(mp) {
			try{
				mp.wait();
			}catch(Exception ex){}
		}
	}
	
	/**
	*Display the choices in a standard way, blocking return until the user makes a selection.
	*/
	public String showMenu(String[] choices)
	{
		MenuPresenter mp = new MenuPresenter(choices,this,this);
		
		enterPresenter(mp);
		synchronized(mp){
			try{
				mp.wait();
			}catch(Exception ex){}
		}
		return mp.choice();
	}
	
	/**
	*Display the choices and message/question in a standard way, blocking return until the user makes a selection.
	*/
	public String showMenu(String message, String[] choices)
	{
		MessagePresenter mep = new MessagePresenter(message,this,this);
		MenuPresenter mup = new MenuPresenter(choices,mep,this);
		
		enterPresenter(mep);
		enterPresenter(mup);
		synchronized(mup){
			try{
				mup.wait();
			}catch(Exception ex){}
		}
		return mup.choice();
	}
	
	/**
	 *Draw this presenter to the screen,  IMPLEMENTIONS OF THIS METHOD SHOULD NOT CHANGE THE PRESENTERS STATE.
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