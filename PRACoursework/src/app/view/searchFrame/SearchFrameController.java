package app.view.searchFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;

import org.jfree.ui.about.Contributor;

import api.jaws.Location;
import app.Contributors;
import app.controller.mapController.MapController;
import app.model.Administration;
import app.model.User;

public class SearchFrameController implements ActionListener{
	private Administration users;
	private SearchFrame searchFrame;
	private SearchModel model;
	private MapController mapController;

	
	public SearchFrameController(SearchFrame searchFrame, Administration users, SearchModel model){
		this.searchFrame = searchFrame;
		this.users = users;
		this.model = model;
	}
	
	public void setMapController(MapController mapController){
		this.mapController = mapController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(searchFrame.isBuilt()){
			if (e.getSource() instanceof JComboBox) {
				sorting(e);
			}  
			else if(e.getSource() instanceof JButton)
	        {
	            
	            if((((JButton) e.getSource()).getText().toString()).equals("Surprise"))
	            {
	                Contributors.surpriseFrameSetVisible(true);
	            }
	            /*else if((((JButton) e.getSource()).getText().toString()).equals("Shark Of The Day!"))
                {
                    Contributors.sharkOfTheDayFrameSetVisible(true);
                }*/
	            else if((((JButton) e.getSource()).getText().toString()).equals("Statistics"))
                {
	                Contributors.statisticsFrameSetVisible(true);
                }
	            else if((((JButton) e.getSource()).getText().toString()).equals("Favourites"))
                {
	                Contributors.favouriteFrameSetVisible(true);
                }
	        }
		}
	}
			
		    
	public ArrayList getUsers(){
		ArrayList<User> usersList = users.getUsers();
		ArrayList<String> usersName = new ArrayList<String>();
		for(int i = 0; i < usersList.size(); i++){
			usersName.add(usersList.get(i).getUserName());
		}
		return usersName;
	}
	
	private void sorting(ActionEvent e){
		System.out.println("~===================##()##======================~");
		
		String[] options = searchFrame.getSelectedSearch();
		ArrayList<String> sharkNames = new ArrayList<String>();
		sharkNames = model.filterPing(options[0]);
		sharkNames = model.filterGender(options[1], sharkNames);
		sharkNames = model.filterAge(options[2], sharkNames);
		sharkNames = model.filterTag(options[3], sharkNames);
		
		HashMap<String, Location> locationNameMap= model.getLocation(sharkNames);
		for(String s: sharkNames){
			System.out.println(s);
		}	
		for(String s: sharkNames){
			addShark(s, locationNameMap.get(s));
		}
		searchFrame.refresh();
	}

	public void addShark(String n, Location l){
		Location loc = l;
		String name = n;
		System.out.println(l);
		System.out.println(n);
		mapController.displayShark(name, loc);
	}
	
	public void addTagLocations(){
		ArrayList<String> tagLocation = model.getTagLocations();
		for(String l : tagLocation ){
			searchFrame.addTagLocation(l);
		}
	}
	

}
