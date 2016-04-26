package app.view.loginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import org.jfree.ui.about.Contributor;

import app.Contributors;
import app.model.Administration;
import app.model.User;
import app.view.mainMenu.MainMenuFrame;

public class LoginController implements ActionListener
{

    private LoginFrame loginFrame;
    
    public LoginController(LoginFrame loginFrame)
    {
        this.loginFrame = loginFrame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof JButton)
        {
            if((((JButton) e.getSource()).getText().toString()).equals("LOGIN"));
            {
                
                String username = LoginFrame.getUsername();
                
                Contributors.addUser(username);
                
                System.out.println(username);
                
                Contributors.mainMenuFrameSetVisible(true);
                Contributors.loginFrameSetVisible(false);
                
            }
        }
    }

}
