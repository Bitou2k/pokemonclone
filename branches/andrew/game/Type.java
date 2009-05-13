
package game;

public enum Type {
	NORMAL {
		double coefOn(Type defender){ 
			if (defender==ROCK || defender ==STEEL)
				return .5;
			else if (defender==GHOST)
				return 0;
			else
				return 1;
		}
	},
	FIRE{
		double coefOn(Type defender){ 
			if (defender==GRASS || defender==ICE || defender==STEEL || defender ==BUG)
				return 2.;
			else if (defender==FIRE || defender==WATER || defender==GROUND || defender == ROCK || defender == DRAGON )
				return .5;
			else
				return 1;
		}
	},
	WATER{
		double coefOn(Type defender){ 
			if (defender==FIRE || defender == GROUND || defender == ROCK)
				return 2;
			else if (defender==WATER || defender ==GRASS || defender==DRAGON)
				return .5;
			else
				return 1;
		}
	},
	ELECTRIC{
		double coefOn(Type defender){ 
			if (defender==WATER || defender == FLYING)
				return 2;
			else if (defender==DRAGON || defender==GRASS || defender ==ELECTRIC)
				return .5;
			else if (defender == GROUND)
				return 0;
			else
				return 1;
		}
	},
	GRASS{
		double coefOn(Type defender){ 
			if (defender==WATER || defender==ROCK || defender==GROUND)
				return 2.;
			else if (defender==FIRE || defender == GRASS || defender==FLYING 
					|| defender == DRAGON || defender==BUG|| defender==STEEL
					|| defender==POISON)
				return .5;
			else
				return 1;
		}
	},
	ICE{
		double coefOn(Type defender){ 
			if (defender==GRASS|| defender==GROUND|| defender==FLYING|| defender==DRAGON)
				return 2.;
			else if (defender==FIRE|| defender==STEEL|| defender==ICE|| defender==WATER)
				return .5;
			else
				return 1;
		}
	},
	FIGHTING{
		double coefOn(Type defender){ 
			if (defender==NORMAL|| defender==ICE|| defender==ROCK|| defender==DARK|| defender==STEEL)
				return 2.;
			else if (defender==BUG|| defender==POISON|| defender==FLYING|| defender==PSYCHIC)
				return .5;
			else if (defender==GHOST)
				return 0;
			else
				return 1;
		}
	},
	POISON{
		double coefOn(Type defender){ 
			if (defender==GRASS|| defender==BUG)
				return 2.;
			else if (defender==POISON|| defender==ROCK|| defender==GROUND|| defender==GHOST)
				return .5;
			else if (defender==STEEL)
				return 0;
			else
				return 1;
		}
	},
	GROUND{
		double coefOn(Type defender){ 
			if (defender==FIRE|| defender==ELECTRIC|| defender==POISON|| defender==ROCK|| defender==STEEL)
				return 2.;
			else if (defender==GRASS|| defender==BUG)
				return .5;
			else if (defender==FLYING)
				return 0;
			else
				return 1;
		}
	},
	FLYING{
		double coefOn(Type defender){ 
			if (defender==GRASS|| defender==FIGHTING|| defender==BUG)
				return 2.;
			else if (defender==ELECTRIC|| defender==ROCK|| defender==STEEL)
				return .5;
			else
				return 1;
		}
	},
	PSYCHIC{
		double coefOn(Type defender){ 
			if (defender==FIGHTING|| defender==POISON)
				return 2.;
			else if (defender==STEEL|| defender==PSYCHIC)
				return .5;
			else if ( defender==DARK)
				return 0;
			else
				return 1;
		}
	},
	BUG{
		double coefOn(Type defender){ 
			if (defender==GRASS|| defender==PSYCHIC|| defender==DARK)
				return 2.;
			else if (defender==FIRE|| defender==FIGHTING|| defender==POISON
					|| defender==FLYING|| defender==GHOST|| defender==STEEL)
				return .5;
			else
				return 1;
		}
	},
	ROCK{
		double coefOn(Type defender){ 
			if (defender==FIRE|| defender==ICE|| defender==FLYING|| defender==BUG)
				return 2.;
			else if (defender==FIGHTING|| defender==GROUND|| defender==STEEL)
				return .5;
			else
				return 1;
		}
	},
	GHOST{
		double coefOn(Type defender){ 
			if (defender==PSYCHIC|| defender==GHOST)
				return 2.;
			else if (defender==DARK|| defender==STEEL)
				return .5;
			else if (defender==NORMAL)
				return 0;
			else
				return 1;
		}
	},
	DRAGON{
		double coefOn(Type defender){ 
			if (defender==DRAGON)
				return 2.;
			else if (defender==STEEL)
				return .5;
			else
				return 1;
		}
	},
	DARK{
		double coefOn(Type defender){ 
			if (defender==PSYCHIC|| defender==GHOST)
				return 2.;
			else if (defender==DARK|| defender==STEEL)
				return .5;
			else
				return 1;
		}
	},
	STEEL{
		double coefOn(Type defender){ 
			if (defender==ROCK|| defender==ICE)
				return 2.;
			else if (defender==FIRE|| defender==WATER|| defender==ELECTRIC|| defender==STEEL)
				return .5;
			else
				return 1;
		}
	};
	
	//double ceofOn(Type defender){return 4.0;}
}