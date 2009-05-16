package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
*The shell for a game, a window containing a presenter.
*/
class Game extends JComponent implements KeyListener {

	//the current area, battle, or menu that is the root to be displayed and receive key events
	private Presenter currentPresenter;
	private Player player = new Player();
	private Map<Button,Boolean> bs = new HashMap<Button,Boolean>();
		
	private Game()
	{
		for(Button b: Button.values())
			bs.put(b,false);
	
		enterPresenter(new StartPresenter());
		
		new Thread(){
			public void run(){
				int ms=0;
				while(true) try{
					Thread.sleep(100);
					ms+=100;
					currentPresenter.step(ms);
					repaint();
				}catch(Exception e){}
			}
		}.start();
		
		setPreferredSize(new Dimension(16*20,16*18));
	}
	
	public void enterPresenter(Presenter p){
		if(currentPresenter!=null) currentPresenter.lostFocus();
		currentPresenter = p;
		p.initGame(this);
		p.gotFocus();
		repaint();
	}
	
	public Player player()
	{
		return player;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0,0,16*20,16*18);
		currentPresenter.drawOn((Graphics2D)g);
	}
	
	public boolean isDown(Button b)
	{
		return bs.get(b);
	}
	
	public void keyPressed(KeyEvent e){
		final Button b = buttonForEvent(e);
		bs.put(b,true);
		new Thread(){
			public void run(){
				currentPresenter.buttonPressed(b);
				repaint();
			}
		}.start();
	}
	public void keyReleased(KeyEvent e){
		Button b = buttonForEvent(e);
		bs.put(b,false);
	}
	public void keyTyped(KeyEvent e){}

	private Button buttonForEvent(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ENTER) return Button.A;
		if(e.getKeyCode()==KeyEvent.VK_A) return Button.A;
		if(e.getKeyCode()==KeyEvent.VK_Z) return Button.A;
		if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) return Button.B;
		if(e.getKeyCode()==KeyEvent.VK_B) return Button.B;
		if(e.getKeyCode()==KeyEvent.VK_X) return Button.B;
		if(e.getKeyCode()==KeyEvent.VK_UP) return Button.UP;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) return Button.DOWN;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) return Button.LEFT;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) return Button.RIGHT;
		if(e.getKeyCode()==KeyEvent.VK_Q) return Button.START;
		if(e.getKeyCode()==KeyEvent.VK_SHIFT) return Button.START;
		if(e.getKeyCode()==KeyEvent.VK_S) return Button.START;
		
		return null;
	}
	
	//in the Beginning, there was Main and it was Good
	public static void main(String[] args){
		
		TM.numbered(5);
		HM.numbered(5);
		
		JFrame f = new JFrame("PokemonClone!");
		Game g = new Game();
		f.addKeyListener(g);
		f.add(g);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		
		Area.named("route01");
		Area.named("saffron");
	}
}