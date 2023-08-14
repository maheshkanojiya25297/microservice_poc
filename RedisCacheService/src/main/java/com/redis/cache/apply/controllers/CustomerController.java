package com.redis.cache.apply.controllers;

import com.redis.cache.apply.entities.Customer;
import com.redis.cache.apply.repositories.CustomerRepositories;
import com.redis.cache.apply.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    private String AddCustomer(@RequestBody Customer customer) {
        String responseMessage = customerService.AddCustomer(customer);
        return responseMessage;
    }


    @GetMapping("/{id}")
    @Cacheable(value = "Customer", key = "#id")
    private ResponseEntity<Customer> GetByCustomerId(@PathVariable("id") long id) {
        log.info("AddCustmer: {}");
        Customer customer = customerService.GetByCustomerId(id);
        log.info("customer1: {}" + customer);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }


    @GetMapping
    @Cacheable("Customer")
    private ResponseEntity<List<Customer>> gatAllCustome() {
        List<Customer> customers = customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }


    @DeleteMapping("{id}")
    @CacheEvict(value = "Customer", key = "#id", allEntries = true)
    private String deleteByCustomerId(@PathVariable long id) {
        customerService.deleteByCustomerId(id);
        return "Customer" + id + "deleted Successfully! ";
    }

}
