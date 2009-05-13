package browser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Browser extends JFrame implements ActionListener
{
	// window elements
	private JFrame window = new JFrame();
	private JPanel tmTab  = new SpecialTab();
	private JPanel hmTab  = new SpecialTab();
	private JPanel loBox  = new JPanel();
	private JTabbedPane content = new JTabbedPane();
	private JButton open  = new JButton("Open");
	private JButton save = new JButton("Save");
	
	private String current = "5";
	
	public Browser()
	{
		// Connect elements
		tmTab.setLayout(new BorderLayout());
		tmTab.add(new JTextField(), BorderLayout.CENTER);
		tmTab.add(new JLabel("hello, world!"), BorderLayout.SOUTH);
		
		loBox.setLayout(new BorderLayout());
		loBox.add(open, BorderLayout.WEST);
		loBox.add(save, BorderLayout.EAST);
		
		// Put myself together
		content.add("     TM     ", tmTab);
		content.add("     HM     ", hmTab);
		add(content, BorderLayout.CENTER);
		add(loBox, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(225, 150);
		setTitle("TM/HM Browser");
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	
	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		new Browser();
	}
	
	class SpecialTab extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D gg = (Graphics2D)g;
			gg.setFont(new Font("Courier New",Font.BOLD,20));
			gg.drawString(current, 10, 25);
		}
	}
}