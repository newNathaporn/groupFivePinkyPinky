
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

public class Points extends JFrame {
	private JCheckBox standard = new JCheckBox("Standard");
	private ArrayList<JTextField> fieldName = new ArrayList<>();
	private static ArrayList<String> name = new ArrayList<>();
	private ArrayList<JTextField> fieldScore = new ArrayList<>();
	private static ArrayList<Double> score = new ArrayList<>();
	private int num = 0;
	private JButton ok = new JButton("OK");
	private ArrayList<JTextField> grade = new ArrayList<>();
	private ArrayList<JLabel> gradelabel = new ArrayList<>();
	private static boolean checkinputscore;
	private ArrayList<String> textall = new ArrayList<>();
	private static Login g;

	public Points() {
		// TODO Auto-generated constructor stub
		while (true) {
			try {
				do {
					num = Integer.valueOf(JOptionPane.showInputDialog(("Input number of score 3-5")));
				} while (!(num > 2 && num < 6));
				break;
			} catch (NumberFormatException e) {
				if (e.getMessage().equals("null"))
					return;
				JOptionPane.showMessageDialog(null, "Input number");
			}
		}

		grade.add(new JTextField(10));
		grade.add(new JTextField(10));
		grade.add(new JTextField(10));
		grade.add(new JTextField(10));
		grade.add(new JTextField(10));
		grade.add(new JTextField(10));
		grade.add(new JTextField(10));
		grade.add(new JTextField(10));

		Font font = new Font("TimesRoman", Font.BOLD, 30);
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel pall = new JPanel();

		p.setLayout(new BorderLayout());

		p2.setLayout(new GridLayout(2, 1));
		JLabel bel = new JLabel("BenchMark");
		bel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		p2.add(bel);
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
					fieldScore.get(0).setText("40");
					fieldScore.get(1).setText("40");
					for (int i = 0; i < grade.size() ; i++) {
						grade.get(i).setEnabled(false);
					}
				} else {
					for (int i = 0; i < grade.size(); i++) {
						grade.get(i).setText("");
						grade.get(i).setEnabled(true);	
					}
					for (int j = 0; j < num; j++) {
						fieldScore.get(j).setText("");
					}
				}
			}
		});
		p1.setLayout(new GridLayout(8 + num, 2));

		String[] gradetext = { "A", "B+", "B", "C+", "C", "D+", "D", "F" };
		for (int i = 0; i < 8; i++) {
			gradelabel.add(new JLabel(gradetext[i]));
			gradelabel.get(i).setFont(font);
		}

		for (int i = 0; i < grade.size(); i++) {
			p1.add(gradelabel.get(i));
			p1.add(grade.get(i));
		}

		for (int i = 0; i < num; i++) {
			if (i == 0) {
				fieldName.add(new JTextField("Midterm"));
				fieldName.get(i).setEnabled(false);
			} else if (i == 1) {
				fieldName.add(new JTextField("Final"));
				fieldName.get(i).setEnabled(false);
			} else {
				fieldName.add(new JTextField(""));
			}
			fieldName.get(i).setFont(new Font("TimesRoman", Font.BOLD, 20));
			fieldScore.add(new JTextField(""));
			p1.add(fieldName.get(i));
			p1.add(fieldScore.get(i));
		}

		p3.add(p1);
		p4.add(ok);
		p.add(p2, BorderLayout.NORTH);
		p.add(p3, BorderLayout.CENTER);
		p.add(p4, BorderLayout.SOUTH);
		ok.setFont(font);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean gread50 = true;
				ArrayList<String> ss = new ArrayList<>();
				int sumss = 0;
				int sum = 0;
				try {
					grade();
					if (Double.valueOf(fieldScore.get(0).getText()) + Double.valueOf(fieldScore.get(1).getText()) >= 50) {
						for (int i = 0; i < num; i++) {
							sum += Double.valueOf(fieldScore.get(i).getText());
							ss.add(fieldName.get(i).getText());
						}
						
						for (int i = 0; i < num ; i++) {
							for (int j = 0; j < num; j++) {
								if(ss.get(i).toLowerCase().equals(ss.get(j).toLowerCase()) && i != j) {
									sumss++;
								}
							}
						}
						if(sumss >= 2) {
							JOptionPane.showMessageDialog(null, "ชื่อคะแนนของท่านซ้ำกันครับ/ค่ะ");
							for (int i = 2; i < num ; i++) {
								fieldName.get(i).setText("");
							}
							return ;
						}
						if (sum == 100) {
							for (int i = 0; i < num; i++) {
								//if (!fieldName.get(i).getText().equals(""))
									name.add(fieldName.get(i).getText());
								//if (!fieldScore.get(i).getText().equals(""))
									score.add(Double.valueOf(fieldScore.get(i).getText()));
							}
							gread50 = false;							
						}else {
							JOptionPane.showMessageDialog(null, "Score total 100");
							for (int i = 0; i < fieldScore.size() ; i++) {
								fieldScore.get(i).setText("");
							}
							for (int i = score.size()-1 ; i >= 0 ; i--) {
								name.remove(i);
								score.remove(i);
							}
							return;
						}
					}
					if (gread50) {
						for (int i = 0; i < fieldScore.size(); i++) {
							fieldScore.get(i).setText("");
						}
						JOptionPane.showMessageDialog(null, "Input midterm and final >= 50%");
						return;
					}

					try {
						FileWriter fw = new FileWriter(new File("Datastandard.csv"), true);
						PrintWriter writer = new PrintWriter(fw);
						checkinputscore = true;
						writer.println(g.getSubject() + "," + checkinputscore);
						String s = "";
						for (int j = 0; j < name.size(); j++) {
							if(!name.get(j).equals(""))
							if(name.size()-1 == j)
								s += name.get(j) ;
							else 
								s += name.get(j) + ",";
						}
						writer.println(s);
						for (int i = 0; i < gradetext.length; i++) {
							writer.println(grade.get(i).getText());
						}
						for (int j = 0; j < score.size(); j++) {
							writer.println(fieldScore.get(j).getText());
						}
						writer.close();
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}
					TotalscoretermFrame f = new TotalscoretermFrame();
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
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}
		});
		add(p);
		setSize(500, 520 + (num * 40));
		setLocation(700, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void grade() throws Exception {

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
		File file = new File("Datastandard.csv");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			String texttrue = g.getSubject() + ",TRUE";
			String textall = "";
			while (s != null) {
				if (s.toUpperCase().equals(texttrue.toUpperCase())) {
					checkinputscore = true;
				} else if (checkinputscore) {
					textall = s;
					break;
				}
				s = reader.readLine();
			}
			reader.close();
			fr.close();
			String[] text = textall.split(",");
			for (int i = 0; i < text.length; i++) {
				if(!text[i].equals(""))
				name.add(text[i]);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		return checkinputscore;
	}

	public static ArrayList<String> stringName() {
		return name;
	}

	public static ArrayList<Double> doubleScore() {
		return score;
	}

	public static void main(String[] args) {
		new Points();
	}
}
