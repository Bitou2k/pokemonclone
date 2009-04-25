
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//the shell for a Game
class PokemonGame extends JFrame implements KeyListener {

	//all the areas in a game
	private java.util.List<Area> areas; 
	
	//the current area, battle, or menu that is the root to be displayed and receive key events
	private Presenter currentPresenter;
	
	//the player
	private Player ash;
	
	
	PokemonGame(){
		super("PokemonClone!");
		
		addKeyListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(320,240);
		setVisible(true);
		
		enterPresenter(new StartScreen());
		//enterPresenter(new Area());
		
		new Thread(){
			public void run(){
				while(true) try{
					Thread.sleep(1000);
					currentPresenter.step();
					repaint();
				}catch(Exception e){}
				
			}
		}.start();
	}
	
	public void enterPresenter(Presenter p){
		currentPresenter = p;
		p.setShell(this);
		repaint();
	}
	
	public synchronized void paint(Graphics g){
		g.translate(4,30); //origin as the visible part, not the corner hidden under the title bar
		g.setColor(Color.GRAY);
		g.fillRect(0,0,320,240);
		currentPresenter.drawOn((Graphics2D)g);
	}
	
	public void keyPressed(KeyEvent e){
		int n = e.getKeyCode();
		char c = e.getKeyChar();
		if(n==KeyEvent.VK_UP) c='W';
		if(n==KeyEvent.VK_LEFT) c='A';
		if(n==KeyEvent.VK_DOWN) c='S';
		if(n==KeyEvent.VK_RIGHT) c='D';
		currentPresenter.keyPressed(c);
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}//pressed and released

	
	
	//ew, static is the root of all evil
	public static void main(String[] args){
		new PokemonGame();
	}
}