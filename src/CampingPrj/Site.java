package CampingPrj;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/*****************************************************************
Gathers and stores info such as checkin date, name, site number ect.

@author Brandon Thedorff
@author Denver DeBoer
@author Nathaniel Johnson
@version November 2nd 2017
 *****************************************************************/
public abstract class Site implements Serializable{
	/** The name of the person who is occupying the Site */
	protected String nameReserving;
	/** The date the Site was checked-in (occupied) */
	protected GregorianCalendar checkIn;
	/** is checking in  */
	protected int daysStaying;
	/** The Site number */
	protected int siteNumber;

	private static final long serialVersionUID = 1L;
	private static final String String = null;

	/*****************************************************************
	 * Default constructor sets everything to 0
	 *****************************************************************/
	public Site() {
		nameReserving = "";
		daysStaying = 0;
		siteNumber = 0;
		GregorianCalendar();

	}

	/******************************************************************
	 * Constructor that takes parameters
	 * @param name the name of the person reserving the site
	 * @param days they number of days they want to stay
	 * @param siteNum the site number that want to stay at
	 * @param date takes a GregorionCalendar to hold the date the checked
	 * in on
	 *****************************************************************/
	public Site(String name, int days, int siteNum, GregorianCalendar date) {
		nameReserving = name;
		daysStaying = days;
		siteNumber = siteNum;
		checkIn = date;
	}

	/******************************************************************
	 * Not sure what this does
	 *****************************************************************/
	private void GregorianCalendar() {

	}

	/******************************************************************
	 * Returns the name of the person reserving
	 * @return nameReserving The name of the reserver
	 *****************************************************************/
	public String getNameReserving() {
		return nameReserving;
	}

	/******************************************************************
	 * Sets the name of the person reserving
	 * @param nameReserving Name of the reserver
	 *****************************************************************/
	public void setNameReserving(String nameReserving) {
		this.nameReserving = nameReserving;
	}

	/******************************************************************
	 * Returns the check in date in the form of a GregorianCalendar
	 * @return checkIn The checkin date
	 *****************************************************************/
	public GregorianCalendar getCheckIn() {
		return checkIn;
	}

	/******************************************************************
	 * Sets the check in date in the form of a GregorianCalendar
	 * @param checkIn the check in date
	 *****************************************************************/
	public void setCheckIn(GregorianCalendar checkIn) {
		this.checkIn = checkIn;
	}

	/******************************************************************
	 * Returns the number of days staying
	 * @return daysStaying Returns days staying
	 *****************************************************************/
	public int getDaysStaying() {
		return daysStaying;
	}

	/******************************************************************
	 * Sets the number of days staying
	 * @param daysStaying sets the days staying
	 *****************************************************************/
	public void setDaysStaying(int daysStaying) {
		this.daysStaying = daysStaying;
	}

	/******************************************************************
	 * Returns the site number
	 * @return siteNumber The number of the site
	 *****************************************************************/
	public int getSiteNumber() {
		return siteNumber;
	}

	/******************************************************************
	 * Sets the site number
	 * @param siteNumber sets the site number staying
	 *****************************************************************/
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}

	/******************************************************************
	 * Gets the cost to stay at the site
	 *****************************************************************/
	public abstract int getCost();
}
