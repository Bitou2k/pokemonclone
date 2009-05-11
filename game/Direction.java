
package game;

/**
 *A typesafe represention of direction.
 */
public enum Direction {

	NORTH(0,-1), SOUTH(0,1), EAST(1,0),WEST(-1,0);

	private int dx, dy;
	
	private Direction(int dx,int dy){
		this.dx=dx;
		this.dy=dy;
	}
	
	public int dx(){return dx;}
	public int dy(){return dy;}
}	