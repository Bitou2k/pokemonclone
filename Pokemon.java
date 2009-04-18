
//a pokemon owned by the player or a rival trainer, or a prototype for generating a wild pokemon/evolving a pokemon
class Pokemon {
	String name;
	String nickname;
	int pokedexNumber;
	int level;
	int currentHP;
	int HP; //max
	Status status;
	Type type;
	int currentAttack, currentDefence, currentSpeed, currentSpecial; //the ones that may be lowered in battle
	int attack, defence, speed, special; //the base stat
	List<Move> moves; //up to 4
	Map<int,Move> futureMoves; //level learned -> move
	Map<int,Pokemon> futureEvolves; //level -> pokemon prototype
	
}