package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import info.About;
import objects.AccountManager;
import objects.HomeFile;
import objects.Room;

/**
 * Homeowner's Manual PRO GUI class. Handles creation of GUI frame and elements.
 * 
 * @author Collin Nguyen
 * @version 1.0
 */
public class GUI extends JFrame{

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4328079188596764858L;

	/**
	 * Account manager for the program
	 */
	AccountManager myManager;
	
	/**
	 * Main JFrame
	 */
	private GUI myFrame;	
	
	/**
	 * Parameterless constructor
	 */
	public GUI() {
		super("Homeowner's Manual PRO");
	}
	
	/**
	 * Start JFrame
	 * @throws IOException 
	 */
	public void start(AccountManager theManager) throws IOException {
		myManager = theManager;
		myFrame = new GUI();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(new BorderLayout());
		
		myFrame.add(generateMenuBar(), BorderLayout.NORTH);
		myFrame.add(generateMainPanel(), BorderLayout.CENTER);
		
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * Generates main panel.
	 * 
	 * @return the generated main panel
	 */
	private JPanel generateMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(generateLeftPanel());
		mainPanel.add(generateCenterPanel());
		mainPanel.add(generateRightPanel());
		return mainPanel;
	}
	
	/**
	 * Generates the menu bar.
	 * 
	 * @return the generated menu bar
	 * @throws IOException 
	 */
	private JMenuBar generateMenuBar() throws IOException {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu optionsMenu = new JMenu("Options");
		menuBar.add(fileMenu);
		menuBar.add(optionsMenu);
		
		JMenuItem importItem = new JMenuItem("Import");
		JMenuItem exportItem = new JMenuItem("Export");
		fileMenu.add(importItem);
		fileMenu.add(exportItem);
		
		JMenuItem aboutItem = new JMenuItem("About");
		optionsMenu.add(aboutItem);
		
		About aboutBox = new About();
		
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutBox.show();
			}
			
        });
		
		return menuBar;
	}
	
	private JPanel generateLeftPanel() {
		JPanel panel = new JPanel();
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		JPanel bottom = new JPanel();
		
		panel.setBorder(new EmptyBorder(0, 10, 0, 10));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(194, 600));

		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		
		top.setMaximumSize(new Dimension(194, 50));
		
		JComboBox<Room> roomBox = new JComboBox<>();
		top.add(roomBox);
		
		JTextArea searchBar = new JTextArea("Search");
		//searchBar.setBorder(new EmptyBorder(0, 0, 10, 0));
		top.add(Box.createRigidArea(new Dimension(0, 10)));
		top.add(searchBar);
		
		DefaultListModel<HomeFile> listModel = new DefaultListModel<>();
		JList fileList = new JList<HomeFile>(listModel);
		fileList.setMaximumSize(new Dimension(194, 500));
		middle.add(fileList);
		
		bottom = generateInfoPanel();
		
		// For testing purposes
		
		Room bedroom = new Room("Bedroom");
		Room kitchen = new Room("Kitchen");
		
		bedroom.addFile(new HomeFile("Bed"));
		bedroom.addFile(new HomeFile("Dresser"));
		bedroom.addFile(new HomeFile("Lamp"));
		
		kitchen.addFile(new HomeFile("Stove"));
		kitchen.addFile(new HomeFile("Fridge"));
		kitchen.addFile(new HomeFile("Microwave"));
		kitchen.addFile(new HomeFile("Banana"));
		
		roomBox.addItem(bedroom);
		roomBox.addItem(kitchen);
		
		roomBox.setSelectedIndex(0);
        for (HomeFile h : bedroom.getFiles()) {
        	listModel.addElement(h);
        }
		
		// End
		
		roomBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> box = (JComboBox<?>) e.getSource();
		        Room room = (Room) box.getSelectedItem();
		        listModel.removeAllElements();
		        for (HomeFile h : room.getFiles()) {
		        	listModel.addElement(h);
		        }
			}
			
        });
		
		panel.add(top);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(middle);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(bottom);
		return panel;
	}
	
	private JPanel generateCenterPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 0, 10, 0));
		panel.setLayout(new BorderLayout());
		//panel.add(generateInfoPanel(), BorderLayout.NORTH);
		panel.add(generateDisplayPanel(), BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel generateRightPanel() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(294, 600));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JTextArea notesArea = new JTextArea("NOTES");
		notesArea.setPreferredSize(new Dimension(225, 400));
		JButton saveButton = new JButton("Save");
		saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		saveButton.setMaximumSize(new Dimension(294, 20));
		panel.setBorder(new EmptyBorder(0, 10, 0, 10));
		panel.add(notesArea);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(saveButton);
		return panel;
	}
	
	private JPanel generateDisplayPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel display = new JLabel();
		display.setPreferredSize(new Dimension(600, 600));
		display.setOpaque(true);
		display.setBackground(Color.WHITE);
		//display.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(display, BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel generateInfoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setMaximumSize(new Dimension(194, 180));
		JLabel title = new JLabel("Name:");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		JTextArea titleArea = new JTextArea();
		JLabel created = new JLabel("Created by:");
		created.setAlignmentX(Component.CENTER_ALIGNMENT);
		JTextArea createdArea = new JTextArea();
		createdArea.setEditable(false);
		
		JLabel permissions = new JLabel("Permissions");
		permissions.setAlignmentX(Component.CENTER_ALIGNMENT);
		String[] perms = {"Only Me", "Everyone can view", "Everyone can edit"};
		JComboBox<String> permissionsBox = new JComboBox<>(perms);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setMaximumSize(new Dimension(194, 20));
		deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(titleArea);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(created);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(createdArea);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(permissions);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(permissionsBox);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(deleteButton);
		
		return panel;
	}
	
}

