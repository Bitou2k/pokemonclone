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
	private int hp, attack, defense, speed, spAttack, spDefense; //the ones that may be lowered in battle by TailWhip/Screech/StringShot et al
	private int currentHp, currentAttack, currentDefense, currentSpeed, currentSpAttack, currentSpDefense; //the ones that may be lowered in battle by TailWhip/Screech/StringShot et al
	private int attackIV, defenseIV, speedIV, spAttackIV, spDefenseIV;
	private int attackEP, defenseEP, speedEP, spAttackEP, spDefenseEP;
	private ArrayList<Move> moves = new ArrayList<Move>(); //up to 4
	
	/**
	*Called by makeWildAtLevl
	*/
	Pokemon(Species s, int level)
	{
		species = s;
		nickname = s.name();
		this.level = level;
		
		for(int l=level; l>0; l--) //pull first four moves in reverse order
		{
			moves.addAll( species.movesLearnedAtLevel(l) );
			if(moves.size()>=4) break;
		}
		
		hp = (int)(s.baseHp() * 2.0 * level / 100.0 + 10.0);
		attack = (int)(s.baseAttack() * 2.0 * level / 100.0 + 5.0);
		defense = (int)(s.baseDefense() * 2.0 * level / 100.0 + 5.0);
		spAttack = (int)(s.baseSpAttack() * 2.0 * level / 100.0 + 5.0);
		spDefense = (int)(s.baseSpDefense() * 2.0 * level / 100.0 + 5.0);
		speed = (int)(s.baseSpeed() * 2.0 * level / 100.0 + 5.0);
		
		hp=s.baseHp();
		restoreStats();
	}
	
	/**
	*For loading, not implemented
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
		currentSpAttack = spAttack;
		currentSpDefense = spDefense;
		currentSpeed = speed;
	}
		
	public String nickname(){return nickname;}		
	public Species species(){return species;}
	
	public int hp(){return hp;}
	public int attack(){return attack;}
	public int defense(){return defense;}
	public int speed(){return speed;}
	public int spAttack(){return spAttack;}
	public int spDefense(){return spDefense;}
	
	public int currentHp(){return currentHp;}
	public int currentAttack(){return currentAttack;}
	public int currentDefense(){return currentDefense;}
	public int currentSpeed(){return currentSpeed;}
	public int currentSpAttack(){return currentSpAttack;}
	public int currentSpDefense(){return currentSpDefense;}
	
	public int IVAttack() { return ((currentAttack - 5)*50/level) - attackEP - species.baseAttack(); }
	public int IVDefense() { return ((currentDefense - 5)*50/level) - defenseEP - species.baseDefense(); }
	public int IVSpeed() { return ((currentSpeed - 5)*50/level) - speedEP - species.baseSpeed(); }
	public int IVSpAttack() { return ((currentSpAttack - 5)*50/level) - spAttackEP - species.baseSpAttack(); }
	public int IVSpDefense() { return ((currentSpDefense - 5)*50/level) - spDefenseEP - species.baseSpDefense(); }

	public double percentHp(){return hp/(double)species.baseHp();}
	
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


