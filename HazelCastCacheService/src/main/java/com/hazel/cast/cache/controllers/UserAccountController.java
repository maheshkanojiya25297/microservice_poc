package com.hazel.cast.cache.controllers;

import com.hazel.cast.cache.entities.UserAccount;
import com.hazel.cast.cache.repositories.UserAccountRepository;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import static java.lang.Thread.sleep;

@RestController
public class UserAccountController {

    private static final String ACCOUNTS = "accounts";
    @Autowired
    private UserAccountRepository userAccountRepository;

    // @Autowired
    // private Map<String, UserAccount> accountMap;


    @GetMapping(path = {"/get/{id}"})
    @Cacheable(value = ACCOUNTS, key = "#id")
    public ResponseEntity<Optional<UserAccount>> getUser(@PathVariable("id") long id) throws Exception {
        //first check if accountMap has the userAccount details, if yes then return it. Else fetch it from database.
        sleep(2);
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        //(accountMap.get(id) != null) ? Optional.ofNullable(accountMap.get(id)) : userAccountRepository.findById(id);
        return ResponseEntity.ok().body(userAccount);
    }

    @DeleteMapping(path = {"/{id}"})
    @CacheEvict(value = ACCOUNTS, key = "#id")
    public String deleteUser(@PathVariable("id") long id) {
        //remove from both cache and database
        //accountMap.remove(id);
        userAccountRepository.deleteById(id);
        return "deleted !";
    }

    @PostMapping("/add")
    public void createUser(@RequestBody UserAccount userAccount) {
        //save user account in cache
        //accountMap.put(userAccount.getAccountNumber(), userAccount);
        userAccountRepository.save(userAccount);
    }
}
