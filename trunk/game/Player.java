package game;
import java.util.*;
import java.awt.*;
import javax.swing.*;

//you
class Player extends Battler {
	String name;
	java.util.List<Pokemon> party;
	java.util.List<Item> pack;
	Map<PokedexPokemon,Boolean> seenIt;
	Map<Pokemon,Boolean> caughtIt;


	Direction d  = Direction.NORTH;
	boolean inStride = false;
	
	ImageIcon ii = new ImageIcon("./entityImages/Player Front.png");
	
	final ImageIcon imgDown = new ImageIcon("./entityImages/Player Front.png");
	final ImageIcon imgRight = new ImageIcon("./entityImages/Player Right.png");
	final ImageIcon imgLeft = new ImageIcon("./entityImages/Player Left.png");
	final ImageIcon imgUp = new ImageIcon("./entityImages/Player Up.png");
	final ImageIcon imgDownStride = new ImageIcon("./entityImages/Player Front Stride.png");
	final ImageIcon imgRightStride = new ImageIcon("./entityImages/Player Right Stide.png");
	final ImageIcon imgLeftStride = new ImageIcon("./entityImages/Player Left Stride.png");
	final ImageIcon imgUpStride = new ImageIcon("./entityImages/Player Up Stride.png");
	
	Player()
	{
		/*java.util.List<PokedexPokemon> pokeList = PokedexPokemon.all();
		boolean seen = true;
		for (PokedexPokemon p : pokeList){
			seenIt.put(p,seen);
			seen = !seen;
		}*/
	}

	void step()
	{
		inStride = false;
	}
	void drawWalk(Graphics2D g, int dx, int dy)
	{
		g.drawImage(ii.getImage(), dx * 4, dy * 4, null);
		//shell.repaint();
		g.drawImage(ii.getImage(), dx * 8, dy * 8, null);
		//shell.repaint();
		g.drawImage(ii.getImage(), dx * 12, 3 * dy * 12, null);
		//shell.repaint();
	}

	void drawOn(Graphics2D g){
		switch (d)
		{
			case NORTH:
				ii = (inStride ? imgUpStride : imgUp);
				break;
			case SOUTH:
				ii = (inStride ? imgDownStride : imgDown);
				break;
			case EAST:
				ii = (inStride ? imgRightStride : imgRight);
				break;
			case WEST:
				ii = (inStride ? imgLeftStride : imgLeft);
				break;
		}

		if(inStride)
			g.drawImage(ii.getImage(), d.dx*8, d.dy*8, null);
		else
			g.drawImage(ii.getImage(),0,0,null);
	}
	
	public void setDirection(Direction d){
		/*
		 * 0	1	2	3
		 * w	a	s	d
		 *u	l	r	d   up left down right
		 */

		this.d = d;
	}
	
}