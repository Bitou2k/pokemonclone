package game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *A grid of Tiles in the game, also known as a map.
 */
class Area extends Presenter {

	private List<Tile> tiles = new ArrayList<Tile>(); 
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
		Player player = player();
		
		Tile now = player.tile();
		Tile next = tileAt(now.x+dx,now.y+dy);
		
		if(next.isObstacle()) return;
		if(next.isDoor())
		{
			System.out.println(next.target);
			System.out.println(next.targetX());
			System.out.println(next.targetY());
		
			Area a = Area.named(next.targetMap());
			a.tileAt(next.targetX(),next.targetY()).entity(player);
			
			enterPresenter(a);
			a.showMessage(a.name);
			
			
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
		
		player.stride=2;
		now.entity(null);
		next.entity(player);
	}
	
	/**
	 *Move player on arrow keys, enter pokedex on Q.
	 */
	public void buttonPressed(Button b){
		Player player = player();
		if(player.inStride())return;
		
		if(b==Button.LEFT){move(-1,0);player.setDirection(Direction.WEST);}
		if(b==Button.DOWN){move(0,1); player.setDirection(Direction.SOUTH);}
		if(b==Button.RIGHT){move(1,0); player.setDirection(Direction.EAST);}
		if(b==Button.UP){move(0,-1); player.setDirection(Direction.NORTH);}
		if(b==Button.START){enterPresenter(new PokedexScreen(this));}
		if(b==Button.A)
		{
			System.out.println("x");
			showMessage("This is a test of the generic message presenter.  Any key will advance the message and at end force control to the next presenter.");
			System.out.println("y");
		}
	}
	
	/**
	 *Draw black background, center the player, then draw each tile.
	 */
	public void drawOn(Graphics2D g)
	{
		Player player = player();
		
		//fill background with black
		g.setColor(Color.BLACK);
		g.fillRect(0,0,16*20+2,16*18+2);
		
		
		//make the player the center
		int yf = (player.d==Direction.NORTH || player.d==Direction.SOUTH && player.inStride() ? -8 : 0);
		int xf = (player.d==Direction.EAST || player.d==Direction.WEST && player.inStride() ? 8 : 0);
		
		g.translate( (player.tile().x-10)*-16-xf, (player.tile().y-10)*-16-yf);

		//draw each tile
		for(Tile t:tiles) t.drawSelfOn(g);
		for(Tile t:tiles) t.drawEntityOn(g);
		
		g.translate( -((player.tile().x-10)*-16-xf), -((player.tile().y-10)*-16-yf));
	}
	
	/**
	 *Step each tile
	 */
	public void step(int ms){
		for(Tile t:tiles)
			t.step(ms);
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