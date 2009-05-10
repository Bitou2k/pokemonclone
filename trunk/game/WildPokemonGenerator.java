package game;

import java.util.*;

class WildPokemonGenerator {

	private String name;
	private double chanceOfAnything;
	private List<Choice> choices = new LinkedList<Choice>(); 
	
	private class Choice{
		private PokedexPokemon pokemon;
		private int level;
		private double chance;
		
		private Choice(Node n){
			pokemon = PokedexPokemon.named(n.contentOf("name"));
			level = new Integer(n.contentOf("level"));
			chance = new Double(n.contentOf("chance"));
		}
		private Pokemon make(){return pokemon.makeWildAtLevel(level);}
	}
	
	private WildPokemonGenerator(Node n)
	{
		name = n.contentOf("name");
		chanceOfAnything = new Double(n.contentOf("overallchance"));
		for(Node cn: n.subnodes("pokemon"))
			choices.add(new Choice(cn));
		
		System.out.println("Loaded WildPokemonGenerator: "+name);
	}
	
	public static WildPokemonGenerator named(String name)
	{
		if(name.equals(""))return null;
		for(WildPokemonGenerator g: gens)
			if(g.name.equalsIgnoreCase(name)) return g;
		System.out.println("There is no wild generator named "+name);
		return null;
	}
	
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

		System.out.println("Error: generator "+name+" probabilities do not add up to 1");
		return null;		
	}
	
	private static ArrayList<WildPokemonGenerator> gens = new ArrayList<WildPokemonGenerator>();
	static { 
		try{
			Node root = Node.documentRootFrom("./generators.nml");
			for(Node n : root.subnodes("generator"))
				gens.add( new WildPokemonGenerator(n) );
				
			System.out.println(gens.size()+" generators!");
		}catch(Exception e){e.printStackTrace();}
	}
}