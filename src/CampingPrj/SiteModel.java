package CampingPrj;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

public class SiteModel extends AbstractTableModel{



	private ArrayList<Site> listSites;
	private String[] columnNames = { "Name Reserving", "Checked in", "Days Staying", "Site #", "Tent/RV info"};

	// constructor method that initializes the arraylist
	// override these two methods from AbstractTableModel class

	/******************************************************************
	 * Gets the value at a specific row and column
	 * @param row
	 * @param col
	 *****************************************************************/
	public Object getValuesAt(int row, int col) {
		
		return null;
	}
	
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

	// add methods to add, delete, and update.
	// add methods to load/save accounts from/to a binary file
	// add other methods as needed

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

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
