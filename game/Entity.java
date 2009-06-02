package game;

import java.awt.*;

/**
*Something interactive that may occupy a Tile, such as a citizen, rival trainer, the player, or a pickup item.
*/
public class Entity
{

	protected String id;
	private Tile tile;
	
	public void tile(Tile t)
	{
		if(tile!=null)tile.entity(null);
		tile=t;
	}
	public Tile tile(){return tile;}
	
	public void drawOn(Graphics2D g)
	{
		g.setColor(Color.RED);
		g.fillOval(tile.width()/2-2,tile.height()/2-2,4,4);
	}
	public void step(int ms){}
	
	public String id(){ return id; }
}