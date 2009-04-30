import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//the shell for a Game
class Main extends JFrame implements ActionListener  {

	JTextField height = new JTextField("40", 10), width = new JTextField("40", 10);
	JComboBox tile = new JComboBox(new String[]{"a","b"});
	JButton generate = new JButton("generate");

	static int lvlWidth = 40;
	static int lvlHeight = 40;
	static final int SQAUARESIDE = 16; // each square in the origional pokemon seems to be 16x16
	Tile tiles[][];
	Tile currentTile;

	Main(){
		super("LEVEL EDITOR!");


		JPanel sidePanel = new JPanel();
		sidePanel.add(height);
		sidePanel.add(width);
		sidePanel.add(tile);
		sidePanel.add(generate);

		height.addActionListener(this);
		width.addActionListener(this);
		generate.addActionListener(this);

		add(new JScrollPane(new DisplayView()));
		add(sidePanel, BorderLayout.NORTH);

		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
		setTileValues();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == width)
		{
			lvlWidth = new Integer(width.getText());
		}
		else if (e.getSource() == height)
		{
			lvlHeight = new Integer(height.getText());
		}
		else if(e.getSource() ==generate)
		{
		}
		tiles = new Tile[lvlWidth][lvlHeight];

		setTileValues();
		//check();
		repaint();
	}

	public void setTileValues()
	{
		tiles = new Tile[lvlWidth][lvlHeight];
		for(int x = 0; x < lvlWidth; x++)
			for(int y = 0; y < lvlHeight; y++)
				tiles[x][y] = new Tile(x, y);
	}

	public void check()
	{
		for (int x = 0; x < lvlWidth; x++)
			for (int y = 0; y < lvlHeight; y++)
			{
				System.out.println(tiles[x][y].toString());
			}
	}


	class DisplayView extends JComponent implements MouseListener
	{
		public DisplayView()
		{
			addMouseListener(this);
		}
		public synchronized void paint(Graphics g)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, lvlWidth * SQAUARESIDE, lvlHeight * SQAUARESIDE);
			g.setColor(Color.RED);
			for (int x = 0; x <= lvlWidth * SQAUARESIDE; x += SQAUARESIDE)
				g.drawLine(x, 0, x, lvlHeight * SQAUARESIDE);
			for (int y = 0; y <= lvlHeight * SQAUARESIDE; y += SQAUARESIDE)
				g.drawLine(0, y, lvlWidth * SQAUARESIDE, y);

		}
		public Dimension getPreferredSize()
		{
			return new Dimension(lvlWidth * SQAUARESIDE + 20, lvlHeight * SQAUARESIDE + 50);
		}
		public void mouseClicked(MouseEvent e)
		{
			int mouseX, mouseY;
			mouseX = e.getX();// - 10; //Translated for width
			mouseY = e.getY();// - 30; //Translated for height
			System.out.println(mouseX +", " + mouseY);
			if(mouseX > 0 && mouseY > 0 && mouseX <= lvlWidth * SQAUARESIDE - 1 && mouseY < lvlHeight * SQAUARESIDE - 1)
			currentTile = tiles[mouseX / SQAUARESIDE][mouseY / SQAUARESIDE];
			System.out.println(currentTile);
		}

		public void mouseEntered(MouseEvent e) { System.out.println("Entered"); }
		// Invoked when the mouse enters a component.
		public void mouseExited(MouseEvent e) { System.out.println("Exited"); }
		// Invoked when the mouse exits a component.
		public void mousePressed(MouseEvent e)
		{
			int mouseX, mouseY;
			mouseX = e.getX();// - 10; //Translated for width
			mouseY = e.getY();// - 30; //Translated for height
			System.out.println(mouseX +", " + mouseY);
			if(mouseX > 0 && mouseY > 0 && mouseX <= lvlWidth * SQAUARESIDE - 1 && mouseY < lvlHeight * SQAUARESIDE - 1)
			currentTile = tiles[mouseX / SQAUARESIDE][mouseY / SQAUARESIDE];
			System.out.println(currentTile);
		}
		public void mouseReleased(MouseEvent e) { System.out.println("Release"); }
		// Invoked when a mouse button has been released on a component.
	


	}

	
	public static void main(String[] args){
		new Main();
	}
}
	