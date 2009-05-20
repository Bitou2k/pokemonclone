package game;

import java.util.*;
import java.awt.*;
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
	private int currentHp, currentAttack, currentDefense, currentSpeed, currentSpecial; //the ones that may be lowered in battle
	private int hp, attack, defense, speed, special; //the base stat
	private ArrayList<Move> moves = new ArrayList<Move>(); //up to 4
	
	Pokemon(Species s, int level)
	{
		species = s;
		nickname = s.name();
		this.level = level;
		
		hp = s.hp();
		currentHp=hp;
		attack = s.attack();
		defense = s.defense();
		restoreStats();
	}
	
	public void restoreStats()
	{
		currentAttack = attack;
		currentDefense = defense;
		//etc
	}
		
	public String nickname(){return nickname;}		
	public Species species(){return species;}
	public int level(){return level;}
	public int xp(){return xp;}
}

