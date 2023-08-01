package com.hazel.cast.cache.controllers;

import com.hazel.cast.cache.entities.UserAccount;
import com.hazel.cast.cache.exceptions.ResourceNotFoundException;
import com.hazel.cast.cache.repositories.UserAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        logger.info("fetchAllUserDetails {}:" + userAccounts);
        return ResponseEntity.status(HttpStatus.OK).body(userAccounts);
    }

    @GetMapping(path = {"/get/{id}"})
    @Cacheable("UserAccount")
    public ResponseEntity<Optional<UserAccount>> getUser(@PathVariable("id") long id) throws InterruptedException {
        sleep(2);
        Optional<UserAccount> userAccount = Optional.ofNullable(userAccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with this Id doesn't exists in the databases!")));
        logger.info("getUser {}:" + userAccount);
        return ResponseEntity.ok().body(userAccount);
    }


    @DeleteMapping(path = {"/{id}"})
    @CacheEvict(value = "UserAccount", allEntries = true)
    public String deleteUser(@PathVariable("id") long id) {
        //remove from both cache and database
        //accountMap.remove(id);
        userAccountRepository.deleteById(id);
        logger.info("deleteUser {}:" + id);
        return "deleted !";
    }

    @PostMapping("/add")
    @CacheEvict(value = "UserAccount", allEntries = true)
    public ResponseEntity<String> createUser(@RequestBody UserAccount userAccount) {
        //save user account in cache
        //accountMap.put(userAccount.getAccountNumber(), userAccount);
        userAccountRepository.save(userAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body("Data Saved !");
    }

    @PutMapping("/{id}")
    //@CachePut(value = "UserAccount", key = "#id")
    @CacheEvict(value = "UserAccount", allEntries = true)
    public ResponseEntity<UserAccount> updateUser(@PathVariable("id") long id, @RequestBody UserAccount userAccount) {
        UserAccount usrAcc = userAccountRepository.findById(id).orElseThrow();
        usrAcc.setAccountNumber(userAccount.getAccountNumber());
        usrAcc.setName(userAccount.getName());
        usrAcc.setAddress(userAccount.getAddress());
        usrAcc.setBalance(userAccount.getBalance());

        UserAccount output = userAccountRepository.save(usrAcc);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }


}
