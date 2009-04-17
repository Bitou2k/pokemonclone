
class PokemonGame extends JFrame {

	PokemonGame(){
		super("Pokemon!");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(320,240);
		setVisible(true);
	}

	public static void main(String[] args){
		new PokemonGame();
	}
}