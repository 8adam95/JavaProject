package app.view.surprise;

import app.view.surprise.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartController extends MouseAdapter{

	private StartPanel spanel;
	
	public StartController(StartPanel p	) {
		spanel = p;
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(spanel.getStartBounds().contains(e.getPoint())){
			spanel.changeStartColor(Color.RED);
		}else{
			spanel.changeStartColor(Color.GREEN);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(spanel.getStartBounds().contains(e.getPoint())){
			spanel.changeStartColor(Color.GREEN);
		}
		spanel.getParentFrame().startGame();
	}
	
}
