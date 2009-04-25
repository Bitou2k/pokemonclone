
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
	Map<Integer,String> futureMoves; //level learned -> move
	Map<Integer,Pokemon> futureEvolves; //level -> pokemon prototype
	

	
	static ArrayList<Pokemon> prototypes = new ArrayList<Pokemon>();
	static { loadPrototypes(); }
	
	static void loadPrototypes(){
		try{
			Node root = Node.documentRootFrom("pokemon.nml");
			for(Node n : root.subnodes("pokemon"))
				prototypes.add( pokemonFromNode(n) );
		}catch(Exception e){e.printStackTrace();}
	}
	
	static Pokemon pokemonFromNode(Node n){
		Pokemon p = new Pokemon();
		p.pokedexNumber = new Integer(n.contentOf("number"));
		p.name = n.contentOf("name");
		//p.type = Enum.valueOf(Type.class,n.contentOf("type"));
		p.image = new ImageIcon("./icons/"+n.contentOf("image"));
		p.description = n.contentOf("description");
		p.futureMoves = movesFromNode(n.subnode("moves"));
		return p;		
	}
	static Map<Integer,String> movesFromNode(Node n){
		Map<Integer,String> moves = new TreeMap<Integer,String>();
		if(n!=null) for(Node m : n.subnodes("move"))
			moves.put(new Integer(m.contentOf("level")),m.contentOf("name"));
		return moves;
	}
	
	static Pokemon pokemonNamed(String name){
		for(Pokemon p: prototypes)
			if(p.name.equals(name))return p;
		return null;
	}
	static Pokemon pokemonNumber(int no){
		for(Pokemon p: prototypes)
			if(p.pokedexNumber==no)return p;
		return null;
	}
	
}