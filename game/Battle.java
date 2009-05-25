package game;

import java.awt.*;
import javax.swing.*;
import java.util.*;

class Battle extends Presenter {

	private Battler ash;
	private Battler enemy;
	
	private ArrayList<Pokemon> yourParty;
	private Pokemon ashsPokemon;
	private Pokemon enemyPokemon;
	private int stage = 0;
	
	private int[][] menuPoints = {{140,155},{220,240}};//{X's},{Y's}
	private int menuIndexX,menuIndexY;
	private boolean locked = true;
	
	//images
	private Image enemyBar = new ImageIcon("./resources/battle/enemybar.png").getImage();
	private Image ashImage = new ImageIcon("./resources/battle/ash.png").getImage();
	private Image bottomFrame = new ImageIcon("./resources/battle/bottomframe.png").getImage();
	private Image battleMenu = new ImageIcon("./resources/battle/battlemenu.png").getImage();
	private Image playerBar = new ImageIcon("./resources/battle/playerbar.png").getImage();
	private Image cursor = new ImageIcon("./resources/arrow.png").getImage();
	
	private Pokemon p; 
	private Presenter oldP;
	public Battle(Pokemon p, Presenter oldP)
	{	
		menuIndexX = 0;
		menuIndexY = 0;
		this.p=p; this.oldP=oldP;
		enemyPokemon = p;
		//remove this
		yourParty = new ArrayList<Pokemon>();
		yourParty.add(Species.named("Bulbasaur").makeWildAtLevel(100));
	}
	public void gotFocus()
	{
		
	}
	
	public void drawOn(Graphics2D g){
		
		g.setColor(Color.WHITE); //blank background
		g.fillRect(0,0,320,288);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,20));
		final int TEXTX = 15;
		final int FULLHEALTH = 97;
		
		g.drawImage(bottomFrame,0,195,null);		
		
		if(enemyPokemon!=null){
			//that background arrow thingy
			g.drawImage(enemyBar,10,20,null);
			//name
			g.setColor(Color.BLACK);
			g.drawString(enemyPokemon.nickname(),10,15);
			//image
			g.drawImage(p.species().imageFront(),170,0,128,128,null);
			//health bar
			g.setColor( colorForHealth( enemyPokemon.percentHp() ) );
			g.fillRect(56,23,(int)(FULLHEALTH * enemyPokemon.percentHp()),7);
		}
		if(ashsPokemon!=null){
			//that background arrow thingy
			g.drawImage(playerBar,155,145,null);
			//name
			g.setColor(Color.BLACK);
			g.drawString(ashsPokemon.nickname(),170,140);
			//image
			g.drawImage(ashsPokemon.species().imageBack(),10,65,128,128,null);
			//health bar
			g.setColor( colorForHealth( ashsPokemon.percentHp() ) );
			g.fillRect(203,146,(int)(FULLHEALTH * ashsPokemon.percentHp()),7);
			//health numbers
			g.setColor(Color.BLACK);
			g.drawString(ashsPokemon.currentHp() + "   "  + ashsPokemon.baseHp(),200,180);
		}
		
		
		
		g.setColor(Color.BLACK);
		if (stage < 30) //3 seconds, intro to battle
		{			
			g.drawString("A WILD "+p.nickname(),TEXTX,225);
			g.drawString("HAS APPEARED!!",TEXTX,250);
			g.drawImage(ashImage,10,100, null);			
		}
		else if (stage < 45){ //sending out players pokemon			
			ashsPokemon = yourParty.get(0); 
			g.drawString("GO..." + ashsPokemon.nickname(),TEXTX,225);
		}
		else{
			locked = false;
			g.drawImage(battleMenu,125,195,null);
			g.drawImage(cursor,140 + menuIndexX * 100,220 + menuIndexY * 35,null);
		}
		
	}
	
	private Color colorForHealth(double percentage)
	{
		if(percentage > 0.5) return Color.GREEN;
		if(percentage > 0.25) return Color.YELLOW;
		return Color.RED;
	}
	
	public void buttonPressed(Button b){
		if(stage > 3 /*&& stage < someothernum*/){
			if(b==Button.UP){if(menuIndexY ==1) menuIndexY = 0;}
			else if (b==Button.DOWN){if(menuIndexY == 0)menuIndexY = 1;}
			else if (b==Button.LEFT){if(menuIndexX == 1)menuIndexX = 0;}
			else if (b==Button.RIGHT){if(menuIndexX == 0)menuIndexX = 1;}
			
			else if(b==Button.START){
				
			}
			
			else if (b == Button.A && !locked){
				if (menuIndexX == 0) //FIGHT / BAG
				{
					if (menuIndexY == 0)//FIGHT
					{
						fight();
					}
					else //BAG
					{
					
					}				
				}
				else //PkMn / RUN
				{
					if (menuIndexY == 0){ //PKMN
					
					}
					else //RUN
					{
						enterPresenter(oldP);
					}
				
				}
			}
		}
	}
	public void step(int ms){
		stage++;
	}
	
	private void fight(){
		locked = true;
		String moveSelection = showMenu("Select a move:",new String[]{
			ashsPokemon.moves().get(0).name(),
			ashsPokemon.moves().get(1).name(),
			ashsPokemon.moves().get(2).name(),
			ashsPokemon.moves().get(3).name()});
	}

}