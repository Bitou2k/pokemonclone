package browser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Browser
{
	public static void main(String[] args)
	{
		JFrame window = new JFrame();
		JPanel content = new JPanel();
		
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		
		JComponent button = new JButton("Hello thar");
		JComponent button2 = new JButton("Hello again");
		JComponent button3 = new JButton("Jeez u following me?");
		JComponent button4 = new JButton("OMG what a creep!");
		
		top.add(button);
		top.add(button2);
		
		bottom.add(button3);
		bottom.add(button4);
		
		content.setLayout(new BorderLayout());
		content.add(top, BorderLayout.NORTH);
		content.add(bottom, BorderLayout.SOUTH);
		
		window.add(content, BorderLayout.CENTER);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}