import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Grade extends JFrame{
	private JLabel subject = new JLabel("");
	private JButton g = new JButton("Grade");
	private JButton s = new JButton("Send");
	private Login login ;
	public Grade() {
		// TODO Auto-generated constructor stub
		subject.setFont(new Font("TimesRoman", Font.BOLD, 30));
		//subject.setText(login.getSubject());
		subject.setText("SCCSCS");
		JPanel p = new JPanel();
		p.add(g);
		p.add(s);

		JPanel p1 = new JPanel();
		p1.add(subject);
		
		setLayout(new GridLayout(3, 1,0,-5));
		add(new JLabel());
		add(p1);
		add(p);
		
		g.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ShowGradesAll();
			}
		});
		
		
		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
