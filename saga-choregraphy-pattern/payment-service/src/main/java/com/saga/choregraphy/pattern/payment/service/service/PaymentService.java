package com.saga.choregraphy.pattern.payment.service.service;

import com.saga.choregraphy.pattern.common.dto.OrderRequestDto;
import com.saga.choregraphy.pattern.common.dto.PaymentRequestDTO;
import com.saga.choregraphy.pattern.common.event.OrderEvent;
import com.saga.choregraphy.pattern.common.event.PaymentEvent;
import com.saga.choregraphy.pattern.common.event.PaymentStatus;
import com.saga.choregraphy.pattern.payment.service.entity.UserBalance;
import com.saga.choregraphy.pattern.payment.service.entity.UserTransaction;
import com.saga.choregraphy.pattern.payment.service.repository.UserBalanceRepository;
import com.saga.choregraphy.pattern.payment.service.repository.UserTransactionRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDB() {
        System.out.println("Initializing user balances...");
        userBalanceRepository.saveAll(
                Stream.of(
                        new UserBalance(101, 5000),
                        new UserBalance(102, 3000),
                        new UserBalance(103, 4000),
                        new UserBalance(104, 7000),
                        new UserBalance(105, 7001),
                        new UserBalance(106, 7002),
                        new UserBalance(107, 7003),
                        new UserBalance(108, 7004),
                        new UserBalance(109, 7005),
                        new UserBalance(110, 7006),
                        new UserBalance(111, 7007)
                ).collect(Collectors.toList())
        );
    }

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(orderRequestDto.getOrderId(), orderRequestDto.getUserId(), orderRequestDto.getAmount());

        return userBalanceRepository.findById(orderRequestDto.getUserId())
                .filter(ub -> ub.getPrice() > orderRequestDto.getAmount())
                .map(ub -> {
                    ub.setPrice(ub.getPrice() - orderRequestDto.getAmount());
                    userTransactionRepository.save(new UserTransaction(orderRequestDto.getOrderId(), orderRequestDto.getUserId(), orderRequestDto.getAmount()));
                    return new PaymentEvent(paymentRequestDTO, PaymentStatus.PAYMENT_COMPLETED);
                })
                .orElse(new PaymentEvent(paymentRequestDTO, PaymentStatus.PAYMENT_FAILED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
                .ifPresent(ut -> {
                    userTransactionRepository.delete(ut);
                    userBalanceRepository.findById(ut.getUserId())
                            .ifPresent(ub -> ub.setPrice(ub.getPrice() + ut.getAmount()));
                });
    }
}
