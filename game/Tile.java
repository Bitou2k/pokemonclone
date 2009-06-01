package game;

import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 *I am one square in an Area; I have a image, type and target; I might contain an Entity.
 */
class Tile {

	//private 
	int x,y;
	private String type;
	//private
	String target;
	private Image img;
	private Entity entity;
	private Area a;
	
	Tile(int x, int y, Area a)
	{
		this.x=x; this.y=y; this.a=a;
	}
	
	static Tile fromNode(Node tileNode, Area a)
	{
		int x = new Integer( tileNode.contentOf("x"));
		int y = new Integer( tileNode.contentOf("y"));
		Tile t = new Tile(x,y,a);
		t.imageFrom(tileNode.contentOf("image"));
		t.type = (tileNode.contentOf("type"));
		t.target = (tileNode.contentOf("target"));
		return t;
	}
	
	public boolean isObstacle(){ return type.equals("obstacle") || type.equals("water");}
	public boolean isWater(){ return type.equals("water");}
	
	public boolean isDoor(){ return type.equals("door")&& !target.equals("");}
	public boolean isGrass(){ return type.equals("pokegrassOrCave"); }
	public boolean isCliff() { return type.equals("cliff"); }
	
	public Pokemon genPokemon(){
		try
		{
			return EncounterSet.named(target).generatePokemon();
		}
		catch(Exception ex){}
		return null;
	}
	
	public Direction cliffDirection()
	{
		String x = target.toUpperCase();
		if(x.equals("N")||x.equals("NORTH"))return Direction.NORTH;
		if(x.equals("S")||x.equals("SOUTH"))return Direction.SOUTH;
		if(x.equals("E")||x.equals("EAST"))return Direction.EAST;
		if(x.equals("W")||x.equals("WEST"))return Direction.WEST;
		return null;
	}
	public String targetMap()
	{
		return target.substring(0,target.indexOf(":"));
	}
	public int targetX()
	{
		return new Integer(target.substring(target.indexOf(":")+1,target.indexOf(",")));
	}
	public int targetY()
	{
		return new Integer(target.substring(target.indexOf(",")+1));
	}
	
	void imageFrom(String s)
	{
		img = new ImageIcon("./tileImages/" + s).getImage();
	}
	
	void entity(Entity e) {
		entity=e; 
		if(e!=null)
		{
			e.tile(this);
		}
	}
	Entity entity() {return entity;}
	
	/**
	*16
	*/
	public int width(){return 16;}
	/**
	*16
	*/
	public int height(){return 16;}
	
	/**
	 *Draw my background image.
	 */
	public void drawSelfOn(Graphics2D g)
	{	
		g.setColor(Color.BLACK);
		g.translate(width()*x,height()*y);
			g.fillRect(0,0,width(),height());
			g.drawImage(img,0,0,null);
			if(entity!=null) entity.drawOn(g);
		g.translate(-width()*x,-height()*y);
	}
	
	public void drawEntityOn(Graphics2D g)
	{	
		g.setColor(Color.BLACK);
		g.translate(width()*x,height()*y);
			if(entity!=null) entity.drawOn(g);
		g.translate(-width()*x,-height()*y);
	}
	
	/**
	 *Step my entity, if any.
	 */
	public void step(int ms)
	{
		if(entity!=null) entity.step(ms);
	}
}