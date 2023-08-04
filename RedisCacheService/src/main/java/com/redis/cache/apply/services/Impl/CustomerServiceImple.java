package com.redis.cache.apply.services.Impl;

import com.redis.cache.apply.entities.Customer;
import com.redis.cache.apply.repositories.CustomerRepositories;
import com.redis.cache.apply.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImple implements
        CustomerService {

    private Logger logger = LoggerFactory.getLogger(CustomerServiceImple.class);

    @Autowired
    private CustomerRepositories customerRepositories;


    @Override
    public Customer AddCustomer(Customer customer) {
        Customer customer1 = customerRepositories.save(customer);
        logger.info("CustomerController method calling {} AddCustomer: " + customer1);
        return customer1;
    }

    @Override
    @Cacheable(cacheNames = "customers", key = "#id")
    public Customer GetByCustomerId(long id) {
        Customer customer = customerRepositories.getById(id);
        logger.info("CustomerController method calling {} GetByCustomerId: " + customer);
        return customer;
    }
}
