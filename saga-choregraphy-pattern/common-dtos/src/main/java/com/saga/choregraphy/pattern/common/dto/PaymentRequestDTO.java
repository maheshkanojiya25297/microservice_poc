package com.saga.choregraphy.pattern.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    private Integer orderId;
    private Integer userId;
    private Integer amount;
}
