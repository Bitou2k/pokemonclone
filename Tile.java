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
	
	void drawOn(Graphics2D g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(x*10+2,y*10+2,x*10+8,y*10+8);	
	}
}