package game;

import java.util.*;
import java.awt.Image;
import javax.swing.*;

/**
 *A prototype for generating a wild pokemon, evolving a pokemon, or viewing pokedex data.
 *
 *@author rmacnak
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
	private String growthRate;
	private int baseExp;
	
	public String name(){return name;}
	public int generation(){return generation;}
	public String description(){return description;}
	public Image image32(){return image32;}
	public Image image80(){return image80;}
	public Image imageFront(){return imageFront;}
	public Image imageBack(){return imageBack;}
	public int number(){return number;}
	/**
	*A value used in determing the experience for defeating a pokemon of this species.
	*/
	public int baseExp(){return baseExp;}
	
	/**
	*The total xp need to attain a given level.
	*/
	public int xpForLevel(int level)
	{
		double l=level;
		if(growthRate.equals("Slow")) return (int)(5.0 * l * l * l / 4.0);
		if(growthRate.equals("Medium")) return (int)(l * l * l);
		if(growthRate.equals("Fast")) return (int)(4.0 * l * l * l / 5.0);
		if(growthRate.equals("Parabolic")) return (int)(6.0 * l * l * l / 5.0 - 15.0 * l * l + 100.0 * l - 140.0);
		if(growthRate.equals("Erratic"))
		{
			if (l <= 50)
				return (int)(l*l*l * ((100.0 - l) / 50.0));
			else if (l <= 68)
				return (int)(l*l*l * ((150.0 - l) / 50.0));
			else if (l <= 98)
				return (int)(l*l*l * (1.274 - (1.0 / 50.0) * (l / 3.0)));
			else
				return (int)(l*l*l * ((160.0 - l) / 50.0));
		}
		else if(growthRate.equals("Fluctuating"))
		{
			if (l <= 15)
				return (int)(l*l*l * ((24.0 + ((l + 1.0) / 3.0)) / 50.0));
			else if (l <= 35)
				return (int)(l*l*l * ((14.0 + l) / 50.0));
			else
				return (int)(l*l*l * ((32.0 + (l / 2.0)) / 50.0));
		}
		else
		{
			System.out.println(name+" has invalid growth rate "+growthRate);
			return 0;
		}
	}
	
	
	/**
	*E.g., 004
	*/
	public String paddedNumber(){
		String x = number+"";
		while(x.length()<3) x="0"+x;
		return x;
	}
	public void cry()
	{
		try
		{
			String x = "species/cries/"+paddedNumber()+"Cry.mp3";
		
			javazoom.jl.player.advanced.AdvancedPlayer p = new javazoom.jl.player.advanced.AdvancedPlayer( Game.jarStream(x) );
			p.play();
		}
		catch(Exception ex)
		{
			System.err.println(name());
			ex.printStackTrace();
		}
	}
	
	public Type type(){return type1;}
	/**
	*Null for pokemon with only one type.
	*/
	public Type type2(){return type2;}
	
	public int baseHp(){return hp;}
	public int baseAttack(){return attack;}
	public int baseDefense(){return defense;}
	public int baseSpAttack(){return spAttack;}
	public int baseSpDefense(){return spDefense;}
	public int baseSpeed(){return speed;}
	
	public List<TM> learnableTMs(){return tms;}
	public List<HM> learnableHMs(){return hms;}
	
	public boolean canLearnTM(TM t){return tms.contains(t);}
	public boolean canLearnHM(HM h){return hms.contains(h);}
	
	/**
	*Not yet implemented.
	*/
	public Species firstForm(){return null;}
	/**
	*Not yet implemented.
	*/
	public Species finalForm(){return null;}
	
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
		
	private Species(XmlElement e)
	{	
		number = e.icontentOf("number");
		generation = e.icontentOf("generation");
		name = e.contentOf("name");
		image32 = Game.jarImage(e.contentOf("image32"));
		image80 = Game.jarImage(e.contentOf("image80"));
		imageFront = Game.jarImage(e.contentOf("imageFront"));
		imageBack = Game.jarImage(e.contentOf("imageBack"));
		
		description = e.contentOf("description");
		type1 = Type.named(e.contentOf("type"));
		type2 = Type.named(e.contentOf("type2"));
		growthRate = e.contentOf("growthRate");
		baseExp = e.icontentOf("baseExperience");
		
		hp = e.icontentOf("baseHp");
		attack = e.icontentOf("baseAttack");
		defense = e.icontentOf("baseDefense");
		spAttack = e.icontentOf("baseSpAttack");
		spDefense = e.icontentOf("baseSpDefense");
		speed = e.icontentOf("baseSpeed");
		
		for(XmlElement en: e.children("evolution")){
			String condition = en.contentOf("level");
			String evolvedForm = en.contentOf("target");
			if(evolvedForm.equals("")) continue;
			evolutions.put( condition, Species.named(evolvedForm) );
		}
		
		
		for(XmlElement mn: e.children("move"))
			moves.put(
				Move.named(mn.contentOf("name")),
				mn.icontentOf("level"));
		
		String[] tmsStr = e.contentOf("TMs").trim().split(",");
		for(String tmStr: tmsStr)
		{
			if(tmStr.equals("")) continue;
			tms.add( TM.numbered(new Integer(tmStr)) );
		}
		
		String[] hmsStr = e.contentOf("HMs").trim().split(",");
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
			XmlElement root = XmlElement.documentRootFrom(Game.jarStream("species/diamondSpecies.xml"));
			System.out.println(root.children().size());
			for(XmlElement e : root.children("species"))
				species.add(new Species(e));

			Collections.reverse(species);
			System.out.println(species.size()+" species!");
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public static List<Species> all(){
		return species;
	}
	
	/**
	*Case insensitive.
	*/
	public static Species named(String name){
		for(Species s: species)
			if(s.name.equalsIgnoreCase(name))return s;
		System.out.println("No species named "+name);
		return null;
	}
	public static Species withNumber(int no){
		for(Species s: species)
			if(s.number==no)return s;
		System.out.println("No species numbered "+no);
		return null;
	}

}