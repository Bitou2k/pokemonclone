package game;

import java.util.*;
import java.awt.Image;
import javax.swing.*;

class Lottery
{
	List<Species> pokemon;
	int timesRun = 0;
	Random r;

	Lottery(int t)
	{
		r = new Random(System.currentTimeMillis());
		timesRun = t;
		pokemon = Species.all();
	}

	Pokemon nextPokemon()
	{
		if (timesRun < 3)
		{
			timesRun++;
			return (pokemon.get(r.nextInt(pokemon.size()))).makeWildAtLevel(3);
		}
		return null;
	}	

}