package com.email.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;
import java.io.IOException;


@Service
public class EmailApiService {

    public boolean sendEmail(String to, String subject, String message ) throws MessagingException, IOException {

        boolean f = false;
        String from = "153478life@gmail.com";
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
        mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to.trim()));
        mimeMessage.setSubject(subject);
        String path = "C:\\Users\\User\\Pictures\\lap.png";
        MimeMultipart mimeMultipart = new MimeMultipart();
        MimeBodyPart textMime = new MimeBodyPart();

        String sanitizedMessage = message.trim().replaceAll("[\\p{Cntrl}]", "");
        //mimeMessage.setText(sanitizedMessage);
        textMime.setText(sanitizedMessage);
        MimeBodyPart fileMime = new MimeBodyPart();
        File file = new File(path);
        fileMime.attachFile(file);
        mimeMultipart.addBodyPart(textMime);
        mimeMultipart.addBodyPart(fileMime);

        mimeMessage.setContent(mimeMultipart);
       // mimeMessage.setText(message);
        Transport.send(mimeMessage);
        System.out.println("Upgraded Mail Sent Sucess...........");
        f=  true;
        return f;
    }
}
