package CampingPrj;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private Site unit;
	private JPanel panel;
	private JFrame paOccupy;
	
	public DialogCheckInTent(JFrame paOccupy, Site d) {
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
		
		paOccupy = new JFrame("Reserve an Tent site");
		paOccupy.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		paOccupy.getContentPane().add(createPanel());
		paOccupy.setSize(300, 250);
		paOccupy.setVisible(true);
		
		this.paOccupy= paOccupy;
	}
	
	private JPanel createPanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.add(name);
		panel.add(nameTxt);
		panel.add(siteNumber);
		panel.add(siteNumberTxt);
		panel.add(OccupyedOn);
		panel.add(OccupyedOnTxt);
		panel.add(staying);
		panel.add(stayingTxt);
		panel.add(numOfGuests);
		panel.add(numOfGuestsTxt);
		panel.add(okButton);
		panel.add(cancelButton);
		return panel;
	}
	
	private int checkDays(int numDays) {
		if(numDays <= 365)
			return numDays;
		else
			return 0;
	}
	
	private int checkSiteNumber(int site) {
		if(site >= 1 && site <= 5)
			return site;
		else
			return 0;
	}
	
	private int checkMonth(int month) {
		if(month >= 1 && month <= 12)
			return month;
		else
			return 1;
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
		if(day > 0 && day <= numDaysInMonth[month])
			return day;
		else
			return 0;
	}
	
	private boolean isLeapYear(int year) {
		if(year % 4 == 0 && year % 100 != 0)
			return true;
		else if(year % 400 == 0)
			return true;
		else
			return false;
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton) {
				String name = nameTxt.getText();
				int days = checkDays(Integer.parseInt(stayingTxt.getText()));
				int site = checkSiteNumber(Integer.parseInt(siteNumberTxt.getText()));
				String[] date = OccupyedOnTxt.getText().split("/");
				int month = checkMonth(Integer.parseInt(date[0]));
				int year = checkYear(Integer.parseInt(date[2]));
				int day = checkDay(month, Integer.parseInt(date[1]), year);
				int numberOfGuests = Integer.parseInt(numOfGuestsTxt.getText());
				//Site sd = new Site(name, days, site);
			}
			else if(e.getSource() == cancelButton) {
				paOccupy.setVisible(false);
			}
		}
	}
}