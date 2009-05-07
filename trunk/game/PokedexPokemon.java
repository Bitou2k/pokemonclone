package game;

import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 *A prototype for generating a wild pokemon, evolving a pokemon, or viewing pokedex data.
 */
class PokedexPokemon {
	
	private String name;
	private String description;
	private Image image;
	private int number;
	private int hp; //max
	private Type type1, type2;
	private int attack, defense, spAttack, spDefense, speed; //the base stat
	private Map<Integer,String> moves; //A dictionary of level learned->move
	private Map<String,PokedexPokemon> evolutions;//A dictionary of condition(level,trade,stone)->pokedexpokemon
		
	public String name(){return name;}
	public String description(){return description;}
	public Image image(){return image;}
	public int number(){return number;}
		
	private PokedexPokemon(Node n)
	{	
		number = new Integer(n.contentOf("number"));
		name = n.contentOf("name");
		image = new ImageIcon("./icons/"+n.contentOf("image")).getImage();
		description = n.contentOf("description");
		type1 = getType(n.contentOf("type"));
		type2 = getType(n.contentOf("type2"));
		
		hp = new Integer(n.contentOf("hp"));
		attack = new Integer(n.contentOf("attack"));
		defense = new Integer(n.contentOf("defense"));
		spAttack = new Integer(n.contentOf("sp atk"));
		spDefense = new Integer(n.contentOf("sp def"));
		speed = new Integer(n.contentOf("speed"));
		
		
		//p.futureMoves = movesFromNode(n.subnode("moves"));
		
		System.out.println("Loaded PokedexPokemon: "+name);
	}
	
	/**
	*!Unimplemented.
	*/
	public Pokemon makeWildAtLevel(int level)
	{
		return null;
	}
		
	private static ArrayList<PokedexPokemon> pokemon = new ArrayList<PokedexPokemon>();
	static { loadPrototypes(); }
	
	private static void loadPrototypes(){
		try{
			Node root = Node.documentRootFrom("./pokemon.nml");
			for(Node n : root.subnodes("pokemon"))
				pokemon.add( new PokedexPokemon(n) );
				
			System.out.println(pokemon.size()+" pokemon!");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static java.util.List<PokedexPokemon> all(){
		return pokemon;
	}
	
	private static Type getType(String n)
	{
		try
		{
			return Type.valueOf(n.toUpperCase());
		}
		catch(Exception ex){}
		return null;
	}
	private static Map<Integer,String> movesFromNode(Node n){
		Map<Integer,String> moves = new TreeMap<Integer,String>();
		if(n!=null) for(Node m : n.subnodes("move"))
			moves.put(new Integer(m.contentOf("level")),m.contentOf("name"));
		return moves;
	}
	
	public static PokedexPokemon named(String name){
		for(PokedexPokemon p: pokemon)
			if(p.name.equals(name))return p;
		return null;
	}
	public static PokedexPokemon withNumber(int no){
		for(PokedexPokemon p: pokemon)
			if(p.number==no)return p;
		return null;
	}

}