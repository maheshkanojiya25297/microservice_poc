package com.email.SMTP.EmailServiceNotification.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private String recipients;
    private String msgBody;
    private String subject;
    private String attachment;

}
