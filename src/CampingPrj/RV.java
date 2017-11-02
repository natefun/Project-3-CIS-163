package CampingPrj;

import java.util.GregorianCalendar;

public class RV extends Site{
	/** holds the amount of power requested for each RV site */
	private int power;		//30, 40, 50 amps

	/******************************************************************
	 * Constructor
	 * Initializes the instance variable
	 * @param p holds the amount of power to the RV site
	 *****************************************************************/
	public RV(int p) {
		power = verifyPower(p);
	}
	
	public RV(int power, String nameReserving, int daysStaying, int siteNum, GregorianCalendar date) {
		super(nameReserving, daysStaying, siteNum, date);
		this.power = verifyPower(power);
	}

	/******************************************************************
	 * Sets the amount of power to the RV site
	 * @param p holds the amount of power to the RV site
	 *****************************************************************/
	public void setPower(int p) {
		power = verifyPower(p);
	}

	/******************************************************************
	 * Returns the amount of power to the RV site
	 * @return the amount of power to the RV site
	 *****************************************************************/
	public int getPower() {
		return power;
	}

	/******************************************************************
	 * Returns the amount of power to the RV site. Valid inputs are
	 * 30, 40, and 50 amps. Other input will be invalid and will
	 * return 0.
	 * @param p holds the amount of power at the RV site
	 * @return either the amount provided or 0
	 *****************************************************************/
	public int verifyPower(int p) {
		if(p == 30 || p == 40 || p == 50)
			return p;
		else
			return 0;
	}

	//	public RV (int p, int SiteNumber) {
	//		super(SiteNum);
	//	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 30 * daysStaying;
	}
}
