package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import javax.sound.midi.*;
import java.io.*;

/**
*The shell for a game, a window containing a presenter.
*/
class Game extends JComponent implements KeyListener {

	//the current area, battle, or menu that is the root to be displayed and receive key events
	protected Presenter currentPresenter;
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
	
	Sequencer sm_sequencer = null;
	Synthesizer sm_synthesizer = null;
	void backgroundMusic(String filename)
	{
	if(5>3) return;
			try
		{
			sm_sequencer.stop();
			sm_sequencer.close();
		}
		catch(Exception e){}
		////////////////
		
		if(filename.equals(""))return;
		
		File	midiFile = new File(filename);

		/*
		 *	We read in the MIDI file to a Sequence object.
		 *	This object is set at the Sequencer later.
		 */
		Sequence	sequence = null;
		try
		{
			sequence = MidiSystem.getSequence(midiFile);
		}
		catch (InvalidMidiDataException e)
		{
			/*
			 *	In case of an exception, we dump the exception
			 *	including the stack trace to the console.
			 *	Then, we exit the program.
			 */
			e.printStackTrace();
			//System.exit(1);
		}
		catch (IOException e)
		{
			/*
			 *	In case of an exception, we dump the exception
			 *	including the stack trace to the console.
			 *	Then, we exit the program.
			 */
			e.printStackTrace();
			//System.exit(1);
		}

		/*
		 *	Now, we need a Sequencer to play the sequence.
		 *	Here, we simply request the default sequencer.
		 */
		try
		{
			sm_sequencer = MidiSystem.getSequencer();
		}
		catch (MidiUnavailableException e)
		{
			e.printStackTrace();
			//System.exit(1);
		}
		if (sm_sequencer == null)
		{
			//out("SimpleMidiPlayer.main(): can't get a Sequencer");
			//System.exit(1);
		}

		/*
		 *	There is a bug in the Sun jdk1.3/1.4.
		 *	It prevents correct termination of the VM.
		 *	So we have to exit ourselves.
		 *	To accomplish this, we register a Listener to the Sequencer.
		 *	It is called when there are "meta" events. Meta event
		 *	47 is end of track.
		 *
		 *	Thanks to Espen Riskedal for finding this trick.
		 */
		sm_sequencer.addMetaEventListener(new MetaEventListener()
			{
				public void meta(MetaMessage event)
				{
					if (event.getType() == 47)
					{
						sm_sequencer.close();
						if (sm_synthesizer != null)
						{
							sm_synthesizer.close();
						}
						//System.exit(0);
						//l.playbackCompleted(a);
					}
				}
			});

		/*
		 *	The Sequencer is still a dead object.
		 *	We have to open() it to become live.
		 *	This is necessary to allocate some ressources in
		 *	the native part.
		 */
		try
		{
			sm_sequencer.open();
		}
		catch (MidiUnavailableException e)
		{
			e.printStackTrace();
			//System.exit(1);
		}

		/*
		 *	Next step is to tell the Sequencer which
		 *	Sequence it has to play. In this case, we
		 *	set it as the Sequence object created above.
		 */
		try
		{
			sm_sequencer.setSequence(sequence);
		}
		catch (InvalidMidiDataException e)
		{
			e.printStackTrace();
			//System.exit(1);
		}

		/*
		 *	Now, we set up the destinations the Sequence should be
		 *	played on. Here, we try to use the default
		 *	synthesizer. With some Java Sound implementations
		 *	(Sun jdk1.3/1.4 and others derived from this codebase),
		 *	the default sequencer and the default synthesizer
		 *	are combined in one object. We test for this
		 *	condition, and if it's true, nothing more has to
		 *	be done. With other implementations (namely Tritonus),
		 *	sequencers and synthesizers are always seperate
		 *	objects. In this case, we have to set up a link
		 *	between the two objects manually.
		 *
		 *	By the way, you should never rely on sequencers
		 *	being synthesizers, too; this is a highly non-
		 *	portable programming style. You should be able to
		 *	rely on the other case working. Alas, it is only
		 *	partly true for the Sun jdk1.3/1.4.
		 */
		if (! (sm_sequencer instanceof Synthesizer))
		{
			/*
			 *	We try to get the default synthesizer, open()
			 *	it and chain it to the sequencer with a
			 *	Transmitter-Receiver pair.
			 */
			try
			{
				sm_synthesizer = MidiSystem.getSynthesizer();
				sm_synthesizer.open();
				Receiver	synthReceiver = sm_synthesizer.getReceiver();
				Transmitter	seqTransmitter = sm_sequencer.getTransmitter();
				seqTransmitter.setReceiver(synthReceiver);
			}
			catch (MidiUnavailableException e)
			{
				e.printStackTrace();
			}
		}

		sm_sequencer.start();
	}
	
	Player player()
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
				synchronized(currentPresenter)
				{
					currentPresenter.buttonPressed(b);
					repaint();
				}
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
	public static void main(String[] args)
	{	
		JFrame f = new JFrame("PokemonClone!");
		Game g = new Game();
		f.addKeyListener(g);
		f.add(g);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		
		Area.named("PalletTown");
	}
	
	public static Image jarImage(String path)
	{
		try{
		return new ImageIcon(Game.class.getClassLoader().getResource(path)).getImage();
		}catch(Exception ex){
			System.out.println(path);
		}return null;
	}
	public static InputStream jarStream(String path)
	{
		return Game.class.getClassLoader().getResourceAsStream(path);
	}
}