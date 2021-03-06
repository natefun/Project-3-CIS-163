package CampingPrj;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


//test
public class DialogCheckInTent extends JDialog {
	private JLabel name;
	private JLabel OccupyedOn;
	private JLabel staying;
	private JLabel siteNumber;
	private JLabel numOfGuests;
	private JTextField nameTxt;
	private JTextField OccupyedOnTxt;
	private JTextField stayingTxt;
	private JTextField siteNumberTxt;
	private JTextField numOfGuestsTxt;
	private JButton okButton;
	private JButton cancelButton;
	private boolean closeStatus;
	private Tent unit;

	public DialogCheckInTent(JFrame paOccupy, Tent d) {
		super(paOccupy, true);

		name = new JLabel("Name:");
		OccupyedOn = new JLabel("Occupyed on Date:");
		staying = new JLabel("Days planning on staying:");
		siteNumber = new JLabel("Requested site number:");
		numOfGuests = new JLabel("Number of tenters:");

		nameTxt = new JTextField("John Smith");
		OccupyedOnTxt = new JTextField("03/14/2015");
		stayingTxt = new JTextField("7");
		siteNumberTxt = new JTextField("1");
		numOfGuestsTxt = new JTextField("1");

		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");

		closeStatus = false;
		unit = d;

		ButtonListener listener = new ButtonListener();

		nameTxt.addActionListener(listener);
		OccupyedOnTxt.addActionListener(listener);
		stayingTxt.addActionListener(listener);
		siteNumberTxt.addActionListener(listener);
		numOfGuestsTxt.addActionListener(listener);
		okButton.addActionListener(listener);
		cancelButton.addActionListener(listener);

		setTitle("Reserve an Tent site:");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane();
		createPanel();
		setSize(300, 250);
		setVisible(true);
	}

	/**
	 * Create and add items to the dialog box
	 */
	private void createPanel() {

		setLayout(new GridLayout(6, 2));
		add(name);
		add(nameTxt);
		add(siteNumber);
		add(siteNumberTxt);
		add(OccupyedOn);
		add(OccupyedOnTxt);
		add(staying);
		add(stayingTxt);
		add(numOfGuests);
		add(numOfGuestsTxt);
		add(okButton);
		add(cancelButton);	
	}

	/**
	 * Error check the number of days staying
	 * @param numDays
	 * @return
	 */
	private int checkDays(int numDays) {
		if(numDays > 0)
			return numDays;
		else
			return 1;
	}

	/**
	 * Error check the site number
	 * @param site
	 * @return
	 */
	private int checkSiteNumber(int site) {
		if(site >= 1 && site <= 5)
			return site;
		else
			return 0;
	}

	/**
	 * Error check the provided month
	 * @param month
	 * @return
	 */
	private int checkMonth(int month) {
		if(month >= 1 && month <= 12)
			return month;
		else
			return 0;
	}

	/**
	 * Error check the provided year
	 * @param year
	 * @return
	 */
	private int checkYear(int year) {
	//	if(year > 2015 && year < 2020)
		if(year > 0 && year < 2037)
			return year;
		else
			return 0;
	}

	/**
	 * Error check the provided day
	 * @param month
	 * @param day
	 * @param year
	 * @return
	 */
	private int checkDay(int month, int day, int year) {
		int[] numDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if(isLeapYear(year))
			numDaysInMonth[1] += 1;
		if(day > 0 && day <= numDaysInMonth[month - 1])
			return day;
		else
			return 0;
	}

	/**
	 * Error check the number of guests
	 * @param guests
	 * @return
	 */
	private int checkGuests(int guests) {
		if(guests > 0)
			return guests;
		else
			return 1;
	}

	/**
	 * Determine if the provided year is a leap year
	 * @param year
	 * @return 
	 */
	private boolean isLeapYear(int year) {
		if(year % 4 == 0 && year % 100 != 0)
			return true;
		else if(year % 100 == 0)
			return false;
		else if(year % 400 == 0)
			return true;
		else
			return false;
	}

	/** button listeners **/
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton) {
				/** Get data from dialog box */
				String name = nameTxt.getText();
				String[] date = OccupyedOnTxt.getText().split("/");
				int days = 0;
				int site  = 0;
				int numberOfGuests = 0;

				try {
					days = checkDays(Integer.parseInt(stayingTxt.getText()));
				}
				catch (NumberFormatException p) {
					JOptionPane.showMessageDialog(null, "Please only type in numbers", null, JOptionPane.WARNING_MESSAGE);
				}

				try {
					site = checkSiteNumber(Integer.parseInt(siteNumberTxt.getText()));
				}
				catch (NumberFormatException p) {
					JOptionPane.showMessageDialog(null, "Please only type in numbers", null, JOptionPane.WARNING_MESSAGE);
				}

				try {
					numberOfGuests = checkGuests(Integer.parseInt(numOfGuestsTxt.getText()));
				}
				catch (NumberFormatException p) {
					JOptionPane.showMessageDialog(null, "Please only type in numbers", null, JOptionPane.WARNING_MESSAGE);
				}
				int month = checkMonth(Integer.parseInt(date[0])-1);
				int year = checkYear(Integer.parseInt(date[2]));
				int day = checkDay(month, Integer.parseInt(date[1]), year);


				/**
				 * Create GregorianCalendar and initialize to provided
				 * date to sent to tent class
				 */
				GregorianCalendar checkInDate = new GregorianCalendar(year, month, day);

				/** Sent data to tent class */
				unit.setNameReserving(name);
				unit.setDaysStaying(days);
				unit.setSiteNumber(site);

				if (checkSiteNumber(site) <= 0 || checkSiteNumber(site) >=6) {
					JOptionPane.showMessageDialog(null, "Please stay at sites 1 - 5", null, JOptionPane.WARNING_MESSAGE);
					unit.setDaysStaying(0);
				}


				if(SiteModel.datesReserved.isReservedMultiple(site, new GregorianCalendar(year, month, day), days) == false) {
					unit.setCheckIn(checkInDate);
				}
				else {
					JOptionPane.showMessageDialog(null, "Already reservation on at least one of the dates", null, JOptionPane.WARNING_MESSAGE);
					unit.setDaysStaying(0);
					days = 0;
					dispose();
				}
				unit.setNumOfTenters(numberOfGuests);

				/** Dispose of the dialog box */
				if (checkDays(days) == 0) {
					JOptionPane.showMessageDialog(null, "Please stay for at least one day", null, JOptionPane.WARNING_MESSAGE);
					unit.setDaysStaying(0);
				}



				if (checkDays(days) != 0 && checkSiteNumber(site) > 0 && checkSiteNumber(site) <=6) {
					Component frame = null;
					if(days != 0) {
						JOptionPane.showMessageDialog(null, "The cost is $" + unit.getCost(), "Price", JOptionPane.WARNING_MESSAGE);
					}
					dispose();
				}



			}
			else if(e.getSource() == cancelButton) {
				dispose();
			}
		}
	}
}