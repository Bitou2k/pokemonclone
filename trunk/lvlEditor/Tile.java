
package lvlEditor;
import game.Node;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Tile{
	private ImageIcon image;
	private String imageName="";
	private String target=""; //where you should go when steping on this tile(ie, a door)
	private int locX;
	private int locY;
	static final int SQUARESIDE = 16; // each square in the origional pokemon seems to be 16x16
	
	public void setX(int x){locX = x;}
	public void setY(int y) {locY = y;}

	public int getX() { return locX;}
	public int getY() { return locY;}

	public String getLocation() { return Integer.toString(locX) + ", " + Integer.toString(locY); }

	public void set(String s) 
	{
		image = new ImageIcon("./tileImages/" + s + ".png");
		imageName = s;
	}
	void setTarget(String s)
	{
		target = s;
	}
	String getTarget()
	{
		return target;
	}

	
	Tile(int x, int y)
	{
		locX = x;
		locY = y;
	}

	public void drawImage(Component c, Graphics g)
	{
		if (image != null)
		{
			image.paintIcon(c, g, locX * SQUARESIDE, locY * SQUARESIDE);
		}
	}
	
	public String toString()
	{
		return asNode().encoded();
	}
	
	public Node asNode()
	{
		Node tileNode = new Node("tile");
		tileNode.addSubnode("image", imageName );
		tileNode.addSubnode("target", target );
		tileNode.addSubnode("x", ""+locX );
		tileNode.addSubnode("y", ""+locY );
		return tileNode;
	}

}
	
	