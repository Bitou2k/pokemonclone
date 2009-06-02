package game;

import java.util.*;

/**
*A set of species and the probablities the player will encouter each associated with pokegrass, cave, or water.
*/
public class EncounterSet 
{
	private String name;
	private double chanceOfAnything;
	private List<Choice> choices = new LinkedList<Choice>(); 
	
	private class Choice{
		private Species pokemon;
		private int level;
		private double chance;
		
		private Choice(XmlElement e){
			pokemon = Species.named(e.contentOf("name"));
			level = e.icontentOf("level");
			chance = e.dcontentOf("chance");
		}
		private Pokemon make(){return pokemon.makeWildAtLevel(level);}
	}
	
	private EncounterSet(XmlElement e)
	{
		name = e.contentOf("name");
		chanceOfAnything = e.dcontentOf("overallchance");
		for(XmlElement ce: e.children("pokemon"))
			choices.add(new Choice(ce));
		
		System.out.println("Loaded EncounterSet: "+name);
	}
	
	public static EncounterSet named(String name)
	{
		if(name.equals(""))return null;
		for(EncounterSet g: gens)
			if(g.name.equalsIgnoreCase(name)) return g;
		System.out.println("No encounter set "+name);
		return null;
	}
	
	/**
	*Return a Pokemon or null, depending on the chance of getting each species or anything.
	*/
	public Pokemon generatePokemon()
	{
		if( Math.random() > chanceOfAnything) return null;
		
		double r = Math.random();
		double t = 0.0;
		
		for(Choice c: choices)
		{
			t = t + c.chance;
			if(t >= r)
			{
				return c.make();
			}
		}

		System.out.println("EncounterSet "+name+" probabilities do not add up to 1!");
		return null;		
	}
	
	private static ArrayList<EncounterSet> gens = new ArrayList<EncounterSet>();
	static 
	{ 
		try{
			XmlElement root = XmlElement.documentRootFrom(Game.jarStream("./encounterSets.xml"));
			for(XmlElement e : root.children("encounterSet"))
				gens.add( new EncounterSet(e) );
				
			System.out.println(gens.size()+" encounter!");
		}catch(Exception e){e.printStackTrace();}
	}
}