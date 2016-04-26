package app.model;

import java.util.ArrayList;
import java.util.HashMap;

import api.jaws.Jaws;
import api.jaws.Location;
import api.jaws.Shark;

public class Cache implements Runnable {
	private Jaws jaws;
	public static HashMap<String, Shark> sharkList;
	public static HashMap<String, Location> sharkLocations;
	public static ArrayList<String> sharkNames;
	public static int progress = 1; // percentage to completion of cashing
	
	
	
	public void setJaws(Jaws jaws){
		this.jaws = jaws;
	}


	@Override
	public void run() {
		while(true){
		    
		}
		//this.upadatecacheData();
	}

	   public void cacheData() {
	        System.out.println("Caching Thread");

	        sharkList = new HashMap<>();
	        sharkNames = new ArrayList<>(jaws.getSharkNames());
	        sharkLocations = new HashMap<>();
	        int total = sharkNames.size();
	        System.out.println(total);
	        for (int i = 0; i < 2; i++) {
	            System.out.println("caching shark data " + progress*100/total);
	            Shark tempshark = jaws.getShark(sharkNames.get(i));
	            if(tempshark!=null){
	                sharkList.put(sharkNames.get(i), tempshark);
	                Location tempLocation = jaws.getLastLocation(sharkNames.get(i));
	                if(tempLocation!=null){
	                    sharkLocations.put(sharkNames.get(i), tempLocation);
	                }
	            }
	            progress++;
	        }
	    }
	/*
	
	public void cacheData() {
		System.out.println("Caching Thread");

		sharkList = new HashMap<>();
		sharkNames = new ArrayList<>(jaws.getSharkNames());
		sharkLocations = new HashMap<>();
		int total = sharkNames.size();
		System.out.println(total);
		for (String s : sharkNames) {
			System.out.println("caching shark data " + progress*100/total);
			Shark tempshark = jaws.getShark(s);
			if(tempshark!=null){
				sharkList.put(s, tempshark);
				Location tempLocation = jaws.getLastLocation(s);
				if(tempLocation!=null){
					sharkLocations.put(s, tempLocation);
				}
			}
			progress++;
		}
	}

*/

	public Shark getCachedShark(String name){
		if(sharkList.containsKey(name)){
			return sharkList.get(name);
		}
		return null;
	}
	
	public Location getCachedtLocation(String name){
		if(sharkLocations.containsKey(name)){
			return sharkLocations.get(name);
		}
		return null;
	}
	
	public ArrayList<String> getCachedNames(){
		ArrayList<String> names = new ArrayList<>(sharkNames);
		return names;
	}
	
	
}
