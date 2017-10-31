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

	public Site() {
		nameReserving = "";
		daysStaying = 0;
		siteNumber = 0;
		GregorianCalendar();

	}

	public Site(String name, int days, int siteNum, GregorianCalendar date) {
		nameReserving = name;
		daysStaying = days;
		siteNumber = siteNum;
		checkIn = date;
	}

	private void GregorianCalendar() {

	}

	public String getNameReserving() {
		return nameReserving;
	}

	public void setNameReserving(String nameReserving) {
		this.nameReserving = nameReserving;
	}

	public GregorianCalendar getCheckIn() {
		return checkIn;
	}

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

	public int getDaysStaying() {
		return daysStaying;
	}

	public void setDaysStaying(int daysStaying) {
		this.daysStaying = daysStaying;
	}

	public int getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	
	public abstract int getCost();

	public Object getOccupyedOn() {
		// TODO Auto-generated method stub
		//These were generated since saveTXT uses them
		return null;
	}

	public char[] getTitle() {
		// TODO Auto-generated method stub
		//These were generated since saveTXT uses them
		return null;
	}



	// add constructors
	// add getter, setter methods

}
