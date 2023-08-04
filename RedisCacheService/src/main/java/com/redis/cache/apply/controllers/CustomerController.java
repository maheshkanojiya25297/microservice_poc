package com.redis.cache.apply.controllers;

import com.redis.cache.apply.entities.Customer;
import com.redis.cache.apply.repositories.CustomerRepositories;
import com.redis.cache.apply.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping
    private ResponseEntity<Customer> AddCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.AddCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer1);
    }


    @GetMapping("/{id}")
    private ResponseEntity<Customer> GetByCustomerId(@PathVariable("id") long id) {
        Customer customer = customerService.GetByCustomerId(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

}
