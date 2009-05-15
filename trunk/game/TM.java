package game;

import java.util.*;

public class TM {

	private int number;
	private Move move;
	
	public int number(){return number;}
	public Move move(){return move;}
	
	private TM(Node n)
	{
		number = new Integer(n.contentOf("number"));
		move = Move.named(n.contentOf("move"));
		
	}
	
	private static ArrayList<TM> tms = new ArrayList<TM>();
	static { 
		try{
			Node root = Node.documentRootFrom("./tmsAndHms.nml");
			for(Node n : root.subnodes("tm"))
			{
				TM t = new TM(n);
				tms.add(t);
				System.out.print(t.number+" ");
			}
				
			System.out.println("\n"+tms.size()+" TMs!");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static java.util.List<TM> all(){
		return tms;
	}
	public static TM numbered(int no){
		for(TM t:tms)
			if(t.number==no)
				return t;
		return null;
	}

}