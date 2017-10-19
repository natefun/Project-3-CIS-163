package CampingPrj;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogCheckInTent extends JDialog implements ActionListener{
	private JTextField nameTxt;
	private JTextField OccupyedOnTxt;
	private JTextField stayingTxt;
	private JTextField siteNumberTxt;
	private JTextField numOfGuests;
	private JButton okButton;
	private JButton cancelButton;
	private boolean closeStatus;
	private Site unit;
	private JPanel panel;
	
	public DialogCheckInTent(JFrame paOccupy, Site d) {
		nameTxt = new JTextField("John Smith");
		OccupyedOnTxt = new JTextField("03/14/2015");
		stayingTxt = new JTextField("7");
		siteNumberTxt = new JTextField("1");
		numOfGuests = new JTextField("1");
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		closeStatus = false;
		unit = d;
		
		paOccupy = new JFrame("Reserve an Tent site");
		paOccupy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paOccupy.getContentPane().add(createPanel());
		paOccupy.setSize(300, 500);
		paOccupy.setVisible(true);
	}
	
	private JPanel createPanel() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 1));
		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
