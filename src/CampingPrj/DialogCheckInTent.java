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
		paOccupy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paOccupy.getContentPane().add(createPanel());
		paOccupy.setSize(300, 500);
		paOccupy.setVisible(true);
	}
	
	private JPanel createPanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.add(name);
		panel.add(nameTxt);
		panel.add(OccupyedOn);
		panel.add(OccupyedOnTxt);
		panel.add(staying);
		panel.add(stayingTxt);
		panel.add(siteNumber);
		panel.add(siteNumberTxt);
		panel.add(numOfGuests);
		panel.add(numOfGuestsTxt);
		panel.add(okButton);
		panel.add(cancelButton);
		return panel;
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton) {
				
			}
			else if(e.getSource() == cancelButton) {
				
			}
		}
	}
}
