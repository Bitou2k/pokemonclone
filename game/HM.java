package game;

import java.util.*;

public class HM extends LearnItem {

	private int number;
	private Move move;
	
	public int number(){return number;}
	public Move move(){return move;}
	
	private HM(Node n)
	{
		number = new Integer(n.contentOf("number"));
		move = Move.named(n.contentOf("name"));
	}
	
	private static ArrayList<HM> hms = new ArrayList<HM>();
	static { 
		try{
			Node root = Node.documentRootFrom("./pokemon/tmsAndHms.nml");
			for(Node n : root.subnodes("hm")){
				HM h = new HM(n);
				hms.add( h );
				//System.out.print(h.number+" ");
			}
				
			System.out.println("\n"+hms.size()+" HMs!");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static java.util.List<HM> allHMs(){
		return hms;
	}
	public static HM numbered(int no){
		for(HM h:hms)
			if(h.number==no)
				return h;
		return null;
	}

}