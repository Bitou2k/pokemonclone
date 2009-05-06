package game;

import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 *A prototype for generating a wild pokemon, evolving a pokemon, or viewing pokedex data.
 */
class PokedexPokemon {
	String name;
	String description;
	Icon image;
	int pokedexNumber;
	int HP; //max
	Type type;
	int attack, defence, speed, special; //the base stat
	
	/**
	*A dictionary of level learned->move
	*/
	Map<Integer,String> moves; 
	/**
	*A dictionary of level evovled->pokedexpokemon
	*/
	Map<Integer,PokedexPokemon> evolutions;
		
}