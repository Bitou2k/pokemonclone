package game;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

/**
*Displays the player's party (the up to six pokemon with him) in the context of a battle. 
*/
public class PokemonBox extends Presenter {
	
	/**
	* the presenter to go back to
	*/
	Presenter oldPresenter;
	/**
	*  cursor index next to the pokemon
	*/
	int pkmnCursorIndex;
	
	/**
	 * cursor index next to pokemon to switch
	 */
	int switchCursorIndex;
	
	/**
	 *  flag for switch pokemon
	 */
	boolean switchFlag;
	
	final ImageIcon bottomBox = new ImageIcon("./resources/bottomBox.png");
	final ImageIcon arrow = new ImageIcon("./resources/arrow.png");
	final ImageIcon idleArrow = new ImageIcon("./resources/idlearrow.png");
	final ImageIcon hpBar = new ImageIcon("./resources/hpbar.png");
	
	public PokemonBox(Presenter oldP){
		oldPresenter = oldP;		
		pkmnCursorIndex = 0;
		switchCursorIndex = 0;
		switchFlag=false;
		//if (player().party()==null)
		//{
		//	player().party().add(new Pokemon(Species.named("Ditto"), 5));
		//}
	}
	
	public void drawOn(Graphics2D g){
		
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,288);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,20));
		
		int inc=32;
		
		g.drawImage(bottomBox.getImage(), 0, 192, null);
		g.drawString("Choose a POKeMON.", 32, 16+192+16+16);

		int lp=0;
		for (lp=0; lp<player().party().size(); lp++)
		{
			Pokemon pokemon;
			pokemon = player().party().get(lp);
		
			g.drawImage( pokemon.species().image32(), 16, 8+lp*inc, null);
			g.drawString( pokemon.nickname(), 48+8, 16+12+lp*inc);
			g.drawString( ":L"+pokemon.level(),208-(16*6),16+12+16+lp*inc);
			g.drawString( pokemon.currentHp() + "/" + pokemon.baseHp(), 208+(16*2)+12, 16+12+(lp*inc));
			g.drawImage( hpBar.getImage(), 208-(16*2)-4, 32+lp*inc,null);
			g.setColor(new Color(0,184,0));
			g.fillRect(208-3, 38+lp*inc-1, 97*(pokemon.currentHp()/pokemon.baseHp()), 4);
			g.setColor(Color.BLACK);
		}
		g.drawString("Cancel", 12, 16+12+lp*inc);

		if (switchFlag)
		{
			g.drawImage(idleArrow.getImage(), 0, 16+ pkmnCursorIndex* 32, null);
			g.drawImage(arrow.getImage(), 0, 16+ switchCursorIndex* 32, null);
		}
		else
			g.drawImage(arrow.getImage(), 0, 16+ pkmnCursorIndex* 32, null);
		
	}
	
	public void step(int ms){}
	
	public void buttonPressed(Button b){
		if (b==Button.START) { enterPresenter(oldPresenter); }
		if (b==Button.A)
		{
			if (pkmnCursorIndex == player().party().size() ) { enterPresenter(oldPresenter); }
			if (switchCursorIndex == player().party().size() ) { enterPresenter(oldPresenter); }
		}
		
		if (switchFlag)
		{
			if (b==Button.DOWN)
				if (switchCursorIndex < player().party().size()) { switchCursorIndex++; }
			if (b==Button.UP)
				if (switchCursorIndex > 0) { switchCursorIndex--; }
			if (b==Button.A) 
			{
				Pokemon switcher=player().party().get(pkmnCursorIndex);
				Pokemon switched=player().party().get(switchCursorIndex);
				player().party().set(pkmnCursorIndex, switched);
				player().party().set(switchCursorIndex, switcher);
				switchFlag=false;
			}		
		}
		else
		{
			if (b==Button.DOWN)
				if (pkmnCursorIndex < player().party().size()) { pkmnCursorIndex++; }
			if (b==Button.UP)
				if (pkmnCursorIndex > 0) { pkmnCursorIndex--; }
			if (b==Button.A) {
				if (pkmnCursorIndex == player().party().size() ) { enterPresenter(oldPresenter); }
				else
				{
					String choice = showMenu( new String[]{ "Stats", "Switch", "Cancel" } );
				
					if ("Stats".equals(choice)) { /*enter stats presenter*/ }
					if ("Switch".equals(choice)) { switchFlag = true; }
					if ("Cancel".equals(choice)) { /*do nothing*/ }
				}
			}
		}
	}
}
