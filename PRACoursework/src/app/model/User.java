package app.model;

import java.io.Serializable;
import java.util.ArrayList;

import api.jaws.Location;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8583781726489475668L;
	
	private double lon;
	private double lat;
	
	private String username;
	private ArrayList<String> favourites;
	
	
	public User(String u){
		username = u;
		favourites = new ArrayList<String>();
		lon = -0.11941330000001926;
		lat = 51.5111388;   //long and lat of KCL
	}
	
	
	public String getUserName(){
		return username;
	}
	
	public ArrayList<String> getFavourites(){
		return favourites;
	}
	
	public void addFavouriteShark(String sharkName){
		favourites.add(sharkName);
	}
	public void removeFavouriteShark(String sharkName){
		for(int i=favourites.size() -1; i >=0; i-- ){
			if(favourites.get(i).equals(sharkName)){
				favourites.remove(i);
			}
		}
	}
	public void setLocation(double lat, double lon){
		this.lat = lat;
		this.lon = lon;
	}
	
	public String toString(){
		return username;
	}
	
	public Location getLocation(){
		return new Location(lon, lat);
	}
	
}
