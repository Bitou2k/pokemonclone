package game;

import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

class PokedexPokemonPresenter extends Presenter {

	private Pokemon p;
	
	PokedexPokemonPresenter(Pokemon p)
	{
		this.p=p;
	}
	
	synchronized void drawOn(Graphics2D g){	
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,240);
		
		p.image.paintIcon(null,g,0,0);
		g.setColor(Color.BLACK);
		g.drawString(""+p.pokedexNumber,0,50);
		g.drawString(p.name,0,60);
		g.drawString(p.description,0,70);
	}
	
	void keyPressed(char key){}
	synchronized void step(){}
}