package com.saga.choregraphy.pattern.order.service.service;

import com.saga.choregraphy.pattern.common.dto.OrderRequestDto;
import com.saga.choregraphy.pattern.common.event.OrderStatus;
import com.saga.choregraphy.pattern.order.service.entity.PurchaseOrder;
import com.saga.choregraphy.pattern.order.service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;


    //for single transaction execution
    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(orderRequestDto.getProductId());
        purchaseOrder.setUserId(orderRequestDto.getUserId());
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(orderRequestDto.getAmount());

        PurchaseOrder order = orderRepository.save(purchaseOrder);
        orderRequestDto.setOrderId(order.getId());

        //produce kafka event with status ORDER_CREATED
        orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
        return order;
    }


    public List<PurchaseOrder> getAllOrder() {
        return orderRepository.findAll();
    }

}
