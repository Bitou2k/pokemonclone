package game;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *Keeps track of which pokemon have been caught and seen.
 */
public class Pokedex
{
	
	private Set<Species> seen = new HashSet<Species>();
	private Set<Species> caught = new HashSet<Species>();
	
	public Pokedex(){
	
		//CHANGE THIS --we've seen all of the ones we've added--we've caught every other one
		boolean flag = true;
		for (Species s: Species.all()){
			addSeen(s);
			if(flag) addCaught(s);
			flag = !flag;
		}
	}
	
	public boolean hasSeen(Species s)
	{
		return seen.contains(s);
	}
	public boolean hasCaught(Species s)
	{
		return caught.contains(s);
	}
	
	public void addSeen(Pokemon p)
	{
		addSeen(p.species());
	}
	public void addSeen(Species s)
	{
		seen.add(s);
	}
	public void addCaught(Pokemon p)
	{
		addCaught(p.species());
	}
	public void addCaught(Species s)
	{
		caught.add(s);
	}
	
	public List<Species> allSeen()
	{
		return new LinkedList<Species>(seen);
	}
	public List<Species> allCaught()
	{
		return new LinkedList<Species>(caught);
	}

}