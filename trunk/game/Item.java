package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//a thing in your pack
public abstract class Item
{

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
	
	public Item(){}

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