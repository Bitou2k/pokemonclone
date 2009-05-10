package game;

import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 *A particular pokemon owned by the player or a rival trainer.
 */
class Pokemon {
	private PokedexPokemon prototype;
	private String nickname;
	private int level;
	private Status status = Status.NORMAL;
	private int currentHp, currentAttack, currentDefense, currentSpeed, currentSpecial; //the ones that may be lowered in battle
	private int hp, attack, defense, speed, special; //the base stat
	private ArrayList<Move> moves = new ArrayList<Move>(); //up to 4
	
	Pokemon(PokedexPokemon proto, int level)
	{
		prototype = proto;
		nickname = proto.name();
		this.level = level;
		
		hp = proto.hp();
		currentHp=hp;
		attack = proto.attack();
		defense = proto.defense();
		restoreStats();
	}
	
	public void restoreStats()
	{
		currentAttack = attack;
		currentDefense = defense;
		//etc
	}
		
	public String nickname(){return nickname;}		
	public PokedexPokemon prototype(){return prototype;}
}

