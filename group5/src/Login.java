
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	private JTextField id;
	private JButton login;
	private JButton cancel;
	private boolean value = false;
	private JPasswordField textPassword;
	private static String subject = "";
	private ArrayList<String> text = new ArrayList<>();
	private String[][] user;
	private Points point;
	private TotalscoretermFrame f4;
	private static String valuename = "";
	public Login() {
		// TODO Auto-generated constructor stub
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel pall = new JPanel();

		textPassword = new JPasswordField(10);
		id = new JTextField(10);	
		readfile();
		id.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!id.getText().equals("") || !textPassword.getText().equals("")) {
					valueIdandPassword();
					File f = new File("Datascore.csv");
					if(!f.exists())
					readTextFile();
					inputValue();
				}
			}
			});
		textPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!id.getText().equals("") || !textPassword.getText().equals("")) {
					valueIdandPassword();
					File f = new File("Datascore" + subject + ".csv");
					if(!f.exists())
					readTextFile();
					inputValue();
				}
			}
		});
		login = new JButton("Login");

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				valueIdandPassword();
				File f = new File("Datascore" + subject + ".csv");
				if(!f.exists())
				readTextFile();
				inputValue();
			}
			
		});
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		pall.setBorder(BorderFactory.createTitledBorder(null, "LOGIN"));
		p.add(login);
		p.add(cancel);
		p1.add(new JLabel("Username"));
		p1.add(id);
		p2.add(new JLabel("Password"));
		p2.add(textPassword);
		pall.setLayout(new GridLayout(3, 2));
		pall.add(p1);
		pall.add(p2);
		pall.add(p);
		p3.add(pall);
		add(p3, BorderLayout.CENTER);
		setLocation(770, 420);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 190);
		setVisible(true);
	}

	public static String getSubject() {
		return subject;
	}

	public void readfile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Datausername.csv"));
			String s = reader.readLine();
			while (s != null) {
				text.add(s);
				s = reader.readLine();
			}
			reader.close();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		user = new String[text.size()][4];
		for (int i = 0; i < text.size(); i++) {
			String[] t = text.get(i).split(",");
			user[i][0] = t[0];
			user[i][1] = t[1];
			user[i][2] = t[2];
			user[i][3] = t[3];
		}
	}
	
	public void valueIdandPassword() {
		
		boolean username = false ;
		boolean password = false ;
		String textname;
		for (int i = 0; i < user.length; i++) {
			textname = id.getText();
			String[] text = textname.split(" ");
			if (text[0].equals(user[i][1]))
				username = true;
			if (textPassword.getText().equals(user[i][2]))
				password = true;
			if (username && password) {
				subject = user[i][0];
				value = true;
				valuename = user[i][3];
				break;
			}
			username = false;
			password = false;
		}
		
	}
	
	public boolean inputValue() {
		if(value) {
			setVisible(false);
			if (point.getcheck()) {
				f4 = new TotalscoretermFrame();
				f4.setVisible(true);
				setVisible(false);
			} else {
				point = new Points();
				point.setVisible(true);
				setVisible(false);
			}
		}else {
			JOptionPane.showMessageDialog(null, "INPUT ID & PASSWORD AGAIN");
			id.setText("");
			textPassword.setText("");
		}
		return value;
	}
	
	public void readTextFile() {
		ArrayList<String> t = new ArrayList<>();
		try {
			FileReader fr = new FileReader(new File("classlist.csv"));
			BufferedReader reader = new BufferedReader(fr);
			String s = reader.readLine();
			String ts = "";
			while (s != null) {
				String ss[] = s.split(",");
				if(ss.length!=0 && ss.length < 6) {
					String[] sss = ss[2].split(" ");
					for (int i = 0; i < sss.length; i++) {
						ts += sss[i] + ",";					
					}
					t.add(ss[1] + "," + ts);
					ts = "";
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
		
		try {
			FileWriter fw = new FileWriter(new File("Datascore" + subject + ".csv"));
			PrintWriter writer = new PrintWriter(fw);
				writer.println(getSubject());			
			for (int i = 0; i < t.size(); i++) {
				writer.println(t.get(i));
			}
			fw.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getValueName() {
		return valuename;
	}
	
	public static void main(String[] args) {
		new Login();
	}
}
