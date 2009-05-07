package game;

import java.awt.*;
import javax.swing.*;

public class PokedexScreen extends Presenter{
	/*
	*TODO
	*
	*/
	Presenter oldPresenter;
	
	final ImageIcon ii = new ImageIcon("./resources/pokedex.png");
	
	public PokedexScreen(Presenter oldP){
		oldPresenter = oldP;		
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
	}
	
	public void step(){}
	
	public void keyPressed(char key){
		if (key == 'Q')	enterPresenter(oldPresenter);
	}

}