package game;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

public class PokemonBox extends Presenter {
	
	/**
	* the presenter to go back to
	*/
	Presenter oldPresenter;
	/**
	*  cursor index next to the pokemon
	*/
	int pkmnCursorIndex;
	
	final ImageIcon bottomBox = new ImageIcon("./resources/bottomBox.png");
	final ImageIcon arrow = new ImageIcon("./resources/arrow.png");
	final ImageIcon idleArrow = new ImageIcon("./resources/idlearrow.png");

	public PokemonBox(Presenter oldP){
		oldPresenter = oldP;		
		pkmnCursorIndex = 0;
		
		//if (player().party()==null)
		//{
		//	player().party().add(new Pokemon(Species.named("Ditto"), 5));
		//}
	}
	
	public void drawOn(Graphics2D g){
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,288);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,16));
		
		int inc=32;
		
		g.drawImage(bottomBox.getImage(), 0, 192, null);
		g.drawString("Choose a POKeMON.", 32, 16+192+16);

		for (int lp=0; lp<player().party().size(); lp++)
		{
			Pokemon pokemon;
			pokemon = player().party().get(lp);
		
			g.drawString( pokemon.nickname(), 48, 16+lp*inc);
			g.drawString( ":L"+pokemon.level(),208,16+lp*inc);
			g.drawString( pokemon.currentHp() + "/" + pokemon.baseHp(), 208, 32+(lp*inc));
			
		}
		
		
		g.drawImage(arrow.getImage(), 0, 16+ pkmnCursorIndex* 32, null);
		
	}
	
	public void step(int ms){}
	
	public void buttonPressed(Button b){
		if (b==Button.START) { enterPresenter(oldPresenter); }
		else if (b==Button.DOWN){
			
			if (pkmnCursorIndex < player().party().size()-1) { pkmnCursorIndex++; }
		
		}
		else if (b==Button.UP){
			if (pkmnCursorIndex > 0) { pkmnCursorIndex--; }
			
		}
		
	}

}
