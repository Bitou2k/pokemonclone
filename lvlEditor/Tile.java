import java.awt.*;

class Tile{
	Image image;
	String name;
	byte byteCode;
	int locX;
	int locY;
	
	Tile(Image i, String s, byte b, int x, int y)
	{
		image = i; name = s; byteCode = b; locX = x; locY = y;
	}
}
	
	