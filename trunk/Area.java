
import java.awt.*;
import java.util.*;

//the area or map that is a grid of Tiles
class Area extends Presenter {

	java.util.List<Tile> tiles = new ArrayList<Tile>();
	java.util.List<Entity> entities = new ArrayList<Entity>();
	Player player;  
	WildPokemonGenerator gen; //for grassy areas
	
	Area()
	{
		for(int x=0;x<12;x++)
			for(int y=0;y<9;y++)
				tiles.add(new Tile(x,y));
	}
	
	void move(int dx, int dy)
	{
		//find player
		//see if target tile is valid
		//place in new target
	}
	
	void drawOn(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,240);
		
		for(Tile t:tiles) t.drawOn(g);
	}
	
	void keyPressed(char key){
		if(key=='A') move(-1,0);
		if(key=='S') move(0,1);
		if(key=='D') move(1,0);
		if(key=='W') move(0,-1);
	}
	void step(){}
}