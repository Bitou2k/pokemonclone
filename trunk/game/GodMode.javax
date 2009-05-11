package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GodMode extends Presenter {

	//private PokemonGame shell;
	
	private Presenter oldPresenter = null;
	
	public GodMode(Presenter oldP){
		oldPresenter = oldP;
	}

	public void drawOn(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,25));
		//drawString (str, x, y)
		g.drawString("HAXOR MODE",17,25);
	}
	
	public void keyPressed(char c) {
		if(c == 'E')
			enterPresenter(oldPresenter);
	}
	public void step() {}
	
}