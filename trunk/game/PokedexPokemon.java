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
	private Image image32, image80, imageFront, imageBack;
	private int number;
	private Type type1, type2;
	private int hp, attack, defense, spAttack, spDefense, speed; //the base stat
	private Map<String,Integer> moves; //A dictionary of move->level learned
	private Map<String,PokedexPokemon> evolutions;//A dictionary of condition(level,trade,stone)->pokedexpokemon
		
	public String name(){return name;}
	public String description(){return description;}
	public Image image32(){return image32;}
	public Image image80(){return image80;}
	public Image imageFront(){return imageFront;}
	public Image imageBack(){return imageBack;}
	public int number(){return number;}
	
	public Type type(){return type1;}
	/**
	*Null for pokemon with only one type.
	*/
	public Type type2(){return type2;}
	
	
	public int hp(){return hp;}
	public int attack(){return attack;}
	public int defense(){return defense;}
	public int spAttack(){return spAttack;}
	public int spDefense(){return spDefense;}
	public int speed(){return speed;}
	
	/**
	*Returns a dictionary of MOVELEARN==>LEVELLEARNED.
	*/
	public Map<String,Integer> moveslearned(){return moves;}
	public java.util.List<String> movesLearnedAtLevel(int level)
	{
		LinkedList<String> ms = new LinkedList<String>();
		for(String m: moves.keySet())
		{
			int l = moves.get(m);
			if(l==level) ms.add(m);
		}
		return ms;
	}
	
	/**
	*Returns a dictionary of EVOLVECONDITION(level,trade,stone)==>POKEMONEVOLVEDINTO.
	*/
	public Map<String,PokedexPokemon> evolutions(){return evolutions;}
		
	private PokedexPokemon(Node n)
	{	
		number = new Integer(n.contentOf("number"));
		name = n.contentOf("name");
		image32 = new ImageIcon("./icons/"+n.contentOf("image")).getImage();
		image80 = new ImageIcon(n.contentOf("image80")).getImage();
		imageFront = new ImageIcon(n.contentOf("imageFront")).getImage();
		imageBack = new ImageIcon(n.contentOf("imageBack")).getImage();
		
		description = n.contentOf("description");
		type1 = getTypeNamed(n.contentOf("type"));
		type2 = getTypeNamed(n.contentOf("type2"));
		
		hp = new Integer(n.contentOf("hp"));
		attack = new Integer(n.contentOf("attack"));
		defense = new Integer(n.contentOf("defense"));
		spAttack = new Integer(n.contentOf("sp atk"));
		spDefense = new Integer(n.contentOf("sp def"));
		speed = new Integer(n.contentOf("speed"));
		
		
		//p.futureMoves = movesFromNode(n.subnode("moves"));
		
		System.out.println("Loaded PokedexPokemon: "+name);
	}
	
	public Pokemon makeWildAtLevel(int level)
	{
		return new Pokemon(this,level);
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
	
	/*public static java.util.List<PokedexPokemon> all(){
		return pokemon;
	}*/
	public static ArrayList<PokedexPokemon> all(){
		return pokemon;
	}
	
	private static Type getTypeNamed(String n)
	{
		try{
			return Type.valueOf(n.toUpperCase());
		}catch(Exception ex){}
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
			if(p.name.equalsIgnoreCase(name))return p;
		System.out.println("There is no pokemon named "+name);
		return null;
	}
	public static PokedexPokemon withNumber(int no){
		for(PokedexPokemon p: pokemon)
			if(p.number==no)return p;
		System.out.println("There is no pokemon numbered "+no);
		return null;
	}

}