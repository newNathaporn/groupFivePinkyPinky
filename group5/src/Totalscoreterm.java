
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.table.DefaultTableModel;

public class Totalscoreterm extends JPanel {
	private JTextField studentsabsent;
	private JButton save;
	private JButton result;
	private DefaultTableModel tableModel;
	private Login login;
	private Points point;
	private ArrayList<String> textsum = new ArrayList<>();
	private String[][] textall;
	private int scoreterm;
	private int file;
	private int count = 0;
	private Calculatescore cal ;
	public Totalscoreterm(int scoreterm) {
		// TODO Auto-generated constructor stub
		this.scoreterm = scoreterm;
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel pall = new JPanel();
		studentsabsent = new JTextField(7);
		studentsabsent.setText("0");
		p.setLayout(new GridLayout(3, 2, 20, 5));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		p.add(new JLabel("Number of student absent"));
		p.add(studentsabsent);
		p.add(new JLabel(""));
		
		save = new JButton("SAVE");
		result = new JButton("RESULT");
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Code");
		tableModel.addColumn("Score");
		
		JTable table = new JTable(tableModel);
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setPreferredSize(new Dimension(300, 500));
		textall = sizeStudent();
		
		if(scoreterm == 1) {
			if(!value()) 
				save.setEnabled(false);
		}
		
		String text[] = new String[2];
		
		for (int i = 0; i < textall.length; i++) {
			text[0] = textall[i][0];
			text[1] = textall[i][3 + scoreterm];
			tableModel.addRow(text);
		}
		result.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(value()) 
					save.setEnabled(true);
				}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textall = sizeStudent();
				int num = 0;
				String text = "";		
				int numstu = 0;
				num = 3 + scoreterm;
				for (int i = 0; i < textall.length; i++) {
					text = tableModel.getValueAt(i, 1).toString();
					if(text.equals("0") || text.equals("")) {
						numstu++;
					}
				}
				try {
					if(Integer.parseInt(studentsabsent.getText())-numstu>0){
						JOptionPane.showMessageDialog(null, "กรอกคะแนนให้นักศึกษาใหม่ครับ");
						return;
					}else if(Integer.parseInt(studentsabsent.getText())-numstu<0) {
						JOptionPane.showMessageDialog(null, "กรอกคะแนนให้นักศึกษาใหม่ครับ");
						return;
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "กรอกจำนวนคนขาดสอบด้วยครับ");
					return;
				}
				
				for (int i = 0; i < textall.length; i++) {
					text = tableModel.getValueAt(i, 1).toString();
					textall[i][num] = text;
				}
				
				String text1 = "";
				String[] t = new String[textall.length];
				for (int i = 0; i < textall.length; i++) {
					for (int j = 0; j < textall[i].length; j++) {
						if(j == (textall[i].length-1))  
							text1 += textall[i][j];
						else
							text1 += textall[i][j] + ",";
					}
					if(!text1.equals(""))
					t[i] = text1;
					text1 = "";
				}
				
				/*for (int i = 0; i < textall.length; i++) {
					for (int j = 0; j < textall[i].length; j++) {
						System.out.print(textall[i][j] + " ");
					}
					System.out.println();
				}*/
				boolean valuee = false;
				try {
					FileWriter fw = new FileWriter(new File("Datascore.csv"));
					PrintWriter writer = new PrintWriter(fw);
					for (int i = 0; i < textsum.size(); i++) {
						if (i == file) {
							writer.println(login.getSubject());
							valuee = true;
						} else if (valuee) {
							for (int j = 0; j < t.length; j++) {
								writer.println(t[j]);
							}
							valuee = false;
							i += t.length-1;
						} else if (valuee == false) {
							writer.println(textsum.get(i));
						}
					}
					fw.close();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cal.calculate(login.getSubject(), scoreterm, t);
				cal.writeFile();
			}
		});

		tableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				// TODO Auto-generated method stub
				int data = 0;
				int row = 0;
				int column = 0;
				int eventType = e.getType();
				if (eventType == TableModelEvent.UPDATE) {
					DefaultTableModel tableModel = (DefaultTableModel) e.getSource();
					row = e.getFirstRow();
					column = e.getColumn();
					String dataUpdated = tableModel.getValueAt(row, column).toString();
					
					try {
						data = Integer.valueOf(dataUpdated);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						if(column == 1) {
							tableModel.setValueAt(0, row, column);
							JOptionPane.showMessageDialog(null, "Input Number");
							return;
						}
					}
				}
				
			}
		});

		pall.setLayout(new BorderLayout());
		pall.add(p, BorderLayout.NORTH);
		pall.add(tableScroll, BorderLayout.CENTER);
		p2.add(save);
		if(scoreterm == 1) {
			p2.add(result);
		}
		pall.add(p2, BorderLayout.SOUTH);
		add(pall, BorderLayout.CENTER);
	}

	public String[][] sizeStudent() {
		
		ArrayList<String> id = new ArrayList<>();
		textsum.clear();
		boolean value = false;
		int count = 0;
		int count5 = 0;
		try {
			FileReader fr = new FileReader(new File("Datascore.csv"));
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			while (s != null) {
				textsum.add(s);
				String[] textstudent = s.split(",");
				if (textstudent[0].equals(login.getSubject())) {
					value = true;
					file = count5;
				} else if (textstudent[0].length() < 6 && !textstudent[0].equals(login.getSubject()) && value) {
					value = false;
				} else if (value) {
					count++;
					id.add(s);
				}
				s = reader.readLine();
				count5++;
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
		 
		String textall [][] ;
		textall = new String[id.size()][3+point.stringName().size()];
		String textsum[] = new String[3+point.stringName().size()];
		for (int i = 0; i < id.size(); i++) {
			String text[] = id.get(i).split(",");
			for (int j = 0; j < textsum.length; j++) {
				String textt = "";
				if (j > text.length - 1) {
					textt = "";
				} else {
					textt = text[j];
				}
				textsum[j] = textt;
			}
			for (int j = 0; j < 3+point.stringName().size() ; j++) {
				textall[i][j] = textsum[j];
			}
		}
		return textall;
	}
	
	public boolean value() {
		point.getcheck();
		
		int index = 0;
		boolean value = false ;
		try (FileReader fr = new FileReader((new File("Datascore.csv")));
				BufferedReader reader = new BufferedReader(fr);){
			String s = reader.readLine();
			String[] ss = new String[point.stringName().size() + 3]; 
			while(s != null){
				String[] text = s.split(",");
				if(text[0].equals(login.getSubject())) {
					value = true;	
				}else if (text[0].length() < 6 && !text[0].equals(login.getSubject()) && value) {
					value = false;
				}
				else if(value) {
					String[] texta = s.split(",");
					for (int i = 0; i < point.stringName().size() + 3; i++) {
						for (int j = 0; j < point.stringName().size() + 3 ; j++) {
							if(j >= texta.length) 
								ss[j] = "" ;
							else
								ss[j] = texta[j];
						}
						if(ss[i].equals("") && i!=4) {
							index++;
						}
					}
				}
				s = reader.readLine();
			}
			if(index == 0) {
				value = true;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
