import java.awt.*;

public class Tile{
	private Image image;
	private String name;
	private byte byteCode;
	private int locX;
	private int locY;
	
	public void setX(int x){locX = x;}
	public void setY(int y) {locY = y;}
	
	Tile(int x, int y)
	{
		locX = x;
		locY = y;
	}
	
	public String toString()
	{
		return "( " + Integer.toString(locX) + ", " + Integer.toString(locY) + " )";
	}

}
	
	