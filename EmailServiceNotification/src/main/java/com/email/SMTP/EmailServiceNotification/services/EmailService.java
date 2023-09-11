package com.email.SMTP.EmailServiceNotification.services;

import com.email.SMTP.EmailServiceNotification.entities.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails emailDetails);
    String sendMailWithAttachment(EmailDetails emailDetails);
}
