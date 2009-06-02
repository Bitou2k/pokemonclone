package game;

import java.util.*;

/**
*An item for teaching a pokemon a move, which can be used more than once.
*/
public class HM extends LearnItem
{

	private int number;
	private Move move;
	
	public int number(){return number;}
	public Move move(){return move;}
	
	private HM(XmlElement e)
	{
		number = e.icontentOf("number");
		move = Move.named(e.contentOf("name"));
	}
	
	private static ArrayList<HM> hms = new ArrayList<HM>();
	static { 
		try{
			XmlElement root = XmlElement.documentRootFrom(Game.jarStream("./species/tmsAndHms.xml"));
			for(XmlElement e : root.children("hm"))
				hms.add( new HM(e) );
				
			System.out.println(hms.size()+" HMs!");
		}catch(Exception ex){ex.printStackTrace();}
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
