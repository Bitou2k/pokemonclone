package game;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

/**
*What you see select stats from a party menu.
*/
public class StatsPresenter extends Presenter {
	
	/**
	* the presenter to go back to
	*/
	Presenter oldPresenter;
	
	Pokemon pokemon;

	final ImageIcon statsScreen = new ImageIcon("./resources/stats.png");

	
	public StatsPresenter(Pokemon p, Presenter oldP){
		oldPresenter = oldP;	
		pokemon = p;
	}
	
	public void drawOn(Graphics2D g){

		g.drawImage( pokemon.species().image80(), 16, 16, null);
		g.drawString(String.valueOf(pokemon.species().number()), 41, 116);
		
	}
	
	public void step(int ms){

	}
	
	public void buttonPressed(Button b)
	{	
	}
	
}