package game;

import java.awt.*;
import java.io.*;
import javax.swing.*;

//one square on a map
class Tile {

	int x,y;
	String type;
	String target;
	Image img;
	Entity entity;
	
	Tile(int x, int y)
	{
		this.x=x; this.y=y;
	}
	
	static Tile fromNode(Node tileNode)
	{
		int x = new Integer( tileNode.contentOf("x"));
		int y = new Integer( tileNode.contentOf("y"));
		Tile t = new Tile(x,y);
		t.imageFrom(tileNode.contentOf("image"));
		t.type = (tileNode.contentOf("type"));
		t.target = (tileNode.contentOf("target"));
		return t;
	}
	
	boolean isObstacle(){ return type.equals("obstacle");}
	boolean isDoor(){ return type.equals("door")&& !target.equals("");}
	
	String targetMap()
	{
		return target.substring(0,target.indexOf(":"));
	}
	int targetX()
	{
		return new Integer(target.substring(target.indexOf(":")+1,target.indexOf(",")));
	}
	int targetY()
	{
		return new Integer(target.substring(target.indexOf(",")+1));
	}
	
	void imageFrom(String s)
	{
		img = new ImageIcon("./tileImages/" + s).getImage();
	}
	
	void entity(Entity e) {entity=e; if(e!=null)e.tile(this);}
	Entity entity() {return entity;}
	
	int width(){return 16;}
	int height(){return 16;}
	
	void drawOn(Graphics2D g)
	{	
		g.setColor(Color.BLACK);
		g.translate(width()*x,height()*y);
			g.fillRect(2,2,14,14);
			g.drawImage(img,0,0,null);
			if(entity!=null) entity.drawOn(g);
		
		g.translate(-width()*x,-height()*y);
	}
}