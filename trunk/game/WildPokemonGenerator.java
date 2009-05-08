package game;

import java.util.*;

class WildPokemonGenerator {

	private String name;
	private double chanceOfAnything;
	private Map<PokedexPokemon,Double> pairs; //pokemon prototype->probobility
	
	private WildPokemonGenerator(Node n)
	{
		name = n.contentOf("name");
		
		System.out.println("Loaded WildPokemonGenerator: "+name);
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