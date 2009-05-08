package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//the shell for a Game
class PokemonGame extends JComponent implements KeyListener {

	//all the areas in a game
	private java.util.List<Area> areas; 
	
	//the current area, battle, or menu that is the root to be displayed and receive key events
	private Presenter currentPresenter;
	
	//the player
	private Player ash;
	
	
	PokemonGame(){
	
		Move.named("x");
		
		enterPresenter(new StartPresenter());
		
		Area.named("");
		
		new Thread(){
			public void run(){
				while(true) try{
					Thread.sleep(1000);
					currentPresenter.step();
					repaint();
				}catch(Exception e){}
			}
		}.start();
		
		setPreferredSize(new Dimension(16*20,16*18));
	}
	
	public void enterPresenter(Presenter p){
		currentPresenter = p;
		p.setShell(this);
		repaint();
	}
	
	public synchronized void paint(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0,0,16*20,16*18);
		currentPresenter.drawOn((Graphics2D)g);
	}
	
	public void keyPressed(KeyEvent e){
		int n = e.getKeyCode();
		char c = e.getKeyChar();
		c = Character.toUpperCase(c);
		if(n==KeyEvent.VK_UP) c='W';
		if(n==KeyEvent.VK_LEFT) c='A';
		if(n==KeyEvent.VK_DOWN) c='S';
		if(n==KeyEvent.VK_RIGHT) c='D';
		if(n==KeyEvent.VK_ENTER) c='A';
		if(n==KeyEvent.VK_BACK_SPACE) c='B';
		if(c=='Z') c='A';
		if(c=='X') c='B';
		currentPresenter.keyPressed(c);
		repaint();
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}//pressed and released

	
	
	//in the Beginning, there was Main and it was Good
	public static void main(String[] args){
		
		JFrame f = new JFrame("PokemonClone!");
		PokemonGame pg = new PokemonGame();
		f.addKeyListener(pg);
		f.add(pg);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
	}
}