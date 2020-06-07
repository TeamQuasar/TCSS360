package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import objects.AccountManager;
import objects.HomeFile;
import objects.User;

public class UsersWindow extends JFrame{

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2602437861988302517L;
	
	/**
	 * Account manager for the program
	 */
	private AccountManager myManager;
	
	/**
	 * Main JFrame
	 */
	private UsersWindow myFrame;
	
	/**
	 * Currently selected user
	 */
	private User selectedUser;
	/**
	 * Parameterless constructor
	 */
	public UsersWindow() {
		super("Modify Users");			
	}
	
	/**
	 * Start JFrame
	 * @throws IOEXception
	 * @author Romi Tshiorny
	 */
	public void start(AccountManager theManager) throws IOException
	{
		myManager = theManager;
		myFrame = new UsersWindow();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(new BorderLayout());	
		myFrame.add(createMainPanel(), BorderLayout.CENTER);
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setLocationRelativeTo(null);
	}
	
	
	/**
	 * Create main panel
	 * @author Romi Tshiorny
	 */
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.add(createUsersPanel(), BorderLayout.CENTER);
		mainPanel.add(generateButtons(), BorderLayout.SOUTH);
		return mainPanel;
	}
	
	/**
	 * Create panel of users
	 * @author Romi Tshiorny
	 */
	private JList<User> createUsersPanel() {
		DefaultListModel<User> listModel = new DefaultListModel<User>();
		JList<User> users = new JList<User>(listModel);
		users.setLayout((new BoxLayout(users, BoxLayout.Y_AXIS)));
		for(User u : myManager.getListOfUsers())
		{
//			if(!myManager.getCurrentUser().equals(u))
//			{
//				listModel.addElement(u);
//			}
			listModel.addElement(u);
		}
		users.setMaximumSize(new Dimension(225,100));
		users.setPreferredSize(new Dimension(225,100));
		
		users.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
						selectedUser = (User) users.getSelectedValue();
	                }	
			}
        });
		return users;
		
	}
	
	private JPanel generateButtons() {
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		JButton giveAdminButton = new JButton("Toggle admin status");
		giveAdminButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedUser.toggleAdmin();
				myManager.serialize();
				
				
			}});
		JButton deleteAccountButton = new JButton("Delete");
		deleteAccountButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		bottom.add(deleteAccountButton);
		bottom.add(giveAdminButton);
		return bottom;
	}
	
	public static void main(String args[]) {
		try { 		  
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } catch (Exception e) { 
            System.out.println("Look and Feel not set"); 
        } 
    EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            try {
				new UsersWindow().start(new AccountManager());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    });
	}

}
