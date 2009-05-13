package browser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class TMBrowser extends JFrame implements ActionListener
{
	// window elements
	private JFrame window = new JFrame();
	private JPanel tmTab  = new JPanel();
	private JPanel hmTab  = new JPanel();
	private JPanel loBox  = new JPanel();
	private JTabbedPane content = new JTabbedPane();
	private JButton openButton  = new JButton("Open");
	private JButton saveButton = new JButton("Save");
	private JLabel currentIndex = new JLabel(" 5 ");
	
	private ImageIcon leftIcon = new ImageIcon("./browser/left.png");
	private ImageIcon rightIcon = new ImageIcon("./browser/right.png");
	private ImageIcon downIcon = new ImageIcon("./browser/down.png");
	private ImageIcon xIcon = new ImageIcon("./browser/x.png");
	
	private JButton leftButton = new JButton(leftIcon);
	private JButton rightButton = new JButton(rightIcon);
	private JButton downButton = new JButton(downIcon);
	private JButton xButton = new JButton(xIcon);
	
	public TMBrowser()
	{
		leftButton.addActionListener(this);
		rightButton.addActionListener(this);
		downButton.addActionListener(this);
		xButton.addActionListener(this);
		saveButton.addActionListener(this);
		openButton.addActionListener(this);
	
		// Connect elements
		tmTab.setLayout(new BorderLayout());
		currentIndex.setFont(new Font("Courier New",Font.BOLD,20));	
		tmTab.add(currentIndex, BorderLayout.WEST);
		tmTab.add(new JTextField(), BorderLayout.CENTER);
		
		JPanel bottomPane = new JPanel(new BorderLayout());
		bottomPane.add(leftButton, BorderLayout.WEST);
		bottomPane.add(rightButton, BorderLayout.EAST);
		
		JPanel middleBottomPane = new JPanel();
		middleBottomPane.add(downButton);
		middleBottomPane.add(xButton);
		
		bottomPane.add(middleBottomPane, BorderLayout.CENTER);
		tmTab.add(bottomPane, BorderLayout.SOUTH);
		
		loBox.setLayout(new BorderLayout());
		loBox.add(openButton, BorderLayout.WEST);
		loBox.add(saveButton, BorderLayout.EAST);
		
		// Put myself together
		hmTab = new JPanel(tmTab);
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
		if(e.getSource() == leftButton)
		{
			currentIndex.setText("YO!");
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		new TMBrowser();
	}
	
	private void setFields(Node given)
	{
	}
}