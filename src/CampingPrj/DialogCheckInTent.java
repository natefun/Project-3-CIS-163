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
	private JTextField OcculyedOnTxt;
	private JTextField stayingTxt;
	private JTextField siteNumberTxt;
	private JComboBox powerTxt;
	private JButton okButton;
	private JButton cancelButton;
	private boolean closeStatus;
	private Site unit;
	
	public DialogCheckInTent(JFrame paOccupy, Site d) {
		unit = d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
