package game;

import java.util.*;


public class Move
{

	private int number;
	private String name;
	private Type type;
	private String category;
	private String contest;
	private int pp;
	private int currentPp;
	private int power;
	private double accuracy;

	public int number(){return number;}
	public String name(){return name;}
	public int currentPp(){return currentPp;}
	public void setPp(int i){currentPp = i;}
	public Type type(){return type;}
	/**
	*Physical, Special, or Status
	*/
	public String category(){return category;}
	public int pp(){return pp;}
	public int power(){return power;}
	
	private Move(XmlElement e)
	{
		number = e.icontentOf("number");
		name = e.contentOf("name");
		type = Type.named(e.contentOf("type"));
		category = e.contentOf("category");
		contest = e.contentOf("contest");
		
		pp = e.icontentOf("pp");
		currentPp = pp;
		try{
			power = e.icontentOf("power");
		}catch(Exception ex){}


		//accuracy = new Double(new String(n.contentOf("accuracy")).replace("%", "")) % 100;
	}
	
	private static ArrayList<Move> moves = new ArrayList<Move>();
	static { 
		try{
			XmlElement root = XmlElement.documentRootFrom(Game.jarStream("species/moves.xml"));
			
			for(XmlElement e : root.children("move"))
				moves.add(new Move(e));

			System.out.println(moves.size()+" moves!");
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public static java.util.List<Move> all(){
		return moves;
	}
	/**
	*Ignores case, spaces or hypens.
	*/
	public static Move named(String name){
		name = normalize(name);
		for(Move m:moves)
			if(normalize(m.name).equals(name))
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
}