
import java.util.*;
import java.awt.*;
import javax.swing.*;

//a pokemon owned by the player or a rival trainer, or a prototype for generating a wild pokemon/evolving a pokemon
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
	Map<Integer,Move> futureMoves; //level learned -> move
	Map<Integer,Pokemon> futureEvolves; //level -> pokemon prototype
	
	
	static ArrayList<Pokemon> prototypes = new ArrayList<Pokemon>();
	static{
		try{
			Node root = Node.documentRootFrom("pokemon.nml");
			
			for(Node pokemonN : root.childrenNamed("pokemon")){
				Pokemon p = new Pokemon();
				p.pokedexNumber = new Integer(pokemonN.contentOf("number"));
				p.name = pokemonN.contentOf("name");
				p.image = new ImageIcon("./icons/"+pokemonN.contentOf("image"));
				p.description = pokemonN.contentOf("description");
				
				prototypes.add(p);
			}
			
		}catch(Exception e){e.printStackTrace();}
	}
	
}