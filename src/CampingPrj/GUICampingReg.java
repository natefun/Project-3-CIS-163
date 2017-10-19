package CampingPrj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class GUICampingReg extends JFrame implements ActionListener{

	public static void main(String[] args) {
		JMenu File, checkingIn;
		JMenuBar FileMenu;
		JMenuItem OpenSerial,SaveSerial, OpenTxt,SaveTxt, Exit, TentIn, RVIn; 
		
		JFrame frame = new JFrame("Camping");
		
		File = new JMenu();
		checkingIn= new JMenu();
		FileMenu = new JMenuBar("File");
		
		OpenSerial= new JMenuItem("Open Serializable File");
		SaveSerial= new JMenuItem("Save Serializable File");
		OpenTxt= new JMenuItem("Open Text File");
		SaveTxt= new JMenuItem("Save Text File");
		Exit = new JMenuItem("Exit");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
