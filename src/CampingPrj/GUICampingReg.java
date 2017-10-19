package CampingPrj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class GUICampingReg extends JFrame implements ActionListener{
	   // declare GUI components (menu items, buttons, etc.) needed
	   // constructor method that prepares the GUI
	   // event listeners and other methods needed to build the GUI


//	public static void main(String[] args) {
//		JMenu File, checkingIn;
//		JMenuBar FileMenu;
//		JMenuItem OpenSerial,SaveSerial, OpenTxt,SaveTxt, Exit, TentIn, RVIn; 
//		
//		JFrame frame = new JFrame("Camping");
//		
//		File = new JMenu();
//		checkingIn= new JMenu();
//		FileMenu = new JMenuBar();
//		
//		OpenSerial= new JMenuItem("Open Serializable File");
//		SaveSerial= new JMenuItem("Save Serializable File");
//		OpenTxt= new JMenuItem("Open Text File");
//		SaveTxt= new JMenuItem("Save Text File");
//		Exit = new JMenuItem("Exit");
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// Finally, to invoke this dialog box from the GUICampingReg class, the following code may help. 
		Object comp = e.getSource();
		Object resvRVItem;
		if (resvRVItem == comp) {
			RV t = new RV(0);
			DialogCheckInRv x = new DialogCheckInRv(this, t);

	}

}
