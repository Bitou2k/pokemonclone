package game;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
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
	int topIndex;
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
	ArrayList<Boolean> caughtList = new ArrayList<Boolean>();
	
	
	final ImageIcon ii = new ImageIcon("./resources/pokedex.png");
	final ImageIcon pokeball = new ImageIcon("./resources/battle/battlepokeball.png");
	final ImageIcon arrow = new ImageIcon("./resources/arrow.png");
	final ImageIcon idleArrow = new ImageIcon("./resources/idlearrow.png");
	
	public PokedexScreen(Presenter oldP){
		oldPresenter = oldP;		
		pkmnCursorIndex = 0;
		menuCursorIndex = 0;
		pkmn = true;
		ash = ((Area)oldP).player;
		topIndex = 0;
		
		//set the pokedex strings
		List<Species> pokeList = Species.all();
		
		for (int i = 0; i < pokeList.size(); i++){
			Species p = pokeList.get(i);
			System.out.println(p.name());
			if (ash.seenIt.get(p) == true){
				pokedexString.add(p.name());
			}
			else{
				pokedexString.add("----------");
			}
			caughtList.add((Boolean)ash.caughtIt.get(p));
		}
		
		
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
		for (int i = 0; i < 6; i++){			
			g.drawString(pokedexString.get((pokedexString.size() - 1 - i - topIndex) % pokedexString.size()),X,Y);
			if (caughtList.get((pokedexString.size() - 1 - i - topIndex) % pokedexString.size()))
				g.drawImage(pokeball.getImage(),X-20, Y - 15, null);
			Y += inc;
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
		g.drawImage(pkmnArrow.getImage(), X - 40, 60 + pkmnCursorIndex * inc, null);
		g.drawImage(menuArrow.getImage(), 240, 158 + menuCursorIndex * 31, null);
	}
	
	public void step(){}
	
	public void keyPressed(char key){
		if (key == 'Q')	enterPresenter(oldPresenter);
		else if (key == 'S'){
			if(pkmn){
				if (pkmnCursorIndex != 5){ pkmnCursorIndex++;}
				else {topIndex++;}
			}
			else{
				if (menuCursorIndex != 5) menuCursorIndex++;
			}
		}
		else if (key == 'W'){
			if(pkmn){
				if (pkmnCursorIndex != 0){ pkmnCursorIndex--;}
				else {topIndex--;}
			}
			else{
				if (menuCursorIndex != 0) menuCursorIndex--;
			}
		}
		else if (key == 'A' || key == 'D') pkmn = !pkmn;
	}
}