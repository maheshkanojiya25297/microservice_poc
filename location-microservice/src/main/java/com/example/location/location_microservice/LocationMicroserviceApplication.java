package com.example.location.location_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocationMicroserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(LocationMicroserviceApplication.class, args);

		System.out.println("Location Microservice Booted........................");
	}

}
