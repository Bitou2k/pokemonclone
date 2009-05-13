package browser;

import java.util.*;

class Pokemon
{
	public enum ImageType
	{
		IMG32, IMG80, IMGFRONT, IMGBACK;
	}

	public int number;
	public int hp;
	public int attack;
	public int defense;
	public int spAttack;
	public int spDefense;
	public int speed
	
	public ImageIcon img32;
	public ImageIcon img80;
	public ImageIcon imgFront;
	public ImageIcon imgBack;
	
	public String img32Path;
	public String img80Path;
	public String imgFrontPath;
	public String imgBackPath;
	public String name;
	public String description;
	public String type1;
	public String type2;

	public Map<int, String> moves;
	
	public ArrayList<int> tm;
	public ArrayList<int> hm;
	
	public Pokemon(Node nmlNode)
	{
	}
	
	public Node makeNode()
	{
		Node newNode = new Node();
		return newNode;
	}
	
	public void loadImage(path, ImageType)
	{
	}
	
	private Node makeMovesNode()
	{
	}
}