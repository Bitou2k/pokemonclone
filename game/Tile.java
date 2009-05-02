package game;

import java.awt.*;

//one square on a map
class Tile {

	enum TileType { GROUND, BARRIER, DOOR, WATER, TALLGRASS } //and more

	int x,y;
	TileType type;
	Image img;
	Entity entity;
	
	Tile(int x, int y)
	{
		this(x,y,TileType.GROUND);
	}
	Tile(int x, int y, TileType type)
	{
		this.x=x;this.y=y;this.type=type;
	}
	
	void entity(Entity e) {entity=e; if(e!=null)e.tile(this);}
	Entity entity() {return entity;}
	
	int width(){return 16;}
	int height(){return 16;}
	
	void drawOn(Graphics2D g)
	{	
		g.setColor(Color.BLUE);
		g.translate(width()*x,height()*y);
			g.fillRect(2,2,14,14);	
			if(entity!=null) entity.drawOn(g);
		
		g.translate(-width()*x,-height()*y);
	}
}