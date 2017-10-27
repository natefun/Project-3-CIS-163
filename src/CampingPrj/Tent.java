package CampingPrj;

public class Tent extends Site{
	/** Represents the number of tenters on this site */
	private int numOfTenters;

	public Tent() {
		numOfTenters = 0;
	}
	
	public Tent(int numTenters, String nameReserving, int daysStaying, int siteNum) {
		super(nameReserving, daysStaying, siteNum);
		numOfTenters = numTenters;
	}
	// add constructor
	// add getter, setter methods
	public int getNumOfTenters() {
		return numOfTenters;
	}

	public void setNumOfTenters(int numOfTenters) {
		this.numOfTenters = numOfTenters;
	}
	public char[] getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 3 * numOfTenters * super.daysStaying;
	}


}
