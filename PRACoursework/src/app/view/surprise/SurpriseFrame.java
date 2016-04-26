package app.view.surprise;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SurpriseFrame {
	
	private JFrame frame;
	private JCheckBox bouncing;
	private JLabel score;
	private ObstPanel obstPanel;
	private StartPanel spanel;
	
	
	public SurpriseFrame(){
		frame = new JFrame("Test Malowania");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(600, 300, 500, 500);
		
		
		spanel = new StartPanel(this);
		spanel.addMouseListener(new StartController(spanel));
		
		
//		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(spanel, BorderLayout.CENTER);
		
		
//		bouncing = new JCheckBox("bounce");
//		bouncing.setFocusable(false);
//		bouncing.setSelected(panel.isBouncing());
//		bouncing.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				panel.setBouncing(!panel.isBouncing());
//			}
//		});
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
//		top.add(bouncing);
//		score = new JLabel("Score - 0");
//		top.add(score);
		
		
		frame.getContentPane().add(top, BorderLayout.NORTH);
//		frame.addKeyListener(new Controller(panel));
//		frame.setVisible(true);
	}
	
	public void startGame(){
		obstPanel = new ObstPanel();
		obstPanel.setBackground(Color.WHITE);
		obstPanel.addKeyListener(new Controller(obstPanel));
		frame.getContentPane().remove(spanel);
		frame.getContentPane().add(obstPanel, BorderLayout.CENTER);
		obstPanel.requestFocusInWindow();
		frame.revalidate();
		frame.repaint();
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	
	public static void main(String[] args){
//		String fonts[] = 
//			      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//
//			    for ( int i = 0; i < fonts.length; i++ )
//			    {
//			      System.out.println(fonts[i]);
//			    }
		new SurpriseFrame().setVisible(true);
	}

}
