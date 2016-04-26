package app.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.controller.MenuFrameController;

public class MenuFrame extends JFrame
{
    JButton searchButton;
    JButton favouritesButton;
    
    /**
     *Constructor. 
     */
    public MenuFrame()
    {
        super("Amnity Police");
        createWidgets();
    }
    
    /**
     * Creating widgets.
     */
    public void createWidgets()
    {
        //Setting name and size.
        this.setName("Amnity Police");
        this.setSize(300, 300);
        
		MenuFrameController mlistener = new MenuFrameController();
		
        searchButton = new JButton("Search");
        searchButton.setActionCommand("Search");
        
        favouritesButton = new JButton("Favourites");
        favouritesButton.setActionCommand("Favourite");
        favouritesButton.addActionListener(mlistener);

		
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Set size of a window to be constant.
        this.setResizable(false);
        
        JPanel gridLayout = new JPanel();
        gridLayout.setLayout(new GridLayout(2, 1));
        
        //Adding buttons to the border layout.
        gridLayout.add(searchButton, BorderLayout.NORTH);
        gridLayout.add(favouritesButton, BorderLayout.SOUTH);
        
        this.setLayout(new BorderLayout());
        
        this.add(gridLayout, BorderLayout.SOUTH);
        
        //Adding image to the layout. 
        ImageIcon ii = new ImageIcon(new ImageIcon("ray.jpg").getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH));
        JLabel lable = new JLabel(ii);
        JScrollPane jsp = new JScrollPane(lable);
        this.getContentPane().add(jsp);
        
        this.setVisible(false);
        pack();
    }
    
    public static void main(String[] args)
    {
        try
        {
            //UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        new MenuFrame();
    }

}
