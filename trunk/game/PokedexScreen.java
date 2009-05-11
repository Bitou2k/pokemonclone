package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class PokedexScreen extends Presenter{
	/*
	*TODO
	*
	*/
	
	/**
	* the presenter to go back to
	*/
	Presenter oldPresenter;
	/**
	*  cursor index next to the pokemon
	*/
	int pkmnCursorIndex;
	/**
	* cursor index next to the menu
	*/
	int menuCursorIndex;
	/**
	* true: pokemon list has focus, false: menu has focus
	*/
	boolean pkmn; 
	/**
	* current player instance
	*/
	Player ash;
	/**
	* contains the strings for the pokedex
	*/
	ArrayList<String> pokedexString = new ArrayList<String>();
	
	
	final ImageIcon ii = new ImageIcon("./resources/pokedex.png");
	final ImageIcon arrow = new ImageIcon("./resources/arrow.png");
	final ImageIcon idleArrow = new ImageIcon("./resources/idlearrow.png");
	
	public PokedexScreen(Presenter oldP){
		oldPresenter = oldP;		
		pkmnCursorIndex = 0;
		menuCursorIndex = 0;
		pkmn = true;
		ash = ((Area)oldP).player;
		
		/*set the pokedex strings
		Map<PokedexPokemon,Boolean> seenIt = ash.getSeenIt();
		java.util.List<PokedexPokemon> pokeList = PokedexPokemon.all();
		for (PokedexPokemon p : pokeList){
			if (seenIt.get(p)){
				pokedexString.add(p.name());
			}
			else{
				pokedexString.add("----------");
			}
		}*/
		
	}
	
	public void drawOn(Graphics2D g){
		g.drawImage(ii.getImage(),0,0,null);
		//trying to find right placement for names
		int Y = 75;
		int inc = 40; //increment for y
		int X = 50;
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,25));
		//drawString (str, x, y)
		g.drawString("AndrewSieg",X,Y);
		Y += inc;
		g.drawString("RyanMacnak",X,Y);
		for (int i = 0; i < 4; i++){
			Y += inc;
			g.drawString("----------",X,Y);
		}
		ImageIcon pkmnArrow,menuArrow;
		if (pkmn){
			pkmnArrow = arrow;
			menuArrow = idleArrow;
		}
		else{
			pkmnArrow = idleArrow;
			menuArrow = arrow;
		}
		g.drawImage(pkmnArrow.getImage(), X - 20, 60 + pkmnCursorIndex * inc, null);
		g.drawImage(menuArrow.getImage(), 240, 158 + menuCursorIndex * 31, null);
	}
	
	public void step(){}
	
	public void keyPressed(char key){
		if (key == 'Q')	enterPresenter(oldPresenter);
		else if (key == 'S'){
			if(pkmn){
				if (pkmnCursorIndex != 5) pkmnCursorIndex++;
			}
			else{
				if (menuCursorIndex != 5) menuCursorIndex++;
			}
		}
		else if (key == 'W'){
			if(pkmn){
				if (pkmnCursorIndex != 0) pkmnCursorIndex--;
			}
			else{
				if (menuCursorIndex != 0) menuCursorIndex--;
			}
		}
		else if (key == 'A' || key == 'D') pkmn = !pkmn;
	}
}