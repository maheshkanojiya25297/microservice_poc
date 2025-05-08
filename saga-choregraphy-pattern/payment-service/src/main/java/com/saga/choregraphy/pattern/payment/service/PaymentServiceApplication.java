package com.saga.choregraphy.pattern.payment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentServiceApplication {

    private static Logger logger = LoggerFactory.getLogger(PaymentServiceApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(PaymentServiceApplication.class);
        logger.info("PaymentServiceApplication up and running !");
    }

}
