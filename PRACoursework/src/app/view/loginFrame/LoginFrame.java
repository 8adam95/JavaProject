package app.view.loginFrame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class LoginFrame extends JFrame
{
    private static JTextArea userNameField;
    private JButton loginButton;
   
    public LoginFrame()
    {
        super("LOGIN");
        createWidgets();
    }
    
    public void createWidgets()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        loginButton = new JButton("LOGIN");
        userNameField = new JTextArea();
        userNameField.setSize(100, 50);
        loginButton.setSize(100, 50);

        loginButton.addActionListener(new LoginController(this));
        
        this.setSize(200, 150);
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));
        this.add(userNameField);
        this.add(loginButton);
    }
    
    public static String getUsername()
    {
        return userNameField.getText();
    }
    
    public static void main(String[] args)
    {
        new LoginFrame();
    }
}
