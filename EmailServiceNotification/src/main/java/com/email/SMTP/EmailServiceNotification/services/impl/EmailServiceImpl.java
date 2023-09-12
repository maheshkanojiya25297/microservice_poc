package com.email.SMTP.EmailServiceNotification.services.impl;

import com.email.SMTP.EmailServiceNotification.entities.EmailDetails;
import com.email.SMTP.EmailServiceNotification.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Override
    public String sendSimpleMail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            log.info("sender: " + sender);
            simpleMailMessage.setTo(emailDetails.getRecipients());
            log.info("emailDetails.getRecipients(): " + emailDetails.getRecipients());
            simpleMailMessage.setText(emailDetails.getMsgBody());
            log.info("emailDetails.getMsgBody(): " + emailDetails.getMsgBody());
            simpleMailMessage.setSubject(emailDetails.getSubject());
            log.info("emailDetails.getSubject(): " + emailDetails.getSubject());
            javaMailSender.send(simpleMailMessage);
            log.info("simpleMailMessage: " + simpleMailMessage);
            return "Mail Sent Successfully....";
        } catch (Exception e) {
            return "Error";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails emailDetails) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //MimeMessageHelper mimeMessageHelper;
            //mimeMessageHelper = new MimeMessageHelper(MimeMessage);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            log.info("sender: " + sender);
            mimeMessageHelper.setTo(emailDetails.getRecipients());
            log.info("emailDetails.getRecipients(): " + emailDetails.getRecipients());
            mimeMessageHelper.setText(emailDetails.getMsgBody());
            log.info("emailDetails.getMsgBody(): " + emailDetails.getMsgBody());
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            log.info("emailDetails.getSubject(): " + emailDetails.getSubject());

            FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
