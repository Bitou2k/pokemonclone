package game;

//a thing in your pack
public abstract class Item
{

	private String name;

	//constructor
	public Item(String n)
	{
		name = n;
	}

	//retrun name
	public String getName()
	{
		return name;
	}

	//item use
	public abstract void use();
	
	
	static Item fromNode(String n)
	{
		return null;
	}
}