package com.email.controller;

import com.email.model.EmailRequest;
import com.email.service.EmailApiService;
import com.email.service.EmailApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@CrossOrigin
public class EmailApiController {

    @Autowired
    private EmailApiService emailService;

    @RequestMapping("/welcome")
     public String welcome(){
        return "this is my testing purpose";
     }

     @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
     public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) throws MessagingException, IOException {
          System.out.println(request);
          boolean result =this.emailService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
 if(result)
 {
         return ResponseEntity.ok("Email Sent SuccessFully..");}
 else{
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not Sent..");
 }

     }
     }
