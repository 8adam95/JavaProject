package app.view.sharkFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import app.model.Administration;
import app.model.User;

public class SharkController implements ActionListener{

    private SharkPanel sf;
    
    public SharkController(SharkPanel sharkPanel) {
        this.sf = sharkPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof JButton)
        {
            if((((JButton) e.getSource()).getText().toString()).equals("FOLLOW"))
            {
                String sharkName = sf.getName();
                Administration allUsers = new Administration("usernames.kai");
                
                allUsers.getSelectedUser().addFavouriteShark(sharkName);
            }
            
            else if((((JButton) e.getSource()).getText().toString()).equals("UNFOLLOW"))
            {
                String sharkName = sf.getName();
                Administration allUsers = new Administration("usernames.kai");
                
                allUsers.getSelectedUser().removeFavouriteShark(sharkName);
            }
            
            else if((((JButton) e.getSource()).getText().toString()).equals("CLICK"))
            {
                String sharkLink = sf.getLink();
                openWebPage(sharkLink);
                //sf.validate();
                //sf.repaint();
            }
        }
    }
    
    public void openWebPage(String url)
    {
        try
        {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));;
        }
        catch (java.io.IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
