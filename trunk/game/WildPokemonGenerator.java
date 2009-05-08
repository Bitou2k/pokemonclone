package game;

import java.util.*;

class WildPokemonGenerator {

	private String name;
	private double chanceOfAnything;
	private List<Choice> choices; 
	
	private class Choice{
		private String pokemon;
		private int level;
		private double chance;
	}
	
	private WildPokemonGenerator(Node n)
	{
		name = n.contentOf("name");
		
		System.out.println("Loaded WildPokemonGenerator: "+name);
	}
	
	public static WildPokemonGenerator named(String name)
	{
		for(WildPokemonGenerator g: gens)
			if(g.name.equals(name)) return g;
		return null;
	}
	
	public Pokemon generatePokemon()
	{
	System.out.println("xxxx");
		//LAZY IMPL FOR NOW
		if(Math.random()<0.1)
			return PokedexPokemon.named("Mewtwo").makeWildAtLevel(70);
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