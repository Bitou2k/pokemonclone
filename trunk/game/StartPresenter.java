package game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.*;
import java.io.*;

class StartPresenter extends Presenter {

	private List<Species> ss = Species.all();
	private Species s = ss.get(0);
	
	public void drawOn(Graphics2D g){	
		g.setColor(Color.WHITE);
		g.fillRect(0,0,16*20,16*18);
		
		g.drawImage(s.image32(),0,0,null);
		g.drawImage(s.image80(),32,0,null);
		g.drawImage(s.imageFront(),32+80,0,null);
		g.drawImage(s.imageBack(),32+80+64,0,null);
		g.setColor(Color.BLACK);
		g.drawString("No. "+s.paddedNumber()+" Gen"+s.generation(),5,90);
		g.drawString(s.name(),5,100);
		int space = s.description().indexOf(" ", 45);
		String desc1 = s.description().substring(0, space);
		String desc2 = s.description().substring(space);
		g.drawString(desc1,5,120);
		g.drawString(desc2,2,130);
		g.drawString("Type: "+s.type().toString(),5,140);
		if(s.type2()!=null)g.drawString(s.type2().toString(),38,150);
		
		g.drawString("Credits:",5,165);
		g.drawString("(add yourself but no one else.)",5,175);
		g.drawString("Ryan Macnak",5,185);
		g.drawString("Adam Hendrickson", 5, 195);
		g.drawString("Michal Broniek",5,205);
		g.drawString("Andrew Siegle",5,215);
		g.drawString("Joe Maguire",5,225);
		g.drawString("Colin Hoernig",5,235);
		g.drawString("Brett Guiden",5,245);
		g.drawString("Nick West", 5, 255);
		g.drawString("Jay Lannin", 5, 265);
		g.drawString("Ryan Craine", 5, 275);
	}
	
	private boolean first=true;
	public void gotFocus()
	{
		if(first&& !new File("D:/Ryan").exists())backgroundMusic("./music/PALLET.mid");
		first=false;
	}
	
	public void buttonPressed(Button b)
	{	
		Area a = Area.named("PalletTown");
		a.tileAt(5,7).entity(player());
		backgroundMusic("");
		enterPresenter(a);
	}
	
	public void step(int ms){
		if(ms%1500!=0)return;
		
		int next = ss.indexOf(s) + 1;
		if(next>=ss.size()) next=0;
		s = ss.get(next);
		repaint();
		s.cry();
		
		if(Math.random()<0.10 && isRoot()) enterPresenter(new HueCyclePresenter(this));
	}
}