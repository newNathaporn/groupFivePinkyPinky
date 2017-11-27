import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Grade extends JFrame{
	private JLabel subject = new JLabel("");
	private JButton g = new JButton("Grade");
	private JButton s = new JButton("Send");
	private Login login ;
	private Totalscoreterm total ;
	private int count = 0;
	private ArrayList<String> text = new ArrayList<>();
	public Grade() {
		// TODO Auto-generated constructor stub
		subject.setFont(new Font("TimesRoman", Font.BOLD, 30));
		//subject.setText(login.getSubject());
		subject.setText(login.getSubject());
		JPanel p = new JPanel();
		p.add(g);
		p.add(s);

		JPanel p1 = new JPanel();
		p1.add(subject);
		
		setLayout(new GridLayout(3, 1,0,-5));
		add(new JLabel());
		add(p1);
		add(p);
		if(login.getValueName().equals("n")) {
			s.setEnabled(false);
		}
		readFile();
		String[] textmail = {"mossclub_na_kup@hotmail.com","nun_pretty@hotmail.co.th","EH_2939@hotmail.com"
				,"new_ziizyy@hotmail.co.th","poppy-domo@hotmail.com"};
		
		s.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Sendemail s = new Sendemail(textmail[0]);				
				for (int i = 0; i < total.getCount5()-1 ; i++) {
					if(count == 5) count = 0;
					Sendemail ss = new Sendemail(text.get(i),textmail[count],login.getSubject());
					count++;
				}
			}
		});
		
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
	
	public void readFile() {
		ArrayList<String> texts = new ArrayList<>();
		try {
			FileReader fr = new FileReader(new File("Grade"+"cs211"+".csv"));
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			while (s != null) {
				texts.add(s);
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
		String ss = "";
		for (int i = 0; i < texts.size() ;i++) {
			String[] s = texts.get(i).split(",");
			for (int j = 0; j < s.length; j++) {
				if(s.length-1 == j) {
					ss+="Grade :" + s[j] ;
				}else {
					ss+=s[j] + "  ";					
				}
			}
			text.add(ss);
			ss = "";
		}
		
	}
	public static void main(String[] args) {
		new Grade();
	}
}
