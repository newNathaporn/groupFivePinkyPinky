import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	public static Double[] calculate(String stringfile, int index , String[] textall){
		
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
			String texttrue = login.getSubject() + ",true";
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
			score[i] = (Double.parseDouble(ss[index+3])*standrad.get(index))/100;
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
		String[][] text = new String[text1.size()][];
		for (int i = 0; i < text1.size() ; i++) {
			String[] ss = text1.get(i).split (",");
			text[i] = new String[ss.length];
			for (int j = 0; j < ss.length; j++) {
				//text[i][j] 
			}
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
		
		/*for (int i = 0; i < texts.length; i++) {
			System.out.println(texts[i]);
		}*/

		String[] arrstr2 = new String[texts.length];
		for (int i = 0; i < texts.length ; i++) {
			String[] ss = text.get(i).split(",");
			for (int j = 0; j < ss.length; j++) {
				if(j > 2) {
					arrstr2[i] += ss[j] + ",,";					
				}else {
					arrstr2[i] += ss[j] + ",";
				}
			}
			System.out.println(arrstr2[i]);
		}

		
		
		/*String[][] arrstr = new String[text.size()][];
		String ars = "";
		for (int i = 0; i < text.size(); i++) {
			String[] ss = text.get(i).split(",");
			String[] sss = texts[i].split(",");
			arrstr[i] = new String[ss.length];
			for (int j = 0; j < ss.length; j++) {
				if(count+3 == j) {
					arrstr[i][j] = sss[j] + "," + score[i] +","; 
				}else if(count+4 == j){
					arrstr[i][j] = "";	
				}else if(j>2){
					arrstr[i][j] = ss[j] + ",,";						
				}else {
					arrstr[i][j] = ss[j] + ",";	
				}
			}
		}
		
		String arrst = "";
		String[] arrstr1 = new String[arrstr.length];
		for (int i = 0; i < arrstr.length ; i++) {
			for (int j = 0; j < arrstr[i].length; j++) {
				arrst += arrstr[i][j];
			}
			arrstr1[i] = arrst;
			arrst = "";
		}
		
		String textss = login.getSubject()+",IdName,,";
		for (int i = 0; i < points.stringName().size() ; i++) {
			textss += points.stringName().get(i) + ",,";
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
		}*/
	}
	
}
