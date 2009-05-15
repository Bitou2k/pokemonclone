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

	public Tile tilePlayerFacing()
	{
		Direction d = player().direction();
		Tile now = player().tile();
		Tile next = tileAt(now.x+d.dx(),now.y+d.dy());
		return next;
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
	
	private void walk()
	{
		Tile now = player().tile();
		Tile next = tilePlayerFacing();
		
		if(next.isObstacle() && !next.target.isEmpty()) 
			showMessage(next.target);
		else
			{
			if(next.isCliff())
			{
				return;
			}
			if(next.isDoor())
			{
				System.out.println("Jump to "+next.target);
				Area a = Area.named(next.targetMap());
				a.tileAt(next.targetX(),next.targetY()).entity(player());
				enterPresenter(a);
				return;
			}
			if(next.isGrass())
			{
				Pokemon p = now.genPokemon();
				if(p!=null)
					enterPresenter(new Battle(p,this)); //open battle
			}
			if (!next.isObstacle())
			{
				player().stride=2;
				next.entity(player());
			}
		}
	}
	
	/**
	 *Move player on arrow keys, enter pokedex on Q.
	 */
	public void buttonPressed(Button b){
		if(player().inStride())
			return;
		if(b==Button.LEFT)
			{ player().direction(Direction.WEST); walk(); }
		if(b==Button.DOWN)
			{ player().direction(Direction.SOUTH); walk(); }
		if(b==Button.RIGHT)
			{ player().direction(Direction.EAST); walk(); }
		if(b==Button.UP)
			{ player().direction(Direction.NORTH); walk(); }
		if(b==Button.START)
			{enterPresenter(new PokedexScreen(this));}
		if(b==Button.A)
		{
			Tile next = tilePlayerFacing();
			
			if (next.isObstacle() && !next.target.isEmpty())
				showMessage(next.target);
		}
		if(b==Button.B)
		{
			String answer = showMenu("PICK ONE:",new String[]{"first","second","third"});
			showMessage(answer);
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
		for(Tile t:tiles) t.step(ms);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////
	
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
		
		new Thread(){
			public void run()
			{
				for(Tile t:tiles)
				{
					if(t.isDoor()) Area.named(t.targetMap());
				}
			}
		}.start();
	}
	
	/**
	 *Returns a loaded map with the given name, only maps in the areas directory will be loaded.
	 */
	public static synchronized Area named(String name){
		for(Area a: areas)
			if(a.name.equalsIgnoreCase(name))
				return a;
				
		try{
			Area a = new Area(new File("./areas/"+name+".nml"));
			areas.add(a);
			return a;
		}catch(Exception exx){}
				
		System.out.println("There is no area named "+name);
		return null;
	}
	private static LinkedList<Area> areas = new LinkedList<Area>();

}