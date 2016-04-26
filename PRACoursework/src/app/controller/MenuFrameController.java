package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Contributors;

public class MenuFrameController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Favourite"){
			//Contributors.favouriteFrameSetVisible(true);
		}		
	}
}
