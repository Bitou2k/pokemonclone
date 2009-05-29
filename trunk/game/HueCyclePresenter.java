package game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.*;
import java.io.*;

class HueCyclePresenter extends Presenter
{
	private Presenter behide;
	private int time=0;
	HueCyclePresenter(Presenter behide){
		this.behide = behide;
	}
	
	public void drawOn(Graphics2D g){	
		
		behide.drawOn(g);
		Color c = Color.getHSBColor( time/15.0f, 0.5f, 0.5f);
		c = new Color(c.getRed(),c.getBlue(),c.getGreen(),150);
		g.setColor(c);
		g.fillRect(0,0,16*20,16*18);
	}
	
	public void buttonPressed(Button b)
	{	
	}
	
	public void step(int ms)
	{
		behide.step(ms);
		
		time++;
		if(time>15){
			enterPresenter(behide);
			synchronized(this) {
				notify();
			}
		}
	}
}