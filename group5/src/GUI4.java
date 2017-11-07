

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GUI4 extends JFrame{

	public GUI4() {
		JPanel p = new JPanel();
		// TODO Auto-generated constructor stub
		
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		tab.add("Quiz",new Panelmid(0));
		tab.add("Midterm",new Panelmid(1));
		tab.add("Final",new Panelmid(2));
		add(tab);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 750);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new GUI4();
	}
}
