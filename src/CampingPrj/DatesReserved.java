package CampingPrj;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatesReserved {
	/** Array list of dates  */
	private ArrayList<ArrayList> dateList;
	
	/** Array list booleans that have whether it it taken or not */
	private ArrayList<Boolean> siteList;

	/******************************************************************
	 * Constructor, Creates ArrayLists that are needed
	 *****************************************************************/
	public DatesReserved() {
		dateList = new ArrayList<ArrayList>();

		//This sets up all of the lists of booleans
		siteList = new ArrayList<Boolean>();
		for(int i = 0; i < 5; i++) {
			siteList.add(false);
		}

		//This creates 20 years worth of boolean arraylists
		for(int i = 0; i < 15330; i++) {
			dateList.add((ArrayList<ArrayList>)siteList.clone());
		}
	}

	public ArrayList getDateList() {
		return dateList;
	}

	public void setDateList(ArrayList list) {
		dateList = list;
	}

	/******************************************************************
	 * Helper method that converts a GregorianCalendar date to an
	 * index that can be used to find the date you are looking for in
	 * the array
	 * @param date the date you would like to convert
	 *****************************************************************/
	private int dateToIndex(GregorianCalendar date) {
		int dayOfYear = date.get(Calendar.DAY_OF_YEAR);
		//int year = date.getInstance().get(date.YEAR);
		String[] dates = DateFormat.getDateInstance(DateFormat.SHORT).format(date.getTime()).split("/");
		int year = 2000 + Integer.parseInt(dates[2]);
		//System.out.println("DayOfTheYear " + dayOfYear);

		switch(year) {
		case 2015:
			return dayOfYear;
		case 2016:
			return dayOfYear + 365;
		case 2017:
			return dayOfYear + 2*365;
		case 2018:
			return dayOfYear + 3*365;
		case 2019:
			return dayOfYear + 4*365;
		case 2020:
			return dayOfYear + 5*365;
		default:
			return -1;
		}
	}

	/******************************************************************
	 * Reserves a site for 1 day
	 * @param siteNum The site you would like to reserve
	 * @param date the date you would like to reserve the site on
	 *****************************************************************/
	public void reserve(int siteNum, GregorianCalendar date) {
		//System.out.println("reserved"+DateFormat.getDateInstance(DateFormat.SHORT).format(date.getTime()));
		//System.out.println("sitenum"+siteNum);
		dateList.get(dateToIndex(date)).set(siteNum -1, true);
	}

	/******************************************************************
	 * Deletes one reservation
	 * @param siteNum The site you would like to delete
	 * @param date the date you would like to delete the site for
	 *****************************************************************/
	public void delete(int siteNum, GregorianCalendar date) {
		dateList.get(dateToIndex(date)).set(siteNum -1, false);
	}

	/******************************************************************
	 * Checks if a site is reserved
	 * @param siteNum The site you would like to check
	 * @param date the date you would like to check
	 *****************************************************************/
	public boolean isReserved(int siteNum, GregorianCalendar date) {
		//	System.out.println("tester"+dateList.get(367).get(0));
		//	System.out.println("tester2"+dateList.get(dateToIndex(date)).get(siteNum -1));
		//	System.out.println("date"+ dateToIndex(date));
		return (boolean) dateList.get(dateToIndex(date)).get(siteNum -1);

	}


	public boolean isReservedMultiple(int siteNum, GregorianCalendar date, int daysStaying) {
		int[] numDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		GregorianCalendar tempDate = null;

		String[] dates = DateFormat.getDateInstance(DateFormat.SHORT).format(date.getTime()).split("/");

		int day = Integer.parseInt(dates[1]);
		int month = Integer.parseInt(dates[0]);
		int year = 2000 + Integer.parseInt(dates[2]);

		boolean[] reserved = new boolean[daysStaying];
		//		System.out.println("day"+day);
		//		System.out.println("month"+month);
		//		System.out.println("year"+year);

		for(int i = 0; i < daysStaying; i++) {
			if(day+i <= numDaysInMonth[month-1]) {
				//if there are enough days in the month just add one to the day
				tempDate = new GregorianCalendar(year, month-1, day + i);
				//System.out.println("I got here");
			}
			else if(day+i > numDaysInMonth[month-1]) {
				//if there are not enough days in the month increment month
				month++;
				//fixes the days for the next month
				day = day - numDaysInMonth[month-1];
				if(month > 12) {
					//If the month is greater than 12 it rolls over to the next year
					month = 1;
					year++;
				}
				//add the new date in there
				tempDate = new GregorianCalendar(year, month-1, day + i);
			}
			//reserves the site for that day
			//System.out.println(DateFormat.getDateInstance(DateFormat.SHORT).format(tempDate.getTime()));
			reserved[i] = isReserved(siteNum, tempDate);
			//System.out.println("reserved[i]"+reserved[i]);
		}

		boolean returnBool = reserved[0];
		for(int i = 0; i < daysStaying; i++) {
			returnBool = returnBool || reserved[i];
		}

		return returnBool;

	}

	/******************************************************************
	 * Reserves a site for multiple days
	 * @param siteNum The site you would like to reserve
	 * @param date the date you would like to reserve the site on
	 *****************************************************************/
	public void reserveMultiple(int siteNum, GregorianCalendar date, int daysStaying) {
		int[] numDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		GregorianCalendar tempDate = null;

		String[] dates = DateFormat.getDateInstance(DateFormat.SHORT).format(date.getTime()).split("/");

		int day = Integer.parseInt(dates[1]);
		int month = Integer.parseInt(dates[0]);
		int year = 2000 + Integer.parseInt(dates[2]);

		//		System.out.println("day"+day);
		//		System.out.println("month"+month);
		//		System.out.println("year"+year);

		for(int i = 0; i < daysStaying; i++) {
			if(day+i <= numDaysInMonth[month-1]) {
				//if there are enough days in the month just add one to the day
				tempDate = new GregorianCalendar(year, month-1, day + i);
				//System.out.println("I got here");
			}
			else if(day+i > numDaysInMonth[month-1]) {
				//if there are not enough days in the month increment month
				month++;
				//fixes the days for the next month
				day = day - numDaysInMonth[month-1];
				if(month > 12) {
					//If the month is greater than 12 it rolls over to the next year
					month = 1;
					year++;
				}
				//add the new date in there
				tempDate = new GregorianCalendar(year, month-1, day + i);
			}
			//reserves the site for that day
			//System.out.println(DateFormat.getDateInstance(DateFormat.SHORT).format(tempDate.getTime()));
			reserve(siteNum, tempDate);
		}



	}


	/******************************************************************
	 * Reserves a site for multiple days
	 * @param siteNum The site you would like to reserve
	 * @param date the date you would like to reserve the site on
	 *****************************************************************/
	public void deleteMultiple(int siteNum, GregorianCalendar date, int daysStaying) {
		int[] numDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		GregorianCalendar tempDate = null;

		String[] dates = DateFormat.getDateInstance(DateFormat.SHORT).format(date.getTime()).split("/");

		int day = Integer.parseInt(dates[1]);
		int month = Integer.parseInt(dates[0]);
		int year = 2000 + Integer.parseInt(dates[2]);

		for(int i = 0; i < daysStaying; i++) {
			if(day+i <= numDaysInMonth[month-1]) {
				//if there are enough days in the month just add one to the day
				tempDate = new GregorianCalendar(year, month-1, day + i);
			}
			else if(day+i > numDaysInMonth[month-1]) {
				//if there are not enough days in the month increment month
				month++;
				//fixes the days for the next month
				day = day - numDaysInMonth[month-1];
				if(month > 12) {
					//If the month is greater than 12 it rolls over to the next year
					month = 1;
					year++;
				}
				//add the new date in there
				tempDate = new GregorianCalendar(year, month-1, day + i);
			}
			//deletes the site for that day
			delete(siteNum, tempDate);
		}


	}
}

