package app.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import api.jaws.Location;


public class Administration extends Observable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5206960835462930155L;
	
	
	
	private User selectedUser;
	private ArrayList<User> users;
	
	
	public Administration(){
		users = new ArrayList<User>();
		selectedUser = new User("DEFAULT");
	}
	public Administration(String filename){
		
		try {
			FileInputStream fin = new FileInputStream("usernames.kai");
			ObjectInputStream in = new ObjectInputStream(fin);
			Administration readUsers = (Administration) in.readObject();
			
			
			users = readUsers.users;
			selectedUser = readUsers.selectedUser;
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Users read from a file");
		
		
	}
	
//	public boolean hasFavourites(String username){
//		
//		for(int i=0; i<users.size(); i++){
//			if(users.get(i).getUserName().equals(username)){
//				return !users.get(i).getFavourites().isEmpty();
//			}
//		}
//		
//		return false;
//	}
	
	public User getSelectedUser(){
		if(selectedUser == null){return null;}
		return selectedUser;
	}
	
	public void setSelectedUser(String username){
		if(selectedUser.getUserName() == username){return;}
		
		for(User u : users){
			if(u.getUserName() == username){
				selectedUser = u;
				notifyObservers();
				setChanged();
				
				return;
			}
		}
		selectedUser = new User(username);
		users.add(selectedUser);
		notifyObservers();
		setChanged();
		
		saveToFile();
	}
	
	
//	public ArrayList<String> getFavourites(String username){
//		User temp = null;
//		
//		for(User u : users){
//			if(u.getUserName() == username){
//				temp = u;
//			}
//		}
//		if(temp == null){return null;}
//		return temp.getFavourites();
//	}
	
//	public void addFavourtes(String sharkName){
//		selectedUser.addFavouriteShark(sharkName);
//	}
//	
//	public void removeFavourites(String sharkName){
//		selectedUser.removeFavouriteShark(sharkName);
//	}
	
	
	public String toString(){
		if(users.isEmpty()){
			return "NO USERS";
		}
		String s = "Users : \n";
		for(User u : users){
			if(u == selectedUser){
				s += u + "\t <- SELECTED USER\n";
			}else{
				s+= u + "\n";
			}
		}
		return s;
	}
	
	public User getUserByUsername(String username){
		for(User u : users){
			if(u.getUserName().equals(username)){
				return u;
			}
		}
		return null;
	}
	
	public ArrayList<User> getUsers(){
		return users;
	}
	
	public void saveToFile(){
		try {
			FileOutputStream fout = new FileOutputStream("usernames.kai");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			
			out.writeObject(this);
			out.close();
			fout.close();
			System.out.println("saved usernames to a file");
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double distFrom(double lat1, double lng1) {
		double lat2 = selectedUser.getLocation().getLatitude() ;
		double lng2 = selectedUser.getLocation().getLongitude();
	    double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    return (earthRadius * c);

	}
	
}
