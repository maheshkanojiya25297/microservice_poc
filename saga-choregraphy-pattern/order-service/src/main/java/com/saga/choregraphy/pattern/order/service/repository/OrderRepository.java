package com.saga.choregraphy.pattern.order.service.repository;

import com.saga.choregraphy.pattern.order.service.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {


}
