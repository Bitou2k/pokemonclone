package game;

import java.util.*;

public class Move {

	private int number;
	private String name;
	private Type type;
	private String category;
	private String contest;
	private int pp;
	private int currentPp;
	private int power;
	private double accuracy;
	
	private String compareName; //lowercased, removed spaces and hypens
	
	public int number(){return number;}
	public String name(){return name;}
	public int currentPp(){return currentPp;}
	public void setPp(int i){currentPp = i;}
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
		currentPp = pp;
		try{
			power = new Integer(n.contentOf("power"));
		}catch(Exception e){}


		//accuracy = new Double(new String(n.contentOf("accuracy")).replace("%", "")) % 100;
		
		compareName = normalize(name);
	}
	
	private static ArrayList<Move> moves = new ArrayList<Move>();
	static { 
		try{
			Node root = Node.documentRootFrom("./pokemon/moves.nml");
			
			for(Node n : root.subnodes("move")){
				Move m = new Move(n);
				moves.add(m);
				//System.out.print(m.name+" ");
			}
			System.out.println("\n"+moves.size()+" moves!");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static java.util.List<Move> all(){
		return moves;
	}
	public static Move named(String name){
		name = normalize(name);
		for(Move m:moves)
			if(m.compareName.equals(name))
				return m;
				
		System.out.println("No moved called "+name);
		return null;
	}
	
	private static String normalize(String x)
	{
		x = x.toUpperCase();
		x = x.replaceAll("-","");
		x = x.replaceAll(" ","");
		return x;
	}
	
	private static Type getTypeNamed(String n)
	{
		try{
			return Type.valueOf(n.toUpperCase());
		}catch(Exception ex){}
		return null;
	}
}