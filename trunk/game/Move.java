package game;

import java.util.*;

public class Move {

	private int number;
	private String name;
	private Type type;
	private String category;
	private String contest;
	private int pp;
	private int power;
	private double accuracy;
	
	private String compareName; //lowercased, removed spaces and hypens
	
	public int number(){return number;}
	public String name(){return name;}
	public Type type(){return type;}
	/**
	*Physical, special, etc
	*/
	public String category(){return category;}
	public int pp(){return pp;}
	public int power(){return power;}
	
	private Move(Node n)
	{
		number = new Integer(n.contentOf("number"));
		name = n.contentOf("name");
		type = getTypeNamed(n.contentOf("type"));
		category = n.contentOf("category");
		contest = n.contentOf("contest");
		
		pp = new Integer(n.contentOf("pp"));
		//power = new Integer(n.contentOf("power"));
		//accuracy = new Double(n.contentOf("accuracy"));
		
		compareName = name.toLowerCase();
		
		System.out.println("Loaded Move: "+name);
	}
	
	private static ArrayList<Move> moves = new ArrayList<Move>();
	static { 
		try{
			Node root = Node.documentRootFrom("./moves.nml");
			for(Node n : root.subnodes("move"))
				moves.add( new Move(n) );
				
			System.out.println(moves.size()+" moves!");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static java.util.List<Move> all(){
		return moves;
	}
	public static Move named(String name){
		name = name.toLowerCase();
		for(Move m:moves)
			if(m.compareName.equals(name))
				return m;
		return null;
	}
	
	private static Type getTypeNamed(String n)
	{
		try{
			return Type.valueOf(n.toUpperCase());
		}catch(Exception ex){}
		return null;
	}
}