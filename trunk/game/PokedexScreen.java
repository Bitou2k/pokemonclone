package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PokedexScreen extends Presenter{
	/*
	*TODO
	*
	*/
	Presenter oldPresenter;
	int pkmnCursorIndex;
	int menuCursorIndex;
	boolean pkmn;
	
	final ImageIcon ii = new ImageIcon("./resources/pokedex.png");
	final ImageIcon arrow = new ImageIcon("./resources/arrow.png");
	final ImageIcon idleArrow = new ImageIcon("./resources/idlearrow.png");
	
	public PokedexScreen(Presenter oldP){
		oldPresenter = oldP;		
		pkmnCursorIndex = 0;
		menuCursorIndex = 0;
		pkmn = true;
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