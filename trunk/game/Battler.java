
package game;

import java.util.*;

/**
*A rival trainer.
*/
class Battler extends Citizen
{
	
	private List<Pokemon> party = new LinkedList<Pokemon>();
	
	/**
	*A party is the set of up to 6 pokemon a trainer may carry with them.
	*/
	public List<Pokemon> party() { return party; }

}