package CampingPrj;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class SiteModel extends AbstractListModel{



	private ArrayList<Site> listSites;
	private String[] columnNames = { "Name Reserving", "Checked in", "Days Staying", "Site #", "Tent/RV info"};

	// constructor method that initializes the arraylist
	// override these two methods from AbstractTableModel class

	public Object getValuesAt(int row, int col) {
		return col;
		//...
	}
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return listSites.size();
	}

	// add methods to add, delete, and update.
	// add methods to load/save accounts from/to a binary file
	// add other methods as needed

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
