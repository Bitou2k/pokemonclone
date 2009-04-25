
import java.awt.*;
import java.util.*;

//the area or map that is a grid of Tiles
class Area extends Presenter {

	java.util.List<Tile> tiles = new ArrayList<Tile>();
	java.util.List<Entity> entities = new ArrayList<Entity>();
	WildPokemonGenerator gen; //for grassy areas
	
	Area()
	{
		for(int x=0;x<12;x++)
			for(int y=0;y<9;y++)
				tiles.add(new Tile(x,y));
	}
	
	void drawOn(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0,0,320,240);
		
		for(Tile t:tiles) t.drawOn(g);
	}
	
	void keyPressed(char key){}
	void step(){}
}