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
		
		g.drawImage(p.image(),0,0,null);
		g.setColor(Color.BLACK);
		g.drawString("No. "+p.number(),0,50);
		g.drawString(p.name(),0,60);
		g.drawString(p.description(),0,70);
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