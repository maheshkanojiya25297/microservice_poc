package com.hazel.cast.cache;

import com.hazel.cast.cache.entities.UserAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@SpringBootApplication
@EnableCaching
public class HazelCastCacheServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(HazelCastCacheServiceApplication.class, args);
        System.out.println("Hazel Cast Service Starting...............");
    }

  /*  @Bean
    public Map<String, UserAccount> accountMap() {
        return new HashMap<>();
    }*/

}

