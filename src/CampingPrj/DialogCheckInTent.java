package CampingPrj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class DialogCheckInTent extends JDialog implements ActionListener{
	private JTextField nameTxt;
	private JTextField OccupyedOnTxt;
	private JTextField stayingTxt;
	private JTextField siteNumberTxt;
	private JComboBox powerTxt;
	private JButton okButton;
	private JButton cancelButton;
	private boolean closeStatus;
	private Site unit;
	
	public DialogCheckInTent(JFrame paOccupy, Site d) {
		nameTxt = new JTextField("John Smith");
		OccupyedOnTxt = new JTextField("03/14/2015");
		stayingTxt = new JTextField("7");
		siteNumberTxt = new JTextField("1");
		powerTxt = new JComboBox();	//Subject to change
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		closeStatus = false;
		unit = d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
