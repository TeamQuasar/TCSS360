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
import java.util.Set;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	
	private HomeFile myFile;
	
	private Set<HomeFile> myFiles;
	
	private Room myRoom;
	
	private DefaultListModel<HomeFile> listModel;
	
	private JList fileList;
	
	private JTextArea titleArea;
	
	private JTextArea createdArea;
	
	private JTextArea notesArea;
	
	private JPanel filesArea;
	
	private boolean isDeleting;
	
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
		JMenuItem userItem = new JMenuItem("Users");
		optionsMenu.add(aboutItem);
		optionsMenu.add(userItem);
		
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
		
		listModel = new DefaultListModel<>();
		fileList = new JList<HomeFile>(listModel);
		fileList.setMaximumSize(new Dimension(194, 500));
		middle.add(fileList);
		
		//bottom = generateInfoPanel();
		
		// For testing purposes
		
		Room bedroom = new Room("Bedroom");
		Room kitchen = new Room("Kitchen");
		
		bedroom.addFile(new HomeFile("Bed"));
		bedroom.addFile(new HomeFile("Dresser"));
		bedroom.addFile(new HomeFile("Lamp"));
		bedroom.addFile(new HomeFile("Desk"));
		bedroom.addFile(new HomeFile("Closet"));
		bedroom.addFile(new HomeFile("Sheets"));
		bedroom.addFile(new HomeFile("Computer"));
		bedroom.addFile(new HomeFile("Keyboard"));
		bedroom.addFile(new HomeFile("TV"));
		
		kitchen.addFile(new HomeFile("Stove"));
		kitchen.addFile(new HomeFile("Fridge"));
		kitchen.addFile(new HomeFile("Microwave"));
		kitchen.addFile(new HomeFile("Banana"));
		kitchen.addFile(new HomeFile("Blender"));
		kitchen.addFile(new HomeFile("Oven"));
		kitchen.addFile(new HomeFile("Fryer"));
		kitchen.addFile(new HomeFile("Apple"));
		
		roomBox.addItem(bedroom);
		roomBox.addItem(kitchen);
		
		roomBox.setSelectedIndex(0);
        for (HomeFile h : bedroom.getFiles()) {
        	listModel.addElement(h);
        }
        
        myRoom = (Room) roomBox.getSelectedItem();
        myFiles = myRoom.getFiles();
        fileList.setSelectedIndex(0);
        myFile = (HomeFile) fileList.getSelectedValue();
		
		// End
		
		roomBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<?> box = (JComboBox<?>) e.getSource();
		        myRoom = (Room) box.getSelectedItem();
		        myFiles = myRoom.getFiles();
		        updateDisplay();
		        listModel.removeAllElements();
		        for (HomeFile h : myRoom.getFiles()) {
		        	listModel.addElement(h);
		        }
		        fileList.setSelectedIndex(0);
			}
        });
		
		fileList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && !roomBox.isFocusOwner() && !isDeleting) {
						myFile = (HomeFile) fileList.getSelectedValue();
						titleArea.setText(myFile.toString());
						createdArea.setText(myFile.getImportDate());
						notesArea.setText(myFile.getFileNotes());
	                }
				
			}
        });
		
		JButton addFileButton = new JButton("New File");
		addFileButton.setMaximumSize(new Dimension(194, 40));
		addFileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}			
		});
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setMaximumSize(new Dimension(194, 20));
		deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isDeleting = true;
				String choiceButtons[] = {"Yes","No"};
				int result = JOptionPane.showOptionDialog(null,"Are you sure you want to delete this file?","Confirm Deletion",
			        		JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null, choiceButtons, choiceButtons[1]);
			    if (result == 0) {
			    	myRoom.removeFile((HomeFile) fileList.getSelectedValue());
					listModel.removeElement(fileList.getSelectedValue());
				    updateDisplay();
			    }
			    isDeleting = false;
			}			
		});
		
		bottom.add(addFileButton);
		bottom.add(Box.createRigidArea(new Dimension(0, 10)));
		bottom.add(deleteButton);
		
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
		notesArea = new JTextArea();
		//notesArea.setPreferredSize(new Dimension(225, 400));
		JButton saveButton = new JButton("Save");
		saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		saveButton.setMaximumSize(new Dimension(294, 20));
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myFile.addNote(notesArea.getText());
			}
        });
		
		JButton openButton = new JButton("Open");
		openButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		openButton.setPreferredSize(new Dimension(294, 100));
		openButton.setMinimumSize(new Dimension(294, 100));
		openButton.setMaximumSize(new Dimension(294, 100));
		
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Opening File...");
			}
        });
		
		JPanel notePanel = new JPanel();
		notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
		notePanel.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Notes"), new EmptyBorder(2, 2, 2, 2)));
		notePanel.add(notesArea);
		notePanel.add(Box.createRigidArea(new Dimension(0, 5)));
		notePanel.add(saveButton);
		
		panel.setBorder(new EmptyBorder(0, 10, 0, 10));
		panel.add(openButton);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(generateInfoPanel());
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(notePanel);
		return panel;
	}
	
	private JPanel generateDisplayPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 0, 0);
		filesArea = new JPanel();
		filesArea.setOpaque(true);
		filesArea.setBackground(Color.WHITE);
		filesArea.setLayout(layout);
		updateDisplay();
		JScrollPane scrollPane = new JScrollPane(filesArea, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(600, 600));
		return panel;
	}
	
	private JPanel generateInfoPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setMaximumSize(new Dimension(294, 180));
		JLabel title = new JLabel("Name:");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleArea = new JTextArea();
		titleArea.setEditable(false);
		JLabel created = new JLabel("Created on:");
		created.setAlignmentX(Component.CENTER_ALIGNMENT);
		createdArea = new JTextArea();
		createdArea.setEditable(false);
		
		JLabel permissions = new JLabel("Editing Permissions:");
		permissions.setAlignmentX(Component.CENTER_ALIGNMENT);
		String[] perms = {"Only Me", "Everyone"};
		JComboBox<String> permissionsBox = new JComboBox<>(perms);
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
		p1.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Name"), new EmptyBorder(2, 2, 2, 2)));
		p2.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Created On"), new EmptyBorder(2, 2, 2, 2)));
		p3.setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Editing Permissions"), new EmptyBorder(2, 2, 2, 2)));
		
		p1.add(titleArea);
		p2.add(createdArea);
		p3.add(permissionsBox);
		
		panel.add(p1);
		panel.add(p2);
		panel.add(p3);
		
		return panel;
	}
	
	private void updateDisplay() {
		filesArea.removeAll();
		filesArea.revalidate();
		filesArea.repaint();
		for(HomeFile h : myFiles) {
			filesArea.add(generateFileButton(h));
		}
		filesArea.setPreferredSize(new Dimension(600, 290*(myFiles.size()+2)/2));
	}
	
	private JButton generateFileButton(HomeFile theFile) {
		JButton button = new JButton(theFile.toString());
		button.setPreferredSize(new Dimension(290, 290));
		button.setBackground(Color.white);
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 myFile = theFile;
				 fileList.setSelectedValue(theFile, true);
			}			
		});
		return button;
	}
	
}

