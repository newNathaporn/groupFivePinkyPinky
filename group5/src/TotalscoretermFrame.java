
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TotalscoretermFrame extends JFrame{
	private Points point ;
	public TotalscoretermFrame() {
		JPanel p = new JPanel();
		// TODO Auto-generated constructor stub
			
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		
		for (int i = 0; i <point.stringName().size(); i++) {
			System.out.println(point.stringName().get(i));
		}
		
		for (int i = 0; i < point.stringName().size()/2 ; i++) {
			tab.add(point.stringName().get(i),new Totalscoreterm(i));
			System.out.println(i);
		}
		add(tab);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 750);
		setVisible(true);
		
	}
}
