package CampingPrj;

import java.io.Serializable;

public class Site implements Serializable{
	private static final long serialVersionUID = 1L;

	/** The name of the person who is occupying the Site */
	protected String nameReserving;

	/** The date the Site was checked-in (occupied) */
	protected GregorianCalendar checkIn;

	/** The estimated number of days the person is reserving */
	/** This is just an estimate when the camper is  */
	/** is checking in  */
	protected int daysStaying; 

	/** The Site number */
	protected int siteNumber;  

	// add constructors
	// add getter, setter methods

}
