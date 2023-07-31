package com.hazel.cast.cache.controllers;

import com.hazel.cast.cache.entities.UserAccount;
import com.hazel.cast.cache.exceptions.ResourceNotFoundException;
import com.hazel.cast.cache.repositories.UserAccountRepository;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import static java.lang.Thread.sleep;

@RestController
public class UserAccountController {

    private static final String ACCOUNTS = "accounts";
    @Autowired
    private UserAccountRepository userAccountRepository;

    private Logger logger = LoggerFactory.getLogger(UserAccountController.class);
    // @Autowired
    // private Map<String, UserAccount> accountMap;


    @GetMapping("/get")
    @Cacheable("UserAccount")
    public ResponseEntity<List<UserAccount>> fetchAllUserDetails() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        logger.info("fetchAllUserDetails {}:" +userAccounts);
        return ResponseEntity.
                status(HttpStatus.OK).body(userAccounts);
    }


    @GetMapping(path = {"/get/{id}"})
    //@Cacheable(value = ACCOUNTS, key = "#id")
    @Cacheable("UserAccount")
    public ResponseEntity<Optional<UserAccount>> getUser(@PathVariable("id") long id) throws InterruptedException {
        //first check if accountMap has the userAccount details, if yes then return it. Else fetch it from database.
        sleep(2);
        Optional<UserAccount> userAccount = Optional.ofNullable(userAccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with this Id doesn't exists in the databases!")));
        //(accountMap.get(id) != null) ? Optional.ofNullable(accountMap.get(id)) : userAccountRepository.findById(id);
        logger.info("getUser {}:" +userAccount);
        return ResponseEntity.ok().body(userAccount);
    }


    @DeleteMapping(path = {"/{id}"})
    @CacheEvict(value = ACCOUNTS, key = "#id")
    public String deleteUser(@PathVariable("id") long id) {
        //remove from both cache and database
        //accountMap.remove(id);
        userAccountRepository.deleteById(id);
        logger.info("deleteUser {}:" +id);
        return "deleted !";
    }

    @PostMapping("/add")
    public void createUser(@RequestBody UserAccount userAccount) {
        //save user account in cache
        //accountMap.put(userAccount.getAccountNumber(), userAccount);
        userAccountRepository.save(userAccount);
    }
}
