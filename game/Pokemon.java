package game;

import java.util.*;
import java.awt.Graphics2D;
import javax.swing.*;

/**
 *A particular pokemon owned by the player or a rival trainer.
 */
public class Pokemon 
{
	private Species species;
	private String nickname;
	private int level, xp;
	private Status status = Status.NORMAL;
	private int currentHp, currentAttack, currentDefense, currentSpeed, currentSpAttack, currentSpDefense; //the ones that may be lowered in battle
	private int hp, attack, defense, speed, spAttack, spDefense; //the base stat
	private ArrayList<Move> moves = new ArrayList<Move>(); //up to 4
	
	Pokemon(Species s, int level)
	{
		species = s;
		nickname = s.name();
		this.level = level;
		
		for(int l=level; l>0; l--)
		{
			moves.addAll( species.movesLearnedAtLevel(l) );
			if(moves.size()>=4) break;
		}
		
		hp = s.hp();
		currentHp=hp;
		attack = s.attack();
		defense = s.defense();
		//etc
		
		restoreStats();
	}
	
	/**
	*For loading
	*/
	Pokemon(Node n)
	{
	}
	
	/**
	*Set current Atk/Def/etc to the base stat; Pokemon has stats restorated after a battle ends.
	*/
	public void restoreStats()
	{
		currentAttack = attack;
		currentDefense = defense;
		//etc
	}
		
	public String nickname(){return nickname;}		
	public Species species(){return species;}
	
	public int currentHp(){return currentHp;}
	public int currentAttack(){return currentAttack;}
	public int currentDefense(){return currentDefense;}
	public int currentSpeed(){return currentSpeed;}
	public int currentSpAttack(){return currentSpAttack;}
	public int currentSpDefense(){return currentSpDefense;}
	
	public int baseHp(){return hp;}
	public int baseAttack(){return attack;}
	public int baseDefense(){return defense;}
	public int baseSpeed(){return speed;}
	public int baseSpAttack(){return spAttack;}
	public int baseSpDefense(){return spDefense;}
	
	public double percentHp(){return currentHp/(double)hp;}
	
	public int level(){return level;}
	public int xp(){return xp;}
	
	public List<Move> moves(){return moves;}
	
	public Status status(){return status;}
	public void status(Status newStatus){status=newStatus;}
	
	public void doDamage(int damage){
		currentHp -= damage;
		if (currentHp < 0)
			currentHp = 0;
	}
}


