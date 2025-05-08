package com.saga.choregraphy.pattern.order.service.config;

import com.saga.choregraphy.pattern.common.event.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfig {

    @Autowired
    private OrderStatusUpdateHandler orderStatusUpdateHandler;

    public Consumer<PaymentEvent> paymentEventConsumer() {
        //listen to the payment-event-topic
        //will check payment status
        //if payment status completed then complete the order
        //if payment status failed then cancel the order
        return (payment) -> orderStatusUpdateHandler.updateOrder(payment.getPaymentRequestDto().getOrderId(),
                po -> {
                    po.setPaymentStatus(payment.getPaymentStatus());
                });
    }
}
