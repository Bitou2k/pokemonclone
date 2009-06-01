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
	
	/*public void drawOn(Graphics2D g){
		
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
			g.drawImage(enemyPokemon.species().imageFront(),170,0,128,128,null);
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
		
		g.drawString(textLine1,TEXTX,225);
		g.drawString(textLine2,TEXTX,250);
		
		
		if (stage < 30) //3 seconds, intro to battle
		{			
			textLine1 = "A WILD "+enemyPokemon.nickname();
			textLine2 = "HAS APPEARED!!";
			g.drawImage(ashImage,10,100, null);			
		}
		else if (stage < 45){ //sending out players pokemon			
			ashsPokemon = ash.party().get(0); 
			textLine1 = "GO..." + ashsPokemon.nickname();
			textLine2 = "";
		}
		else if (stage == 46){ //show battle menu
			locked = false;
			g.drawImage(battleMenu,125,195,null);
			g.drawImage(cursor,140 + menuIndexX * 100,220 + menuIndexY * 35,null);
		}
		else if (stage == 51){//dont show battle menu
			locked = true;
		}
		
	}*/
	
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
					enterPresenter(new BattleBox(this));
					}
					else //RUN
					{
						enterPresenter(returnPresenter);
					}
				
				}
			}
		}
	}
	public void step(int ms){
	}
	
	private void fight(){
		locked = true;
		stage = 51;
		String[] moveSet = new String[4];
		for (int i = 0; i < 4; i++){
			moveSet[i] = ashsPokemon.moves().get(i).name();
		}
		String moveSelection = showMenu("Select a move:",new String[]{
			moveSet[0],
			moveSet[1],
			moveSet[2],
			moveSet[3]});
			
		Move move;			
		if (moveSelection.equals(moveSet[0]))
			move = ashsPokemon.moves().get(0);
		else if (moveSelection.equals(moveSet[1]))
			move = ashsPokemon.moves().get(1);
		else if (moveSelection.equals(moveSet[2]))
			move = ashsPokemon.moves().get(2);
		else
			move = ashsPokemon.moves().get(3);
		
		Attack(move,getAIMove());
	}
	
	private Move getAIMove(){
		//return a random move
		Move selection = enemyPokemon.moves().get((int)(Math.random() * 100) % enemyPokemon.moves().size());
		
		return selection;
	}
	
	private void Attack(Move ashsMove, Move enemyMove){
		Pokemon pkmnAttack, pkmnDefend;
		Move firstAttack, secondAttack;
		//determine who will attack first
		if(Switched == false)
		{
			if (ashsPokemon.currentSpeed() > enemyPokemon.currentSpeed()){
				pkmnAttack = ashsPokemon;
				pkmnDefend = enemyPokemon;
				firstAttack = ashsMove;
				secondAttack = enemyMove;
			}
			else{
				pkmnAttack = enemyPokemon;
				pkmnDefend = ashsPokemon;
				firstAttack = enemyMove;
				secondAttack = ashsMove;
			}
			//attack
			pkmnDefend.doDamage(calcDamage(firstAttack,pkmnAttack,pkmnDefend));
			textLine1 = pkmnAttack.nickname() + " uses " + firstAttack.name();
			sleep(2000);
			pkmnAttack.doDamage(calcDamage(secondAttack,pkmnDefend,pkmnAttack));
			textLine1 = pkmnDefend.nickname() + " uses " + secondAttack.name();
			sleep(2000);
			textLine1 = "";
		}
		else
		{
		    pkmnAttack = enemyPokemon;
		    pkmnDefend = ashsPokemon;
		    firstAttack = enemyMove;
		    textLine1 = "GO..." + pkmnDefend.nickname();
		    sleep(2000);
		    pkmnDefend.doDamage(calcDamage(firstAttack, pkmnAttack, pkmnDefend));
		    textLine1 = pkmnAttack.nickname() + " uses " + firstAttack.name();
		    sleep(2000);
		    Switched = false;
		}
		if(ashsPokemon.currentHp() <= 0)
		{
			enterPresenter(new BattleBox(this));
			Switched = false;
		}
		stage = 46;
	}
	private int calcDamage(Move move, Pokemon att, Pokemon def){
	//Damage = ((((2 * Level / 5 + 2) * AttackStat * AttackPower / DefenseStat) / 50) + 2) * STAB * Weakness/Resistance * RandomNumber / 100
		double level = (double)att.level();
		double attackStat = (double)att.currentAttack();
		double attackPower = 100.0;//(double)move.power();
		double defenseStat = (double)def.currentDefense();
		double STAB = (move.type() == att.species().type() || move.type() == att.species().type2() ? 1.5 : 1.0);
		double weakResist = 1.0;//type comparison CHANGE
		Random r = new Random();
		double randomNum = (double)r.nextInt(16) + 85.0;
		System.out.println("level:"+level +", attStat:" + attackStat + ", attPower:"+ move.power() + 
			", defStat:" + defenseStat+", STAB:" + STAB+", random:"+ randomNum);
		return (int)(((((2 * level / 5 + 2) * attackStat * attackPower / defenseStat)/50)+2) * STAB * weakResist * randomNum / 100);
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
			g.drawImage(enemyBar,10,20,null);
			//name
			g.setColor(Color.BLACK);
			g.drawString(enemyPokemon.nickname(),10,15);
			//image
			g.drawImage(enemyPokemon.species().imageFront(),170,0,128,128,null);
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
	
	}
	private boolean getBattleCommand(){
			String selection = showGridMenu(new String[] {"FIGHT","PACK","PKMN","RUN"});
			System.out.println(selection);
			if (selection.equals("FIGHT")){
				String[] moveNameList = new String[]{"","","","","Cancel"};
				for (int i = 0; i < ashsPokemon.moves().size(); i++){
					moveNameList[i] = ashsPokemon.moves().get(i).name() + " " + ashsPokemon.moves().get(i).currentPp() + "/" + ashsPokemon.moves().get(i).pp();
				}
				Move moveToUse;
				boolean canUse = false;
				while (!canUse){
					String moveSel = showMenu(moveNameList);
					if (!moveSel.equals("")){
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
				enemyMove();
			}
			else{ //run
				tryToRun();
			}
		return true;
	}
}
