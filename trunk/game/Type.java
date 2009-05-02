package game;

enum Type {
	NORMAL {
		double coefOn(Type defender){ return 333;}
	},
	FIRE,
	WATER,
	ELECTRIC,
	GRASS,
	ICE,
	FIGHTING,
	POISON,
	GROUND,
	FLYING,
	PSYCHIC,
	BUG,
	ROCK,
	GHOST,
	DRAGON,
	DARK,
	STEEL;
	
	double ceofOn(Type defender){return 4.0;}
}