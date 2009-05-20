package game;

import java.awt.*;
import java.util.*;

/**
 *I display the player's backpack, one of the the four pockets at a time.
 */
public class PackPresenter extends Presenter {
	
	private Pack pack;
	
	public void gotFocus()
	{
		pack = player().pack();
	}
	
	/**
	*Draw the pack, see Pokemon Gold/Silver for example.
	*/
	public void drawOn(Graphics2D g)
	{
	}
	
	/**
	*On up or down, change selected item within the currect pocket; on left or right, switch current pocket; on A, try to use the current item.
	*/
	public void buttonPressed(Button b)
	{	
	}
	
	/**
	*Not applicable for this Presenter.
	*/
	public void step(int ms){}
	
}