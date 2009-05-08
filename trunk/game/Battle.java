package game;

import java.awt.*;

class Battle extends Presenter {

	private Battler ash;
	private Battler enemy;
	
	private Pokemon ashsPokemon;
	private Pokemon enemyPokemon;
	
	private Pokemon p; private Presenter oldP;
	Battle(Pokemon p, Presenter oldP)
	{	
		this.p=p; this.oldP=oldP;
	}
	
	void drawOn(Graphics2D g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,16*20,16*18);
		
		//g.drawImage(p.image(),0,0,null);
		g.setColor(Color.BLACK);
		g.drawString("Wild "+p.name+" appeared!",0,60);
	}
	void keyPressed(char key){
		enterPresenter(oldP);
	}
	void step(){
		
	}
	
}