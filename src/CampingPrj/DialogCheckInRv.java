package CampingPrj;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

/*****************************************************************
Creates the dialog box for checking in for RV

@author Brandon Thedorff
@author Denver DeBoer
@author Nathaniel Johnson
@version November 2nd 2017
*****************************************************************/

public class DialogCheckInRv extends JDialog {
	
	/** Instantiates Variables */
	private JTextField nameTxt;
	private JTextField OccupyedOnTxt;
	private JTextField stayingTxt;
	private JTextField siteNumberTxt;
	private JComboBox powerTxt;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel NameLab, SiteLab, DateLab, DaysLab, PowerLab;
	private boolean closeStatus;
	private Site unit;  	


	/*****************************************************************
	Creates the dialog box components for checking in for RV
	*****************************************************************/
	public DialogCheckInRv(JFrame paOccupy, Site d) {	
		super(paOccupy,true);
		unit = d; 



		/** Creates the text fields */
		nameTxt = new JTextField("Joe Dohn");
		OccupyedOnTxt = new JTextField("10/07/2018");
		stayingTxt = new JTextField("5");
		siteNumberTxt  = new JTextField("3");


		/** Creates the Jlabels*/
		NameLab = new JLabel("Name of reserver:");
		SiteLab =  new JLabel("Requested Site Number:");
		DateLab = new JLabel("Reseving Date:");
		DaysLab = new JLabel("Days Staying:");
		PowerLab = new JLabel("Type of power in amps:");
		
		/** Creates  the buttonlistener */
		ButtonListener listener = new ButtonListener();

		/** Creates the JButtons */
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		closeStatus = false;

		/** Creates JComboBox and its options */
		String[] options = { "30", "40", "50"};
		powerTxt = new JComboBox(options);




		/** Connects the buttons to the action listener*/
		okButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		powerTxt.addActionListener(listener);

		/** Sets defaults and adds to the JDialogBox*/
		setTitle("Reserve an RV site");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		createPanel();
		getContentPane();
		setSize(300, 250);
		setVisible(true);

	}

	/*****************************************************************
	class that Adds components to JDialogBox when called
	*****************************************************************/
	private void createPanel() {

		setLayout(new GridLayout(6, 2));
		add(NameLab);
		add(nameTxt);
		add(SiteLab);
		add(siteNumberTxt);
		add(DateLab);
		add(OccupyedOnTxt);
		add(DaysLab);
		add(stayingTxt);
		add(PowerLab);
		add(powerTxt);
		add(okButton);
		add(cancelButton);
	}

	/*****************************************************************
	Checks that the site number is 1 - 5
	@param SiteNum The site number
	*****************************************************************/
	private int checkSiteNumber(int SiteNum) {
		if(SiteNum >= 1 && SiteNum <= 5)
			return SiteNum;
		else
			return 0;
	}
	/*****************************************************************
	Checks that the days staying is positive
	@param daysStay The days staying
	*****************************************************************/
	private int checkDays(int daysStay) {
		if(daysStay > 0)
			return daysStay;
		else
			return 0;
	}

	/*****************************************************************
	Creates the dialog box components for checking in for RV
	*****************************************************************/
	private int checkMonth(int month) {
		if(month >= 1 && month <= 12)
			return month;
		else if (month > 12)
			return month % 12;
		else
			return month / -1;
	}

	private int checkYear(int year) {
		if(year > 0 && year < Integer.MAX_VALUE)
			return year;
		else
			return 0;
	}

	private int checkDay(int month, int day, int year) {
		int[] numDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if(isLeapYear(year))
			numDaysInMonth[1] += 1;
		if(day > 0 && day <= numDaysInMonth[month - 1])
			return day;
		else
			return 0;
	}

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

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == okButton) {
				String name = nameTxt.getText();
				String[] date = OccupyedOnTxt.getText().split("/");

				int SiteNum = 0;
				int daysStay = 0;

				try {
					SiteNum = Integer.parseInt(siteNumberTxt.getText());

				}
				catch (NumberFormatException p) {
					JOptionPane.showMessageDialog(null, "Please only type in numbers", null, JOptionPane.WARNING_MESSAGE);
				}

				try {

					daysStay = Integer.parseInt(stayingTxt.getText());

				}
				catch (NumberFormatException p) {
					JOptionPane.showMessageDialog(null, "Please only type in numbers", null, JOptionPane.WARNING_MESSAGE);
				}


				int month = checkMonth(Integer.parseInt(date[0])-1);
				int year = checkYear(Integer.parseInt(date[2]));
				int day = checkDay(month, Integer.parseInt(date[1]), year);
				GregorianCalendar checkInDate = new GregorianCalendar(year, month, day);

				checkSiteNumber(SiteNum);
				checkDays(daysStay);

				int power = Integer.parseInt((String)powerTxt.getSelectedItem());


				unit.setNameReserving(name);
				unit.setDaysStaying(daysStay);
				unit.setSiteNumber(SiteNum);
				unit.setCheckIn(checkInDate);
				((RV) unit).setPower(power);

				if (checkDays(daysStay) == 0) {
					JOptionPane.showMessageDialog(null, "Please stay for at least one day", null, JOptionPane.WARNING_MESSAGE);
					unit.setDaysStaying(0);
				}

				if (checkSiteNumber(SiteNum) <= 0 || checkSiteNumber(SiteNum) >=6) {
					JOptionPane.showMessageDialog(null, "Please stay at sites 1 - 5", null, JOptionPane.WARNING_MESSAGE);
					unit.setDaysStaying(0);
				}

				if (checkDays(daysStay) != 0 && checkSiteNumber(SiteNum) > 0 && checkSiteNumber(SiteNum) <=6) {
					Component frame = null;
					JOptionPane.showMessageDialog(frame, "The cost is $" + daysStay * 30 , "Price", JOptionPane.WARNING_MESSAGE);
					dispose();
				}
				
				
			}
			if (e.getSource() == cancelButton) {
				dispose();
			}
		}
	}



}