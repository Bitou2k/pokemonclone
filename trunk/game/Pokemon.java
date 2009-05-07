package game;

import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 *A particular pokemon owned by the player or a rival trainer.
 */
class Pokemon {
	String name;
	String nickname;
	String description;
	Icon image;
	int pokedexNumber;
	int level;
	int currentHP;
	int HP; //max
	Status status;
	Type type;
	int currentAttack, currentDefence, currentSpeed, currentSpecial; //the ones that may be lowered in battle
	int attack, defence, speed, special; //the base stat
	ArrayList<Move> moves; //up to 4
		
}