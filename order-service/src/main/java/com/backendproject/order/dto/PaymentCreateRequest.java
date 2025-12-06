package com.backendproject.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentCreateRequest {
    private double amount;
    private String email;
    private String receipt;
}
