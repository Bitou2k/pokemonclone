import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//the shell for a Game
class Main extends JFrame implements MouseListener, ActionListener  {

	JTextField length = new JTextField(10), width = new JTextField(10);
	JComboBox tile = new JComboBox(new String[]{"a","b"});
	JButton generate = new JButton("generate");

	Main(){
		super("LEVEL EDITOR!");


		JPanel sidePanel = new JPanel();
		sidePanel.add(length);
		sidePanel.add(width);
		sidePanel.add(tile);
		sidePanel.add(generate);

		length.addActionListener(this);
		width.addActionListener(this);
		generate.addActionListener(this);

		add(new JScrollPane(new DisplayView()));
		add(sidePanel, BorderLayout.NORTH);

		addMouseListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == width)
		{
		}
		else if (e.getSource() == length)
		{
		}
		else if(e.getSource() ==generate)
		{
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
			g.translate(10, 30); //origin as the visible part, not the corner hidden under the title bar
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 400, 400);
			g.setColor(Color.RED);
			for (int x = 0; x <= 400; x += 10)
				g.drawLine(x, 0, x, 400);
			for (int y = 0; y <= 400; y += 10)
				g.drawLine(0, y, 400, y);

		}
		public Dimension getPreferredSize()
		{
			return new Dimension(1000, 1000);
		}
		public void mouseClicked(MouseEvent e)
		{
			System.out.println(e.getX());
			System.out.println(e.getY());
			System.out.println("Click");
		}

		public void mouseEntered(MouseEvent e) { System.out.println("Entered"); }
		// Invoked when the mouse enters a component.
		public void mouseExited(MouseEvent e) { System.out.println("Exited"); }
		// Invoked when the mouse exits a component.
		public void mousePressed(MouseEvent e) { System.out.println("Pressed"); }
		// Invoked when a mouse button has been pressed on a component.
		public void mouseReleased(MouseEvent e) { System.out.println("Release"); }

	}

	public void mouseClicked(MouseEvent e)
	{
		System.out.println(e.getX());
		System.out.println(e.getY());
		System.out.println("Click");
	}

	public void mouseEntered(MouseEvent e) { System.out.println("Entered"); }
	// Invoked when the mouse enters a component.
	public void mouseExited(MouseEvent e) { System.out.println("Exited"); }
	// Invoked when the mouse exits a component.
	public void mousePressed(MouseEvent e) { System.out.println("Pressed"); }
	// Invoked when a mouse button has been pressed on a component.
	public void mouseReleased(MouseEvent e) { System.out.println("Release"); }
	// Invoked when a mouse button has been released on a component.
	
	
	public static void main(String[] args){
		new Main();
	}
}
	