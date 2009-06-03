package game;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
*A Presenter for a battle between the player and a rival trainer or a wild pokemon
*/
class Battle extends Presenter {

	private Battler ash;
	private Battler enemy; //if enemy is null, this is a wild battle
	
	private Pokemon ashsPokemon;
	private Pokemon enemyPokemon;
	
	private int stage = 0;
	
	private int[][] menuPoints = {{140,155},{220,240}};//{X's},{Y's}
	private int menuIndexX=0,menuIndexY=0;
	private boolean locked = true;
	private boolean Switched = false;
	
	//images
	private Image enemyBar = new ImageIcon("./resources/battle/enemybar.png").getImage();
	private Image ashImage = new ImageIcon("./resources/battle/ash.png").getImage();
	private Image bottomFrame = new ImageIcon("./resources/battle/bottomframe.png").getImage();
	private Image battleMenu = new ImageIcon("./resources/battle/battlemenu.png").getImage();
	private Image playerBar = new ImageIcon("./resources/battle/playerbar.png").getImage();
	private Image cursor = new ImageIcon("./resources/arrow.png").getImage();
	
	private String textLine1 = "";
	private String textLine2 = "";
	
	 
	private Presenter returnPresenter;
	
	public void ashsPokemon(Pokemon p)
	{
		ashsPokemon = p;
	}
	public void enemyPokemon(Pokemon p)
	{
		enemyPokemon = p;
	}

	public void Switched() { Switched = true;}

	
	/**
	*A wild battle.
	*/
	public Battle(Pokemon p, Presenter returnPresenter)
	{	
		this.returnPresenter=returnPresenter;
		enemyPokemon = p;
		new Thread(){public void run(){battleLoop();}}.start();
	}
	/**
	*A trainer battle.
	*/
	public Battle(Battler b, Presenter returnPresenter)
	{
		this.returnPresenter=returnPresenter;
		enemy = b;
		new Thread(){public void run(){battleLoop();}}.start();
	}

	public void gotFocus()
	{
		ash = player();
	}
	
	private Color colorForHealth(double percentage)
	{
		if(percentage > 0.5) return Color.GREEN;
		if(percentage > 0.25) return Color.YELLOW;
		return Color.RED;
	}
	
	public void buttonPressed(Button b){
	
	}
	public void step(int ms){
	}
	
	
	private Move getAIMove(){
		//return a random move
		Move selection = enemyPokemon.moves().get((int)(Math.random() * 100) % enemyPokemon.moves().size());
		
		return selection;
	}
	/**
	*	@return 0 if successful, 1 if your pokemon KO, 2 if enemy Pokemon KO
	*/
	private int Attack(Move ashsMove, Move enemyMove){
		Pokemon pkmnAttack, pkmnDefend;
		Move firstAttack, secondAttack;
		//determine who will attack first
		/*if(Switched == false)
		{*/
		boolean youAttackFirst;
			if (ashsPokemon.currentSpeed() > enemyPokemon.currentSpeed()){
				pkmnAttack = ashsPokemon;
				pkmnDefend = enemyPokemon;
				firstAttack = ashsMove;
				secondAttack = enemyMove;
				youAttackFirst = true;
			}
			else{
				pkmnAttack = enemyPokemon;
				pkmnDefend = ashsPokemon;
				firstAttack = enemyMove;
				secondAttack = ashsMove;
				youAttackFirst = false;
			}
			//attack
			if (firstAttack != null){
				textLine1 = pkmnAttack.nickname() + " uses " + firstAttack.name();
				if (firstAttack.category().equals("Physical") || firstAttack.category().equals("Special")){
				pkmnDefend.doDamage(calcDamage(firstAttack,pkmnAttack,pkmnDefend));		
				}
				sleep(2000);
				if (pkmnDefend.currentHp() == 0){
					textLine1 = pkmnDefend.nickname() + " fainted";
					pkmnDefend.species().cry();
					return (youAttackFirst ? 2 : 1);
				}
			}
			
			if (secondAttack != null){
				textLine1 = pkmnDefend.nickname() + " uses " + secondAttack.name();
				if (secondAttack.category().equals("Physical")|| secondAttack.category().equals("Special")){
				pkmnAttack.doDamage(calcDamage(secondAttack,pkmnDefend,pkmnAttack));	
				}
				sleep(2000);
				if (pkmnAttack.currentHp() == 0){
					textLine1 = pkmnAttack.nickname() + " fainted";
					pkmnAttack.species().cry();
					return (youAttackFirst ?  1 :  2);
				}
			}
			textLine1 = "";
			return 0;
	}
	private int calcDamage(Move move, Pokemon att, Pokemon def){
	//Damage = ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100
		double level = (double)att.level();
		double attackStat = (double)att.currentAttack();
		double attackPower = (double)move.power();
		double defenseStat = (double)def.currentDefense();
		double STAB = (move.type() == att.species().type() || move.type() == att.species().type2() ? 1.5 : 1.0);
		double weakResist = 1.0;//type comparison CHANGE
		Random r = new Random();
		double randomNum = (double)r.nextInt(16) + 85.0;
		int damage = (int)(((((2 * level / 5 + 2) * attackStat * attackPower / defenseStat)/50)+2) * STAB * weakResist * randomNum / 100);
		System.out.println(att.nickname() + " attacks" + '\n' + "level:"+level +", attStat:" + attackStat + ", attPower:"+ move.power() + 
			", defStat:" + defenseStat+", STAB:" + STAB+", random:"+ randomNum + '\n' +"damage: " + damage);
		return damage;
	}

	private void sleep(long ms)
	{
		try
		{
			Thread.sleep(ms);
		}catch(Exception e){}
	}
	
	public void drawOn(Graphics2D g){
		g.setColor(Color.WHITE); //blank background
		g.fillRect(0,0,320,288);
		
		//setup
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD,20));
		final int TEXTX = 15;
		final int FULLHEALTH = 97;
		
		//draw little box at the bottom
		g.drawImage(bottomFrame,0,195,null);	

		
		if(enemyPokemon!=null){
			//that background arrow thingy
			g.drawImage(enemyBar,10,32,null);
			//name
			g.setColor(Color.BLACK);
			g.drawString(enemyPokemon.nickname(),10,15);
			g.drawString(":L" + enemyPokemon.level(),35,30);
			//image
			g.drawImage(enemyPokemon.species().imageFront(),170,0,128,128,null);
			//health bar
			g.setColor( colorForHealth( enemyPokemon.percentHp() ) );
			g.fillRect(56,35,(int)(FULLHEALTH * enemyPokemon.percentHp()),7);
		}
		if(ashsPokemon!=null){
			//that background arrow thingy
			g.drawImage(playerBar,155,145,null);
			//name
			g.setColor(Color.BLACK);
			g.drawString(ashsPokemon.nickname(),170,128);
			g.drawString(":L" + ashsPokemon.level(),195,143);
			//image
			g.drawImage(ashsPokemon.species().imageBack(),10,65,128,128,null);
			//health bar
			g.setColor( colorForHealth( ashsPokemon.percentHp() ) );
			g.fillRect(203,146,(int)(FULLHEALTH * ashsPokemon.percentHp()),7);
			//XP
			g.setColor(Color.BLUE);
			g.fillRect(203,153,(int)(FULLHEALTH * ashsPokemon.xp() / ashsPokemon.nextLevelXp()),7);
			//health numbers
			g.setColor(Color.BLACK);
			g.drawString(ashsPokemon.currentHp() + "   "  + ashsPokemon.baseHp(),200,180);
		}		
		g.setColor(Color.BLACK);
		
		if (stage == 0){
			g.drawImage(ashImage,10,100,null);
		}
		
		g.drawString(textLine1,TEXTX,225);
		g.drawString(textLine2,TEXTX,250);
		
	}
	
	private void battleLoop(){
		textLine1 = "A WILD " + enemyPokemon.species().name();//announce wild pokemon
		textLine2 = "APPEARED!!";
		sleep(2000);
		stage++;
		//send out your pokemon
		for (int i = 0; i < ash.party().size(); i ++){
			if (ash.party().get(i).currentHp() != 0){
				ashsPokemon = ash.party().get(i);
				i = ash.party().size();
			}
		}
		textLine1 = "GO..." + ashsPokemon.nickname();
		textLine2 = "";
		repaint();
		ashsPokemon.species().cry();
		sleep(1500);
		//loop starts here
		while(!battleEnd()){
			textLine1 = "";
			textLine2 = "";
			boolean battleCommand = getBattleCommand();
			//if it returns false, do it again
			while (!battleCommand){battleCommand = getBattleCommand();}
		}
		enterPresenter(returnPresenter);
	}
	private boolean battleEnd(){		
		if (stage == 3){return true;}
		return false;
	}
	private void tryToRun(){
		stage = 3;
	}
	private void enemyMove(){
		Attack(null, getAIMove());
	}
	private boolean getBattleCommand(){
			String selection = showGridMenu(new String[] {"FIGHT","PACK","PKMN","RUN"});
			System.out.println(selection);
			if (selection.equals("FIGHT")){
				String[] moveNameList = new String[]{"-","COREY","STI","OMG GLITCH","Cancel"};
				for (int i = 0; i < ashsPokemon.moves().size(); i++){
					moveNameList[i] = ashsPokemon.moves().get(i).name() + " " + ashsPokemon.moves().get(i).currentPp() + "/" + ashsPokemon.moves().get(i).pp();
				}
				Move moveToUse = null;
				boolean canUse = false;
				while (!canUse){
					String moveSel = showMenu(moveNameList);
					if (!moveSel.equals("-") && !moveSel.equals("OMG GLITCH")&& !moveSel.equals("COREY")&& !moveSel.equals("STI")){
						if (moveSel.equals("Cancel")){
							return false;
						}
						else if (moveSel.equals(moveNameList[0])){
							moveToUse = ashsPokemon.moves().get(0);
						}
						else if (moveSel.equals(moveNameList[1])){
							moveToUse = ashsPokemon.moves().get(1);
						}
						else if (moveSel.equals(moveNameList[2])){
							moveToUse = ashsPokemon.moves().get(2);
						}
						else{ //index = 3
							moveToUse = ashsPokemon.moves().get(3);
						}
						
						if (moveToUse.currentPp() == 0){
							textLine1 = "No PP left";
							textLine2 = "";
							sleep(250);
						}
						else
							canUse = true;
					}
				}
				//attack
				switch(Attack(moveToUse,getAIMove())){
				case 0: //normal--nothing
					break;
				case 1: //you KO
					BattleBox bb = new BattleBox(this);
					enterPresenter(bb);
					try{
					synchronized(bb){ bb.wait(); }
					}catch(Exception ex){}
					Pokemon newPokemon = bb.selectedPokemon;
					ashsPokemon(newPokemon);
					
					textLine1 = "GO..." + ashsPokemon.nickname();
					textLine2 = "";
					repaint();
					ashsPokemon.species().cry();
					break;
				case 2: //enemy KO--XP and stuff
					gainXp();
					stage = 3;
					break;
				}
				
			}
			else if (selection.equals("PACK")){
				//use an item
				enemyMove();
			}
			else if (selection.equals("PKMN")){

				BattleBox bb = new BattleBox(this);
				enterPresenter(bb);
				try{
				synchronized(bb){ bb.wait(); }
				}catch(Exception ex){}
				Pokemon newPokemon = bb.selectedPokemon;
				ashsPokemon(newPokemon);
				
				textLine1 = "GO..." + ashsPokemon.nickname();
				textLine2 = "";
				repaint();
				ashsPokemon.species().cry();
				enemyMove();
			}
			else{ //run
				tryToRun();
			}
		return true;
	}

	void gainXp()
	{
		double xpGained = (enemy==null?1:1.5) * enemyPokemon.species().baseExp() * enemyPokemon.level() / 7.0;
		
		showMessage(ashsPokemon.nickname() + " gained " + (int)xpGained + " XP");
		ashsPokemon.gainXp(xpGained);
	}


}
