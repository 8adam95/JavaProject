package app.view;
import java.awt.BorderLayout;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import app.model.Administration;
import api.jaws.Location;
import api.jaws.Jaws;

public class FavouriteFrame extends JFrame
{
    private JTextArea jFavouriteTextArea;
    private JLabel jlFavouriteText;
    
    /**
     * Constructor.
     */
    public FavouriteFrame()
    {
        super("Favourite Sharks");
        createWidgets();
    }
    
    /**
     * Creating widgets.
     */
    public void createWidgets()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        jFavouriteTextArea = new JTextArea(10,15);
        jFavouriteTextArea.setEditable(false);
        //jFavouriteTextArea.setText("Shark!");
        jlFavouriteText = new JLabel("Your favourite sharks:");
        
        BorderLayout bl = new BorderLayout();
        bl.setHgap(15);
        bl.setVgap(15);
        this.setLayout(bl);
        
        //Adding text area and label to the layout.
        this.add(jlFavouriteText, BorderLayout.NORTH);
        this.add(jFavouriteTextArea, BorderLayout.CENTER);
        
        this.addSharksToPanel();
        
        
        pack();
        this.setSize(500, 250);
        this.setVisible(false);
    }
    
    public void addSharksToPanel()
    {
        Administration allUsers = new Administration("usernames.kai");
        
        if(allUsers.getSelectedUser().getFavourites().isEmpty()){
        	jFavouriteTextArea.setText("YOU DONT HAVE ANY FAVOURITE SHARKS YET");
        	return;
        }
        
        
        for(String s: allUsers.getSelectedUser().getFavourites())
        {
            String sharkName = s;
            Jaws j = new Jaws("LCALruJFV1ApE4T5", "X2J8m3lOSDwik1bj");
//            System.out.println(sharkName + " lat " + j.getLastLocation(sharkName).getLatitude() + "  lon " + j.getLastLocation(sharkName).getLongitude());
            double sharkDistance = allUsers.distFrom(j.getLastLocation(sharkName).getLatitude() , j.getLastLocation(sharkName).getLongitude() );
            jFavouriteTextArea.append("Distance to " + s +" is " + String.format("%.2f",sharkDistance/1000) + " km\n");
        }
        
        this.validate();
        this.repaint();
    }
    
   
    /*public static void main(String[] args)
    {
        
        	// Set Motif L&F on any platform
     		try {
     		//	UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
     		} catch (Exception e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
     		new FavouriteFrame().setVisible(true);
    }*/

}
//comment test
