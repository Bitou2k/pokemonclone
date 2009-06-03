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
	private int currentHp, currentAttack, currentDefense, currentSpeed, currentSpAttack, currentSpDefense; //the ones that may be lowered in battle by TailWhip/Screech/StringShot et al
	private int hp, attack, defense, speed, spAttack, spDefense; //the base stat
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
		
		currentHp=hp;
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
	public void xp(int i) { xp = i; }
	public void gainXp(double i) { xp += (int)i; }
	
	public List<Move> moves(){return moves;}
	
	public Status status(){return status;}
	public void status(Status newStatus){status=newStatus;}
	
	public void doDamage(int damage){
		currentHp -= damage;
		if (currentHp < 0)
			currentHp = 0;
	}

	void checkLevelUp()
	{
		double levelXp = 0;
		double tlevel = level + 1;
		if (species.GrowthRate().equals("Slow"))
		{
			levelXp = (5 * tlevel * tlevel * tlevel / 4);
		}
		else if (species.GrowthRate().equals("Medium"))
		{
			levelXp = tlevel * tlevel * tlevel;
		}
		else if (species.GrowthRate().equals("Fast"))
		{
			levelXp = 4 * tlevel * tlevel * tlevel / 5;
		}
		else if (species.GrowthRate().equals("Parabolic"))
		{
			levelXp = 6 * tlevel * tlevel * tlevel / 5 - 15 * tlevel * tlevel + 100 * tlevel - 140;
		}
		else if (species.GrowthRate().equals("Erratic"))
		{
			if (tlevel <= 50)
				levelXp = tlevel * tlevel * tlevel * ((100 - tlevel) / 50);
			else if (tlevel <= 68)
				levelXp = tlevel * tlevel * tlevel * ((150 - tlevel) / 50);
			else if (tlevel <= 98)
				levelXp = tlevel * tlevel * tlevel * (1.274 - (1 / 50) * (tlevel / 3));
			else
				levelXp = tlevel * tlevel * tlevel * ((160 - tlevel) / 50);
		}
		else if (species.GrowthRate().equals("Fluctuating"))
		{
			if (tlevel <= 15)
				levelXp = tlevel * tlevel * tlevel * ((24 + ((tlevel + 1) / 3)) / 50);
			else if (tlevel <= 35)
				levelXp = tlevel * tlevel * tlevel * ((14 + tlevel) / 50);
			else
				levelXp = tlevel * tlevel * tlevel * ((32 + (tlevel / 2)) / 50);
		}
		if (xp >= levelXp)
		{
			// level up
			level += 1;
			//leveluppresenter
		}
	}


}


