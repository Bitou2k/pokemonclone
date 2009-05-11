package game;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

//you
class Player extends Battler {
	String name;
	List<Pokemon> party;
	List<Item> pack;
	Map<Species,Boolean> seenIt = new HashMap<Species,Boolean>();
	Map<Species,Boolean> caughtIt = new HashMap<Species,Boolean>();


	Direction d  = Direction.NORTH;
	boolean inStride = false;
	boolean isOne = false;
	
	ImageIcon ii = new ImageIcon("./entityImages/Player Front.png");
	
	final ImageIcon imgDown = new ImageIcon("./entityImages/Player Front.png");
	final ImageIcon imgDownStrideTwo = new ImageIcon("./entityImages/Player Front StrideTwo.png");
	final ImageIcon imgDownStrideOne = new ImageIcon("./entityImages/Player Front StrideOne.png");
	final ImageIcon imgRight = new ImageIcon("./entityImages/Player Right.png");
	final ImageIcon imgRightStride = new ImageIcon("./entityImages/Player Right Stide.png");
	final ImageIcon imgLeft = new ImageIcon("./entityImages/Player Left.png");
	final ImageIcon imgLeftStride = new ImageIcon("./entityImages/Player Left Stride.png");
	final ImageIcon imgUp = new ImageIcon("./entityImages/Player Up.png");
	final ImageIcon imgUpStrideTwo = new ImageIcon("./entityImages/Player Up StrideTwo.png");
	final ImageIcon imgUpStrideOne = new ImageIcon("./entityImages/Player Up StrideOne.png");


	
	Player()
	{
	//CHANGE THIS --we've seen all of the ones we've added--we've caught every other one
		boolean seen = true;
		List<Species> p = Species.all();
		for (int i = 0; i < p.size(); i++){
			seenIt.put(p.get(i),true);
			caughtIt.put(p.get(i),seen);
			seen = !seen;
		}
	}

	void step()
	{
		if(inStride && isOne)
		{
			isOne=false;
		}
		else if(inStride)
		{
			inStride = false;
		}
	}

	void drawOn(Graphics2D g){
		switch (d)
		{
			case NORTH:
				if (inStride)
				{
					if (isOne)
						ii = imgUpStrideOne;
					else
						ii = imgUpStrideTwo;
					isOne = !isOne;
				}
				else
					ii = imgDown;
				break;
			case SOUTH:
				if (inStride)
				{
					if (isOne)
						ii = imgDownStrideOne;
					else
						ii = imgDownStrideTwo;
					isOne = !isOne;
				}
				else
					ii = imgDown;
				break;
			case EAST:
				if (inStride)
				{
					if (isOne)
						ii = imgRightStride;
					else
						ii = imgRight;
					isOne = !isOne;
				}
				else
					ii = imgRight;
				break;
			case WEST:
				if (inStride)
				{
					if (isOne)
						ii = imgLeftStride;
					else
						ii = imgLeft;
					isOne = !isOne;
				}
				else
					ii = imgLeft;
				break;
		}

		if(inStride)
			g.drawImage(ii.getImage(), d.dx*-8, d.dy*-8, null);
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
//	public Map getSeenIt(){
//		Map<PokedexPokemon,Boolean> returnMap;
//		seenIt.putAll(returnMap);
//		return returnMap;
//	}
}