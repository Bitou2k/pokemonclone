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
	int curNumItems;
	int itemCategory;
	int topIndex;
	
	/**
	* true: item list has focus, false: menu has focus
	*/
	boolean itmf; 
	
	private ImageIcon BagScreen = new ImageIcon("./resources/bag/bag.png");
	
	private ImageIcon ItemsText = new ImageIcon("./resources/bag/itemstext.png");
	private ImageIcon BallsText = new ImageIcon("./resources/bag/ballstext.png");
	private ImageIcon KeyItemsText = new ImageIcon("./resources/bag/keyitemstext.png");
	private ImageIcon TmHmText = new ImageIcon("./resources/bag/tmhmtext.png");

	private ImageIcon ItemsImage = new ImageIcon("./resources/bag/itemsimage.png");
	private ImageIcon BallsImage = new ImageIcon("./resources/bag/ballsimage.png");
	private ImageIcon KeyItemsImage = new ImageIcon("./resources/bag/keyitemsimage.png");
	private ImageIcon TmHmImage = new ImageIcon("./resources/bag/tmhmimage.png");

	private ImageIcon arrow = new ImageIcon("./resources/arrow.png");
	private ImageIcon idleArrow = new ImageIcon("./resources/idlearrow.png");
	
	
	public BagPresenter(Presenter oldP){
		oldPresenter = oldP;		
		itemCursorIndex = 0;
		menuCursorIndex = 0;
		itmf = true;
		topIndex = 0;
		itemCategory=1;
	}

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

		Set<Item> Items;
		
		if (itemCategory == 1){ 
			g.drawImage(ItemsImage.getImage(),0,48,null); 
			g.drawImage(ItemsText.getImage(),0,112,null); 
			Items = player().pack().getAllItems();
			curNumItems = player().pack().getAllItems().size();
		}
		else if (itemCategory == 2){ 
			g.drawImage(BallsImage.getImage(),0,48,null); 
			g.drawImage(BallsText.getImage(),0,112,null); 
			Items = player().pack().getAllPokeballs();
			curNumItems = player().pack().getAllPokeballs().size();
		}
		else if (itemCategory == 3){ 
			g.drawImage(KeyItemsImage.getImage(),0,48,null); 
			g.drawImage(KeyItemsText.getImage(),0,112,null); 
			Items = player().pack().getAllKeyItems();
			curNumItems = player().pack().getAllKeyItems().size();
		}
		else if (itemCategory == 4){ 
			g.drawImage(TmHmImage.getImage(),0,48,null); 
			g.drawImage(TmHmText.getImage(),0,112,null); 
			Items = player().pack().getAllTmHms();
			curNumItems = player().pack().getAllTmHms().size();
		}
		else
		{
			return;
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,20));
		
		Iterator<Item> kI = Items.iterator();
		ArrayList<Item> ItemsList = new ArrayList<Item>();
		for (int x = 0; kI.hasNext(); x++){
			ItemsList.add(kI.next());
		}
		
		int lp=0;
		if (ItemsList.size() > 5) { lp=5; }
		else { lp=ItemsList.size(); }
		for (int i=0; i < lp; i++){
			Item item = ItemsList.get((topIndex+i) % (Items.size()));
			g.drawString( item.getName(), X, Y );
			if (itemCategory==1) { g.drawString( "X " + String.valueOf(player().pack().getQtyOfItem(item)), X+160, Y+15); }
			if (itemCategory==2) { g.drawString( "X " + String.valueOf(player().pack().getQtyOfPokeball(item)), X+160, Y+15); }
			if (itemCategory==3) { g.drawString( "X " + String.valueOf(player().pack().getQtyOfKeyItem(item)), X+160, Y+15); }
			if (itemCategory==4) { g.drawString( "X " + String.valueOf(player().pack().getQtyOfTmHm(item)), X+160, Y+15); }
			Y+=inc;
		}

		Item selItem = ItemsList.get((itemCursorIndex+topIndex) % Items.size());
		g.drawString( selItem.description(), 20, 232);
	
	}
	
	public void buttonPressed(Button b){
		int bounds=0;
		if (b==Button.START)	enterPresenter(oldPresenter);
		else if (b==Button.DOWN){
			if(itmf){
				if (itemCursorIndex==4){
					if (topIndex+4<curNumItems)
						topIndex++;
				}
				else{
					if (itemCursorIndex<curNumItems-1)
						itemCursorIndex++;
				}
			}
			else{
				if (menuCursorIndex != 3) menuCursorIndex++;
			}
		}
		else if (b==Button.UP){
			if(itmf){

				if (itemCursorIndex>0)
					itemCursorIndex--;
				else
					if (topIndex>0) { topIndex--; }
				
			}
			else{
				if (menuCursorIndex != 0) menuCursorIndex--;
			}
		}
		else if (b==Button.LEFT){
			if (itemCategory != 1) itemCategory--;
		}
		else if (b==Button.RIGHT){
			if (itemCategory != 4 ) itemCategory++;
		}

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