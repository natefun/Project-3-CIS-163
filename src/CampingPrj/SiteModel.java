package CampingPrj;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
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
		return listSites.get(index);
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
		try{
			FileOutputStream fos= new FileOutputStream(filename);
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(listSites);
			oos.close();
			fos.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.out.println("Problems happened");
		}
	}

	/******************************************************************
	 * Loads database as serialized type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void loadSerial(String filename) {
		ObjectInputStream objectinputstream = null;
		try {
			FileInputStream streamIn = new FileInputStream(filename);
			objectinputstream = new ObjectInputStream(streamIn);
			ArrayList<Site> readCase = (ArrayList<Site>) objectinputstream.readObject();
			listSites = new ArrayList<Site>();
			for(int i = 0; i < readCase.size()-1; i++) {
				listSites.add(readCase.get(i));
				System.out.println(readCase.get(i));
			}
			
			fireTableRowsInserted(1, readCase.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(objectinputstream != null){
				try {
					objectinputstream .close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
	}
	}

	/******************************************************************
	 * Saves database as txt type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void saveTxt(String filename) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			out.println(listSites.size());
			for (int i = 0; i < listSites.size(); i++) {

				// listSites is an ArrayList<Site>
				Site SiteUnit = listSites.get(i);   	

				// Output the class name. 
				out.println(SiteUnit.getClass().getName()); 

				// Output the OccupyOn date to a file in a readable format.
				//				out.println(DateFormat.getDateInstance(DateFormat.SHORT).
				//						format(SiteUnit.getOccupyedOn().getTime()));

				// Output the Title of the Site
				out.println(SiteUnit.getTitle());

				// See if the curOccupy SiteUnit is a Tent, if so output the player. 
				if (SiteUnit instanceof Tent)
					out.println(((Tent)SiteUnit).getPlayer());
			}
			out.close();

		} catch (IOException ex) {
			System.out.println ("IO Error!");
		}

	}

	/******************************************************************
	 * Loads database as txt type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void loadTxt(String filename) {

	}
	// add other methods as needed

}
