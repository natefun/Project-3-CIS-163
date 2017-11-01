package CampingPrj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class SiteModel extends AbstractTableModel implements Changeable{
	private ArrayList<Site> listSites;
	private ArrayList<ArrayList> undoList;
	private String[] columnNames = { "Name Reserving", "Checked in", "Days Staying", "Site #", "Tent/RV info"};
	private Site unit;
	int undoIndex = 2;

	// constructor method that initializes the arraylist
	public SiteModel() {
		GregorianCalendar testDate = new GregorianCalendar(10, 30, 2017);
		listSites = new ArrayList<Site>();
		undoList = new ArrayList<ArrayList>();
		undoList.add((ArrayList<ArrayList>)listSites.clone());
		//listSites.add(new Tent(3, "Nate Johnson", 5, 2, testDate));
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
	 * @return 
	 *****************************************************************/
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//This is not working yet
		switch(columnIndex) {
		case 0://returns name
			return listSites.get(rowIndex).getNameReserving();
		case 1://returns check in date
			return DateFormat.getDateInstance(DateFormat.SHORT).format(listSites.get(rowIndex).getCheckIn().getTime());
			//return listSites.get(rowIndex).getCheckIn().getTime();
		case 2://returns days staying
			return listSites.get(rowIndex).getDaysStaying();
		case 3://returns site number
			return listSites.get(rowIndex).getSiteNumber();
		case 4:
			if(listSites.get(rowIndex) instanceof Tent) //gets number of tenters
				return ((Tent) listSites.get(rowIndex)).getNumOfTenters()+ " Tenters";
			else
				return ((RV) listSites.get(rowIndex)).getPower() + " amps";
		default://Shouldn't ever run this but it has to be here
			return "";

		}
	}

	/******************************************************************
	 * Adds a new site to the arrayList
	 * @param site the site that you want to add
	 *****************************************************************/
	public void add(Site site) {
		//this method may need to change at some point
		listSites.add(site);
		fireTableRowsInserted(0, listSites.size());
		undoList.add((ArrayList<ArrayList>)listSites.clone());
	}

	/******************************************************************
	 * Deletes a row from the jTable
	 * @param rowIndex the index of the row you want to delete
	 *****************************************************************/
	public void delete(int rowIndex) {
		listSites.remove(rowIndex);
		fireTableRowsInserted(0, listSites.size());
		undoList.add((ArrayList<ArrayList>)listSites.clone());
	}

	/******************************************************************
	 * Updates the jTable display
	 *****************************************************************/
	public void update() {
		//No idea if this works
		fireTableRowsInserted(0, listSites.size());
	}

	/******************************************************************
	 * Saves database as serialized type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void saveSerial(String filename) {
		try{
			FileOutputStream fos= new FileOutputStream(filename);
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			//writes out the entire arraylist
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
			//creates new arraylist
			listSites = new ArrayList<Site>();
			//reads in arraylist
			for(int i = 0; i < readCase.size(); i++) {
				listSites.add(readCase.get(i));
				System.out.println(readCase.get(i));
			}
			//updates table
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


	/******************************************************************
	 * Saves database as txt type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void saveTxt(String filename) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for(Site s : listSites) {
				out.print(s.getNameReserving());
				out.print("\t");
				out.print(DateFormat.getDateInstance(DateFormat.SHORT).format(s.getCheckIn().getTime()));
				out.print("\t");
				out.print(s.getDaysStaying());
				out.print("\t");
				out.print(s.getSiteNumber());
				out.print("\t");
				if(s instanceof Tent) {
					out.print(((Tent) s).getNumOfTenters());
					out.print("\t");
					out.println(0);
				}
				else {
					out.print(((RV) s).getPower());
					out.print("\t");
					out.println(1);
				}
			}
			out.close();
			JOptionPane.showMessageDialog(null, "File saved", "Saved", JOptionPane.ERROR_MESSAGE);
		} catch (IOException ex) {
			System.out.println ("IO Error!");
		}

	}

	/******************************************************************
	 * Loads database as txt type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void loadTxt(String filename) {
		try {
			Scanner reader = new Scanner(new File(filename));
			String[] fileLine = reader.nextLine().split("\t");
			String name = fileLine[0];
			String[] date = fileLine[1].split("/");
			int month = Integer.parseInt(date[0]);
			int day = Integer.parseInt(date[1]);
			int year = Integer.parseInt(date[2]);
			GregorianCalendar calendar = new GregorianCalendar(year, month, day);
			int daysStaying = Integer.parseInt(fileLine[2]);
			int siteNumber = Integer.parseInt(fileLine[3]);
			int other = Integer.parseInt(fileLine[4]);
			int flag = Integer.parseInt(fileLine[5]);
			if(flag == 0)
				unit = new Tent(other, name, daysStaying, siteNumber, calendar);
			else if(flag == 1) {
				unit.setNameReserving(name);
				unit.setDaysStaying(daysStaying);
				unit.setSiteNumber(siteNumber);
				unit.setCheckIn(calendar);
				((RV) unit).setPower(other);
			}
			JOptionPane.showMessageDialog(null, "File loaded", "Loaded", JOptionPane.ERROR_MESSAGE);	
		}
		catch(Exception error) {
			System.out.println("IO Error");
		}
	}
	// add other methods as needed

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if(undoIndex != undoList.size() + 1 || undoIndex < 0) {
			listSites = undoList.get(undoList.size()-undoIndex);
			undoIndex++;
			fireTableRowsInserted(0, listSites.size());
		}

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		if(undoIndex > 2) {
			//listSites = undoList.get(undoList.size()+undoIndex);
			listSites = undoList.get(undoIndex-2);
			undoIndex++;
			fireTableRowsInserted(0, listSites.size());
		}
	}

}
