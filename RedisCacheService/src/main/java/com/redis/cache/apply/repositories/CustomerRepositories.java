package com.redis.cache.apply.repositories;

import com.redis.cache.apply.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositories extends JpaRepository<Customer, Long> {
}
