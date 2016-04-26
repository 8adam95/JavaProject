package app.view.surprise;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller extends MouseAdapter implements KeyListener{
	
	private MyPanel panel;


	public Controller(MyPanel p) {
		panel = p;
	}


	@Override
	public void keyPressed(KeyEvent ke) {
		
		
		if(ke.getKeyCode() == KeyEvent.VK_DOWN){
			panel.setMoveDown(true);
		}
		if(ke.getKeyCode() == KeyEvent.VK_UP){
			panel.setMoveUp(true);
		}
		if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
			panel.setMoveRight(true);
		}
		if(ke.getKeyCode() == KeyEvent.VK_LEFT){
			panel.setMoveLeft(true);
		}
		if(ke.getKeyCode() == KeyEvent.VK_SPACE){
			panel.jump(true);
		}
		if(ke.getKeyCode() == KeyEvent.VK_S){
			panel.stop();
		}
		
	}


	@Override
	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_DOWN){
			panel.setMoveDown(false);
		}
		if(ke.getKeyCode() == KeyEvent.VK_UP){
			panel.setMoveUp(false);
		}
		if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
			panel.setMoveRight(false);
		}
		if(ke.getKeyCode() == KeyEvent.VK_LEFT){
			panel.setMoveLeft(false);
		}
		
	}
	
	
	
	


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
