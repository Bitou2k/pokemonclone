package game;

import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

class StartPresenter extends Presenter {

	private java.util.List<PokedexPokemon> ps = PokedexPokemon.all();
	private PokedexPokemon p = ps.get(0);
	
	public void drawOn(Graphics2D g){	
		g.setColor(Color.WHITE);
		g.fillRect(0,0,16*20,16*18);
		
		g.drawImage(p.image32(),0,0,null);
		g.drawImage(p.image80(),32,0,null);
		g.drawImage(p.imageFront(),32+80,0,null);
		g.drawImage(p.imageBack(),32+80+64,0,null);
		g.setColor(Color.BLACK);
		g.drawString("No. "+p.number(),0,70);
		g.drawString(p.name(),0,80);
		g.drawString(p.description(),0,90);
		
		g.drawString("Credits:",0,120);
		g.drawString("(add yourself but no one else!)",0,130);
		g.drawString("Ryan Macnak",0,140);
		g.drawString("Michal Broniek",0,150);
	}
	
	public void keyPressed(char key){
	
		Area a = Area.named("route01");
		a.playerAt(5,5);
		enterPresenter(a);
	}
	
	public void step(){
		int next = ps.indexOf(p) + 1;
		if(next>=ps.size()) next=0;
		p = ps.get(next);
	}
}