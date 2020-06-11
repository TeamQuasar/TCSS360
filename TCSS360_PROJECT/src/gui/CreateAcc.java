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

import info.UserSettings;
import objects.AccountManager;

/**
 * Class for Acccount Creation GUI
 * @author Romi Tshiorny
 *
 */
public class CreateAcc extends JFrame{

	/**
	 *  Auto generated UID stub
	 */
	private static final long serialVersionUID = -5961000432447996908L;
	
	
	/**
	 * Account manager for the program
	 */
	private AccountManager myManager;
	
	/**
	 * Main JFrame
	 */
	private CreateAcc myFrame;
	
	/**
	 * Log in button 
	 */
	final JButton createButton = new JButton("Create");

	
	/**
	 * Parameterless Constructor
	 */
	public CreateAcc() {
		super("Create Account");
	}
	
	/**
	 * Starts the JFrame
	 * @author Romi Tshiorny
	 * @throws IOException
	 */
	public void start(AccountManager theManager, JButton login) throws IOException {
		myManager = theManager;
		myFrame = new CreateAcc();
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLayout(new BorderLayout());
		
		myFrame.add(createMainPanel(login));
		
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * Create the main panel
	 * @author Romi Tshiorny
	 * @author Collin Nguyen
	 */
	private JPanel createMainPanel(JButton login) {
		JPanel mainPanel = new JPanel();
		JPanel fieldPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel usernameLabel = new JLabel("Username (Required):");
		JLabel passwordLabel = new JLabel("Password (Required):");
		JLabel fullnameLabel = new JLabel("Full Name (Optional):");
		JLabel emailLabel = new JLabel("Email (Optional):");
		JTextField usernameField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		JTextField fullnameField = new JTextField();
		JTextField emailField = new JTextField();
		//JButton loginButton = new JButton("Log In");
		JButton createButton = new JButton("Create");
		JButton quitButton = new JButton("Cancel");
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
				
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		fieldPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		usernameField.setMargin(new Insets(2,2,2,2));
		passwordField.setMargin(new Insets(2,2,2,2));
		
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create(usernameField.getText(), 
						new String(passwordField.getPassword()), 
						new UserSettings(fullnameField.getText(), emailField.getText()),
						login);
				myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
			}
        });
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
			}
        });
		
		fieldPanel.add(usernameLabel);
		fieldPanel.add(usernameField);
		fieldPanel.add(passwordLabel);
		fieldPanel.add(passwordField);
		fieldPanel.add(fullnameLabel);
		fieldPanel.add(fullnameField);
		fieldPanel.add(emailLabel);
		fieldPanel.add(emailField);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(createButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(quitButton);
		
		mainPanel.add(fieldPanel);
		mainPanel.add(buttonPanel);
		return mainPanel;
		
		
	}
	
	/**
	 * Attempts to create account with entered credentials
	 * @author Collin Nguyen
	 */
	private void create(final String username, final String password, UserSettings settings,
												JButton login) {
		if (myManager.createAccount(username, password)) {
			myManager.getNewestUser().setSettings(settings);
			JOptionPane.showMessageDialog(myFrame,
					"Account Created!",
					"Success", 
					JOptionPane.INFORMATION_MESSAGE);
			login.setEnabled(true);
		} else {
			JOptionPane.showMessageDialog(myFrame,
				    "Username already taken. Please try another.",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	

}
