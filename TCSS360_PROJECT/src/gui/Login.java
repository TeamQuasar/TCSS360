package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import objects.AccountManager;

/**
 * Homeowner's Manual PRO Login GUI
 * 
 * @author Collin Nguyen
 * @version 1.0
 */
public class Login extends JFrame {
	
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
	private Login myFrame;
	
	/**
	 * Log in button 
	 */
	final JButton loginButton = new JButton("Log In");
	
	/**
	 * Set this to true to bypass login screen and go straight to the main GUI.
	 */
	private boolean debug = true;
	
	/**
	 * Parameterless constructor
	 */
	public Login() {
		super("HMP - Log in");
		myManager = new AccountManager();
	}

	/**
	 * Start JFrame
	 * @throws IOException 
	 * @author Collin Nguyen
	 */
	public void start() throws IOException {
		if (!debug) {
			myFrame = new Login();
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			myFrame.setLayout(new BorderLayout());
		
			myFrame.add(createMainPanel());
		
			myFrame.pack();
			myFrame.setVisible(true);
			myFrame.setLocationRelativeTo(null);
		} else {
			GUI mainGUI = new GUI();
			try {
				mainGUI.start(myManager);
			} catch (IOException e1) {
				e1.printStackTrace();
				loginButton.setEnabled(true);
			}
		}
	}
	
	/**
	 * Create the main panel
	 * @author Collin Nguyen
	 */
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel loginLabel = new JLabel("Username:");
		JLabel passwordLabel = new JLabel("Password:");
		JTextField loginField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		//JButton loginButton = new JButton("Log In");
		JButton createButton = new JButton("Create Account");
		JButton quitButton = new JButton("Quit");
		
		//mainPanel.setPreferredSize(new Dimension(400, 200));
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		fieldPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		loginField.setMargin(new Insets(2,2,2,2));
		passwordField.setMargin(new Insets(2,2,2,2));
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(loginField.getText(), new String(passwordField.getPassword()));
			}
        });
		
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create(loginField.getText(), new String(passwordField.getPassword()));
			}
        });
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
			}
        });
		
		fieldPanel.add(loginLabel);
		fieldPanel.add(loginField);
		fieldPanel.add(passwordLabel);
		fieldPanel.add(passwordField);
		
		buttonPanel.add(loginButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(createButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(quitButton);
		
		mainPanel.add(fieldPanel);
		mainPanel.add(buttonPanel);
		
		return mainPanel;
	}
	
	/**
	 * Attempts to login with entered credentials
	 * @author Collin Nguyen
	 */
	private void login(final String username, final String password) {
		loginButton.setEnabled(false);
		if (myManager.enterCredentials(username, password)) {
			GUI mainGUI = new GUI();
			try {
				mainGUI.start(myManager);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				loginButton.setEnabled(true);
			}
			myFrame.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(myFrame,
				    "Invalid username/password. Please try again.",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			loginButton.setEnabled(true);
			
		}
	}
	
	/**
	 * Attempts to create account with entered credentials
	 * @author Collin Nguyen
	 */
	private void create(final String username, final String password) {
		if (myManager.createAccount(username, password)) {
			JOptionPane.showMessageDialog(myFrame,
					"Account Created!",
					"Success", 
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(myFrame,
				    "Username already taken. Please try another.",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
