package app.view.sharkFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//JPanel
public class SharkFrame extends JFrame
{
    JPanel mainFrame;
    
    public SharkFrame()
    {
        super("Shark Info");
        createWidgets();
    }
    
    public void createWidgets()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //mainFrame = new JPanel(new BorderLayout());
        //mainFrame.setBorder(new EmptyBorder(10, 10, 10, 10));
        //this.setContentPane(mainFrame);
        
        pack();
        this.setSize(500, 500);
        this.setVisible(false);
    }
    
    public void addSharkToSharkPanel(String name, String description, String link)
    {
        SharkPanel sharkPanel = new SharkPanel(name, description, link);
        sharkPanel.setName(name);
        
        getContentPane().add(sharkPanel);
        
        this.validate();
        this.repaint();
    }
    
    public static void main(String[] args)
    {
       SharkFrame sf = new SharkFrame();
        
       sf.addSharkToSharkPanel("Shark Kajetan", "trololo", "http://buylaughs.com/wp-content/uploads/2014/01/Funny-Babies-Pictures-2.jpg");
    }
}
