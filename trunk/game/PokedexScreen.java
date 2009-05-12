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
	//ArrayList<String> pokedexString = new ArrayList<String>();
	
	
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
		
		ArrayList<Species> speciesList = new ArrayList<Species>();
		for (int x = 0; x < Species.all().size(); x++){
			speciesList.add(0,Species.all().get(x));
		}
		for (int i = 0; i < 6; i++){	
			
			Species s = speciesList.get((i + topIndex) % (speciesList.size()));
			
			
			
			g.drawString( player().pokedex.hasSeen(s) ? s.name() : "----------" , X,Y );
			if ( player().pokedex.hasCaught(s) )
				g.drawImage(pokeball.getImage(),X-20, Y - 15, null);
			Y += inc;
		}
		
		g.drawString( ""+player().pokedex.allSeen().size() , 270, 75 );
		g.drawString( ""+player().pokedex.allCaught().size() , 270, 75+50 );
		
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
	
	public void step(int ms){}
	

	private String padLeft(String str,String fill){
		if (str.length() < 3)
			str = fill + str;
		if (str.length() < 3)
			return padLeft(str,fill);
		else
			return str;
	}


	public void buttonPressed(Button b){
		if (b==Button.START)	enterPresenter(oldPresenter);
		else if (b==Button.DOWN){
			if(pkmn){
				if (pkmnCursorIndex != 5){ pkmnCursorIndex++;}
				else {
					topIndex++;
					if (topIndex == Species.all().size()) topIndex = 0;
				}
			}
			else{
				if (menuCursorIndex != 5) menuCursorIndex++;
			}
		}
		else if (b==Button.UP){
			if(pkmn){
				if (pkmnCursorIndex != 0){ pkmnCursorIndex--;}
				else 
				{
					topIndex--;
					if (topIndex == -1) topIndex = Species.all().size() - 1;
				}
			}
			else{
				if (menuCursorIndex != 0) menuCursorIndex--;
			}
		}
		else if (b==Button.LEFT || b==Button.RIGHT) pkmn = !pkmn;
	}
}