package com.email.SMTP.EmailServiceNotification.controllers;

import com.email.SMTP.EmailServiceNotification.entities.EmailDetails;
import com.email.SMTP.EmailServiceNotification.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails emailDetails) {
        log.info("sendMail--------------->: ");
        String status = this.emailService.sendSimpleMail(emailDetails);
        log.info("status----------------->:" +status);
        return status;
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails emailDetails) {
        log.info("sendMailWithAttachment--------------->: ");
        String status = this.emailService.sendMailWithAttachment(emailDetails);
        log.info("status----------------->:" +status);
        return status;
    }
}
