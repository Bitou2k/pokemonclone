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
		int lvl = 30;
		party().add(Species.named("Bulbasaur").makeWildAtLevel(lvl));
		party().add(Species.named("Mew").makeWildAtLevel(lvl));
		party().add(Species.named("Ditto").makeWildAtLevel(lvl));
		party().add(Species.named("Charmander").makeWildAtLevel(lvl));
		party().add(Species.named("Squirtle").makeWildAtLevel(lvl));
		party().add(Species.named("Dragonite").makeWildAtLevel(lvl));
	}	
	
	public Pack pack(){ return pack; }
	public Pokedex pokedex(){ return pokedex; }
	
	private final Image imgUp = new ImageIcon("./entityImages/Player Up.png").getImage();
	private final Image imgUpStrideOne = new ImageIcon("./entityImages/Player UpStrideOne.png").getImage();
	private final Image imgUpStrideTwo = new ImageIcon("./entityImages/Player UpStrideTwo.png").getImage();

	private final Image imgDown = new ImageIcon("./entityImages/Player Down.png").getImage();
	private final Image imgDownStrideOne = new ImageIcon("./entityImages/Player DownStrideOne.png").getImage();
	private final Image imgDownStrideTwo = new ImageIcon("./entityImages/Player DownStrideTwo.png").getImage();	
	
	private final Image imgLeft = new ImageIcon("./entityImages/Player Left.png").getImage();
	private final Image imgLeftStride = new ImageIcon("./entityImages/Player LeftStride.png").getImage();
	
	private final Image imgRight = new ImageIcon("./entityImages/Player Right.png").getImage();
	private final Image imgRightStride = new ImageIcon("./entityImages/Player RightStride.png").getImage();
	
	public void step(int ms)
	{
		if(ms%100!=0)return;
		
		stride--;
		if(stride<0)stride=0;
	}
	
										int stepCount=0;
	
	private int stride=0; //2, 1, or 0
	public void startStride(){stride=2;stepCount++;}
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
		if(direction()==Direction.NORTH && stride==0) return imgUp;
		if(direction()==Direction.NORTH && stride>0) 
			if(stepCount%2==0) return imgUpStrideOne;
			else return imgUpStrideTwo;
		
		if(direction()==Direction.SOUTH && stride==0) return imgDown;
		if(direction()==Direction.SOUTH && stride>0) 
			if(stepCount%2==0) return imgDownStrideOne;
			else return imgDownStrideTwo;
		
		if(direction()==Direction.EAST && stride==0) return imgRight;
		if(direction()==Direction.EAST && stride==1) return imgRight;
		if(direction()==Direction.EAST && stride==2) return imgRightStride;
		
		if(direction()==Direction.WEST && stride==0) return imgLeft;
		if(direction()==Direction.WEST && stride==1) return imgLeft;
		if(direction()==Direction.WEST && stride==2) return imgLeftStride;
		
		return null;//error!
	}
	
}