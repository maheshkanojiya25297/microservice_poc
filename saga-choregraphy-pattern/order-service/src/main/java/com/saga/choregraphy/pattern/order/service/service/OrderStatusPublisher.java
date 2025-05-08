package com.saga.choregraphy.pattern.order.service.service;

import com.saga.choregraphy.pattern.common.dto.OrderRequestDto;
import com.saga.choregraphy.pattern.common.event.OrderEvent;
import com.saga.choregraphy.pattern.common.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
