
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Sendemail {
	private String textTest = "";
	public Sendemail(String text,String email,String subject) {
		// TODO Auto-generated constructor stub
			textTest = text;
			
	       	final String username = "cs284.ttest@gmail.com";
	        final String password = "cs284123";

	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(username));
	            message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(email));
	            message.setSubject("Grade Subject "+subject);
	            message.setText(text);

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	}
	
	public Sendemail(String email) {
	       final String username = "cs284.ttest@gmail.com";
	        final String password = "cs284123";

	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(username));
	            message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(email));
	            message.setSubject("Grade");
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setText("Hello Mr.Songsak");
	            Multipart multipart = new MimeMultipart();
	            multipart.addBodyPart(messageBodyPart);
	            messageBodyPart = new MimeBodyPart();
	            DataSource source = new FileDataSource("C:\\Users\\Moss\\git\\groupFivePinkyPinky\\group5\\Grade"+Login.getSubject()+".csv");
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName("Datascore.csv");
	            multipart.addBodyPart(messageBodyPart);
	             
	            message.setContent(multipart);
	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	}
	
	public String getTexttest() {
		return textTest;
	}
	

}