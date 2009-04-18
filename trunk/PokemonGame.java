
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//the shell for a Game
class PokemonGame extends JFrame implements KeyListener {

	//all the areas in a game
	private java.util.List<Area> areas; 
	
	//the current area, battle, or menu that is the root to be displayed and receive key events
	private ScreenOwner currentScreenOwner = PMenu.createStartMenu(); 
	
	//the player
	private Player ash;
	
	
	PokemonGame(){
		super("PokemonClone!");
		
		addKeyListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(320,240);
		setVisible(true);
	}
	
	public void paint(Graphics g){
		currentScreenOwner.drawOn((Graphics2D)g);
	}
	
	public void keyPressed(KeyEvent e){
		int n = e.getKeyCode();
		char c = e.getKeyChar();
		if(n==KeyEvent.VK_UP) c='W';
		if(n==KeyEvent.VK_LEFT) c='A';
		if(n==KeyEvent.VK_DOWN) c='S';
		if(n==KeyEvent.VK_RIGHT) c='D';
		currentScreenOwner.keyPressed(c);
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}//pressed and released

	
	
	//ew, static is the root of all evil
	public static void main(String[] args){
		new PokemonGame();
	}
}