package app.view.surprise;

import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JPanel;

public class ObstaclePair {
	
	private int X, width, lTop, lBot, bTop, bBot;
	private JPanel parent;
	
	
	public ObstaclePair(int x, int w,  int lTop, int lBot, JPanel p){
		
		X = x;
		width = w;
		setBias();
		this.lTop =lTop + bTop;
		this.lBot =lBot + bBot;
		
		parent = p;
	}
	
	public void decX(int dec){
		int pw = parent.getParent().getWidth();
		if(X+width+10 <0){
			X += pw + width;
			setBias();
		}
		X -= dec;
		
	}
	
	private void setBias(){
		Random r = new Random();
		bTop = r.nextInt(80);
		bBot = r.nextInt(80);
	}
	
	
	public int getX(){
		return X;
	}
	
	public int getWidth(){
		return width;
	}
	
	public Rectangle2D getRectTop(){
		return new Rectangle2D.Double(X, 0, width, lTop+bTop);
	}
	
	public Rectangle2D getRectBot(){
		return new Rectangle2D.Double(X, parent.getHeight() - (lBot + bBot), width, lBot + bBot);
	}
	

}
