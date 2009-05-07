package game;
import java.util.*;
import java.awt.*;
import javax.swing.*;

//you
class Player extends Battler {
	String name;
	java.util.List<Pokemon> party;
	java.util.List<Item> pack;
	Map<Pokemon,Boolean> seenIt;
	Map<Pokemon,Boolean> caughtIt;
	
	ImageIcon ii = new ImageIcon("./entityImages/Player Front.png");
	
	final ImageIcon imgDown = new ImageIcon("./entityImages/Player Front.png");
	final ImageIcon imgRight = new ImageIcon("./entityImages/Player Right.png");
	final ImageIcon imgLeft = new ImageIcon("./entityImages/Player Left.png");
	final ImageIcon imgUp = new ImageIcon("./entityImages/Player Up.png");
	
	Player()
	{
		
	}
	void drawOn(Graphics2D g){
	
		g.drawImage(ii.getImage(),0,0,null);
	}
	
	public void setDirection(int d){
		/*
		 * 0	1	2	3
		 * w	a	s	d
		 *u	l	r	d   up left down right
		 */
		switch(d){
		case 0:
			ii = imgUp;
			break;
		case 2:
			ii = imgDown;
			break;
		case 3:
			ii = imgRight;
			break;
		case 1:
			ii = imgLeft;
			break;
		}
	}
}