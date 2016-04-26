package app.view.mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import api.jaws.Jaws;
import api.jaws.Shark;
import app.Contributors;
import app.controller.mapController.MapController;
import app.model.Administration;
import app.model.Cache;
import app.view.FavouriteFrame;
import app.view.mapFrame.MapPanel;
import app.view.searchFrame.SearchFrame;
import app.view.searchFrame.SearchFrameController;
import app.view.searchFrame.SearchModel;

public class MainMenuController implements ActionListener
{
    private MainMenuFrame mmf;

    public MainMenuController(MainMenuFrame mmf) {
        this.mmf = mmf;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof JButton)
        {
            if((((JButton) e.getSource()).getText().toString()).equals("Favourites"))
                Contributors.favouriteFrameSetVisible(true);
            else if((((JButton) e.getSource()).getText().toString()).equals("Search!"))
                Contributors.searchFrameSetVisible(true);
        }
    }

}
