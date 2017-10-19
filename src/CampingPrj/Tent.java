package CampingPrj;

public class Tent extends Site{
	/** Represents the number of tenters on this site */
	private int numOfTenters;

	public Tent() {
		numOfTenters = 0;
	}
	// add constructor
	// add getter, setter methods
	public int getNumOfTenters() {
		return numOfTenters;
	}

	public void setNumOfTenters(int numOfTenters) {
		this.numOfTenters = numOfTenters;
	}


}
