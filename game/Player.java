package game;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
*You.
*/
public class Player extends Battler {

	private String name = "Ash"; //will be moved up to superclass Citizen
	private List<Pokemon> party; //will be moved up to superclass Battler
	private Pack pack = new Pack();
	private Pokedex pokedex = new Pokedex();
	private Direction d  = Direction.NORTH;
	
	public String name(){ return name; }
	public void name(String n){ name=n; }
	public List<Pokemon> party() { return party; }
	
	public Direction direction(){ return d; }
	public void direction(Direction d){	this.d = d;	}
	
	public Pack pack(){ return pack; }
	public Pokedex pokedex(){ return pokedex; }
	
	int stride=0; //2, 1, or 0
	
	private ImageIcon ii = new ImageIcon("./entityImages/Player Front.png");
	private final ImageIcon imgDown = new ImageIcon("./entityImages/Player Front.png");
	private final ImageIcon imgDownStrideTwo = new ImageIcon("./entityImages/Player Front StrideTwo.png");
	private final ImageIcon imgDownStrideOne = new ImageIcon("./entityImages/Player Front StrideOne.png");
	private final ImageIcon imgRight = new ImageIcon("./entityImages/Player Right.png");
	private final ImageIcon imgRightStride = new ImageIcon("./entityImages/Player Right Stride.png");
	private final ImageIcon imgLeft = new ImageIcon("./entityImages/Player Left.png");
	private final ImageIcon imgLeftStride = new ImageIcon("./entityImages/Player Left Stride.png");
	private final ImageIcon imgUp = new ImageIcon("./entityImages/Player Up.png");
	private final ImageIcon imgUpStrideTwo = new ImageIcon("./entityImages/Player Up StrideTwo.png");
	private final ImageIcon imgUpStrideOne = new ImageIcon("./entityImages/Player Up StrideOne.png");

	public void step(int ms)
	{
		if(ms%100!=0)return;
		
		stride--;
		if(stride<0)stride=0;
	}
	
	/**
	*Am I in the middle of walking?  If so, don't try to walk again yet.
	*/
	public boolean inStride()
	{
		return stride > 0;
	}

	public void drawOn(Graphics2D g){
		g.drawImage(getImage(), d.dx()*-4*stride, d.dy()*-4*stride, null);
	}
	
	private Image getImage()
	{
		if(d==Direction.NORTH && stride==0) return imgUp.getImage();
		if(d==Direction.NORTH && stride==1) return imgUpStrideOne.getImage();
		if(d==Direction.NORTH && stride==2) return imgUpStrideTwo.getImage();
		
		if(d==Direction.SOUTH && stride==0) return imgDown.getImage();
		if(d==Direction.SOUTH && stride==1) return imgDownStrideOne.getImage();
		if(d==Direction.SOUTH && stride==2) return imgDownStrideTwo.getImage();
		
		if(d==Direction.EAST && stride==0) return imgRight.getImage();
		if(d==Direction.EAST && stride==1) return imgRight.getImage();
		if(d==Direction.EAST && stride==2) return imgRightStride.getImage();
		
		if(d==Direction.WEST && stride==0) return imgLeft.getImage();
		if(d==Direction.WEST && stride==1) return imgLeft.getImage();
		if(d==Direction.WEST && stride==2) return imgLeftStride.getImage();
		
		return null;//error!
	}
	
}