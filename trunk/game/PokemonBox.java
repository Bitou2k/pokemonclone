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
	
	final ImageIcon arrow = new ImageIcon("./resources/arrow.png");
	final ImageIcon idleArrow = new ImageIcon("./resources/idlearrow.png");

	public PokemonBox(Presenter oldP){
		oldPresenter = oldP;		
		pkmnCursorIndex = 0;
	}
	
	public void drawOn(Graphics2D g){
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,16));
		
		int inc=16;
		for (int lp=0; lp<player().party.size(); lp++)
		{
			Pokemon pokemon;
			pokemon = player().party.get(lp);
		
			g.drawString( pokemon.nickname(), 48, lp*inc);
			g.drawString( ":L"+pokemon.getLevel(),208,lp*inc);
			g.drawString( pokemon.getCurrentHP() + "/" + pokemon.getBaseHP(), 208, (lp*inc)+16);
			
		}
		
		g.drawImage(arrow.getImage(), 0, 16+ pkmnCursorIndex* 32, null);

	}
	
	public void step(int ms){}
	
	public void buttonPressed(Button b){
		if (b==Button.START)	enterPresenter(oldPresenter);
		else if (b==Button.DOWN){
			
			if (pkmnCursorIndex <= player().party.size()) { pkmnCursorIndex++; }
		
		}
		else if (b==Button.UP){
			if (pkmnCursorIndex >= 0) { pkmnCursorIndex--; }
			
		}
		
	}

}
