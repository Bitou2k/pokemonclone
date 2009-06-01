package game;

import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
*a thing in your pack
*/
public abstract class Item
{
	private Image image;
	private String name;
	private String type;
	private String description;
	private double price;

	//constructor
	public Item(Node n)
	{
		//name = n.contentOf("name");
		//price = Double.parseDouble(n.contentOf("price"));
		//type = n.contentOf("type");
		//description = n.contentOf("desc");
	}
	
	public void image(Image i){image = i;}

	public Item(String n, String t, String d, double p){
		image = new ImageIcon("./entityImages/Item.png").getImage();
		name = n;
		type = t;
		description = d;
		price = p;
	}

	public void drawOn(Graphics2D g)
	{
		g.drawImage(image, 0, 0, null);
	}

	//return name
	public String getName()
	{
		return name;
	}
	
	public String description()
	{
		return description;
	}

	/**item use: subclasses should override*/
	public void use(){}
	
	private static ArrayList<Item> items = new ArrayList<Item>();
	static { 
		try{
			
			Node root = Node.documentRootFrom("itemtest.nml");
			for(Node n : root.subnodes("item")){
				Item i = new RegularItem(n);
				items.add( i );
				System.out.print(i.name+" ");
			}
			/*root = Node.documentRootFrom("---REPLACE WITH KEY ITEM DATABASE PATH---");
			for(Node n : root.subnodes("item")){
				Item i = new KeyItem(n);
				items.add( i );
				System.out.print(i.name+" ");
			}			
			root = Node.documentRootFrom("---REPLACE WITH POKEBALL DATABASE PATH---");
			for(Node n : root.subnodes("item")){
				Item i = new Pokeball(n);
				items.add( i );
				System.out.print(i.name+" ");
			}*/
			
			Collections.reverse(items);
			System.out.println("\n"+items.size()+" items!");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static List<Item> all(){
		return items;
	}
	
	public static Item named(String name){
		for(Item i: items)
			if(i.name.equalsIgnoreCase(name))return i;
		System.out.println("There is no item named "+name);
		return null;
	}
	
	static Item fromNode(String n)
	{
		return null;
	}
}