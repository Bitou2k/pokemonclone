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
	
	void drawOn(Graphics2D g)
	{
		int size = 20;
		
		g.setColor(Color.BLUE);
		g.translate(20*x,20*y);
			g.fillRect(3,3,17,17);	
			if(entity!=null) entity.drawOn(g);
		
		g.translate(-20*x,-20*y);
	}
}