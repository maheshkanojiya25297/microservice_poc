package com.redis.cache.apply.services;

import com.redis.cache.apply.entities.Customer;

import java.util.List;

public interface CustomerService {

    Customer AddCustomer(Customer customer);

    Customer GetByCustomerId(long id);
}
