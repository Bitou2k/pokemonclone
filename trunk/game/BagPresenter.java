package game;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * 
 */
class BagPresenter extends Presenter {
	
	
	/**
	* the presenter to go back to
	*/
	Presenter oldPresenter;
	
	/**
	* cursor index next to the pokemon
	*/
	int itemCursorIndex;
	
	/**
	* cursor index next to the menu
	*/
	int menuCursorIndex;
	int topIndex;
	
	/**
	* true: item list has focus, false: menu has focus
	*/
	boolean itmf; 
	
	private ImageIcon BagScreen = new ImageIcon("./resources/bag.png");
	private ImageIcon arrow = new ImageIcon("./resources/arrow.png");
	private ImageIcon idleArrow = new ImageIcon("./resources/idlearrow.png");

	public BagPresenter(Presenter oldP){
		oldPresenter = oldP;		
		itemCursorIndex = 0;
		menuCursorIndex = 0;
		itmf = true;
		topIndex = 0;
	}
	//57,15
	public void drawOn(Graphics2D g){
		
		int inc = 30;
		int X = 114;
		int Y = 45;
		
		ImageIcon itemArrow,menuArrow;
		if (itmf){
			itemArrow = arrow;
			menuArrow = idleArrow;
		}
		else{
			itemArrow = idleArrow;
			menuArrow = arrow;
		}
		
		g.drawImage(BagScreen.getImage(),0,0,null);
		g.drawImage(itemArrow.getImage(), X - 25, 30 + itemCursorIndex * inc, null);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,20));
		
		Set<Item> keyItems = player().pack().getAllItems();
		Iterator<Item> kI = keyItems.iterator();
		ArrayList<Item> keyItemsList = new ArrayList<Item>();
		for (int x = 0; kI.hasNext(); x++){
			keyItemsList.add(kI.next());
		}
		
		int lp=0;
		if (keyItemsList.size()>5) { lp=5; }
		else { lp=keyItemsList.size(); }
		for (int i=0; i < lp; i++){
			Item item = keyItemsList.get((i + topIndex) % (keyItems.size()));
			g.drawString( item.getName(), X, Y );
			g.drawString( "X " + String.valueOf(player().pack().getQtyOfItem(item)), X+160, Y+15);
			Y+=inc;
		}

		Item selItem = keyItemsList.get((topIndex) % keyItems.size());
		g.drawString( selItem.description(), 20, 232);
	
	}
	
	public void buttonPressed(Button b){
		int bounds=0;
		if (b==Button.START)	enterPresenter(oldPresenter);
		else if (b==Button.DOWN){
			if(itmf){
	
				if (player().pack().getAllKeyItems().size()>5) { bounds = 4; } 
				else { bounds = player().pack().getAllKeyItems().size(); }
				if (itemCursorIndex != bounds){ itemCursorIndex++;}
				else {
					topIndex++;
					if (topIndex == player().pack().getAllKeyItems().size()) topIndex = 0;
				}

			}
			else{
				if (menuCursorIndex != 3) menuCursorIndex++;
			}
		}
		else if (b==Button.UP){
			if(itmf){
				if (itemCursorIndex != 0){ itemCursorIndex--;}
				else 
				{
					topIndex--;
					if (topIndex == -1) topIndex = player().pack().getAllKeyItems().size() - 1;
				}
			}
			else{
				if (menuCursorIndex != 0) menuCursorIndex--;
			}
		}
		//else if (b==Button.LEFT || b==Button.RIGHT) pkmn = !pkmn;

	}
	
	public void step(int ms){}
	

	 /**
	 *  Retrieves all items from Bag
	 */
	//public abstract ArrayList<Item> getAllItems();
	
	/**
	 * Retrieves all item quantities from Bag
	 */
	//public abstract ArrayList<Integer> getAllQtys();

}