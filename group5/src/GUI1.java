

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GUI1 extends JFrame {
	private JCheckBox standard = new JCheckBox("Standard");
	private JTextField quiz = new JTextField(10);
	private JTextField mid = new JTextField(10);
	private JTextField fin = new JTextField(10);
	private JButton ok = new JButton("OK");
	private ArrayList<JTextField> grade = new ArrayList<>();
	private ArrayList<JLabel> gradelabel = new ArrayList<>();
	private static boolean checkinputscore ;
	private ArrayList<String> textall = new ArrayList<>();
	private static GUI g ;
	public GUI1() {
		// TODO Auto-generated constructor stub

		grade.add(new JTextField("50-100"));
		grade.add(new JTextField("40-49"));
		grade.add(new JTextField("30-39"));
		grade.add(new JTextField("20-29"));
		grade.add(new JTextField("10-19"));
		grade.add(new JTextField("8-9"));
		grade.add(new JTextField("6-7"));
		grade.add(new JTextField("0-5"));

		Font font = new Font("TimesRoman", Font.BOLD, 30);
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel pall = new JPanel();

		p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.cyan), "eiei"));
		p.setLayout(new BorderLayout());

		p2.setLayout(new GridLayout(2, 1));
		p2.add(new JLabel("ee"));
		p2.add(standard);
		standard.setFont(new Font("TimesRoman", Font.BOLD, 25));

		standard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (standard.isSelected()) {
					grade.get(0).setText("80-100");
					grade.get(1).setText("75-79");
					grade.get(2).setText("70-74");
					grade.get(3).setText("65-69");
					grade.get(4).setText("60-64");
					grade.get(5).setText("55-59");
					grade.get(6).setText("50-54");
					grade.get(7).setText("0-49");
					quiz.setText("20");
					mid.setText("40");
					fin.setText("40");
				} else {
					for (int i = 0; i < grade.size(); i++) {
						grade.get(i).setText("");
						quiz.setText("");
						mid.setText("");
						fin.setText("");
					}
				}
			}
		});
		p1.setLayout(new GridLayout(12, 2));

		String[] gradetext = { "A", "B+", "B", "C+", "C", "D+", "D", "F" };
		for (int i = 0; i < 8; i++) {
			gradelabel.add(new JLabel(gradetext[i]));
			gradelabel.get(i).setFont(font);
		}

		for (int i = 0; i < grade.size(); i++) {
			p1.add(gradelabel.get(i));
			p1.add(grade.get(i));
			if (i == grade.size() - 1) {
				JLabel quizz = new JLabel("Quiz");
				JLabel midterm = new JLabel("Midterm");
				JLabel finall = new JLabel("Final");
				quizz.setFont(font);
				midterm.setFont(font);
				finall.setFont(font);
				p1.add(quizz);
				p1.add(quiz);
				p1.add(midterm);
				p1.add(mid);
				p1.add(finall);
				p1.add(fin);
			}
		}

		p3.add(p1);
		p4.add(ok);

		p.add(p2, BorderLayout.NORTH);
		p.add(p3, BorderLayout.CENTER);
		p.add(p4, BorderLayout.SOUTH);
		ok.setFont(font);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) throws NumberFormatException {
				// TODO Auto-generated method stub
				boolean gread50 = true;
				try {
					eiei();
					if ((Integer.valueOf(mid.getText()) + Integer.valueOf(fin.getText())) >= 50) {
						if ((Integer.valueOf(mid.getText()) + Integer.valueOf(fin.getText())
								+ Integer.valueOf(quiz.getText()) == 100))
							gread50 = false;
					}
					if (gread50) {
						mid.setText("");
						fin.setText("");
						quiz.setText("");
						JOptionPane.showMessageDialog(null, "Input mid and fin > 50%");
					}
					
					try {
						FileWriter fw = new FileWriter(new File("Datastandard.csv"),true);
						PrintWriter writer = new PrintWriter(fw);
						checkinputscore = true;
						writer.println(g.getSubject()+","+checkinputscore);
						for (int i = 0; i < gradetext.length; i++) {
							writer.println(grade.get(i).getText());
						}
						writer.println(quiz.getText());
						writer.println(mid.getText());
						writer.println(fin.getText());
						writer.close();
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}
					GUI4 f = new GUI4();
					f.setVisible(true);
					setVisible(false);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("Input mid , fin");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					for (int i = 0; i < grade.size(); i++) {
						grade.get(i).setText("");
					}
					e.printStackTrace();
					// JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}
		});
		add(p);
		setSize(500, 700);
		setLocation(700, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void eiei() throws Exception {

		ArrayList<Integer> array = new ArrayList<>();
		ArrayList<Integer> array1 = new ArrayList<>();
		int count = 0;
		boolean value = false;
		String textt = "";
		for (int i = grade.size() - 1; i >= 0; i--) {
			String text = grade.get(i).getText();
			String text1[] = text.split("-");
			try {
				int numstart = Integer.valueOf(text1[0]);
				int numstop = Integer.valueOf(text1[1]);
				if (numstart > numstop)
					throw new Exception("input stop > start");
				while (numstart <= numstop) {
					array.add(numstart++);
				}
			} catch (NumberFormatException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Input again");
				return;
			}
		}
		String text = "";
		int countt = 0;
		for (int j = 0; j < array.size(); j++) {
			if (array.get(countt) == j) {
				countt++;
			} else {
				text += j + " ";
			}
			for (int k = 0; k < array.size(); k++) {
				if (array.get(j) == array.get(k)) {
					count++;
				}
			}
			if (count > 1) {
				array1.add(array.get(j));
				array.remove(j);
				value = true;
			}
			count = 0;
		}
		if (text != "")
			throw new Exception(text);

		Collections.sort(array1);
		for (int i = 0; i < array1.size(); i++) {
			textt += array1.get(i) + " ";
		}
		if (value) {
			throw new Exception("Input " + textt);
		}

	}

	public static boolean getcheck() {
		FileReader fr;
		try {
			fr = new FileReader(new File("Datastandard.csv"));
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			String texttrue = g.getSubject() + ",TRUE";
			while(s!=null) {
				if(s.toUpperCase().equals(texttrue.toUpperCase())) {
					checkinputscore = true;
				}
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
		return checkinputscore;
	}
	
	public static void main(String[] args) {
		new GUI1();
	}
}
