package CampingPrj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GUICampingReg extends JFrame implements ActionListener{
	   // declare GUI components (menu items, buttons, etc.) needed
	   // constructor method that prepares the GUI
	   // event listeners and other methods needed to build the GUI


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
