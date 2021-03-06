package CampingPrj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class GUICampingReg extends JFrame implements ActionListener{
	// declare GUI components (menu items, buttons, etc.) needed
	// constructor method that prepares the GUI
	// event listeners and other methods needed to build the GUI
	JMenu file, checkingIn, edit;
	JMenuBar menuBar;
	JMenuItem openSerial,saveSerial, openTxt,saveTxt, exit, tentIn, RVIn, undo, delete;//, redo; 

	private JTable jListTable;
	private SiteModel dList;
	private Timer autoSaveTime;

	public GUICampingReg() {		
		menuBar = new JMenuBar();

		file = new JMenu("File");
		edit = new JMenu("Edit");
		checkingIn= new JMenu("Checking In");

		openSerial= new JMenuItem("Open Serializable File");
		saveSerial= new JMenuItem("Save Serializable File");
		openTxt= new JMenuItem("Open Text File");
		saveTxt= new JMenuItem("Save Text File");
		exit = new JMenuItem("Exit");

		undo = new JMenuItem("Undo");
		delete = new JMenuItem("Delete");
		//redo = new JMenuItem("Redo");

		tentIn = new JMenuItem("Check-In Tent Site");
		RVIn = new JMenuItem("Check-In RV Site");

		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(checkingIn);

		file.add(openSerial);
		file.add(saveSerial);
		file.addSeparator();
		file.add(openTxt);
		file.add(saveTxt);
		file.addSeparator();
		file.add(exit);

		edit.add(undo);
		//edit.add(redo);
		edit.add(delete);

		checkingIn.add(tentIn);
		checkingIn.add(RVIn);

		openSerial.addActionListener(this);
		saveSerial.addActionListener(this);
		openTxt.addActionListener(this);
		saveTxt.addActionListener(this);
		exit.addActionListener(this);

		undo.addActionListener(this);
		delete.addActionListener(this);
		//redo.addActionListener(this);

		tentIn.addActionListener(this);
		RVIn.addActionListener(this);

		setJMenuBar(menuBar);

		dList = new SiteModel();
		jListTable = new JTable(dList);

		add(jListTable);
		add(new JScrollPane(jListTable));

		setVisible(true);
		setSize(800, 600);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		autoSaveTime = new Timer(30000, new TimeListener());
		autoSaveTime.start();

	}

	public static void main(String[] args) {
		new GUICampingReg();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(exit)) {
			SiteModel.autosaveTxt();
			SiteModel.autosaveSerial();
			System.exit(0);
		}

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
			int status = chooser.showSaveDialog(null);
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
			int status = chooser.showSaveDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				dList.saveTxt(filename, true);
			}
		}

		if(e.getSource().equals(undo)) {
			dList.undo();
			SiteModel.autosaveTxt();
			SiteModel.autosaveSerial();
		}

		//		if(e.getSource().equals(redo)) {
		//			dList.redo();
		//		}

		if(e.getSource().equals(delete)) {
			if(jListTable.getSelectedRow() != -1)
				dList.delete(jListTable.getSelectedRow());
			else
				dList.getColumnCount();
		}

		if(e.getSource().equals(tentIn)) {
			Tent t = new Tent();
			DialogCheckInTent x = new DialogCheckInTent(this, t);
			if(t.daysStaying != 0)
				dList.add(t);
		}


		if(e.getSource().equals(RVIn)) {
			RV r = new RV(0);
			DialogCheckInRv x = new DialogCheckInRv(this, r);
			if(r.daysStaying != 0)
				dList.add(r);
		}

		// Finally, to invoke this dialog box from the GUICampingReg class, the following code may help. 
		//		Object comp = e.getSource();
		//		Object resvRVItem = null;
		//		if (resvRVItem == comp) {
		//			RV t = new RV(0);
		//			DialogCheckInRv x = new DialogCheckInRv(this, t);
		//		}
	}

	private class TimeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			SiteModel.autosaveSerial();
			SiteModel.autosaveTxt();
			autoSaveTime.restart();
		}
	}

}
