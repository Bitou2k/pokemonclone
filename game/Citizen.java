package game;

/**
*You can talk to them, but not battle.
*/
public class Citizen extends Entity {

	private String name = "UNSETNAME";
	//private String talk = "HELLO WORLD";
	//private Image[] image;
	private Direction d  = Direction.NORTH;

	//Citizen(Tile t, Image[] i, Direction dir, String n, String i)
	//{
	//	tile(t);
	//	d = dir;
	//	image = i;
	//	name = n;
	//	talk = i;
	//}

	public Direction direction(){ return d; }
	public void direction(Direction d){	this.d = d;	}
	
	public String name(){ return name; }
	public void name(String n){ name=n; }
}