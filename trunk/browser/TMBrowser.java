package browser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class TMBrowser extends JFrame implements ActionListener {
	private ArrayList<String> tmList = new ArrayList();
	private ArrayList<String> hmList = new ArrayList();
	private int tmIndex = 0;
	private int hmIndex = 0;
	private String fileName = new String("untitled");
	private String fullPath = new String("");
	private JPanel tabArea = new JPanel();
	private JPanel lowArea  = new JPanel();
	private JTabbedPane tabContent = new JTabbedPane();
	private JPanel tmTab  = new JPanel();
	private JPanel hmTab  = new JPanel();
	private JPanel controlPanel = new JPanel();
	private ImageIcon leftIcon = new ImageIcon("./browser/left.png");
	private ImageIcon rightIcon = new ImageIcon("./browser/right.png");
	private ImageIcon downIcon = new ImageIcon("./browser/down.png");
	private ImageIcon xIcon = new ImageIcon("./browser/x.png");
	private JLabel tmLabel = new JLabel((tmIndex+1) + " ");
	private JLabel hmLabel = new JLabel((hmIndex+1) + " ");
	private JLabel fileLabel = new JLabel(fileName);
	private JTextField tmTextBox = new JTextField(18);
	private JTextField hmTextBox = new JTextField(18);
	private JButton leftButton = new JButton(leftIcon);
	private JButton rightButton = new JButton(rightIcon);
	private JButton downButton = new JButton(downIcon);
	private JButton xButton = new JButton(xIcon);
	private JButton openButton  = new JButton("Open");
	private JButton saveButton = new JButton("Save");
	
	public TMBrowser() {
		leftButton.addActionListener(this);
		rightButton.addActionListener(this);
		downButton.addActionListener(this);
		xButton.addActionListener(this);
		saveButton.addActionListener(this);
		openButton.addActionListener(this);
		tmTextBox.addActionListener(this);
		hmTextBox.addActionListener(this);
		tmLabel.setFont(new Font("Courier New", Font.BOLD, 18));
		hmLabel.setFont(new Font("Courier New", Font.BOLD, 18));
		tmTab.add(tmLabel);
		hmTab.add(hmLabel);
		tmTab.add(tmTextBox);
		hmTab.add(hmTextBox);
		controlPanel.add(leftButton);
		controlPanel.add(downButton);
		controlPanel.add(xButton);
		controlPanel.add(rightButton);
		tabContent.add("     TM     ", tmTab);
		tabContent.add("     HM     ", hmTab);
		tabArea.setLayout(new BorderLayout());
		tabArea.add(tabContent, BorderLayout.CENTER);
		tabArea.add(controlPanel, BorderLayout.SOUTH);
		lowArea.setLayout(new BorderLayout());
		lowArea.add(openButton, BorderLayout.WEST);
		lowArea.add(saveButton, BorderLayout.EAST);
		fileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lowArea.add(fileLabel, BorderLayout.CENTER);
		add(tabArea, BorderLayout.NORTH);
		add(lowArea, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(240, 145);
		setResizable(false);
		setTitle("TM/HM Browser");
		setVisible(true);
		remakeFields();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == leftButton) {
			if(tabContent.getSelectedIndex() == 0) {
				if(tmIndex > 0)
					tmIndex--;
			}
			else {
				if(hmIndex > 0)
					hmIndex--;
			}
		}
		else if(e.getSource() == rightButton) {
			if(tabContent.getSelectedIndex() == 0) {
				if(tmIndex < tmList.size())
					tmIndex++;
			}
			else {
				if(hmIndex < hmList.size())
					hmIndex++;
			}
		}
		else if(e.getSource() == tmTextBox) {
			if(tmList.size() > tmIndex)
				tmList.set(tmIndex, new String(tmTextBox.getText()));
			else
				tmList.add(tmIndex, new String(tmTextBox.getText()));
		}
		else if(e.getSource() == hmTextBox) {
			if(hmList.size() > hmIndex)
				hmList.set(hmIndex, new String(hmTextBox.getText()));
			else
				hmList.add(hmIndex, new String(hmTextBox.getText()));
		}
		else if(e.getSource() == xButton) {
			if(tabContent.getSelectedIndex() == 0) {
				if(tmList.size() > tmIndex) {
					tmList.remove(tmIndex);
				}
			}
			else {
				if(hmList.size() > hmIndex) {
					hmList.remove(hmIndex);
				}
			}
		}
		else if(e.getSource() == downButton) {
			if(tabContent.getSelectedIndex() == 0)
				tmList.add(tmIndex, new String());
			else
				hmList.add(hmIndex, new String());
		}
		else if(e.getSource() == openButton) {
			fullPath = getFileName();
			createDataList();
		}
		else if(e.getSource() == saveButton) {
			fullPath = getFileName();
			saveDataList();
		}
		remakeFields();
	}
	
	private String getFileName() {
		JFileChooser dialog = new JFileChooser("./");
		if(dialog.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION) {
			fileName = dialog.getSelectedFile().getName();
			return dialog.getSelectedFile().toString();
		}
		else {
			fileName = "untitled";
			return new String();
		}
	}
	
	private void createDataList() {
		if(fullPath.length() == 0)
			return;
		else {
			try {
				Node documentRoot = new Node();
				documentRoot = Node.documentRootFrom(fullPath);
				ArrayList<Node> stanzas = (ArrayList)documentRoot.subnodes();
				tmList = new ArrayList();
				hmList = new ArrayList();
				for(Node currentStanza : stanzas) {
					if(currentStanza.name().equals("tm"))
						tmList.add(currentStanza.subnode("name").content());
					else
						hmList.add(currentStanza.subnode("name").content());
				}
			}
			catch(IOException e) {
				JOptionPane.showMessageDialog(this, "The file failed to load properly.");
			}
		}
	}
	
	private void saveDataList() {
		if(fullPath.length() == 0)
			return;
		else {
			try {
				Node documentRoot = new Node();
				ByteArrayOutputStream temp = new ByteArrayOutputStream();
				String fileData = new String();
				FileOutputStream file = new FileOutputStream(new File(fullPath));
				for(int time = 0; time < 2; time++) {
					int count = 1;
					for(String currentDesc : (time==0)?(tmList):(hmList)) {
						Node newStanza = new Node();
						newStanza.name((time==0)?("tm"):("hm"));
						newStanza.addSubnode("number", "" + count);
						newStanza.addSubnode("name", currentDesc);
						documentRoot.addSubnode(newStanza);
					}
				}
				documentRoot.writeOn(temp);
				fileData = temp.toString();
				fileData = fileData.substring(2, fileData.length()-3);
				file.write(fileData.getBytes());
			}
			catch(IOException e) {
				JOptionPane.showMessageDialog(this, "The file wasn't saved successfully.");
			}
		}
	}
	
	private void remakeFields() {
		tmLabel.setText((tmIndex+1) + " ");
		hmLabel.setText((hmIndex+1) + " ");
		tmTextBox.setText( (tmList.size() > tmIndex) ? ((String)tmList.get(tmIndex)) : (new String()) );
		hmTextBox.setText( (hmList.size() > hmIndex) ? ((String)hmList.get(hmIndex)) : (new String()) );
		fileLabel.setText(fileName);
	}
	
	public static void main(String[] args) throws Exception /*lol*/ {
		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		new TMBrowser();
	}
}