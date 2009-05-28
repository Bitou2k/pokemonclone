package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RegularItem extends Item {

	private String effect;	
	private String statEffect;
	
	public RegularItem(Node n)
	{
		super(n);
		
		effect = n.contentOf("effect");     // what effect the item has
		statEffect = n.contentOf("stat");   // what stat heals/raises)
	}
	public void use()
	{
		//* does different things to player/etc. */
		//*  depending on effect and statEffect  */
		
	}

}
