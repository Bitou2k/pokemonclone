package game;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *A grid of Tiles in the game, also known as a map.
 */
class Area extends Presenter {

	private java.util.List<Tile> tiles = new ArrayList<Tile>();
	Player player; 
	private String name;
	
	private Area(File f) throws Exception
	{
		Node mapNode = Node.parseFrom(new FileInputStream(f));
		name = mapNode.contentOf("name");
		
		for(Node tileNode: mapNode.subnodes("tile"))
		{
			tiles.add(Tile.fromNode(tileNode, this));
		}
		if(tiles.size()==0) throw new Exception();

		System.out.println("Loaded Area: "+name);
	}
	
	void player(Player p){player=p;}
	
	/**
	 *Find the tile with the given coordinated
	 */
	public Tile tileAt(int x, int y)
	{
		for(Tile t: tiles)
			if(t.x==x && t.y==y) return t;
		return null;
	}
	
	private void move(int dx, int dy)
	{
		Tile now = player.tile();
		Tile next = tileAt(now.x+dx,now.y+dy);
		
		if(next.isObstacle()) return;
		if(next.isDoor())
		{
			System.out.println(next.target);
			System.out.println(next.targetX());
			System.out.println(next.targetY());
		
			Area a = Area.named(next.targetMap());
			a.player(player);
			a.tileAt(next.targetX(),next.targetY()).entity(player);
			enterPresenter(a);
			return;

		}
		if(next.isGrass())
		{
			Pokemon p = now.genPokemon();
			if(p!=null)
			{
				//open battle
				enterPresenter(new Battle(p,this));
			}
		}
		
		player.inStride=true;
		player.isOne=true;
		now.entity(null);
		next.entity(player);
	}
	
	/**
	 *Move player on arrow keys, enter pokedex on Q.
	 */
	public void keyPressed(char key){
		if(key=='A'){move(-1,0);player.setDirection(Direction.WEST);}
		if(key=='S'){move(0,1); player.setDirection(Direction.SOUTH);}
		if(key=='D'){move(1,0); player.setDirection(Direction.EAST);}
		if(key=='W'){move(0,-1); player.setDirection(Direction.NORTH);}
		if(key=='Q'){enterPresenter(new PokedexScreen(this));}
	}
	
	/**
	 *Draw black background, center the player, then draw each tile.
	 */
	public void drawOn(Graphics2D g)
	{
		//fill background with black
		g.setColor(Color.BLACK);
		g.fillRect(0,0,16*20+2,16*18+2);
		
		//make the player the center
		g.translate( (player.tile().x-10)*-16, (player.tile().y-10)*-16);

		//draw each tile
		for(Tile t:tiles) t.drawOn(g);
	}
	
	/**
	 *Step each tile
	 */
	public void step(){
		for(Tile t:tiles)
			t.step();
	}
	
	
	/**
	 *Returns a loaded map with the given name, only maps in the root directory will be loaded.
	 */
	public static Area named(String name){
		for(Area a: areas)
			if(a.name.equalsIgnoreCase(name))
				return a;
		System.out.println("There is no area named "+name);
		return null;
	}
	private static LinkedList<Area> areas = new LinkedList<Area>();
	static{
		try	{
			for(File f: new File("./").listFiles() ){
				try{
					areas.add(new Area(f));
				}catch(Exception exx){}
			}
		}catch(Exception ex){}	
	}
}