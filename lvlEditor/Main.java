import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//the shell for a Game
class Main extends JFrame implements ActionListener  {

	static final String[] supportedTiles = { "Blank", "Sparce Grass", "More Grass", "Grass", "Poke Grass",
		"Water TL", "Water T", "Water TR", "Water R", "Water L", "Water", "Barrel", "Sign", "Fence",
	"Flower 1", "Flower 2", "Door", "Small House BL", "Small House B Windows", "Small House BR", "Small House ML",
	"Small House M Window", "Small House M Windows", "Small House MR", "Small House TL","Small House TM", "Small House TR"};
	JTextField height = new JTextField("40", 10), width = new JTextField("40", 10);
	JComboBox tile = new JComboBox(supportedTiles);
	JButton generate = new JButton("generate");

	static int lvlWidth = 40;
	static int lvlHeight = 40;
	public static final int SQUARESIDE = 16; // each square in the origional pokemon seems to be 16x16
	static String tileType = supportedTiles[1];
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
		currentTile = tiles[0][0];
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == width)
		{
			lvlWidth = new Integer(width.getText());
			tiles = new Tile[lvlWidth][lvlHeight];
			setTileValues();
			currentTile = tiles[0][0];
			repaint();
		}
		else if (e.getSource() == height)
		{
			lvlHeight = new Integer(height.getText());
			tiles = new Tile[lvlWidth][lvlHeight];
			setTileValues();
			currentTile = tiles[0][0];
			repaint();
		}
		else if(e.getSource() ==generate)
		{


			Node mapNode = new Node("map");
			for(int x = 0; x < lvlWidth; x++)
				for (int y = 0; y < lvlHeight; y++)
				{
					Node tileNode = new Node("tile");
					tileNode.addSubnode("type", tiles[x][y].toString());
					tileNode.addSubnode("position", tiles[x][y].getLocation());
					mapNode.addSubnode(tileNode);
				}
			try
			{
				OutputStream os = new java.io.FileOutputStream("level.nml");
				mapNode.tryWriteOn(os); os.flush();os.close();
			}
			catch (Exception ex) { }
		}
	}

	public void setTileValues()
	{
		tiles = new Tile[lvlWidth][lvlHeight];
		for(int x = 0; x < lvlWidth; x++)
			for(int y = 0; y < lvlHeight; y++)
				tiles[x][y] = new Tile(x, y);
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
			g.fillRect(0, 0, lvlWidth * SQUARESIDE, lvlHeight * SQUARESIDE);
			g.setColor(Color.RED);
			for (int x = 0; x <= lvlWidth * SQUARESIDE; x += SQUARESIDE)
				g.drawLine(x, 0, x, lvlHeight * SQUARESIDE);
			for (int y = 0; y <= lvlHeight * SQUARESIDE; y += SQUARESIDE)
				g.drawLine(0, y, lvlWidth * SQUARESIDE, y);

			for (int x = 0; x < lvlWidth; x++)
				for (int y = 0; y < lvlHeight; y++)
					tiles[x][y].drawImage(this, g);

		}
		public Dimension getPreferredSize()
		{
			return new Dimension(lvlWidth * SQUARESIDE + 20, lvlHeight * SQUARESIDE + 50);
		}
		public void mouseClicked(MouseEvent e)
		{
			int mouseX, mouseY;
			mouseX = e.getX();// - 10; //Translated for width
			mouseY = e.getY();// - 30; //Translated for height
			System.out.println(mouseX +", " + mouseY);
			if(mouseX > 0 && mouseY > 0 && mouseX <= lvlWidth * SQUARESIDE - 1 && mouseY < lvlHeight * SQUARESIDE - 1)
			currentTile = tiles[mouseX / SQUARESIDE][mouseY / SQUARESIDE];
			tileType = (String)tile.getSelectedItem();
			currentTile.set(tileType);
			System.out.println(currentTile);
			repaint();
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
			System.out.println(mouseX + ", " + mouseY);
			if (mouseX > 0 && mouseY > 0 && mouseX <= lvlWidth * SQUARESIDE - 1 && mouseY < lvlHeight * SQUARESIDE - 1)
				currentTile = tiles[mouseX / SQUARESIDE][mouseY / SQUARESIDE];
			tileType = (String)tile.getSelectedItem();
			currentTile.set(tileType);
			System.out.println(currentTile);
			repaint();
		}
		public void mouseReleased(MouseEvent e) { System.out.println("Release"); }
		// Invoked when a mouse button has been released on a component.
	


	}

	
	public static void main(String[] args){
		new Main();
	}
}
	