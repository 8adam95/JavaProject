package app.view.surprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
	
	private int startWidth, startHeight, fontSize;
	private JLabel start;
	private Color borderColor;
	private SurpriseFrame parentFrame;
	
	
	public StartPanel(SurpriseFrame parent){
		parentFrame = parent;
		//setSize(new Dimension(500, 500));
		this.setLayout(null);
		setBackground(Color.WHITE);
		start = new JLabel(" S T A R T ");
		fontSize = 20;
		Font startFont = new Font("Bauhaus 93", Font.PLAIN, fontSize);
		start.setFont(startFont);
		FontMetrics metrics = start.getFontMetrics(startFont);
		startWidth = metrics.stringWidth(" S T A R T ") + 10;
		startHeight = metrics.getHeight() + 10;
//		System.out.println("W  " +start.getWidth() + " H " + start.getHeight());
		borderColor = Color.green;
		start.setBorder(BorderFactory.createLineBorder(borderColor, 5) );
		add(start);
		
		addMouseListener(new MouseAdapter() {
			
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(start.getBounds().contains(e.getPoint())){
					changeStartColor(Color.RED);
				}else{
					changeStartColor(Color.GREEN);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(start.getBounds().contains(e.getPoint())){
					changeStartColor(Color.GREEN);
				}
			}
			
		});
		
	}
	
	public void changeStartBorder(boolean b){
		start.setBorder(BorderFactory.createLineBorder(borderColor, 5) );
	}
	
	public void changeStartColor(Color c){
		borderColor = c;
		start.setBorder(BorderFactory.createLineBorder(c, 5) );
	}
	
	public SurpriseFrame getParentFrame(){
		return parentFrame;
	}

	public Rectangle2D getStartBounds(){
		return start.getBounds();
	}
	
	
	@Override
	public void paint(Graphics g1) {
		super.paint(g1);
		Graphics2D g = (Graphics2D) g1;
		g.draw(start.getBounds());
		int pw = getWidth();
		int ph = getHeight();
//		System.out.println("W  " +getWidth() + " H " + getHeight());
		start.setBounds((pw - startWidth - 10)/2, (ph - startHeight - 10)/2, startWidth, startHeight);
		
	}
}
