package com.saga.choregraphy.pattern.payment.service.repository;

import com.saga.choregraphy.pattern.payment.service.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
}
