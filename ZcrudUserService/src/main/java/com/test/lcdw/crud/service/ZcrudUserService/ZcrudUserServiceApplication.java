package com.test.lcdw.crud.service.ZcrudUserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class ZcrudUserServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(ZcrudUserServiceApplication.class, args);
		System.out.println("The Crud Service Processing..");
	}

}
