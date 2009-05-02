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
	JTextField height = new JTextField("40", 3), width = new JTextField("40", 3);
	JComboBox tile = new JComboBox(supportedTiles);
	JButton save = new JButton("Save");
	JButton load = new JButton("Load");

	static int lvlWidth = 10;
	static int lvlHeight = 10;
	public static final int SQUARESIDE = 16; // each square in the origional pokemon seems to be 16x16
	static String tileType = supportedTiles[1];
	Tile tiles[][] = new Tile[10][10];
	Tile currentTile;

	Main(){
		super("LEVEL EDITOR!");


		JPanel sidePanel = new JPanel();
		sidePanel.add(new JLabel("Width:"));
		sidePanel.add(width);
		sidePanel.add(new JLabel("Height:"));
		sidePanel.add(height);
		sidePanel.add(tile);
		sidePanel.add(save);
		sidePanel.add(load);

		height.addActionListener(this);
		width.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);

		add(new JScrollPane(new DisplayView()));
		add(sidePanel, BorderLayout.NORTH);

		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
		
		resizeMap(20,20);
	}

	void resizeMap(int newW, int newH)
	{
		Tile[][] newTiles = new Tile[newW][newH];
		
		//fill with default tiles
		for(int x=0; x<newW; x++)
			for(int y=0; y<newH; y++)
				newTiles[x][y] = new Tile(x,y);
				
		//copy over orginals that will fit
		for(int x=0; x<newW && x<lvlWidth; x++)
			for(int y=0; y<newH && y<lvlHeight; y++)
				if(tiles[x][y]!=null)
					newTiles[x][y] = tiles[x][y];
					
		tiles = newTiles;
		lvlWidth = newW; width.setText(""+lvlWidth);
		lvlHeight = newH; height.setText(""+lvlHeight);
		currentTile = tiles[0][0];
		repaint();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == width)
		{
			resizeMap(new Integer(width.getText()), lvlHeight);
		}
		else if (e.getSource() == height)
		{
			resizeMap(lvlWidth, new Integer(height.getText()));
		}
		else if(e.getSource() == save)
		{

			String filename = getFile("Save");
			
			Node mapNode = new Node("map");
			mapNode.addSubnode("width",""+lvlWidth);
			mapNode.addSubnode("height",""+lvlHeight);
			for(int x = 0; x < lvlWidth; x++)
				for (int y = 0; y < lvlHeight; y++)
					mapNode.addSubnode( tiles[x][y].asNode() );
					
			try
			{
				OutputStream os = new FileOutputStream(filename);
				mapNode.tryWriteOn(os); 
				os.flush();
				os.close();
			}
			catch (Exception ex) { }
		}
		else if(e.getSource() == load)
		{
			try{
				String filename = getFile("Load");
				Node mapNode = Node.parseFrom(new FileInputStream(filename));
				int w = new Integer( mapNode.contentOf("width"));
				int h = new Integer( mapNode.contentOf("height"));
				resizeMap(w,h);
				
				
				
				for(Node tileNode: mapNode.subnodes("tile"))
				{
					int x = new Integer( tileNode.contentOf("x"));
					int y = new Integer( tileNode.contentOf("y"));
					Tile t = new Tile(x,y);
					t.set(tileNode.contentOf("type"));
					tiles[x][y] = t;
				}
				
				currentTile = tiles[0][0];
				repaint();
				
			}catch(Exception ex){}
		}
	}
	
	String getFile(String prompt)
	{
		JFileChooser chooser = new JFileChooser("./");

		if(chooser.showDialog(this, prompt ) == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile().toString();
			
		return "";
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
					if(tiles[x][y]!=null) tiles[x][y].drawImage(this, g);

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
	