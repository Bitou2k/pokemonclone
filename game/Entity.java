package game;

import java.awt.*;

//something that may occupy a Tile
class Entity {

	Tile tile;
	
	void tile(Tile t){tile=t;}
	Tile tile(){return tile;}
	
	void drawOn(Graphics2D g){
	
		g.setColor(Color.RED);
		g.fillOval(tile.width()/2-2,tile.height()/2-2,4,4);
	}
	
	void step(){}
}