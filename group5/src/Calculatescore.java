import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthSpinnerUI;

public class Calculatescore {
	private static ArrayList<String> text = new ArrayList<>();
	private static Login login;
	private static Double[] score;
	private static ArrayList<Double> pointScore = new ArrayList<>();
	private static Points points ;
	private static Totalscoreterm scoreterm;
	private static int count = 0;
	private static String ss = "";
	private static String[] texts;
	private static String filestring;
	
	public static Double[] calculate(String stringfile, int index , String[] textall,String textsubject){
		
		filestring = stringfile;
		File file = new File(stringfile+".csv");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ss = stringfile+".csv";
		count = index;
		texts = textall;
		ArrayList<Double> standrad = new ArrayList<>();
		FileReader fr;
		boolean value = false;
		try {
			fr = new FileReader(new File("Datastandard.csv"));
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			String texttrue = textsubject + ",true";
			String text = "";
			while (s != null) {
				if(s.equals(texttrue)) {
					value = true;
				}else if(s.length() <= 2 && value ) {
					standrad.add(Double.valueOf(s));
				}else if(standrad.size() > 0 && s.length()>2) {
					value = false;
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
	
		score = new Double[textall.length];
		for (int i = 0; i < textall.length ; i++) {
			String[] ss = textall[i].split(",");
			for (int j = 0; j < ss.length; j++) {
				if(j > 2) {
					if(ss[index+3].equals("-")) {
						score[i] = (0*standrad.get(index))/100;	
					}else {			
						score[i] = (Double.parseDouble(ss[index+3])*standrad.get(index))/100;					
					}
				}
			}
		}
		
		return score;
	}

	public static void gradeFile() {
		ArrayList<String> text1 = new ArrayList<>();
		File file = new File("Grade"+ filestring +".csv");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		boolean value = false;
		FileReader fr;
		try {
			fr = new FileReader(new File(filestring+".csv"));
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			while (s != null) {
				String ss[] = s.split(",");
				if(ss[0].equals(login.getSubject())) {
					value = true;
				}else if(value) {
					text1.add(s);
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
		double scoresum = 0.0;
		Double[] score = new Double[text1.size()];
		String[] arrgrade = new String[text1.size()];
		for (int i = 0; i < text1.size() ; i++) {
			String[] ss = text1.get(i).split (",");
			for (int j = 0; j < ss.length; j++) {
				if(j > 2 &&  j % 2 == 0) {
					if(ss[j].equals("")) {
						scoresum += 0.0;
					}else {						
						scoresum += Double.parseDouble(ss[j]);					
					}
				}else if(j == 0){
					arrgrade[i] = ss[j];
				}
			}
			score[i] =  scoresum;
			scoresum = 0;
		}
		ArrayList<String> num = new ArrayList<>();
		try {
			fr = new FileReader(new File("Datastandard.csv"));
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			while (s != null) {
				if(s.equals(login.getSubject()+",true")) {
					value = true;
				}else if(s.length() < 3) {
					value = false;
				}else if(value && s.length() < 9) {
					num.add(s);
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
		
		String[] grade = {"A","B+","B","C+","C","D+","D","F"};
	
		for (int i = 0; i < score.length; i++) {
			for (int j = 0; j < num.size() ; j++) {
				String[] ss = num.get(j).split("-");
				if(Integer.parseInt(ss[1])>=score[i] && score[i]>=Integer.parseInt(ss[0])) {
					arrgrade[i] += "," + grade[j]; 
				}
			}
		}
		try {
			FileWriter fw = new FileWriter(new File("Grade"+ filestring +".csv"));
			PrintWriter writer = new PrintWriter(fw);
			for (int i = 0; i < arrgrade.length; i++) {
				writer.println(arrgrade[i]);
			}
			fw.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void writeFile() {
		boolean value = false ;
		text.clear();
		try {
			FileReader fr = new FileReader(new File(ss));
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			while (s != null) {
				String[] ts = s.split(",");
				if(ts[0].equals(login.getSubject())) {
					value = true;
				}else if(value) {
					text.add(s);
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
		if(text.size() == 0) {
			for (int i = 0; i < texts.length; i++) {
				text.add(texts[i]);										
			}
		}
			
		String textss = login.getSubject()+",IdName,,";
		for (int i = 0; i < points.stringName().size() ; i++) {
			textss += points.stringName().get(i) + ",,";
		}
		
		String[][] arrstr = new String[text.size()][];
		for (int i = 0; i < text.size(); i++) {
			String[] ss = text.get(i).split(",");
			String[] sss = texts[i].split(",");
			arrstr[i] = new String[points.stringName().size()+points.stringName().size()+3];
			for (int j = 0; j < arrstr[i].length ; j++) {
				if(count+count+4 == j) {
					arrstr[i][j] = score[i] + ","; 
				}else if(count+count+3 == j){
					try {
						arrstr[i][j] = sss[j-count] + ",";							
					}catch (ArrayIndexOutOfBoundsException e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null,"กรุณากรอกคะแนนให้ครบ");
						return ;
					}
				}else if(ss.length-1 < j){
					arrstr[i][j] =  ",";	
				}else {
					arrstr[i][j] = ss[j] + ",";	
				}
			}	
		}
		
		String arrst = "";
		String[] arrstr1 = new String[arrstr.length];
		
		for (int i = 0; i < arrstr.length ; i++) {
			for (int j = 0; j < arrstr[i].length; j++) {
				arrst += arrstr[i][j] ;
			}
			arrstr1[i] = arrst;
			arrst = "";
		}
		
		try {
			FileWriter fw = new FileWriter(new File(ss));
			PrintWriter writer = new PrintWriter(fw);
			writer.println(textss);
			for (int i = 0; i < arrstr1.length; i++) {
				writer.println(arrstr1[i]);
			}
			fw.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
