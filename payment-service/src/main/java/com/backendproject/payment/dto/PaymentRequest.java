package com.backendproject.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private double amount;
    private String email;
    private String receipt;
}
