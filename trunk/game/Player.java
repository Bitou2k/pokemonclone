package game;
import java.util.*;
import java.awt.*;
import javax.swing.*;

//you
class Player extends Battler {
	String name;
	java.util.List<Pokemon> party;
	java.util.List<Item> pack;
	Map<Pokemon,Boolean> seenIt;
	Map<Pokemon,Boolean> caughtIt;
	
	ImageIcon ii = new ImageIcon("./entityImages/Player Front.png");
	Player()
	{
		
	}
	void drawOn(Graphics2D g){
	
		g.drawImage(ii.getImage(),0,0,null);
	}
}