package com.saga.choregraphy.pattern.order.service.controller;

import com.saga.choregraphy.pattern.common.dto.OrderRequestDto;
import com.saga.choregraphy.pattern.order.service.entity.PurchaseOrder;
import com.saga.choregraphy.pattern.order.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping
    public List<PurchaseOrder> getOrder() {
        return orderService.getAllOrder();
    }
}
