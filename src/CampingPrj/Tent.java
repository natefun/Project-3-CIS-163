package CampingPrj;

import java.util.GregorianCalendar;

/**********************************************************************
 * Holds data concerning a tent site
 * @author Denver DeBoer
 * @author Brandon Thedorff
 * @author Nathaniel Johnson
 * @version 1.0
 *********************************************************************/

public class Tent extends Site {
	/** Represents the number of tenters on this site */
	private int numOfTenters;

	/******************************************************************
	 * Default constructor
	 * Sets the instance variable to 1
	 *****************************************************************/
	public Tent() {
		numOfTenters = 1;
	}

	/******************************************************************
	 * 4 parameter constructor
	 * Sets the reserver's name, the number of days staying and the
	 * site number to the super class
	 * Sets the number of tenters in this class
	 * @param numTenters holds the number of tenters
	 * @param nameReserving holds the name of the reserver
	 * @param daysStaying holds the number of days staying
	 * @param siteNum holds the site number
	 *****************************************************************/
	public Tent(int numTenters, String nameReserving, int daysStaying, int siteNum, GregorianCalendar date) {
		super(nameReserving, daysStaying, siteNum, date);
		numOfTenters = numTenters;
	}

	/******************************************************************
	 * Getter method for the number of tenters
	 * @return the number of tenters
	 *****************************************************************/
	public int getNumOfTenters() {
		return numOfTenters;
	}

	/******************************************************************
	 * Setter method for the number of tenters
	 * @param numOfTenters the number of tenters
	 *****************************************************************/
	public void setNumOfTenters(int numOfTenters) {
		this.numOfTenters = numOfTenters;
	}


	/******************************************************************
	 * Calculates the cost
	 * Cost is 3 * number of tenters * number of days
	 * @return the cost of the stay
	 *****************************************************************/
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 3 * numOfTenters * super.daysStaying;
	}
}
