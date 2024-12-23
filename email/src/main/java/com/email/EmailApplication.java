package com.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class EmailApplication {

	public static void main(String[] args) throws MessagingException, IOException {


		String message= "Hi, Where are you !?";
		String subject = "secret";
		String to = "mahesh7666417769@gmail.com";
		String from = "153478life@gmail.com";
		//sendEmail(message,subject,to,from);

		SpringApplication.run(EmailApplication.class, args);
		System.out.println("booted...");
	}

	private static void sendEmail(String message, String subject, String to, String from) throws MessagingException, IOException {
	    //variable for gmail
		String host ="smtp.gmail.com";
		//get the system properties
		Properties properties = System.getProperties();

        //setting important information to properties object
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		System.out.println("properties :" +properties);
		Session session =Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("153478life@gmail.com", "quzl kitj gsvy wkuo");
		}
		});

		session.setDebug(true);

		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(from);
		mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		mimeMessage.setSubject(subject);
		String path = "C:\\Users\\User\\Pictures\\lap.png";
		MimeMultipart mimeMultipart = new MimeMultipart();
		MimeBodyPart textMime = new MimeBodyPart();
		textMime.setText(message);
		MimeBodyPart fileMime = new MimeBodyPart();
		File file = new File(path);
		fileMime.attachFile(file);
    	mimeMultipart.addBodyPart(textMime);
     	mimeMultipart.addBodyPart(fileMime);

		mimeMessage.setContent(mimeMultipart);
		//mimeMessage.setText(message);
        Transport.send(mimeMessage);
		System.out.println("Mail Sent Sucess...........");
	}

}
