package CampingPrj;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class SiteModel extends AbstractTableModel{
	private ArrayList<Site> listSites;
	private String[] columnNames = { "Name Reserving", "Checked in", "Days Staying", "Site #", "Tent/RV info"};

	// constructor method that initializes the arraylist
	public SiteModel() {
		listSites = new ArrayList<Site>();
		listSites.add(new Tent());
	}
	
	// override these two methods from AbstractTableModel class
	
	/******************************************************************
	 * Gets the name of the column
	 * @param col Which column you want the name of
	 *****************************************************************/
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/******************************************************************
	 * Returns how many columns there are.
	 *****************************************************************/
	public int getColumnCount() {
		return columnNames.length;
	}

	/******************************************************************
	 * Returns how many rows there are
	 *****************************************************************/
	public int getRowCount() {
		return listSites.size();
	}

	/******************************************************************
	 * Gets the total size of the ArrayList overrides AbstractListModel
	 *****************************************************************/
	public int getSize() {
		return listSites.size();
	}

	/******************************************************************
	 * Gets the element and an index overrides AbstractListModel
	 *****************************************************************/
	public Object getElementAt(int index) {
		return null;
	}

	/******************************************************************
	 * Gets the value at a specific row and column
	 * @param row
	 * @param col
	 *****************************************************************/
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	

	// add methods to add, delete, and update.
	// add methods to load/save accounts from/to a binary file
	/******************************************************************
	 * Saves database as serialized type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void saveSerial(String filename) {
		
	}
	
	/******************************************************************
	 * Loads database as serialized type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void loadSerial(String filename) {
		
	}
	
	/******************************************************************
	 * Saves database as txt type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void saveTxt(String filename) {
		
	}
	
	/******************************************************************
	 * Loads database as txt type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void loadTxt(String filename) {
		
	}
	// add other methods as needed

}
