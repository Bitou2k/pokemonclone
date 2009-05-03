package game;

import java.awt.*;
import java.io.*;
import javax.swing.*;

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
	
	void imageFrom(String s)
	{
		img = new ImageIcon("./tileImages/" + s + ".png").getImage();
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
			g.drawImage(img,0,0,null);
			if(entity!=null) entity.drawOn(g);
		
		g.translate(-width()*x,-height()*y);
	}
}