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
	* contains the strings for the pokedex
	*/
	ArrayList<String> pokedexString = new ArrayList<String>();
	int intSeen;
	int intCaught;
	final int NUMOFLINES = 7;
	
	
	final ImageIcon ii = new ImageIcon("./resources/pokedex.png");
	final ImageIcon pokeball = new ImageIcon("./resources/battle/battlepokeball.png");
	final ImageIcon arrow = new ImageIcon("./resources/arrow.png");
	final ImageIcon idleArrow = new ImageIcon("./resources/idlearrow.png");
	
	public PokedexScreen(Presenter oldP){
		oldPresenter = oldP;		
		pkmnCursorIndex = 0;
		menuCursorIndex = 0;
		pkmn = true;
		topIndex = 0;
		intSeen = 0;intCaught = 0;
		
	}
	
	//I shouldn't do this here but for now
	public void initGame(Game g)
	{
		super.initGame(g);
		
		//set the pokedex strings
		for (Species s: Species.all()){

			System.out.println(s.name());
			if (player().pokedex.hasSeen(s) == true){
				pokedexString.add(s.name());
			}
			else{
				pokedexString.add(0,"----------");
			}
			
			if((Boolean)ash.caughtIt.get(p)) intCaught++;
		}
		
	}
	
	public void drawOn(Graphics2D g){
		g.drawImage(ii.getImage(),0,0,null);
		//trying to find right placement for names
		int Y = 75;
		int inc = 30; //increment for y
		int X = 50;
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,20));
		//drawString (str, x, y)
		for (int i = 0; i < NUMOFLINES; i++){			
			g.drawString(pokedexString.get((i + topIndex) % pokedexString.size()),X,Y);
			if (caughtList.get((i + topIndex) % pokedexString.size()))
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
		g.drawString(padLeft(Integer.toString(intSeen),"0"),265,75);
		g.drawString(padLeft(Integer.toString(intCaught),"0"),265,120);
	}
	
	public void step(int ms){}
	
	private String padLeft(String str,String fill){
		if (str.length() < 3)
			str = fill + str;
		if (str.length() < 3)
			return padLeft(str,fill);
		else
			return str;
	}
	
	public void keyPressed(char key){
		if (key == 'Q')	enterPresenter(oldPresenter);
		else if (key == 'S'){
			if(pkmn){
				if (pkmnCursorIndex != NUMOFLINES-1){ pkmnCursorIndex++;}
				else {
					topIndex++;
					if (topIndex == pokedexString.size()) topIndex = 0;
				}
			}
			else{
				if (menuCursorIndex != 5) menuCursorIndex++;
			}
		}
		else if (b==Button.UP){
			if(pkmn){
				if (pkmnCursorIndex != 0){ pkmnCursorIndex--;}
				else {
					topIndex--;
					if (topIndex == -1) topIndex = pokedexString.size() - 1;
				}
			}
			else{
				if (menuCursorIndex != 0) menuCursorIndex--;
			}
		}
		else if (b==Button.LEFT || b==Button.RIGHT) pkmn = !pkmn;
	}
}