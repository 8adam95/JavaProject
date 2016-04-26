package app.view.sharkFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import app.model.Administration;
import app.model.User;
import app.view.loginFrame.LoginController;

public class SharkPanel extends JPanel
{
    /**
     * fields
     */
    JButton videoButton, followButton, unfollowButton;
    String sharkLink, sharkName;
    JPanel gridLayout;
    JTextArea sharkDescription;
    
    /**
     * Constructor
     * @param name - Name of a shark
     * @param descritpion - Description of a shark
     * @param link - Link to a picture of a shark
     */
    public SharkPanel(String name, String descritpion, String link)
    {
        sharkName = name;
        sharkLink = link;
        sharkDescription = new JTextArea();
        sharkDescription.setText(descritpion);
        createWidgets();
    }
    
    /**
     * Method which creates widgets.
     * Sets layout and adds text area and buttons in an appropriate positions.
     */
    public void createWidgets()
    {
        setLayout(new BorderLayout());
        
        videoButton = new JButton("CLICK");
        followButton = new JButton("FOLLOW");
        unfollowButton = new JButton("UNFOLLOW");
        gridLayout = new JPanel();
        
        this.setBackground(Color.WHITE);
        
        gridLayout.setLayout(new GridLayout(1, 2));
        
        gridLayout.add(videoButton);
        
        videoButton.addActionListener(new SharkController(this));
        followButton.addActionListener(new SharkController(this));
        unfollowButton.addActionListener(new SharkController(this));
        
        if(sharkFollowed() == false)
            gridLayout.add(followButton);
        else
            gridLayout.add(unfollowButton);
        
        
        this.add(sharkDescription, BorderLayout.CENTER);
        this.add(gridLayout, BorderLayout.SOUTH);
    }
    
    
    private boolean sharkFollowed()
    {
        Administration allUsers = new Administration("usernames.kai");
        
        ArrayList<User> listOfUsers = allUsers.getUsers();
        
        for(User u: listOfUsers)
            for(String s: u.getFavourites())
                if(sharkName.equals(s))
                    return true;
        
        return false;
    }
    
    /**
     * Getter returning description of a shark.
     * @return (String) description of a shark. 
     */
    public String getDescritpion()
    {
        return sharkDescription.getText();
    }
    
    
    /**
     * Getter returning name of a shark.
     * @return (String) Name of a shark.
     */
    public String getName()
    {
        return sharkName;
    }
    
    
    /**
     * Getter returning link of a shark.
     * @return (String) link of a shark.
     */
    public String getLink()
    {
        return sharkLink;
    }
}