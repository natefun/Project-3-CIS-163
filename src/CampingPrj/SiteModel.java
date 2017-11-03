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
/*****************************************************************
Database and the main class for everything that isnt gui

@author Brandon Thedorff
@author Denver DeBoer
@author Nathaniel Johnson
@version November 2nd 2017
 *****************************************************************/
public class SiteModel extends AbstractTableModel{
	/** Array list of sites  */
	private static ArrayList<Site> listSites;
	/** Array list of undos  */
	private static ArrayList<ArrayList> undoList;
	/** Array list of undos  */
	private static ArrayList<ArrayList> datesUndoList;
	/** Names of columns  */
	private String[] columnNames = { "Name Reserving", "Checked in", "Days Staying", "Site #", "Tent/RV info"};
	/** A site */
	private Site unit;
	/** Index for undos  */
	int undoIndex = 2;
	/** dates reserved to prevent double booking  */
	public static DatesReserved datesReserved;
	static int timesTxt = 1;
	static int times = 1;



	// constructor method that initializes the arraylist
	public SiteModel() {
		//GregorianCalendar testDate = new GregorianCalendar(10, 30, 2017);
		listSites = new ArrayList<Site>();
		undoList = new ArrayList<ArrayList>();
		undoList.add((ArrayList<ArrayList>)listSites.clone());

		//listSites.add(new Tent(3, "Nate Johnson", 5, 2, testDate));
		datesReserved = new DatesReserved();
		//		datesReserved.reserveMultiple(1, new GregorianCalendar(2016,0,1), 2);
		//		datesReserved.reserve(1, new GregorianCalendar(2016,0,1));
		//		datesReserved.reserve(1, new GregorianCalendar(2016,0,2));
		//		System.out.println(datesReserved.isReserved(1, new GregorianCalendar(2016,0,1)));
		//		System.out.println(datesReserved.isReserved(1, new GregorianCalendar(2016,0,2)));
		//		System.out.println(datesReserved.isReservedMultiple(1, new GregorianCalendar(2016,0,2), 2));

		datesUndoList = new ArrayList<ArrayList>();
		datesUndoList.add((ArrayList<ArrayList>)datesReserved.getDateList().clone());
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
		datesReserved.reserveMultiple(site.getSiteNumber(), site.getCheckIn(), site.getDaysStaying());
		fireTableRowsInserted(0, listSites.size());
		undoList.add((ArrayList<ArrayList>)listSites.clone());
		datesUndoList.add((ArrayList<ArrayList>)datesReserved.getDateList().clone());
		SiteModel.autosaveTxt();
		SiteModel.autosaveSerial();
	}

	/******************************************************************
	 * Deletes a row from the jTable
	 * @param rowIndex the index of the row you want to delete
	 *****************************************************************/
	public void delete(int rowIndex) {
		datesReserved.deleteMultiple(listSites.get(rowIndex).getSiteNumber(), listSites.get(rowIndex).getCheckIn(), listSites.get(rowIndex).getDaysStaying());
		listSites.remove(rowIndex);
		fireTableRowsInserted(0, listSites.size());
		undoList.add((ArrayList<ArrayList>)listSites.clone());
		datesUndoList.add((ArrayList<ArrayList>)datesReserved.getDateList().clone());

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
	 * @return 
	 *****************************************************************/
	public static void saveSerial(String filename) {
		try{
			FileOutputStream fos= new FileOutputStream(filename);
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			//writes out the entire arraylist
			oos.writeObject(listSites);
			oos.writeObject(undoList);
			oos.writeObject(datesUndoList);
			oos.close();
			fos.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.out.println("Problems happened");
		}
	}

	public static void autosaveSerial() {
		String filename = "autosaveSerial";
		int i = times;
		if (i < 5 && i > 0) {
			saveSerial(filename + i);
			times++;
		}
		else {
			times = 1;
			saveSerial(filename + i);

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
				//System.out.println(readCase.get(i));
			}
			
			for(int i = 0; i < listSites.size(); i++) {
				datesReserved.reserveMultiple(listSites.get(i).getSiteNumber(), listSites.get(i).getCheckIn(), listSites.get(i).getDaysStaying());
			}
			
			ArrayList<ArrayList> readCase3 = (ArrayList<ArrayList>) objectinputstream.readObject();
			//creates new arraylist
			undoList = new ArrayList<ArrayList>();
			//reads in arraylist
			for(int i = 0; i < readCase3.size(); i++) {
				undoList.add(readCase3.get(i));
				//System.out.println(readCase3.get(i));
			}
			
			ArrayList<ArrayList> readCase4 = (ArrayList<ArrayList>) objectinputstream.readObject();
			//creates new arraylist
			datesUndoList = new ArrayList<ArrayList>();
			//reads in arraylist
			for(int i = 0; i < readCase4.size(); i++) {
				datesUndoList.add(readCase4.get(i));
				//System.out.println(readCase4.get(i));
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
	 * @return 
	 *****************************************************************/
	public static void saveTxt(String filename, boolean flag) {
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
			if(flag)
				JOptionPane.showMessageDialog(null, "File saved", "Saved", JOptionPane.ERROR_MESSAGE);
		} catch (IOException ex) {
			System.out.println ("IO Error!");
		}

	}

	public static void autosaveTxt() {
		String filename = "autosave";
		int i = timesTxt;
		if (i < 5 && i > 0) {
			saveTxt(filename + i, false);
			timesTxt++;
		}
		else {
			timesTxt = 1;
			saveTxt(filename + i, false);

		}
	}

	/******************************************************************
	 * Loads database as txt type
	 * @param filename the filepath you want to save to
	 *****************************************************************/
	public void loadTxt(String filename) {
		try {
			//Add loop to run through file lines
			//Last line of loop: add(unit)
			Scanner reader = new Scanner(new File(filename));
			while(reader.hasNextLine()) {
				try {
					String[] fileLine = reader.nextLine().split("\t");
					String name = checkName(fileLine[0]);
					String[] date = fileLine[1].split("/");
					int month = checkMonth(Integer.parseInt(date[0]));
					int year = checkYear(Integer.parseInt(date[2]));
					int day = checkDay(month, Integer.parseInt(date[1]), year);
					GregorianCalendar calendar = new GregorianCalendar(year, month, day);
					int daysStaying = checkDaysStaying(Integer.parseInt(fileLine[2]));
					int siteNumber = checkSiteNumber(Integer.parseInt(fileLine[3]));
					int flag = checkFlag(Integer.parseInt(fileLine[5]));
					int other = checkOther(Integer.parseInt(fileLine[4]), flag);
					if(flag == 0)
						unit = new Tent(other, name, daysStaying, siteNumber, calendar);
					else if(flag == 1) {
						unit = new RV(other, name, daysStaying, siteNumber, calendar);
					}
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid Input", "ERROR", JOptionPane.INFORMATION_MESSAGE);

				}
				add(unit);
				update();
			}
			reader.close();
		}
		catch(Exception error) {
			System.out.println("IO Error");
		}
	}
	// add other methods as needed

	public void undo() {
		if(undoIndex != undoList.size() + 1 || undoIndex < 0) {
			listSites = undoList.get(undoList.size()-undoIndex);
			datesReserved.setDateList(datesUndoList.get(datesUndoList.size()-undoIndex));
			undoIndex++;
			fireTableRowsInserted(0, listSites.size());
			SiteModel.autosaveTxt();
			SiteModel.autosaveSerial();
		}

	}
	
	private int checkMonth(int m) {
		if(m >= 1 && m <= 12)
			return m;
		else {
			JOptionPane.showMessageDialog(null, "Corrupted file. Value switched to 1.", "IO ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
		}
	}
	
	private int checkYear(int y) {
		if(y >= 2015 && y <= 2020)
			return y;
		else {
			JOptionPane.showMessageDialog(null, "Corrupted file. Value switched to 2015.", "IO ERROR", JOptionPane.ERROR_MESSAGE);
			return 2015;
		}
	}
	
	private int checkDay(int month, int day, int year) {
		int numDaysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if(isLeapYear(year))
			numDaysInMonth[1] += 1;
		if(day > 0 && day < numDaysInMonth[month - 1])
			return day;
		else {
			JOptionPane.showMessageDialog(null, "Corrupted file. Value switched to 1.", "IO ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
		}
	}
	
	/******************************************************
	 * Determine if the provided year is a leap year
	 * @param y The year that is given
	 * @return true if its a leap year, flase if not
	 ******************************************************/
	private boolean isLeapYear(int y) {
		if(y % 4 == 0 && y % 100 != 0)
			return true;
		else if(y % 100 == 0)
			return false;
		else if(y % 400 == 0)
			return true;
		else
			return false;
	}
	
	/******************************************************
	 * Determine if the provided year is a leap year
	 * @param n  The name of the checkIn reserver
	 * @return n if the name is returned correct
	 ******************************************************/
	private String checkName(String n) {
		if(n != null)
			return n;
		else {
			JOptionPane.showMessageDialog(null, "Corrupted file. Switched value to A.", "IO ERROR", JOptionPane.ERROR_MESSAGE);
			return "A";
		}
	}
	
	/******************************************************
	 * Determine if the provided year is a leap year
	 * @param d The number of days staying
	 * @return d if the number is valid 
	 ******************************************************/
	private int checkDaysStaying(int d) {
		if(d > 0)
			return d;
		else {
			JOptionPane.showMessageDialog(null, "Corrupted file. Value switched to 1.", "IO ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
		}
	}
	
	/******************************************************
	 * Determine if the provided year is a leap year
	 * @param site the site number being reserved
	 * @return site A valid site number
	 ******************************************************/
	private int checkSiteNumber(int site)
	{
		if(site > 0 && site < 6)
			return site;
		else {
			JOptionPane.showMessageDialog(null, "Corrupted file. Value switched to 1.", "IO ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
		}
	}
	
	/******************************************************************
	 * Checks whether or not the data is for a tent or RV
	 * @return 0 or 1
	 *****************************************************************/
	private int checkFlag(int f) {
		if(f == 0 || f == 1)
			return f;
		else {
			JOptionPane.showMessageDialog(null, "Corrupted file. Value switched to 0.", "IO ERROR", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}
	
	/******************************************************************
	 * Checks whether or not the number of tenters or amount of power
	 * is valid
	 * @param num number of players or amount of power
	 * @param flag 0 if tent or 1 if RV
	 * @return number of tenters or amount of power
	 *****************************************************************/
	private int checkOther(int num, int flag)
	{
		if(flag == 0) {
			if(num > 0)
				return num;
			else {
				JOptionPane.showMessageDialog(null, "Corrupted file. Value switched to 1.", "IO ERROR", JOptionPane.ERROR_MESSAGE);
				return 1;
			}
		}
		else {
			if(num == 30 || num == 40 || num == 50)
				return num;
			else {
				JOptionPane.showMessageDialog(null, "Corrupted file. Value switched to 30.", "IO Error", JOptionPane.ERROR_MESSAGE);
				return 30;
			}
		}
	}
}
