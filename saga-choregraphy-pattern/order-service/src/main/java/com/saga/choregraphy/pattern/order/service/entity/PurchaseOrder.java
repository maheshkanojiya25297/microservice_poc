package com.saga.choregraphy.pattern.order.service.entity;

import com.saga.choregraphy.pattern.common.event.OrderStatus;
import com.saga.choregraphy.pattern.common.event.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PURCHASE_ORDER_TBL")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
