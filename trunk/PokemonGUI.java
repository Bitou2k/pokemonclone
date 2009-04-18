
//the shell for a Game
class PokemonGUI extends JFrame {

	//the current game
	Game g;
	
	PokemonGame(){
		super("Pokemon!");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(320,240);
		setVisible(true);
	}
	
	public void paint(Graphics g){
		
	}

	
	
	//ew, static is the root of all evil
	public static void main(String[] args){
		new PokemonGame();
	}
}