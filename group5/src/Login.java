
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
import java.io.IOException;
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
		id.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(!id.getText().equals("") || !textPassword.getText().equals("")) {
						valueIdandPassword();
						inputValue();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		textPassword.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(!id.getText().equals("") || !textPassword.getText().equals("")) {
						valueIdandPassword();
						inputValue();
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		login = new JButton("Login");

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				valueIdandPassword();
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

	public static void main(String[] args) {
		new Login();
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
		user = new String[text.size()][3];
		for (int i = 0; i < text.size(); i++) {
			String[] t = text.get(i).split(",");
			user[i][0] = t[0];
			user[i][1] = t[1];
			user[i][2] = t[2];
		}
	}
	
	public void valueIdandPassword() {
		
		boolean username = false ;
		boolean password = false ;
		for (int i = 0; i < user.length; i++) {
			if (id.getText().equals(user[i][1]))
				username = true;
			if (textPassword.getText().equals(user[i][2]))
				password = true;
			if (username && password) {
				subject = user[i][0];
				value = true;
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
			} else {
				point = new Points();
				point.setVisible(true);
			}
		}else {
			JOptionPane.showMessageDialog(null, "IN PUT ID & PASSWORD AGAIN");
			id.setText("");
			textPassword.setText("");
		}
		return value;
	}
}
