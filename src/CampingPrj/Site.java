package CampingPrj;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

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

	/******************************************************************
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
	 *****************************************************************/
	public String getNameReserving() {
		return nameReserving;
	}

	/******************************************************************
	 * Sets the name of the person reserving
	 *****************************************************************/
	public void setNameReserving(String nameReserving) {
		this.nameReserving = nameReserving;
	}

	/******************************************************************
	 * Returns the check in date in the form of a GregorianCalendar
	 *****************************************************************/
	public GregorianCalendar getCheckIn() {
		return checkIn;
	}

	/******************************************************************
	 * Sets the check in date in the form of a GregorianCalendar
	 *****************************************************************/
	public void setCheckIn(GregorianCalendar checkIn) {
		this.checkIn = checkIn;
	}
	
//	public String getCheckIn() {
//		
//		this.checkIn = check
//		String dateStr = checkIn;
//		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//		try {
//			Date checkIn = (Date)formatter.parse(dateStr);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return dateStr;  
//	}

	/******************************************************************
	 * Returns the number of days staying
	 *****************************************************************/
	public int getDaysStaying() {
		return daysStaying;
	}

	/******************************************************************
	 * Sets the number of days staying
	 *****************************************************************/
	public void setDaysStaying(int daysStaying) {
		this.daysStaying = daysStaying;
	}

	/******************************************************************
	 * Returns the site number
	 *****************************************************************/
	public int getSiteNumber() {
		return siteNumber;
	}

	/******************************************************************
	 * Sets the site number
	 *****************************************************************/
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	
	/******************************************************************
	 * Gets the cost to stay at the site
	 *****************************************************************/
	public abstract int getCost();

	/******************************************************************
	 * No idea
	 *****************************************************************/
	public Object getOccupyedOn() {
		// TODO Auto-generated method stub
		//These were generated since saveTXT uses them
		return null;
	}

	/******************************************************************
	 * No idea
	 *****************************************************************/
	public char[] getTitle() {
		// TODO Auto-generated method stub
		//These were generated since saveTXT uses them
		return null;
	}



	// add constructors
	// add getter, setter methods

}
