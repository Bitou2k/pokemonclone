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

	private Pack pack = new Pack();
	private Pokedex pokedex = new Pokedex();
	
	public Player()
	{
		name("Ash");
	}	
	
	public Pack pack(){ return pack; }
	public Pokedex pokedex(){ return pokedex; }
	
	private final ImageIcon imgUp = new ImageIcon("./entityImages/Player Up.png");
	private final ImageIcon imgUpStrideTwo = new ImageIcon("./entityImages/Player UpStrideTwo.png");
	private final ImageIcon imgUpStrideOne = new ImageIcon("./entityImages/Player UpStrideOne.png");

	private final ImageIcon imgDown = new ImageIcon("./entityImages/Player Down.png");
	private final ImageIcon imgDownStrideTwo = new ImageIcon("./entityImages/Player DownStrideTwo.png");
	private final ImageIcon imgDownStrideOne = new ImageIcon("./entityImages/Player DownStrideOne.png");	
	
	private final ImageIcon imgLeft = new ImageIcon("./entityImages/Player Left.png");
	private final ImageIcon imgLeftStride = new ImageIcon("./entityImages/Player LeftStride.png");
	
	private final ImageIcon imgRight = new ImageIcon("./entityImages/Player Right.png");
	private final ImageIcon imgRightStride = new ImageIcon("./entityImages/Player RightStride.png");
	
	public void step(int ms)
	{
		if(ms%100!=0)return;
		
		stride--;
		if(stride<0)stride=0;
	}
	
	
	private int stride=0; //2, 1, or 0
	public void startStride(){stride=2;}
	/**
	*Am I in the middle of walking?  If so, don't try to walk again yet.
	*/
	public boolean inStride()
	{
		return stride > 0;
	}

	public void drawOn(Graphics2D g){
		g.drawImage(getImage(), direction().dx()*-4*stride, direction().dy()*-4*stride-4, null);
	}
	
	private Image getImage()
	{
		if(direction()==Direction.NORTH && stride==0) return imgUp.getImage();
		if(direction()==Direction.NORTH && stride==1) return imgUpStrideOne.getImage();
		if(direction()==Direction.NORTH && stride==2) return imgUpStrideTwo.getImage();
		
		if(direction()==Direction.SOUTH && stride==0) return imgDown.getImage();
		if(direction()==Direction.SOUTH && stride==1) return imgDownStrideOne.getImage();
		if(direction()==Direction.SOUTH && stride==2) return imgDownStrideTwo.getImage();
		
		if(direction()==Direction.EAST && stride==0) return imgRight.getImage();
		if(direction()==Direction.EAST && stride==1) return imgRight.getImage();
		if(direction()==Direction.EAST && stride==2) return imgRightStride.getImage();
		
		if(direction()==Direction.WEST && stride==0) return imgLeft.getImage();
		if(direction()==Direction.WEST && stride==1) return imgLeft.getImage();
		if(direction()==Direction.WEST && stride==2) return imgLeftStride.getImage();
		
		return null;//error!
	}
	
}