package game;

import java.util.*;
import java.awt.Image;
import javax.swing.*;

/**
 *A prototype for generating a wild pokemon, evolving a pokemon, or viewing pokedex data.
 */
public class Species {
	
	private String name;
	private int generation; //1=RBY,2=GSC,3,4
	private String description;
	private Image image32, image80, imageFront, imageBack;
	private int number;
	private Type type1, type2;
	private int hp, attack, defense, spAttack, spDefense, speed; 
	private Map<Move,Integer> moves = new HashMap<Move,Integer>();
	//condition(level,trade,stone)-->pokedexpokemon
	private Map<String,Species> evolutions = new HashMap<String,Species>();
	private List<TM> tms = new LinkedList<TM>();
	private List<HM> hms = new LinkedList<HM>();
	
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
	
	public List<TM> learnableTMs(){return tms;}
	public List<HM> learnableHMs(){return hms;}
	
	public boolean canLearnTM(TM t){return tms.contains(t);}
	public boolean canLearnHM(HM h){return hms.contains(h);}
	
	//public Species firstForm(){return null;}
	
	/**
	*MOVELEARNED==>LEVELLEARNED.
	*/
	public Map<Move,Integer> movesLearned(){return moves;}
	public List<Move> movesLearnedAtLevel(int level)
	{
		LinkedList<Move> ms = new LinkedList<Move>();
		for(Move m: moves.keySet())
		{
			int l = moves.get(m);
			if(l==level) ms.add(m);
		}
		return ms;
	}
	
	/**
	*EVOLVECONDITION(level,trade,stone)==>POKEMONEVOLVEDINTO.
	*/
	public Map<String,Species> evolutions(){return evolutions;}
		
	private Species(Node n)
	{	
		number = new Integer(n.contentOf("number"));
		name = n.contentOf("name");
		image32 = new ImageIcon(n.contentOf("image32")).getImage();
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
		
		for(Node en: n.subnodes("evolution")){
			String condition = en.contentOf("level");
			String evolvedForm = en.contentOf("target");
			if(evolvedForm.equals("")) continue;
			evolutions.put( condition, Species.named(evolvedForm) );
		}
		
		if(n.subnode("moves")!=null)
		for(Node mn: n.subnode("moves").subnodes("move"))
		{
			moves.put(
				Move.named(mn.contentOf("name")) , mn.icontentOf("level")
			);
		}
		else System.out.println("missing move data");
		
		String[] tmsStr = n.contentOf("TMs").trim().split(",");
		for(String tmStr: tmsStr)
		{
			if(tmStr.equals("")) continue;
			tms.add( TM.numbered(new Integer(tmStr)) );
		}
		
		String[] hmsStr = n.contentOf("HMs").trim().split(",");
		for(String hmStr: hmsStr)
		{
			if(hmStr.equals("")) continue;
			hms.add( HM.numbered(new Integer(hmStr)) );
		}
	}
	
	public Pokemon makeWildAtLevel(int level)
	{
		return new Pokemon(this,level);
	}
		
	private static ArrayList<Species> species = new ArrayList<Species>();
	static { 
		try{
			Node root = Node.documentRootFrom("./pokemon/pokemon.nml");
			for(Node n : root.subnodes("pokemon")){
				Species s = new Species(n);
				species.add( s );
				System.out.print(s.name+" ");
			}
			Collections.reverse(species);
			System.out.println("\n"+species.size()+" species!");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static java.util.List<Species> all(){
		return species;
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
	
	public static Species named(String name){
		for(Species s: species)
			if(s.name.equalsIgnoreCase(name))return s;
		System.out.println("There is no species named "+name);
		return null;
	}
	public static Species withNumber(int no){
		for(Species s: species)
			if(s.number==no)return s;
		System.out.println("There is no species numbered "+no);
		return null;
	}

}