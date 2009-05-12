package game;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

//you
class Player extends Battler {

	String name = "Ash";
	List<Pokemon> party;
	List<Item> pack;
	Pokedex pokedex;

	Direction d  = Direction.NORTH;

	int stride=0;
	//2,1,0
	
	ImageIcon ii = new ImageIcon("./entityImages/Player Front.png");
	
	final ImageIcon imgDown = new ImageIcon("./entityImages/Player Front.png");
	final ImageIcon imgDownStrideTwo = new ImageIcon("./entityImages/Player Front StrideTwo.png");
	final ImageIcon imgDownStrideOne = new ImageIcon("./entityImages/Player Front StrideOne.png");
	final ImageIcon imgRight = new ImageIcon("./entityImages/Player Right.png");
	final ImageIcon imgRightStride = new ImageIcon("./entityImages/Player Right Stride.png");
	final ImageIcon imgLeft = new ImageIcon("./entityImages/Player Left.png");
	final ImageIcon imgLeftStride = new ImageIcon("./entityImages/Player Left Stride.png");
	final ImageIcon imgUp = new ImageIcon("./entityImages/Player Up.png");
	final ImageIcon imgUpStrideTwo = new ImageIcon("./entityImages/Player Up StrideTwo.png");
	final ImageIcon imgUpStrideOne = new ImageIcon("./entityImages/Player Up StrideOne.png");


	void step(int ms)
	{
		if(ms%100!=0)return;
		
		stride--;
		if(stride<0)stride=0;
	}
	
	boolean inStride()
	{
		return stride > 0;
	}

	void drawOn(Graphics2D g){
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
	
	public void setDirection(Direction d){
		this.d = d;
	}
}