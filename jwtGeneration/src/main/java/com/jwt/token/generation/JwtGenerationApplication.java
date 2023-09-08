package com.jwt.token.generation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class JwtGenerationApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtGenerationApplication.class, args);
    }
}
