package CampingPrj;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Site implements Serializable{
	public static class SiteData {
		/** The name of the person who is occupying the Site */
		protected String nameReserving;
		/** The date the Site was checked-in (occupied) */
		protected GregorianCalendar checkIn;
		/** is checking in  */
		protected int daysStaying;
		/** The Site number */
		protected int siteNumber;

		private static final long serialVersionUID = 1L;
		
	
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
	}



	// add constructors
	// add getter, setter methods

}
