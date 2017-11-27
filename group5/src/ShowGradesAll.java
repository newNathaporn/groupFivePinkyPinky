import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowGradesAll extends JFrame{
	private Login login ;
	private JTextArea area = new JTextArea(20,20);
	private JButton close = new JButton("CLOSE");
	
	public ShowGradesAll() {
		// TODO Auto-generated constructor stub
		JScrollPane pane = new JScrollPane(area);
		add(pane,BorderLayout.CENTER);
		JPanel p = new JPanel();
		p.add(close);
		add(p,BorderLayout.SOUTH);
		ArrayList<String> text = new ArrayList<>();
		try {
			FileReader fr = new FileReader(new File("Grade"+login.getSubject()+".csv"));
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			while (s != null) {
				text.add(s);
				s = reader.readLine();
			}
			reader.close();
			fr.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String s = "";
		area.append(login.getSubject() + "\n");
		for (int i = 0; i < text.size() ; i++) {
			String[] ss = text.get(i).split(",");
			for (int j = 0; j < ss.length; j++) {
				s += ss[j]+ "  ";
			}
			area.append(s+ "\n");
			s = "";
			
		}
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
	}
	
}
