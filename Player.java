import java.util.*;

//you
class Player extends Battler {
	String name;
	List<Pokemon> party;
	List<Item> pack;
	Map<Pokemon,Boolean> seenIt;
	Map<Pokemon,Boolean> caughtIt;
}