package browser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class TMBrowser extends JFrame implements ActionListener
{
	// window elements
	
	private JPanel tabArea = new JPanel();
	private JPanel lowArea  = new JPanel();
	
	private JTabbedPane tabContent = new JTabbedPane();
	private JPanel tmTab  = new JPanel();
	private JPanel hmTab  = new JPanel();
	private JPanel controlPanel = new JPanel();

	private JLabel tmLabel = new JLabel(" 5 ");
	private JLabel hmLabel = new JLabel(" 6" );
	private JTextField tmTextBox = new JTextField();
	private JTextField hmTextBox = new JTextField();
	
	private ImageIcon leftIcon = new ImageIcon("./browser/left.png");
	private ImageIcon rightIcon = new ImageIcon("./browser/right.png");
	private ImageIcon downIcon = new ImageIcon("./browser/down.png");
	private ImageIcon xIcon = new ImageIcon("./browser/x.png");
	
	private JButton leftButton = new JButton(leftIcon);
	private JButton rightButton = new JButton(rightIcon);
	private JButton downButton = new JButton(downIcon);
	private JButton xButton = new JButton(xIcon);
	
	private JButton openButton  = new JButton("Open");
	private JButton saveButton = new JButton("Save");
	
	public TMBrowser()
	{
		// Add action listeners
		leftButton.addActionListener(this);
		rightButton.addActionListener(this);
		downButton.addActionListener(this);
		xButton.addActionListener(this);
		saveButton.addActionListener(this);
		openButton.addActionListener(this);
		tmTextBox.addActionListener(this);
		hmTextBox.addActionListener(this);
	
		// Add unique elements to tabs
		tmTab.setLayout(new BorderLayout());
		hmTab.setLayout(new BorderLayout());
		tmLabel.setFont(new Font("Courier New", Font.BOLD, 20));
		hmLabel.setFont(new Font("Courier New", Font.BOLD, 20));
		tmTab.add(tmLabel, BorderLayout.WEST);
		hmTab.add(hmLabel, BorderLayout.WEST);
		tmTab.add(tmTextBox, BorderLayout.CENTER);
		hmTab.add(hmTextBox, BorderLayout.CENTER);
		
		// Assemble control panel
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(leftButton, BorderLayout.WEST);
		controlPanel.add(downButton, BorderLayout.CENTER);
		controlPanel.add(xButton, BorderLayout.CENTER);
		controlPanel.add(rightButton, BorderLayout.EAST);
		
		// Assemble tab area
		tabContent.add("     TM     ", tmTab);
		tabContent.add("     HM     ", hmTab);
		tabArea.setLayout(new BorderLayout());
		tabArea.add(tabContent, BorderLayout.CENTER);
		tabArea.add(controlPanel, BorderLayout.SOUTH);
		
		// Assemble low area
		lowArea.setLayout(new BorderLayout());
		lowArea.add(openButton, BorderLayout.WEST);
		lowArea.add(saveButton, BorderLayout.EAST);
		
		// Put window together
		add(tabArea, BorderLayout.NORTH);
		add(lowArea, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(225, 150);
		setTitle("TM/HM Browser");
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == leftButton)
		{
			tmLabel.setText("YO!");
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