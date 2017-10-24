package CampingPrj;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JPanel panel;
	private JFrame paOccupy;
	

	
	public DialogCheckInRv(JFrame paOccupy, Site d) {	
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

//		powerTxt = new JComboBox();
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		closeStatus = false;
		
		String[] options = { "30", "40", "50"};
		powerTxt = new JComboBox(options);
		
		
		
		okButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		powerTxt.addActionListener(listener);
		
		paOccupy = new JFrame("Reserve an Tent site");
		paOccupy.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		paOccupy.getContentPane().add(createPanal());
		paOccupy.setSize(300, 250);
		paOccupy.setVisible(true);
		
		this.paOccupy = paOccupy;
	}
	
	private JPanel createPanal() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.add(NameLab);
		panel.add(nameTxt);
		panel.add(SiteLab);
		panel.add(siteNumberTxt);
		panel.add(DateLab);
		panel.add(OccupyedOnTxt);
		panel.add(DaysLab);
		panel.add(stayingTxt);
		panel.add(PowerLab);
		panel.add(powerTxt);
		panel.add(okButton);
		panel.add(cancelButton);
		return panel;
	}
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == okButton) {
				
			}
			if (e.getSource() == cancelButton) {
				paOccupy.setVisible(false);
			}
		}
	}

}
