package com.saga.choregraphy.pattern.payment.service.config;

import com.saga.choregraphy.pattern.common.event.OrderEvent;
import com.saga.choregraphy.pattern.common.event.OrderStatus;
import com.saga.choregraphy.pattern.common.event.PaymentEvent;
import com.saga.choregraphy.pattern.payment.service.service.PaymentService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {


    @Autowired
    private PaymentService paymentService;

    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        // get the user id
        // check the balance availbility
        //if balance sufficient then payment completed
        //deduct amount from db
        //if payment failed and not sufficent then cancel it and update the amount in db
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
            return Mono.fromSupplier(() -> this.paymentService.newOrderEvent(orderEvent));
        }else {
            return  Mono.fromRunnable(() -> this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
