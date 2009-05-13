package game;

import java.awt.*;
import javax.swing.*;

class Battle extends Presenter {

	private Battler ash;
	private Battler enemy;
	
	private Pokemon ashsPokemon;
	private Pokemon enemyPokemon;
	private int stage = 0;
	
	private int[][] menuPoints = {{140,155},{220,240}};//{X's},{Y's}
	private int menuIndexX,menuIndexY;
	
	
	private ImageIcon enemyBar = new ImageIcon("./resources/battle/enemybar.png");
	private ImageIcon ashImage = new ImageIcon("./resources/battle/ash.png");
	private ImageIcon bottomFrame = new ImageIcon("./resources/battle/bottomframe.png");
	private ImageIcon battleMenu = new ImageIcon("./resources/battle/battlemenu.png");
	private ImageIcon playerBar = new ImageIcon("./resources/battle/playerbar.png");
	private ImageIcon cursor = new ImageIcon("./resources/arrow.png");
	
	private Pokemon p; 
	private Presenter oldP;
	public Battle(Pokemon p, Presenter oldP)
	{	
		menuIndexX = 0;
		menuIndexY = 0;
		this.p=p; this.oldP=oldP;
	}
	
	public void drawOn(Graphics2D g){
		g.setColor(Color.WHITE);
		g.fillRect(0,0,16*20,16*18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,16));
		final int TEXTX = 15;
		
		g.drawImage(enemyBar.getImage(),10,20,null);
		g.drawImage(playerBar.getImage(),155,145,null);
		g.drawImage(bottomFrame.getImage(),0,195,null);
		
		Image image = p.species().imageFront();
		
		//TODO::MAKE THIS WORK
		//Image pokeImage = image.getScaledInstance(160,-1,Image.SCALE_SMOOTH);
		
		g.drawImage(image,185,20,null);
		
		if (stage < 3) //3 seconds
		{			
			g.drawString("A wild "+p.nickname()+" has appeared!!",TEXTX,225);
			g.drawImage(ashImage.getImage(),10,100, null);			
		}
		else{
			g.drawImage(battleMenu.getImage(),125,195,null);
			g.drawImage(cursor.getImage(),140 + menuIndexX * 100,220 + menuIndexY * 35,null);
		}
		
		
	}
	public void buttonPressed(Button b){
		if(stage > 3 /*&& stage < someothernum*/){
			if(b==Button.UP){if(menuIndexY ==1) menuIndexY = 0;}
			else if (b==Button.DOWN){if(menuIndexY == 0)menuIndexY = 1;}
			else if (b==Button.LEFT){if(menuIndexX == 1)menuIndexX = 0;}
			else if (b==Button.RIGHT){if(menuIndexX == 0)menuIndexX = 1;}
			
			else if(b==Button.START){
				
			}
			else enterPresenter(oldP);
		}
	}
	public void step(int ms){
		stage++;
	}

}