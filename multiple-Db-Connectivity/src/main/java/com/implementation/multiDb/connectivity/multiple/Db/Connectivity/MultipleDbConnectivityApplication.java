package com.implementation.multiDb.connectivity.multiple.Db.Connectivity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MultipleDbConnectivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDbConnectivityApplication.class, args);
        System.out.println("Service started! ");
    }

}
