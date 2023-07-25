package com.implemented.user.service.controllers;

import com.implemented.user.service.entities.User;
import com.implemented.user.service.services.UserServices;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    int retryCount = 1;


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    @GetMapping("/{userId}")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userServices.getSingleUser(userId);
        return ResponseEntity.ok(user);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> user = userServices.getAllUser();
        return ResponseEntity.ok(user);
    }


    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        //logger.info("fallaback method executed because of service is down: " + ex.getMessage());
        ex.printStackTrace();

        User user = User.builder()
                .email("dummy@gmail.com")
                .about("This user is created as dummy because some service is down !")
                .name("dummy")
                .userId("7160")
                .build();
        return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
    }

}
