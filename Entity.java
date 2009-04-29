
import java.awt.*;

//something that may occupy a Tile
class Entity {

	Tile tile;
	
	void tile(Tile t){tile=t;}
	Tile tile(){return tile;}
	
	void drawOn(Graphics2D g){
	
		g.setColor(Color.RED);
		g.fillOval(10,10,4,4);
	}
}