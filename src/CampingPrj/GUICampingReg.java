package CampingPrj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GUICampingReg extends JFrame implements ActionListener{
	// declare GUI components (menu items, buttons, etc.) needed
	// constructor method that prepares the GUI
	// event listeners and other methods needed to build the GUI
	JMenu file, checkingIn;
	JMenuBar menuBar;
	JMenuItem openSerial,saveSerial, openTxt,saveTxt, exit, tentIn, RVIn; 

	private JTable jListTable;
	private SiteModel dList;

	public GUICampingReg() {
		menuBar = new JMenuBar();

		file = new JMenu("File");
		checkingIn= new JMenu("Checking In");

		openSerial= new JMenuItem("Open Serializable File");
		saveSerial= new JMenuItem("Save Serializable File");
		openTxt= new JMenuItem("Open Text File");
		saveTxt= new JMenuItem("Save Text File");
		exit = new JMenuItem("Exit");

		tentIn = new JMenuItem("Check-In Tent Site");
		RVIn = new JMenuItem("Check-In RV Site");

		menuBar.add(file);
		menuBar.add(checkingIn);

		file.add(openSerial);
		file.add(saveSerial);
		file.addSeparator();
		file.add(openTxt);
		file.add(saveTxt);
		file.addSeparator();
		file.add(exit);

		checkingIn.add(tentIn);
		checkingIn.add(RVIn);

		openSerial.addActionListener(this);
		saveSerial.addActionListener(this);
		openTxt.addActionListener(this);
		saveTxt.addActionListener(this);
		exit.addActionListener(this);

		tentIn.addActionListener(this);
		RVIn.addActionListener(this);

		setJMenuBar(menuBar);

		dList = new SiteModel();
		jListTable = new JTable(dList);

		add(jListTable);
		add(new JScrollPane(jListTable));

		setVisible(true);
		setSize(800, 600);
	}

	public static void main(String[] args) {
		new GUICampingReg();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(exit))
			System.exit(0);

		if(e.getSource().equals(openSerial)) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				dList.loadSerial(filename);
			}
		}

		if(e.getSource().equals(saveSerial)) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				dList.saveSerial(filename);
			}
		}

		if(e.getSource().equals(openTxt)) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				dList.loadTxt(filename);
			}
		}

		if(e.getSource().equals(saveTxt)) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				dList.saveTxt(filename);
			}
		}

		if(e.getSource().equals(tentIn))
			System.exit(0);

		if(e.getSource().equals(RVIn))
			System.exit(0);

		// Finally, to invoke this dialog box from the GUICampingReg class, the following code may help. 
		Object comp = e.getSource();
		Object resvRVItem = null;
		if (resvRVItem == comp) {
			RV t = new RV(0);
			DialogCheckInRv x = new DialogCheckInRv(this, t);
		}
	}

}