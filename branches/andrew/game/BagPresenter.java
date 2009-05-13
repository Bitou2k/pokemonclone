package game;

import java.awt.*;
import java.util.*;

/**
 * 
 */
abstract class BagPresenter extends Presenter {
	
	public void drawOn(Graphics2D g)
	{
		
	}
	
	public void buttonPressed(Button b)
	{
		
	}
	
	public void step()
	{
		
	}
	
	/**
	 *  Retrieves all items from Bag
	 */
	public abstract ArrayList<Item> getAllItems();
	
	/**
	 * Retrieves all item quantities from Bag
	 */
	public abstract ArrayList<Integer> getAllQtys();
	
	
	
}