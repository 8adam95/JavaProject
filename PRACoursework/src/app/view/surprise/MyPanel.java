package app.view.surprise;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MyPanel extends JPanel {
	
	protected double rectX, rectY;
	
	protected Timer timer;
	protected boolean right, left, up, down;
	protected double vX, vY;
	protected double rate = 0.5;
	protected boolean bouncing;
	protected final double bounceRate = 0.5;
	
	
	public MyPanel(){
		setDoubleBuffered(true);
		rectX = 100;
		rectY = 100;
		vX = 0;
		vY = 0;
		setupTimer();
		right = false;
		left = false;
		up = false;
		down = false;
		timer.start();
		bouncing = false;
	}
	
	
	
	
	protected void setupTimer(){
		timer = new Timer(20, new ActionListener() { // timer exacutes 1000/20 = 50 times a second
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("right "+right+" left "+left+" up " + up + " down " + down);
				if(up){
					vY -= rate;
				}
				if(down){
					vY += rate;
				}
				if(right){
					vX += rate;
				}
				if(left){
					vX -= rate;
				}
				
				
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
				
				
				repaint();
				
			}
		});
	}
	
	public boolean isBouncing(){
		return bouncing;
	}
	
	public void setBouncing(boolean b){
		bouncing = b;
	}
	
	public void setMoveRight(boolean b){
		right = b;
	}
	public void setMoveLeft(boolean b){
		left = b;
	}
	public void setMoveUp(boolean b){
		up = b;
	}
	public void setMoveDown(boolean b){
		down = b;
	}
	
	public void jump(boolean b){}
	
	public void stop(){}
	
	
	@Override
	public void paint(Graphics g1) {
		
		super.paintComponent(g1);
    	Graphics2D g = (Graphics2D) g1;
    	
    	g.setColor(Color.RED);
    	g.drawRect((int)Math.round(rectX), (int)Math.round(rectY), 50, 50);
    	
    	
	}

}
