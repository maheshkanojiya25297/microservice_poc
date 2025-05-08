package com.saga.choregraphy.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SagaChoregraphyPatternApplication {

	private static Logger logger = LoggerFactory.getLogger(SagaChoregraphyPatternApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(SagaChoregraphyPatternApplication.class, args);
		logger.info("SagaChoregraphyPatternApplication service up and running !");
	}

}
