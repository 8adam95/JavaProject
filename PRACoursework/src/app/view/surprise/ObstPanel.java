package app.view.surprise;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.ShapeGraphicAttribute;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import com.alee.managers.settings.SettingsProcessorData;

public class ObstPanel extends MyPanel {
	
	Random r = new Random();
	
	private int score;
	private boolean jump;
//	private ArrayList<ObstaclePair> obstacles;
	private ObstaclePair op;
	private boolean freeze;
	private double speed;
	
	public ObstPanel(){
		super();
		jump = false;
//		obstacles = new ArrayList<ObstaclePair>();  
//		obstacles.add(new ObstaclePair(300, 40, 80, 80, this));
		op = new ObstaclePair(300, 40, 20, 20, this);
		freeze = false;
		
		rate = 0.4;
		speed = 5*rate;
	}
	
	
	
	
	
	
	
	@Override
	protected void setupTimer(){
		timer = new Timer(20, new ActionListener() { // timer exacutes 1000/20 = 50 times a second
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("right "+right+" left "+left+" up " + up + " down " + down);
				
				
				
				
				
//				for(ObstaclePair p : obstacles){
//					Rectangle2D.Double r = new Rectangle2D.Double(rectX, rectY, 50, 50);
//					if(p.getRectBot().intersects(r) || p.getRectTop().intersects(r)){
//						stop();
//					}
//				}
				
				Rectangle2D.Double r = new Rectangle2D.Double(rectX, rectY, 50, 50);
				if(op.getRectBot().intersects(r) || op.getRectTop().intersects(r)){
					stop();
				}
				
				
				if(!freeze){
					score++;
					
					speed += 0.01;
					
//					obstacles.get(0).decX((int) (rate*5));
					op.decX((int) (speed));
					
					
					
					
					
					vY += rate/2;
					if(jump){
						vY = (-1)*rate*12;
						jump = false;
					}
					if(up){
						vY -= rate;
					}
					if(down){
						vY += rate;
					}
//					if(right){
//						vX += rate;
//					}
//					if(left){
//						vX -= rate;
//					}
					
					
					rectX = rectX + vX;
					if(rectX < 2){
						rectX = 2;
						if(bouncing){
							vX = -vX*bounceRate;
						}else{
							vX = 0;
						}
						
					}
					if(rectX > (getWidth() - 52)){
						rectX = getWidth() - 52;
						if(bouncing){
							vX = -vX*bounceRate;
						}else{
							vX = 0;
						}
					}
					
					
					
					rectY = rectY + vY;
					if(rectY < 2){
						rectY = 2;
						if(bouncing){
							vY = -vY*bounceRate;
						}else{
							vY = 0;
						}
					}
					if(rectY > (getHeight() - 52)){
						rectY = getHeight() - 52;
						if(bouncing){
							vY = -vY*bounceRate;
						}else{
							vY = 0;
						}
					}
				}
				
				repaint();
				
			}
		});
		timer.setInitialDelay(5000);
	}
	
	public int getScore(){
		return score;
	}
	
	public void stop(){
		freeze = true;
	}
	
	public void jump(boolean b){
		jump = b;
	}
	
	

	
	@Override
	public void paint(Graphics g1) {
		super.paint(g1);
		
		Graphics2D g = (Graphics2D) g1;
    	
    	g.setColor(Color.CYAN);
    	
    	g.fill(op.getRectBot());
		g.fill(op.getRectTop());
    	
    	
		g.setColor(Color.BLACK);
		g.drawString("SCORE : " + score, getWidth() - 100, getHeight()-100);
		
		
		
	}

}
























