package com.twilio.twilioPushNotification;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TwilioInitiazer {

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioInitiazer(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
        log.info("Twilio initialized...with account sid{}", twilioConfiguration.getAccountSid());
    }
}
