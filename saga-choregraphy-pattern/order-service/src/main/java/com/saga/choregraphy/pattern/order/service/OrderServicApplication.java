package com.saga.choregraphy.pattern.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServicApplication {

    private static Logger logger = LoggerFactory.getLogger(OrderServicApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(OrderServicApplication.class);
        logger.info("OrderServicApplication up and running !");
    }
}