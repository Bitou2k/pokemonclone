
package splitter;
import game.*;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class JohtoTile{
	
	public java.io.File image;
	private String target="";
	private String type="walkable";
	private int locX;
	private int locY;
	static final int SQUARESIDE = 16; // each square in the origional pokemon seems to be 16x16

	
	public void setX(int x){locX = x;}
	public void setY(int y) {locY = y;}

	public int getX() { return locX;}
	public int getY() { return locY;}

	public String getLocation() { return Integer.toString(locX) + ", " + Integer.toString(locY); }

	public void setTarget(String s) {target = s;}
	public String getTarget() {return target;}

	public void setType(String s) {type = s;}
	public String getType() {return type;}
	
	JohtoTile(int x, int y)
	{
		locX = x;
		locY = y;
		type = "walkable";
	}
	
	public Node asNode()
	{
		Node JohtoTileNode = new Node("tile");
		JohtoTileNode.addSubnode("image", image.getName() );
		JohtoTileNode.addSubnode("target", target );
		JohtoTileNode.addSubnode("type", type );
		JohtoTileNode.addSubnode("x", ""+locX );
		JohtoTileNode.addSubnode("y", ""+locY );
		return JohtoTileNode;
	}
	
	

}

	
	