package com.saga.choregraphy.pattern.payment.service.repository;

import com.saga.choregraphy.pattern.payment.service.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {
}
