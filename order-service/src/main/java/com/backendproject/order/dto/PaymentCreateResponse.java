package com.backendproject.order.dto;

import lombok.Data;

@Data
public class PaymentCreateResponse {
    private String orderId;
    private String status;
    private String message;
}
