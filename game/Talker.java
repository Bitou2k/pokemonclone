package game;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
*
*/
public class Talker extends Citizen
{

	public Talker(String name, String s, Image[] i, Direction d)
	{
		name(name);
		talk = s;
		images = i; 
		direction(d);
	}

	
	Image[] images;

	private String talk;

	public String talk()
	{
		return talk;
	}


	public void step(int ms)
	{
		if (ms % 100 != 0) return;

		stride--;
		if (stride < 0) stride = 0;
	}

	int stepCount = 0;

	private int stride = 0; //2, 1, or 0
	public void startStride() { stride = 2; stepCount++; }
	/**
	*Am I in the middle of walking?  If so, don't try to walk again yet.
	*/
	public boolean inStride()
	{
		return stride > 0;
	}

	public void drawOn(Graphics2D g)
	{
		g.drawImage(getImage(), direction().dx() * -4 * stride, direction().dy() * -4 * stride - 4, null);
	}

	private Image getImage()
	{
		if(images.length == 10)
		{
			if (direction() == Direction.NORTH && stride == 0) return images[0];
			if (direction() == Direction.NORTH && stride > 0)
				if (stepCount % 2 == 0) return images[1];
				else return images[2];

			if (direction() == Direction.SOUTH && stride == 0) return images[3];
			if (direction() == Direction.SOUTH && stride > 0)
				if (stepCount % 2 == 0) return images[4];
				else return images[5];

			if (direction() == Direction.EAST && stride == 0) return images[6];
			if (direction() == Direction.EAST && stride == 1) return images[6];
			if (direction() == Direction.EAST && stride == 2) return images[7];

			if (direction() == Direction.WEST && stride == 0) return images[8];
			if (direction() == Direction.WEST && stride == 1) return images[8];
			if (direction() == Direction.WEST && stride == 2) return images[9];
		}
		return null;//error!
	}

}