package app.view.searchFrame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import api.jaws.Jaws;
import api.jaws.Location;
import api.jaws.Shark;
import app.model.Cache;
import api.jaws.Ping;

public class SearchModel implements Comparator<Ping> {
	private Jaws jaws;
	private ArrayList<String> sharkNames;
	private Cache cache = new Cache();
	
	public SearchModel(Jaws jaws) {
		this.jaws = jaws;
		getSharkNames();
	}

	private void getSharkNames() {
		System.out.println("array copy");
		sharkNames = new ArrayList<>(cache.getCachedNames());
		for(String s: sharkNames){
			System.out.println(s);
		}
	}
	
	
	/**
	 * Creates a list of shark names that are required for the map based on ping times
	 * 
	 * @param pingTime String: The time frame for displaying the sharks, either "All Activity", "Last Week", "Last Week", "Last Month"
	 * @return ArrayList<String>, containing shark names to be displayed.
	 */
	public ArrayList<String> filterPing(String pingTime) {
		ArrayList<String> newSharkNames = new ArrayList<String>(sharkNames);
		switch (pingTime) {
			case "All Activity": {
				break;
			}
			case "Last 24 Hours": {
				newSharkNames.clear();
				ArrayList<Ping> pings = jaws.past24Hours();
				pings = sortAndFilterPing(pings);
				for (Ping p : pings) {
					newSharkNames.add(p.getName());
				}
				break;
			}
			case "Last Week": {
				newSharkNames.clear();
				ArrayList<Ping> pings = jaws.pastWeek();
				pings = sortAndFilterPing(pings);
				for (Ping p : pings) {
					newSharkNames.add(p.getName());
				}
				break;
			}
			case "Last Month": {
				newSharkNames.clear();
				ArrayList<Ping> pings = jaws.pastWeek();
				pings = sortAndFilterPing(pings);
				for (Ping p : pings) {
					newSharkNames.add(p.getName());
	 			}
				break;
			}
		}
		return newSharkNames;
	}

	
	/**
	 * 
	 * @param age String, indicating stage of life of the shark, either "Mature", "Immature" or "Unspecified"
	 * @param temp ArrayList<String>, list of shark names that need to be filtered
	 * @return ArrayList<String>, list of filtered names
	 */
	public ArrayList<String> filterAge(String age, ArrayList<String> temp) {
		
		ArrayList<String> newSharkNames = new ArrayList<>();
		ArrayList<String> namesToFilter = temp;
		
		if(age.equals("All Stages of Life")){
			newSharkNames = namesToFilter;
		}
		else{
			for (String s : namesToFilter) {
				if(cache.getCachedShark(s) != null){
					if (cache.getCachedShark(s).getStageOfLife().equals(age)) {
						newSharkNames.add(s);
					}
				}
			}
			return newSharkNames;
		}
		return newSharkNames;
	}

	
	/**
	 * 
	 * @param tag String, tag that has been extracted out of JComboBox
	 * @param temp ArrayList<Strings> of names that needs to be filtered through
	 * @return ArrayList<String> Containing Filtered Names
	 */
	public ArrayList<String> filterTag(String tag, ArrayList<String> temp) {
		ArrayList<String> newSharkNames = new ArrayList<>();
		ArrayList<String> namesToFilter = temp;
		if(tag.equals("All Tag Locations")){
			newSharkNames = namesToFilter;
		}
		else{
			for (String s : namesToFilter) {
				if(cache.getCachedShark(s) != null){
					if (cache.getCachedShark(s).getTagLocation().equals(tag)) {
						newSharkNames.add(s);
					}
				}
			}
			return newSharkNames;
		}
		return newSharkNames;
	}

	
	/**
	 * 
	 * @param gender String from JcomboBox specifying Selected gender, it can be "Male", "Female", "Unspecified"
	 * @param temp ArrayList<String> of names that needs to be filtered through
	 * @return ArrayList<String> of filtered names
	 */
	public ArrayList<String> filterGender(String gender, ArrayList<String> temp) {
		ArrayList<String> newSharkNames = new ArrayList<>();
		ArrayList<String> namesToFilter = temp;
		if(gender.equals("All Genders")){
			newSharkNames = namesToFilter;
		}
		else{
			for (String s : namesToFilter) {
				if(cache.getCachedShark(s) != null ){
					if (cache.getCachedShark(s).getGender().equals(gender)) {
						newSharkNames.add(s);
					}
				}
			}
		}
		return newSharkNames;
	}

	public HashMap<String, Location> getLocation(ArrayList<String> names) {
		HashMap<String, Location> sharksToDisplay = new HashMap<>();
		for (String s : names) {
			sharksToDisplay.put(s, cache.getCachedtLocation(s));
		}
		return sharksToDisplay;
	}

	public ArrayList<Ping> sortAndFilterPing(ArrayList<Ping> list) {
		
		list.sort(this);
		
		ArrayList<Ping> sortedAndFiltered = new ArrayList<Ping>();
		ArrayList<String> pingNames = new ArrayList<>();
		
		for(Ping p: list){
			if(!pingNames.contains(p.getName())){
				sortedAndFiltered.add(p);
				pingNames.add(p.getName());
			}
		}
		return sortedAndFiltered;
	}

	
	@Override
	public int compare(Ping o1, Ping o2) {
		return o1.getTime().compareTo(o2.getTime());
	}
	
	
	public ArrayList<String> getTagLocations(){
		ArrayList<String> tags = new ArrayList<>();
		System.out.println("check");
		for (String s : sharkNames) {
			System.out.println(s + " looping");
			if(cache.getCachedShark(s) != null){
				String tempTag = cache.getCachedShark(s).getTagLocation();
				tags.add(tempTag);
			}
		}
		return tags;
	}
}
