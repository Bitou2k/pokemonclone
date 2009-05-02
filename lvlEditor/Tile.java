import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Tile{
	private ImageIcon image;
	private String name;
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
		name = s;
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
		return name;
	}
	
	public Node asNode()
	{
		Node tileNode = new Node("tile");
		tileNode.addSubnode("type", name );
		tileNode.addSubnode("x", ""+locX );
		tileNode.addSubnode("y", ""+locY );
		return tileNode;
	}

}
	
	