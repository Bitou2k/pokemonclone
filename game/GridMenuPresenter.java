
package game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.util.*;
import javax.swing.*;

/**
*Do not use this class directly, use the showMenu methods inherited on Presenters.
*/
public class GridMenuPresenter extends Presenter {

	private String[][] choices;
	private String longest="";
	private int selectedx=0, selectedy=0;
	private String choice;
	private int s;
	
	private int bottomx=16*20,bottomy=16*18;
	
	private Presenter behide,next;
	
	public String choice()
	{
		return choice;
	}
	
	public GridMenuPresenter(String[] choicesArg, Presenter b, Presenter n)
	{
		behide = b;
		next = n;
		
		for(String x: choicesArg)
			if(x.length()>longest.length())
				longest = x;
				
		choice = choicesArg[0];
		
		s = (int)Math.ceil(Math.sqrt(choicesArg.length));
		choices = new String[s][s];
		int count = 0;
		for(int x=0; x<s; x++)
			for(int y=0; y<s; y++)
			{
				choices[s-x-1][y] = (count<choicesArg.length ? choicesArg[count] : "");
				count++;
			}
	}
	
	/**
	*
	*/
	public void drawOn(Graphics2D g){
		behide.drawOn(g);
		
		int unitWidth = (longest.length() * 18 + 20);
		int width = unitWidth * 2;
		int height = choices[0].length * 30;
			
		g.setColor(Color.WHITE);
			g.fillRect(bottomx-width-1,bottomy-height-1,width+3,height+3);
			
		g.setColor(Color.BLACK);
			g.fillRoundRect(bottomx-width,bottomy-height,width,height,4,4); 
		
		g.setColor(Color.WHITE);
			g.fillRect(bottomx-width+3,bottomy-height+3,width-6,height-6);

		g.setColor(Color.BLACK);
		g.setFont(new java.awt.Font("Courier New",java.awt.Font.BOLD,25));
		
		for(int x=0; x<s; x++)
			for(int y=0; y<s; y++)
			{
				g.drawString( choices[x][y],bottomx-unitWidth*(x+1)+20,bottomy-height+22+30*y);
			
			}
			
		g.drawString( ">",bottomx-unitWidth*(selectedx+1)+4,bottomy-height+22+30*(selectedy));	
	}
	
	/**
	*
	*/
	public void buttonPressed(Button b){
		
		if(b==Button.UP)
		{
			selectedy--;
			if(selectedy<0) selectedy=s-1;
			choice = choices[selectedx][selectedy];
		}
		else if(b==Button.DOWN)
		{
			selectedy++;
			if(selectedy>=s) selectedy=0;
			choice = choices[selectedx][selectedy];
		}
		else if(b==Button.LEFT)
		{
			selectedx--;
			if(selectedx<0) selectedx=s-1;
			choice = choices[selectedx][selectedy];
		}
		else if(b==Button.RIGHT)
		{
			selectedx++;
			if(selectedx>=s) selectedx=0;
			choice = choices[selectedx][selectedy];
		}
		else if(b==Button.A)
		{
			if(next!=null) enterPresenter(next);
			synchronized(this) {
				notify();
			}
		}
	}
	
	/**
	*
	*/
	public void step(int ms){}
}