package game;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import static game.Button.*;

/**
 * displays the players's collection of items (the up to six items with him
 * in the context of a battle.
 * @author kinis
 *
 */
public class ItemBox extends Presenter
 {
	/**
	 * the computer presenter to go back to
	 */
	private Presenter oldPresenter;
	/*
	 * cursor index next to item
	 */
	private int itemCursorIndex;
	/**
	 * cursor index next to item to switch
	 */
	private int switchCursorIndex;
	/**
	 * flag for switch item
	 */
	//boolean switchFlag;
	
	final ImageIcon bottomBox = new ImageIcon("./resources/bottomBox.png");
	final ImageIcon arrow = new ImageIcon("./resources.arrow.png");
	//final ImageIcon idleArrow = new ImageIcon("./resources.idlearrow.png");
    //final ImageIcon hpBar = new ImageIcon("./resources/hpbar.png");

    
    public ItemBox(Presenter oldP){
    	oldPresenter = oldP;
    	itemCursorIndex = 0;
    	switchCursorIndex=0;
    }
    
    public void drawOn(Graphics2D g){
    	
    	g.setColor(Color.WHITE);
		g.fillRect(0,0,320,288);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,20));
		
		
		int inc=32;
		
		g.drawImage(bottomBox.getImage(), 0, 192, null);
		g.drawString("Choose an Item .", 32, 16+192+16+16);

		int lp=0;
		for (lp = 0; lp < player().pack().getAllItems().size(); lp++)
		{
			Item item=null;
		
//			item = player().pack().getAllItems().toArray();
			//currentItem = item[lp];
		
			g.drawString( item.getName(), 48+8, 16+12+lp*inc);

		}
		g.drawString("Cancel", 12, 16+12+lp*inc +16);

		
	}

	public void step(int ms) { }

	public void buttonPressed(Button b)
	{
		if(b==START)
		{ 
			enterPresenter(oldPresenter);
		}
		if(b==A)
		{
			if (itemCursorIndex == player().pack().getAllItems().size()) { enterPresenter(oldPresenter); }
			if (switchCursorIndex == player().pack().getAllItems().size()) { enterPresenter(oldPresenter); }
		}
		if(b==DOWN)
		{
			if (itemCursorIndex < player().pack().getAllItems().size()) { itemCursorIndex++; }
		}
		if(b==UP)
		{
			if (itemCursorIndex > 0) { itemCursorIndex--; }
		}
		if(b==A)
		{
			if (itemCursorIndex == player().pack().getAllItems().size()) { enterPresenter(oldPresenter); }
			else
			{
				String choice = showMenu(new String[] { "Use", "Toss", "Cancel" });

				if ("Use".equals(choice)) { /*enter stats presenter*/ }
				if ("Toss".equals(choice)) { /* toss*/}
				if ("Cancel".equals(choice)) { enterPresenter(oldPresenter); }
			}
		}
	}
}
