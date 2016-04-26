package app.view.mainMenu;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import app.view.loginFrame.LoginController;

public class MainMenuFrame extends JFrame
{
    JButton favouritesButton;
    JButton searchButton;
    
    public MainMenuFrame()
    {
        super("Main Menu");
        createWidgets();
    }
    
    public void createWidgets()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        pack();
        
        this.setSize(200, 150);
        this.setVisible(false);
        this.setLayout(new GridLayout(2, 1));
        
        favouritesButton = new JButton("Favourites");
        searchButton = new JButton("Search!");
        
        favouritesButton.addActionListener(new MainMenuController(this));
        searchButton.addActionListener(new MainMenuController(this));

        this.add(favouritesButton);
        this.add(searchButton);
        
    }
}
