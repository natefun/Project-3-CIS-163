package CampingPrj;

import java.text.DateFormat;
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
		for(int i = 0; i < 14600; i++) {
			dateList.add((ArrayList<ArrayList>)siteList.clone());
		}
	}
	
	private int dateToIndex(GregorianCalendar date) {
		int dayOfYear = date.getInstance().get(date.DAY_OF_YEAR);
		int year = date.getInstance().get(date.YEAR);
		
		switch(year) {
		case 2015:
			return dayOfYear;
		case 2016:
			return dayOfYear + 365;
		case 2017:
			return dayOfYear + 2*365;
		case 2018:
			return dayOfYear + 3*365;
		default:
			return -1;
		}
	}
	
	
	public void reserve(int siteNum, GregorianCalendar date) {
		dateList.get(dateToIndex(date)).set(siteNum -1, true);
	}
	
	public void delete(int siteNum, GregorianCalendar date) {
		dateList.get(dateToIndex(date)).set(siteNum -1, false);
	}
}
