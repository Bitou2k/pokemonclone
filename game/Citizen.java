package game;

/**
*You can talk to them, but not battle.
*/
public class Citizen extends Entity {

	private String name = "UNSETNAME";
	private Direction d  = Direction.NORTH;

	public Direction direction(){ return d; }
	public void direction(Direction d){	this.d = d;	}
	
	public String name(){ return name; }
	public void name(String n){ name=n; }
}