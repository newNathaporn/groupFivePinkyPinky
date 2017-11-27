
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
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
	private JButton b = new JButton("NEXT");
	private Totalscoreterm total ;
	public TotalscoretermFrame() {
		JPanel p = new JPanel();
		// TODO Auto-generated constructor stub
			
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		
		for (int i = 0; i < point.stringName().size() ; i++) {
			tab.add(point.stringName().get(i),new Totalscoreterm(i));
		}
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(total.getResult()) {
					Grade g = new Grade();
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "กรุณากรอกให้ครบทั้งหมด แล้วกด save ที่ Final ก่อน");
				}
			}
		});
		
		add(tab,BorderLayout.CENTER);
		add(b,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 750);
		setVisible(true);
		
	}
}
