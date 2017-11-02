package CampingPrj;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DatesReserved {
	private ArrayList<ArrayList> dateList;
	private ArrayList<Boolean> siteList;
	
	public DatesReserved() {
		dateList = new ArrayList<ArrayList>();
		
		//This sets up all of the lists of booleans
		siteList = new ArrayList<Boolean>();
		for(int i = 0; i < 5; i++) {
			siteList.add(false);
		}
		
		//This creates 20 years worth of boolean arraylists
		for(int i = 0; i < 7300; i++) {
			dateList.add((ArrayList<ArrayList>)siteList.clone());
		}
	}
	
	public void reserve(int siteNum, GregorianCalendar date) {
		
	}
	
	public void delete(int siteNum, GregorianCalendar date) {
		
	}
}
