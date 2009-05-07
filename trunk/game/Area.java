package game;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *A grid of Tiles in the game, also known as a map.
 */
class Area extends Presenter {

	private static Map<String,Area> areas = new HashMap<String,Area>();
	
	static
	{
		try
		{
		for(File f: new File("./").listFiles() )
		{
			try
			{

				Area a = new Area(f);
				areas.put(a.name,a);
				
			}
			catch(Exception exx)
			{
			}
		}
		}
		catch(Exception ex)
		{
		}
		
	}
	
	/**
	 *Returns a loaded map with the given name, only maps in the root directory will be loaded.
	 */
	public static Area named(String x)
	{
		return areas.get(x);
	}

	java.util.List<Tile> tiles = new ArrayList<Tile>();
	java.util.List<Entity> entities = new ArrayList<Entity>();
	Player player = new Player();  
	WildPokemonGenerator gen; //for grassy areas
	String name;
	
	Area(File f) throws Exception
	{
		Node mapNode = Node.parseFrom(new FileInputStream(f));
		name = mapNode.contentOf("name");
		
		for(Node tileNode: mapNode.subnodes("tile"))
		{
			tiles.add(Tile.fromNode(tileNode));
		}
		if(tiles.size()==0) throw new Exception();
				
	}
	
	void playerAt(int x,int y)
	{
		Tile now = player.tile();
		if(now!=null) now.entity(null);
		tile(x,y).entity(player);
	}
	
	Tile tile(int x, int y)
	{
		for(Tile t: tiles)
			if(t.x==x && t.y==y) return t;
		return null;
	}
	
	void move(int dx, int dy)
	{
		Tile now = player.tile();
		Tile next = tile(now.x+dx,now.y+dy);
		
		if(next.isObstacle()) return;
		
		if(next.isDoor())
		{
		System.out.println(next.target);
		System.out.println(next.targetX());
		System.out.println(next.targetY());
		
			Area a = Area.named(next.targetMap());
			a.playerAt(next.targetX(),next.targetY());
			enterPresenter(a);
			return;
		}
			
		now.entity(null);
		next.entity(player);
	}
	
	void drawOn(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0,0,16*20,16*18);
		
		g.translate( (player.tile().x-10)*-16, (player.tile().y-10)*-16);
		
		for(Tile t:tiles) t.drawOn(g);
	}
	
	void keyPressed(char key){
		if(key=='A'){move(-1,0);player.setDirection(1);}
		if(key=='S'){move(0,1); player.setDirection(2);}
		if(key=='D'){move(1,0); player.setDirection(3);}
		if(key=='W'){move(0,-1); player.setDirection(0);}
		if(key=='Q'){enterPresenter(new PokedexScreen(this));}
	}
	void step(){}
}