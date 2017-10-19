package CampingPrj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class DialogCheckInRv extends JDialog implements ActionListener{
	private JTextField nameTxt;
	private JTextField OccupyedOnTxt;
	private JTextField stayingTxt;
	private JTextField siteNumberTxt;
	private JComboBox powerTxt;
	private JButton okButton;
	private JButton cancelButton;
	private boolean closeStatus;
	private Site unit;  	

	public DialogCheckInRv(JFrame paOccupy, Site d) {	
		unit = d; 
//		...
	}
	public void actionPerformed(ActionEvent e) {
		
	}

}
