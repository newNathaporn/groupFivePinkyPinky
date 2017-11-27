import java.awt.BorderLayout;
import java.awt.Font;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowGrades extends JFrame{
	private Login login ;
	private JTextArea area = new JTextArea(20,20);
	private JButton close = new JButton("CLOSE");
	public ShowGrades(int index) {
		ArrayList<String> text = new ArrayList<>();
		area.setFont(new Font("TimesRoman", Font.BOLD, 20));
		boolean value = false;
		try {
			FileReader fr = new FileReader(new File(login.getSubject()+".csv"));
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
		if(index > 0) index+=2;
		System.out.println(index);
		String s = "";
		ArrayList<String> textall = new ArrayList<>();
		for (int i = 0; i < text.size() ; i++) {
			String ss[] = text.get(i).split(",");
			for (int j = 0; j < ss.length; j++) {
				if(j == 0) {
					s+= ss[j] + "  " ;						
				}else if(index+3 == j) {
					s+= ss[j] + "  " ;					
				}else if(index+4 == j) {
					s+= ss[j] ;										
				}
			}
			/*if(i == 0) {
				s+= "55";
			}*/
			textall.add(s);
			s = "";
		}
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		JScrollPane pane = new JScrollPane(area);
		JPanel p = new JPanel();
		p.add(close);
		add(pane,BorderLayout.CENTER);
		add(p,BorderLayout.SOUTH);
		for (int i = 0; i < textall.size() ; i++) {
			area.append(textall.get(i)+"\n");
		}
		
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
