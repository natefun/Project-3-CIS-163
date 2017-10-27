package CampingPrj;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class DialogCheckInRv extends JDialog {
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
	

	
	public DialogCheckInRv(JFrame paOccupy, Site d) {	
		super(paOccupy,true);
		unit = d; 
		
		
		
		//Add defaults later for display
		nameTxt = new JTextField("Joe Dohn");
		OccupyedOnTxt = new JTextField("10/07/2018");
		stayingTxt = new JTextField("5");
		siteNumberTxt  = new JTextField("3");
		
	

		NameLab = new JLabel("Name of reserver:");
		SiteLab =  new JLabel("Requested Site Number:");
		DateLab = new JLabel("Reseving Date:");
		DaysLab = new JLabel("Days Staying:");
		PowerLab = new JLabel("Type of power in amps:");
		
		ButtonListener listener = new ButtonListener();


		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		closeStatus = false;
		
		String[] options = { "30", "40", "50"};
		powerTxt = new JComboBox(options);
		
		
		
		okButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		powerTxt.addActionListener(listener);
		
		setTitle("Reserve an RV site");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		createPanel();
		getContentPane();
		setSize(300, 250);
		setVisible(true);
		
		


		try 
		{  
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
			Date startDate = df.parse(OccupyedOnTxt.getText());
		}
		catch (Exception e) {

		}

	}
	
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
	
	private int checkSiteNumber(int SiteNum) {
		if(SiteNum >= 1 && SiteNum <= 5)
			return SiteNum;
		else
			return 0;
	}
	
//	private int checkDays(int reserveDay) {
//		if(reserveDay <= 365)
//			return reserveDay;
//		else
//			return 0;
//	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == okButton) {
				int SiteNum = Integer.parseInt(siteNumberTxt.getText());
				int daysStay = Integer.parseInt(stayingTxt.getText());
				
				checkSiteNumber(SiteNum);
				//SiteNum = super.siteNumber;
				
			}
			if (e.getSource() == cancelButton) {
				dispose();
				
			}
		}
	}

}
