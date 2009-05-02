package game;

//a move in a pokemon, or a prototype to be copied when a new move is learned
class Move {

	String name;
	Type type;
	int currentPP;
	int PP;
	
	String compareName; //lowercased, removed spaces and hypens
}